/**
   Copyright [2013] [Mushroom]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/**
 Notice : this source is extracted from Hadoop metric2 package
 and some source code may changed by zavakid
 */
package com.zavakid.mushroom.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import com.zavakid.mushroom.Metric;
import com.zavakid.mushroom.MetricsRecord;
import com.zavakid.mushroom.MetricsSink;
import com.zavakid.mushroom.MetricsTag;
import com.zavakid.mushroom.MoreAsserts;
import com.zavakid.mushroom.lib.AbstractMetricsSource;
import com.zavakid.mushroom.lib.MetricMutableCounterLong;
import com.zavakid.mushroom.lib.MetricMutableDeltaLong;
import com.zavakid.mushroom.lib.MetricMutableGaugeLong;
import com.zavakid.mushroom.lib.MetricMutableStat;
import com.zavakid.mushroom.util.FileUtils;

/**
 * Test the MetricsSystemImpl class
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMetricsSystemImpl {

    private static final Log              LOG         = LogFactory.getLog(TestMetricsSystemImpl.class);
    static final Collection<File>         NEED_DELETE = new HashSet<File>();

    @Captor
    private ArgumentCaptor<MetricsRecord> r1;
    @Captor
    private ArgumentCaptor<MetricsRecord> r2;
    @Captor
    private ArgumentCaptor<MetricsRecord> r3;
    private static String                 hostname    = MetricsSystemImpl.getHostname();

    @Test
    public void testInitFirst() throws Exception {
        String fileName = TestMetricsConfig.getTestFilename("mushroom-metric-test");
        ConfigBuilder cb = new ConfigBuilder().add("default.period", 8)
                                              .add("source.filter.class", "com.zavakid.mushroom.filter.GlobFilter")
                                              .add("test.*.source.filter.class", "${source.filter.class}")
                                              .add("test.*.source.filter.exclude", "s1*")
                                              .add("test.sink.sink3.source.filter.class", "${source.filter.class}")
                                              .add("test.sink.sink3.source.filter.exclude", "s2*").save(fileName);
        NEED_DELETE.add(new File(fileName));

        MetricsSystemImpl ms = new MetricsSystemImpl("Test");
        ms.start();
        TestSource s1 = ms.register("s1", "s1 desc", new TestSource("s1rec"));
        TestSource s2 = ms.register("s2", "s2 desc", new TestSource("s2rec"));
        ms.registerIfAbsent("s3", "s3 desc", new TestSource("s3rec"));
        TestSource s4 = ms.registerIfAbsent("s3", "s3 desc", new TestSource("s3rec"));
        TestSource s5 = ms.registerIfAbsent("s3", "s3 desc", new TestSource("s3rec"));
        s1.s1.add(0);
        s1.s1.add(0);
        s2.s1.add(0);
        s2.s1.add(0);
        s4.s1.add(0);
        s5.s1.add(0);
        MetricsSink sink1 = mock(MetricsSink.class);
        MetricsSink sink2 = mock(MetricsSink.class);
        MetricsSink sink3 = mock(MetricsSink.class);
        ms.register("sink1", "sink1 desc", sink1);
        ms.register("sink2", "sink2 desc", sink2);
        ms.register("sink3", "sink3 desc", sink3);
        ms.onTimerEvent(); // trigger something interesting
        ms.stop();

        verify(sink1, times(3)).putMetrics(r1.capture()); // 2 + 1 sys source
        List<MetricsRecord> mr1 = r1.getAllValues();
        verify(sink2, times(3)).putMetrics(r2.capture()); // ditto
        List<MetricsRecord> mr2 = r2.getAllValues();
        verify(sink3, times(2)).putMetrics(r3.capture()); // 1 + 1 (s1, s2 filtered)
        List<MetricsRecord> mr3 = r3.getAllValues();
        checkMetricsRecords(mr1, "s2rec");
        assertEquals("output", mr1, mr2);
        checkMetricsRecords(mr3, "s3rec");
    }

    static void checkMetricsRecords(List<MetricsRecord> recs, String expected) {
        LOG.debug(recs);
        MetricsRecord r = recs.get(0);
        assertEquals("name", expected, r.name());
        MoreAsserts.assertEquals("tags", new MetricsTag[] { new MetricsTag("context", "Metrics context", "test"),
                new MetricsTag("hostName", "Local hostname", hostname), }, r.tags());
        MoreAsserts.assertEquals("metrics", new Metric[] { new MetricCounterLong("c1", "c1 desc", 1),
                new MetricDeltaLong("d1", "d1 desc", 3L), new MetricGaugeLong("g1", "g1 desc", 2),
                new MetricCounterLong("s1_num_ops", "Number of ops for s1 desc", 2),
                new MetricGaugeDouble("s1_avg_time", "Average time for s1 desc", 0) }, r.metrics());

        // Skip the system metrics for now.
        // MetricsRecord r1 = recs.get(1);
    }

    private static class TestSource extends AbstractMetricsSource {

        final MetricMutableCounterLong c1;
        final MetricMutableDeltaLong   d1;
        final MetricMutableGaugeLong   g1;
        final MetricMutableStat        s1;

        TestSource(String name){
            super(name);
            registry.setContext("test");
            c1 = registry.newCounter("c1", "c1 desc", 1L);
            d1 = registry.newDelta("d1", "d1 desc", 3L);
            g1 = registry.newGauge("g1", "g1 desc", 2L);
            s1 = registry.newStat("s1", "s1 desc", "ops", "time");
        }
    }

    @AfterClass
    public static void deleteResource() {
        FileUtils.deleteFiles(NEED_DELETE);
    }

}

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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import com.zavakid.mushroom.Metric;
import com.zavakid.mushroom.MetricCounter;
import com.zavakid.mushroom.MetricDelta;
import com.zavakid.mushroom.MetricGauge;
import com.zavakid.mushroom.MetricsVisitor;

/**
 * Test the metric visitor interface
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMetricsVisitor {

    @Captor
    private ArgumentCaptor<MetricCounter<Integer>> c1;
    @Captor
    private ArgumentCaptor<MetricCounter<Long>>    c2;
    @Captor
    private ArgumentCaptor<MetricDelta<Integer>>   d1;
    @Captor
    private ArgumentCaptor<MetricDelta<Long>>      d2;
    @Captor
    private ArgumentCaptor<MetricGauge<Integer>>   g1;
    @Captor
    private ArgumentCaptor<MetricGauge<Long>>      g2;
    @Captor
    private ArgumentCaptor<MetricGauge<Float>>     g3;
    @Captor
    private ArgumentCaptor<MetricGauge<Double>>    g4;

    /**
     * Test the common use cases
     */
    @Test
    public void testCommon() {
        MetricsVisitor visitor = mock(MetricsVisitor.class);
        List<Metric> metrics = Arrays.asList(new MetricCounterInt("c1", "int counter", 1),
                                             new MetricCounterLong("c2", "long counter", 2L),
                                             new MetricDeltaInt("d1", "int delta", 3),
                                             new MetricDeltaLong("d2", "long delta", 4L),
                                             new MetricGaugeInt("g1", "int gauge", 5),
                                             new MetricGaugeLong("g2", "long gauge", 6L),
                                             new MetricGaugeFloat("g3", "float gauge", 7f),
                                             new MetricGaugeDouble("g4", "double gauge", 8d));

        for (Metric metric : metrics) {
            metric.visit(visitor);
        }

        verify(visitor).counter(c1.capture(), eq(1));
        assertEquals("c1 name", "c1", c1.getValue().name());
        assertEquals("c1 description", "int counter", c1.getValue().description());

        verify(visitor).counter(c2.capture(), eq(2L));
        assertEquals("c2 name", "c2", c2.getValue().name());
        assertEquals("c2 description", "long counter", c2.getValue().description());

        verify(visitor).delta(d1.capture(), eq(3));
        assertEquals("d1 name", "d1", d1.getValue().name());
        assertEquals("d1 description", "int delta", d1.getValue().description());

        verify(visitor).delta(d2.capture(), eq(4L));
        assertEquals("d2 name", "d2", d2.getValue().name());
        assertEquals("d2 description", "long delta", d2.getValue().description());

        verify(visitor).gauge(g1.capture(), eq(5));
        assertEquals("g1 name", "g1", g1.getValue().name());
        assertEquals("g1 description", "int gauge", g1.getValue().description());

        verify(visitor).gauge(g2.capture(), eq(6L));
        assertEquals("g2 name", "g2", g2.getValue().name());
        assertEquals("g2 description", "long gauge", g2.getValue().description());

        verify(visitor).gauge(g3.capture(), eq(7f));
        assertEquals("g3 name", "g3", g3.getValue().name());
        assertEquals("g3 description", "float gauge", g3.getValue().description());

        verify(visitor).gauge(g4.capture(), eq(8d));
        assertEquals("g4 name", "g4", g4.getValue().name());
        assertEquals("g4 description", "double gauge", g4.getValue().description());
    }

}

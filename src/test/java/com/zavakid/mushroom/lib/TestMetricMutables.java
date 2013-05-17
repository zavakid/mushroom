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
package com.zavakid.mushroom.lib;

import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zavakid.mushroom.MetricsRecordBuilder;

/**
 * Test metrics record builder interface and mutable metrics
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public class TestMetricMutables {

    private final double EPSILON = 1e-42;

    /**
     * Test the snapshot method
     */
    @Test
    public void testSnapshot() {
        MetricsRecordBuilder mb = mock(MetricsRecordBuilder.class);
        MetricMutableStat stat = new MetricMutableStat("s1", "stat", "ops", "time", true);
        stat.add(0);
        MetricMutableStat stat2 = new MetricMutableStat("s2", "stat", "ops", "time");
        stat2.add(0);
        List<MetricMutable> metrics = Arrays.asList(new MetricMutableCounterInt("c1", "int counter", 1),
                                                    new MetricMutableCounterLong("c2", "long counter", 2L),
                                                    new MetricMutableGaugeInt("g1", "int gauge", 3),
                                                    new MetricMutableGaugeLong("g2", "long gauge", 4L),
                                                    new MetricMutableDeltaInt("d1", "int delta", 5),
                                                    new MetricMutableDeltaLong("d2", "int delta", 6L), stat, stat2);

        for (MetricMutable metric : metrics) {
            metric.snapshot(mb, true);
        }
        stat2.snapshot(mb, true); // should get the same back.
        stat2.add(1);
        stat2.snapshot(mb, true); // should get new interval values back

        verify(mb).addCounter("c1", "int counter", 1);
        verify(mb).addCounter("c2", "long counter", 2L);
        verify(mb).addGauge("g1", "int gauge", 3);
        verify(mb).addGauge("g2", "long gauge", 4L);
        verify(mb).addDelta("d1", "int delta", 5);
        verify(mb).addDelta("d2", "int delta", 6L);
        verify(mb).addCounter("s1_num_ops", "Number of ops for stat", 1L);
        verify(mb).addGauge(eq("s1_avg_time"), eq("Average time for stat"), eq(0.0, EPSILON));
        verify(mb).addGauge(eq("s1_stdev_time"), eq("Standard deviation of time for stat"), eq(0.0, EPSILON));
        verify(mb).addGauge(eq("s1_imin_time"), eq("Interval min time for stat"), eq(0.0, EPSILON));
        verify(mb).addGauge(eq("s1_imax_time"), eq("Interval max time for stat"), eq(0.0, EPSILON));
        verify(mb).addGauge(eq("s1_min_time"), eq("Min time for stat"), eq(0.0, EPSILON));
        verify(mb).addGauge(eq("s1_max_time"), eq("Max time for stat"), eq(0.0, EPSILON));
        verify(mb, times(2)).addCounter("s2_num_ops", "Number of ops for stat", 1L);
        verify(mb, times(2)).addGauge(eq("s2_avg_time"), eq("Average time for stat"), eq(0.0, EPSILON));
        verify(mb).addCounter("s2_num_ops", "Number of ops for stat", 2L);
        verify(mb).addGauge(eq("s2_avg_time"), eq("Average time for stat"), eq(1.0, EPSILON));
    }

}

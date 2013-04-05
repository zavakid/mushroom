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
package com.zavakid.mushroom;

/**
 * A visitor interface for metrics
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public interface MetricsVisitor {

    /**
     * Callback for int value gauges
     * 
     * @param metric the metric object
     * @param value of the metric
     */
    public void gauge(MetricGauge<Integer> metric, int value);

    /**
     * Callback for long value gauges
     * 
     * @param metric the metric object
     * @param value of the metric
     */
    public void gauge(MetricGauge<Long> metric, long value);

    /**
     * Callback for float value gauges
     * 
     * @param metric the metric object
     * @param value of the metric
     */
    public void gauge(MetricGauge<Float> metric, float value);

    /**
     * Callback for double value gauges
     * 
     * @param metric the metric object
     * @param value of the metric
     */
    public void gauge(MetricGauge<Double> metric, double value);

    /**
     * Callback for integer value counters
     * 
     * @param metric the metric object
     * @param value of the metric
     */
    public void counter(MetricCounter<Integer> metric, int value);

    /**
     * Callback for long value counters
     * 
     * @param metric the metric object
     * @param value of the metric
     */
    public void counter(MetricCounter<Long> metric, long value);
}

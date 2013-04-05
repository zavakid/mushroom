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
 * The metrics sink interface
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public interface MetricsSink extends MetricsPlugin {

    /**
     * Put a metrics record in the sink
     * 
     * @param record the record to put
     */
    void putMetrics(MetricsRecord record);

    /**
     * Flush any buffered metrics
     */
    void flush();

}

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

/**
 * The mutable counter (monotonically increasing) metric interface
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午9:55:27
 * @since 0.1
 */
public abstract class MetricMutableCounter<T extends Number> extends MetricMutable {

    /**
     * Construct the metric with name and description
     * 
     * @param name
     * @param description
     */
    public MetricMutableCounter(String name, String description){
        super(name, description);
    }

    /**
     * Increment the metric value by 1.
     */
    public abstract void incr();

}

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
package com.zavakid.mushroom.lib;

/**
 * @author zavakid 2013 2013-5-17 下午6:48:35
 * @since 0.4
 */
public abstract class MetricMutableDelta<T extends Number> extends MetricMutable {

    public MetricMutableDelta(String name, String description){
        super(name, description);
    }

    /**
     * Increment the value of the metric by 1
     */
    public abstract void incr();

    /**
     * Decrement the value of the metric by 1
     */
    public abstract void decr();

}

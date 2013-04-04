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

import com.zavakid.mushroom.MetricsRecordBuilder;

/**
 * A mutable int counter for implementing metrics sources
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午9:56:34
 * @since 0.1
 */
public class MetricMutableCounterInt extends MetricMutableCounter<Integer> {

    private volatile int value;

    /**
     * Construct a mutable int counter
     * 
     * @param name of the counter
     * @param description of the counter
     * @param initValue the initial value of the counter
     */
    public MetricMutableCounterInt(String name, String description, int initValue){
        super(name, description);
        this.value = initValue;
    }

    public synchronized void incr() {
        ++value;
        setChanged();
    }

    /**
     * Increment the value by a delta
     * 
     * @param delta of the increment
     */
    public synchronized void incr(int delta) {
        value += delta;
        setChanged();
    }

    public void snapshot(MetricsRecordBuilder builder, boolean all) {
        if (all || changed()) {
            builder.addCounter(name, description, value);
            clearChanged();
        }
    }

}

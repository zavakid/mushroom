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

import com.zavakid.mushroom.MetricsRecordBuilder;

/**
 * @author zavakid 2013 2013-5-17 下午6:50:51
 * @since 0.4
 */
public class MetricMutableDeltaLong extends MetricMutableDelta<Long> {

    private final long    initValue;
    private volatile long value;

    public MetricMutableDeltaLong(String name, String description, long initValue){
        super(name, description);
        this.initValue = initValue;
        this.value = initValue;
    }

    @Override
    public synchronized void incr() {
        ++value;
        setChanged();

    }

    @Override
    public synchronized void decr() {
        --value;
        setChanged();
    }

    public synchronized void incr(long delta) {
        value += delta;
        setChanged();
    }

    public synchronized void decr(long delta) {
        value -= delta;
        setChanged();
    }

    public synchronized void set(long value) {
        this.value = value;
        setChanged();
    }

    @Override
    public void snapshot(MetricsRecordBuilder builder, boolean all) {
        if (all || changed()) {
            builder.addDelta(name, description, value);
            set(initValue);
            clearChanged();
        }
    }

}

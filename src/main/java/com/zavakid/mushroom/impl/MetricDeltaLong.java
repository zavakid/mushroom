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
package com.zavakid.mushroom.impl;

import com.zavakid.mushroom.MetricDelta;
import com.zavakid.mushroom.MetricsVisitor;

/**
 * @author zavakid 2013 2013-5-17 下午6:43:43
 * @since 0.4
 */
public class MetricDeltaLong extends MetricDelta<Long> {

    private final long value;

    public MetricDeltaLong(String name, String description, long value){
        super(name, description);
        this.value = value;
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public void visit(MetricsVisitor visitor) {
        visitor.delta(this, value);
    }

}

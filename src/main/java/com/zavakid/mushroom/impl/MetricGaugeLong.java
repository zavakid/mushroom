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

import com.zavakid.mushroom.MetricGauge;
import com.zavakid.mushroom.MetricsVisitor;

/**
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午8:10:58
 * @since 0.1
 */
public class MetricGaugeLong extends MetricGauge<Long> {

    private final long value;

    MetricGaugeLong(String name, String description, long value){
        super(name, description);
        this.value = value;
    }

    public Long value() {
        return value;
    }

    public void visit(MetricsVisitor visitor) {
        visitor.gauge(this, value);
    }

}

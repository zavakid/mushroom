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

import com.zavakid.mushroom.Metric;
import com.zavakid.mushroom.MetricsRecord;
import com.zavakid.mushroom.MetricsTag;
import com.zavakid.mushroom.util.Contracts;

/**
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public class MetricsRecordImpl implements MetricsRecord {

    protected static final String      CONTEXT_KEY     = "context";
    protected static final String      DEFAULT_CONTEXT = "default";

    private final long                 timestamp;
    private final String               name;
    private final Iterable<MetricsTag> tags;
    private final Iterable<Metric>     metrics;

    /**
     * Construct a metrics record
     * 
     * @param name of the record
     * @param timestamp of the record
     * @param tags of the record
     * @param metrics of the record
     */
    public MetricsRecordImpl(String name, long timestamp, Iterable<MetricsTag> tags, Iterable<Metric> metrics){
        this.timestamp = Contracts.checkArg(timestamp, timestamp > 0, "timestamp");
        this.name = Contracts.checkNotNull(name, "name");
        this.tags = Contracts.checkNotNull(tags, "tags");
        this.metrics = Contracts.checkNotNull(metrics, "metrics");
    }

    public long timestamp() {
        return timestamp;
    }

    public String name() {
        return name;
    }

    public String context() {
        // usually the first tag
        for (MetricsTag t : tags) {
            if (t.name().equals(CONTEXT_KEY)) {
                return String.valueOf(t.value());
            }
        }
        return DEFAULT_CONTEXT;
    }

    public Iterable<MetricsTag> tags() {
        return tags;
    }

    public Iterable<Metric> metrics() {
        return metrics;
    }

    // Mostly for testing
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetricsRecordImpl other = (MetricsRecordImpl) obj;
        if (this.timestamp != other.timestamp()) {
            return false;
        }
        if (!this.name.equals(other.name())) {
            return false;
        }
        if (!this.tags.equals(other.tags())) {
            return false;
        }
        if (!this.metrics.equals(other.metrics())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "MetricsRecordImpl{" + "timestamp=" + timestamp + " name='" + name + "' tags=" + tags + " metrics="
               + metrics + "}\n";
    }

}

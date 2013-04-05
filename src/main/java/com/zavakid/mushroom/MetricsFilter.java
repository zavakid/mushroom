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

import org.apache.commons.configuration.SubsetConfiguration;

/**
 * The metrics filter
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public abstract class MetricsFilter implements MetricsPlugin {

    public abstract void init(SubsetConfiguration conf);

    /**
     * Whether to accept the name
     * 
     * @param name to filter on
     * @return true to accept; false otherwise.
     */
    public abstract boolean accepts(String name);

    /**
     * Whether to accept the tag
     * 
     * @param tag to filter on
     * @return true to accept; false otherwise
     */
    public abstract boolean accepts(MetricsTag tag);

    /**
     * Whether to accept the tags
     * 
     * @param tags to filter on
     * @return true to accept; false otherwise
     */
    public abstract boolean accepts(Iterable<MetricsTag> tags);

    /**
     * Whether to accept the record
     * 
     * @param record to filter on
     * @return true to accept; false otherwise.
     */
    public boolean accepts(MetricsRecord record) {
        return accepts(record.tags());
    }

}

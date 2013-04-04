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

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zavakid.mushroom.MetricsSystem;
import com.zavakid.mushroom.MetricsTag;

/**
 * A base class for metrics system singletons
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午5:28:45
 * @since 0.1
 */
public class MetricsSystemImpl implements MetricsSystem {

    private static final Log                        LOG              = LogFactory.getLog(MetricsSystemImpl.class);

    static final String                             MS_CONTEXT       = "metricssystem";
    static final String                             NUM_SOURCES_KEY  = "num_sources";
    static final String                             NUM_SOURCES_DESC = "Number of metrics sources";
    static final String                             NUM_SINKS_KEY    = "num_sinks";
    static final String                             NUM_SINKS_DESC   = "Number of metrics sinks";
    static final String                             MS_NAME          = "MetricsSystem";
    static final String                             MS_STATS_NAME    = MS_NAME + ",sub=Stats";
    static final String                             MS_STATS_DESC    = "Metrics system metrics";
    static final String                             MS_CONTROL_NAME  = MS_NAME + ",sub=Control";

    private final Map<String, MetricsSourceAdapter> sources;
    private final Map<String, MetricsSinkAdapter>   sinks;
    private final List<Callback>                    callbacks;
    private final MetricsBuilderImpl                metricsBuilder;
    private final MetricMutableStat                 snapshotStat     = new MetricMutableStat("snapshot",
                                                                                             "snapshot stats", "ops",
                                                                                             "time", true);
    private final MetricMutableStat                 publishStat      = new MetricMutableStat("publish",
                                                                                             "publishing stats", "ops",
                                                                                             "time", true);
    private final MetricMutableCounterLong          dropStat         = new MetricMutableCounterLong(
                                                                                                    "dropped_pub_all",
                                                                                                    "number of dropped updates by all sinks",
                                                                                                    0L);
    private final List<MetricsTag>                  injectedTags;
}

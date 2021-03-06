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

import com.zavakid.mushroom.impl.MetricsSystemImpl;

/**
 * The default metrics system singleton <br/>
 * 使用 enum 实现单例
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public enum DefaultMetricsSystem implements MetricsSystem {

    /** The singleton instance */
    INSTANCE;

    //private static final int        VERSION  = 2;
    private final MetricsSystemImpl impl = new MetricsSystemImpl();

    private MetricsSystem init(String prefix) {
        impl.init(prefix);
        return impl;
    }

    /**
     * Common static convenience method to initialize the metrics system
     * 
     * @param prefix for configuration
     * @return the metrics system instance
     */
    public static MetricsSystem initialize(String prefix) {
        return INSTANCE.init(prefix);
    }

    public <T extends MetricsSource> T register(String name, String desc, T source) {
        return impl.register(name, desc, source);
    }

    @Override
    public <T extends MetricsSource> T registerIfAbsent(String name, String desc, T source) {
        return impl.registerIfAbsent(name, desc, source);
    }

    /**
     * Common static method to register a source
     * 
     * @param <T> type of the source
     * @param name of the source
     * @param desc description
     * @param source the source object to register
     * @return the source object
     */
    public static <T extends MetricsSource> T registerSource(String name, String desc, T source) {
        return INSTANCE.register(name, desc, source);
    }

    /**
     * common static method to register a source if absent, see
     * {@link MetricsSystem#registerIfAbsent(String, String, MetricsSource)}
     * 
     * @param name
     * @param desc
     * @param source
     * @return
     */
    public static <T extends MetricsSource> T registerSourceIfAbsent(String name, String desc, T source) {
        return INSTANCE.registerIfAbsent(name, desc, source);
    }

    public <T extends MetricsSink> T register(String name, String desc, T sink) {
        return impl.register(name, desc, sink);
    }

    @Override
    public <T extends MetricsSink> T registerIfAbsent(String name, String desc, T sink) {
        return impl.registerIfAbsent(name, desc, sink);
    }

    public void register(Callback callback) {
        impl.register(callback);
    }

    public void start() {
        impl.start();
    }

    public void stop() {
        impl.stop();
    }

    public void refreshMBeans() {
        impl.refreshMBeans();
    }

    public String currentConfig() {
        return impl.currentConfig();
    }

    public void shutdown() {
        impl.shutdown();
    }

}

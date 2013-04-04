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

/**
 * The metrics system interface
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午4:36:20
 * @since 0.1
 */
public interface MetricsSystem extends MetricsSystemMXBean {

    /**
     * Register a metrics source
     * 
     * @param <T> the type of the source
     * @param source to register
     * @param name of the source. Must be unique.
     * @param desc the description of the source.
     * @return the source
     * @exception MetricsException
     */
    <T extends MetricsSource> T register(String name, String desc, T source);

    /**
     * Register a metrics sink
     * 
     * @param <T> the type of the sink
     * @param sink to register
     * @param name of the sink. Must be unique.
     * @param desc the description of the sink
     * @return the sink
     * @exception MetricsException
     */
    <T extends MetricsSink> T register(String name, String desc, T sink);

    /**
     * Register a callback interface for JMX events
     * 
     * @param callback the callback object implementing the MBean interface.
     */
    void register(Callback callback);

    /**
     * Shutdown the metrics system completely (usually during server shutdown.) The MetricsSystemMXBean will be
     * unregistered.
     */
    void shutdown();

    static interface Callback {

        /**
         * Called before start()
         */
        void preStart();

        /**
         * Called after start()
         */
        void postStart();

        /**
         * Called before stop()
         */
        void preStop();

        /**
         * Called after stop()
         */
        void postStop();

    }

    public static abstract class AbstractCallback implements Callback {

        public void preStart() {
        }

        public void postStart() {
        }

        public void preStop() {
        }

        public void postStop() {
        }

    }
}

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
 and some sourc ecode may changed by zavakid
 */
package com.zavakid.mushroom;

/**
 * The JMX interface to the metrics system
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午4:24:53
 * @since 0.1
 */
public interface MetricsSystemMXBean {
    
    /**
     * Start the metrics system
     * @exception MetricsException
     */
    public void start();

    /**
     * Stop the metrics system
     * @exception MetricsException
     */
    public void stop();

    /**
     * Force a refresh of MBeans
     * @exception MetricsException
     */
    public void refreshMBeans();

    /**
     * @return the current config
     * Note, avoid getConfig, as it'll turn it into an attribute,
     * which doesn't support multiple lines in the values.
     * @exception MetricsException
     */
    public String currentConfig();

}

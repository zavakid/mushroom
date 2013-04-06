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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test metrics configuration
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-6 上午11:08:58
 * @since 0.1
 */
public class TestMetricsConfig {

    static final Log LOG = LogFactory.getLog(TestMetricsConfig.class);

    /**
     * Common use cases
     * 
     * @throws Exception
     */
    @Test
    public void testCommon() throws Exception {
        String filename = getTestFilename("test-metrics2");
        new ConfigBuilder().add("*.foo", "default foo").add("p1.*.bar", "p1 default bar").add("p1.t1.*.bar",
                                                                                              "p1.t1 default bar").add("p1.t1.i1.name",
                                                                                                                       "p1.t1.i1.name").add("p1.t1.42.bar",
                                                                                                                                            "p1.t1.42.bar").add("p1.t2.i1.foo",
                                                                                                                                                                "p1.t2.i1.foo").add("p2.*.foo",
                                                                                                                                                                                    "p2 default foo").save(filename);

        MetricsConfig mc = MetricsConfig.create("p1", filename);
        ConfigUtil.dump("mc:", mc);

        Configuration expected = new ConfigBuilder().add("*.bar", "p1 default bar").add("t1.*.bar", "p1.t1 default bar").add("t1.i1.name",
                                                                                                                             "p1.t1.i1.name").add("t1.42.bar",
                                                                                                                                                  "p1.t1.42.bar").add("t2.i1.foo",
                                                                                                                                                                      "p1.t2.i1.foo").config;

        ConfigUtil.assertEq(expected, mc);

        testInstances(mc);
    }

    private void testInstances(MetricsConfig c) throws Exception {
        Map<String, MetricsConfig> map = c.getInstanceConfigs("t1");
        Map<String, MetricsConfig> map2 = c.getInstanceConfigs("t2");

        assertEquals("number of t1 instances", 2, map.size());
        assertEquals("number of t2 instances", 1, map2.size());
        assertTrue("contains t1 instance i1", map.containsKey("i1"));
        assertTrue("contains t1 instance 42", map.containsKey("42"));
        assertTrue("contains t2 instance i1", map2.containsKey("i1"));

        MetricsConfig t1i1 = map.get("i1");
        MetricsConfig t1i42 = map.get("42");
        MetricsConfig t2i1 = map2.get("i1");
        ConfigUtil.dump("--- t1 instance i1:", t1i1);
        ConfigUtil.dump("--- t1 instance 42:", t1i42);
        ConfigUtil.dump("--- t2 instance i1:", t2i1);

        Configuration t1expected1 = new ConfigBuilder().add("name", "p1.t1.i1.name").config;
        Configuration t1expected42 = new ConfigBuilder().add("bar", "p1.t1.42.bar").config;
        Configuration t2expected1 = new ConfigBuilder().add("foo", "p1.t2.i1.foo").config;

        ConfigUtil.assertEq(t1expected1, t1i1);
        ConfigUtil.assertEq(t1expected42, t1i42);
        ConfigUtil.assertEq(t2expected1, t2i1);

        LOG.debug("asserting foo == default foo");
        // Check default lookups
        assertEquals("value of foo in t1 instance i1", "default foo", t1i1.getString("foo"));
        assertEquals("value of bar in t1 instance i1", "p1.t1 default bar", t1i1.getString("bar"));
        assertEquals("value of foo in t1 instance 42", "default foo", t1i42.getString("foo"));
        assertEquals("value of foo in t2 instance i1", "p1.t2.i1.foo", t2i1.getString("foo"));
        assertEquals("value of bar in t2 instance i1", "p1 default bar", t2i1.getString("bar"));
    }

    /**
     * Test the config file load order
     * 
     * @throws Exception
     */
    @Test
    public void testLoadFirst() throws Exception {
        String filename = getTestFilename("hadoop-metrics2-p1");
        new ConfigBuilder().add("p1.foo", "p1foo").save(filename);

        MetricsConfig mc = MetricsConfig.create("p1", filename);
        MetricsConfig mc2 = MetricsConfig.create("p1", "na1", "na2", filename);
        Configuration expected = new ConfigBuilder().add("foo", "p1foo").config;

        ConfigUtil.assertEq(expected, mc);
        ConfigUtil.assertEq(expected, mc2);
    }

    /**
     * Return a test filename in the class path
     * 
     * @param basename
     * @return the filename
     */
    public static String getTestFilename(String basename) {
        return "target/classes/" + basename + ".properties";
    }

}

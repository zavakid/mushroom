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

import com.zavakid.mushroom.MetricsException;

/**
 * The metrics configuration runtime exception
 * 
 * @author Hadoop metric2 package's authors
 * @since 0.1
 */
public class MetricsConfigException extends MetricsException {

    private static final long serialVersionUID = 1L;

    MetricsConfigException(String message){
        super(message);
    }

    MetricsConfigException(String message, Throwable cause){
        super(message, cause);
    }

    MetricsConfigException(Throwable cause){
        super(cause);
    }

}

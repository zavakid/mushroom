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
 * A general metrics exception wrapper
 * 
 * @author Hadoop metric2 package's authors
 * @author zavakid 2013 2013-4-4 下午4:33:05
 * @since 0.1
 */
public class MetricsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construct the exception with a message
     * 
     * @param message for the exception
     */
    public MetricsException(String message){
        super(message);
    }

    /**
     * Construct the exception with a message and a cause
     * 
     * @param message for the exception
     * @param cause of the exception
     */
    public MetricsException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * Construct the exception with a cause
     * 
     * @param cause of the exception
     */
    public MetricsException(Throwable cause){
        super(cause);
    }

}

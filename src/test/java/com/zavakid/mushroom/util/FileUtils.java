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
package com.zavakid.mushroom.util;

import java.io.File;
import java.util.Collection;

/**
 * @author zavakid 2013 2013-4-11 下午9:00:48
 * @since 1.0
 */
public final class FileUtils {

    private FileUtils(){
    }

    public static void deleteFiles(Collection<File> files) {
        for (File file : files) {
            deleteQuietly(file);
        }

    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            return file.delete();
        } catch (Exception ignored) {
            return false;
        }
    }

}

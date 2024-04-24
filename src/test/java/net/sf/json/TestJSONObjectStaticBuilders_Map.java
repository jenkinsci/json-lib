/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public class TestJSONObjectStaticBuilders_Map extends AbstractJSONObjectStaticBuildersTestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestJSONObjectStaticBuilders_Map.class);
    }

    public TestJSONObjectStaticBuilders_Map(String name) {
        super(name);
    }

    @Override
    protected Object getSource() {
        Map map = new HashMap();
        String[] props = getProperties();
        for (int i = 0; i < props.length; i++) {
            map.put(props[i], PropertyConstants.getPropertyValue(props[i]));
        }
        map.put("pexcluded", "");
        return map;
    }
}

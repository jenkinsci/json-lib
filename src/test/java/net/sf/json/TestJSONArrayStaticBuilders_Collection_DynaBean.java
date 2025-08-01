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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import org.apache.commons.beanutils.DynaBean;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArrayStaticBuilders_Collection_DynaBean extends AbstractJSONArrayStaticBuildersTestCase {

    @Override
    protected Object getSource() {
        Map<String, Class<?>> map = new HashMap<>();
        String[] props = getProperties();
        for (String prop : props) {
            map.put(prop, PropertyConstants.getPropertyClass(prop));
        }
        map.put("class", Class.class);
        map.put("pexcluded", String.class);
        MorphDynaClass dynaClass = new MorphDynaClass(map);
        MorphDynaBean dynaBean = null;
        try {
            dynaBean = (MorphDynaBean) dynaClass.newInstance();
            for (String prop : props) {
                dynaBean.set(prop, PropertyConstants.getPropertyValue(prop));
            }
            dynaBean.set("class", Object.class);
            dynaBean.set("pexcluded", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<DynaBean> list = new ArrayList<>();
        list.add(dynaBean);
        return list;
    }
}

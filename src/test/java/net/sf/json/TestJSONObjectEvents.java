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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import net.sf.json.sample.BeanA;
import net.sf.json.sample.JsonEventAdpater;
import net.sf.json.sample.PropertyBean;
import org.apache.commons.beanutils.DynaBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONObjectEvents {
    private JsonConfig jsonConfig;
    private JsonEventAdpater jsonEventAdpater;

    @Test
    void testFromObject_bean() {
        JSONObject.fromObject(new BeanA(), jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_bean2() {
        JSONObject.fromObject(new PropertyBean(), jsonConfig);
        assertEquals(0, jsonEventAdpater.getError());
        assertEquals(1, jsonEventAdpater.getWarning());
        assertEquals(0, jsonEventAdpater.getArrayStart());
        assertEquals(0, jsonEventAdpater.getArrayEnd());
        assertEquals(1, jsonEventAdpater.getObjectStart());
        assertEquals(1, jsonEventAdpater.getObjectEnd());
        assertEquals(0, jsonEventAdpater.getElementAdded());
        assertEquals(1, jsonEventAdpater.getPropertySet());
    }

    @Test
    void testFromObject_dynaBean() throws Exception {
        JSONObject.fromObject(createDynaBean(), jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_error() {
        assertThrows(JSONException.class, () -> JSONObject.fromObject("[]", jsonConfig));
        assertEquals(1, jsonEventAdpater.getError());
        assertEquals(0, jsonEventAdpater.getWarning());
        assertEquals(0, jsonEventAdpater.getArrayStart());
        assertEquals(0, jsonEventAdpater.getArrayEnd());
        assertEquals(0, jsonEventAdpater.getObjectStart());
        assertEquals(0, jsonEventAdpater.getObjectEnd());
        assertEquals(0, jsonEventAdpater.getElementAdded());
        assertEquals(0, jsonEventAdpater.getPropertySet());
    }

    @Test
    void testFromObject_JSONObject() {
        JSONObject jsonObject =
                new JSONObject().element("name", "json").element("bool", true).element("int", 1);
        JSONObject.fromObject(jsonObject, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_map() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        map.put("bool", true);
        map.put("int", 1);
        JSONObject.fromObject(map, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_string() {
        JSONObject.fromObject("{name:'json',int:1,bool:true}", jsonConfig);
        assertEvents();
    }

    @BeforeEach
    void setUp() {
        jsonEventAdpater = new JsonEventAdpater();
        jsonConfig = new JsonConfig();
        jsonConfig.addJsonEventListener(jsonEventAdpater);
        jsonConfig.enableEventTriggering();
    }

    @AfterEach
    void tearDown() {
        jsonEventAdpater.reset();
    }

    private void assertEvents() {
        assertEquals(0, jsonEventAdpater.getError());
        assertEquals(0, jsonEventAdpater.getWarning());
        assertEquals(0, jsonEventAdpater.getArrayStart());
        assertEquals(0, jsonEventAdpater.getArrayEnd());
        assertEquals(1, jsonEventAdpater.getObjectStart());
        assertEquals(1, jsonEventAdpater.getObjectEnd());
        assertEquals(0, jsonEventAdpater.getElementAdded());
        assertEquals(3, jsonEventAdpater.getPropertySet());
    }

    private DynaBean createDynaBean() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("name", String.class);
        properties.put("bool", Boolean.class);
        properties.put("int", Integer.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("name", "json");
        dynaBean.set("bool", true);
        dynaBean.set("int", 1);
        return dynaBean;
    }
}

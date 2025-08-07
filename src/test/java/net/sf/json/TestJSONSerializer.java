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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ezmorph.beanutils.DynaBean;
import net.sf.ezmorph.beanutils.PropertyUtils;
import net.sf.json.sample.ArrayJSONStringBean;
import net.sf.json.sample.BeanA;
import net.sf.json.sample.MappingBean;
import net.sf.json.sample.ObjectJSONStringBean;
import net.sf.json.sample.ValueBean;
import net.sf.json.util.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONSerializer {
    private JsonConfig jsonConfig;

    @Test
    @DisplayName("JSONArray('[]') -&gt; ToJava[default]")
    void testToJava_JSONArray_1() {
        JSONArray jsonArray = JSONArray.fromObject("[]");
        Object java = JSONSerializer.toJava(jsonArray);
        assertNotNull(java);
        assertInstanceOf(List.class, java);
        List<?> list = (List<?>) java;
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("JSONArray('[]') -&gt; ToJava[arrayMode:OBJECT_ARRAY]")
    void testToJava_JSONArray_2() {
        JSONArray jsonArray = JSONArray.fromObject("[]");
        jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
        Object java = JSONSerializer.toJava(jsonArray, jsonConfig);
        assertNotNull(java);
        assertTrue(Object[].class.isAssignableFrom(java.getClass()));
        Object[] array = (Object[]) java;
        assertEquals(0, array.length);
    }

    @Test
    @DisplayName("JSONNull -&gt; ToJava[default]")
    void testToJava_JSONNull_1() {
        Object java = JSONSerializer.toJava(JSONNull.getInstance());
        assertNull(java);
    }

    @Test
    @DisplayName("JSONObject(null:true) -&gt; ToJava[default]")
    void testToJava_JSONObject_1() {
        Object java = JSONSerializer.toJava(new JSONObject(true));
        assertNull(java);
    }

    @Test
    @DisplayName("JSONObject -&gt; ToJava[default]")
    void testToJava_JSONObject_2() throws Exception {
        String json = "{name=\"json\",bool:true,int:1,double:2.2,array:[1,2]}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONSerializer.toJava(jsonObject);
        assertNotNull(bean);
        assertInstanceOf(DynaBean.class, bean);
        assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
        List<?> expected = (List<?>) JSONArray.toCollection(jsonObject.getJSONArray("array"));
        Assertions.assertEquals(expected, (List<?>) PropertyUtils.getProperty(bean, "array"));
    }

    @Test
    @DisplayName("JSONObject -&gt; ToJava[rootClass:BeanA]")
    void testToJava_JSONObject_3() {
        String json = "{bool:true,integer:1,string:\"json\"}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        jsonConfig.setRootClass(BeanA.class);
        Object java = JSONSerializer.toJava(jsonObject, jsonConfig);
        assertNotNull(java);
        assertInstanceOf(BeanA.class, java);
        BeanA bean = (BeanA) java;
        assertEquals(jsonObject.get("bool"), bean.isBool());
        assertEquals(jsonObject.get("integer"), bean.getInteger());
        assertEquals(jsonObject.get("string"), bean.getString());
    }

    @Test
    @DisplayName("JSONObject -&gt; ToJava[rootClass:BeanA,classMap]")
    void testToJava_JSONObject_4() {
        MappingBean mappingBean = new MappingBean();
        ValueBean beanA = new ValueBean();
        beanA.setValue(90000);
        ValueBean beanB = new ValueBean();
        beanB.setValue(91000);
        mappingBean.addAttribute("beanA", beanA);
        mappingBean.addAttribute("beanB", beanB);
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("bean.*", ValueBean.class);

        JSONObject jsonObject = JSONObject.fromObject(mappingBean);
        jsonConfig.setRootClass(MappingBean.class);
        jsonConfig.setClassMap(classMap);
        Object java = JSONSerializer.toJava(jsonObject, jsonConfig);
        assertNotNull(java);
        assertInstanceOf(MappingBean.class, java);
        MappingBean mappingBean2 = (MappingBean) java;

        Object ba = mappingBean2.getAttributes().get("beanA");
        Object bb = mappingBean2.getAttributes().get("beanB");
        assertInstanceOf(ValueBean.class, ba);
        assertInstanceOf(ValueBean.class, bb);
        assertEquals(beanA.getValue(), ((ValueBean) ba).getValue());
        assertEquals(beanB.getValue(), ((ValueBean) bb).getValue());
    }

    @Test
    void testToJava_JSONObject_5() {
        assertThrows(JSONException.class, () -> JSONObject.fromObject("/**"));
    }

    @Test
    void testToJava_JSONObject_and_reset() throws Exception {
        String json = "{bool:true,integer:1,string:\"json\"}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        jsonConfig.setRootClass(BeanA.class);
        Object java = JSONSerializer.toJava(jsonObject, jsonConfig);
        assertNotNull(java);
        assertInstanceOf(BeanA.class, java);
        BeanA bean = (BeanA) java;
        assertEquals(jsonObject.get("bool"), bean.isBool());
        assertEquals(jsonObject.get("integer"), bean.getInteger());
        assertEquals(jsonObject.get("string"), bean.getString());
        jsonConfig.reset();
        java = JSONSerializer.toJava(jsonObject, jsonConfig);
        assertInstanceOf(DynaBean.class, java);
        assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(java, "bool"));
        assertEquals(jsonObject.get("integer"), PropertyUtils.getProperty(java, "integer"));
        assertEquals(jsonObject.get("string"), PropertyUtils.getProperty(java, "string"));
    }

    @Test
    void testToJSON_JSONString_array() {
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        JSON json = JSONSerializer.toJSON(bean);
        assertNotNull(json);
        assertInstanceOf(JSONArray.class, json);
        Assertions.assertEquals(JSONArray.fromObject("['json','json']"), (JSONArray) json);
    }

    @Test
    void testToJSON_JSONString_null() {
        JSON json = JSONSerializer.toJSON(null);
        assertNotNull(json);
        assertEquals(JSONNull.getInstance(), json);
    }

    @Test
    void testToJSON_JSONString_object() {
        ObjectJSONStringBean bean = new ObjectJSONStringBean();
        bean.setName("json");
        JSON json = JSONSerializer.toJSON(bean);
        assertNotNull(json);
        assertInstanceOf(JSONObject.class, json);
        Assertions.assertEquals(JSONObject.fromObject("{\"name\":\"json\"}"), (JSONObject) json);
    }

    @Test
    void testToJSON_Object_array() {
        JSON json = JSONSerializer.toJSON(new int[] {1, 2});
        assertNotNull(json);
        assertInstanceOf(JSONArray.class, json);
        Assertions.assertEquals(JSONArray.fromObject("[1,2]"), (JSONArray) json);
    }

    @Test
    void testToJSON_Object_JSONTokener_array() {
        JSON json = JSONSerializer.toJSON(new JSONTokener("[1,2]"));
        assertNotNull(json);
        assertInstanceOf(JSONArray.class, json);
        Assertions.assertEquals(JSONArray.fromObject("[1,2]"), (JSONArray) json);
    }

    @Test
    void testToJSON_Object_null() {
        JSON json = JSONSerializer.toJSON(null);
        assertNotNull(json);
        assertEquals(JSONNull.getInstance(), json);
    }

    @Test
    void testToJSON_Object_object() {
        JSON json = JSONSerializer.toJSON(new BeanA());
        assertNotNull(json);
        assertInstanceOf(JSONObject.class, json);
        Assertions.assertEquals(JSONObject.fromObject(new BeanA()), (JSONObject) json);
    }

    @Test
    void testToJSON_String_array() {
        JSON json = JSONSerializer.toJSON("['json','json']");
        assertNotNull(json);
        assertInstanceOf(JSONArray.class, json);
        Assertions.assertEquals(JSONArray.fromObject("['json','json']"), (JSONArray) json);
    }

    @Test
    void testToJSON_String_invalid() {
        assertThrows(JSONException.class, () -> JSONSerializer.toJSON("garbage"));
    }

    @Test
    void testToJSON_String_null() {
        JSON json = JSONSerializer.toJSON(null);
        assertNotNull(json);
        assertEquals(JSONNull.getInstance(), json);
    }

    @Test
    void testToJSON_String_null_literal() {
        JSON json = JSONSerializer.toJSON("null");
        assertNotNull(json);
        assertEquals(JSONNull.getInstance(), json);
    }

    @Test
    void testToJSON_String_object() {
        JSON json = JSONSerializer.toJSON("{'name':'json'}");
        assertNotNull(json);
        assertInstanceOf(JSONObject.class, json);
        Assertions.assertEquals(JSONObject.fromObject("{\"name\":\"json\"}"), (JSONObject) json);
    }

    @BeforeEach
    void setUp() {
        jsonConfig = new JsonConfig();
    }
}

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import net.sf.ezmorph.test.ArrayAssertions;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.DefaultValueProcessorMatcher;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.sample.BeanA;
import net.sf.json.sample.BeanB;
import net.sf.json.sample.BeanC;
import net.sf.json.sample.BeanFoo;
import net.sf.json.sample.ChildBean;
import net.sf.json.sample.ClassBean;
import net.sf.json.sample.EmptyBean;
import net.sf.json.sample.JavaIdentifierBean;
import net.sf.json.sample.ListingBean;
import net.sf.json.sample.MappingBean;
import net.sf.json.sample.NumberBean;
import net.sf.json.sample.ObjectBean;
import net.sf.json.sample.ObjectJSONStringBean;
import net.sf.json.sample.ParentBean;
import net.sf.json.sample.PrimitiveBean;
import net.sf.json.sample.PropertyBean;
import net.sf.json.sample.SetBean;
import net.sf.json.sample.TransientBean;
import net.sf.json.sample.ValueBean;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONTokener;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.JavaIdentifierTransformer;
import net.sf.json.util.PropertyExclusionClassMatcher;
import net.sf.json.util.PropertyFilter;
import net.sf.json.util.PropertySetStrategy;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONObject {
    private JsonConfig jsonConfig;

    @Test
    void testAccumulate() {
        JSONObject json = new JSONObject();
        json.accumulate("key", "1");
        assertEquals(1, json.getInt("key"));
        json.accumulate("key", "2");
        Assertions.assertEquals(JSONArray.fromObject("['1','2']"), json.getJSONArray("key"));
        json.accumulate("key", "3");
        Assertions.assertEquals(JSONArray.fromObject("['1','2','3']"), json.getJSONArray("key"));
    }

    @Test
    void testAccumulate__nullObject() {
        assertThrows(JSONException.class, () -> new JSONObject(true).accumulate("key", null));
    }

    @Test
    void testConstructor_Object__nullJSONObject() {
        JSONObject jsonObject = JSONObject.fromObject(null);
        assertTrue(jsonObject.isNullObject());
    }

    @Test
    void testConstructor_Object_String_Array__nullObject() {
        jsonConfig.setExcludes(new String[] {"bool", "integer"});
        JSONObject jsonObject = JSONObject.fromObject(null, jsonConfig);
        assertTrue(jsonObject.isNullObject());
    }

    @Test
    void testCycleDetection_beans_noprop() {
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.NOPROP);
        ParentBean parent = new ParentBean();
        parent.setChild(new ChildBean());

        JSONObject actual = JSONObject.fromObject(parent, jsonConfig);
        JSONObject expected =
                new JSONObject().element("value", 0).element("child", new JSONObject().element("value", 0));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCycleDetection_beans_null() {
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        ParentBean parent = new ParentBean();
        parent.setChild(new ChildBean());

        JSONObject actual = JSONObject.fromObject(parent, jsonConfig);
        JSONObject expected = new JSONObject()
                .element("value", 0)
                .element("child", new JSONObject().element("value", 0).element("parent", new JSONObject(true)));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCycleDetection_beans_strict() {
        ParentBean parent = new ParentBean();
        parent.setChild(new ChildBean());
        JSONException expected = assertThrows(JSONException.class, () -> JSONObject.fromObject(parent));
        assertTrue(expected.getMessage().endsWith("There is a cycle in the hierarchy!"));
    }

    @Test
    void testDiscard() {
        JSONObject jsonObject = new JSONObject()
                .element("int", "1")
                .element("long", "1")
                .element("boolean", "true")
                .element("string", "string")
                .element("array", "[1,2,3]");
        assertEquals(5, jsonObject.size());
        jsonObject.discard("int").discard("array");
        assertEquals(3, jsonObject.size());
        assertFalse(jsonObject.has("int"));
    }

    @Test
    void testElement__duplicateProperty() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("duplicated", "json1");
        jsonObject.element("duplicated", "json2");
        Object o = jsonObject.get("duplicated");
        assertFalse(o instanceof JSONArray);
        assertEquals("json2", o);
    }

    @Test
    void testElement__duplicateProperty_2() {
        JSONObject jsonObject = JSONObject.fromObject("{'duplicated':'json1','duplicated':'json2'}");
        Object o = jsonObject.get("duplicated");
        assertInstanceOf(JSONArray.class, o);
        assertEquals(new JSONArray().element("json1").element("json2"), o);
    }

    @Test
    void testElement_Bean() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("bean", new ObjectBean());
        JSONObject actual = jsonObject.getJSONObject("bean");
        assertFalse(actual.has("class"));
    }

    @Test
    void testElement_Bean_exclusions() {
        JSONObject jsonObject = new JSONObject();
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        jsonObject.element("bean", new ObjectBean(), jsonConfig);
        JSONObject actual = jsonObject.getJSONObject("bean");
        assertFalse(actual.has("class"));
        assertFalse(actual.has("pexcluded"));
    }

    @Test
    void testElement_Bean_exclusions_ignoreDefault() {
        JSONObject jsonObject = new JSONObject();
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        jsonConfig.setIgnoreDefaultExcludes(true);
        jsonObject.element("bean", new ObjectBean(), jsonConfig);
        JSONObject actual = jsonObject.getJSONObject("bean");
        assertFalse(actual.has("class"));
        assertFalse(actual.has("pexcluded"));
    }

    @Test
    void testElement_boolean() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("bool", true);
        assertTrue(jsonObject.getBoolean("bool"));
    }

    @Test
    void testElement_Boolean() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("bool", Boolean.TRUE);
        assertTrue(jsonObject.getBoolean("bool"));
    }

    @Test
    void testElement_Class() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("class", Object.class);
        assertEquals("java.lang.Object", jsonObject.get("class"));
    }

    @Test
    void testElement_Collection() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("list", Collections.EMPTY_LIST);
        Assertions.assertEquals(new JSONArray(), jsonObject.getJSONArray("list"));
    }

    @Test
    void testElement_Collection2() {
        List<Object> list = new ArrayList<>();
        list.add(new ObjectBean());
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("list", list);
        JSONObject actual = jsonObject.getJSONArray("list").getJSONObject(0);
        assertFalse(actual.has("class"));
    }

    @Test
    void testElement_Collection2_exclusions() {
        List<Object> list = new ArrayList<>();
        list.add(new ObjectBean());
        JSONObject jsonObject = new JSONObject();
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        jsonObject.element("list", list, jsonConfig);
        JSONObject actual = jsonObject.getJSONArray("list").getJSONObject(0);
        assertFalse(actual.has("class"));
        assertFalse(actual.has("pexcluded"));
    }

    @Test
    void testElement_Collection2_exclusions_ignoreDefault() {
        List<Object> list = new ArrayList<>();
        list.add(new ObjectBean());
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        jsonConfig.setIgnoreDefaultExcludes(true);
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("list", list, jsonConfig);
        JSONObject actual = jsonObject.getJSONArray("list").getJSONObject(0);
        assertFalse(actual.has("class"));
        assertFalse(actual.has("pexcluded"));
    }

    @Test
    void testElement_double() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("double", 1d);
        assertEquals(1d, jsonObject.getDouble("double"), 0d);
    }

    @Test
    void testElement_int() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("int", 1);
        assertEquals(1, jsonObject.getInt("int"));
    }

    @Test
    void testElement_JSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("null", JSONNull.getInstance());
        Assertions.assertEquals(JSONNull.getInstance(), jsonObject.get("null"));
    }

    @Test
    void testElement_JSONString() {
        JSONObject jsonObject = new JSONObject();
        ObjectJSONStringBean bean = new ObjectJSONStringBean();
        bean.setName("json");
        jsonObject.element("bean", bean);
        Assertions.assertEquals(JSONObject.fromObject(bean), jsonObject.getJSONObject("bean"));
    }

    @Test
    void testElement_JSONTokener() {
        JSONObject jsonObject = new JSONObject();
        JSONTokener tok = new JSONTokener("{'name':'json'}");
        jsonObject.element("obj", tok);
        tok.reset();
        Assertions.assertEquals(JSONObject.fromObject(tok), jsonObject.getJSONObject("obj"));
    }

    @Test
    void testJSONTokener_ParseNumber() {
        String jsonStr =
                "{\"int\":42,\"negInt\":-17,\"long\":9223372036854775807,\"negLong\":-9223372036854775808,\"float\":3.1415,\"negFloat\":-0.01,\"exp\":1.23e4,\"negExp\":-1.23e4,\"expNeg\":1.23e-4,\"negExpNeg\":-1.23e-4,\"zero\":0}";
        JSONTokener jsonTokener = new JSONTokener(jsonStr);
        JSONObject json = JSONObject.fromObject(jsonTokener);

        assertInstanceOf(Integer.class, json.get("int"));
        assertInstanceOf(Integer.class, json.get("negInt"));
        assertInstanceOf(Long.class, json.get("long"));
        assertInstanceOf(Long.class, json.get("negLong"));
        assertInstanceOf(Double.class, json.get("float"));
        assertInstanceOf(Double.class, json.get("negFloat"));
        assertInstanceOf(Double.class, json.get("exp"));
        assertInstanceOf(Double.class, json.get("negExp"));
        assertInstanceOf(Double.class, json.get("expNeg"));
        assertInstanceOf(Double.class, json.get("negExpNeg"));
        assertInstanceOf(Integer.class, json.get("zero"));

        assertEquals(42, json.get("int"));
        assertEquals(-17, json.get("negInt"));
        assertEquals(9223372036854775807L, json.get("long"));
        assertEquals(-9223372036854775808L, json.get("negLong"));
        assertEquals(3.1415, (Double) json.get("float"), 1e-10);
        assertEquals(-0.01, (Double) json.get("negFloat"), 1e-10);
        assertEquals(1.23e4, (Double) json.get("exp"), 1e-10);
        assertEquals(-1.23e4, (Double) json.get("negExp"), 1e-10);
        assertEquals(1.23e-4, (Double) json.get("expNeg"), 1e-10);
        assertEquals(-1.23e-4, (Double) json.get("negExpNeg"), 1e-10);
        assertEquals(0, json.get("zero"));
    }

    @Test
    void testElement_long() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("long", 1L);
        assertEquals(1L, jsonObject.getLong("long"));
    }

    @Test
    void testElement_Map() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("map", map);
        Assertions.assertEquals(JSONObject.fromObject(map), jsonObject.getJSONObject("map"));
    }

    @Test
    void testElement_Map2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        map.put("class", "java.lang.Object");
        map.put("excluded", "excluded");
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("map", map);
        JSONObject actual = jsonObject.getJSONObject("map");
        assertFalse(actual.has("class"));
    }

    @Test
    void testElement_Map2_exclusions() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        map.put("class", "java.lang.Object");
        map.put("pexcluded", "excluded");
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("map", map, jsonConfig);
        JSONObject actual = jsonObject.getJSONObject("map");
        assertFalse(actual.has("class"));
        assertFalse(actual.has("pexcluded"));
    }

    @Test
    void testElement_Map2_exclusions_ignoreDefault() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        map.put("class", "java.lang.Object");
        map.put("pexcluded", "excluded");
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        jsonConfig.setIgnoreDefaultExcludes(true);
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("map", map, jsonConfig);
        JSONObject actual = jsonObject.getJSONObject("map");
        assertTrue(actual.has("class"));
        assertFalse(actual.has("pexcluded"));
    }

    @Test
    void testElement_null_key() {
        assertThrows(JSONException.class, () -> new JSONObject().element(null, "value"));
    }

    @Test
    void testElement_Number() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("num", 2d);
        assertEquals(2d, jsonObject.getDouble("num"), 0d);
    }

    @Test
    void testElement_Object() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("bean", new BeanA());
        Assertions.assertEquals(JSONObject.fromObject(new BeanA()), jsonObject.getJSONObject("bean"));
    }

    @Test
    void testElement_String() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("str", "json");
        assertEquals("json", jsonObject.getString("str"));
    }

    @Test
    void testElement_String_JSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("str", "[]");
        assertEquals(new JSONArray().toString(), jsonObject.getString("str"));
    }

    @Test
    void testElement_String_null() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("str", (String) null);
        // special case, if value null, there is no value associated to key
        assertThrows(JSONException.class, () -> jsonObject.getString("str"));
    }

    @Test
    void testFromBean_array() {
        assertThrows(JSONException.class, () -> JSONObject.fromObject(new ArrayList<>()));
        assertThrows(JSONException.class, () -> JSONObject.fromObject(new String[] {"json"}));
    }

    @Test
    void testFromBean_ClassBean() {
        ClassBean classBean = new ClassBean();
        classBean.setKlass(Object.class);
        JSONObject json = JSONObject.fromObject(classBean);
        assertEquals("java.lang.Object", json.get("klass"));
    }

    @Test
    void testFromBean_DynaBean() throws Exception {
        JSONObject json = JSONObject.fromObject(createDynaBean());
        assertEquals("json", json.getString("name"));
        assertEquals("[1,2]", json.getString("str"));
        Assertions.assertEquals(JSONObject.fromObject("{'id':'1'}"), json.getJSONObject("json"));
        Assertions.assertEquals(JSONObject.fromObject("{'name':''}"), json.getJSONObject("jsonstr"));
    }

    @Test
    void testFromBean_JSONObject() {
        JSONObject json = new JSONObject();
        json.element("name", "json");
        Assertions.assertEquals(json, JSONObject.fromObject(json));
    }

    @Test
    void testFromBean_JSONString() {
        ObjectJSONStringBean bean = new ObjectJSONStringBean();
        bean.setId(1);
        bean.setName("json");
        JSONObject json = JSONObject.fromObject(bean);
        assertEquals("json", json.getString("name"));
        assertFalse(json.has("id"));
    }

    @Test
    void testFromBean_JSONTokener() {
        JSONTokener jsonTokener = new JSONTokener("{\"string\":\"json\"}");
        JSONObject json = JSONObject.fromObject(jsonTokener);
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromBean_Map() {
        Map<String, Object> map = new HashMap<>();
        map.put("bool", Boolean.TRUE);
        map.put("integer", 42);
        map.put("string", "json");

        JSONObject json = JSONObject.fromObject(map);
        assertTrue(json.getBoolean("bool"));
        assertEquals(42, json.getInt("integer"));
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromBean_noReadMethod() {
        JSONObject json = JSONObject.fromObject(new PropertyBean());
        assertTrue(json.has("propertyWithNoWriteMethod"));
        assertFalse(json.has("propertyWithNoReadMethod"));
    }

    @Test
    void testFromBean_null() {
        JSONObject json = JSONObject.fromObject(null);
        assertTrue(json.isNullObject());
        assertEquals(JSONNull.getInstance().toString(), json.toString());
    }

    @Test
    void testFromBean_String() {
        JSONObject json = JSONObject.fromObject("{\"string\":\"json\"}");
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromBean_use_wrappers() {
        JSONObject json = JSONObject.fromObject(Boolean.TRUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Byte.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Short.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Integer.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Long.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Float.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Double.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject('A');
        assertTrue(json.isEmpty());
    }

    @Test
    void testFromBeanWithJsonPropertyNameProcessor() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonPropertyNameProcessor(BeanA.class, new PrefixerPropertyNameProcessor("json"));
        JSONObject jsonObject = JSONObject.fromObject(new BeanA(), jsonConfig);
        assertNotNull(jsonObject);
        assertEquals(3, jsonObject.names().size());
        assertTrue(jsonObject.has("jsonbool"));
        assertTrue(jsonObject.has("jsonstring"));
        assertTrue(jsonObject.has("jsoninteger"));
    }

    @Test
    void testFromDynaBean_full() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("string", String.class);
        properties.put("number", Integer.class);
        properties.put("array", Object[].class);
        properties.put("list", List.class);
        properties.put("boolean", Boolean.class);
        properties.put("bean", BeanA.class);
        MorphDynaClass dynaClass = new MorphDynaClass("JSON", MorphDynaBean.class, properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("string", "json");
        dynaBean.set("number", 2d);
        dynaBean.set("array", new Integer[] {1, 2});
        dynaBean.set("list", new ArrayList<>());
        dynaBean.set("boolean", Boolean.TRUE);
        dynaBean.set("bean", new BeanA());

        JSONObject jsonObject = JSONObject.fromObject(dynaBean);
        assertEquals("json", jsonObject.get("string"));
        assertEquals(2d, jsonObject.get("number"));
        assertEquals(Boolean.TRUE, jsonObject.get("boolean"));
    }

    @Test
    void testFromDynaBean_null() {
        JSONObject jsonObject = JSONObject.fromObject(null);
        assertTrue(jsonObject.isNullObject());
    }

    @Test
    void testFromJSONTokener() {
        JSONTokener jsonTokener = new JSONTokener("{\"string\":\"json\"}");
        JSONObject json = JSONObject.fromObject(jsonTokener);
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromMap_nested_null_object() {
        Map<String, Object> map = new HashMap<>();
        map.put("nested", null);
        map.put("string", "json");

        JSONObject json = JSONObject.fromObject(map);
        assertEquals("json", json.getString("string"));
        Object nested = json.get("nested");
        assertTrue(JSONUtils.isNull(nested));
    }

    @Test
    void testFromMap_null_Map() {
        JSONObject json = JSONObject.fromObject(null);
        assertTrue(json.isNullObject());
        assertEquals(JSONNull.getInstance().toString(), json.toString());
    }

    @Test
    void testFromObject_array() {
        assertThrows(JSONException.class, () -> JSONObject.fromObject(new ArrayList<>()));
        assertThrows(JSONException.class, () -> JSONObject.fromObject(new String[] {"json"}));
    }

    @Test
    void testFromObject_Bean() {
        JSONObject json = JSONObject.fromObject(new BeanA());
        assertTrue(json.getBoolean("bool"));
        assertEquals(42, json.getInt("integer"));
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromObject_DynaBean() throws Exception {
        JSONObject json = JSONObject.fromObject(createDynaBean());
        assertEquals("json", json.getString("name"));
    }

    @Test
    void testFromObject_emptyBean() {
        EmptyBean bean = new EmptyBean();
        JSONObject json = JSONObject.fromObject(bean);
        JSONObject expected = new JSONObject();
        expected.element("arrayp", new JSONArray());
        expected.element("listp", new JSONArray());
        expected.element("bytep", 0);
        expected.element("shortp", 0);
        expected.element("intp", 0);
        expected.element("longp", 0);
        expected.element("floatp", 0);
        expected.element("doublep", 0d);
        expected.element("charp", "");
        expected.element("stringp", "");

        Assertions.assertEquals(expected, json);
    }

    @Test
    void testFromObject_ExtendedBean() {
        JSONObject json = JSONObject.fromObject(new BeanB());
        assertTrue(json.getBoolean("bool"));
        assertEquals(42, json.getInt("integer"));
        assertEquals("json", json.getString("string"));
        assertNotNull(json.get("intarray"));
    }

    @Test
    void testFromObject_ignoreTransientFields() {
        jsonConfig.setIgnoreTransientFields(true);
        TransientBean bean = new TransientBean();
        bean.setValue(42);
        bean.setTransientValue(84);
        JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
        assertTrue(jsonObject.has("value"));
        assertFalse(jsonObject.has("transientValue"));
    }

    @Test
    void testFromObject_JSONObject() {
        JSONObject expected = new JSONObject().element("id", "1").element("name", "json");
        JSONObject actual = JSONObject.fromObject(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_JSONString() {
        ObjectJSONStringBean bean = new ObjectJSONStringBean();
        bean.setId(1);
        bean.setName("json");
        JSONObject json = JSONObject.fromObject(bean);
        assertEquals("json", json.getString("name"));
        assertFalse(json.has("id"));
    }

    @Test
    void testFromObject_JSONTokener() {
        JSONTokener jsonTokener = new JSONTokener("{\"string\":\"json\"}");
        JSONObject json = JSONObject.fromObject(jsonTokener);
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromObject_Map() {
        Map<String, Object> map = new HashMap<>();
        map.put("bool", Boolean.TRUE);
        map.put("integer", 42);
        map.put("string", "json");
        map.put("array", JSONArray.fromObject("[1]"));
        map.put("object", JSONObject.fromObject("{\"name\":\"json\"}"));

        JSONObject json = JSONObject.fromObject(map);
        assertTrue(json.getBoolean("bool"));
        assertEquals(42, json.getInt("integer"));
        assertEquals("json", json.getString("string"));
        Assertions.assertEquals(JSONArray.fromObject("[1]"), json.getJSONArray("array"));
        Assertions.assertEquals(JSONObject.fromObject("{\"name\":\"json\"}"), json.getJSONObject("object"));
    }

    @Test
    void testFromObject_nested_bean() {
        JSONObject json = JSONObject.fromObject(new BeanC());
        assertNotNull(json.get("beanA"));
        assertNotNull(json.get("beanB"));
    }

    @Test
    void testFromObject_null() {
        JSONObject json = JSONObject.fromObject(null);
        assertTrue(json.isNullObject());
        assertEquals(JSONNull.getInstance().toString(), json.toString());
    }

    @Test
    void testFromObject_ObjectBean() {
        // FR 1611204
        ObjectBean bean = new ObjectBean();
        bean.setPbyte(Byte.valueOf("1"));
        bean.setPshort(Short.valueOf("1"));
        bean.setPint(Integer.valueOf("1"));
        bean.setPlong(Long.valueOf("1"));
        bean.setPfloat(Float.valueOf("1"));
        bean.setPdouble(Double.valueOf("1"));
        bean.setPchar('1');
        bean.setPboolean(Boolean.TRUE);
        bean.setPstring("json");
        bean.setParray(new String[] {"a", "b"});
        bean.setPbean(new BeanA());
        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        bean.setPlist(list);
        Map<String, Object> map = new HashMap<>();
        map.put("string", "json");
        bean.setPmap(map);

        JSONObject json = JSONObject.fromObject(bean);
        assertEquals(1, json.getInt("pbyte"));
        assertEquals(1, json.getInt("pshort"));
        assertEquals(1, json.getInt("pint"));
        assertEquals(1, json.getInt("plong"));
        assertEquals(1d, json.getDouble("pfloat"), 0d);
        assertEquals(1d, json.getDouble("pdouble"), 0d);
        assertTrue(json.getBoolean("pboolean"));
        assertEquals("json", json.get("pstring"));
        Assertions.assertEquals(JSONArray.fromObject("['a','b']"), json.getJSONArray("parray"));
        Assertions.assertEquals(JSONArray.fromObject("['1','2']"), json.getJSONArray("plist"));
        assertEquals("1", json.getString("pchar"));
        JSONObject b = new JSONObject()
                .element("string", "json")
                .element("integer", "42")
                .element("bool", "true");
        Assertions.assertEquals(b, json.getJSONObject("pbean"));
        b = new JSONObject().element("string", "json");
        Assertions.assertEquals(b, json.getJSONObject("pmap"));
    }

    @Test
    void testFromObject_ObjectBean_empty() {
        // FR 1611204
        ObjectBean bean = new ObjectBean();
        JSONObject json = JSONObject.fromObject(bean);

        String[] keys = {
            "pbyte",
            "pshort",
            "pint",
            "plong",
            "pfloat",
            "pdouble",
            "pboolean",
            "pchar",
            "pstring",
            "parray",
            "plist",
            "pmap",
            "pbean"
        };
        for (String key : keys) {
            assertEquals(JSONNull.getInstance(), json.get(key));
        }
    }

    @Test
    void testFromObject_String() {
        JSONObject json = JSONObject.fromObject("{\"string\":\"json\"}");
        assertEquals("json", json.getString("string"));
    }

    @Test
    void testFromObject_toBean_DynaBean() {
        // bug report 1540137

        String jsondata = "{\"person\":{\"phone\":[\"111-222-3333\",\"777-888-9999\"],"
                + "\"address\":{\"street\":\"123 somewhere place\",\"zip\":\"30005\",\"city\":\"Alpharetta\"},"
                + "\"email\":[\"allen@work.com\",\"allen@home.net\"],\"name\":\"Allen\"}}";

        JSONObject jsonobj = JSONObject.fromObject(jsondata);
        Object bean = JSONObject.toBean(jsonobj);
        // bean is a DynaBean
        assertInstanceOf(MorphDynaBean.class, bean);
        // convert the DynaBean to a JSONObject again
        JSONObject jsonobj2 = JSONObject.fromObject(bean);

        assertNotNull(jsonobj.getJSONObject("person"));
        assertFalse(JSONUtils.isNull(jsonobj.getJSONObject("person")));
        assertNotNull(jsonobj2.getJSONObject("person"));
        assertFalse(JSONUtils.isNull(jsonobj2.getJSONObject("person")));

        JSONObject person1 = jsonobj.getJSONObject("person");
        JSONObject person2 = jsonobj2.getJSONObject("person");
        assertEquals(person1.get("name"), person2.get("name"));
        assertEquals(person1.get("phone").toString(), person2.get("phone").toString());
        assertEquals(person1.get("email").toString(), person2.get("email").toString());
        JSONObject address1 = person1.getJSONObject("address");
        JSONObject address2 = person2.getJSONObject("address");
        assertEquals(address1.get("street"), address2.get("street"));
        assertEquals(address1.get("zip"), address2.get("zip"));
        assertEquals(address1.get("city"), address2.get("city"));
    }

    @Test
    void testFromObject_use_wrappers() {
        JSONObject json = JSONObject.fromObject(Boolean.TRUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Byte.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Short.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Integer.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Long.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Float.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject(Double.MIN_VALUE);
        assertTrue(json.isEmpty());
        json = JSONObject.fromObject('A');
        assertTrue(json.isEmpty());
    }

    @Test
    void testFromObject_withCustomDefaultValueProcessor() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerDefaultValueProcessor(Integer.class, new NumberDefaultValueProcessor());
        JSONObject jsonObject = JSONObject.fromObject(new NumberBean(), jsonConfig);
        assertNotNull(jsonObject);
        assertEquals(0, jsonObject.get("pwbyte"));
        assertEquals(0, jsonObject.get("pwshort"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwint"));
        assertEquals(0, jsonObject.get("pwlong"));
        assertEquals(0, jsonObject.get("pwfloat"));
        assertEquals(0d, jsonObject.get("pwdouble"));
        assertEquals(0, jsonObject.get("pbigdec"));
        assertEquals(0, jsonObject.get("pbigint"));
        assertEquals(0, jsonObject.get("pbyte"));
        assertEquals(0, jsonObject.get("pshort"));
        assertEquals(0, jsonObject.get("pint"));
        assertEquals(0, jsonObject.get("plong"));
        assertEquals(0d, jsonObject.get("pfloat"));
        assertEquals(0d, jsonObject.get("pdouble"));
    }

    @Test
    void testFromObject_withCustomDefaultValueProcessor_andMatcher() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerDefaultValueProcessor(Integer.class, new NumberDefaultValueProcessor());
        jsonConfig.setDefaultValueProcessorMatcher(new NumberDefaultValueProcessorMatcher());
        JSONObject jsonObject = JSONObject.fromObject(new NumberBean(), jsonConfig);
        assertNotNull(jsonObject);
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pbigdec"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pbigint"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwbyte"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwshort"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwint"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwlong"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwfloat"));
        assertEquals(NumberDefaultValueProcessor.NUMBER, jsonObject.get("pwdouble"));
        assertEquals(0, jsonObject.get("pbyte"));
        assertEquals(0, jsonObject.get("pshort"));
        assertEquals(0, jsonObject.get("pint"));
        assertEquals(0, jsonObject.get("plong"));
        assertEquals(0d, jsonObject.get("pfloat"));
        assertEquals(0d, jsonObject.get("pdouble"));
    }

    @Test
    void testFromObject_withExcludesPerClass() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerPropertyExclusion(BeanA.class, "bool");
        JSONObject jsonA = JSONObject.fromObject(new BeanA(), jsonConfig);
        JSONObject jsonB = JSONObject.fromObject(new BeanB(), jsonConfig);
        assertNotNull(jsonA);
        assertNotNull(jsonB);
        assertFalse(jsonA.has("bool"));
        assertTrue(jsonB.has("bool"));
    }

    @Test
    void testFromObject_withExcludesPerClassAndMatcher() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerPropertyExclusion(BeanA.class, "bool");
        jsonConfig.setPropertyExclusionClassMatcher(new BeanAPropertyExclusionClassMatcher());
        JSONObject jsonA = JSONObject.fromObject(new BeanA(), jsonConfig);
        JSONObject jsonB = JSONObject.fromObject(new BeanB(), jsonConfig);
        assertNotNull(jsonA);
        assertNotNull(jsonB);
        assertFalse(jsonA.has("bool"));
        assertFalse(jsonB.has("bool"));
    }

    @Test
    void testFromObject_withFilters() {
        PrimitiveBean bean = new PrimitiveBean();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new NumberPropertyFilter());
        JSONObject json = JSONObject.fromObject(bean, jsonConfig);
        assertNotNull(json);
        assertTrue(json.has("pbean"));
        assertTrue(json.has("pclass"));
        assertTrue(json.has("pexcluded"));
        assertTrue(json.has("plist"));
        assertTrue(json.has("pmap"));
        assertTrue(json.has("pstring"));
        assertTrue(json.has("parray"));

        assertTrue(json.has("pboolean"));
        assertFalse(json.has("pbyte"));
        assertFalse(json.has("pshort"));
        assertFalse(json.has("pint"));
        assertFalse(json.has("plong"));
        assertFalse(json.has("pfloat"));
        assertFalse(json.has("pdouble"));
        assertTrue(json.has("pchar"));
    }

    @Test
    void testFromObject_withFiltersAndExcludes() {
        PrimitiveBean bean = new PrimitiveBean();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new NumberPropertyFilter());
        jsonConfig.setExcludes(new String[] {"pexcluded"});
        JSONObject json = JSONObject.fromObject(bean, jsonConfig);
        assertNotNull(json);
        assertTrue(json.has("pbean"));
        assertTrue(json.has("pclass"));
        assertFalse(json.has("pexcluded"));
        assertTrue(json.has("plist"));
        assertTrue(json.has("pmap"));
        assertTrue(json.has("pstring"));
        assertTrue(json.has("parray"));

        assertTrue(json.has("pboolean"));
        assertFalse(json.has("pbyte"));
        assertFalse(json.has("pshort"));
        assertFalse(json.has("pint"));
        assertFalse(json.has("plong"));
        assertFalse(json.has("pfloat"));
        assertFalse(json.has("pdouble"));
        assertTrue(json.has("pchar"));
    }

    @Test
    void testFromString_null_String() {
        JSONObject json = JSONObject.fromObject(null);
        assertTrue(json.isNullObject());
        assertEquals(JSONNull.getInstance().toString(), json.toString());
    }

    @Test
    void testHas() {
        assertFalse(new JSONObject().has("any"));
        assertTrue(new JSONObject().element("any", "value").has("any"));
    }

    @Test
    void testLength() {
        assertEquals(0, new JSONObject().size());
    }

    @Test
    void testLength_nullObject() {
        /*
        try{
           new JSONObject( true ).size();
           fail( "Expected a JSONException" );
        }catch( JSONException expected ){
           // ok
        }
        */
        assertEquals(0, new JSONObject(true).size());
    }

    @Test
    void testOptBoolean() {
        assertFalse(new JSONObject().optBoolean("any"));
    }

    @Test
    void testOptBoolean_defaultValue() {
        assertTrue(new JSONObject().optBoolean("any", true));
    }

    @Test
    void testOptDouble() {
        assertTrue(Double.isNaN(new JSONObject().optDouble("any")));
    }

    @Test
    void testOptDouble_defaultValue() {
        assertEquals(2d, new JSONObject().optDouble("any", 2d), 0d);
    }

    @Test
    void testOptInt() {
        assertEquals(0, new JSONObject().optInt("any"));
    }

    @Test
    void testOptInt_defaultValue() {
        assertEquals(1, new JSONObject().optInt("any", 1));
    }

    @Test
    void testOptJSONArray() {
        JSONObject json = new JSONObject();
        assertNull(json.optJSONArray("a"));
        json.element("a", new JSONArray());
        Assertions.assertEquals(new JSONArray(), json.optJSONArray("a"));
    }

    @Test
    void testOptJSONObject() {
        JSONObject json = new JSONObject();
        assertNull(json.optJSONObject("a"));
        json.element("a", new JSONObject());
        Assertions.assertEquals(new JSONObject(), json.optJSONObject("a"));
    }

    @Test
    void testOptLong() {
        assertEquals(0L, new JSONObject().optLong("any"));
    }

    @Test
    void testOptLong_defaultValue() {
        assertEquals(1L, new JSONObject().optLong("any", 1L));
    }

    @Test
    void testOptString() {
        assertEquals("", new JSONObject().optString("any"));
    }

    @Test
    void testOptString_defaultValue() {
        assertEquals("json", new JSONObject().optString("any", "json"));
    }

    @Test
    void testToBean() throws Exception {
        String json = "{name=\"json\",bool:true,int:1,double:2.2,array:[1,2]}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject);
        assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
        List<?> expected = (List<?>) JSONArray.toCollection(jsonObject.getJSONArray("array"));
        Assertions.assertEquals(expected, (List<?>) PropertyUtils.getProperty(bean, "array"));
    }

    @Test
    void testToBean_BeanA() {
        String json = "{bool:true,integer:1,string:\"json\"}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        BeanA bean = (BeanA) JSONObject.toBean(jsonObject, BeanA.class);
        assertEquals(jsonObject.get("bool"), bean.isBool());
        assertEquals(jsonObject.get("integer"), bean.getInteger());
        assertEquals(jsonObject.get("string"), bean.getString());
    }

    @Test
    void testToBean_BeanB() {
        String json = "{bool:true,integer:1,string:\"json\",intarray:[4,5,6]}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        BeanB bean = (BeanB) JSONObject.toBean(jsonObject, BeanB.class);
        assertEquals(jsonObject.get("bool"), bean.isBool());
        assertEquals(jsonObject.get("integer"), bean.getInteger());
        assertEquals(jsonObject.get("string"), bean.getString());
        Assertions.assertEquals(bean.getIntarray(), JSONArray.toArray(jsonObject.getJSONArray("intarray")));
    }

    @Test
    void testToBean_ClassBean() {
        JSONObject json = new JSONObject();
        json.element("klass", "java.lang.Object");

        ClassBean bean = (ClassBean) JSONObject.toBean(json, ClassBean.class);
        assertEquals(Object.class, bean.getKlass());
    }

    @Test
    void testToBean_DynaBean__BigInteger_BigDecimal() {
        BigInteger l = new BigDecimal("1.7976931348623157E308").toBigInteger();
        BigDecimal m = new BigDecimal("1.7976931348623157E307").add(new BigDecimal("0.0001"));
        JSONObject json = new JSONObject()
                .element("i", BigInteger.ZERO)
                .element("d", MorphUtils.BIGDECIMAL_ONE)
                .element("bi", l)
                .element("bd", m);
        Object bean = JSONObject.toBean(json);
        Object i = ((MorphDynaBean) bean).get("i");
        Object d = ((MorphDynaBean) bean).get("d");
        assertInstanceOf(Integer.class, i);
        assertInstanceOf(Integer.class, d);

        Object bi = ((MorphDynaBean) bean).get("bi");
        Object bd = ((MorphDynaBean) bean).get("bd");
        assertInstanceOf(BigInteger.class, bi);
        assertInstanceOf(BigDecimal.class, bd);
    }

    @Test
    void testToBean_emptyBean() {
        EmptyBean bean = new EmptyBean();

        JSONObject json = JSONObject.fromObject(bean);
        JSONObject expected = new JSONObject();
        expected.element("arrayp", new JSONArray());
        expected.element("listp", new JSONArray());
        expected.element("bytep", 0);
        expected.element("shortp", 0);
        expected.element("intp", 0);
        expected.element("longp", 0);
        expected.element("floatp", 0);
        expected.element("doublep", 0d);
        expected.element("charp", "");
        expected.element("stringp", "");

        Assertions.assertEquals(expected, json);

        EmptyBean bean2 = (EmptyBean) JSONObject.toBean(json, EmptyBean.class);

        ArrayAssertions.assertEquals(new Object[0], bean2.getArrayp());
        Assertions.assertEquals(new ArrayList<>(), bean2.getListp());
        Assertions.assertEquals((Byte) (byte) 0, bean2.getBytep());
        Assertions.assertEquals((Short) (short) 0, bean2.getShortp());
        Assertions.assertEquals((Integer) 0, bean2.getIntp());
        Assertions.assertEquals((Long) 0L, bean2.getLongp());
        Assertions.assertEquals(0f, bean2.getFloatp());
        Assertions.assertEquals(0d, bean2.getDoublep());
        Assertions.assertEquals((Character) '\0', bean2.getCharp());
        assertEquals("", bean2.getStringp());
    }

    @Test
    void testToBean_interface() {
        // BUG 1542104

        assertThrows(
                JSONException.class, () -> JSONObject.toBean(JSONObject.fromObject("{\"int\":1}"), Serializable.class));
    }

    @Test
    void testToBean_Map() {
        // BUG 1542104

        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        Object obj = JSONObject.toBean(JSONObject.fromObject(map), Map.class);
        assertInstanceOf(Map.class, obj);
        assertEquals(map.get("name"), ((Map<?, ?>) obj).get("name"));
    }

    @Test
    void testToBean_nested() throws Exception {
        String json = "{name=\"json\",bool:true,int:1,double:2.2,nested:{nested:true}}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject);
        assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
        JSONObject nestedJson = jsonObject.getJSONObject("nested");
        Object nestedBean = PropertyUtils.getProperty(bean, "nested");
        assertEquals(nestedJson.get("nested"), PropertyUtils.getProperty(nestedBean, "nested"));
    }

    @Test
    void testToBean_nested_beans__null_object() {
        // BUG 1553617

        String json = "{\"beanA\":{bool:true,integer:1,string:\"jsonbean\"},\"beanB\":null}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        BeanC bean = (BeanC) JSONObject.toBean(jsonObject, BeanC.class);
        assertNotNull(bean);
        BeanA beanA = bean.getBeanA();
        assertNotNull(beanA);
        assertTrue(beanA.isBool());
        assertEquals(1, beanA.getInteger());
        assertEquals("jsonbean", beanA.getString());
        BeanB beanB = bean.getBeanB();
        assertNull(beanB);
    }

    @Test
    void testToBean_nested_beans_in_list__beans() {
        // BUG 1592799

        ListingBean listingBean = new ListingBean();

        ValueBean beanA1 = new ValueBean();
        beanA1.setValue(90000);
        ValueBean beanA2 = new ValueBean();
        beanA2.setValue(91000);

        listingBean.addAttribute(beanA1);
        listingBean.addAttribute(beanA2);

        JSONObject jsonObject = JSONObject.fromObject(listingBean);
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("attributes", ValueBean.class);
        ListingBean listingBean2 = (ListingBean) JSONObject.toBean(jsonObject, ListingBean.class, classMap);
        List<?> attributes = listingBean2.getAttributes();
        Object ba = attributes.get(0);
        Object bb = attributes.get(1);

        assertInstanceOf(ValueBean.class, ba);
        assertInstanceOf(ValueBean.class, bb);
        assertEquals(beanA1.getValue(), ((ValueBean) ba).getValue());
        assertEquals(beanA2.getValue(), ((ValueBean) bb).getValue());
    }

    @Test
    void testToBean_nested_beans_in_list__DynaBean() {
        // BUG 1592799

        ListingBean listingBean = new ListingBean();

        ValueBean beanA1 = new ValueBean();
        beanA1.setValue(90000);
        ValueBean beanA2 = new ValueBean();
        beanA2.setValue(91000);

        listingBean.addAttribute(beanA1);
        listingBean.addAttribute(beanA2);

        JSONObject jsonObject = JSONObject.fromObject(listingBean);
        ListingBean listingBean2 = (ListingBean) JSONObject.toBean(jsonObject, ListingBean.class);
        List<?> attributes = listingBean2.getAttributes();
        Object ba = attributes.get(0);
        Object bb = attributes.get(1);

        assertInstanceOf(MorphDynaBean.class, ba);
        assertInstanceOf(MorphDynaBean.class, bb);
        assertEquals(beanA1.getValue(), ((MorphDynaBean) ba).get("value"));
        assertEquals(beanA2.getValue(), ((MorphDynaBean) bb).get("value"));
    }

    @Test
    void testToBean_nested_beans_in_map__beans() {
        // BUG 1542092

        MappingBean mappingBean = new MappingBean();

        ValueBean beanA = new ValueBean();
        beanA.setValue(90000);
        ValueBean beanB = new ValueBean();
        beanB.setValue(91000);

        mappingBean.addAttribute("beanA", beanA);
        mappingBean.addAttribute("beanB", beanB);

        JSONObject jsonObject = JSONObject.fromObject(mappingBean);
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("bean.*", ValueBean.class);
        MappingBean mappingBean2 = (MappingBean) JSONObject.toBean(jsonObject, MappingBean.class, classMap);
        Object ba = mappingBean2.getAttributes().get("beanA");
        Object bb = mappingBean2.getAttributes().get("beanB");
        assertInstanceOf(ValueBean.class, ba);
        assertInstanceOf(ValueBean.class, bb);
        assertEquals(beanA.getValue(), ((ValueBean) ba).getValue());
        assertEquals(beanB.getValue(), ((ValueBean) bb).getValue());
    }

    @Test
    void testToBean_nested_beans_in_map__DynaBean() {
        // BUG 1542092

        MappingBean mappingBean = new MappingBean();

        ValueBean beanA = new ValueBean();
        beanA.setValue(90000);
        ValueBean beanB = new ValueBean();
        beanB.setValue(91000);

        mappingBean.addAttribute("beanA", beanA);
        mappingBean.addAttribute("beanB", beanB);

        JSONObject jsonObject = JSONObject.fromObject(mappingBean);
        MappingBean mappingBean2 = (MappingBean) JSONObject.toBean(jsonObject, MappingBean.class);
        Object ba = mappingBean2.getAttributes().get("beanA");
        Object bb = mappingBean2.getAttributes().get("beanB");
        assertInstanceOf(MorphDynaBean.class, ba);
        assertInstanceOf(MorphDynaBean.class, bb);
        assertEquals(beanA.getValue(), ((MorphDynaBean) ba).get("value"));
        assertEquals(beanB.getValue(), ((MorphDynaBean) bb).get("value"));
    }

    @Test
    void testToBean_nested_beans_in_set__beans() {
        // FR 1847116

        SetBean setBean = new SetBean();

        ValueBean beanA1 = new ValueBean();
        beanA1.setValue(90000);
        ValueBean beanA2 = new ValueBean();
        beanA2.setValue(91000);

        setBean.addAttribute(beanA1);
        setBean.addAttribute(beanA2);

        JSONObject jsonObject = JSONObject.fromObject(setBean);
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("attributes", ValueBean.class);
        SetBean setBean2 = (SetBean) JSONObject.toBean(jsonObject, SetBean.class, classMap);
        assertEquals(setBean, setBean2);
    }

    @Test
    void testToBean_nested_beans_in_set__DynaBean() {
        // FR 1847116

        SetBean setBean = new SetBean();

        ValueBean beanA1 = new ValueBean();
        beanA1.setValue(90000);
        ValueBean beanA2 = new ValueBean();
        beanA2.setValue(91000);

        setBean.addAttribute(beanA1);
        setBean.addAttribute(beanA2);

        JSONObject jsonObject = JSONObject.fromObject(setBean);
        // SetBean setBean2 = (SetBean) JSONObject.toBean( jsonObject, SetBean.class );
        // assertEquals( setBean, setBean2 );
    }

    @Test
    void testToBean_nested_dynabeans__null_object() throws Exception {
        // BUG 1553617

        String json = "{\"beanA\":{bool:true,integer:1,string:\"jsonbean\"},\"beanB\":null}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject);
        assertNotNull(bean);
        Object beanA = PropertyUtils.getProperty(bean, "beanA");
        assertNotNull(beanA);
        assertEquals(Boolean.TRUE, PropertyUtils.getProperty(beanA, "bool"));
        assertEquals(1, PropertyUtils.getProperty(beanA, "integer"));
        assertEquals("jsonbean", PropertyUtils.getProperty(beanA, "string"));
        Object beanB = PropertyUtils.getProperty(bean, "beanB");
        assertNull(beanB);
    }

    @Test
    void testtoBean_noWriteMethod() {
        JSONObject json = new JSONObject();
        json.element("propertyWithNoReadMethod", "json");
        json.element("propertyWithNoWriteMethod", "value");
        PropertyBean bean = (PropertyBean) JSONObject.toBean(json, PropertyBean.class);
        assertNotNull(bean);
        assertEquals("json", bean.valueOfPropertyWithNoReadMethod());
        assertEquals("json", bean.getPropertyWithNoWriteMethod());
    }

    @Test
    void testToBean_null() {
        assertNull(JSONObject.toBean((JSONObject) null));
    }

    @Test
    void testToBean_null_2() {
        assertNull(JSONObject.toBean(null, BeanA.class));
    }

    @Test
    void testToBean_null_object() {
        JSONObject jsonObject = new JSONObject(true);
        BeanA bean = (BeanA) JSONObject.toBean(jsonObject, BeanA.class);
        assertNull(bean);
    }

    @Test
    void testToBean_null_values() {
        // bug report 1540196

        String json = "{\"items\":[[\"000\"],[\"010\", \"011\"],[\"020\"]]}";
        JSONObject jsonObject = JSONObject.fromObject(json);

        BeanFoo foo = (BeanFoo) JSONObject.toBean(jsonObject, BeanFoo.class);
        assertNotNull(foo);
        assertNotNull(foo.getItems());
        String[][] items = foo.getItems();
        assertEquals(3, items.length);
        assertEquals("000", items[0][0]);
        assertEquals("010", items[1][0]);
        assertEquals("011", items[1][1]);
        assertEquals("020", items[2][0]);
    }

    @Test
    void testToBean_NumberBean() {
        JSONObject json = new JSONObject();
        json.element("pbyte", (byte) 2);
        json.element("pshort", (short) 2);
        json.element("pint", 2);
        json.element("plong", 2L);
        json.element("pfloat", 2f);
        json.element("pdouble", 2d);
        json.element("pbigint", new BigInteger("2"));
        json.element("pbigdec", new BigDecimal("2"));

        NumberBean bean = (NumberBean) JSONObject.toBean(json, NumberBean.class);
        assertEquals((byte) 2, bean.getPbyte());
        assertEquals((short) 2, bean.getPshort());
        assertEquals(2, bean.getPint());
        assertEquals(2L, bean.getPlong());
        assertEquals(2f, bean.getPfloat(), 0f);
        assertEquals(2d, bean.getPdouble(), 0d);
        assertEquals(new BigInteger("2"), bean.getPbigint());
        assertEquals(new BigDecimal("2"), bean.getPbigdec());
    }

    @Test
    void testToBean_NumberBean_2() {
        JSONObject json = new JSONObject();
        json.element("pbyte", 2);
        json.element("pshort", 2);
        json.element("pint", 2);
        json.element("plong", 2);
        json.element("pfloat", 2);
        json.element("pdouble", 2);
        json.element("pbigint", 2);
        json.element("pbigdec", 2);

        NumberBean bean = (NumberBean) JSONObject.toBean(json, NumberBean.class);
        assertEquals((byte) 2, bean.getPbyte());
        assertEquals((short) 2, bean.getPshort());
        assertEquals(2, bean.getPint());
        assertEquals(2L, bean.getPlong());
        assertEquals(2f, bean.getPfloat(), 0f);
        assertEquals(2d, bean.getPdouble(), 0d);
        assertEquals(new BigInteger("2"), bean.getPbigint());
        assertEquals(new BigDecimal("2"), bean.getPbigdec());
    }

    @Test
    void testToBean_ObjectBean() {
        // FR 1611204
        ObjectBean bean = new ObjectBean();
        bean.setPbyte(Byte.valueOf("1"));
        bean.setPshort(Short.valueOf("1"));
        bean.setPint(Integer.valueOf("1"));
        bean.setPlong(Long.valueOf("1"));
        bean.setPfloat(Float.valueOf("1"));
        bean.setPdouble(Double.valueOf("1"));
        bean.setPchar('1');
        bean.setPboolean(Boolean.TRUE);
        bean.setPstring("json");
        bean.setParray(new String[] {"a", "b"});
        bean.setPbean(new BeanA());
        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        bean.setPlist(list);
        Map<String, Object> map = new HashMap<>();
        map.put("string", "json");
        bean.setPmap(map);
        JSONObject json = JSONObject.fromObject(bean);
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("pbean", BeanA.class);
        ObjectBean obj = (ObjectBean) JSONObject.toBean(json, ObjectBean.class, classMap);
        assertEquals(Integer.valueOf("1"), obj.getPbyte());
        assertEquals(Integer.valueOf("1"), obj.getPshort());
        assertEquals(Integer.valueOf("1"), obj.getPint());
        assertEquals(Integer.valueOf("1"), obj.getPlong());
        assertEquals(Double.valueOf("1"), obj.getPfloat());
        assertEquals(Double.valueOf("1"), obj.getPdouble());
        assertEquals("1", obj.getPchar());
        assertEquals("json", obj.getPstring());
        List<Object> l = new ArrayList<>();
        l.add("a");
        l.add("b");
        ArrayAssertions.assertEquals(l.toArray(), (Object[]) obj.getParray());
        l = new ArrayList<>();
        l.add("1");
        l.add("2");
        ArrayAssertions.assertEquals(l.toArray(), (Object[]) obj.getPlist());
        assertEquals(new BeanA(), obj.getPbean());
        assertInstanceOf(MorphDynaBean.class, obj.getPmap());
    }

    @Test
    void testToBean_ObjectBean_empty() throws Exception {
        // FR 1611204
        ObjectBean bean = new ObjectBean();
        JSONObject json = JSONObject.fromObject(bean);
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("bean", BeanA.class);
        ObjectBean obj = (ObjectBean) JSONObject.toBean(json, ObjectBean.class, classMap);

        String[] keys = {
            "pbyte",
            "pshort",
            "pint",
            "plong",
            "pfloat",
            "pdouble",
            "pboolean",
            "pchar",
            "pstring",
            "parray",
            "plist",
            "pmap",
            "pbean"
        };
        for (String key : keys) {
            assertNull(PropertyUtils.getProperty(obj, key));
        }
    }

    @Test
    void testToBean_rootObject() {
        JSONObject json =
                new JSONObject().element("bool", "false").element("integer", 84).element("string", "bean");
        BeanA expected = new BeanA();
        BeanA actual = (BeanA) JSONObject.toBean(json, expected, new JsonConfig());
        assertNotNull(actual);
        assertEquals(expected, actual);
        assertFalse(actual.isBool());
        assertEquals(84, actual.getInteger());
        assertEquals("bean", actual.getString());
    }

    @Test
    void testToBean_withFilters() {
        BeanA bean = new BeanA();
        bean.setBool(false);
        bean.setInteger(84);
        bean.setString("filter");
        JSONObject json = JSONObject.fromObject(bean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(BeanA.class);
        jsonConfig.setJavaPropertyFilter(new BeanAPropertyFilter());
        BeanA actual = (BeanA) JSONObject.toBean(json, jsonConfig);
        assertNotNull(actual);
        assertTrue(actual.isBool());
        assertEquals(42, actual.getInteger());
        assertEquals("filter", actual.getString());
    }

    @Test
    void testToBean_withNonJavaIdentifier_camelCase_Strategy() {
        JSONObject json = new JSONObject().element("camel case", "json");
        jsonConfig.setJavaIdentifierTransformer(JavaIdentifierTransformer.CAMEL_CASE);
        jsonConfig.setRootClass(JavaIdentifierBean.class);
        JavaIdentifierBean bean = (JavaIdentifierBean) JSONObject.toBean(json, jsonConfig);
        assertNotNull(bean);
        assertEquals("json", bean.getCamelCase());
    }

    @Test
    void testToBean_withNonJavaIdentifier_underScore_Strategy() {
        JSONObject json = new JSONObject().element("under score", "json");
        jsonConfig.setJavaIdentifierTransformer(JavaIdentifierTransformer.UNDERSCORE);
        jsonConfig.setRootClass(JavaIdentifierBean.class);
        JavaIdentifierBean bean = (JavaIdentifierBean) JSONObject.toBean(json, jsonConfig);
        assertNotNull(bean);
        assertEquals("json", bean.getUnder_score());
    }

    @Test
    void testToBean_withNonJavaIdentifier_whitespace_Strategy() {
        JSONObject json = new JSONObject().element(" white space ", "json");
        jsonConfig.setJavaIdentifierTransformer(JavaIdentifierTransformer.WHITESPACE);
        jsonConfig.setRootClass(JavaIdentifierBean.class);
        JavaIdentifierBean bean = (JavaIdentifierBean) JSONObject.toBean(json, jsonConfig);
        assertNotNull(bean);
        assertEquals("json", bean.getWhitespace());
    }

    @Test
    void testToBean_withPropertySetStrategy() {
        JSONObject json = new JSONObject().element("key", "value");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(MappingBean.class);
        jsonConfig.setPropertySetStrategy(new MappingPropertySetStrategy());
        MappingBean bean = (MappingBean) JSONObject.toBean(json, jsonConfig);
        assertNotNull(bean);
        assertEquals("value", bean.getAttributes().get("key"));
    }

    @Test
    void testToBeanWithJavaPropertyNameProcessor() {
        String json = "{bool:false}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJavaPropertyNameProcessor(BeanA.class, new SwapPropertyNameProcessor());
        jsonConfig.setRootClass(BeanA.class);
        BeanA bean = (BeanA) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertTrue(bean.isBool());
        assertEquals("false", bean.getString());
    }

    @Test
    void testToJSONArray() {
        String json = "{bool:true,integer:1,string:\"json\"}";
        JSONArray names = JSONArray.fromObject("['string','integer','bool']");
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.toJSONArray(names);
        assertEquals("json", jsonArray.getString(0));
        assertEquals(1, jsonArray.getInt(1));
        assertTrue(jsonArray.getBoolean(2));
    }

    @BeforeEach
    void setUp() {
        jsonConfig = new JsonConfig();
    }

    private MorphDynaBean createDynaBean() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("name", String.class);
        properties.put("jsonstr", JSONString.class);
        properties.put("json", JSON.class);
        properties.put("str", String.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("name", "json");
        dynaBean.set("jsonstr", new ObjectJSONStringBean());
        dynaBean.set("json", new JSONObject().element("id", "1"));
        dynaBean.set("str", "[1,2]");
        // JSON Strings can not be null, only empty
        return dynaBean;
    }

    public static class BeanAPropertyExclusionClassMatcher extends PropertyExclusionClassMatcher {
        @Override
        public Object getMatch(Class target, Set set) {
            for (Object o : set) {
                Class<?> c = (Class<?>) o;
                if (BeanA.class.isAssignableFrom(c)) {
                    return c;
                }
            }
            return null;
        }
    }

    public static class BeanAPropertyFilter implements PropertyFilter {
        @Override
        public boolean apply(Object source, String name, Object value) {
            return "bool".equals(name) || "integer".equals(name);
        }
    }

    public static class MappingPropertySetStrategy extends PropertySetStrategy {
        @Override
        public void setProperty(Object bean, String key, Object value) throws JSONException {
            ((MappingBean) bean).addAttribute(key, value);
        }
    }

    public static class NumberDefaultValueProcessor implements DefaultValueProcessor {
        public static final Integer NUMBER = 42;

        @Override
        public Object getDefaultValue(Class type) {
            return NUMBER;
        }
    }

    public static class NumberDefaultValueProcessorMatcher extends DefaultValueProcessorMatcher {
        @Override
        public Object getMatch(Class target, Set set) {
            for (Object o : set) {
                Class<?> c = (Class<?>) o;
                if (Number.class.isAssignableFrom(c)) {
                    return c;
                }
            }
            return null;
        }
    }

    public static class NumberPropertyFilter implements PropertyFilter {
        @Override
        public boolean apply(Object source, String name, Object value) {
            return value != null && Number.class.isAssignableFrom(value.getClass());
        }
    }

    public static class SwapPropertyNameProcessor implements PropertyNameProcessor {
        @Override
        public String processPropertyName(Class beanClass, String name) {
            if (name.equals("bool")) {
                return "string";
            }
            return name;
        }
    }

    @Test
    void test_fromJSONObject() {}

    @Test
    void testCanonicalWrite() throws Exception {
        JSONArray a = new JSONArray();
        a.add(true);
        //        a.add(null);
        a.add(1);
        a.add(5.3);
        JSONObject o = new JSONObject();
        o.put("key1", "1");
        o.put("key2", "2");
        o.put("key3", "3");
        o.put("string", "123\r\n\b\t\f\\\\u65E5\\u672C\\u8A9E");
        a.add(o);

        StringWriter sw = new StringWriter();
        a.writeCanonical(sw);
        assertEquals(
                "[true,1,5.3,{\"key1\":\"1\",\"key2\":\"2\",\"key3\":\"3\",\"string\":\"123\\u000d\\u000a\\u0008\\u0009\\u000c\\\\\\\\u65E5\\\\u672C\\\\u8A9E\"}]",
                sw.toString());
    }

    @Test
    void testMayBeJSON() {
        assertEquals("[foo]", JSONObject.fromObject("{x=\"[foo]\"}").getString("x"));
    }
}

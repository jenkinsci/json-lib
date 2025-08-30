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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import net.sf.ezmorph.beanutils.DynaBean;
import net.sf.json.sample.ArrayJSONStringBean;
import net.sf.json.sample.BeanA;
import net.sf.json.util.JSONTokener;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArray {

    @Test
    void testDiscard() {
        JSONArray jsonArray =
                new JSONArray().element("1").element("true").element("string").element("[1,2,3]");
        assertEquals(4, jsonArray.size());
        jsonArray.discard("string").discard(0);
        assertEquals(2, jsonArray.size());
        assertFalse(jsonArray.contains("string"));
        assertFalse(jsonArray.contains("1"));
    }

    @Test
    void testConstructor_Collection() {
        List<Object> l = new ArrayList<>();
        l.add(Boolean.TRUE);
        l.add(1);
        l.add("string");
        l.add(Object.class);
        testJSONArray(l, "[true,1,\"string\",\"java.lang.Object\"]");
    }

    @Test
    void testConstructor_Collection_JSONArray() {
        List<Object> l = new ArrayList<>();
        l.add(JSONArray.fromObject(new int[] {1, 2}));
        testJSONArray(l, "[[1,2]]");
    }

    @Test
    void testConstructor_Collection_JSONString() {
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        List<Object> l = new ArrayList<>();
        l.add(bean);
        testJSONArray(l, "[[\"json\",\"json\"]]");
    }

    @Test
    void testConstructor_Collection_nulls() {
        List<Object> l = new ArrayList<>();
        l.add(null);
        l.add(null);
        testJSONArray(l, "[null,null]");
    }

    @Test
    void testConstructor_JSONArray() {
        JSONArray expected = JSONArray.fromObject("[1,2]");
        JSONArray actual = JSONArray.fromObject(JSONArray.fromObject("[1,2]"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_JSONTokener_nulls() {
        testJSONArray(new JSONTokener("[,,]"), "[null,null]");
    }

    @Test
    void testConstructor_JSONTokener_syntax_errors() {
        assertThrows(JSONException.class, () -> JSONArray.fromObject(""));
    }

    @Test
    void testConstructor_Object_Array() {
        JSONArray expected = JSONArray.fromObject("[\"json\",1]");
        JSONArray actual = JSONArray.fromObject(new Object[] {"json", 1});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_Object_Array_Array() {
        JSONArray expected = JSONArray.fromObject("[[1,2]]");
        JSONArray actual = JSONArray.fromObject(new Object[] {new int[] {1, 2}});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_Object_Array_BigDecimal() {
        // bug 1596168

        // an array of BigDecimals
        Number[] numberArray =
                new Number[] {BigDecimal.valueOf(10000000000L, 10), new BigDecimal("-1.0"), new BigDecimal("99.99E-1")};

        assertEquals("1.0000000000", numberArray[0].toString());
        assertEquals("-1.0", numberArray[1].toString());
        assertEquals("9.999", numberArray[2].toString());

        JSONArray jsonNumArray = JSONArray.fromObject(numberArray);

        assertEquals("1.0000000000", jsonNumArray.get(0).toString());
        assertEquals("-1.0", jsonNumArray.get(1).toString());
        assertEquals("9.999", jsonNumArray.get(2).toString());
    }

    @Test
    void testConstructor_Object_Array_BigInteger() {
        // bug 1596168

        Number[] numberArray =
                new Number[] {new BigInteger("18437736874454810627"), new BigInteger("9007199254740990")};

        assertEquals("18437736874454810627", numberArray[0].toString());
        assertEquals("9007199254740990", numberArray[1].toString());

        JSONArray jsonNumArray = JSONArray.fromObject(numberArray);

        assertEquals("18437736874454810627", jsonNumArray.get(0).toString());
        assertEquals("9007199254740990", jsonNumArray.get(1).toString());
    }

    @Test
    void testConstructor_Object_Array_Class() {
        JSONArray expected = JSONArray.fromObject("[\"java.lang.Object\"]");
        JSONArray actual = JSONArray.fromObject(new Object[] {Object.class});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_Object_Array_JSONArray() {
        JSONArray expected = JSONArray.fromObject("[[1,2]]");
        JSONArray actual = JSONArray.fromObject(new Object[] {JSONArray.fromObject("[1,2]")});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_Object_Array_JSONString() {
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        JSONArray expected = JSONArray.fromObject("[[\"json\",\"json\"]]");
        JSONArray actual = JSONArray.fromObject(new Object[] {bean});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_Object_Array_nulls() {
        JSONArray expected = JSONArray.fromObject("[null,null]");
        JSONArray actual = JSONArray.fromObject(new Object[] {null, null});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testConstructor_primitive_array_boolean() {
        testJSONArray(new boolean[] {true, false}, "[true,false]");
    }

    @Test
    void testConstructor_primitive_array_byte() {
        testJSONArray(new byte[] {1, 2, 3}, "[1,2,3]");
    }

    @Test
    void testConstructor_primitive_array_char() {
        testJSONArray(new char[] {'a', 'b', 'c'}, "[\"a\",\"b\",\"c\"]");
    }

    @Test
    void testConstructor_primitive_array_double() {
        testJSONArray(new double[] {1.1, 2.2, 3.3}, "[1.1,2.2,3.3]");
    }

    @Test
    void testConstructor_primitive_array_double_Infinity() {
        assertThrows(JSONException.class, () -> JSONArray.fromObject(new double[] {Double.NEGATIVE_INFINITY}));
        assertThrows(JSONException.class, () -> JSONArray.fromObject(new double[] {Double.POSITIVE_INFINITY}));
    }

    @Test
    void testConstructor_primitive_array_double_NaNs() {
        assertThrows(JSONException.class, () -> JSONArray.fromObject(new double[] {Double.NaN}));
    }

    @Test
    void testConstructor_primitive_array_float() {
        testJSONArray(new float[] {1.1f, 2.2f, 3.3f}, "[1.1,2.2,3.3]");
    }

    @Test
    void testConstructor_primitive_array_float_Infinity() {
        assertThrows(JSONException.class, () -> JSONArray.fromObject(new float[] {Float.NEGATIVE_INFINITY}));
        assertThrows(JSONException.class, () -> JSONArray.fromObject(new float[] {Float.POSITIVE_INFINITY}));
    }

    @Test
    void testConstructor_primitive_array_float_NaNs() {
        assertThrows(JSONException.class, () -> JSONArray.fromObject(new float[] {Float.NaN}));
    }

    @Test
    void testConstructor_primitive_array_int() {
        testJSONArray(new int[] {1, 2, 3}, "[1,2,3]");
    }

    @Test
    void testConstructor_primitive_array_long() {
        testJSONArray(new long[] {1, 2, 3}, "[1,2,3]");
    }

    @Test
    void testConstructor_primitive_array_short() {
        testJSONArray(new short[] {1, 2, 3}, "[1,2,3]");
    }

    @Test
    void testCycleDetection_arrays() {
        Object[] array1 = new Object[2];
        Object[] array2 = new Object[2];
        array1[0] = 1;
        array1[1] = array2;
        array2[0] = 2;
        array2[1] = array1;
        JSONException expected = assertThrows(JSONException.class, () -> JSONArray.fromObject(array1));
        assertTrue(expected.getMessage().endsWith("There is a cycle in the hierarchy!"));
    }

    @Test
    void testElement_Array() {
        JSONArray array = new JSONArray();
        int[] ints = {1, 2};
        array.element(ints);
        Assertions.assertEquals(JSONArray.fromObject(ints), array.getJSONArray(0));
    }

    @Test
    void testElement_boolean() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(true);
        assertEquals(1, jsonArray.size());
        assertTrue(jsonArray.getBoolean(0));
    }

    @Test
    void testElement_Boolean() {
        JSONArray array = new JSONArray();
        array.element(Boolean.TRUE);
        assertTrue(array.getBoolean(0));
    }

    @Test
    void testElement_Collection() {
        List<Object> l = new ArrayList<>();
        l.add(Boolean.TRUE);
        l.add(1);
        l.add("string");
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(l);
        assertEquals(1, jsonArray.size());
        Assertions.assertEquals(JSONArray.fromObject("[true,1,\"string\"]"), jsonArray.getJSONArray(0));
    }

    @Test
    void testElement_double() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(2.0d);
        assertEquals(1, jsonArray.size());
        assertEquals(2.0d, jsonArray.getDouble(0), 0d);
    }

    @Test
    void testElement_index_0_Array() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        int[] ints = {0, 2};
        array.element(0, ints);
        Assertions.assertEquals(JSONArray.fromObject(ints), array.getJSONArray(0));
    }

    @Test
    void testElement_index_0_Boolean() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, Boolean.TRUE);
        assertTrue(array.getBoolean(0));
    }

    @Test
    void testElement_index_0_Class() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, Object.class);
        assertEquals("java.lang.Object", array.getString(0));
    }

    @Test
    void testElement_index_0_JSON() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, JSONNull.getInstance());
        Assertions.assertEquals(JSONNull.getInstance(), array.get(0));
    }

    @Test
    void testElement_index_0_JSONString() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        array.element(0, bean);
        Assertions.assertEquals(JSONArray.fromObject(bean), array.getJSONArray(0));
    }

    @Test
    void testElement_index_0_JSONTokener() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        JSONTokener tok = new JSONTokener("[0,2]");
        array.element(0, tok);
        tok.reset();
        Assertions.assertEquals(JSONArray.fromObject(tok), array.getJSONArray(0));
    }

    @Test
    void testElement_index_0_Number() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, 2d);
        assertEquals(2d, array.getDouble(0), 0d);
    }

    @Test
    void testElement_index_0_Object() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, new BeanA());
        Assertions.assertEquals(JSONObject.fromObject(new BeanA()), array.getJSONObject(0));
    }

    @Test
    void testElement_index_0_String() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, "json");
        assertEquals("json", array.getString(0));
    }

    @Test
    void testElement_index_0_String_JSON() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, "[]");
        assertEquals(new JSONArray().toString(), array.getString(0));
    }

    @Test
    void testElement_index_0_String_null() {
        JSONArray array = JSONArray.fromObject("[null,null]");
        array.element(0, (String) null);
        assertEquals("", array.getString(0));
    }

    @Test
    void testElement_index_1_Array() {
        JSONArray array = new JSONArray();
        int[] ints = {1, 2};
        array.element(1, ints);
        Assertions.assertEquals(JSONArray.fromObject(ints), array.getJSONArray(1));
    }

    @Test
    void testElement_index_1_boolean() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, true);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertTrue(jsonArray.getBoolean(1));
    }

    @Test
    void testElement_index_1_Boolean() {
        JSONArray array = new JSONArray();
        array.element(1, Boolean.TRUE);
        assertTrue(array.getBoolean(1));
    }

    @Test
    void testElement_index_1_Class() {
        JSONArray array = new JSONArray();
        array.element(1, Object.class);
        assertEquals("java.lang.Object", array.getString(1));
    }

    @Test
    void testElement_index_1_Collection() {
        List<Object> l = new ArrayList<>();
        l.add(Boolean.TRUE);
        l.add(1);
        l.add("string");
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, l);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        Assertions.assertEquals(JSONArray.fromObject("[true,1,\"string\"]"), jsonArray.getJSONArray(1));
    }

    @Test
    void testElement_index_1_double() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, 2.0d);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertEquals(2.0d, jsonArray.getDouble(1), 0d);
    }

    @Test
    void testElement_index_1_int() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, 1);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertEquals(1, jsonArray.getInt(1));
    }

    @Test
    void testElement_index_1_JSON() {
        JSONArray array = new JSONArray();
        array.element(1, JSONNull.getInstance());
        Assertions.assertEquals(JSONNull.getInstance(), array.get(1));
    }

    @Test
    void testElement_index_1_JSONString() {
        JSONArray array = new JSONArray();
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        array.element(1, bean);
        Assertions.assertEquals(JSONArray.fromObject(bean), array.getJSONArray(1));
    }

    @Test
    void testElement_index_1_JSONTokener() {
        JSONArray array = new JSONArray();
        JSONTokener tok = new JSONTokener("[1,2]");
        array.element(1, tok);
        tok.reset();
        Assertions.assertEquals(JSONArray.fromObject(tok), array.getJSONArray(1));
    }

    @Test
    void testElement_index_1_long() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, 1L);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertEquals(1L, jsonArray.getLong(1));
    }

    @Test
    void testElement_index_1_Map() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, map);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        Assertions.assertEquals(JSONObject.fromObject(map), jsonArray.getJSONObject(1));
    }

    @Test
    void testElement_index_1_Number() {
        JSONArray array = new JSONArray();
        array.element(1, 2d);
        assertEquals(2d, array.getDouble(1), 1d);
    }

    @Test
    void testElement_index_1_Object() {
        JSONArray array = new JSONArray();
        array.element(1, new BeanA());
        Assertions.assertEquals(JSONObject.fromObject(new BeanA()), array.getJSONObject(1));
    }

    @Test
    void testElement_index_1_String() {
        JSONArray array = new JSONArray();
        array.element(1, "json");
        assertEquals("json", array.getString(1));
    }

    @Test
    void testElement_index_1_String_JSON() {
        JSONArray array = new JSONArray();
        array.element(1, "[]");
        assertEquals(new JSONArray().toString(), array.getString(1));
    }

    @Test
    void testElement_index_1_String_null() {
        JSONArray array = new JSONArray();
        array.element(1, (String) null);
        assertEquals("", array.getString(1));
    }

    @Test
    void testElement_int() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1);
        assertEquals(1, jsonArray.size());
        assertEquals(1, jsonArray.getInt(0));
    }

    @Test
    void testElement_JSON() {
        JSONArray array = new JSONArray();
        array.element(JSONNull.getInstance());
        Assertions.assertEquals(JSONNull.getInstance(), array.get(0));
    }

    @Test
    void testElement_JSONString() {
        JSONArray array = new JSONArray();
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        array.element(bean);
        Assertions.assertEquals(JSONArray.fromObject(bean), array.getJSONArray(0));
    }

    @Test
    void testElement_JSONTokener() {
        JSONArray array = new JSONArray();
        JSONTokener tok = new JSONTokener("[1,2]");
        array.element(tok);
        tok.reset();
        Assertions.assertEquals(JSONArray.fromObject(tok), array.getJSONArray(0));
    }

    @Test
    void testElement_long() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1L);
        assertEquals(1, jsonArray.size());
        assertEquals(1L, jsonArray.getLong(0));
    }

    @Test
    void testElement_Map() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "json");
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(map);
        assertEquals(1, jsonArray.size());
        Assertions.assertEquals(JSONObject.fromObject(map), jsonArray.getJSONObject(0));
    }

    @Test
    void testElement_negativeIndex() {
        JSONArray jsonArray = new JSONArray();
        assertThrows(JSONException.class, () -> jsonArray.element(-1, JSONNull.getInstance()));
    }

    @Test
    void testElement_Number() {
        JSONArray array = new JSONArray();
        array.element(2d);
        assertEquals(2d, array.getDouble(0), 0d);
    }

    @Test
    void testElement_Object() {
        JSONArray array = new JSONArray();
        array.element(new BeanA());
        Assertions.assertEquals(JSONObject.fromObject(new BeanA()), array.getJSONObject(0));
    }

    @Test
    void testElement_replace() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(true);
        assertEquals(1, jsonArray.size());
        assertTrue(jsonArray.getBoolean(0));
        jsonArray.element(0, false);
        assertEquals(1, jsonArray.size());
        assertFalse(jsonArray.getBoolean(0));
    }

    @Test
    void testElement_String() {
        JSONArray array = new JSONArray();
        array.element("json");
        assertEquals("json", array.getString(0));
    }

    @Test
    void testElement_String_JSON() {
        JSONArray array = new JSONArray();
        array.element("[]");
        assertEquals(new JSONArray().toString(), array.getString(0));
    }

    @Test
    void testElement_String_null() {
        JSONArray array = new JSONArray();
        array.element((String) null);
        assertEquals("", array.getString(0));
    }

    @Test
    void testFromObject_BigDecimal() {
        JSONArray actual = JSONArray.fromObject(new BigDecimal("12345678901234567890.1234567890"));
        assertInstanceOf(BigDecimal.class, actual.get(0));
    }

    @Test
    void testFromObject_BigInteger() {
        JSONArray actual = JSONArray.fromObject(new BigInteger("123456789012345678901234567890"));
        assertInstanceOf(BigInteger.class, actual.get(0));
    }

    @Test
    void testFromObject_Boolean() {
        JSONArray expected = JSONArray.fromObject("[true]");
        JSONArray actual = JSONArray.fromObject(Boolean.TRUE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Byte() {
        JSONArray expected = JSONArray.fromObject("[1]");
        JSONArray actual = JSONArray.fromObject((byte) 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Double() {
        JSONArray expected = JSONArray.fromObject("[1.0]");
        JSONArray actual = JSONArray.fromObject(1d);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Float() {
        JSONArray expected = JSONArray.fromObject("[1.0]");
        JSONArray actual = JSONArray.fromObject(1f);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Integer() {
        JSONArray expected = JSONArray.fromObject("[1]");
        JSONArray actual = JSONArray.fromObject(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_JSONArray() {
        JSONArray expected = JSONArray.fromObject("[1,2]");
        JSONArray actual = JSONArray.fromObject(JSONArray.fromObject("[1,2]"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_JSONString() {
        ArrayJSONStringBean bean = new ArrayJSONStringBean();
        bean.setValue("'json','json'");
        JSONArray actual = JSONArray.fromObject(bean);
        JSONArray expected = JSONArray.fromObject("['json','json']");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Long() {
        JSONArray expected = JSONArray.fromObject("[1]");
        JSONArray actual = JSONArray.fromObject(1L);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Map() {
        JSONArray expected = JSONArray.fromObject("[{}]");
        JSONArray actual = JSONArray.fromObject(new HashMap<>());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Short() {
        JSONArray expected = JSONArray.fromObject("[1]");
        JSONArray actual = JSONArray.fromObject((short) 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGet_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[]");
        assertThrows(IndexOutOfBoundsException.class, () -> jsonArray.get(0));
    }

    @Test
    void testGetBoolean_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[[]]");
        assertThrows(JSONException.class, () -> jsonArray.getBoolean(0));
    }

    @Test
    void testGetBoolean_false() {
        JSONArray jsonArray = JSONArray.fromObject("[false]");
        assertFalse(jsonArray.getBoolean(0));
    }

    @Test
    void testGetBoolean_true() {
        JSONArray jsonArray = JSONArray.fromObject("[true]");
        assertTrue(jsonArray.getBoolean(0));
    }

    @Test
    void testGetDimensions_empty_array() {
        int[] dims = JSONArray.getDimensions(new JSONArray());
        assertEquals(1, dims.length);
        assertEquals(0, dims[0]);
    }

    @Test
    void testGetDimensions_null_array() {
        int[] dims = JSONArray.getDimensions(null);
        assertEquals(1, dims.length);
        assertEquals(0, dims[0]);
    }

    @Test
    void testGetDimensions_one_dimension() {
        int[] dims = JSONArray.getDimensions(JSONArray.fromObject("[1,2,3]"));
        assertEquals(1, dims.length);
        assertEquals(3, dims[0]);
    }

    @Test
    void testGetDimensions_pyramid() {
        int[] dims = JSONArray.getDimensions(JSONArray.fromObject("[1,[2,[3,[4]]]]"));
        assertEquals(4, dims.length);
        assertEquals(2, dims[0]);
        assertEquals(2, dims[1]);
        assertEquals(2, dims[2]);
        assertEquals(1, dims[3]);

        dims = JSONArray.getDimensions(JSONArray.fromObject("[[[[1],2],3],4]"));
        assertEquals(4, dims.length);
        assertEquals(2, dims[0]);
        assertEquals(2, dims[1]);
        assertEquals(2, dims[2]);
        assertEquals(1, dims[3]);
    }

    @Test
    void testGetDimensions_two_dimension() {
        int[] dims = JSONArray.getDimensions(JSONArray.fromObject("[[1,2,3],[4,5,6]]"));
        assertEquals(2, dims.length);
        assertEquals(2, dims[0]);
        assertEquals(3, dims[1]);

        dims = JSONArray.getDimensions(JSONArray.fromObject("[[1,2],[4,5,6]]"));
        assertEquals(2, dims.length);
        assertEquals(2, dims[0]);
        assertEquals(3, dims[1]);

        dims = JSONArray.getDimensions(JSONArray.fromObject("[[1,2,3],[4,5]]"));
        assertEquals(2, dims.length);
        assertEquals(2, dims[0]);
        assertEquals(3, dims[1]);
    }

    @Test
    void testGetDouble_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[[]]");
        assertThrows(JSONException.class, () -> jsonArray.getDouble(0));
    }

    @Test
    void testGetDouble_Number() {
        JSONArray jsonArray = JSONArray.fromObject("[2.0]");
        assertEquals(2.0d, jsonArray.getDouble(0), 0d);
    }

    @Test
    void testGetDouble_String() {
        JSONArray jsonArray = JSONArray.fromObject("[\"2.0\"]");
        assertEquals(2.0d, jsonArray.getDouble(0), 0d);
    }

    @Test
    void testGetInt_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[[]]");
        assertThrows(JSONException.class, () -> jsonArray.getInt(0));
    }

    @Test
    void testGetInt_Number() {
        JSONArray jsonArray = JSONArray.fromObject("[2.0]");
        assertEquals(2, jsonArray.getInt(0));
    }

    @Test
    void testGetInt_String() {
        JSONArray jsonArray = JSONArray.fromObject("[\"2.0\"]");
        assertEquals(2, jsonArray.getInt(0));
    }

    @Test
    void testGetJSONArray() {
        JSONArray jsonArray = JSONArray.fromObject("[[1,2]]");
        assertEquals(
                JSONArray.fromObject("[1,2]").toString(),
                jsonArray.getJSONArray(0).toString());
    }

    @Test
    void testGetJSONArray_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[1]");
        assertThrows(JSONException.class, () -> jsonArray.getJSONArray(0));
    }

    @Test
    void testGetJSONObject() {
        JSONArray jsonArray = JSONArray.fromObject("[{\"name\":\"json\"}]");
        Assertions.assertEquals(JSONObject.fromObject("{\"name\":\"json\"}"), jsonArray.getJSONObject(0));
    }

    @Test
    void testGetJSONObject_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[1]");
        assertThrows(JSONException.class, () -> jsonArray.getJSONObject(0));
    }

    @Test
    void testGetLong_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[[]]");
        assertThrows(JSONException.class, () -> jsonArray.getLong(0));
    }

    @Test
    void testGetLong_Number() {
        JSONArray jsonArray = JSONArray.fromObject("[2.0]");
        assertEquals(2, jsonArray.getLong(0));
    }

    @Test
    void testGetLong_String() {
        JSONArray jsonArray = JSONArray.fromObject("[\"2.0\"]");
        assertEquals(2, jsonArray.getLong(0));
    }

    @Test
    void testGetString() {
        JSONArray jsonArray = JSONArray.fromObject("[\"2.0\"]");
        assertEquals("2.0", jsonArray.getString(0));
    }

    @Test
    void testGetString_exception() {
        JSONArray jsonArray = JSONArray.fromObject("[]");
        assertThrows(IndexOutOfBoundsException.class, () -> jsonArray.getString(0));
    }

    @Test
    void testOptionalGet() {
        Object[] params = new Object[] {new JSONArray(), JSONObject.fromObject("{\"int\":1}")};
        JSONArray jsonArray = JSONArray.fromObject(params);
        assertFalse(jsonArray.optBoolean(0));
        assertTrue(jsonArray.optBoolean(0, true));
        assertTrue(Double.isNaN(jsonArray.optDouble(0)));
        assertEquals(0d, jsonArray.optDouble(0, 0d), 0d);
        assertEquals(0, jsonArray.optInt(0));
        assertEquals(1, jsonArray.optInt(0, 1));
        assertNull(jsonArray.optJSONArray(1));
        Assertions.assertEquals((JSONArray) params[0], jsonArray.optJSONArray(0));
        assertNull(jsonArray.optJSONObject(0));
        Assertions.assertEquals((JSONObject) params[1], jsonArray.optJSONObject(1));
        assertEquals(0, jsonArray.optLong(0));
        assertEquals(1, jsonArray.optLong(0, 1));
        assertEquals("", jsonArray.optString(3));
        assertEquals("json", jsonArray.optString(3, "json"));
    }

    @Test
    void testToArray_bean_element() {
        BeanA[] expected = new BeanA[] {new BeanA()};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray, BeanA.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_BigDecimal() {
        BigDecimal[] expected = new BigDecimal[] {MorphUtils.BIGDECIMAL_ZERO, MorphUtils.BIGDECIMAL_ONE};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_BigInteger() {
        BigInteger[] expected = new BigInteger[] {BigInteger.ZERO, BigInteger.ONE};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_boolean() {
        boolean[] expected = new boolean[] {true, false};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Boolean() {
        Boolean[] expected = new Boolean[] {Boolean.TRUE, Boolean.FALSE};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_boolean_multi() {
        boolean[][] expected = new boolean[][] {{true, false}, {false, true}};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_byte() {
        byte[] input = new byte[] {1, 2, 3, 4, 5, 6};
        int[] expected = new int[] {1, 2, 3, 4, 5, 6};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Byte() {
        Integer[] expected = new Integer[] {1, 2};
        Byte[] bytes = new Byte[] {(byte) 1, (byte) 2};
        JSONArray jsonArray = JSONArray.fromObject(bytes);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_byte_multi() {
        byte[][] input = new byte[][] {{1, 2, 3}, {4, 5, 6}};
        int[][] expected = new int[][] {{1, 2, 3}, {4, 5, 6}};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_char() {
        String[] expected = new String[] {"A", "B"};
        char[] input = new char[] {'A', 'B'};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_char_multi() {
        String[][] expected = new String[][] {{"a", "b"}, {"c", "d"}};
        char[][] input = new char[][] {{'a', 'b'}, {'c', 'd'}};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Character() {
        String[] expected = {"A", "B"};
        Character[] chars = new Character[] {'A', 'B'};
        JSONArray jsonArray = JSONArray.fromObject(chars);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_double() {
        double[] expected = new double[] {1, 2, 3, 4, 5, 6};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Double() {
        Double[] expected = new Double[] {1d, 2d};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_double_multi() {
        double[][] expected = new double[][] {{1, 2, 3}, {4, 5, 6}};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_dynabean_element() throws Exception {
        DynaBean[] expected = new DynaBean[] {createDynaBean()};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_float() {
        double[] expected = new double[] {1, 2, 3, 4, 5, 6};
        float[] input = new float[] {1, 2, 3, 4, 5, 6};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Float() {
        Double[] expected = new Double[] {1d, 2d};
        Float[] floats = new Float[] {1f, 2f};
        JSONArray jsonArray = JSONArray.fromObject(floats);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_float_multi() {
        double[][] expected = new double[][] {{1, 2, 3}, {4, 5, 6}};
        float[][] input = new float[][] {{1, 2, 3}, {4, 5, 6}};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_int() {
        int[] expected = new int[] {1, 2, 3, 4, 5, 6};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_int_multi() {
        int[][] expected = new int[][] {{1, 2, 3}, {4, 5, 6}};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Integer() {
        Integer[] expected = new Integer[] {1, 2};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_long() {
        long[] input = new long[] {1, 2, 3, 4, 5, 6};
        int[] expected = new int[] {1, 2, 3, 4, 5, 6};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Long() {
        Integer[] expected = new Integer[] {1, 2};
        Long[] longs = new Long[] {1L, 2L};
        JSONArray jsonArray = JSONArray.fromObject(longs);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_long_multi() {
        long[][] input = new long[][] {{1, 2, 3}, {4, 5, 6}};
        int[][] expected = new int[][] {{1, 2, 3}, {4, 5, 6}};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Long2() {
        Long[] expected = new Long[] {Integer.MAX_VALUE + 1L, Integer.MAX_VALUE + 2L};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_null_elements() {
        String[] expected = new String[] {null, null, null};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_short() {
        short[] input = new short[] {1, 2, 3, 4, 5, 6};
        int[] expected = new int[] {1, 2, 3, 4, 5, 6};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_Short() {
        Integer[] expected = new Integer[] {1, 2};
        Short[] shorts = new Short[] {(short) 1, (short) 2};
        JSONArray jsonArray = JSONArray.fromObject(shorts);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_short_multi() {
        short[][] input = new short[][] {{1, 2, 3}, {4, 5, 6}};
        int[][] expected = new int[][] {{1, 2, 3}, {4, 5, 6}};
        JSONArray jsonArray = JSONArray.fromObject(input);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_String() {
        String[] expected = new String[] {"1", "2", "3", "4", "5", "6"};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_String_multi() {
        String[][] expected = new String[][] {{"1", "2", "3"}, {"4", "5", "6"}};
        JSONArray jsonArray = JSONArray.fromObject(expected);
        Object actual = JSONArray.toArray(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_StringToInt() {
        int[] expected = new int[] {1, 2, 3, 4, 5, 6};
        String[] input = new String[] {"1", "2", "3", "4", "5", "6"};
        JSONArray jsonArray = JSONArray.fromObject(input);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(Integer.TYPE);
        Object actual = JSONArray.toArray(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_StringToInteger() {
        int[] expected = new int[] {1, 2, 3, 4, 5, 6};
        String[] input = new String[] {"1", "2", "3", "4", "5", "6"};
        JSONArray jsonArray = JSONArray.fromObject(input);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(Integer.class);
        Object actual = JSONArray.toArray(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToArray_StringToInteger_empty() {
        int[] expected = new int[] {};
        String[] input = new String[] {};
        JSONArray jsonArray = JSONArray.fromObject(input);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(Integer.class);
        Object actual = JSONArray.toArray(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToJSONObject() {
        JSONArray jsonArray = JSONArray.fromObject("[\"json\",1,true]");
        JSONObject expected = JSONObject.fromObject("{\"string\":\"json\",\"int\":1,\"bool\":true}");
        JSONArray names = JSONArray.fromObject("['string','int','bool']");

        Assertions.assertEquals(expected, jsonArray.toJSONObject(names));
    }

    @Test
    void testToJSONObject_null_object() {
        JSONArray jsonArray = new JSONArray();
        assertNull(jsonArray.toJSONObject(null));
        assertNull(jsonArray.toJSONObject(new JSONArray()));
        assertNull(jsonArray.toJSONObject(JSONArray.fromObject("['json']")));
    }

    @Test
    void testToList_bean_elements() {
        List<Object> expected = new ArrayList<>();
        expected.add(new BeanA());
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray, BeanA.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_BigDecimal() {
        List<Object> expected = new ArrayList<>();
        expected.add(MorphUtils.BIGDECIMAL_ZERO);
        expected.add(MorphUtils.BIGDECIMAL_ONE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_BigInteger() {
        List<Object> expected = new ArrayList<>();
        expected.add(BigInteger.ZERO);
        expected.add(BigInteger.ONE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Boolean() {
        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Byte() {
        List<Object> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 1);
        bytes.add((byte) 2);
        JSONArray jsonArray = JSONArray.fromObject(bytes);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Character() {
        List<Object> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        List<Character> chars = new ArrayList<>();
        chars.add('A');
        chars.add('B');
        JSONArray jsonArray = JSONArray.fromObject(chars);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Double() {
        List<Object> expected = new ArrayList<>();
        expected.add(1d);
        expected.add(2d);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_dynaBean_elements() throws Exception {
        List<Object> expected = new ArrayList<>();
        expected.add(createDynaBean());
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Float() {
        List<Object> expected = new ArrayList<>();
        expected.add(1d);
        expected.add(2d);
        List<Float> floats = new ArrayList<>();
        floats.add(1f);
        floats.add(2f);
        JSONArray jsonArray = JSONArray.fromObject(floats);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Integer() {
        List<Object> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Long() {
        List<Object> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        JSONArray jsonArray = JSONArray.fromObject(longs);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Long2() {
        List<Object> expected = new ArrayList<>();
        expected.add(Integer.MAX_VALUE + 1L);
        expected.add(Integer.MAX_VALUE + 2L);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_null_elements() {
        List<Object> expected = new ArrayList<>();
        expected.add(null);
        expected.add(null);
        expected.add(null);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Short() {
        List<Object> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Short> shorts = new ArrayList<>();
        shorts.add((short) 1);
        shorts.add((short) 2);
        JSONArray jsonArray = JSONArray.fromObject(shorts);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_String() {
        List<Object> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_String_multi() {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        List<String> b = new ArrayList<>();
        b.add("1");
        b.add("2");
        List<Object> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = JSONArray.toList(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testWrite() throws IOException {
        JSONArray jsonArray = JSONArray.fromObject("[[],{},1,true,\"json\"]");
        StringWriter sw = new StringWriter();
        jsonArray.write(sw);
        assertEquals("[[],{},1,true,\"json\"]", sw.toString());
    }

    private MorphDynaBean createDynaBean() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("name", String.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("name", "json");
        // JSON Strings can not be null, only empty
        return dynaBean;
    }

    private void testJSONArray(Object array, String expected) {
        JSONArray jsonArray = JSONArray.fromObject(array);
        assertEquals(expected, jsonArray.toString());
    }

    @Test
    void testMayBeJSON() {
        assertEquals("[foo]", JSONArray.fromObject("[\"[foo]\"]").getString(0));
    }
}

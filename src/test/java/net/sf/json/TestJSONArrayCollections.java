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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import net.sf.json.sample.BeanA;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArrayCollections {

    @Test
    void testToList_bean_elements() {
        List<BeanA> expected = new ArrayList<>();
        expected.add(new BeanA());
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray, BeanA.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_BigDecimal() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(MorphUtils.BIGDECIMAL_ZERO);
        expected.add(MorphUtils.BIGDECIMAL_ONE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_BigInteger() {
        List<BigInteger> expected = new ArrayList<>();
        expected.add(BigInteger.ZERO);
        expected.add(BigInteger.ONE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Boolean() {
        List<Boolean> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Byte() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 1);
        bytes.add((byte) 2);
        JSONArray jsonArray = JSONArray.fromObject(bytes);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Character() {
        List<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        List<Character> chars = new ArrayList<>();
        chars.add('A');
        chars.add('B');
        JSONArray jsonArray = JSONArray.fromObject(chars);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Double() {
        List<Double> expected = new ArrayList<>();
        expected.add(1d);
        expected.add(2d);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_dynaBean_elements() throws Exception {
        List<MorphDynaBean> expected = new ArrayList<>();
        expected.add(createDynaBean());
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Float() {
        List<Double> expected = new ArrayList<>();
        expected.add(1d);
        expected.add(2d);
        List<Float> floats = new ArrayList<>();
        floats.add(1f);
        floats.add(2f);
        JSONArray jsonArray = JSONArray.fromObject(floats);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Integer() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Long() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        JSONArray jsonArray = JSONArray.fromObject(longs);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Long2() {
        List<Long> expected = new ArrayList<>();
        expected.add(Integer.MAX_VALUE + 1L);
        expected.add(Integer.MAX_VALUE + 2L);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_null_elements() {
        List<Object> expected = new ArrayList<>();
        expected.add(null);
        expected.add(null);
        expected.add(null);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_Short() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Short> shorts = new ArrayList<>();
        shorts.add((short) 1);
        shorts.add((short) 2);
        JSONArray jsonArray = JSONArray.fromObject(shorts);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToList_String() {
        List<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
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
        List<List<String>> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        List<?> actual = (List<?>) JSONArray.toCollection(jsonArray);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_bean_elements() {
        Set<BeanA> expected = new HashSet<>();
        expected.add(new BeanA());
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        jsonConfig.setRootClass(BeanA.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_BigDecimal() {
        Set<BigDecimal> expected = new HashSet<>();
        expected.add(MorphUtils.BIGDECIMAL_ZERO);
        expected.add(MorphUtils.BIGDECIMAL_ONE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_BigInteger() {
        Set<BigInteger> expected = new HashSet<>();
        expected.add(BigInteger.ZERO);
        expected.add(BigInteger.ONE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Boolean() {
        Set<Boolean> expected = new HashSet<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Byte() {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        Set<Byte> bytes = new HashSet<>();
        bytes.add((byte) 1);
        bytes.add((byte) 2);
        JSONArray jsonArray = JSONArray.fromObject(bytes);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Character() {
        Set<String> expected = new HashSet<>();
        expected.add("A");
        expected.add("B");
        Set<Character> chars = new HashSet<>();
        chars.add('A');
        chars.add('B');
        JSONArray jsonArray = JSONArray.fromObject(chars);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Double() {
        Set<Double> expected = new HashSet<>();
        expected.add(1d);
        expected.add(2d);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_dynaBean_elements() throws Exception {
        Set<MorphDynaBean> expected = new HashSet<>();
        expected.add(createDynaBean());
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Float() {
        Set<Double> expected = new HashSet<>();
        expected.add(1d);
        expected.add(2d);
        Set<Float> floats = new HashSet<>();
        floats.add(1f);
        floats.add(2f);
        JSONArray jsonArray = JSONArray.fromObject(floats);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Integer() {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Long() {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        Set<Long> longs = new HashSet<>();
        longs.add(1L);
        longs.add(2L);
        JSONArray jsonArray = JSONArray.fromObject(longs);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Long2() {
        Set<Long> expected = new HashSet<>();
        expected.add(Integer.MAX_VALUE + 1L);
        expected.add(Integer.MAX_VALUE + 2L);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_null_elements() {
        Set<Object> expected = new HashSet<>();
        expected.add(null);
        expected.add(null);
        expected.add(null);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_Short() {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        Set<Short> shorts = new HashSet<>();
        shorts.add((short) 1);
        shorts.add((short) 2);
        JSONArray jsonArray = JSONArray.fromObject(shorts);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_String() {
        Set<String> expected = new HashSet<>();
        expected.add("A");
        expected.add("B");
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToSet_String_multi() {
        Set<String> a = new HashSet<>();
        a.add("a");
        a.add("b");
        Set<String> b = new HashSet<>();
        b.add("1");
        b.add("2");
        Set<Set<String>> expected = new HashSet<>();
        expected.add(a);
        expected.add(b);
        JSONArray jsonArray = JSONArray.fromObject(expected);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCollectionType(Set.class);
        Set<?> actual = (Set<?>) JSONArray.toCollection(jsonArray, jsonConfig);
        Assertions.assertEquals(expected, actual);
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
}

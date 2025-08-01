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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.sample.AnnotationBean;
import net.sf.json.sample.JsonEnum;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArrayJdk15 {

    @Test
    void testConstructor_Annotation() {
        AnnotationBean bean = new AnnotationBean();
        Annotation[] annotations = bean.getClass().getAnnotations();
        assertThrows(JSONException.class, () -> JSONArray.fromObject(annotations[0]));
    }

    @Test
    void testConstructor_Collection() {
        List<Object> l = new ArrayList<>();
        l.add(Boolean.TRUE);
        l.add(1);
        l.add("string");
        l.add(Object.class);
        l.add(JsonEnum.ARRAY);
        testJSONArray(l, "[true,1,\"string\",\"java.lang.Object\",\"ARRAY\"]");
    }

    @Test
    void testConstructor_Enum() {
        testJSONArray(JsonEnum.ARRAY, "[\"ARRAY\"]");
    }

    @Test
    void testConstructor_Object_Array_Enum() {
        JSONArray expected = JSONArray.fromObject("[\"ARRAY\",\"OBJECT\"]");
        JSONArray actual = JSONArray.fromObject(new JsonEnum[] {JsonEnum.ARRAY, JsonEnum.OBJECT});
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObject_Enum() {
        JSONArray actual = JSONArray.fromObject(JsonEnum.ARRAY);
        JSONArray expected = new JSONArray().element("ARRAY");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPut_Enum() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(JsonEnum.ARRAY);
        assertEquals(1, jsonArray.size());
        assertEquals("ARRAY", jsonArray.get(0));
    }

    @Test
    void testPut_Enum_2() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element((JsonEnum) null);
        assertEquals(1, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
    }

    @Test
    void testPut_index_0_Annotation() {
        AnnotationBean bean = new AnnotationBean();
        Annotation[] annotations = bean.getClass().getAnnotations();
        JSONArray array = JSONArray.fromObject("[null,null]");
        assertThrows(JSONException.class, () -> array.element(0, annotations[0]));
    }

    @Test
    void testPut_index_0_Enum() {
        JSONArray jsonArray = JSONArray.fromObject("[null,null]");
        jsonArray.element(0, JsonEnum.ARRAY);
        assertEquals("ARRAY", jsonArray.get(0));
        assertEquals(JSONNull.getInstance(), jsonArray.get(1));
    }

    @Test
    void testPut_index_0_Enum_2() {
        JSONArray jsonArray = JSONArray.fromObject("[null,null]");
        jsonArray.element(0, (JsonEnum) null);
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertEquals(JSONNull.getInstance(), jsonArray.get(1));
    }

    @Test
    void testPut_index_1_Enum() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, JsonEnum.ARRAY);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertEquals("ARRAY", jsonArray.get(1));
    }

    @Test
    void testPut_index_1_Enum_2() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.element(1, (JsonEnum) null);
        assertEquals(2, jsonArray.size());
        assertEquals(JSONNull.getInstance(), jsonArray.get(0));
        assertEquals(JSONNull.getInstance(), jsonArray.get(1));
    }

    private void testJSONArray(Object array, String expected) {
        JSONArray jsonArray = JSONArray.fromObject(array);
        assertEquals(expected, jsonArray.toString());
    }
}

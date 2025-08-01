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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONObjectEqualsHashCodeCompareTo {
    private static final JSONObject strings;
    private static final Map<String, Object> values = new HashMap<>();
    private static final JSONObject values1;
    private static final JSONObject values2;
    private static final JSONObject values3;

    static {
        values.put("JSONObject.null.1", new JSONObject(true));
        values.put("JSONObject.null.2", new JSONObject(true));
        values.put("int.1", Integer.valueOf("1"));
        values.put("int.2", Integer.valueOf("2"));
        values.put("long.1", Long.valueOf("1"));
        values.put("long.2", Long.valueOf("2"));
        values.put("string.1", "1");
        values.put("string.2", "2");
        values.put("boolean.1", Boolean.TRUE);
        values.put("boolean.2", Boolean.FALSE);

        strings = new JSONObject()
                .element("int", "1")
                .element("long", "1")
                .element("boolean", "true")
                .element("string", "string")
                .element("array", JSONArray.fromObject("[1,2,3]"));
        values.put("JSONObject.strings", strings);
        values1 = new JSONObject()
                .element("int", Integer.valueOf("1"))
                .element("long", Long.valueOf("1"))
                .element("boolean", Boolean.TRUE)
                .element("string", "string")
                .element("array", JSONArray.fromObject(new int[] {1, 2, 3}));
        values.put("JSONObject.values.1", values1);
        values2 = new JSONObject()
                .element("int", Integer.valueOf("1"))
                .element("long", Long.valueOf("1"))
                .element("boolean", Boolean.TRUE)
                .element("string", "string");
        values.put("JSONObject.values.2", values2);
        values3 = new JSONObject()
                .element("int", Integer.valueOf("2"))
                .element("long", Long.valueOf("2"))
                .element("boolean", Boolean.FALSE)
                .element("string", "string2");
        values.put("JSONObject.values.3", values3);
    }

    @Test
    void testCompareTo_different_size() {
        assertEquals(-1, values2.compareTo(strings));
        assertEquals(1, strings.compareTo(values2));
    }

    @Test
    void testCompareTo_null() {
        assertEquals(-1, strings.compareTo(null));
    }

    @Test
    void testCompareTo_object() {
        assertEquals(-1, strings.compareTo(new Object()));
    }

    @Test
    void testCompareTo_same_array() {
        assertEquals(0, strings.compareTo(strings));
    }

    @Test
    void testCompareTo_same_size_different_values() {
        assertEquals(-1, values2.compareTo(values3));
    }

    @Test
    void testCompareTo_same_size_similar_values() {
        assertEquals(0, strings.compareTo(values1));
    }

    @Test
    void testEquals_different_key_same_size() {
        JSONObject a = new JSONObject().element("key1", "string");
        JSONObject b = new JSONObject().element("key2", "json");
        assertNotEquals(a, b);
        assertNotEquals(b, a);
    }

    @Test
    void testEquals_different_sizes() {
        assertNotEquals(values.get("JSONObject.values.1"), values.get("JSONObject.values.2"));
    }

    @Test
    void testEquals_nullObject_other() {
        assertNotEquals(values.get("JSONObject.null.1"), values.get("JSONObject.strings"));
    }

    @Test
    void testEquals_nullObjects_different() {
        assertEquals(values.get("JSONObject.null.1"), values.get("JSONObject.null.2"));
    }

    @Test
    void testEquals_other_nullObject() {
        assertNotEquals(values.get("JSONObject.strings"), values.get("JSONObject.null.1"));
    }

    @Test
    void testEquals_same() {
        assertEquals(values.get("JSONObject.null.1"), values.get("JSONObject.null.1"));
    }

    @Test
    void testEquals_same_key_different_value() {
        JSONObject a = new JSONObject().element("key", "string");
        JSONObject b = new JSONObject().element("key", "json");
        assertNotEquals(a, b);
        assertNotEquals(b, a);
    }

    @Test
    void testEquals_strings_values() {
        assertEquals(values.get("JSONObject.strings"), values.get("JSONObject.values.1"));
    }

    @Test
    void testEquals_to_null() {
        assertNotEquals(null, values.get("JSONObject.null.1"));
    }

    @Test
    void testEquals_to_other() {
        assertNotEquals(values.get("JSONObject.null.1"), new Object());
    }

    @Test
    void testEquals_values_strings() {
        assertEquals(values.get("JSONObject.values.1"), values.get("JSONObject.strings"));
    }

    @Test
    void testHashCode_different_size() {
        assertNotEquals(
                values.get("JSONObject.values.1").hashCode(),
                values.get("JSONObject.values.2").hashCode());
    }

    @Test
    void testHashCode_nullObject_other() {
        assertNotEquals(
                values.get("JSONObject.null.1").hashCode(),
                values.get("JSONObject.strings").hashCode());
    }

    @Test
    void testHashCode_nullObjects_different() {
        assertEquals(
                values.get("JSONObject.null.1").hashCode(),
                values.get("JSONObject.null.2").hashCode());
    }

    @Test
    void testHashCode_other_nullObject() {
        assertNotEquals(
                values.get("JSONObject.strings").hashCode(),
                values.get("JSONObject.null.1").hashCode());
    }

    @Test
    void testHashCode_same() {
        assertEquals(
                values.get("JSONObject.null.1").hashCode(),
                values.get("JSONObject.null.1").hashCode());
    }

    @Test
    void testHashCode_same_key_different_value() {
        JSONObject a = new JSONObject().element("key", "string");
        JSONObject b = new JSONObject().element("key", "json");
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testHashCode_strings_values() {
        assertEquals(
                values.get("JSONObject.strings").hashCode(),
                values.get("JSONObject.values.1").hashCode());
    }

    @Test
    void testHashCode_to_other() {
        assertNotEquals(values.get("JSONObject.null.1").hashCode(), new Object().hashCode());
    }
}

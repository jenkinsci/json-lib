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

package net.sf.json.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import junit.framework.AssertionFailedError;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONAssert {

    @Test
    void testArrayWithNullsShouldFail() {
        JSONArray one = new JSONArray();
        one.element("hello");
        one.element((Object) null);
        one.element("world");

        JSONArray two = new JSONArray();
        two.element("hello");
        two.element((Object) null);
        two.element("world!");

        AssertionFailedError e = assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(one, two));
        assertEquals("arrays first differed at element [2]; expected:<world[]> but was:<world[!]>", e.getMessage());
    }

    @Test
    void testArrayWithNullsShouldPass() {
        JSONArray one = new JSONArray();
        one.element("hello");
        one.element((Object) null);
        one.element("world");

        JSONArray two = new JSONArray();
        two.element("hello");
        two.element((Object) null);
        two.element("world");

        JSONAssert.assertEquals(one, two);
    }

    @Test
    void testAssertEquals_JSON_JSON__actual_null() {
        JSON expected = JSONArray.fromObject("[1,2,3]");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, null));
        assertEquals("actual was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSON_JSON__expected_null() {
        JSON actual = JSONObject.fromObject("{\"str\":\"json\"}");
        AssertionFailedError e = assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(null, actual));
        assertEquals("expected was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSON_JSON__JSONArray_JSONArray() {
        JSON expected = JSONArray.fromObject("[1,2,3]");
        JSON actual = JSONArray.fromObject("[1,2,3]");
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Arrays should be equal");
    }

    @Test
    void testAssertEquals_JSON_JSON__JSONArray_JSONObject() {
        JSON expected = JSONArray.fromObject("[1,2,3]");
        JSON actual = JSONObject.fromObject("{\"str\":\"json\"}");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is not a JSONArray", e.getMessage());
    }

    @Test
    void testAssertEquals_JSON_JSON__JSONNull_JSONArray() {
        JSON expected = JSONNull.getInstance();
        JSON actual = JSONArray.fromObject("[1,2,3]");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is not a JSONNull", e.getMessage());
    }

    @Test
    void testAssertEquals_JSON_JSON__JSONNull_JSONNull() {
        JSON expected = JSONNull.getInstance();
        JSON actual = JSONNull.getInstance();

        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "JSONNull should be equal to itself");
    }

    @Test
    void testAssertEquals_JSON_JSON__JSONObject_JSONArray() {
        JSON expected = JSONObject.fromObject("{\"str\":\"json\"}");
        JSON actual = JSONArray.fromObject("[1,2,3]");

        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is not a JSONObject", e.getMessage());
    }

    @Test
    void testAssertEquals_JSON_JSON__JSONObject_JSONObject() {
        JSON expected = JSONObject.fromObject("{\"str\":\"json\"}");
        JSON actual = JSONObject.fromObject("{\"str\":\"json\"}");
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Objects should be equal");
    }

    @Test
    void testAssertEquals_JSONArray_JSONArray() {
        Object[] values = new Object[] {
            Boolean.TRUE,
            Integer.MAX_VALUE,
            Long.MAX_VALUE,
            Float.MAX_VALUE,
            Double.MAX_VALUE,
            "json",
            new JSONArray(),
            new JSONObject(true),
            new JSONObject(),
            new JSONObject().element("str", "json"),
            new int[] {1, 2}
        };
        JSONArray expected = JSONArray.fromObject(values);
        JSONArray actual = JSONArray.fromObject(values);

        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Arrays should be equal");
    }

    @Test
    void testAssertEquals_JSONArray_JSONArray__actual_null() {
        JSONArray expected = JSONArray.fromObject("[1,2,3]");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, (JSONArray) null));
        assertEquals("actual array was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONArray_JSONArray__different_length() {
        JSONArray expected = JSONArray.fromObject("[1]");
        JSONArray actual = new JSONArray();
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("arrays sizes differed, expected.length()=1 actual.length()=0", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONArray_JSONArray__expected_null() {
        JSONArray actual = JSONArray.fromObject("[1,2,3]");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals((JSONArray) null, actual));
        assertEquals("expected array was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONArray_JSONArray__nulls() {
        {
            JSONArray expected = JSONArray.fromObject("[1]");
            JSONArray actual = new JSONArray().element(JSONNull.getInstance());
            AssertionFailedError e =
                    assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
            assertEquals("arrays first differed at element [0];", e.getMessage());
        }
        {
            JSONArray expected = new JSONArray().element(JSONNull.getInstance());
            JSONArray actual = JSONArray.fromObject("[1]");
            AssertionFailedError e =
                    assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
            assertEquals("arrays first differed at element [0];", e.getMessage());
        }
    }

    @Test
    void testAssertEquals_JSONArray_String() {
        JSONArray expected = JSONArray.fromObject("[1,2,3]");
        String actual = "[1,2,3]";
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Arrays should be equal");
    }

    @Test
    void testAssertEquals_JSONArray_String_fail() {
        JSONArray expected = JSONArray.fromObject("[1,2,3]");
        String actual = "{1,2,3}";
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is not a JSONArray", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONNull_String() {
        JSONNull expected = JSONNull.getInstance();
        String actual = "null";
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Should be equal");
    }

    @Test
    void testAssertEquals_JSONNull_String__actual_null() {
        JSONNull expected = JSONNull.getInstance();
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, (String) null));
        assertEquals("actual string was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONNull_String__expected_null() {
        assertDoesNotThrow(() -> JSONAssert.assertEquals((JSONNull) null, "null"), "Should be equal");
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_() {
        Map<String, Object> map = Map.ofEntries(
                Map.entry("b", true),
                Map.entry("i", Integer.MAX_VALUE),
                Map.entry("l", Long.MAX_VALUE),
                Map.entry("f", Float.MAX_VALUE),
                Map.entry("d", Double.MAX_VALUE),
                Map.entry("s", "json"),
                Map.entry("a1", new JSONArray()),
                Map.entry("o1", new JSONObject(true)),
                Map.entry("o2", new JSONObject()),
                Map.entry("o3", new JSONObject().element("str", "json")));
        JSONObject expected = JSONObject.fromObject(map);
        JSONObject actual = JSONObject.fromObject(map);
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Objects should be equal");
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject__actual_null() {
        JSONObject expected = JSONObject.fromObject("{}");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, (JSONObject) null));
        assertEquals("actual object was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject__expected_null() {
        JSONObject actual = JSONObject.fromObject("{}");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals((JSONObject) null, actual));
        assertEquals("expected object was null", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_missingExpectedNamesAreGivenInErrorMessage() {
        JSONObject expected = new JSONObject().element("foo", "fooValue");
        JSONObject actual = new JSONObject();
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("missing expected names: [foo]", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_unexpectedNamesAreGivenInErrorMessage() {
        JSONObject expected = new JSONObject();
        JSONObject actual = new JSONObject().element("foo", "fooValue");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("unexpected names: [foo]", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_missingExpectedAnUnexpectedNamesAreBothGivenInErrorMessage() {
        JSONObject expected = new JSONObject().element("foo", "fooValue").element("baz", "bazValue");
        JSONObject actual = new JSONObject().element("bar", "barValue");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("missing expected names: [baz, foo], unexpected names: [bar]", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_nullObjects() {
        JSONObject expected = new JSONObject(true);
        JSONObject actual = new JSONObject(true);
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Objects should be equal");
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_nullObjects_fail1() {
        JSONObject expected = new JSONObject();
        JSONObject actual = new JSONObject(true);
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is a null JSONObject", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_JSONObject_nullObjects_fail2() {
        JSONObject expected = new JSONObject(true);
        JSONObject actual = new JSONObject();
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is not a null JSONObject", e.getMessage());
    }

    @Test
    void testAssertEquals_JSONObject_String() {
        JSONObject expected = JSONObject.fromObject("{\"str\":\"json\"}");
        String actual = "{\"str\":\"json\"}";
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Objects should be equal");
    }

    @Test
    void testAssertEquals_JSONObject_String_fail() {
        JSONObject expected = JSONObject.fromObject("{\"str\":\"json\"}");
        String actual = "[1,2,3]";
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("actual is not a JSONObject", e.getMessage());
    }

    @Test
    void testAssertEquals_String_JSONArray() {
        String expected = "[1,2,3]";
        JSONArray actual = JSONArray.fromObject("[1,2,3]");
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Arrays should be equal");
    }

    @Test
    void testAssertEquals_String_JSONArray_fail() {
        String expected = "{1,2,3}";
        JSONArray actual = JSONArray.fromObject("[1,2,3]");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("expected is not a JSONArray", e.getMessage());
    }

    @Test
    void testAssertEquals_String_JSONNull() {
        String expected = "null";
        JSONNull actual = JSONNull.getInstance();
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Should be equal");
    }

    @Test
    void testAssertEquals_String_JSONObject() {
        String expected = "{\"str\":\"json\"}";
        JSONObject actual = JSONObject.fromObject("{\"str\":\"json\"}");
        assertDoesNotThrow(() -> JSONAssert.assertEquals(expected, actual), "Objects should be equal");
    }

    @Test
    void testAssertEquals_String_JSONObject_fail() {
        String expected = "[1,2,3]";
        JSONObject actual = JSONObject.fromObject("{\"str\":\"json\"}");
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertEquals(expected, actual));
        assertEquals("expected is not a JSONObject", e.getMessage());
    }

    @Test
    void testAssertJsonEquals_garbage_json() {
        String expected = "garbage";
        String actual = "null";
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertJsonEquals(expected, actual));
        assertEquals("expected is not a valid JSON string", e.getMessage());
    }

    @Test
    void testAssertJsonEquals_json_garbage() {
        String expected = "null";
        String actual = "garbage";
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertJsonEquals(expected, actual));
        assertEquals("actual is not a valid JSON string", e.getMessage());
    }

    @Test
    void testAssertJsonEquals_jsonArray_jsonArray() {
        String expected = "[1,2,3]";
        String actual = "[1,2,3]";
        assertDoesNotThrow(
                () -> JSONAssert.assertJsonEquals(expected, actual), "Strings should be valid JSON and equal");
    }

    @Test
    void testAssertJsonEquals_jsonNull_jsonNull() {
        String expected = "null";
        String actual = "null";
        assertDoesNotThrow(
                () -> JSONAssert.assertJsonEquals(expected, actual), "Strings should be valid JSON and equal");
    }

    @Test
    void testAssertJsonEquals_jsonObject_jsonObject() {
        String expected = "{\"str\":\"json\"}";
        String actual = "{\"str\":\"json\"}";
        assertDoesNotThrow(
                () -> JSONAssert.assertJsonEquals(expected, actual), "Strings should be valid JSON and equal");
    }

    @Test
    void testAssertNotNull_JSONNull() {
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertNotNull(JSONNull.getInstance()));
        assertEquals("Object is null", e.getMessage());
    }

    @Test
    void testAssertNotNull_jsonObject_null() {
        AssertionFailedError e =
                assertThrows(AssertionFailedError.class, () -> JSONAssert.assertNotNull(new JSONObject(true)));
        assertEquals("Object is null", e.getMessage());
    }

    @Test
    void testAssertNotNull_null() {
        AssertionFailedError e = assertThrows(AssertionFailedError.class, () -> JSONAssert.assertNotNull(null));
        assertEquals("Object is null", e.getMessage());
    }

    @Test
    void testAssertNull_JSONNull() {
        assertDoesNotThrow(
                () -> JSONAssert.assertNull(JSONNull.getInstance()), "Parameter is null and assertion failed");
    }

    @Test
    void testAssertNull_jsonObject_null() {
        assertDoesNotThrow(() -> JSONAssert.assertNull(new JSONObject(true)), "Parameter is null and assertion failed");
    }

    @Test
    void testAssertNull_null() {
        assertDoesNotThrow(() -> JSONAssert.assertNull(null), "Parameter is null and assertion failed");
    }
}

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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import net.sf.json.test.JSONAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArrayAsList {
    private JSONArray jsonArray;

    @Test
    void testAdd() {
        assertEquals(4, jsonArray.size());
    }

    @Test
    void testAdd_index_value() {
        assertEquals(4, jsonArray.size());
        Object first = jsonArray.get(0);
        jsonArray.add(0, "value");
        assertEquals(5, jsonArray.size());
        assertEquals("value", jsonArray.get(0));
        assertEquals(first, jsonArray.get(1));
    }

    @Test
    void testAddAll() {
        JSONArray array = new JSONArray();
        array.addAll(jsonArray);
        JSONAssert.assertEquals(jsonArray, array);
    }

    @Test
    void testAddAll_index_value() {
        JSONArray array = new JSONArray().element("value");
        array.addAll(0, jsonArray);
        assertEquals(5, array.size());
        assertEquals("value", array.get(4));
    }

    @Test
    void testClear() {
        assertEquals(4, jsonArray.size());
        jsonArray.clear();
        assertEquals(0, jsonArray.size());
    }

    @Test
    void testContains() {
        assertTrue(jsonArray.contains("1"));
        assertFalse(jsonArray.contains("2"));
    }

    @Test
    void testContainsAll() {
        assertTrue(jsonArray.containsAll(jsonArray));
    }

    @Test
    void testIndexOf() {
        jsonArray.element("1");
        assertEquals(0, jsonArray.indexOf("1"));
    }

    @Test
    void testIsEmpty() {
        assertFalse(jsonArray.isEmpty());
        jsonArray = new JSONArray();
        assertTrue(jsonArray.isEmpty());
    }

    @Test
    void testLastIndexOf() {
        jsonArray.element("1");
        assertEquals(4, jsonArray.lastIndexOf("1"));
    }

    @Test
    void testRemove() {
        assertEquals(4, jsonArray.size());
        jsonArray.remove("string");
        assertEquals(3, jsonArray.size());
        assertFalse(jsonArray.contains("string"));
    }

    @Test
    void testRemove_index() {
        assertEquals(4, jsonArray.size());
        jsonArray.remove(2);
        assertEquals(3, jsonArray.size());
        assertFalse(jsonArray.contains("string"));
    }

    @Test
    void testRemoveAll() {
        assertEquals(4, jsonArray.size());
        jsonArray.removeAll(jsonArray);
        assertTrue(jsonArray.isEmpty());
    }

    @Test
    void testRetainAll() {
        assertEquals(4, jsonArray.size());
        jsonArray.retainAll(jsonArray);
        assertEquals(4, jsonArray.size());
    }

    @Test
    void testSubList() {
        List<?> actual = jsonArray.subList(0, 3);
        JSONArray expected = new JSONArray().element("1").element("true").element("string");
        JSONAssert.assertEquals(expected, JSONArray.fromObject(actual));
    }

    /*
     * public void testToArray() { } public void testToArray_array() { }
     */

    @BeforeEach
    void setUp() {
        jsonArray = new JSONArray()
                .element("1")
                .element("true")
                .element("string")
                .element("[1,2,3]");
    }
}

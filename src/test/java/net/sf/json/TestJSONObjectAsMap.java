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

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONObjectAsMap {

    private JSONObject jsonObject;

    @Test
    void testClear() {
        assertEquals(5, jsonObject.size());
        jsonObject.clear();
        assertEquals(0, jsonObject.size());
    }

    @Test
    void testContainsKey() {
        assertFalse(jsonObject.containsKey("bogus"));
    }

    @Test
    void testContainsValue() {
        assertTrue(jsonObject.containsValue("string"));
    }

    @Test
    void testIsEmpty() {
        assertFalse(jsonObject.isEmpty());
    }

    @Test
    void testPut() {
        String key = "key";
        Object value = "value";
        jsonObject.put(key, value);
        assertEquals(value, jsonObject.get(key));
    }

    @Test
    void testPutAll() {
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        json.putAll(map);
        assertEquals(1, json.size());
        assertEquals("value", json.get("key"));
        map.put("key", "value2");
        json.putAll(map);
        assertEquals(1, json.size());
        assertEquals("value2", json.get("key"));
    }

    @Test
    void testRemove() {
        assertTrue(jsonObject.has("array"));
        jsonObject.remove("array");
        assertFalse(jsonObject.has("array"));
    }

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject()
                .element("int", "1")
                .element("long", "1")
                .element("boolean", "true")
                .element("string", "string")
                .element("array", "[1,2,3]");
    }
}

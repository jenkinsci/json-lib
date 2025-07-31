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

import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArrayEqualsHashCodeCompareTo {
    private static final JSONArray strings;
    private static final JSONArray values1;
    private static final JSONArray values2;
    private static final JSONArray values3;

    static {
        strings = new JSONArray()
                .element("1")
                .element("1")
                .element("true")
                .element("string")
                .element("[1,2,3]");
        values1 = new JSONArray()
                .element(Integer.valueOf("1"))
                .element(Long.valueOf("1"))
                .element(Boolean.TRUE)
                .element("string")
                .element(JSONArray.fromObject(new int[] {1, 2, 3}));
        values2 = new JSONArray()
                .element(Integer.valueOf("1"))
                .element(Long.valueOf("1"))
                .element(Boolean.TRUE)
                .element("string");
        values3 = new JSONArray()
                .element(Integer.valueOf("2"))
                .element(Long.valueOf("2"))
                .element(Boolean.FALSE)
                .element("string2");
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
    void testEquals_different_elements_same_size() {
        assertNotEquals(values2, values3);
        assertNotEquals(values3, values2);
    }

    @Test
    void testEquals_null() {
        assertNotEquals(null, strings);
    }

    @Test
    void testEquals_object() {
        assertNotEquals(strings, new Object());
    }

    @Test
    void testEquals_same_object() {
        assertEquals(strings, strings);
    }

    @Test
    void testEquals_same_size_similar_values() {
        assertEquals(strings, values1);
    }

    @Test
    void testHashCode_different_elements_same_size() {
        assertNotEquals(values2.hashCode(), values3.hashCode());
    }
}

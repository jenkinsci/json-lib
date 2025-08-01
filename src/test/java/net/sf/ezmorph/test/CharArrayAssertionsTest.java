/*
 * Copyright 2006-2007 the original author or authors.
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

package net.sf.ezmorph.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class CharArrayAssertionsTest {
    @Test
    void testAssertEquals_char_char() {
        char[] expecteds = new char[] {'A', 'B'};
        char[] actuals = new char[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_char_char_actuals_is_null() {
        char[] expecteds = new char[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (char[]) null));
    }

    @Test
    void testAssertEquals_char_char_different_length() {
        char[] expecteds = new char[] {'A'};
        char[] actuals = new char[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_char_char_expecteds_is_null() {
        char[] actuals = new char[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((char[]) null, actuals));
    }

    @Test
    void testAssertEquals_char_Character() {
        char[] expecteds = new char[] {'A', 'B'};
        Character[] actuals = new Character[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_char_Character_actuals_is_null() {
        char[] expecteds = new char[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Character[]) null));
    }

    @Test
    void testAssertEquals_char_Character_different_length() {
        char[] expecteds = new char[] {'A'};
        Character[] actuals = new Character[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_char_Character_expecteds_is_null() {
        Character[] actuals = new Character[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((char[]) null, actuals));
    }

    @Test
    void testAssertEquals_Character_char() {
        Character[] expecteds = new Character[] {'A', 'B'};
        char[] actuals = new char[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Character_char_actuals_is_null() {
        Character[] expecteds = new Character[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (char[]) null));
    }

    @Test
    void testAssertEquals_Character_char_different_length() {
        Character[] expecteds = new Character[] {'A'};
        char[] actuals = new char[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Character_char_expecteds_is_null() {
        char[] actuals = new char[] {'A', 'B'};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Character[]) null, actuals));
    }

    @Test
    void testAssertEquals_Character_Character() {
        Character[] expecteds = new Character[] {'A', 'B'};
        Character[] actuals = new Character[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_char_char() {
        char[][] expecteds = new char[][] {{'A', 'B'}, {'A', 'B'}};
        char[][] actuals = new char[][] {{'A', 'B'}, {'A', 'B'}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_char_Character() {
        char[][] expecteds = new char[][] {{'A', 'B'}, {'A', 'B'}};
        Character[][] actuals = new Character[][] {{'A', 'B'}, {'A', 'B'}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Character_char() {
        Character[][] expecteds = new Character[][] {{'A', 'B'}, {'A', 'B'}};
        char[][] actuals = new char[][] {{'A', 'B'}, {'A', 'B'}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Character_Character() {
        Character[][] expecteds = new Character[][] {{'A', 'B'}, {'A', 'B'}};
        Character[][] actuals = new Character[][] {{'A', 'B'}, {'A', 'B'}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_char_char() {
        Object expecteds = new char[] {'A', 'B'};
        Object actuals = new char[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_char_Character() {
        Object expecteds = new char[] {'A', 'B'};
        Object actuals = new Character[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_char_double() {
        Object expecteds = new char[] {'A', 'B'};
        Object actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_char_Object_array() {
        Object expecteds = new char[] {'A', 'B'};
        Object actuals = new Object[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Character_char() {
        Object expecteds = new Character[] {'A', 'B'};
        Object actuals = new char[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_char() {
        Object expecteds = new Object[] {'A', 'B'};
        Object actuals = new char[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {'A', 'B'};
        Object actuals = new Object[] {'A', 'B'};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

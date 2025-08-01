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
class ShortArrayAssertionsTest {
    @Test
    void testAssertEquals_multi_short_short() {
        short[][] expecteds = new short[][] {{1, 2}, {1, 2}};
        short[][] actuals = new short[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_short_Short() {
        short[][] expecteds = new short[][] {{1, 2}, {1, 2}};
        Short[][] actuals = new Short[][] {{(short) 1, (short) 2}, {(short) 1, (short) 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Short_short() {
        Short[][] expecteds = new Short[][] {{(short) 1, (short) 2}, {(short) 1, (short) 2}};
        short[][] actuals = new short[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Short_Short() {
        Short[][] expecteds = new Short[][] {{(short) 1, (short) 2}, {(short) 1, (short) 2}};
        Short[][] actuals = new Short[][] {{(short) 1, (short) 2}, {(short) 1, (short) 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {(short) 1, (short) 2};
        Object actuals = new Object[] {(short) 1, (short) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_short() {
        Object expecteds = new Object[] {(short) 1, (short) 2};
        Object actuals = new short[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_short_double() {
        Object expecteds = new short[] {1, 2};
        Object actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_short_Object_array() {
        Object expecteds = new short[] {1, 2};
        Object actuals = new Object[] {(short) 1, (short) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_short_short() {
        Object expecteds = new short[] {1, 2};
        Object actuals = new short[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_short_Short() {
        Object expecteds = new short[] {1, 2};
        Object actuals = new Short[] {(short) 1, (short) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Short_short() {
        Object expecteds = new Short[] {(short) 1, (short) 2};
        Object actuals = new short[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_short_short() {
        short[] expecteds = new short[] {1, 2};
        short[] actuals = new short[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_short_Short() {
        short[] expecteds = new short[] {1, 2};
        Short[] actuals = new Short[] {(short) 1, (short) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Short_short() {
        Short[] expecteds = new Short[] {(short) 1, (short) 2};
        short[] actuals = new short[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Short_Short() {
        Short[] expecteds = new Short[] {(short) 1, (short) 2};
        Short[] actuals = new Short[] {(short) 1, (short) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_short_short_actuals_is_null() {
        short[] expecteds = new short[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (short[]) null));
    }

    @Test
    void testAssertEquals_short_Short_actuals_is_null() {
        short[] expecteds = new short[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Short[]) null));
    }

    @Test
    void testAssertEquals_Short_short_actuals_is_null() {
        Short[] expecteds = new Short[] {(short) 1, (short) 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (short[]) null));
    }

    @Test
    void testAssertEquals_short_short_different_length() {
        short[] expecteds = new short[] {1};
        short[] actuals = new short[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_short_Short_different_length() {
        short[] expecteds = new short[] {1};
        Short[] actuals = new Short[] {(short) 1, (short) 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Short_short_different_length() {
        Short[] expecteds = new Short[] {(short) 1};
        short[] actuals = new short[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_short_short_expecteds_is_null() {
        short[] actuals = new short[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((short[]) null, actuals));
    }

    @Test
    void testAssertEquals_short_Short_expecteds_is_null() {
        Short[] actuals = new Short[] {(short) 1, (short) 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((short[]) null, actuals));
    }

    @Test
    void testAssertEquals_Short_short_expecteds_is_null() {
        short[] actuals = new short[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Short[]) null, actuals));
    }
}

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
class IntArrayAssertionsTest {
    @Test
    void testAssertEquals_int_int() {
        int[] expecteds = new int[] {1, 2};
        int[] actuals = new int[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_int_int_actuals_is_null() {
        int[] expecteds = new int[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (int[]) null));
    }

    @Test
    void testAssertEquals_int_int_different_length() {
        int[] expecteds = new int[] {1};
        int[] actuals = new int[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_int_int_expecteds_is_null() {
        int[] actuals = new int[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((int[]) null, actuals));
    }

    @Test
    void testAssertEquals_int_Integer() {
        int[] expecteds = new int[] {1, 2};
        Integer[] actuals = new Integer[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_int_Integer_actuals_is_null() {
        int[] expecteds = new int[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Integer[]) null));
    }

    @Test
    void testAssertEquals_int_Integer_different_length() {
        int[] expecteds = new int[] {1};
        Integer[] actuals = new Integer[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_int_Integer_expecteds_is_null() {
        Integer[] actuals = new Integer[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((int[]) null, actuals));
    }

    @Test
    void testAssertEquals_Integer_int() {
        Integer[] expecteds = new Integer[] {1, 2};
        int[] actuals = new int[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Integer_int_actuals_is_null() {
        Integer[] expecteds = new Integer[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (int[]) null));
    }

    @Test
    void testAssertEquals_Integer_int_different_length() {
        Integer[] expecteds = new Integer[] {1};
        int[] actuals = new int[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Integer_int_expecteds_is_null() {
        int[] actuals = new int[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Integer[]) null, actuals));
    }

    @Test
    void testAssertEquals_Integer_Integer() {
        Integer[] expecteds = new Integer[] {1, 2};
        Integer[] actuals = new Integer[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_int_int() {
        int[][] expecteds = new int[][] {{1, 2}, {1, 2}};
        int[][] actuals = new int[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_int_Integer() {
        int[][] expecteds = new int[][] {{1, 2}, {1, 2}};
        Integer[][] actuals = new Integer[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Integer_int() {
        Integer[][] expecteds = new Integer[][] {{1, 2}, {1, 2}};
        int[][] actuals = new int[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Integer_Integer() {
        Integer[][] expecteds = new Integer[][] {{1, 2}, {1, 2}};
        Integer[][] actuals = new Integer[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_int_double() {
        Object expecteds = new int[] {1, 2};
        Object actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_int_int() {
        Object expecteds = new int[] {1, 2};
        Object actuals = new int[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_int_Integer() {
        Object expecteds = new int[] {1, 2};
        Object actuals = new Integer[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_int_Object_array() {
        Object expecteds = new int[] {1, 2};
        Object actuals = new Object[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Integer_int() {
        Object expecteds = new Integer[] {1, 2};
        Object actuals = new int[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_int() {
        Object expecteds = new Object[] {1, 2};
        Object actuals = new int[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {1, 2};
        Object actuals = new Object[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

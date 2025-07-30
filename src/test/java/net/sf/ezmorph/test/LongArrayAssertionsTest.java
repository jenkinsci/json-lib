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

import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class LongArrayAssertionsTest {
    @Test
    void testAssertEquals_long_long() {
        long[] expecteds = new long[] {1, 2};
        long[] actuals = new long[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_long_Long() {
        long[] expecteds = new long[] {1, 2};
        Long[] actuals = new Long[] {1L, 2L};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Long_long() {
        Long[] expecteds = new Long[] {1L, 2L};
        long[] actuals = new long[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Long_Long() {
        Long[] expecteds = new Long[] {1L, 2L};
        Long[] actuals = new Long[] {1L, 2L};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_long_long_actuals_is_null() {
        long[] expecteds = new long[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (long[]) null));
    }

    @Test
    void testAssertEquals_long_Long_actuals_is_null() {
        long[] expecteds = new long[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Long[]) null));
    }

    @Test
    void testAssertEquals_Long_long_actuals_is_null() {
        Long[] expecteds = new Long[] {1L, 2L};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (long[]) null));
    }

    @Test
    void testAssertEquals_long_long_different_length() {
        long[] expecteds = new long[] {1};
        long[] actuals = new long[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_long_Long_different_length() {
        long[] expecteds = new long[] {1};
        Long[] actuals = new Long[] {1L, 2L};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Long_long_different_length() {
        Long[] expecteds = new Long[] {1L};
        long[] actuals = new long[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_long_long_expecteds_is_null() {
        long[] actuals = new long[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((long[]) null, actuals));
    }

    @Test
    void testAssertEquals_long_Long_expecteds_is_null() {
        Long[] actuals = new Long[] {1L, 2L};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((long[]) null, actuals));
    }

    @Test
    void testAssertEquals_Long_long_expecteds_is_null() {
        long[] actuals = new long[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Long[]) null, actuals));
    }

    @Test
    void testAssertEquals_multi_long_long() {
        long[][] expecteds = new long[][] {{1, 2}, {1, 2}};
        long[][] actuals = new long[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_long_Long() {
        long[][] expecteds = new long[][] {{1, 2}, {1, 2}};
        Long[][] actuals = new Long[][] {{1L, 2L}, {1L, 2L}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Long_long() {
        Long[][] expecteds = new Long[][] {{1L, 2L}, {1L, 2L}};
        long[][] actuals = new long[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Long_Long() {
        Long[][] expecteds = new Long[][] {{1L, 2L}, {1L, 2L}};
        Long[][] actuals = new Long[][] {{1L, 2L}, {1L, 2L}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_long_double() {
        Object expecteds = new long[] {1, 2};
        Object actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_long_long() {
        Object expecteds = new long[] {1, 2};
        Object actuals = new long[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_long_Long() {
        Object expecteds = new long[] {1, 2};
        Object actuals = new Long[] {1L, 2L};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Long_long() {
        Object expecteds = new Long[] {1L, 2L};
        Object actuals = new long[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_long_Object_array() {
        Object expecteds = new long[] {1, 2};
        Object actuals = new Object[] {1L, 2L};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_long() {
        Object expecteds = new Object[] {1L, 2L};
        Object actuals = new long[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {1L, 2L};
        Object actuals = new Object[] {1L, 2L};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

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
class FloatArrayAssertionsTest {
    @Test
    void testAssertEquals_float_float() {
        float[] expecteds = new float[] {1, 2};
        float[] actuals = new float[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_float_Float() {
        float[] expecteds = new float[] {1, 2};
        Float[] actuals = new Float[] {1f, 2f};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Float_float() {
        Float[] expecteds = new Float[] {1f, 2f};
        float[] actuals = new float[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Float_Float() {
        Float[] expecteds = new Float[] {1f, 2f};
        Float[] actuals = new Float[] {1f, 2f};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_float_float_actuals_is_null() {
        float[] expecteds = new float[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (float[]) null));
    }

    @Test
    void testAssertEquals_float_Float_actuals_is_null() {
        float[] expecteds = new float[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Float[]) null));
    }

    @Test
    void testAssertEquals_Float_float_actuals_is_null() {
        Float[] expecteds = new Float[] {1f, 2f};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (float[]) null));
    }

    @Test
    void testAssertEquals_float_float_different_length() {
        float[] expecteds = new float[] {1};
        float[] actuals = new float[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_float_Float_different_length() {
        float[] expecteds = new float[] {1};
        Float[] actuals = new Float[] {1f, 2f};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Float_float_different_length() {
        Float[] expecteds = new Float[] {1f};
        float[] actuals = new float[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_float_float_expecteds_is_null() {
        float[] actuals = new float[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((float[]) null, actuals));
    }

    @Test
    void testAssertEquals_float_Float_expecteds_is_null() {
        Float[] actuals = new Float[] {1f, 2f};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((float[]) null, actuals));
    }

    @Test
    void testAssertEquals_Float_float_expecteds_is_null() {
        float[] actuals = new float[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Float[]) null, actuals));
    }

    @Test
    void testAssertEquals_multi_float_float() {
        float[][] expecteds = new float[][] {{1, 2}, {1, 2}};
        float[][] actuals = new float[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_float_Float() {
        float[][] expecteds = new float[][] {{1, 2}, {1, 2}};
        Float[][] actuals = new Float[][] {{1f, 2f}, {1f, 2f}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Float_float() {
        Float[][] expecteds = new Float[][] {{1f, 2f}, {1f, 2f}};
        float[][] actuals = new float[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Float_Float() {
        Float[][] expecteds = new Float[][] {{1f, 2f}, {1f, 2f}};
        Float[][] actuals = new Float[][] {{1f, 2f}, {1f, 2f}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_float_double() {
        Object expecteds = new float[] {1, 2};
        Object actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_float_float() {
        Object expecteds = new float[] {1, 2};
        Object actuals = new float[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_float_Float() {
        Object expecteds = new float[] {1, 2};
        Object actuals = new Float[] {1f, 2f};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Float_float() {
        Object expecteds = new Float[] {1f, 2f};
        Object actuals = new float[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_float_Object_array() {
        Object expecteds = new float[] {1, 2};
        Object actuals = new Object[] {1f, 2f};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_float() {
        Object expecteds = new Object[] {1f, 2f};
        Object actuals = new float[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {1f, 2f};
        Object actuals = new Object[] {1f, 2f};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

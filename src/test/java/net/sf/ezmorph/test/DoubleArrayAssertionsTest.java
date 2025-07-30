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
class DoubleArrayAssertionsTest {
    @Test
    void testAssertEquals_double_double() {
        double[] expecteds = new double[] {1, 2};
        double[] actuals = new double[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_double_Double() {
        double[] expecteds = new double[] {1, 2};
        Double[] actuals = new Double[] {1d, 2d};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Double_double() {
        Double[] expecteds = new Double[] {1d, 2d};
        double[] actuals = new double[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Double_Double() {
        Double[] expecteds = new Double[] {1d, 2d};
        Double[] actuals = new Double[] {1d, 2d};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_double_double_actuals_is_null() {
        double[] expecteds = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (double[]) null));
    }

    @Test
    void testAssertEquals_double_Double_actuals_is_null() {
        double[] expecteds = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Double[]) null));
    }

    @Test
    void testAssertEquals_Double_double_actuals_is_null() {
        Double[] expecteds = new Double[] {1d, 2d};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (double[]) null));
    }

    @Test
    void testAssertEquals_double_double_different_length() {
        double[] expecteds = new double[] {1};
        double[] actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_double_Double_different_length() {
        double[] expecteds = new double[] {1};
        Double[] actuals = new Double[] {1d, 2d};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Double_double_different_length() {
        Double[] expecteds = new Double[] {1d};
        double[] actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_double_double_expecteds_is_null() {
        double[] actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((double[]) null, actuals));
    }

    @Test
    void testAssertEquals_double_Double_expecteds_is_null() {
        Double[] actuals = new Double[] {1d, 2d};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((double[]) null, actuals));
    }

    @Test
    void testAssertEquals_Double_double_expecteds_is_null() {
        double[] actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Double[]) null, actuals));
    }

    @Test
    void testAssertEquals_multi_double_double() {
        double[][] expecteds = new double[][] {{1, 2}, {1, 2}};
        double[][] actuals = new double[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_double_Double() {
        double[][] expecteds = new double[][] {{1, 2}, {1, 2}};
        Double[][] actuals = new Double[][] {{1d, 2d}, {1d, 2d}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Double_double() {
        Double[][] expecteds = new Double[][] {{1d, 2d}, {1d, 2d}};
        double[][] actuals = new double[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Double_Double() {
        Double[][] expecteds = new Double[][] {{1d, 2d}, {1d, 2d}};
        Double[][] actuals = new Double[][] {{1d, 2d}, {1d, 2d}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_double_byte() {
        Object expecteds = new double[] {1, 2};
        Object actuals = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_double_double() {
        Object expecteds = new double[] {1, 2};
        Object actuals = new double[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_double_Double() {
        Object expecteds = new double[] {1, 2};
        Object actuals = new Double[] {1d, 2d};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Double_double() {
        Object expecteds = new Double[] {1d, 2d};
        Object actuals = new double[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_double_Object_array() {
        Object expecteds = new double[] {1, 2};
        Object actuals = new Object[] {1d, 2d};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_double() {
        Object expecteds = new Object[] {1d, 2d};
        Object actuals = new double[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {1d, 2d};
        Object actuals = new Object[] {1d, 2d};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

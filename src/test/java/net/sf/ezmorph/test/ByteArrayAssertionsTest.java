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
class ByteArrayAssertionsTest {
    @Test
    void testAssertEquals_byte_byte() {
        byte[] expecteds = new byte[] {1, 2};
        byte[] actuals = new byte[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_byte_Byte() {
        byte[] expecteds = new byte[] {1, 2};
        Byte[] actuals = new Byte[] {(byte) 1, (byte) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Byte_byte() {
        Byte[] expecteds = new Byte[] {(byte) 1, (byte) 2};
        byte[] actuals = new byte[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_Byte_Byte() {
        Byte[] expecteds = new Byte[] {(byte) 1, (byte) 2};
        Byte[] actuals = new Byte[] {(byte) 1, (byte) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_byte_byte_actuals_is_null() {
        byte[] expecteds = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (byte[]) null));
    }

    @Test
    void testAssertEquals_byte_Byte_actuals_is_null() {
        byte[] expecteds = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Byte[]) null));
    }

    @Test
    void testAssertEquals_Byte_byte_actuals_is_null() {
        Byte[] expecteds = new Byte[] {(byte) 1, (byte) 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (byte[]) null));
    }

    @Test
    void testAssertEquals_byte_byte_different_length() {
        byte[] expecteds = new byte[] {1};
        byte[] actuals = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_byte_Byte_different_length() {
        byte[] expecteds = new byte[] {1};
        Byte[] actuals = new Byte[] {(byte) 1, (byte) 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Byte_byte_different_length() {
        Byte[] expecteds = new Byte[] {(byte) 1};
        byte[] actuals = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_byte_byte_expecteds_is_null() {
        byte[] actuals = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((byte[]) null, actuals));
    }

    @Test
    void testAssertEquals_byte_Byte_expecteds_is_null() {
        Byte[] actuals = new Byte[] {(byte) 1, (byte) 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((byte[]) null, actuals));
    }

    @Test
    void testAssertEquals_Byte_byte_expecteds_is_null() {
        byte[] actuals = new byte[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Byte[]) null, actuals));
    }

    @Test
    void testAssertEquals_multi_byte_byte() {
        byte[][] expecteds = new byte[][] {{1, 2}, {1, 2}};
        byte[][] actuals = new byte[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_byte_Byte() {
        byte[][] expecteds = new byte[][] {{1, 2}, {1, 2}};
        Byte[][] actuals = new Byte[][] {{(byte) 1, (byte) 2}, {(byte) 1, (byte) 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Byte_byte() {
        Byte[][] expecteds = new Byte[][] {{(byte) 1, (byte) 2}, {(byte) 1, (byte) 2}};
        byte[][] actuals = new byte[][] {{1, 2}, {1, 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_multi_Byte_Byte() {
        Byte[][] expecteds = new Byte[][] {{(byte) 1, (byte) 2}, {(byte) 1, (byte) 2}};
        Byte[][] actuals = new Byte[][] {{(byte) 1, (byte) 2}, {(byte) 1, (byte) 2}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_byte_byte() {
        Object expecteds = new byte[] {1, 2};
        Object actuals = new byte[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_byte_Byte() {
        Object expecteds = new byte[] {1, 2};
        Object actuals = new Byte[] {(byte) 1, (byte) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Byte_byte() {
        Object expecteds = new Byte[] {(byte) 1, (byte) 2};
        Object actuals = new byte[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_byte_double() {
        Object expecteds = new byte[] {1, 2};
        Object actuals = new double[] {1, 2};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_OO_byte_Object_array() {
        Object expecteds = new byte[] {1, 2};
        Object actuals = new Object[] {(byte) 1, (byte) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_byte() {
        Object expecteds = new Object[] {(byte) 1, (byte) 2};
        Object actuals = new byte[] {1, 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_OO_Object_array_Object_array() {
        Object expecteds = new Object[] {(byte) 1, (byte) 2};
        Object actuals = new Object[] {(byte) 1, (byte) 2};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

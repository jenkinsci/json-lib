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

package net.sf.ezmorph.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class NumberMorpherTest extends AbstractObjectMorpherTestCase {
    private NumberMorpher anotherMorpher;
    private NumberMorpher anotherMorpherWithDefaultValue;
    private NumberMorpher morpher;
    private NumberMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testBigDecimalConversion_Double() {
        morpher = new NumberMorpher(BigDecimal.class);
        BigDecimal expected = new BigDecimal(Double.MIN_VALUE);
        BigDecimal actual = (BigDecimal) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testBigDecimalConversion_null() {
        morpher = new NumberMorpher(BigDecimal.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testBigDecimalConversion_useDefault() {
        morpher = new NumberMorpher(BigDecimal.class);
        BigDecimal expected = MorphUtils.BIGDECIMAL_ONE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        BigDecimal actual = (BigDecimal) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testBigDecimalConversion_useDefault_null() {
        morpher = new NumberMorpher(BigDecimal.class, null);
        assertNull(morpher.morph(null));
    }

    @Test
    void testBigIntegerConversion_Long() {
        morpher = new NumberMorpher(BigInteger.class);
        BigInteger expected = BigInteger.valueOf(Long.MIN_VALUE);
        BigInteger actual = (BigInteger) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testBigIntegerConversion_null() {
        morpher = new NumberMorpher(BigInteger.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testBigIntegerConversion_useDefault() {
        morpher = new NumberMorpher(BigInteger.class);
        BigInteger expected = BigInteger.ONE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        BigInteger actual = (BigInteger) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testBigIntegerConversion_useDefault_null() {
        morpher = new NumberMorpher(BigInteger.class, null);
        assertNull(morpher.morph(null));
    }

    @Test
    void testByteConversion_byte() {
        morpher = new NumberMorpher(Byte.TYPE);
        Byte expected = Byte.MIN_VALUE;
        Byte actual = (Byte) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testByteConversion_Byte() {
        morpher = new NumberMorpher(Byte.class);
        Byte expected = Byte.MIN_VALUE;
        Byte actual = (Byte) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testByteConversion_null() {
        morpher = new NumberMorpher(Byte.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testByteConversion_String() {
        morpher = new NumberMorpher(Byte.class);
        Byte actual = (Byte) morpher.morph(String.valueOf(Byte.MIN_VALUE));
        assertEquals(Byte.MIN_VALUE, actual);
    }

    @Test
    void testByteConversion_useDefault() {
        Byte expected = Byte.MIN_VALUE;
        morpher = new NumberMorpher(Byte.class, expected);
        Byte actual = (Byte) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testByteConversion_useDefault_null() {
        morpher = new NumberMorpher(Byte.class, null);
        assertNull(morpher.morph(new Object()));
    }

    @Test
    void testDoubleConversion_double() {
        morpher = new NumberMorpher(Double.TYPE);
        Double expected = Double.MIN_VALUE;
        Double actual = (Double) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testDoubleConversion_Double() {
        morpher = new NumberMorpher(Double.class);
        Double expected = Double.MIN_VALUE;
        Double actual = (Double) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testDoubleConversion_null() {
        morpher = new NumberMorpher(Double.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testDoubleConversion_String() {
        morpher = new NumberMorpher(Double.class);
        Double actual = (Double) morpher.morph(String.valueOf(Double.MIN_VALUE));
        assertEquals(Double.MIN_VALUE, actual);
    }

    @Test
    void testDoubleConversion_useDefault() {
        morpher = new NumberMorpher(Double.class);
        Double expected = Double.MIN_VALUE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        Double actual = (Double) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testDoubleConversion_useDefault_null() {
        morpher = new NumberMorpher(Double.class, null);
        assertNull(morpher.morph(new Object()));
    }

    @Test
    void testFloatConversion_float() {
        morpher = new NumberMorpher(Float.TYPE);
        Float expected = Float.MIN_VALUE;
        Float actual = (Float) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testFloatConversion_Float() {
        morpher = new NumberMorpher(Float.class);
        Float expected = Float.MIN_VALUE;
        Float actual = (Float) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testFloatConversion_null() {
        morpher = new NumberMorpher(Float.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testFloatConversion_String() {
        morpher = new NumberMorpher(Float.class);
        Float actual = (Float) morpher.morph(String.valueOf(Float.MIN_VALUE));
        assertEquals(Float.MIN_VALUE, actual);
    }

    @Test
    void testFloatConversion_useDefault() {
        morpher = new NumberMorpher(Float.class);
        Float expected = Float.MIN_VALUE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        Float actual = (Float) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testFloatConversion_useDefault_null() {
        morpher = new NumberMorpher(Float.class, null);
        assertNull(morpher.morph(new Object()));
    }

    @Test
    void testIntegerConversion_int() {
        morpher = new NumberMorpher(Integer.TYPE);
        Integer expected = Integer.MIN_VALUE;
        Integer actual = (Integer) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testIntegerConversion_Integer() {
        morpher = new NumberMorpher(Integer.class);
        Integer expected = Integer.MIN_VALUE;
        Integer actual = (Integer) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testIntegerConversion_null() {
        morpher = new NumberMorpher(Integer.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testIntegerConversion_String() {
        morpher = new NumberMorpher(Integer.class);
        Integer actual = (Integer) morpher.morph(String.valueOf(Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, actual);
    }

    @Test
    void testIntegerConversion_useDefault() {
        morpher = new NumberMorpher(Integer.class);
        Integer expected = Integer.MIN_VALUE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        Integer actual = (Integer) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testIntegerConversion_useDefault_null() {
        morpher = new NumberMorpher(Integer.class, null);
        assertNull(morpher.morph(new Object()));
    }

    @Test
    void testlongConversion_long() {
        morpher = new NumberMorpher(Long.TYPE);
        Long expected = Long.MIN_VALUE;
        Long actual = (Long) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testLongConversion_Long() {
        morpher = new NumberMorpher(Long.class);
        Long expected = Long.MIN_VALUE;
        Long actual = (Long) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testLongConversion_null() {
        morpher = new NumberMorpher(Long.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testLongConversion_String() {
        morpher = new NumberMorpher(Long.class);
        Long actual = (Long) morpher.morph(String.valueOf(Long.MIN_VALUE));
        assertEquals(Long.MIN_VALUE, actual);
    }

    @Test
    void testLongConversion_useDefault() {
        morpher = new NumberMorpher(Long.class);
        Long expected = Long.MIN_VALUE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        Long actual = (Long) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testLongConversion_useDefault_null() {
        morpher = new NumberMorpher(Long.class, null);
        assertNull(morpher.morph(new Object()));
    }

    @Test
    void testNumbermorpher_incompatible_defaultValue() {
        assertThrows(MorphException.class, () -> new NumberMorpher(Integer.class, 0d));
    }

    @Test
    void testNumbermorpher_notSupported() {
        assertThrows(MorphException.class, () -> new NumberMorpher(String.class));
    }

    @Test
    void testNumbermorpher_notSupported_2() {
        assertThrows(MorphException.class, () -> new NumberMorpher(String.class, 0));
    }

    @Test
    void testNumbermorpher_unspecifiedClass() {
        assertThrows(MorphException.class, () -> new NumberMorpher(null));
    }

    @Test
    void testNumbermorpher_unspecifiedClass2() {
        assertThrows(MorphException.class, () -> new NumberMorpher(null, 0));
    }

    @Test
    void testShortConversion_null() {
        morpher = new NumberMorpher(Short.class);
        assertNull(morpher.morph(null));
    }

    @Test
    void testShortConversion_short() {
        morpher = new NumberMorpher(Short.TYPE);
        Short expected = Short.MIN_VALUE;
        Short actual = (Short) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testShortConversion_Short() {
        morpher = new NumberMorpher(Short.class);
        Short expected = Short.MIN_VALUE;
        Short actual = (Short) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testShortConversion_String() {
        morpher = new NumberMorpher(Short.class);
        Short actual = (Short) morpher.morph(String.valueOf(Short.MIN_VALUE));
        assertEquals(Short.MIN_VALUE, actual);
    }

    @Test
    void testShortConversion_useDefault() {
        morpher = new NumberMorpher(Short.class);
        Short expected = Short.MIN_VALUE;
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        Short actual = (Short) morpher.morph(new Object());
        assertEquals(expected, actual);
    }

    @Test
    void testShortConversion_useDefault_null() {
        morpher = new NumberMorpher(Short.class, null);
        assertNull(morpher.morph(new Object()));
    }

    @Override
    protected Morpher getAnotherMorpher() {
        return anotherMorpher;
    }

    @Override
    protected Morpher getAnotherMorpherWithDefaultValue() {
        return anotherMorpherWithDefaultValue;
    }

    @Override
    protected Morpher getMorpher() {
        return morpher;
    }

    @Override
    protected Morpher getMorpherWithDefaultValue() {
        return morpherWithDefaultValue;
    }

    @BeforeEach
    void setUp() {
        morpher = new NumberMorpher(Integer.class);
        morpherWithDefaultValue = new NumberMorpher(Integer.class, 0);
        anotherMorpher = new NumberMorpher(Integer.class);
        anotherMorpherWithDefaultValue = new NumberMorpher(Integer.class, 1);
    }
}

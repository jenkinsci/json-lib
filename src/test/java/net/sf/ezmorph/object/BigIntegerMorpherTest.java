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

import java.math.BigInteger;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BigIntegerMorpherTest extends AbstractObjectMorpherTestCase {

    private BigIntegerMorpher anotherMorpher;
    private BigIntegerMorpher anotherMorpherWithDefaultValue;
    private BigIntegerMorpher morpher;
    private BigIntegerMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testBigIntegerMorph_BigDecimal() {
        Object actual = morpherWithDefaultValue.morph(MorphUtils.BIGDECIMAL_ZERO);
        assertEquals(BigInteger.ZERO, actual);
    }

    @Test
    void testBigIntegerMorph_BigInteger() {
        Object actual = morpherWithDefaultValue.morph(BigInteger.ZERO);
        assertEquals(BigInteger.ZERO, actual);
    }

    @Test
    void testBigIntegerMorph_Number() {
        Object actual = morpher.morph((byte) 1);
        assertEquals(BigInteger.ONE, actual);
        actual = morpher.morph((short) 1);
        assertEquals(BigInteger.ONE, actual);
        actual = morpher.morph(1);
        assertEquals(BigInteger.ONE, actual);
        actual = morpher.morph(1L);
        assertEquals(BigInteger.ONE, actual);
        actual = morpher.morph(1d);
        assertEquals(BigInteger.ONE, actual);
        actual = morpher.morph(1f);
        assertEquals(BigInteger.ONE, actual);
        actual = morpher.morph(MorphUtils.BIGDECIMAL_ONE);
        assertEquals(BigInteger.ONE, actual);
    }

    @Test
    void testBigIntegerMorph_Number__Double_INFINITY() {
        assertThrows(MorphException.class, () -> morpher.morph(Double.POSITIVE_INFINITY));
    }

    @Test
    void testBigIntegerMorph_Number__Double_NAN() {
        assertThrows(MorphException.class, () -> morpher.morph(Double.NaN));
    }

    @Test
    void testBigIntegerMorph_Number__Float_INFINITY() {
        assertThrows(MorphException.class, () -> morpher.morph(Float.POSITIVE_INFINITY));
    }

    @Test
    void testBigIntegerMorph_Number__Float_NAN() {
        assertThrows(MorphException.class, () -> morpher.morph(Float.NaN));
    }

    @Test
    void testBigIntegerMorph_String__decimal() {
        Object actual = morpherWithDefaultValue.morph("123.45");
        assertEquals(new BigInteger("123"), actual);
    }

    @Test
    void testBigIntegerMorph_String__int() {
        Object actual = morpherWithDefaultValue.morph("123");
        assertEquals(new BigInteger("123"), actual);
    }

    @Test
    void testBigIntegerMorph_String_empty() {
        assertNull(morpher.morph(""));
    }

    @Test
    void testBigIntegerMorph_String_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testBigIntegerMorph_String_null2() {
        assertNull(morpher.morph("null"));
    }

    @Test
    void testBigIntegerMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testBigIntegerMorph_useDefault() {
        Object actual = morpherWithDefaultValue.morph("A");
        assertEquals(BigInteger.ZERO, actual);
    }

    @Test
    void testBigIntegerMorph_useDefault_null() {
        Object actual = morpherWithDefaultValue.morph(null);
        assertEquals(BigInteger.ZERO, actual);
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
        morpher = new BigIntegerMorpher();
        morpherWithDefaultValue = new BigIntegerMorpher(BigInteger.ZERO);
        anotherMorpher = new BigIntegerMorpher();
        anotherMorpherWithDefaultValue = new BigIntegerMorpher(BigInteger.ONE);
    }
}

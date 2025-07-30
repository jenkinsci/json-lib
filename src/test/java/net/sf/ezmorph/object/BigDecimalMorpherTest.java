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

class BigDecimalMorpherTest extends AbstractObjectMorpherTestCase {

    private BigDecimalMorpher anotherMorpher;
    private BigDecimalMorpher anotherMorpherWithDefaultValue;
    private BigDecimalMorpher morpher;
    private BigDecimalMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testBigDecimalMorph_BigDecimal() {
        Object actual = morpherWithDefaultValue.morph(MorphUtils.BIGDECIMAL_ZERO);
        assertEquals(MorphUtils.BIGDECIMAL_ZERO, actual);
    }

    @Test
    void testBigDecimalMorph_BigInteger() {
        Object actual = morpherWithDefaultValue.morph(BigInteger.ZERO);
        assertEquals(MorphUtils.BIGDECIMAL_ZERO, actual);
    }

    @Test
    void testBigDecimalMorph_Number() {
        Object actual = morpherWithDefaultValue.morph(1f);
        assertEquals(MorphUtils.BIGDECIMAL_ONE, actual);
        actual = morpherWithDefaultValue.morph(1d);
        assertEquals(MorphUtils.BIGDECIMAL_ONE, actual);
    }

    @Test
    void testBigDecimalMorph_Number__Double_INFINITY() {
        assertThrows(MorphException.class, () -> morpher.morph(Double.POSITIVE_INFINITY));
    }

    @Test
    void testBigDecimalMorph_Number__Double_NAN() {
        assertThrows(MorphException.class, () -> morpher.morph(Double.NaN));
    }

    @Test
    void testBigDecimalMorph_Number__Float_INFINITY() {
        assertThrows(MorphException.class, () -> morpher.morph(Float.POSITIVE_INFINITY));
    }

    @Test
    void testBigDecimalMorph_Number__Float_NAN() {
        assertThrows(MorphException.class, () -> morpher.morph(Float.NaN));
    }

    @Test
    void testBigDecimalMorph_String() {
        Object actual = morpherWithDefaultValue.morph("123.45");
        assertEquals(new BigDecimal("123.45"), actual);
    }

    @Test
    void testBigDecimalMorph_String_empty() {
        assertNull(morpher.morph(""));
    }

    @Test
    void testBigDecimalMorph_String_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testBigDecimalMorph_String_null2() {
        assertNull(morpher.morph("null"));
    }

    @Test
    void testBigDecimalMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testBigDecimalMorph_useDefault() {
        Object actual = morpherWithDefaultValue.morph("A");
        assertEquals(MorphUtils.BIGDECIMAL_ZERO, actual);
    }

    @Test
    void testBigDecimalMorph_useDefault_null() {
        Object actual = morpherWithDefaultValue.morph(null);
        assertEquals(MorphUtils.BIGDECIMAL_ZERO, actual);
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
        morpher = new BigDecimalMorpher();
        morpherWithDefaultValue = new BigDecimalMorpher(MorphUtils.BIGDECIMAL_ZERO);
        anotherMorpher = new BigDecimalMorpher();
        anotherMorpherWithDefaultValue = new BigDecimalMorpher(MorphUtils.BIGDECIMAL_ONE);
    }
}

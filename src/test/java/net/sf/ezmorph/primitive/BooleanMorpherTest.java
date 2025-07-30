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

package net.sf.ezmorph.primitive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class BooleanMorpherTest extends AbstractMorpherTestCase {
    private BooleanMorpher anotherMorpher;
    private BooleanMorpher anotherMorpherWithDefaultValue;
    private BooleanMorpher morpher;
    private BooleanMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testBooleanMorph_noConversion() {
        boolean actual = morpherWithDefaultValue.morph(Boolean.TRUE);
        assertTrue(actual);
    }

    @Test
    void testBooleanMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testBooleanMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testBooleanMorph_useDefault() {
        String expected = String.valueOf("A");
        boolean actual = morpherWithDefaultValue.morph(expected);
        assertTrue(actual);
    }

    @Test
    void testBooleanMorph_useDefault_null() {
        boolean actual = morpherWithDefaultValue.morph(null);
        assertTrue(actual);
    }

    @Test
    void testBooleanMorphNumberValues_false() {
        assertFalse(morpher.morph((byte) 0));
        assertFalse(morpher.morph((short) 0));
        assertFalse(morpher.morph(0));
        assertFalse(morpher.morph(0L));
        assertFalse(morpher.morph(0f));
        assertFalse(morpher.morph(0d));
        assertFalse(morpher.morph(BigInteger.ZERO));
        assertFalse(morpher.morph(MorphUtils.BIGDECIMAL_ZERO));
    }

    @Test
    void testBooleanMorphNumberValues_true() {
        assertTrue(morpher.morph((byte) 1));
        assertTrue(morpher.morph((short) 1));
        assertTrue(morpher.morph(1));
        assertTrue(morpher.morph(1L));
        assertTrue(morpher.morph(1f));
        assertTrue(morpher.morph(1d));
        assertTrue(morpher.morph(BigInteger.ONE));
        assertTrue(morpher.morph(MorphUtils.BIGDECIMAL_ONE));

        assertTrue(morpher.morph(Float.NEGATIVE_INFINITY));
        assertTrue(morpher.morph(Float.POSITIVE_INFINITY));
        assertTrue(morpher.morph((Float.NaN)));
        assertTrue(morpher.morph(Double.NEGATIVE_INFINITY));
        assertTrue(morpher.morph(Double.POSITIVE_INFINITY));
        assertTrue(morpher.morph(Double.NaN));
    }

    @Test
    void testBooleanMorphStringValues_false() {
        assertFalse(morpher.morph("false"));
        assertFalse(morpher.morph("no"));
        assertFalse(morpher.morph("off"));
    }

    @Test
    void testBooleanMorphStringValues_true() {
        assertTrue(morpher.morph("true"));
        assertTrue(morpher.morph("yes"));
        assertTrue(morpher.morph("on"));
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

    @Override
    protected Class<?> getMorphsToClass() {
        return Boolean.TYPE;
    }

    @BeforeEach
    void setUp() {
        morpher = new BooleanMorpher();
        morpherWithDefaultValue = new BooleanMorpher(true);
        anotherMorpher = new BooleanMorpher();
        anotherMorpherWithDefaultValue = new BooleanMorpher(false);
    }
}

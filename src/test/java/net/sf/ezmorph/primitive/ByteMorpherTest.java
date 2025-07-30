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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class ByteMorpherTest extends AbstractMorpherTestCase {
    private ByteMorpher anotherMorpher;
    private ByteMorpher anotherMorpherWithDefaultValue;
    private ByteMorpher morpher;
    private ByteMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testByteMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testByteMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testByteMorph_useDefault() {
        byte actual = morpherWithDefaultValue.morph("A");
        assertEquals(0, actual);
    }

    @Test
    void testByteMorph_useDefault_null() {
        byte actual = morpherWithDefaultValue.morph(null);
        assertEquals(0, actual);
    }

    @Test
    void testByteMorphDecimalValue_Number() {
        Double expected = 3.1416d;
        byte actual = morpher.morph(expected);
        assertEquals(3, actual);
    }

    @Test
    void testByteMorphDecimalValue_Number_outOfRange() {
        byte actual = morpher.morph(Double.MAX_VALUE);
        assertEquals(-1, actual);
    }

    @Test
    void testByteMorphDecimalValue_String() {
        String expected = "3.1416";
        byte actual = morpher.morph(expected);
        assertEquals(3, actual);
    }

    @Test
    void testByteMorphMaxValue_Number() {
        Byte expected = Byte.MAX_VALUE;
        byte actual = morpher.morph(expected);
        assertEquals(expected.byteValue(), actual);
    }

    @Test
    void testByteMorphMaxValue_String() {
        String expected = String.valueOf(Byte.MAX_VALUE);
        byte actual = morpher.morph(expected);
        assertEquals(expected, String.valueOf(actual));
    }

    @Test
    void testByteMorphMinValue_Number() {
        Byte expected = Byte.MIN_VALUE;
        byte actual = morpher.morph(expected);
        assertEquals(expected.byteValue(), actual);
    }

    @Test
    void testByteMorphMinValue_String() {
        String expected = String.valueOf(Byte.MIN_VALUE);
        byte actual = morpher.morph(expected);
        assertEquals(expected, String.valueOf(actual));
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
        return Byte.TYPE;
    }

    @Override
    protected Morpher getAnotherMorpher() {
        return anotherMorpher;
    }

    @Override
    protected Morpher getAnotherMorpherWithDefaultValue() {
        return anotherMorpherWithDefaultValue;
    }

    @BeforeEach
    void setUp() {
        morpher = new ByteMorpher();
        morpherWithDefaultValue = new ByteMorpher((byte) 0);
        anotherMorpher = new ByteMorpher();
        anotherMorpherWithDefaultValue = new ByteMorpher((byte) 1);
    }
}

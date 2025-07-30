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
class ShortMorpherTest extends AbstractMorpherTestCase {
    private ShortMorpher anotherMorpher;
    private ShortMorpher anotherMorpherWithDefaultValue;
    private ShortMorpher morpher;
    private ShortMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testShortMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testShortMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testShortMorph_useDefault() {
        String expected = "A";
        short actual = morpherWithDefaultValue.morph(expected);
        assertEquals(0, actual);
    }

    @Test
    void testShortMorph_useDefault_null() {
        short actual = morpherWithDefaultValue.morph(null);
        assertEquals(0, actual);
    }

    @Test
    void testShortMorphDecimalValue_Number() {
        short actual = morpher.morph(3.1416d);
        assertEquals(3, actual);
    }

    @Test
    void testShortMorphDecimalValue_Number_outOfRange() {
        short actual = morpher.morph(Double.MAX_VALUE);
        assertEquals(-1, actual);
    }

    @Test
    void testShortMorphDecimalValue_String() {
        String expected = "3.1416";
        short actual = morpher.morph(expected);
        assertEquals(3, actual);
    }

    @Test
    void testShortMorphMaxValue_Number() {
        Short expected = Short.MAX_VALUE;
        short actual = morpher.morph(expected);
        assertEquals(expected.shortValue(), actual);
    }

    @Test
    void testShortMorphMaxValue_String() {
        String expected = String.valueOf(Short.MAX_VALUE);
        short actual = morpher.morph(expected);
        assertEquals(expected, String.valueOf(actual));
    }

    @Test
    void testShortMorphMinValue_Number() {
        Short expected = Short.MIN_VALUE;
        short actual = morpher.morph(expected);
        assertEquals(expected.shortValue(), actual);
    }

    @Test
    void testShortMorphMinValue_String() {
        String expected = String.valueOf(Short.MIN_VALUE);
        short actual = morpher.morph(expected);
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
        return Short.TYPE;
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
        morpher = new ShortMorpher();
        morpherWithDefaultValue = new ShortMorpher((short) 0);
        anotherMorpher = new ShortMorpher();
        anotherMorpherWithDefaultValue = new ShortMorpher((short) 1);
    }
}

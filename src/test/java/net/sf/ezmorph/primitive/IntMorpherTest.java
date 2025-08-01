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
class IntMorpherTest extends AbstractMorpherTestCase {
    private IntMorpher anotherMorpher;
    private IntMorpher anotherMorpherWithDefaultValue;
    private IntMorpher morpher;
    private IntMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testIntMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testIntMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testIntMorph_useDefault() {
        String expected = "A";
        int actual = morpherWithDefaultValue.morph(expected);
        assertEquals(0, actual);
    }

    @Test
    void testIntMorph_useDefault_null() {
        int actual = morpherWithDefaultValue.morph(null);
        assertEquals(0, actual);
    }

    @Test
    void testIntMorphDecimalValue_Number() {
        int actual = morpher.morph(3.1416d);
        assertEquals(3, actual);
    }

    @Test
    void testIntMorphDecimalValue_Number_outOfRange() {
        int actual = morpher.morph(Double.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, actual);
    }

    @Test
    void testIntMorphDecimalValue_String() {
        String expected = "3.1416";
        int actual = morpher.morph(expected);
        assertEquals(3, actual);
    }

    @Test
    void testIntMorphMaxValue_Number() {
        Integer expected = Integer.MAX_VALUE;
        int actual = morpher.morph(expected);
        assertEquals(expected.intValue(), actual);
    }

    @Test
    void testIntMorphMaxValue_String() {
        String expected = String.valueOf(Integer.MAX_VALUE);
        int actual = morpher.morph(expected);
        assertEquals(expected, String.valueOf(actual));
    }

    @Test
    void testIntMorphMinValue_Number() {
        Integer expected = Integer.MIN_VALUE;
        int actual = morpher.morph(expected);
        assertEquals(expected.intValue(), actual);
    }

    @Test
    void testIntMorphMinValue_String() {
        String expected = String.valueOf(Integer.MIN_VALUE);
        int actual = morpher.morph(expected);
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
        return Integer.TYPE;
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
        morpher = new IntMorpher();
        morpherWithDefaultValue = new IntMorpher(0);
        anotherMorpher = new IntMorpher();
        anotherMorpherWithDefaultValue = new IntMorpher(1);
    }
}

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
class FloatMorpherTest extends AbstractMorpherTestCase {
    private FloatMorpher anotherMorpher;
    private FloatMorpher anotherMorpherWithDefaultValue;
    private FloatMorpher morpher;
    private FloatMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testFloatMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testFloatMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testFloatMorph_useDefault() {
        String expected = String.valueOf("A");
        float actual = morpherWithDefaultValue.morph(expected);
        assertEquals(0f, actual, 0f);
    }

    @Test
    void testFloatMorph_useDefault_null() {
        float actual = morpherWithDefaultValue.morph(null);
        assertEquals(0f, actual, 0f);
    }

    @Test
    void testFloatMorphDecimalValue_Number() {
        float actual = morpher.morph(3.1416d);
        assertEquals(3.1416f, actual, 0f);
    }

    @Test
    void testFloatMorphDecimalValue_Number_outOfRange() {
        float actual = morpher.morph(Double.MAX_VALUE);
        assertEquals(Float.POSITIVE_INFINITY, actual, 0f);
    }

    @Test
    void testFloatMorphDecimalValue_String() {
        String expected = "3.1416";
        float actual = morpher.morph(expected);
        assertEquals(3.1416f, actual, 0f);
    }

    @Test
    void testFloatMorphMaxValue_Number() {
        float expected = Float.MAX_VALUE;
        float actual = morpher.morph(expected);
        assertEquals(expected, actual, 0f);
    }

    @Test
    void testFloatMorphMaxValue_String() {
        String expected = String.valueOf(Float.MAX_VALUE);
        float actual = morpher.morph(expected);
        assertEquals(expected, String.valueOf(actual));
    }

    @Test
    void testFloatMorphMinValue_Number() {
        float expected = Float.MIN_VALUE;
        float actual = morpher.morph(expected);
        assertEquals(expected, actual, 0f);
    }

    @Test
    void testFloatMorphMinValue_String() {
        String expected = String.valueOf(Float.MIN_VALUE);
        float actual = morpher.morph(expected);
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
        return Float.TYPE;
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
        morpher = new FloatMorpher();
        morpherWithDefaultValue = new FloatMorpher(0);
        anotherMorpher = new FloatMorpher();
        anotherMorpherWithDefaultValue = new FloatMorpher(1);
    }
}

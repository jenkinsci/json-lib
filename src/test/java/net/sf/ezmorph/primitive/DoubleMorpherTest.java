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
class DoubleMorpherTest extends AbstractMorpherTestCase {
    private DoubleMorpher anotherMorpher;
    private DoubleMorpher anotherMorpherWithDefaultValue;
    private DoubleMorpher morpher;
    private DoubleMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testDoubleMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testDoubleMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testDoubleMorph_useDefault() {
        String expected = "A";
        double actual = morpherWithDefaultValue.morph(expected);
        assertEquals(0d, actual, 0d);
    }

    @Test
    void testDoubleMorph_useDefault_null() {
        double actual = morpherWithDefaultValue.morph(null);
        assertEquals(0d, actual, 0d);
    }

    @Test
    void testDoubleMorphDecimalValue_Number() {
        Double expected = 3.1416d;
        double actual = morpher.morph(expected);
        assertEquals(3.1416d, actual, 0d);
    }

    @Test
    void testDoubleMorphDecimalValue_String() {
        String expected = "3.1416";
        double actual = morpher.morph(expected);
        assertEquals(3.1416d, actual, 0d);
    }

    @Test
    void testDoubleMorphMaxValue_Number() {
        double expected = Double.MAX_VALUE;
        double actual = morpher.morph(expected);
        assertEquals(expected, actual, 0d);
    }

    @Test
    void testDoubleMorphMaxValue_String() {
        String expected = String.valueOf(Double.MAX_VALUE);
        double actual = morpher.morph(expected);
        assertEquals(expected, String.valueOf(actual));
    }

    @Test
    void testDoubleMorphMinValue_Number() {
        double expected = Double.MIN_VALUE;
        double actual = morpher.morph(expected);
        assertEquals(expected, actual, 0d);
    }

    @Test
    void testDoubleMorphMinValue_String() {
        String expected = String.valueOf(Double.MIN_VALUE);
        double actual = morpher.morph(expected);
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
        return Double.TYPE;
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
        morpher = new DoubleMorpher();
        morpherWithDefaultValue = new DoubleMorpher(0);
        anotherMorpher = new DoubleMorpher();
        anotherMorpherWithDefaultValue = new DoubleMorpher(1);
    }
}

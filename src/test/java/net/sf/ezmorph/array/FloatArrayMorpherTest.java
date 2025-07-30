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

package net.sf.ezmorph.array;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.test.ArrayAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class FloatArrayMorpherTest extends AbstractArrayMorpherTestCase {
    private FloatArrayMorpher anotherMorpher;
    private FloatArrayMorpher anotherMorpherWithDefaultValue;
    private FloatArrayMorpher morpher;
    private FloatArrayMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testMorph_floatArray() {
        float[] expected = {1f, 2f, 3f};
        float[] actual = (float[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_floatArray_threedims() {
        float[][][] expected = {{{1}, {2}}, {{3}, {4}}};
        float[][][] actual = (float[][][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_floatArray_twodims() {
        float[][] expected = {{1, 2, 3}, {4, 5, 6}};
        float[][] actual = (float[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_illegalArgument() {
        assertThrows(MorphException.class, () -> morpher.morph(""));
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testMorph_strings() {
        String[] expected = {"1", "2", "3.3"};
        float[] actual = (float[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(new float[] {1f, 2f, 3.3f}, actual);
    }

    @Test
    void testMorph_strings_twodims() {
        String[][] expected = {{"1", "2", "3.3"}, {"4", "5", "6.6"}};
        float[][] actual = (float[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(new float[][] {{1f, 2f, 3.3f}, {4f, 5f, 6.6f}}, actual);
    }

    @Test
    void testMorph_throwException() {
        assertThrows(MorphException.class, () -> new FloatArrayMorpher().morph(new String[] {null}));
    }

    @Override
    protected AbstractArrayMorpher getAnotherMorpher() {
        return anotherMorpher;
    }

    @Override
    protected AbstractArrayMorpher getAnotherMorpherWithDefaultValue() {
        return anotherMorpherWithDefaultValue;
    }

    @Override
    protected AbstractArrayMorpher getMorpher() {
        return morpher;
    }

    @Override
    protected AbstractArrayMorpher getMorpherWithDefaultValue() {
        return morpherWithDefaultValue;
    }

    @Override
    protected Class<?> getMorphsToClass() {
        return float[].class;
    }

    @BeforeEach
    void setUp() {
        morpher = new FloatArrayMorpher();
        morpherWithDefaultValue = new FloatArrayMorpher(0);
        anotherMorpher = new FloatArrayMorpher();
        anotherMorpherWithDefaultValue = new FloatArrayMorpher(1);
    }
}

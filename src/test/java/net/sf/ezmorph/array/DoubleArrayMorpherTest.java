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
class DoubleArrayMorpherTest extends AbstractArrayMorpherTestCase {
    private DoubleArrayMorpher anotherMorpher;
    private DoubleArrayMorpher anotherMorpherWithDefaultValue;
    private DoubleArrayMorpher morpher;
    private DoubleArrayMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testMorph_doubleArray() {
        double[] expected = {1d, 2d, 3d};
        double[] actual = (double[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_doubleArray_threedims() {
        double[][][] expected = {{{1}, {2}}, {{3}, {4}}};
        double[][][] actual = (double[][][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_doubleArray_twodims() {
        double[][] expected = {{1, 2, 3}, {4, 5, 6}};
        double[][] actual = (double[][]) morpher.morph(expected);
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
        double[] actual = (double[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(new double[] {1d, 2d, 3.3d}, actual);
    }

    @Test
    void testMorph_strings_twodims() {
        String[][] expected = {{"1", "2", "3.3"}, {"4", "5", "6.6"}};
        double[][] actual = (double[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(new double[][] {{1d, 2d, 3.3d}, {4d, 5d, 6.6}}, actual);
    }

    @Test
    void testMorph_throwException() {
        assertThrows(MorphException.class, () -> new DoubleArrayMorpher().morph(new String[] {null}));
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
        return double[].class;
    }

    @BeforeEach
    void setUp() {
        morpher = new DoubleArrayMorpher();
        morpherWithDefaultValue = new DoubleArrayMorpher(0);
        anotherMorpher = new DoubleArrayMorpher();
        anotherMorpherWithDefaultValue = new DoubleArrayMorpher(1);
    }
}

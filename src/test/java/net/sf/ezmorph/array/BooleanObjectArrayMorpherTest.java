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
class BooleanObjectArrayMorpherTest extends AbstractArrayMorpherTestCase {

    private BooleanObjectArrayMorpher anotherMorpher;
    private BooleanObjectArrayMorpher anotherMorpherWithDefaultValue;
    private BooleanObjectArrayMorpher morpher;
    private BooleanObjectArrayMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testMorph_booleanArray() {
        boolean[] expected = {true, false};
        Boolean[] actual = (Boolean[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_BooleanArray() {
        Boolean[] expected = {Boolean.TRUE, Boolean.FALSE};
        Boolean[] actual = (Boolean[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_booleanArray_threedims() {
        boolean[][][] expected = {{{true}, {false}}, {{true}, {false}}};
        Boolean[][][] actual = (Boolean[][][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_BooleanArray_threedims() {
        Boolean[][][] expected = {{{Boolean.TRUE}, {Boolean.FALSE}}, {{Boolean.TRUE}, {Boolean.FALSE}}};
        Boolean[][][] actual = (Boolean[][][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_booleanArray_twodims() {
        boolean[][] expected = {{true, false}, {true, false}};
        Boolean[][] actual = (Boolean[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_BooleanArray_twodims() {
        Boolean[][] expected = {{Boolean.TRUE, Boolean.FALSE}, {Boolean.TRUE, Boolean.FALSE}};
        Boolean[][] actual = (Boolean[][]) morpher.morph(expected);
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
    void testMorph_StringArray_Boolean_default() {
        Boolean[] expected = {Boolean.TRUE, Boolean.TRUE};
        morpher = new BooleanObjectArrayMorpher(Boolean.TRUE);
        Boolean[] actual = (Boolean[]) morpher.morph(new String[] {"A", "B"});
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_StringArray_null_default() {
        Boolean[] expected = {null, null};
        morpher = new BooleanObjectArrayMorpher(null);
        Boolean[] actual = (Boolean[]) morpher.morph(new String[] {"A", "B"});
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_strings() {
        String[] expected = {"true", "yes", "on", "false", "no", "off"};
        Boolean[] actual = (Boolean[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(
                new Boolean[] {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE},
                actual);
    }

    @Test
    void testMorph_strings_twodims() {
        String[][] expected = {{"true", "yes", "on"}, {"false", "no", "off"}};
        Boolean[][] actual = (Boolean[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(
                new Boolean[][] {
                    {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE}, {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}
                },
                actual);
    }

    @Test
    void testMorph_throwException() {
        assertThrows(MorphException.class, () -> new BooleanObjectArrayMorpher().morph(new String[] {"A"}));
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
        return Boolean[].class;
    }

    @BeforeEach
    void setUp() {
        morpher = new BooleanObjectArrayMorpher();
        morpherWithDefaultValue = new BooleanObjectArrayMorpher(Boolean.TRUE);
        anotherMorpher = new BooleanObjectArrayMorpher();
        anotherMorpherWithDefaultValue = new BooleanObjectArrayMorpher(Boolean.FALSE);
    }
}

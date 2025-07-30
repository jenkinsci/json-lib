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
class CharacterObjectArrayMorpherTest extends AbstractArrayMorpherTestCase {

    private CharacterObjectArrayMorpher anotherMorpher;
    private CharacterObjectArrayMorpher anotherMorpherWithDefaultValue;
    private CharacterObjectArrayMorpher morpher;
    private CharacterObjectArrayMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testMorph_CharacterArray() {
        Character[] expected = {'A', 'B'};
        Character[] actual = (Character[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_CharacterArray_threedims() {
        Character[][][] expected = {{{'A'}, {'B'}}, {{'A'}, {'B'}}};
        Character[][][] actual = (Character[][][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_CharacterArray_twodims() {
        Character[][] expected = {{'A', 'B'}, {'A', 'B'}};
        Character[][] actual = (Character[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_charArray() {
        char[] input = {'A', 'B'};
        Character[] actual = (Character[]) morpher.morph(input);
        ArrayAssertions.assertEquals(new Character[] {'A', 'B'}, actual);
    }

    @Test
    void testMorph_charArray_threedims() {
        char[][][] input = {{{'A'}}, {{'B'}}};
        Character[][][] actual = (Character[][][]) morpher.morph(input);
        ArrayAssertions.assertEquals(new Character[][][] {{{'A'}}, {{'B'}}}, actual);
    }

    @Test
    void testMorph_charArray_twodims() {
        char[][] input = {{'A'}, {'B'}};
        Character[][] actual = (Character[][]) morpher.morph(input);
        ArrayAssertions.assertEquals(new Character[][] {{'A'}, {'B'}}, actual);
    }

    @Test
    void testMorph_illegalArgument() {
        assertThrows(MorphException.class, () -> morpher.morph(""));
    }

    @Test
    void testMorph_IntegerArray_default() {
        Character[] expected = {'A', 'A'};
        morpher = new CharacterObjectArrayMorpher('A');
        Character[] actual = (Character[]) morpher.morph(new Integer[] {null, null});
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testMorph_StringArray_null_default() {
        Character[] expected = {null, null};
        morpher = new CharacterObjectArrayMorpher(null);
        Character[] actual = (Character[]) morpher.morph(new String[] {"A", "B"});
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_strings() {
        String[] expected = {"A", "B"};
        Character[] actual = (Character[]) morpher.morph(expected);
        ArrayAssertions.assertEquals(new Character[] {'A', 'B'}, actual);
    }

    @Test
    void testMorph_strings_twodims() {
        String[][] expected = {{"A"}, {"B"}};
        Character[][] actual = (Character[][]) morpher.morph(expected);
        ArrayAssertions.assertEquals(new Character[][] {{'A'}, {'B'}}, actual);
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
        return Character[].class;
    }

    @BeforeEach
    void setUp() {
        morpher = new CharacterObjectArrayMorpher();
        morpherWithDefaultValue = new CharacterObjectArrayMorpher('A');
        anotherMorpher = new CharacterObjectArrayMorpher();
        anotherMorpherWithDefaultValue = new CharacterObjectArrayMorpher('B');
    }
}

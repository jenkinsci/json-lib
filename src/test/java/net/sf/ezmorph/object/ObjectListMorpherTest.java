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

package net.sf.ezmorph.object;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.test.ArrayAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class ObjectListMorpherTest extends AbstractObjectMorpherTestCase {
    private ObjectListMorpher anotherMorpher;
    private ObjectListMorpher anotherMorpherWithDefaultValue;
    private ObjectListMorpher morpher;
    private ObjectListMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testMorph_illegalArgument() {
        // argument is not a list
        assertThrows(MorphException.class, () -> morpher.morph(""));
    }

    @Test
    void testMorph_IntegerList() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        List<?> actual = (List<?>) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testMorph_NullList() {
        List<Object> expected = new ArrayList<>();
        expected.add(null);
        expected.add(null);
        expected.add(null);
        List<?> actual = (List<?>) morpher.morph(expected);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_NullList_withDefaultValue() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);
        expected.add(0);
        List<Object> input = new ArrayList<>();
        input.add(null);
        input.add(null);
        input.add(null);
        List<?> actual = (List<?>) morpherWithDefaultValue.morph(input);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_StringList() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("2");
        input.add("3");
        List<?> actual = (List<?>) morpher.morph(input);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testObjectListMorpher_illegalMorpher_noMorphMethod() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ObjectListMorpher(new Morpher() {
                    @Override
                    public Class morphsTo() {
                        return Object.class;
                    }

                    @Override
                    public boolean supports(Class clazz) {
                        return false;
                    }
                }));
    }

    @Test
    void testObjectListMorpher_illegalMorpher_nullMorpher() {
        assertThrows(IllegalArgumentException.class, () -> new ObjectListMorpher(null));
    }

    @Test
    void testObjectListMorpher_illegalMorpher_supportsList() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ObjectListMorpher(new Morpher() {
                    @Override
                    public Class morphsTo() {
                        return List.class;
                    }

                    @Override
                    public boolean supports(Class clazz) {
                        return false;
                    }
                }));
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

    @BeforeEach
    void setUp() {
        morpher = new ObjectListMorpher(new NumberMorpher(Integer.class));
        morpherWithDefaultValue = new ObjectListMorpher(new NumberMorpher(Integer.class, 0), 0);
        anotherMorpher = new ObjectListMorpher(new NumberMorpher(Integer.class));
        anotherMorpherWithDefaultValue = new ObjectListMorpher(new NumberMorpher(Integer.class, 1), 1);
    }
}

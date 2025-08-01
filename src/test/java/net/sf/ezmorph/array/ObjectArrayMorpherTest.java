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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Array;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import net.sf.ezmorph.object.StringMorpher;
import net.sf.ezmorph.test.ArrayAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class ObjectArrayMorpherTest {
    private ObjectArrayMorpher anotherMorpher;
    private ObjectArrayMorpher morpher;

    // -----------------------------------------------------------------------

    public ObjectArrayMorpher getAnotherMorpher() {
        return anotherMorpher;
    }

    public ObjectArrayMorpher getMorpher() {
        return morpher;
    }

    @Test
    void testEquals_another_Morpher() {
        assertNotEquals(getMorpher(), getAnotherMorpher());
    }

    @Test
    void testEquals_different_morpher() {
        assertNotEquals(
                new Morpher() {
                    @Override
                    public Class morphsTo() {
                        return null;
                    }

                    @Override
                    public boolean supports(Class clazz) {
                        return false;
                    }
                },
                getMorpher());
    }

    @Test
    void testEquals_null() {
        assertNotNull(getMorpher());
    }

    @Test
    void testEquals_same_morpher() {
        assertEquals(getMorpher(), getMorpher());
        assertEquals(getAnotherMorpher(), getAnotherMorpher());
    }

    @Test
    void testHashCode_same_morpher() {
        assertEquals(getMorpher().hashCode(), getMorpher().hashCode());
        assertEquals(getAnotherMorpher().hashCode(), getAnotherMorpher().hashCode());
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
    void testMorph_onedim() {
        Object[] input = new Object[] {1, Boolean.TRUE};
        String[] expected = new String[] {"1", "true"};
        String[] actual = (String[]) morpher.morph(input);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_threedims() {
        Object[][][] input = new Object[][][] {{{1, Boolean.TRUE}}, {{'A'}}};
        String[][][] expected = new String[][][] {{{"1", "true"}}, {{"A"}}};
        String[][][] actual = (String[][][]) morpher.morph(input);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_twodims() {
        Object[][] input = new Object[][] {{1, Boolean.TRUE}, {'A'}};
        String[][] expected = new String[][] {{"1", "true"}, {"A"}};
        String[][] actual = (String[][]) morpher.morph(input);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorph_twodims_reflection() {
        Object input = Array.newInstance(Object.class, new int[] {2, 2});
        Object[] a = new Object[] {1, Boolean.TRUE};
        Object[] b = new Object[] {'A'};
        Array.set(input, 0, a);
        Array.set(input, 1, b);

        String[][] expected = new String[][] {{"1", "true"}, {"A"}};
        String[][] actual = (String[][]) morpher.morph(input);
        ArrayAssertions.assertEquals(expected, actual);
    }

    @Test
    void testMorphsTo() {
        assertEquals(String[].class, getMorpher().morphsTo());
    }

    @Test
    void testObjectArrayMorpher_illegalMorpher_noMorphMethod() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ObjectArrayMorpher(new Morpher() {
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
    void testObjectArrayMorpher_illegalMorpher_nullMorpher() {
        assertThrows(IllegalArgumentException.class, () -> new ObjectArrayMorpher(null));
    }

    @Test
    void testObjectArrayMorpher_illegalMorpher_supportsArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ObjectArrayMorpher(new Morpher() {
                    @Override
                    public Class morphsTo() {
                        return Object[].class;
                    }

                    @Override
                    public boolean supports(Class clazz) {
                        return false;
                    }
                }));
    }

    @BeforeEach
    void setUp() {
        morpher = new ObjectArrayMorpher(StringMorpher.getInstance());
        anotherMorpher = new ObjectArrayMorpher(IdentityObjectMorpher.getInstance());
    }
}

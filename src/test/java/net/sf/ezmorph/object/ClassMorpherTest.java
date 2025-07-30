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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.sf.ezmorph.MorphException;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class ClassMorpherTest {

    private final ClassMorpher morpher = ClassMorpher.getInstance();

    // -----------------------------------------------------------------------

    @Test
    void testEquals() {
        assertEquals(ClassMorpher.getInstance(), ClassMorpher.getInstance());
        assertNotEquals(StringMorpher.getInstance(), ClassMorpher.getInstance());
    }

    @Test
    void testHashCode() {
        assertEquals(
                ClassMorpher.getInstance().hashCode(),
                ClassMorpher.getInstance().hashCode());
        assertNotEquals(
                StringMorpher.getInstance().hashCode(),
                ClassMorpher.getInstance().hashCode());
        assertTrue(ClassMorpher.getInstance().hashCode()
                != StringMorpher.getInstance().hashCode());
    }

    @Test
    void testMorph() {
        Class<?> actual = (Class<?>) morpher.morph("java.lang.Object");
        assertEquals(Object.class, actual);
    }

    @Test
    void testMorph_array() {
        assertThrows(MorphException.class, () -> morpher.morph(new boolean[] {true, false}));
    }

    @Test
    void testMorph_arrayClass() {
        Class<?> actual = (Class<?>) morpher.morph("[I");
        assertEquals(int[].class, actual);
    }

    @Test
    void testMorph_class() {
        Class<?> actual = (Class<?>) morpher.morph(Object.class);
        assertEquals(Object.class, actual);
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testMorph_unknownClassname() {
        assertThrows(MorphException.class, () -> morpher.morph("bogusClass.I.do.not.exist"));
    }

    @Test
    void testMorph_withtoString() {
        Class<?> actual = (Class<?>) morpher.morph(new MyClass());
        assertEquals(MyClass.class, actual);
    }

    public static class MyClass {
        @Override
        public String toString() {
            return MyClass.class.getName();
        }
    }
}

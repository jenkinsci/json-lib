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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.sf.ezmorph.MorphException;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class StringMorpherTest {
    private final StringMorpher morpher = StringMorpher.getInstance();

    // -----------------------------------------------------------------------

    @Test
    void testMorph_array() {
        assertThrows(MorphException.class, () -> morpher.morph(new boolean[] {true, false}));
    }

    @Test
    void testMorph_boolean() {
        String expected = "true";
        String actual = (String) morpher.morph(Boolean.TRUE);
        assertEquals(expected, actual);
    }

    @Test
    void testMorph_noConversion() {
        String expected = "true";
        String actual = (String) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }
}

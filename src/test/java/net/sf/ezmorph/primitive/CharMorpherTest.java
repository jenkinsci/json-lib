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
class CharMorpherTest extends AbstractMorpherTestCase {
    private CharMorpher anotherMorpher;
    private CharMorpher anotherMorpherWithDefaultValue;
    private CharMorpher morpher;
    private CharMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testCharMorph() {
        char actual = morpher.morph("A");
        assertEquals('A', actual);
    }

    @Test
    void testCharMorph_throwException_emptyString() {
        assertThrows(MorphException.class, () -> morpher.morph(""));
    }

    @Test
    void testCharMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testCharMorph_useDefault() {
        char actual = morpherWithDefaultValue.morph("");
        assertEquals('A', actual);
    }

    @Test
    void testCharMorph_useDefault_null() {
        char actual = morpherWithDefaultValue.morph(null);
        assertEquals('A', actual);
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
        return Character.TYPE;
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
        morpher = new CharMorpher();
        morpherWithDefaultValue = new CharMorpher('A');
        anotherMorpher = new CharMorpher();
        anotherMorpherWithDefaultValue = new CharMorpher('B');
    }
}

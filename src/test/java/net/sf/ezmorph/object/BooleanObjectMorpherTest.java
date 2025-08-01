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
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class BooleanObjectMorpherTest extends AbstractObjectMorpherTestCase {

    private BooleanObjectMorpher anotherMorpher;
    private BooleanObjectMorpher anotherMorpherWithDefaultValue;
    private BooleanObjectMorpher morpher;
    private BooleanObjectMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testBooleanMorph_noConversion() {
        Boolean actual = (Boolean) morpherWithDefaultValue.morph(Boolean.TRUE);
        assertEquals(Boolean.TRUE, actual);
    }

    @Test
    void testBooleanMorph_throwException() {
        assertThrows(MorphException.class, () -> morpher.morph("A"));
    }

    @Test
    void testBooleanMorph_throwException_null() {
        assertThrows(MorphException.class, () -> morpher.morph(null));
    }

    @Test
    void testBooleanMorph_useDefault() {
        Boolean actual = (Boolean) morpherWithDefaultValue.morph("A");
        assertEquals(Boolean.TRUE, actual);
    }

    @Test
    void testBooleanMorph_useDefault_null() {
        Boolean actual = (Boolean) morpherWithDefaultValue.morph(null);
        assertEquals(Boolean.TRUE, actual);
    }

    @Test
    void testBooleanMorphStringValues_false() {
        assertEquals(Boolean.FALSE, morpher.morph("false"));
        assertEquals(Boolean.FALSE, morpher.morph("no"));
        assertEquals(Boolean.FALSE, morpher.morph("off"));
    }

    @Test
    void testBooleanMorphStringValues_true() {
        assertEquals(Boolean.TRUE, morpher.morph("true"));
        assertEquals(Boolean.TRUE, morpher.morph("yes"));
        assertEquals(Boolean.TRUE, morpher.morph("on"));
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
        morpher = new BooleanObjectMorpher();
        morpherWithDefaultValue = new BooleanObjectMorpher(Boolean.TRUE);
        anotherMorpher = new BooleanObjectMorpher();
        anotherMorpherWithDefaultValue = new BooleanObjectMorpher(Boolean.FALSE);
    }
}

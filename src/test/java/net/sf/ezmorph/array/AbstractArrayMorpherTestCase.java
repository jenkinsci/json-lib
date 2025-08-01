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

import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
abstract class AbstractArrayMorpherTestCase {

    @Test
    void testEquals_another_Morpher() {
        assertNotEquals(getMorpherWithDefaultValue(), getAnotherMorpherWithDefaultValue());
        assertEquals(getMorpher(), getAnotherMorpher());
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
    void testEquals_morpher_withDefaultValue() {
        assertNotEquals(getMorpher(), getMorpherWithDefaultValue());
    }

    @Test
    void testEquals_null() {
        assertNotNull(getMorpher());
    }

    @Test
    void testEquals_same_morpher() {
        assertEquals(getMorpher(), getMorpher());
        assertEquals(getMorpherWithDefaultValue(), getMorpherWithDefaultValue());
    }

    @Test
    void testHashCode_morpher_withDefaultValue() {
        assertNotEquals(getMorpher().hashCode(), getMorpherWithDefaultValue().hashCode());
    }

    @Test
    void testHashCode_same_morpher() {
        assertEquals(getMorpher().hashCode(), getMorpher().hashCode());
        assertEquals(
                getMorpherWithDefaultValue().hashCode(),
                getMorpherWithDefaultValue().hashCode());
    }

    @Test
    void testMorphsTo() {
        assertEquals(getMorphsToClass(), getMorpher().morphsTo());
    }

    // -----------------------------------------------------------------------

    protected abstract AbstractArrayMorpher getAnotherMorpher();

    protected abstract AbstractArrayMorpher getAnotherMorpherWithDefaultValue();

    protected abstract AbstractArrayMorpher getMorpher();

    protected abstract AbstractArrayMorpher getMorpherWithDefaultValue();

    protected abstract Class<?> getMorphsToClass();
}

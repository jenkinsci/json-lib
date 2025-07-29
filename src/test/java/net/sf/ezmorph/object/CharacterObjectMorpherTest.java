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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public class CharacterObjectMorpherTest extends AbstractObjectMorpherTestCase {
    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CharacterObjectMorpherTest.class);
        suite.setName("CharacterObjectMorpher Tests");
        return suite;
    }

    private CharacterObjectMorpher anotherMorpher;
    private CharacterObjectMorpher anotherMorpherWithDefaultValue;
    private CharacterObjectMorpher morpher;
    private CharacterObjectMorpher morpherWithDefaultValue;

    public CharacterObjectMorpherTest(String name) {
        super(name);
    }

    // -----------------------------------------------------------------------

    public void testCharMorph() {
        String expected = "A";
        Character actual = (Character) new CharacterObjectMorpher().morph(expected);
        assertEquals((Character) 'A', actual);
    }

    public void testCharMorph_noConversion() {
        Character expected = 'A';
        Character actual = (Character) new CharacterObjectMorpher().morph(expected);
        assertEquals(expected, actual);
        assertSame(expected, actual);
    }

    public void testCharMorph_throwException_emptyString() {
        try {
            new CharacterObjectMorpher().morph("");
            fail("Should have thrown an Exception");
        } catch (MorphException expected) {
            // ok
        }
    }

    public void testCharMorph_throwException_null() {
        try {
            new CharacterObjectMorpher().morph(null);
            fail("Should have thrown an Exception");
        } catch (MorphException expected) {
            // ok
        }
    }

    public void testCharMorph_useDefault() {
        String expected = "";
        Character actual = (Character) new CharacterObjectMorpher('A').morph(expected);
        assertEquals((Character) 'A', actual);
    }

    public void testCharMorph_useDefault_null() {
        Character actual = (Character) new CharacterObjectMorpher('A').morph(null);
        assertEquals((Character) 'A', actual);
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

    @Override
    protected void setUp() throws Exception {
        morpher = new CharacterObjectMorpher();
        morpherWithDefaultValue = new CharacterObjectMorpher('A');
        anotherMorpher = new CharacterObjectMorpher();
        anotherMorpherWithDefaultValue = new CharacterObjectMorpher('B');
    }
}

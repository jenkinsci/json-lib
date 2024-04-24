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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public class DoubleMorpherTest extends AbstractMorpherTestCase {
    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DoubleMorpherTest.class);
        suite.setName("DoubleMorpher Tests");
        return suite;
    }

    private Morpher anotherMorpher;
    private Morpher anotherMorpherWithDefaultValue;
    private Morpher morpher;
    private Morpher morpherWithDefaultValue;

    public DoubleMorpherTest(String name) {
        super(name);
    }

    // -----------------------------------------------------------------------

    public void testDoubleMorph_throwException() {
        try {
            ((DoubleMorpher) getMorpher()).morph(String.valueOf("A"));
            fail("Should have thrown an Exception");
        } catch (MorphException expected) {
            // ok
        }
    }

    public void testDoubleMorph_throwException_null() {
        try {
            ((DoubleMorpher) getMorpher()).morph(null);
            fail("Should have thrown an Exception");
        } catch (MorphException expected) {
            // ok
        }
    }

    public void testDoubleMorph_useDefault() {
        String expected = String.valueOf("A");
        double actual = ((DoubleMorpher) getMorpherWithDefaultValue()).morph(expected);
        assertEquals(0d, actual, 0d);
    }

    public void testDoubleMorph_useDefault_null() {
        double actual = ((DoubleMorpher) getMorpherWithDefaultValue()).morph(null);
        assertEquals(0d, actual, 0d);
    }

    public void testDoubleMorphDecimalValue_Number() {
        Double expected = new Double(3.1416d);
        double actual = ((DoubleMorpher) getMorpher()).morph(expected);
        assertEquals(3.1416d, actual, 0d);
    }

    public void testDoubleMorphDecimalValue_String() {
        String expected = "3.1416";
        double actual = ((DoubleMorpher) getMorpher()).morph(expected);
        assertEquals(3.1416d, actual, 0d);
    }

    public void testDoubleMorphMaxValue_Number() {
        Double expected = new Double(Double.MAX_VALUE);
        double actual = ((DoubleMorpher) getMorpher()).morph(expected);
        assertEquals(expected.doubleValue(), actual, 0d);
    }

    public void testDoubleMorphMaxValue_String() {
        String expected = String.valueOf(new Double(Double.MAX_VALUE));
        double actual = ((DoubleMorpher) getMorpher()).morph(expected);
        assertEquals(expected, String.valueOf(actual));
    }

    public void testDoubleMorphMinValue_Number() {
        Double expected = new Double(Double.MIN_VALUE);
        double actual = ((DoubleMorpher) getMorpher()).morph(expected);
        assertEquals(expected.doubleValue(), actual, 0d);
    }

    public void testDoubleMorphMinValue_String() {
        String expected = String.valueOf(new Double(Double.MIN_VALUE));
        double actual = ((DoubleMorpher) getMorpher()).morph(expected);
        assertEquals(expected, String.valueOf(actual));
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
    protected Class getMorphsToClass() {
        return Double.TYPE;
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
    protected void setUp() throws Exception {
        morpher = new DoubleMorpher();
        morpherWithDefaultValue = new DoubleMorpher(0);
        anotherMorpher = new DoubleMorpher();
        anotherMorpherWithDefaultValue = new DoubleMorpher(1);
    }
}

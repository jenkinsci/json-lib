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

package net.sf.ezmorph.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class ArrayAssertionsTest {
    @Test
    void testAssertEquals_null_null() {
        // assert that original contract is not borken
        ArrayAssertions.assertEquals(null, (Object) null);
    }

    @Test
    void testAssertEquals_actuals_is_null() {
        Object[] expecteds = new Object[] {new Object(), new Object()};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, (Object[]) null));
    }

    @Test
    void testAssertEquals_different_length() {
        Object[] expecteds = new Object[] {new Object(), new Object()};
        Object[] actuals = new Object[] {new Object()};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_expecteds_is_null() {
        Object[] actuals = new Object[] {new Object(), new Object()};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals((Object[]) null, actuals));
    }

    @Test
    void testAssertEquals_multi_Object_Object_nulls() {
        Object[][] expecteds = new Object[][] {{null}, {null}};
        Object[][] actuals = new Object[][] {{null}, {null}};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }

    @Test
    void testAssertEquals_null_elements() {
        Object[] expecteds = new Object[] {null};
        Object[] actuals = new Object[] {new Object()};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_null_elements_2() {
        Object[] expecteds = new Object[] {new Object()};
        Object[] actuals = new Object[] {null};
        assertThrows(AssertionFailedError.class, () -> ArrayAssertions.assertEquals(expecteds, actuals));
    }

    @Test
    void testAssertEquals_Object_Object_nulls() {
        Object[] expecteds = new Object[] {null};
        Object[] actuals = new Object[] {null};
        ArrayAssertions.assertEquals(expecteds, actuals);
    }
}

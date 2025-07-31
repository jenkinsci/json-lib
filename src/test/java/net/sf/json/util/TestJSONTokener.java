/*
 * Copyright 2002-2009 the original author or authors.
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

package net.sf.json.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.sf.json.JSONException;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONTokener {
    @Test
    void testDehexchar() {
        assertEquals(0, JSONTokener.dehexchar('0'));
        assertEquals(1, JSONTokener.dehexchar('1'));
        assertEquals(2, JSONTokener.dehexchar('2'));
        assertEquals(3, JSONTokener.dehexchar('3'));
        assertEquals(4, JSONTokener.dehexchar('4'));
        assertEquals(5, JSONTokener.dehexchar('5'));
        assertEquals(6, JSONTokener.dehexchar('6'));
        assertEquals(7, JSONTokener.dehexchar('7'));
        assertEquals(8, JSONTokener.dehexchar('8'));
        assertEquals(9, JSONTokener.dehexchar('9'));

        assertEquals(10, JSONTokener.dehexchar('a'));
        assertEquals(10, JSONTokener.dehexchar('A'));
        assertEquals(11, JSONTokener.dehexchar('b'));
        assertEquals(11, JSONTokener.dehexchar('B'));
        assertEquals(12, JSONTokener.dehexchar('c'));
        assertEquals(12, JSONTokener.dehexchar('C'));
        assertEquals(13, JSONTokener.dehexchar('d'));
        assertEquals(13, JSONTokener.dehexchar('D'));
        assertEquals(14, JSONTokener.dehexchar('e'));
        assertEquals(14, JSONTokener.dehexchar('E'));
        assertEquals(15, JSONTokener.dehexchar('f'));
        assertEquals(15, JSONTokener.dehexchar('F'));
    }

    @Test
    void testLength() {
        assertEquals(0, new JSONTokener(null).length());
        assertEquals(0, new JSONTokener("").length());
        assertEquals(2, new JSONTokener("[]").length());
    }

    @Test
    void testNextChar() {
        JSONTokener tok = new JSONTokener("abc");
        assertEquals('a', tok.next('a'));
        assertThrows(JSONException.class, () -> tok.next('e'));
    }

    @Test
    void testStartsWith() {
        assertFalse(new JSONTokener("").startsWith("null"));
        assertFalse(new JSONTokener("n").startsWith("null"));
        assertFalse(new JSONTokener("nu").startsWith("null"));
        assertFalse(new JSONTokener("nul").startsWith("null"));
        assertTrue(new JSONTokener("null").startsWith("null"));
        assertTrue(new JSONTokener("nulll").startsWith("null"));
        assertFalse(new JSONTokener("nn").startsWith("null"));
        assertFalse(new JSONTokener("nnu").startsWith("null"));
        assertFalse(new JSONTokener("nnul").startsWith("null"));
        assertFalse(new JSONTokener("nnull").startsWith("null"));
        assertFalse(new JSONTokener("nnulll").startsWith("null"));
    }

    @Test
    void testReset() {
        JSONTokener tok = new JSONTokener("abc");
        tok.next();
        tok.next();
        assertEquals('c', tok.next());
        tok.reset();
        assertEquals('a', tok.next());
    }
}

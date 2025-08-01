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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public class TestJSONUtils {
    @Test
    void testDoubleToString_infinite() {
        assertEquals("null", JSONUtils.doubleToString(Double.POSITIVE_INFINITY));
    }

    @Test
    void testDoubleToString_nan() {
        assertEquals("null", JSONUtils.doubleToString(Double.NaN));
    }

    @Test
    void testDoubleToString_trailingZeros() {
        assertEquals("200", JSONUtils.doubleToString(200.00000));
    }

    @Test
    void testIsArray() {
        assertTrue(JSONUtils.isArray(new Object[0]));
        assertTrue(JSONUtils.isArray(new boolean[0]));
        assertTrue(JSONUtils.isArray(new byte[0]));
        assertTrue(JSONUtils.isArray(new char[0]));
        assertTrue(JSONUtils.isArray(new short[0]));
        assertTrue(JSONUtils.isArray(new int[0]));
        assertTrue(JSONUtils.isArray(new long[0]));
        assertTrue(JSONUtils.isArray(new float[0]));
        assertTrue(JSONUtils.isArray(new double[0]));

        // two dimensions
        assertTrue(JSONUtils.isArray(new Object[0][0]));
        assertTrue(JSONUtils.isArray(new boolean[0][0]));
        assertTrue(JSONUtils.isArray(new byte[0][0]));
        assertTrue(JSONUtils.isArray(new char[0][0]));
        assertTrue(JSONUtils.isArray(new short[0][0]));
        assertTrue(JSONUtils.isArray(new int[0][0]));
        assertTrue(JSONUtils.isArray(new long[0][0]));
        assertTrue(JSONUtils.isArray(new float[0][0]));
        assertTrue(JSONUtils.isArray(new double[0][0]));

        // collections
        assertTrue(JSONUtils.isArray(Collections.EMPTY_SET));
        assertTrue(JSONUtils.isArray(Collections.EMPTY_LIST));

        // jsonArray
        assertTrue(JSONUtils.isArray(new JSONArray()));
    }

    @Test
    void testNumberToString_null() {
        assertThrows(JSONException.class, () -> JSONUtils.numberToString(null));
    }

    @Test
    void testQuote_emptyString() {
        assertEquals("\"\"", JSONUtils.quote(""));
    }

    @Test
    void testQuote_escapeChars() {
        assertEquals("\"\\b\\t\\n\\r\\f\"", JSONUtils.quote("\b\t\n\r\f"));
    }

    @Test
    void testQuote_nullString() {
        assertEquals("\"\"", JSONUtils.quote(null));
    }

    @Test
    void testStripQuotes_singleChar_doubleQuote() {
        String quoted = "\"";
        String actual = JSONUtils.stripQuotes(quoted);
        assertEquals(quoted, actual);
    }

    @Test
    void testStripQuotes_singleChar_singleQuote() {
        String quoted = "'";
        String actual = JSONUtils.stripQuotes(quoted);
        assertEquals(quoted, actual);
    }

    @Test
    void testStripQuotes_twoChars_doubleQuote() {
        String quoted = "\"\"";
        String actual = JSONUtils.stripQuotes(quoted);
        assertEquals("", actual);
    }

    @Test
    void testStripQuotes_twoChars_singleQuote() {
        String quoted = "''";
        String actual = JSONUtils.stripQuotes(quoted);
        assertEquals("", actual);
    }

    @Test
    void testValidity_inifiniteDouble() {
        assertThrows(JSONException.class, () -> JSONUtils.testValidity(Double.POSITIVE_INFINITY));
    }

    @Test
    void testValidity_inifiniteFloat() {
        assertThrows(JSONException.class, () -> JSONUtils.testValidity(Float.POSITIVE_INFINITY));
    }

    @Test
    void testValidity_nanDouble() {
        assertThrows(JSONException.class, () -> JSONUtils.testValidity(Double.NaN));
    }

    @Test
    void testValidity_nanFloat() {
        assertThrows(JSONException.class, () -> JSONUtils.testValidity(Float.NaN));
    }
}

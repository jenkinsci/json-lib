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

package net.sf.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.sample.JsonEventAdpater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONArrayEvents {

    private JsonConfig jsonConfig;
    private JsonEventAdpater jsonEventAdpater;

    @Test
    void testFromObject_array() {
        JSONArray.fromObject(new Object[] {"1", "2", "3"}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_error() {
        assertThrows(JSONException.class, () -> JSONArray.fromObject("{}", jsonConfig));
        assertEquals(1, jsonEventAdpater.getError());
        assertEquals(0, jsonEventAdpater.getWarning());
        assertEquals(0, jsonEventAdpater.getArrayStart());
        assertEquals(0, jsonEventAdpater.getArrayEnd());
        assertEquals(0, jsonEventAdpater.getObjectStart());
        assertEquals(0, jsonEventAdpater.getObjectEnd());
        assertEquals(0, jsonEventAdpater.getElementAdded());
        assertEquals(0, jsonEventAdpater.getPropertySet());
    }

    @Test
    void testFromObject_JSONArray() {
        JSONArray array = new JSONArray().element("1").element("2").element("3");
        JSONArray.fromObject(array, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_list() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        JSONArray.fromObject(list, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_boolean() {
        JSONArray.fromObject(new boolean[] {true, false, true}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_byte() {
        JSONArray.fromObject(new byte[] {(byte) 1, (byte) 2, (byte) 3}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_double() {
        JSONArray.fromObject(new double[] {1d, 2d, 3d}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_float() {
        JSONArray.fromObject(new float[] {1f, 2f, 3f}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_int() {
        JSONArray.fromObject(new int[] {1, 2, 3}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_long() {
        JSONArray.fromObject(new long[] {1L, 2L, 3L}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_primitive_short() {
        JSONArray.fromObject(new short[] {(short) 1, (short) 2, (short) 3}, jsonConfig);
        assertEvents();
    }

    @Test
    void testFromObject_string() {
        JSONArray.fromObject("[1,2,3]", jsonConfig);
        assertEvents();
    }

    @BeforeEach
    void setUp() {
        jsonEventAdpater = new JsonEventAdpater();
        jsonConfig = new JsonConfig();
        jsonConfig.addJsonEventListener(jsonEventAdpater);
        jsonConfig.enableEventTriggering();
    }

    private void assertEvents() {
        assertEquals(0, jsonEventAdpater.getError());
        assertEquals(0, jsonEventAdpater.getWarning());
        assertEquals(1, jsonEventAdpater.getArrayStart());
        assertEquals(1, jsonEventAdpater.getArrayEnd());
        assertEquals(0, jsonEventAdpater.getObjectStart());
        assertEquals(0, jsonEventAdpater.getObjectEnd());
        assertEquals(3, jsonEventAdpater.getElementAdded());
        assertEquals(0, jsonEventAdpater.getPropertySet());
    }
}

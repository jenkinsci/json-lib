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

import java.io.StringWriter;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONBuilder {
    @Test
    void testCreateArray() {
        StringWriter w = new StringWriter();
        new JSONBuilder(w)
                .array()
                .value(true)
                .value(1.1d)
                .value(2L)
                .value("text")
                .endArray();
        assertEquals("[true,1.1,2,\"text\"]", w.toString());
    }

    @Test
    void testCreateEmptyArray() {
        StringWriter w = new StringWriter();
        new JSONBuilder(w).array().endArray();
        assertEquals("[]", w.toString());
    }

    @Test
    void testCreateEmptyArrayWithNullObjects() {
        StringWriter w = new StringWriter();
        new JSONBuilder(w).array().value(null).value(null).endArray();
        assertEquals("[null,null]", w.toString());
    }

    @Test
    void testCreateEmptyObject() {
        StringWriter w = new StringWriter();
        new JSONBuilder(w).object().endObject();
        assertEquals("{}", w.toString());
    }

    @Test
    void testCreateSimpleObject() {
        StringWriter w = new StringWriter();
        new JSONBuilder(w)
                .object()
                .key("bool")
                .value(true)
                .key("numDouble")
                .value(1.1d)
                .key("numInt")
                .value(2)
                .key("text")
                .value("text")
                .endObject();
        JSONObject jsonObj = JSONObject.fromObject(w.toString());
        assertEquals(Boolean.TRUE, jsonObj.get("bool"));
        assertEquals(1.1d, jsonObj.get("numDouble"));
        assertEquals(2L, ((Number) jsonObj.get("numInt")).longValue());
        assertEquals("text", jsonObj.get("text"));
    }
}

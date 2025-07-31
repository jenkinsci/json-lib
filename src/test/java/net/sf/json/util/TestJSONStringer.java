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
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONStringer {
    @Test
    void testCreateArray() {
        JSONBuilder b = new JSONStringer()
                .array()
                .value(true)
                .value(1.1d)
                .value(2L)
                .value("text")
                .endArray();
        assertEquals("[true,1.1,2,\"text\"]", b.toString());
    }

    @Test
    void testCreateEmptyArray() {
        JSONBuilder b = new JSONStringer().array().endArray();
        assertEquals("[]", b.toString());
    }

    @Test
    void testCreateEmptyArrayWithNullObjects() {
        JSONBuilder b = new JSONStringer().array().value(null).value(null).endArray();
        assertEquals("[null,null]", b.toString());
    }

    @Test
    void testCreateEmptyObject() {
        JSONBuilder b = new JSONStringer().object().endObject();
        assertEquals("{}", b.toString());
    }

    @Test
    void testCreateSimpleObject() {
        JSONBuilder b = new JSONStringer()
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
        JSONObject jsonObj = JSONObject.fromObject(b.toString());
        assertEquals(Boolean.TRUE, jsonObj.get("bool"));
        assertEquals(1.1d, jsonObj.get("numDouble"));
        assertEquals(2L, ((Number) jsonObj.get("numInt")).longValue());
        assertEquals("text", jsonObj.get("text"));
    }
}

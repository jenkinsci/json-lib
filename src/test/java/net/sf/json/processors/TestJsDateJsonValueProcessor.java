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

package net.sf.json.processors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJsDateJsonValueProcessor {
    private JsDateJsonValueProcessor processor;

    @Test
    void testProcessBean() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2007);
        c.set(Calendar.MONTH, 5);
        c.set(Calendar.DAY_OF_MONTH, 17);
        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(Calendar.MINUTE, 13);
        c.set(Calendar.SECOND, 14);
        c.set(Calendar.MILLISECOND, 150);
        Date date = c.getTime();
        JSONObject jsonObject = (JSONObject) processor.processObjectValue("date", date, new JsonConfig());
        assertNotNull(jsonObject);
        assertEquals(2007, jsonObject.getInt("year"));
        assertEquals(5, jsonObject.getInt("month"));
        assertEquals(17, jsonObject.getInt("day"));
        assertEquals(12, jsonObject.getInt("hours"));
        assertEquals(13, jsonObject.getInt("minutes"));
        assertEquals(14, jsonObject.getInt("seconds"));
        assertEquals(150, jsonObject.getInt("milliseconds"));
    }

    @Test
    void testProcessBean_sqlDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2007);
        c.set(Calendar.MONTH, 5);
        c.set(Calendar.DAY_OF_MONTH, 17);
        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(Calendar.MINUTE, 13);
        c.set(Calendar.SECOND, 14);
        c.set(Calendar.MILLISECOND, 150);
        Date date = c.getTime();
        JSONObject jsonObject =
                (JSONObject) processor.processObjectValue("date", new java.sql.Date(date.getTime()), new JsonConfig());
        assertNotNull(jsonObject);
        assertEquals(2007, jsonObject.getInt("year"));
        assertEquals(5, jsonObject.getInt("month"));
        assertEquals(17, jsonObject.getInt("day"));
        assertEquals(12, jsonObject.getInt("hours"));
        assertEquals(13, jsonObject.getInt("minutes"));
        assertEquals(14, jsonObject.getInt("seconds"));
        assertEquals(150, jsonObject.getInt("milliseconds"));
    }

    @BeforeEach
    void setUp() {
        processor = new JsDateJsonValueProcessor();
    }
}

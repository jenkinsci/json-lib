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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class MapToDateMorpherTest extends AbstractObjectMorpherTestCase {
    private MapToDateMorpher anotherMorpher;
    private MapToDateMorpher anotherMorpherWithDefaultValue;
    private MapToDateMorpher morpher;
    private MapToDateMorpher morpherWithDefaultValue;

    // -----------------------------------------------------------------------

    @Test
    void testMorph() {
        Map<String, Integer> map = new HashMap<>();
        map.put("year", 2007);
        map.put("month", 5);
        map.put("day", 17);
        map.put("hour", 12);
        map.put("minutes", 13);
        map.put("seconds", 14);
        map.put("milliseconds", 150);

        Date date = (Date) morpher.morph(map);
        assertNotNull(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        assertEquals(2007, c.get(Calendar.YEAR));
        assertEquals(5, c.get(Calendar.MONTH));
        assertEquals(17, c.get(Calendar.DATE));
        assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
        assertEquals(13, c.get(Calendar.MINUTE));
        assertEquals(14, c.get(Calendar.SECOND));
        assertEquals(150, c.get(Calendar.MILLISECOND));
    }

    @Test
    void testMorph_noConversion() {
        Date expected = new Date();
        Date actual = (Date) morpher.morph(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testMorph_notSupported() {
        Assertions.assertThrows(MorphException.class, () -> morpher.morph(new Object[0]));
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }

    @Test
    void testMorph_useDefault() {
        Date expected = new Date();
        morpher.setDefaultValue(expected);
        morpher.setUseDefault(true);
        Date actual = (Date) morpher.morph(new HashMap<>());
        assertEquals(expected, actual);
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

    @BeforeEach
    void setUp() {
        morpher = new MapToDateMorpher();
        morpherWithDefaultValue = new MapToDateMorpher(new Date());
        anotherMorpher = new MapToDateMorpher();
        anotherMorpherWithDefaultValue = new MapToDateMorpher(getUnixEpoch());
    }

    private Date getUnixEpoch() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1970);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}

/*
 * Copyright 2006-2007-2007 the original author or authors.
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

package net.sf.ezmorph.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ezmorph.MorphException;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class MorphDynaClassTest {

    @Test
    void testConstructor_emptyAttributes() {
        MorphDynaClass dynaClass = new MorphDynaClass(null);
        assertEquals(0, dynaClass.getDynaProperties().length);
        dynaClass = new MorphDynaClass(new HashMap<>());
        assertEquals(0, dynaClass.getDynaProperties().length);
    }

    @Test
    void testConstructor_emptyAttributes_throwException() {
        assertThrows(MorphException.class, () -> new MorphDynaClass(null, true));
        assertThrows(MorphException.class, () -> new MorphDynaClass(new HashMap<>(), true));
    }

    @Test
    void testEquals() {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("byte", Byte.class);
        Map<String, Class<?>> props = new HashMap<>();
        props.put("byte", byte.class);
        MorphDynaClass class1 = new MorphDynaClass(properties);
        MorphDynaClass class2 = new MorphDynaClass(properties);
        MorphDynaClass class3 = new MorphDynaClass(props);

        assertNotEquals(null, class1);
        assertEquals(class1, class1);
        assertEquals(class1, class2);
        assertNotEquals(new Object(), class1);
        assertNotEquals(class1, class3);
    }

    @Test
    void testGetDynaProperty_null() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("obj", Object.class.getName());
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        assertThrows(MorphException.class, () -> dynaClass.getDynaProperty(null));
    }

    @Test
    void testHashcode() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("byte", Byte.class);
        Map<String, Class<?>> props = new HashMap<>();
        props.put("byte", byte.class);
        MorphDynaClass class1 = new MorphDynaClass(properties);
        MorphDynaClass class2 = new MorphDynaClass(properties);
        MorphDynaClass class3 = new MorphDynaClass(props);

        assertEquals(class1.hashCode(), class1.hashCode());
        assertEquals(class1.hashCode(), class2.hashCode());
        assertNotEquals(class1.hashCode(), class3.hashCode());
    }

    @Test
    void testMiscellaneousClasses() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("byte", Byte.class);
        properties.put("short", Short.class);
        properties.put("int", Integer.class);
        properties.put("long", Long.class);
        properties.put("float", Float.class);
        properties.put("double", Double.class);
        properties.put("bi", BigInteger.class);
        properties.put("bd", BigDecimal.class);
        properties.put("boolean", Boolean.class);
        properties.put("char", Character.class);
        properties.put("map", Map.class);
        properties.put("strs", String[].class);
        properties.put("list", List.class);
        new MorphDynaClass(properties);
    }

    @Test
    void testMultidimensionalArrayClass_Class() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("array", Object[][].class);
        assertThrows(MorphException.class, () -> new MorphDynaClass(properties));
    }

    @Test
    void testMultidimensionalArrayClass_String() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("array", Object[][].class.getName());
        assertThrows(MorphException.class, () -> new MorphDynaClass(properties));
    }
}

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.sample.BeanA;
import net.sf.ezmorph.bean.sample.BeanB;
import net.sf.ezmorph.bean.sample.BeanC;
import net.sf.ezmorph.bean.sample.BeanD;
import net.sf.ezmorph.bean.sample.ObjectBean;
import net.sf.ezmorph.bean.sample.PrimitiveBean;
import net.sf.ezmorph.bean.sample.TypedBean;
import net.sf.ezmorph.test.ArrayAssertions;
import org.apache.commons.beanutils.DynaBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class BeanMorpherTest {

    private MorpherRegistry morpherRegistry;

    @Test
    void testException_array_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(Map[].class, morpherRegistry));
    }

    @Test
    void testException_Collection_subclass() {
        assertThrows(MorphException.class, () -> new BeanMorpher(ArrayList.class, morpherRegistry));
    }

    @Test
    void testException_DynaBean_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(MorphDynaBean.class, morpherRegistry));
    }

    @Test
    void testException_interface_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(Map.class, morpherRegistry));
    }

    @Test
    void testException_Map_subclass() {
        assertThrows(MorphException.class, () -> new BeanMorpher(HashMap.class, morpherRegistry));
    }

    @Test
    void testException_null_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(null, morpherRegistry));
    }

    @Test
    void testException_null_morpherRegistry() {
        assertThrows(MorphException.class, () -> new BeanMorpher(BeanA.class, null));
    }

    @Test
    void testException_primitive_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(int.class, morpherRegistry));
    }

    @Test
    void testException_String_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(String.class, morpherRegistry));
    }

    @Test
    void testException_wrapper_class() {
        assertThrows(MorphException.class, () -> new BeanMorpher(Integer.class, morpherRegistry));
        assertThrows(MorphException.class, () -> new BeanMorpher(Boolean.class, morpherRegistry));
        assertThrows(MorphException.class, () -> new BeanMorpher(Character.class, morpherRegistry));
    }

    @Test
    void testMorph_dynaBean() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("string", String.class);
        properties.put("integer", Integer.class);
        properties.put("bool", Boolean.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("string", "dyna morph");
        dynaBean.set("integer", "24");
        dynaBean.set("bool", "false");

        BeanMorpher morpher = new BeanMorpher(BeanA.class, morpherRegistry);
        BeanA beanA = (BeanA) morpher.morph(dynaBean);
        assertNotNull(beanA);
        assertFalse(beanA.isBool());
        assertEquals(24, beanA.getInteger());
        assertEquals("dyna morph", beanA.getString());
    }

    @Test
    void testMorph_dynaBean_missingProperty() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("integer", Integer.class);
        properties.put("bool", Boolean.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("integer", "24");
        dynaBean.set("bool", "false");

        BeanMorpher morpher = new BeanMorpher(BeanA.class, morpherRegistry);
        BeanA beanA = (BeanA) morpher.morph(dynaBean);
        assertNotNull(beanA);
        assertFalse(beanA.isBool());
        assertEquals(24, beanA.getInteger());
        assertEquals("morph", beanA.getString());
    }

    @Test
    void testMorph_nested__dynaBeans() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("string", String.class);
        properties.put("integer", Integer.class);
        properties.put("bool", Boolean.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBeanA = (MorphDynaBean) dynaClass.newInstance(morpherRegistry);
        dynaBeanA.setDynaBeanClass(dynaClass);
        dynaBeanA.set("string", "dyna morph");
        dynaBeanA.set("integer", "24");
        dynaBeanA.set("bool", "false");

        properties = new HashMap<>();
        properties.put("string", String.class);
        properties.put("integer", Integer.class);
        properties.put("bool", Boolean.class);
        properties.put("intarray", int[].class);
        dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBeanB = (MorphDynaBean) dynaClass.newInstance(morpherRegistry);
        dynaBeanB.setDynaBeanClass(dynaClass);
        dynaBeanB.set("string", "dyna morph B");
        dynaBeanB.set("integer", "48");
        dynaBeanB.set("bool", "true");
        dynaBeanB.set("intarray", new int[] {4, 5, 6});

        properties = new HashMap<>();
        properties.put("beanA", DynaBean.class);
        properties.put("beanB", DynaBean.class);
        dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBeanC = (MorphDynaBean) dynaClass.newInstance(morpherRegistry);
        dynaBeanC.setDynaBeanClass(dynaClass);
        dynaBeanC.set("beanA", dynaBeanA);
        dynaBeanC.set("beanB", dynaBeanB);

        morpherRegistry.registerMorpher(new BeanMorpher(BeanA.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(BeanB.class, morpherRegistry));
        BeanMorpher morpher = new BeanMorpher(BeanC.class, morpherRegistry);
        BeanC beanC = (BeanC) morpher.morph(dynaBeanC);
        assertNotNull(beanC);
        BeanA beanA = beanC.getBeanA();
        assertFalse(beanA.isBool());
        assertEquals(24, beanA.getInteger());
        assertEquals("dyna morph", beanA.getString());
        BeanB beanB = beanC.getBeanB();
        assertTrue(beanB.isBool());
        assertEquals(48, beanB.getInteger());
        assertEquals("dyna morph B", beanB.getString());
        ArrayAssertions.assertEquals(new int[] {4, 5, 6}, beanB.getIntarray());
    }

    @Test
    void testMorph_null() {
        BeanMorpher morpher = new BeanMorpher(BeanA.class, morpherRegistry);
        BeanA beanA = (BeanA) morpher.morph(null);
        assertNull(beanA);
    }

    @Test
    void testMorph_ObjectBean_to_PrimitiveBean() {
        ObjectBean objectBean = new ObjectBean();
        objectBean.setPclass(Object.class);
        objectBean.setPstring("MORPH");
        morpherRegistry.registerMorpher(new BeanMorpher(PrimitiveBean.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(ObjectBean.class, morpherRegistry));
        PrimitiveBean primitiveBean = (PrimitiveBean) morpherRegistry.morph(PrimitiveBean.class, objectBean);
        assertNotNull(primitiveBean);
        assertFalse(primitiveBean.isPboolean());
        assertEquals((byte) 0, primitiveBean.getPbyte());
        assertEquals((short) 0, primitiveBean.getPshort());
        assertEquals(0, primitiveBean.getPint());
        assertEquals(0L, primitiveBean.getPlong());
        assertEquals(0f, primitiveBean.getPfloat(), 0f);
        assertEquals(0d, primitiveBean.getPdouble(), 0d);
        assertEquals('\0', primitiveBean.getPchar());
        assertNull(primitiveBean.getParray());
        assertNull(primitiveBean.getPlist());
        assertNull(primitiveBean.getPbean());
        assertNull(primitiveBean.getPmap());
        assertEquals("MORPH", primitiveBean.getPstring());
        assertEquals(Object.class, primitiveBean.getPclass());
    }

    @Test
    void testMorph_ObjectBean_to_PrimitiveBean_lenient() {
        ObjectBean objectBean = new ObjectBean();
        objectBean.setPclass(Object.class);
        objectBean.setPstring("MORPH");
        objectBean.setPbean(objectBean);
        morpherRegistry.registerMorpher(new BeanMorpher(PrimitiveBean.class, morpherRegistry, true));
        PrimitiveBean primitiveBean = (PrimitiveBean) morpherRegistry.morph(PrimitiveBean.class, objectBean);
        assertNotNull(primitiveBean);
        assertFalse(primitiveBean.isPboolean());
        assertEquals((byte) 0, primitiveBean.getPbyte());
        assertEquals((short) 0, primitiveBean.getPshort());
        assertEquals(0, primitiveBean.getPint());
        assertEquals(0L, primitiveBean.getPlong());
        assertEquals(0f, primitiveBean.getPfloat(), 0f);
        assertEquals(0d, primitiveBean.getPdouble(), 0d);
        assertEquals('\0', primitiveBean.getPchar());
        assertNull(primitiveBean.getParray());
        assertNull(primitiveBean.getPlist());
        assertNull(primitiveBean.getPbean());
        assertNull(primitiveBean.getPmap());
        assertEquals("MORPH", primitiveBean.getPstring());
        assertEquals(Object.class, primitiveBean.getPclass());
    }

    @Test
    void testMorph_ObjectBean_to_PrimitiveBean_notLenient() {
        ObjectBean objectBean = new ObjectBean();
        objectBean.setPclass(Object.class);
        objectBean.setPstring("MORPH");
        objectBean.setPbean(objectBean);
        morpherRegistry.registerMorpher(new BeanMorpher(PrimitiveBean.class, morpherRegistry));
        assertThrows(MorphException.class, () -> morpherRegistry.morph(PrimitiveBean.class, objectBean));
    }

    @Test
    void testMorph_ObjectBean_to_TypedBean() {
        ObjectBean objectBean = new ObjectBean();
        objectBean.setPclass(Object.class);
        objectBean.setPstring("MORPH");
        morpherRegistry.registerMorpher(new BeanMorpher(TypedBean.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(ObjectBean.class, morpherRegistry));
        TypedBean typedBean = (TypedBean) morpherRegistry.morph(TypedBean.class, objectBean);
        assertNotNull(typedBean);
        assertNull(typedBean.getPboolean());
        assertNull(typedBean.getPbyte());
        assertNull(typedBean.getPshort());
        assertNull(typedBean.getPint());
        assertNull(typedBean.getPlong());
        assertNull(typedBean.getPfloat());
        assertNull(typedBean.getPdouble());
        assertNull(typedBean.getPchar());
        assertNull(typedBean.getParray());
        assertNull(typedBean.getPlist());
        assertNull(typedBean.getPbean());
        assertNull(typedBean.getPmap());
        assertEquals("MORPH", typedBean.getPstring());
        assertEquals(Object.class, typedBean.getPclass());
    }

    @Test
    void testMorph_PrimitiveBean_to_ObjectBean() {
        PrimitiveBean primitiveBean = new PrimitiveBean();
        primitiveBean.setPclass(Object.class);
        primitiveBean.setPstring("MORPH");
        morpherRegistry.registerMorpher(new BeanMorpher(ObjectBean.class, morpherRegistry));
        ObjectBean objectBean = (ObjectBean) morpherRegistry.morph(ObjectBean.class, primitiveBean);
        assertNotNull(objectBean);
        assertEquals(Boolean.FALSE, objectBean.getPboolean());
        assertEquals(Byte.valueOf("0"), objectBean.getPbyte());
        assertEquals(Short.valueOf("0"), objectBean.getPshort());
        assertEquals(Integer.valueOf("0"), objectBean.getPint());
        assertEquals(Long.valueOf("0"), objectBean.getPlong());
        assertEquals(Float.valueOf("0"), objectBean.getPfloat());
        assertEquals(Double.valueOf("0"), objectBean.getPdouble());
        assertEquals('\0', objectBean.getPchar());
        assertNull(objectBean.getParray());
        assertNull(objectBean.getPlist());
        assertNull(objectBean.getPbean());
        assertNull(objectBean.getPmap());
        assertEquals("MORPH", objectBean.getPstring());
        assertEquals(Object.class, objectBean.getPclass());
    }

    @Test
    void testMorph_PrimitiveBean_to_TypedBean() {
        PrimitiveBean primitiveBean = new PrimitiveBean();
        primitiveBean.setPclass(Object.class);
        primitiveBean.setPstring("MORPH");
        morpherRegistry.registerMorpher(new BeanMorpher(TypedBean.class, morpherRegistry));
        TypedBean typedBean = (TypedBean) morpherRegistry.morph(TypedBean.class, primitiveBean);
        assertNotNull(typedBean);
        assertEquals(Boolean.FALSE, typedBean.getPboolean());
        assertEquals(Byte.valueOf("0"), typedBean.getPbyte());
        assertEquals(Short.valueOf("0"), typedBean.getPshort());
        assertEquals(Integer.valueOf("0"), typedBean.getPint());
        assertEquals(Long.valueOf("0"), typedBean.getPlong());
        assertEquals(Float.valueOf("0"), typedBean.getPfloat());
        assertEquals(Double.valueOf("0"), typedBean.getPdouble());
        assertEquals('\0', typedBean.getPchar());
        assertNull(typedBean.getParray());
        assertNull(typedBean.getPlist());
        assertNull(typedBean.getPbean());
        assertNull(typedBean.getPmap());
        assertEquals("MORPH", typedBean.getPstring());
        assertEquals(Object.class, typedBean.getPclass());
    }

    @Test
    void testMorph_unsupported() {
        BeanMorpher morpher = new BeanMorpher(BeanA.class, morpherRegistry);
        assertThrows(MorphException.class, () -> morpher.morph(new Object[0]));
    }

    @Test
    void testMorph_BeanA_to_BeanD() {
        morpherRegistry.registerMorpher(new BeanMorpher(BeanD.class, morpherRegistry));
        BeanA beanA = new BeanA();
        beanA.setBool(false);
        beanA.setInteger(84);
        beanA.setString("string");
        BeanD beanD = (BeanD) morpherRegistry.morph(BeanD.class, beanA);
        assertNotNull(beanD);
        assertFalse(beanD.isBool());
        assertEquals(84, beanD.getInteger());
        assertEquals(0d, beanD.getDecimal(), 0d);
    }

    @BeforeEach
    void setUp() {
        morpherRegistry = new MorpherRegistry();
        MorphUtils.registerStandardMorphers(morpherRegistry);
    }
}

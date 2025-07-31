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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import net.sf.json.sample.AnnotatedBean;
import net.sf.json.sample.AnnotationBean;
import net.sf.json.sample.EnumBean;
import net.sf.json.sample.JsonAnnotation;
import net.sf.json.sample.JsonEnum;
import net.sf.json.util.EnumMorpher;
import net.sf.json.util.JSONUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestJSONObjectJdk15 {
    @Test
    void testFromBean_AnnotationBean() {
        AnnotationBean bean = new AnnotationBean();
        Annotation[] annotations = bean.getClass().getAnnotations();
        assertThrows(JSONException.class, () -> JSONObject.fromObject(annotations[0]));
    }

    @Test
    void testFromBean_Enum() {
        assertThrows(JSONException.class, () -> JSONObject.fromObject(JsonEnum.OBJECT));
    }

    @Test
    void testFromBean_EnumBean() {
        EnumBean bean = new EnumBean();
        bean.setJsonEnum(JsonEnum.OBJECT);
        bean.setString("string");
        JSONObject json = JSONObject.fromObject(bean);
        assertNotNull(json);
        assertEquals(JsonEnum.OBJECT.toString(), json.get("jsonEnum"));
        assertEquals("string", json.get("string"));
    }

    @Test
    void testFromObject_AnnotationBean() {
        AnnotationBean bean = new AnnotationBean();
        Annotation[] annotations = bean.getClass().getAnnotations();
        assertThrows(JSONException.class, () -> JSONObject.fromObject(annotations[0]));
    }

    @Test
    void testFromObject_DynaBean__Enum() throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();
        properties.put("jsonEnum", JsonEnum.class);
        MorphDynaClass dynaClass = new MorphDynaClass(properties);
        MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
        dynaBean.set("jsonEnum", JsonEnum.OBJECT);
        JSONObject json = JSONObject.fromObject(dynaBean);
        assertNotNull(json);
        assertEquals(JsonEnum.OBJECT.toString(), json.get("jsonEnum"));
    }

    @Test
    void testFromObject_Enum() {
        assertThrows(JSONException.class, () -> JSONObject.fromObject(JsonEnum.OBJECT));
    }

    @Test
    void testFromObject_EnumBean() {
        EnumBean bean = new EnumBean();
        bean.setJsonEnum(JsonEnum.OBJECT);
        bean.setString("string");
        JSONObject json = JSONObject.fromObject(bean);
        assertNotNull(json);
        assertEquals(JsonEnum.OBJECT.toString(), json.get("jsonEnum"));
        assertEquals("string", json.get("string"));
    }

    @Test
    void testFromObject_Map__Enum() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jsonEnum", JsonEnum.OBJECT);
        JSONObject json = JSONObject.fromObject(properties);
        assertNotNull(json);
        assertEquals(JsonEnum.OBJECT.toString(), json.get("jsonEnum"));
    }

    @Test
    void testPut_Annotation() {
        AnnotationBean bean = new AnnotationBean();
        Annotation[] annotations = bean.getClass().getAnnotations();
        JSONObject jsonObject = new JSONObject();
        assertThrows(JSONException.class, () -> jsonObject.element("annotation", annotations[0]));
    }

    @Test
    void testPut_Enum() {
        JSONObject json = new JSONObject();
        json.element("jsonEnum", JsonEnum.OBJECT);
        assertEquals(JsonEnum.OBJECT.toString(), json.get("jsonEnum"));
    }

    @Test
    void testToBean_EnumBean() {
        JSONUtils.getMorpherRegistry().registerMorpher(new EnumMorpher(JsonEnum.class));
        JSONObject json = new JSONObject();
        json.element("jsonEnum", "OBJECT");
        EnumBean bean = (EnumBean) JSONObject.toBean(json, EnumBean.class);
        assertNotNull(bean);
        assertEquals(JsonEnum.OBJECT, bean.getJsonEnum());
    }

    /*
    public void testToBean_EnumBean2()
    {
       JSONUtils.getMorpherRegistry()
             .registerMorpher( new EnumMorpher( JsonEnum.class ) );
       EnumBean bean = new EnumBean();
       bean.getEnums().add(JsonEnum.ARRAY);
       bean.getEnums().add(JsonEnum.OBJECT);
       JSONObject json = JSONObject.fromObject(bean);
       System.err.println(json);
       EnumBean bean2 = (EnumBean) JSONObject.toBean( json, EnumBean.class );
       assertNotNull( bean2 );
       System.err.println(bean.getEnums().toString());
       System.err.println(bean2.getEnums().toString());
       Map classMap = new HashMap();
       classMap.put("enums", JsonEnum.class);
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.setRootClass( EnumBean.class );
       jsonConfig.setClassMap(classMap);
       EnumBean bean3 = (EnumBean) JSONObject.toBean( JSONObject.fromObject(json.toString()), jsonConfig );
       assertNotNull( bean3 );
       System.err.println(bean3.getEnums().toString());
       for(java.util.Iterator i= bean3.getEnums().iterator(); i.hasNext();) System.err.println(i.next().getClass());
    }
    */

    @Test
    void testToBean_EnumBean_autoRegisterMorpher() {
        JSONObject json = new JSONObject();
        json.element("jsonEnum", "OBJECT");
        EnumBean bean = (EnumBean) JSONObject.toBean(json, EnumBean.class);
        assertNotNull(bean);
        assertEquals(JsonEnum.OBJECT, bean.getJsonEnum());
    }

    @Test
    void testFromObject_ignoreAnnotations() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.addIgnoreFieldAnnotation(JsonAnnotation.class);
        AnnotatedBean bean = new AnnotatedBean();
        bean.setString1("STRING_1");
        bean.setString2("STRING_2");
        bean.setString3("STRING_3");
        JSONObject json = JSONObject.fromObject(bean, jsonConfig);
        assertNotNull(json);
        assertEquals("STRING_1", json.get("string1"));
        assertEquals("STRING_2", json.get("string2"));
        assertFalse(json.has("string3"));

        jsonConfig.setIgnoreTransientFields(true);
        json = JSONObject.fromObject(bean, jsonConfig);
        assertNotNull(json);
        assertEquals("STRING_1", json.get("string1"));
        assertFalse(json.has("string2"));
        assertFalse(json.has("string3"));
    }

    @BeforeEach
    void setUp() {
        Morpher morpher = JSONUtils.getMorpherRegistry().getMorpherFor(JsonEnum.class);
        JSONUtils.getMorpherRegistry().deregisterMorpher(morpher);
    }
}

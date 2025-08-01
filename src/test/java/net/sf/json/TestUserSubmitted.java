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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.sample.ArrayBean;
import net.sf.json.sample.BeanA;
import net.sf.json.sample.BeanA1763699;
import net.sf.json.sample.BeanB1763699;
import net.sf.json.sample.BeanC;
import net.sf.json.sample.FieldBean;
import net.sf.json.sample.JSONTestBean;
import net.sf.json.sample.ListingBean;
import net.sf.json.sample.MappedBean;
import net.sf.json.sample.Media;
import net.sf.json.sample.MediaBean;
import net.sf.json.sample.MediaList;
import net.sf.json.sample.MediaListBean;
import net.sf.json.sample.NumberArrayBean;
import net.sf.json.sample.PackageProtectedBean;
import net.sf.json.sample.Player;
import net.sf.json.sample.PlayerList;
import net.sf.json.sample.PrimitiveBean;
import net.sf.json.sample.PrivateConstructorBean;
import net.sf.json.sample.UnstandardBean;
import net.sf.json.sample.UnstandardBeanInstanceStrategy;
import net.sf.json.util.JavaIdentifierTransformer;
import net.sf.json.util.JsonEventListener;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class TestUserSubmitted {
    private JsonConfig jsonConfig;

    @Test
    void testPatch_2929940() {
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("attributes", Long.class);

        ListingBean original = new ListingBean();
        original.addAttribute(12L);

        JSONObject jsonObject =
                JSONObject.fromObject(JSONObject.fromObject(original).toString());

        // JSON config
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(ListingBean.class);
        jsonConfig.setClassMap(classMap);

        // toBean
        ListingBean bean = (ListingBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertInstanceOf(Long.class, bean.getAttributes().get(0));
    }

    @Test
    void testBug_1635890() {
        // submited by arco.vandenheuvel[at]points[dot].com

        String TEST_JSON_STRING =
                "{\"rateType\":\"HOTRATE\",\"rateBreakdown\":{\"rate\":[{\"amount\":\"109.74\",\"date\":{\"month\":\"01\",\"day\":\"15\",\"year\":\"2007\"}},{\"amount\":\"109.74\",\"date\":{\"month\":\"1\",\"day\":\"16\",\"year\":\"2007\"}}]}}";

        DynaBean jsonBean = (DynaBean) JSONObject.toBean(JSONObject.fromObject(TEST_JSON_STRING));
        assertNotNull(jsonBean);
        assertEquals("HOTRATE", jsonBean.get("rateType"), "wrong rate Type");
        assertNotNull(jsonBean.get("rateBreakdown"), "null rate breakdown");
        DynaBean jsonRateBreakdownBean = (DynaBean) jsonBean.get("rateBreakdown");
        assertNotNull(jsonRateBreakdownBean, "null rate breakdown ");
        Object jsonRateBean = jsonRateBreakdownBean.get("rate");
        assertNotNull(jsonRateBean, "null rate ");
        assertInstanceOf(ArrayList.class, jsonRateBean, "list");
        assertNotNull(jsonRateBreakdownBean.get("rate", 0), "null rate ");
    }

    @Test
    void testBug_1650535_builders() {
        // submitted by Paul Field <paulfield[at]users[dot]sourceforge[dot]net>

        String json = "{\"obj\":\"{}\",\"array\":\"[]\"}";
        JSONObject object = JSONObject.fromObject(json);
        assertNotNull(object);
        assertTrue(object.has("obj"));
        assertTrue(object.has("array"));
        Object obj = object.get("obj");
        assertInstanceOf(String.class, obj);
        Object array = object.get("array");
        assertInstanceOf(String.class, array);

        json = "{'obj':'{}','array':'[]'}";
        object = JSONObject.fromObject(json);
        assertNotNull(object);
        assertTrue(object.has("obj"));
        assertTrue(object.has("array"));
        obj = object.get("obj");
        assertInstanceOf(String.class, obj);
        array = object.get("array");
        assertInstanceOf(String.class, array);

        json = "[\"{}\",\"[]\"]";
        JSONArray jarray = JSONArray.fromObject(json);
        assertNotNull(jarray);
        obj = jarray.get(0);
        assertInstanceOf(String.class, obj);
        array = jarray.get(1);
        assertInstanceOf(String.class, array);

        json = "['{}','[]']";
        jarray = JSONArray.fromObject(json);
        assertNotNull(jarray);
        obj = jarray.get(0);
        assertInstanceOf(String.class, obj);
        array = jarray.get(1);
        assertInstanceOf(String.class, array);

        // submitted by Elizabeth Keogh <ekeogh[at]thoughtworks[dot]com>

        Map<String, String> map = new HashMap<>();
        map.put("address", "1 The flats [Upper floor]");
        map.put("phoneNumber", "[+44] 582 401923");
        map.put("info1", "[Likes coffee]");
        map.put("info2", "[Likes coffee] [Likes tea]");
        map.put("info3", "[Likes coffee [but not with sugar]]");
        object = JSONObject.fromObject(map);
        assertNotNull(object);
        assertTrue(object.has("address"));
        assertTrue(object.has("phoneNumber"));
        assertTrue(object.has("info1"));
        assertTrue(object.has("info2"));
        assertTrue(object.has("info3"));
        assertInstanceOf(String.class, object.get("address"));
        assertInstanceOf(String.class, object.get("phoneNumber"));
        assertInstanceOf(String.class, object.get("info1"));
        assertInstanceOf(String.class, object.get("info2"));
        assertInstanceOf(String.class, object.get("info3"));
    }

    /* I consider this behavior of "oh I added string but it's not really a string" very evil, as there's no way to add a String that really looks like "{}"
       public void testBug_1650535_setters() {
          JSONObject object = new JSONObject();
          object.element( "obj", "{}" );
          object.element( "notobj", "{string}" );
          object.element( "array", "[]" );
          object.element( "notarray", "[string]" );
          assertTrue( object.get( "obj" ) instanceof JSONObject );
          assertTrue( object.get( "array" ) instanceof JSONArray );
          assertTrue( object.get( "notobj" ) instanceof String );
          assertTrue( object.get( "notarray" ) instanceof String );
          object.element( "str", "json,json" );
          assertTrue( object.get( "str" ) instanceof String );
       }
    */
    @Test
    void testBug_1753528_ArrayStringLiteralToString() {
        // submited bysckimos[at]gmail[dot]com
        BeanA bean = new BeanA();
        bean.setString("[1234]");
        JSONObject jsonObject = JSONObject.fromObject(bean);
        assertEquals("[1234]", jsonObject.get("string"));
        bean.setString("{'key':'1234'}");
        jsonObject = JSONObject.fromObject(bean);
        assertEquals("{'key':'1234'}", jsonObject.get("string"));
    }

    @Test
    void testBug_1763699_toBean() {
        JSONObject json = JSONObject.fromObject("{'bbeans':[{'str':'test'}]}");
        BeanA1763699 bean = (BeanA1763699) JSONObject.toBean(json, BeanA1763699.class);
        assertNotNull(bean);
        BeanB1763699[] bbeans = bean.getBbeans();
        assertNotNull(bbeans);
        assertEquals(1, bbeans.length);
        assertEquals("test", bbeans[0].getStr());
    }

    @Test
    void testBug_1764768_toBean() {
        JSONObject json = JSONObject.fromObject("{'beanA':''}");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("beanA", BeanA.class);
        BeanC bean = (BeanC) JSONObject.toBean(json, BeanC.class, classMap);
        assertNotNull(bean);
        assertNotNull(bean.getBeanA());
        assertEquals(new BeanA(), bean.getBeanA());
    }

    @Test
    void testBug_1769559_array_conversion() {
        JSONObject jsonObject = new JSONObject()
                .element(
                        "beans", new JSONArray().element("{}").element("{'bool':false,'integer':216,'string':'JsOn'}"));
        ArrayBean bean = (ArrayBean) JSONObject.toBean(jsonObject, ArrayBean.class);
        assertNotNull(bean); // no error should happen here
        JSONArray jsonArray = jsonObject.getJSONArray("beans");
        BeanA[] beans = (BeanA[]) JSONArray.toArray(jsonArray, BeanA.class);
        assertNotNull(beans);
        assertEquals(2, beans.length);
        assertEquals(new BeanA(), beans[0]);
        assertEquals(new BeanA(false, 216, "JsOn"), beans[1]);
    }

    @Test
    void testBug_1769578_array_conversion() {
        JSONObject jsonObject = JSONObject.fromObject("{'media':[{'title':'Giggles'},{'title':'Dreamland?'}]}");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("media", MediaBean.class);
        MediaListBean bean = (MediaListBean) JSONObject.toBean(jsonObject, MediaListBean.class, classMap);
        assertNotNull(bean);
        assertNotNull(bean.getMedia());
        assertTrue(bean.getMedia().getClass().isArray());
        Object[] media = (Object[]) bean.getMedia();
        assertEquals(2, media.length);
        Object mediaItem1 = media[0];
        assertInstanceOf(MediaBean.class, mediaItem1);
        assertEquals("Giggles", ((MediaBean) mediaItem1).getTitle());
    }

    @Test
    void testBug_1812682() {
        int[] numbers = new int[] {1, 2, 3, 4, 5};
        JSONObject json = new JSONObject()
                .element("bytes", numbers)
                .element("shorts", numbers)
                .element("ints", numbers)
                .element("longs", numbers)
                .element("floats", numbers)
                .element("doubles", numbers);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(NumberArrayBean.class);
        NumberArrayBean bean = (NumberArrayBean) JSONObject.toBean(json, jsonConfig);
        assertNotNull(bean);
    }

    @Test
    void testBug_1813301() {
        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        JSONObject jsonObject = new JSONObject().element("name", "JSON").element("list", list);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(MappedBean.class);
        MappedBean bean = (MappedBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertEquals("JSON", bean.getName());
        Assertions.assertEquals(list, bean.getList());
    }

    @Test
    void testBug_1875600_1() {
        JSONArray jArray = JSONArray.fromObject("[]");
        int[] iArray = (int[]) JSONArray.toArray(jArray, int.class);
        JSONArray actual = JSONArray.fromObject(iArray);
        Assertions.assertEquals(new JSONArray(), actual);
    }

    @Test
    void testBug_1875600_2() {
        JSONArray jArray = JSONArray.fromObject("[ [] ]");
        int[][] iArray = (int[][]) JSONArray.toArray(jArray, int.class);
        JSONArray actual = JSONArray.fromObject(iArray);
        Assertions.assertEquals(new JSONArray().element(new JSONArray()), actual);
    }

    @Test
    void testConstructor_Object__nullArray() {
        // submitted by Matt Small
        String[] strarr = null;
        JSONObject jsonObject = JSONObject.fromObject(strarr, jsonConfig);
        assertTrue(jsonObject.isNullObject());
    }

    @Test
    void testConstructor_Object_EnclosedArray() {
        // submitted by Matt Small
        PrimitiveBean bean = new PrimitiveBean();
        bean.setOarray(new String[] {"hi", "bye"});
        JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
        assertFalse(jsonObject.isNullObject());
        assertFalse(jsonObject.getJSONArray("oarray").isEmpty());
    }

    @Test
    void testConstructor_Object_EnclosedNullArray() {
        // submitted by Matt Small
        PrimitiveBean bean = new PrimitiveBean();
        bean.setOarray(null);
        JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
        assertFalse(jsonObject.isNullObject());
        assertTrue(jsonObject.getJSONArray("oarray").isEmpty());
    }

    @Test
    void testConstructorAndToBean_Object_RoundTrip_EnclosedNullArray() {

        PrimitiveBean bean = new PrimitiveBean();
        bean.setOarray(null);
        JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
        PrimitiveBean bean2 = (PrimitiveBean) JSONObject.toBean(jsonObject, PrimitiveBean.class);
        assertNotNull(bean2);
        // bean.oarray == null
        // jsonObject.oarray == [] therefore
        // bean2.oarray != null
        assertEquals(0, bean2.getOarray().length);
    }

    @Test
    void testDynaBeanAttributeMap() {
        // submited by arco.vandenheuvel[at]points[dot].com
        JSONObject jsonObject = JSONObject.fromObject(new JSONTestBean());
        String jsonString = jsonObject.toString();
        DynaBean jsonBean = (DynaBean) JSONObject.toBean(JSONObject.fromObject(jsonString));
        assertNotNull(jsonBean);
        assertEquals("", jsonBean.get("inventoryID"), "wrong inventoryID");
    }

    @Test
    void testFR_1768960_array_conversion() { // 2 items
        JSONObject jsonObject = JSONObject.fromObject("{'media2':[{'title':'Giggles'},{'title':'Dreamland?'}]}");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("media2", MediaBean.class);
        MediaListBean bean = (MediaListBean) JSONObject.toBean(jsonObject, MediaListBean.class, classMap);
        assertNotNull(bean);
        assertNotNull(bean.getMedia2());
        List<?> media2 = bean.getMedia2();
        assertEquals(2, media2.size());
        Object mediaItem1 = media2.get(0);
        assertInstanceOf(MediaBean.class, mediaItem1);
        assertEquals("Giggles", ((MediaBean) mediaItem1).getTitle()); // 1
        // item
        jsonObject = JSONObject.fromObject("{'media2':[{'title':'Giggles'}]}");
        bean = (MediaListBean) JSONObject.toBean(jsonObject, MediaListBean.class, classMap);
        assertNotNull(bean);
        assertNotNull(bean.getMedia2());
        media2 = bean.getMedia2();
        assertEquals(1, media2.size());
        mediaItem1 = media2.get(0);
        assertInstanceOf(MediaBean.class, mediaItem1);
        assertEquals("Giggles", ((MediaBean) mediaItem1).getTitle());
    }

    @Test
    void testFR_1808430_newBeanInstance() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setNewBeanInstanceStrategy(new UnstandardBeanInstanceStrategy());
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("id", 1);
        jsonConfig.setRootClass(UnstandardBean.class);
        UnstandardBean bean = (UnstandardBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertEquals(UnstandardBean.class, bean.getClass());
        assertEquals(1, bean.getId());
    }

    @Test
    void testFR_1832047_packageProtectedBean() {
        JSONObject jsonObject = new JSONObject().element("value", "42");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(PackageProtectedBean.class);
        PackageProtectedBean bean = (PackageProtectedBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertEquals(42, bean.getValue());
    }

    @Test
    void testFR_1832047_privateProtectedBean() {
        JSONObject jsonObject = new JSONObject().element("value", "42");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(PrivateConstructorBean.class);
        PrivateConstructorBean bean = (PrivateConstructorBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertEquals(42, bean.getValue());
    }

    @Test
    void testFR_1858073_preserveInsertionOrder() {
        JSONObject jsonObject =
                new JSONObject().element("one", "one").element("two", "two").element("three", "three");
        JSONArray actual = jsonObject.names();
        JSONArray expected = new JSONArray().element("one").element("two").element("three");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFromObjectCurliesOnString() {
        String json = "{'prop':'{value}'}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        assertNotNull(jsonObject);
        assertEquals(1, jsonObject.size());
        assertEquals("{value}", jsonObject.get("prop"));

        json = "{'prop':'{{value}}'}";
        jsonObject = JSONObject.fromObject(json);
        assertNotNull(jsonObject);
        assertEquals(1, jsonObject.size());
        assertEquals("{{value}}", jsonObject.get("prop"));

        json = "{'prop':'{{{value}}}'}";
        jsonObject = JSONObject.fromObject(json);
        assertNotNull(jsonObject);
        assertEquals(1, jsonObject.size());
        assertEquals("{{{value}}}", jsonObject.get("prop"));
    }

    @Test
    void testHandleJettisonEmptyElement() {
        JSONObject jsonObject = JSONObject.fromObject("{'beanA':'','beanB':''}");
        jsonConfig.setHandleJettisonEmptyElement(true);
        jsonConfig.setRootClass(BeanC.class);
        BeanC bean = (BeanC) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertNull(bean.getBeanA());
        assertNull(bean.getBeanB());
    }

    @Test
    void testHandleJettisonSingleElementArray() {
        JSONObject jsonObject = JSONObject.fromObject("{'media2':{'title':'Giggles'}}");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("media2", MediaBean.class);
        jsonConfig.setHandleJettisonSingleElementArray(true);
        jsonConfig.setRootClass(MediaListBean.class);
        jsonConfig.setClassMap(classMap);
        MediaListBean bean = (MediaListBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertNotNull(bean.getMedia2());
        List<?> media2 = bean.getMedia2();
        assertEquals(1, media2.size());
        Object mediaItem1 = media2.get(0);
        assertInstanceOf(MediaBean.class, mediaItem1);
        assertEquals("Giggles", ((MediaBean) mediaItem1).getTitle());
    }

    @Test
    void testHandleJettisonSingleElementArray2() {
        JSONObject jsonObject = JSONObject.fromObject("{'mediaList':{'media':{'title':'Giggles'}}}");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("media", Media.class);
        classMap.put("mediaList", MediaList.class);
        jsonConfig.setHandleJettisonSingleElementArray(true);
        jsonConfig.setRootClass(Player.class);
        jsonConfig.setClassMap(classMap);
        Player bean = (Player) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertNotNull(bean.getMediaList());
        MediaList mediaList = bean.getMediaList();
        assertNotNull(mediaList.getMedia());
        ArrayList<?> medias = mediaList.getMedia();
        assertEquals("Giggles", ((Media) medias.get(0)).getTitle());
    }

    @Test
    void testHandleJettisonSingleElementArray3() {
        JSONObject jsonObject = JSONObject.fromObject("{'player':{'mediaList':{'media':{'title':'Giggles'}}}}");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("media", Media.class);
        classMap.put("mediaList", MediaList.class);
        classMap.put("player", Player.class);
        jsonConfig.setHandleJettisonSingleElementArray(true);
        jsonConfig.setRootClass(PlayerList.class);
        jsonConfig.setClassMap(classMap);
        PlayerList bean = (PlayerList) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean);
        assertNotNull(bean.getPlayer());
        ArrayList<?> players = bean.getPlayer();
        assertNotNull(players);
        assertNotNull(players.get(0));
        Player player = (Player) players.get(0);
        assertNotNull(player.getMediaList());
        MediaList mediaList = player.getMediaList();
        assertNotNull(mediaList.getMedia());
        ArrayList<?> medias = mediaList.getMedia();
        assertEquals("Giggles", ((Media) medias.get(0)).getTitle());
    }

    @Test
    void testJsonWithNamespaceToDynaBean() throws Exception {
        // submited by Girish Ipadi

        jsonConfig.setJavaIdentifierTransformer(JavaIdentifierTransformer.NOOP);
        String str = "{'version':'1.0'," + "'sid':'AmazonDocStyle',    'svcVersion':'0.1',"
                + "'oid':'ItemLookup',    'params':[{            'ns:ItemLookup': {"
                + "'ns:SubscriptionId':'0525E2PQ81DD7ZTWTK82'," + "'ns:Validate':'False',"
                + "'ns:Request':{" + "'ns:ItemId':'SDGKJSHDGAJSGL'," + "'ns:IdType':'ASIN',"
                + "'ns:ResponseGroup':'Large'" + "}," + "'ns:Request':{" + "'ns:ItemId':'XXXXXXXXXX',"
                + "'ns:IdType':'ASIN'," + "'ns:ResponseGroup':'Large'" + "}" + "}" + "}]" + "} ";
        JSONObject json = JSONObject.fromObject(str, jsonConfig);
        Object bean = JSONObject.toBean((JSONObject) json);
        assertNotNull(bean);
        List<?> params = (List<?>) PropertyUtils.getProperty(bean, "params");
        DynaBean param0 = (DynaBean) params.get(0);
        DynaBean itemLookup = (DynaBean) param0.get("ns:ItemLookup");
        assertNotNull(itemLookup);
        assertEquals("0525E2PQ81DD7ZTWTK82", itemLookup.get("ns:SubscriptionId"));
    }

    /* No morpher, please - Kohsuke
       public void testToBeanSimpleToComplexValueTransformation() {
          // Submitted by Oliver Zyngier
          JSONObject jsonObject = JSONObject.fromObject( "{'id':null}" );
          IdBean idBean = (IdBean) JSONObject.toBean( jsonObject, IdBean.class );
          assertNotNull( idBean );
          assertEquals( null, idBean.getId() );

          jsonObject = JSONObject.fromObject( "{'id':1}" );
          idBean = (IdBean) JSONObject.toBean( jsonObject, IdBean.class );
          assertNotNull( idBean );
          assertNotNull( idBean.getId() );
          assertEquals( 0L, idBean.getId().getValue() );

          JSONUtils.getMorpherRegistry().registerMorpher( new IdBean.IdMorpher(), true );
          jsonObject = JSONObject.fromObject( "{'id':1}" );
          idBean = (IdBean) JSONObject.toBean( jsonObject, IdBean.class );
          assertNotNull( idBean );
          assertEquals( new IdBean.Id( 1L ), idBean.getId() );
       }

       public void testToBeanWithMultipleMorphersForTargetType() {
          Calendar c = Calendar.getInstance();
          c.set( Calendar.YEAR, 2007 );
          c.set( Calendar.MONTH, 5 );
          c.set( Calendar.DATE, 17 );
          c.set( Calendar.HOUR_OF_DAY, 12 );
          c.set( Calendar.MINUTE, 13 );
          c.set( Calendar.SECOND, 14 );
          c.set( Calendar.MILLISECOND, 150 );
          Date date = c.getTime();

          DateBean bean = new DateBean();
          bean.setDate( date );
          JSONObject jsonObject = JSONObject.fromObject( bean );

          JSONUtils.getMorpherRegistry().registerMorpher( new MapToDateMorpher() );

          JsonConfig jsonConfig = new JsonConfig();
          jsonConfig.setRootClass( DateBean.class );
          DateBean actual = (DateBean) JSONObject.toBean( jsonObject, jsonConfig );
          Calendar d = Calendar.getInstance();
          d.setTime( actual.getDate() );
          assertEquals( c.get( Calendar.YEAR ), d.get( Calendar.YEAR ) );
          assertEquals( c.get( Calendar.MONTH ), d.get( Calendar.MONTH ) );
          assertEquals( c.get( Calendar.DATE ), d.get( Calendar.DATE ) );
          assertEquals( c.get( Calendar.HOUR_OF_DAY ), d.get( Calendar.HOUR_OF_DAY ) );
          assertEquals( c.get( Calendar.MINUTE ), d.get( Calendar.MINUTE ) );
          assertEquals( c.get( Calendar.SECOND ), d.get( Calendar.SECOND ) );
          assertEquals( c.get( Calendar.MILLISECOND ), d.get( Calendar.MILLISECOND ) );
       }

       public void testToBeanWithInterfaceField() {
          JSONObject jsonObject = JSONObject.fromObject( "{runnable:{}}" );
          JsonConfig jsonConfig = new JsonConfig();
          jsonConfig.setRootClass( InterfaceBean.class );
          Map classMap = new HashMap();
          classMap.put( "runnable", RunnableImpl.class );
          jsonConfig.setClassMap( classMap );
          InterfaceBean bean = (InterfaceBean) JSONObject.toBean( jsonObject, jsonConfig );
          assertNotNull( bean );
          assertNotNull( bean.getRunnable() );
          assertTrue( bean.getRunnable() instanceof RunnableImpl );
       }

       public void testCycleDetection_withExclusions() {
          ParentBean parent = new ParentBean();
          parent.setChild( new ChildBean() );

          // will fail if throws an exception
          jsonConfig.setExcludes( new String[] { "parent" } );
          JSONObject.fromObject( parent, jsonConfig );
       }

       public void testJSONArrayIterator() {
          JSONArray jsonArray = new JSONArray();
          jsonArray.add( "1" );
          jsonArray.add( "2" );
          jsonArray.add( "3" );

          List list = new LinkedList();
          list.add( "4" );
          list.add( "5" );
          list.add( "6" );
          jsonArray.add( list );

          List newList = new LinkedList();
          newList.add( "7" );
          newList.add( "8" );
          newList.add( "9" );

          Assertions.assertEquals( JSONArray.fromObject( "['1','2','3',['4','5','6']]" ), jsonArray );

          ListIterator listIterator = jsonArray.listIterator();
          listIterator.add( newList );

          Assertions.assertEquals( JSONArray.fromObject( "[['7','8','9'],'1','2','3',['4','5','6']]" ), jsonArray );
       }

       public void testJSONArray_badFormattedString() {
          String badJson = "[{\"a\":\"b\"},";
          try {
             JSONArray.fromObject(badJson);
             fail("Expecting a syntax error from JSONTokener.");
          }catch( JSONException jsone ) {
             assertTrue( jsone.getMessage().startsWith( "Found starting '[' but missing ']' at the end." ));
          }
       }

       public void testJSONObject_badFormattedString() {
          String badJson = "{\"a\":\"b\"},";
          try {
             JSONObject.fromObject(badJson);
             fail("Expecting a syntax error from JSONTokener.");
          }catch( JSONException jsone ) {
             assertTrue( jsone.getMessage().startsWith( "Found starting '{' but missing '}' at the end." ));
          }
    */

    @Test
    void testJsonWithNullKeys() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        map.put(null, "value2");

        Object[] obj = {map};

        assertThrows(JSONException.class, () -> JSONSerializer.toJSON(obj));
    }

    @Test
    void testJsonWithNullKeys2() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        map.put(null, "value2");

        assertThrows(JSONException.class, () -> JSONSerializer.toJSON(map));
    }

    @Test
    void testJSONArray_JavascriptCompliant() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJavascriptCompliant(true);
        String json = "[null, undefined]";
        JSONArray array = JSONArray.fromObject(json, jsonConfig);
        assertNotNull(array);
        Assertions.assertEquals(JSONNull.getInstance(), array.get(1));
    }

    @Test
    void testJSONArray_JavascriptComplian2t() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJavascriptCompliant(true);
        JSONArray array = new JSONArray();
        array.element("null", jsonConfig);
        array.element("undefined", jsonConfig);
        assertNotNull(array);
        Assertions.assertEquals("null", array.get(0));
        Assertions.assertEquals("undefined", array.get(1));
    }

    @Test
    void testJSONObject_JavascriptCompliant() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJavascriptCompliant(true);
        String json = "{key1: null, key2: undefined}";
        JSONObject object = JSONObject.fromObject(json, jsonConfig);
        assertNotNull(object);
        Assertions.assertEquals(JSONNull.getInstance(), object.get("key2"));
    }

    @Test
    void testJSONObject_JavascriptCompliant2() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJavascriptCompliant(true);
        JSONObject object = new JSONObject();
        object.element("key1", "null", jsonConfig);
        object.element("key2", "undefined", jsonConfig);
        assertNotNull(object);
        Assertions.assertEquals("undefined", object.get("key2"));
    }

    @Test
    void testJSONObject_fromObject_FieldBean() {
        JsonConfig jsonConfig = new JsonConfig();
        FieldBean bean = new FieldBean();
        bean.setValue(42);
        bean.string = "stringy";

        jsonConfig.setIgnorePublicFields(true);
        JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
        assertNotNull(jsonObject);
        assertEquals(42, jsonObject.getInt("value"));
        assertFalse(jsonObject.has("string"));

        jsonConfig.setIgnorePublicFields(false);
        jsonObject = JSONObject.fromObject(bean, jsonConfig);
        assertNotNull(jsonObject);
        assertEquals(42, jsonObject.getInt("value"));
        assertEquals("stringy", jsonObject.getString("string"));
    }

    @Test
    void testJSONObject_toBean_FieldBean() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(FieldBean.class);

        JSONObject jsonObject = new JSONObject();
        jsonConfig.setIgnorePublicFields(true);
        jsonObject.element("value", 42);
        jsonObject.element("string", "stringy");
        FieldBean bean1 = (FieldBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean1);
        assertEquals(42, bean1.getValue());
        assertNull(bean1.string);

        jsonConfig.setIgnorePublicFields(false);
        FieldBean bean2 = (FieldBean) JSONObject.toBean(jsonObject, jsonConfig);
        assertNotNull(bean2);
        assertEquals(42, bean1.getValue());
        assertEquals("stringy", bean2.string);
    }

    @Test
    void testBug_2692698() {
        String input =
                "[\"{'selectedOperatorIndex':2,'leftSideValue':'report.field9','rightSideValue':'2009-3-1'}\",\"all\"]";
        JSON json = JSONArray.fromObject(input);
        String output = json.toString();
        assertEquals(input, output);
    }

    @Test
    void testWithoutAnyTroubleTheyMayBeAlreadyDoneByOtherTest() {
        JsonConfig tConfig = new JsonConfig();
        tConfig.enableEventTriggering();
        tConfig.addJsonEventListener(new JsonErrorDetector());

        // String
        JSONObject jsonObject = JSONObject.fromObject("{\"string\":\"aString\"}", tConfig);
        assertTrue(jsonObject.containsKey("string"), "L'objet doit contenir une clef \"string\"");
        assertNotNull(jsonObject.optString("string"), "Le membre \"string\" doit être une String");
        assertEquals("aString", jsonObject.get("string"), "Le membre \"string\" doit être égal a \"aString\"");

        // int
        jsonObject = JSONObject.fromObject("{\"integer\":10}", tConfig);
        assertTrue(jsonObject.containsKey("integer"), "L'objet doit contenir une clef \"integer\"");
        assertEquals(10, jsonObject.optInt("integer"), "Le membre \"integer\" doit être égal a 10");

        // boolean
        jsonObject = JSONObject.fromObject("{\"double\":2.02}", tConfig);
        assertTrue(jsonObject.containsKey("double"), "L'objet doit contenir une clef \"double\"");
        assertEquals(2.02d, jsonObject.optDouble("double"), 0.0001, "Le membre \"double\" doit être égal a 2.02");

        // double
        jsonObject = JSONObject.fromObject("{\"boolean\":true}", tConfig);
        assertTrue(jsonObject.containsKey("boolean"), "L'objet doit contenir une clef \"boolean\"");
        assertTrue(jsonObject.optBoolean("boolean"), "Le membre \"boolean\" doit être égal a true");

        // String array
        jsonObject = JSONObject.fromObject("{\"strArray\":[\"a\",\"b\",\"c\"]}", tConfig);
        assertTrue(jsonObject.containsKey("strArray"), "L'objet doit contenir une clef \"strArray\"");
        JSONArray strArray = jsonObject.optJSONArray("strArray");
        assertNotNull(strArray, "Le membre \"strArray\" doit être une Array");
        assertEquals("a", strArray.optString(0), "L'element 0 de \"strArray\" doit être égal a \"a\"");
        assertEquals("b", strArray.optString(1), "L'element 1 de \"strArray\" doit être égal a \"b\"");
        assertEquals("c", strArray.optString(2), "L'element 2 de \"strArray\" doit être égal a \"c\"");

        // int array
        jsonObject = JSONObject.fromObject("{\"intArray\":[1,2,3]}", tConfig);
        assertTrue(jsonObject.containsKey("intArray"), "L'objet doit contenir une clef \"intArray\"");
        JSONArray intArray = jsonObject.optJSONArray("intArray");
        assertNotNull(intArray, "Le membre \"intArray\" doit être une Array");
        assertEquals(1, intArray.optInt(0), "L'element 0 de \"intArray\" doit être égal a 1");
        assertEquals(2, intArray.optInt(1), "L'element 1 de \"intArray\" doit être égal a 2");
        assertEquals(3, intArray.optInt(2), "L'element 2 de \"intArray\" doit être égal a 3");

        // boolean array
        jsonObject = JSONObject.fromObject("{\"booleanArray\":[true, false, true]}", tConfig);
        assertTrue(jsonObject.containsKey("booleanArray"), "L'objet doit contenir une clef \"booleanArray\"");
        JSONArray booleanArray = jsonObject.optJSONArray("booleanArray");
        assertNotNull(booleanArray, "Le membre \"strArray\" doit être une booleanArray");
        assertTrue(booleanArray.optBoolean(0), "L'element 0 de \"booleanArray\" doit être égal a true");
        assertFalse(booleanArray.optBoolean(1), "L'element 1 de \"booleanArray\" doit être égal a false");
        assertTrue(booleanArray.optBoolean(2), "L'element 2 de \"booleanArray\" doit être égal a true");

        // double array
        jsonObject = JSONObject.fromObject("{\"doubleArray\":[\"a\",\"b\",\"c\"]}", tConfig);
        assertTrue(jsonObject.containsKey("doubleArray"), "L'objet doit contenir une clef \"doubleArray\"");
        JSONArray doubleArray = jsonObject.optJSONArray("doubleArray");
        assertNotNull(doubleArray, "Le membre \"doubleArray\" doit être une Array");
        assertEquals("a", doubleArray.optString(0), "L'element 0 de \"doubleArray\" doit être égal a \"a\"");

        jsonObject = JSONObject.fromObject("{\"weirdString\":\"[Hello]\"}", tConfig);
        assertTrue(jsonObject.containsKey("weirdString"), "L'objet doit contenir une clef \"weirdString\"");
        assertNotNull(jsonObject.optString("weirdString"), "Le membre \"weirdString\" doit être une String");
        assertEquals(
                "[Hello]", jsonObject.get("weirdString"), "Le membre \"weirdString\" doit être égal a \"[Hello]\"");
        jsonObject = JSONObject.fromObject("{\"weirdString\":\"{912}\"}");
        assertTrue(jsonObject.containsKey("weirdString"), "L'objet doit contenir une clef \"weirdString\"");
        assertNotNull(jsonObject.optString("weirdString"), "Le membre \"weirdString\" doit être une String");
        assertEquals("{912}", jsonObject.get("weirdString"), "Le membre \"weirdString\" doit être égal a \"{912}\"");
    }
    /*
    public void testDifferenceBetweenStringSerialisationWithJSONObjectAndJSONArray() {
       JsonConfig tConfig = new JsonConfig();
       tConfig.enableEventTriggering();
       tConfig.addJsonEventListener( new JsonErrorDetector() );

       // This was Ko
       JSONObject tJsonSource = new JSONObject();
       tJsonSource.element( "weirdString", "[{}][:,;:.[][[]", jsonConfig );
       assertEquals( "[{}][:,;:.[][[]", tJsonSource.get( "weirdString" ) );

       String tExpected = "{\"weirdString\":\"[{}][:,;:.[][[]\"}";
       assertEquals( tExpected, tJsonSource.toString() );

       // This was Ko too
       tJsonSource = new JSONObject();
       JSONArray tArraySource = new JSONArray();
       tArraySource.element( "{912}", jsonConfig );
       tArraySource.element( "[Hello]", jsonConfig );
       tArraySource.element( "[]{}[,;.:[[]", jsonConfig );
       assertEquals( "[]{}[,;.:[[]", tArraySource.get( 2 ) );
       tJsonSource.put( "weirdStringArray", tArraySource );

       tExpected = "{\"weirdStringArray\":[\"{912}\",\"[Hello]\",\"[]{}[,;.:[[]\"]}";
       assertEquals( tExpected, tJsonSource.toString() );
    }*/
    /*
    public void testDifferenceBetweenStringParsingIntoJSONObjectAndJSONArray() {
       JsonConfig tConfig = new JsonConfig();
       tConfig.enableEventTriggering();
       tConfig.addJsonEventListener( new JsonErrorDetector() );

       // This part was Ok
       JSONObject jsonObject = JSONObject.fromObject( "{\"weirdString\":\"[{}][:,;:.[][[]\"}", tConfig );
       assertTrue( jsonObject.containsKey( "weirdString" ) );
       assertNotNull( jsonObject.optString( "weirdString" ) );
       assertEquals( "[{}][:,;:.[][[]", jsonObject.get( "weirdString" ) );

       // This part very similar to the previous part was Ko
       jsonObject = JSONObject.fromObject( "{\"weirdStringArray\":[\"{912}\",\"[Hello]\",\"[]{}[,;.:[[]\"]}", tConfig );
       assertTrue( jsonObject.containsKey( "weirdStringArray" ) );
       assertNotNull( jsonObject.optJSONArray( "weirdStringArray" ) );
       assertEquals( "{912}", jsonObject.getJSONArray( "weirdStringArray" ).optString( 0 ) );
       assertEquals( "[Hello]", jsonObject.getJSONArray( "weirdStringArray" ).optString( 1 ) );
       assertEquals( "[]{}[,;.:[[]", jsonObject.getJSONArray( "weirdStringArray" ).optString( 2 ) );
    }
    */

    @Test
    void testBug_2893329() {
        String jsonStr = "{x:\"\\'hello\\'\"}";
        JSONObject json = JSONObject.fromObject(jsonStr);
        assertEquals("'hello'", json.getString("x"));
    }

    @Test
    void testBug_3047519() {
        String jsonStr = "{data:\"[1,2,3]\"}";
        JSONObject json = JSONObject.fromObject(jsonStr);
        Object data = json.get("data");
        assertInstanceOf(String.class, data);
        assertEquals("[1,2,3]", data);
    }
    /*
    public void testBug_3074732() {
        String test = "{\"c\":\"{\\\"k\\\":\\\"k\\\", \\\"l\\\":\\\"l\\\"}\"}";
        JSONObject jsonObject = JSONObject.fromObject(test);
        assertTrue(jsonObject.get("c") instanceof String);

        String test2 = "{\"a\":[{\"c\":\"{\\\"k\\\":\\\"k\\\", \\\"l\\\":\\\"l\\\"}\"}]}";
        jsonObject = JSONObject.fromObject(test2);
        assertTrue(jsonObject.getJSONArray("a").getJSONObject(0).get("c") instanceof String);
    }
    */
    public static class RunnableImpl implements Runnable {
        @Override
        public void run() {}
    }

    public static class JsonErrorDetector implements JsonEventListener {
        @Override
        public void onArrayEnd() {}

        @Override
        public void onArrayStart() {}

        @Override
        public void onElementAdded(int index, Object element) {}

        @Override
        public void onError(JSONException jsone) {
            fail("An error occurs during JsonProcessing " + jsone.getMessage());
        }

        @Override
        public void onObjectEnd() {}

        @Override
        public void onObjectStart() {}

        @Override
        public void onPropertySet(String key, Object value, boolean accumulated) {}

        @Override
        public void onWarning(String warning) {}
    }

    @BeforeEach
    void setUp() {
        jsonConfig = new JsonConfig();
    }
}

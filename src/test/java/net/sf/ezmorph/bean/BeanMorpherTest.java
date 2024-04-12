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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
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

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class BeanMorpherTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BeanMorpherTest.class );
      suite.setName( "BeanMorpher Tests" );
      return suite;
   }

   private MorpherRegistry morpherRegistry;

   public BeanMorpherTest( String name )
   {
      super( name );
   }

   public void testException_array_class()
   {
      try{
         new BeanMorpher( Map[].class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_Collection_subclass()
   {
      try{
         new BeanMorpher( ArrayList.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_DynaBean_class()
   {
      try{
         new BeanMorpher( MorphDynaBean.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_interface_class()
   {
      try{
         new BeanMorpher( Map.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_Map_subclass()
   {
      try{
         new BeanMorpher( HashMap.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_null_class()
   {
      try{
         new BeanMorpher( null, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_null_morpherRegistry()
   {
      try{
         new BeanMorpher( BeanA.class, null );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_primitive_class()
   {
      try{
         new BeanMorpher( int.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_String_class()
   {
      try{
         new BeanMorpher( String.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testException_wrapper_class()
   {
      try{
         new BeanMorpher( Integer.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
      try{
         new BeanMorpher( Boolean.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
      try{
         new BeanMorpher( Character.class, morpherRegistry );
         fail( "Expected an MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_dynaBean() throws Exception
   {
      Map properties = new HashMap();
      properties.put( "string", String.class );
      properties.put( "integer", Integer.class );
      properties.put( "bool", Boolean.class );
      MorphDynaClass dynaClass = new MorphDynaClass( properties );
      MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
      dynaBean.setDynaBeanClass( dynaClass );
      dynaBean.set( "string", "dyna morph" );
      dynaBean.set( "integer", "24" );
      dynaBean.set( "bool", "false" );

      BeanMorpher morpher = new BeanMorpher( BeanA.class, morpherRegistry );
      BeanA beanA = (BeanA) morpher.morph( dynaBean );
      assertNotNull( beanA );
      assertEquals( false, beanA.isBool() );
      assertEquals( 24, beanA.getInteger() );
      assertEquals( "dyna morph", beanA.getString() );
   }

   public void testMorph_dynaBean_missingProperty() throws Exception
   {
      Map properties = new HashMap();
      properties.put( "integer", Integer.class );
      properties.put( "bool", Boolean.class );
      MorphDynaClass dynaClass = new MorphDynaClass( properties );
      MorphDynaBean dynaBean = (MorphDynaBean) dynaClass.newInstance();
      dynaBean.setDynaBeanClass( dynaClass );
      dynaBean.set( "integer", "24" );
      dynaBean.set( "bool", "false" );

      BeanMorpher morpher = new BeanMorpher( BeanA.class, morpherRegistry );
      BeanA beanA = (BeanA) morpher.morph( dynaBean );
      assertNotNull( beanA );
      assertEquals( false, beanA.isBool() );
      assertEquals( 24, beanA.getInteger() );
      assertEquals( "morph", beanA.getString() );
   }
   
   public void testMorph_nested__dynaBeans() throws Exception
   {
      Map properties = new HashMap();
      properties.put( "string", String.class );
      properties.put( "integer", Integer.class );
      properties.put( "bool", Boolean.class );
      MorphDynaClass dynaClass = new MorphDynaClass( properties );
      MorphDynaBean dynaBeanA = (MorphDynaBean) dynaClass.newInstance( morpherRegistry );
      dynaBeanA.setDynaBeanClass( dynaClass );
      dynaBeanA.set( "string", "dyna morph" );
      dynaBeanA.set( "integer", "24" );
      dynaBeanA.set( "bool", "false" );

      properties = new HashMap();
      properties.put( "string", String.class );
      properties.put( "integer", Integer.class );
      properties.put( "bool", Boolean.class );
      properties.put( "intarray", int[].class );
      dynaClass = new MorphDynaClass( properties );
      MorphDynaBean dynaBeanB = (MorphDynaBean) dynaClass.newInstance( morpherRegistry );
      dynaBeanB.setDynaBeanClass( dynaClass );
      dynaBeanB.set( "string", "dyna morph B" );
      dynaBeanB.set( "integer", "48" );
      dynaBeanB.set( "bool", "true" );
      dynaBeanB.set( "intarray", new int[] { 4, 5, 6 } );

      properties = new HashMap();
      properties.put( "beanA", DynaBean.class );
      properties.put( "beanB", DynaBean.class );
      dynaClass = new MorphDynaClass( properties );
      MorphDynaBean dynaBeanC = (MorphDynaBean) dynaClass.newInstance( morpherRegistry );
      dynaBeanC.setDynaBeanClass( dynaClass );
      dynaBeanC.set( "beanA", dynaBeanA );
      dynaBeanC.set( "beanB", dynaBeanB );

      morpherRegistry.registerMorpher( new BeanMorpher( BeanA.class, morpherRegistry ) );
      morpherRegistry.registerMorpher( new BeanMorpher( BeanB.class, morpherRegistry ) );
      BeanMorpher morpher = new BeanMorpher( BeanC.class, morpherRegistry );
      BeanC beanC = (BeanC) morpher.morph( dynaBeanC );
      assertNotNull( beanC );
      BeanA beanA = beanC.getBeanA();
      assertEquals( false, beanA.isBool() );
      assertEquals( 24, beanA.getInteger() );
      assertEquals( "dyna morph", beanA.getString() );
      BeanB beanB = beanC.getBeanB();
      assertEquals( true, beanB.isBool() );
      assertEquals( 48, beanB.getInteger() );
      assertEquals( "dyna morph B", beanB.getString() );
      ArrayAssertions.assertEquals( new int[] { 4, 5, 6 }, beanB.getIntarray() );
   }

   public void testMorph_null()
   {
      BeanMorpher morpher = new BeanMorpher( BeanA.class, morpherRegistry );
      BeanA beanA = (BeanA) morpher.morph( null );
      assertNull( beanA );
   }

   public void testMorph_ObjectBean_to_PrimitiveBean()
   {
      ObjectBean objectBean = new ObjectBean();
      objectBean.setPclass( Object.class );
      objectBean.setPstring( "MORPH" );
      morpherRegistry.registerMorpher( new BeanMorpher( PrimitiveBean.class, morpherRegistry ) );
      morpherRegistry.registerMorpher( new BeanMorpher( ObjectBean.class, morpherRegistry ) );
      PrimitiveBean primitiveBean = (PrimitiveBean) morpherRegistry.morph( PrimitiveBean.class,
            objectBean );
      assertNotNull( primitiveBean );
      assertEquals( false, primitiveBean.isPboolean() );
      assertEquals( (byte) 0, primitiveBean.getPbyte() );
      assertEquals( (short) 0, primitiveBean.getPshort() );
      assertEquals( 0, primitiveBean.getPint() );
      assertEquals( 0L, primitiveBean.getPlong() );
      assertEquals( 0f, primitiveBean.getPfloat(), 0f );
      assertEquals( 0d, primitiveBean.getPdouble(), 0d );
      assertEquals( '\0', primitiveBean.getPchar() );
      assertEquals( null, primitiveBean.getParray() );
      assertEquals( null, primitiveBean.getPlist() );
      assertEquals( null, primitiveBean.getPbean() );
      assertEquals( null, primitiveBean.getPmap() );
      assertEquals( "MORPH", primitiveBean.getPstring() );
      assertEquals( Object.class, primitiveBean.getPclass() );
   }

   public void testMorph_ObjectBean_to_PrimitiveBean_lenient()
   {
      ObjectBean objectBean = new ObjectBean();
      objectBean.setPclass( Object.class );
      objectBean.setPstring( "MORPH" );
      objectBean.setPbean( objectBean );
      morpherRegistry.registerMorpher( new BeanMorpher( PrimitiveBean.class, morpherRegistry, true ) );
      PrimitiveBean primitiveBean = (PrimitiveBean) morpherRegistry.morph( PrimitiveBean.class,
            objectBean );
      assertNotNull( primitiveBean );
      assertEquals( false, primitiveBean.isPboolean() );
      assertEquals( (byte) 0, primitiveBean.getPbyte() );
      assertEquals( (short) 0, primitiveBean.getPshort() );
      assertEquals( 0, primitiveBean.getPint() );
      assertEquals( 0L, primitiveBean.getPlong() );
      assertEquals( 0f, primitiveBean.getPfloat(), 0f );
      assertEquals( 0d, primitiveBean.getPdouble(), 0d );
      assertEquals( '\0', primitiveBean.getPchar() );
      assertEquals( null, primitiveBean.getParray() );
      assertEquals( null, primitiveBean.getPlist() );
      assertEquals( null, primitiveBean.getPbean() );
      assertEquals( null, primitiveBean.getPmap() );
      assertEquals( "MORPH", primitiveBean.getPstring() );
      assertEquals( Object.class, primitiveBean.getPclass() );
   }

   public void testMorph_ObjectBean_to_PrimitiveBean_notLenient()
   {
      ObjectBean objectBean = new ObjectBean();
      objectBean.setPclass( Object.class );
      objectBean.setPstring( "MORPH" );
      objectBean.setPbean( objectBean );
      morpherRegistry.registerMorpher( new BeanMorpher( PrimitiveBean.class, morpherRegistry ) );
      try{
         morpherRegistry.morph( PrimitiveBean.class, objectBean );
         fail( "Should have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_ObjectBean_to_TypedBean()
   {
      ObjectBean objectBean = new ObjectBean();
      objectBean.setPclass( Object.class );
      objectBean.setPstring( "MORPH" );
      morpherRegistry.registerMorpher( new BeanMorpher( TypedBean.class, morpherRegistry ) );
      morpherRegistry.registerMorpher( new BeanMorpher( ObjectBean.class, morpherRegistry ) );
      TypedBean typedBean = (TypedBean) morpherRegistry.morph( TypedBean.class, objectBean );
      assertNotNull( typedBean );
      assertEquals( null, typedBean.getPboolean() );
      assertEquals( null, typedBean.getPbyte() );
      assertEquals( null, typedBean.getPshort() );
      assertEquals( null, typedBean.getPint() );
      assertEquals( null, typedBean.getPlong() );
      assertEquals( null, typedBean.getPfloat() );
      assertEquals( null, typedBean.getPdouble() );
      assertEquals( null, typedBean.getPchar() );
      assertEquals( null, typedBean.getParray() );
      assertEquals( null, typedBean.getPlist() );
      assertEquals( null, typedBean.getPbean() );
      assertEquals( null, typedBean.getPmap() );
      assertEquals( "MORPH", typedBean.getPstring() );
      assertEquals( Object.class, typedBean.getPclass() );
   }

   public void testMorph_PrimitiveBean_to_ObjectBean()
   {
      PrimitiveBean primitiveBean = new PrimitiveBean();
      primitiveBean.setPclass( Object.class );
      primitiveBean.setPstring( "MORPH" );
      morpherRegistry.registerMorpher( new BeanMorpher( ObjectBean.class, morpherRegistry ) );
      ObjectBean objectBean = (ObjectBean) morpherRegistry.morph( ObjectBean.class, primitiveBean );
      assertNotNull( objectBean );
      assertEquals( Boolean.FALSE, objectBean.getPboolean() );
      assertEquals( Byte.valueOf( "0" ), objectBean.getPbyte() );
      assertEquals( Short.valueOf( "0" ), objectBean.getPshort() );
      assertEquals( Integer.valueOf( "0" ), objectBean.getPint() );
      assertEquals( Long.valueOf( "0" ), objectBean.getPlong() );
      assertEquals( Float.valueOf( "0" ), objectBean.getPfloat() );
      assertEquals( Double.valueOf( "0" ), objectBean.getPdouble() );
      assertEquals( new Character( '\0' ), objectBean.getPchar() );
      assertEquals( null, objectBean.getParray() );
      assertEquals( null, objectBean.getPlist() );
      assertEquals( null, objectBean.getPbean() );
      assertEquals( null, objectBean.getPmap() );
      assertEquals( "MORPH", objectBean.getPstring() );
      assertEquals( Object.class, objectBean.getPclass() );
   }

   public void testMorph_PrimitiveBean_to_TypedBean()
   {
      PrimitiveBean primitiveBean = new PrimitiveBean();
      primitiveBean.setPclass( Object.class );
      primitiveBean.setPstring( "MORPH" );
      morpherRegistry.registerMorpher( new BeanMorpher( TypedBean.class, morpherRegistry ) );
      TypedBean typedBean = (TypedBean) morpherRegistry.morph( TypedBean.class, primitiveBean );
      assertNotNull( typedBean );
      assertEquals( Boolean.FALSE, typedBean.getPboolean() );
      assertEquals( Byte.valueOf( "0" ), typedBean.getPbyte() );
      assertEquals( Short.valueOf( "0" ), typedBean.getPshort() );
      assertEquals( Integer.valueOf( "0" ), typedBean.getPint() );
      assertEquals( Long.valueOf( "0" ), typedBean.getPlong() );
      assertEquals( Float.valueOf( "0" ), typedBean.getPfloat() );
      assertEquals( Double.valueOf( "0" ), typedBean.getPdouble() );
      assertEquals( new Character( '\0' ), typedBean.getPchar() );
      assertEquals( null, typedBean.getParray() );
      assertEquals( null, typedBean.getPlist() );
      assertEquals( null, typedBean.getPbean() );
      assertEquals( null, typedBean.getPmap() );
      assertEquals( "MORPH", typedBean.getPstring() );
      assertEquals( Object.class, typedBean.getPclass() );
   }

   public void testMorph_unsupported()
   {
      BeanMorpher morpher = new BeanMorpher( BeanA.class, morpherRegistry );
      try{
         morpher.morph( new Object[0] );
         fail( "Should vae thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_BeanA_to_BeanD()
   {
      morpherRegistry.registerMorpher( new BeanMorpher( BeanD.class, morpherRegistry ) );
      BeanA beanA = new BeanA();
      beanA.setBool( false );
      beanA.setInteger( 84 );
      beanA.setString( "string" );
      BeanD beanD = (BeanD) morpherRegistry.morph( BeanD.class, beanA );
      assertNotNull( beanD );
      assertFalse( beanD.isBool() );
      assertEquals( 84, beanD.getInteger() );
      assertEquals( 0d, beanD.getDecimal(), 0d );
   }

   protected void setUp() throws Exception
   {
      morpherRegistry = new MorpherRegistry();
      MorphUtils.registerStandardMorphers( morpherRegistry );
   }
}
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class MorphDynaClassTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( MorphDynaClassTest.class );
      suite.setName( "MorphDynaClass Tests" );
      return suite;
   }

   public void testConstructor_emptyAttributes()
   {
      MorphDynaClass dynaClass = new MorphDynaClass( null );
      assertEquals( 0, dynaClass.getDynaProperties().length );
      dynaClass = new MorphDynaClass( new HashMap() );
      assertEquals( 0, dynaClass.getDynaProperties().length );
   }

   public void testConstructor_emptyAttributes_throwException()
   {
      try{
         new MorphDynaClass( null, true );
         fail( "Expected a MorphException" );
      }
      catch( MorphException exception ){
         // ok
      }

      try{
         new MorphDynaClass( new HashMap(), true );
         fail( "Expected a MorphException" );
      }
      catch( MorphException exception ){
         // ok
      }
   }

   public void testEquals()
   {
      Map properties = new HashMap();
      properties.put( "byte", Byte.class );
      Map props = new HashMap();
      props.put( "byte", byte.class );
      MorphDynaClass class1 = new MorphDynaClass( properties );
      MorphDynaClass class2 = new MorphDynaClass( properties );
      MorphDynaClass class3 = new MorphDynaClass( props );

      assertFalse( class1.equals( null ) );
      assertTrue( class1.equals( class1 ) );
      assertTrue( class1.equals( class2 ) );
      assertFalse( class1.equals( new Object() ) );
      assertFalse( class1.equals( class3 ) );
   }

   public void testGetDynaProperty_null()
   {
      try{
         Map properties = new HashMap();
         properties.put( "obj", Object.class.getName() );
         MorphDynaClass dynaClass = new MorphDynaClass( properties );
         dynaClass.getDynaProperty( null );
         fail( "Expected a MorphException" );
      }
      catch( MorphException exception ){
         // ok
      }
   }

   public void testHashcode()
   {
      Map properties = new HashMap();
      properties.put( "byte", Byte.class );
      Map props = new HashMap();
      props.put( "byte", byte.class );
      MorphDynaClass class1 = new MorphDynaClass( properties );
      MorphDynaClass class2 = new MorphDynaClass( properties );
      MorphDynaClass class3 = new MorphDynaClass( props );

      assertEquals( class1.hashCode(), class1.hashCode() );
      assertEquals( class1.hashCode(), class2.hashCode() );
      assertTrue( class1.hashCode() != class3.hashCode() );
   }

   public void testMiscelaneousClasses()
   {
      Map properties = new HashMap();
      properties.put( "byte", Byte.class );
      properties.put( "short", Short.class );
      properties.put( "int", Integer.class );
      properties.put( "long", Long.class );
      properties.put( "float", Float.class );
      properties.put( "double", Double.class );
      properties.put( "bi", BigInteger.class );
      properties.put( "bd", BigDecimal.class );
      properties.put( "boolean", Boolean.class );
      properties.put( "char", Character.class );
      properties.put( "map", Map.class );
      properties.put( "strs", String[].class );
      properties.put( "list", List.class );
      new MorphDynaClass( properties );
   }

   public void testMultidimensionalArrayClass_Class()
   {
      try{
         Map properties = new HashMap();
         properties.put( "array", Object[][].class );
         new MorphDynaClass( properties );
         fail( "Expected a MorphException" );
      }
      catch( MorphException exception ){
         // ok
      }
   }

   public void testMultidimensionalArrayClass_String()
   {
      try{
         Map properties = new HashMap();
         properties.put( "array", Object[][].class.getName() );
         new MorphDynaClass( properties );
         fail( "Expected a MorphException" );
      }
      catch( MorphException exception ){
         // ok
      }
   }
}
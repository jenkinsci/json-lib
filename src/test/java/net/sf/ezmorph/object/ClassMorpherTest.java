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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class ClassMorpherTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( ClassMorpherTest.class );
      suite.setName( "ClassMorpher Tests" );
      return suite;
   }

   private ClassMorpher morpher = ClassMorpher.getInstance();

   public ClassMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testEquals()
   {
      assertTrue( ClassMorpher.getInstance()
            .equals( ClassMorpher.getInstance() ) );
      assertFalse( ClassMorpher.getInstance()
            .equals( StringMorpher.getInstance() ) );
   }

   public void testHashCode()
   {
      assertEquals( ClassMorpher.getInstance()
            .hashCode(), ClassMorpher.getInstance()
            .hashCode() );
      assertTrue( ClassMorpher.getInstance()
            .hashCode() != StringMorpher.getInstance()
            .hashCode() );
   }

   public void testMorph()
   {
      Class expected = Object.class;
      Class actual = (Class) morpher.morph( "java.lang.Object" );
      assertEquals( expected, actual );
   }

   public void testMorph_array()
   {
      try{
         morpher.morph( new boolean[] { true, false } );
         fail( "Expected a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_arrayClass()
   {
      Class expected = int[].class;
      Class actual = (Class) morpher.morph( "[I" );
      assertEquals( expected, actual );
   }

   public void testMorph_class()
   {
      Class expected = Object.class;
      Class actual = (Class) morpher.morph( Object.class );
      assertEquals( expected, actual );
   }

   public void testMorph_null()
   {
      assertNull( morpher.morph( null ) );
   }

   public void testMorph_unknownClassname()
   {
      try{
         morpher.morph( "bogusClass.I.do.not.exist" );
         fail( "Expected a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_withtoString()
   {
      Class expected = MyClass.class;
      Class actual = (Class) morpher.morph( new MyClass() );
      assertEquals( expected, actual );
   }

   public static class MyClass{
      public String toString(){
         return MyClass.class.getName();
      }
   }
}
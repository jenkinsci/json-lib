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

package net.sf.ezmorph.array;

import java.lang.reflect.Array;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import net.sf.ezmorph.object.StringMorpher;
import net.sf.ezmorph.test.ArrayAssertions;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class ObjectArrayMorpherTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( ObjectArrayMorpherTest.class );
      suite.setName( "ObjectListMorpher Tests" );
      return suite;
   }

   private ObjectArrayMorpher anotherMorpher;
   private ObjectArrayMorpher morpher;

   public ObjectArrayMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public ObjectArrayMorpher getAnotherMorpher()
   {
      return anotherMorpher;
   }

   public ObjectArrayMorpher getMorpher()
   {
      return morpher;
   }

   public void testEquals_another_Morpher()
   {
      assertFalse( getMorpher().equals( getAnotherMorpher() ) );
   }

   public void testEquals_different_morpher()
   {
      assertFalse( getMorpher().equals( new Morpher(){
         public Class morphsTo()
         {
            return null;
         }

         public boolean supports( Class clazz )
         {
            return false;
         }
      } ) );
   }

   public void testEquals_null()
   {
      assertFalse( getMorpher().equals( null ) );
   }

   public void testEquals_same_morpher()
   {
      assertTrue( getMorpher().equals( getMorpher() ) );
      assertTrue( getAnotherMorpher().equals( getAnotherMorpher() ) );
   }

   public void testHashCode_same_morpher()
   {
      assertEquals( getMorpher().hashCode(), getMorpher().hashCode() );
      assertEquals( getAnotherMorpher().hashCode(), getAnotherMorpher().hashCode() );
   }

   public void testMorph_illegalArgument()
   {
      try{
         // argument is not an array
         morpher.morph( "" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_null()
   {
      assertNull( morpher.morph( null ) );
   }

   public void testMorph_onedim()
   {
      Object[] input = new Object[] { new Integer( 1 ), Boolean.TRUE };
      String[] expected = new String[] { "1", "true" };
      String[] actual = (String[]) morpher.morph( input );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_threedims()
   {
      Object[][][] input = new Object[][][] { { { new Integer( 1 ), Boolean.TRUE } },
            { { new Character( 'A' ) } } };
      String[][][] expected = new String[][][] { { { "1", "true" } }, { { "A" } } };
      String[][][] actual = (String[][][]) morpher.morph( input );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_twodims()
   {
      Object[][] input = new Object[][] { { new Integer( 1 ), Boolean.TRUE },
            { new Character( 'A' ) } };
      String[][] expected = new String[][] { { "1", "true" }, { "A" } };
      String[][] actual = (String[][]) morpher.morph( input );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_twodims_reflection()
   {
      Object input = Array.newInstance( Object.class, new int[] { 2, 2 } );
      Object[] a = new Object[] { new Integer( 1 ), Boolean.TRUE };
      Object[] b = new Object[] { new Character( 'A' ) };
      Array.set( input, 0, a );
      Array.set( input, 1, b );

      String[][] expected = new String[][] { { "1", "true" }, { "A" } };
      String[][] actual = (String[][]) morpher.morph( input );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorphsTo()
   {
      assertEquals( String[].class, getMorpher().morphsTo() );
   }

   public void testObjectArrayMorpher_illegalMorpher_noMorphMethod()
   {
      try{
         morpher = new ObjectArrayMorpher( new Morpher(){
            public Class morphsTo()
            {
               return Object.class;
            }

            public boolean supports( Class clazz )
            {
               return false;
            }
         } );
      }
      catch( IllegalArgumentException expected ){
         // ok
      }
   }

   public void testObjectArrayMorpher_illegalMorpher_nullMorpher()
   {
      try{
         morpher = new ObjectArrayMorpher( null );
      }
      catch( IllegalArgumentException expected ){
         // ok
      }
   }

   public void testObjectArrayMorpher_illegalMorpher_supportsArray()
   {
      try{
         morpher = new ObjectArrayMorpher( new Morpher(){
            public Class morphsTo()
            {
               return Object[].class;
            }

            public boolean supports( Class clazz )
            {
               return false;
            }
         } );
      }
      catch( IllegalArgumentException expected ){
         // ok
      }
   }

   protected void setUp() throws Exception
   {
      morpher = new ObjectArrayMorpher( StringMorpher.getInstance() );
      anotherMorpher = new ObjectArrayMorpher( IdentityObjectMorpher.getInstance() );
   }
}
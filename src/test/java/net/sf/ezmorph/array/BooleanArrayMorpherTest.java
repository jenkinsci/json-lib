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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.test.ArrayAssertions;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class BooleanArrayMorpherTest extends AbstractArrayMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BooleanArrayMorpherTest.class );
      suite.setName( "BooleanArrayMorpher Tests" );
      return suite;
   }

   private BooleanArrayMorpher anotherMorpher;
   private BooleanArrayMorpher anotherMorpherWithDefaultValue;
   private BooleanArrayMorpher morpher;
   private BooleanArrayMorpher morpherWithDefaultValue;

   public BooleanArrayMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testMorph_booleanArray()
   {
      boolean[] expected = { true, false };
      boolean[] actual = (boolean[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_booleanArray_threedims()
   {
      boolean[][][] expected = { { { true }, { false } }, { { true }, { false } } };
      boolean[][][] actual = (boolean[][][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_booleanArray_twodims()
   {
      boolean[][] expected = { { true, false }, { true, false } };
      boolean[][] actual = (boolean[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
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

   public void testMorph_strings()
   {
      String[] expected = { "true", "yes", "on", "false", "no", "off" };
      boolean[] actual = (boolean[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new boolean[] { true, true, true, false, false, false }, actual );
   }

   public void testMorph_strings_twodims()
   {
      String[][] expected = { { "true", "yes", "on" }, { "false", "no", "off" } };
      boolean[][] actual = (boolean[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals(
            new boolean[][] { { true, true, true }, { false, false, false } }, actual );
   }

   public void testMorph_throwException()
   {
      try{
         new BooleanArrayMorpher().morph( new String[] { null } );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   protected AbstractArrayMorpher getAnotherMorpher()
   {
      return anotherMorpher;
   }

   protected AbstractArrayMorpher getAnotherMorpherWithDefaultValue()
   {
      return anotherMorpherWithDefaultValue;
   }

   protected AbstractArrayMorpher getMorpher()
   {
      return morpher;
   }

   protected AbstractArrayMorpher getMorpherWithDefaultValue()
   {
      return morpherWithDefaultValue;
   }

   protected Class getMorphsToClass()
   {
      return boolean[].class;
   }

   protected void setUp() throws Exception
   {
      morpher = new BooleanArrayMorpher();
      morpherWithDefaultValue = new BooleanArrayMorpher( true );
      anotherMorpher = new BooleanArrayMorpher();
      anotherMorpherWithDefaultValue = new BooleanArrayMorpher( false );
   }
}
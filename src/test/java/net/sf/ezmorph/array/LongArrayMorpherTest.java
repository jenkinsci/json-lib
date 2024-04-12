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
public class LongArrayMorpherTest extends AbstractArrayMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( LongArrayMorpherTest.class );
      suite.setName( "LongArrayMorpher Tests" );
      return suite;
   }

   private LongArrayMorpher anotherMorpher;
   private LongArrayMorpher anotherMorpherWithDefaultValue;
   private LongArrayMorpher morpher;
   private LongArrayMorpher morpherWithDefaultValue;

   public LongArrayMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

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

   public void testMorph_longArray()
   {
      long[] expected = { 1, 2, 3 };
      long[] actual = (long[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_longArray_threedims()
   {
      long[][][] expected = { { { 1 }, { 2 } }, { { 3 }, { 4 } } };
      long[][][] actual = (long[][][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_longArray_twodims()
   {
      long[][] expected = { { 1, 2, 3 }, { 4, 5, 6 } };
      long[][] actual = (long[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_null()
   {
      assertNull( morpher.morph( null ) );
   }

   public void testMorph_strings()
   {
      String[] expected = { "1", "2", "3.3" };
      long[] actual = (long[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new long[] { 1, 2, 3 }, actual );
   }

   public void testMorph_strings_twodims()
   {
      String[][] expected = { { "1", "2", "3.3" }, { "4", "5", "6.6" } };
      long[][] actual = (long[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new long[][] { { 1, 2, 3 }, { 4, 5, 6 } }, actual );
   }

   public void testMorph_throwException()
   {
      try{
         new LongArrayMorpher().morph( new String[] { null } );
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
      return long[].class;
   }

   protected void setUp() throws Exception
   {
      morpher = new LongArrayMorpher();
      morpherWithDefaultValue = new LongArrayMorpher( 0 );
      anotherMorpher = new LongArrayMorpher();
      anotherMorpherWithDefaultValue = new LongArrayMorpher( 1 );
   }
}
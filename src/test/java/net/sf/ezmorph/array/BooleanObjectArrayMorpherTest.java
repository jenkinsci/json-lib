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
public class BooleanObjectArrayMorpherTest extends AbstractArrayMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BooleanObjectArrayMorpherTest.class );
      suite.setName( "BooleanObjectArrayMorpher Tests" );
      return suite;
   }

   private BooleanObjectArrayMorpher anotherMorpher;
   private BooleanObjectArrayMorpher anotherMorpherWithDefaultValue;
   private BooleanObjectArrayMorpher morpher;
   private BooleanObjectArrayMorpher morpherWithDefaultValue;

   public BooleanObjectArrayMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testMorph_booleanArray()
   {
      boolean[] expected = { true, false };
      Boolean[] actual = (Boolean[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_BooleanArray()
   {
      Boolean[] expected = { Boolean.TRUE, Boolean.FALSE };
      Boolean[] actual = (Boolean[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_booleanArray_threedims()
   {
      boolean[][][] expected = { { { true }, { false } }, { { true }, { false } } };
      Boolean[][][] actual = (Boolean[][][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_BooleanArray_threedims()
   {
      Boolean[][][] expected = { { { Boolean.TRUE }, { Boolean.FALSE } },
            { { Boolean.TRUE }, { Boolean.FALSE } } };
      Boolean[][][] actual = (Boolean[][][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_booleanArray_twodims()
   {
      boolean[][] expected = { { true, false }, { true, false } };
      Boolean[][] actual = (Boolean[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_BooleanArray_twodims()
   {
      Boolean[][] expected = { { Boolean.TRUE, Boolean.FALSE }, { Boolean.TRUE, Boolean.FALSE } };
      Boolean[][] actual = (Boolean[][]) morpher.morph( expected );
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

   public void testMorph_StringArray_Boolean_default()
   {
      Boolean[] expected = { Boolean.TRUE, Boolean.TRUE };
      morpher = new BooleanObjectArrayMorpher( Boolean.TRUE );
      Boolean[] actual = (Boolean[]) morpher.morph( new String[] { "A", "B" } );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_StringArray_null_default()
   {
      Boolean[] expected = { null, null };
      morpher= new BooleanObjectArrayMorpher( null );
      Boolean[] actual = (Boolean[]) morpher.morph( new String[] { "A", "B" } );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_strings()
   {
      String[] expected = { "true", "yes", "on", "false", "no", "off" };
      Boolean[] actual = (Boolean[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
            Boolean.FALSE, Boolean.FALSE, Boolean.FALSE }, actual );
   }

   public void testMorph_strings_twodims()
   {
      String[][] expected = { { "true", "yes", "on" }, { "false", "no", "off" } };
      Boolean[][] actual = (Boolean[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new Boolean[][] { { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE },
            { Boolean.FALSE, Boolean.FALSE, Boolean.FALSE } }, actual );
   }

   public void testMorph_throwException()
   {
      try{
         new BooleanObjectArrayMorpher().morph( new String[] { "A" } );
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
      return Boolean[].class;
   }

   protected void setUp() throws Exception
   {
      morpher = new BooleanObjectArrayMorpher();
      morpherWithDefaultValue = new BooleanObjectArrayMorpher( Boolean.TRUE );
      anotherMorpher = new BooleanObjectArrayMorpher();
      anotherMorpherWithDefaultValue = new BooleanObjectArrayMorpher( Boolean.FALSE );
   }
}
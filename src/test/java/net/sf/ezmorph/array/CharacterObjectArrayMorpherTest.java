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
public class CharacterObjectArrayMorpherTest extends AbstractArrayMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( CharacterObjectArrayMorpherTest.class );
      suite.setName( "CharacterObjectArrayMorpher Tests" );
      return suite;
   }

   private CharacterObjectArrayMorpher anotherMorpher;
   private CharacterObjectArrayMorpher anotherMorpherWithDefaultValue;
   private CharacterObjectArrayMorpher morpher;
   private CharacterObjectArrayMorpher morpherWithDefaultValue;

   public CharacterObjectArrayMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testMorph_CharacterArray()
   {
      Character[] expected = { new Character( 'A' ), new Character( 'B' ) };
      Character[] actual = (Character[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_CharacterArray_threedims()
   {
      Character[][][] expected = { { { new Character( 'A' ) }, { new Character( 'B' ) } },
            { { new Character( 'A' ) }, { new Character( 'B' ) } } };
      Character[][][] actual = (Character[][][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_CharacterArray_twodims()
   {
      Character[][] expected = { { new Character( 'A' ), new Character( 'B' ) },
            { new Character( 'A' ), new Character( 'B' ) } };
      Character[][] actual = (Character[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_charArray()
   {
      char[] input = { 'A', 'B' };
      Character[] actual = (Character[]) morpher.morph( input );
      ArrayAssertions.assertEquals( new Character[] { new Character( 'A' ), new Character( 'B' ) },
            actual );
   }

   public void testMorph_charArray_threedims()
   {
      char[][][] input = { { { 'A' } }, { { 'B' } } };
      Character[][][] actual = (Character[][][]) morpher.morph( input );
      ArrayAssertions.assertEquals( new Character[][][] { { { new Character( 'A' ) } },
            { { new Character( 'B' ) } } }, actual );
   }

   public void testMorph_charArray_twodims()
   {
      char[][] input = { { 'A' }, { 'B' } };
      Character[][] actual = (Character[][]) morpher.morph( input );
      ArrayAssertions.assertEquals( new Character[][] { { new Character( 'A' ) },
            { new Character( 'B' ) } }, actual );
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

   public void testMorph_IntegerArray_default()
   {
      Character[] expected = { new Character( 'A' ), new Character( 'A' ) };
      morpher = new CharacterObjectArrayMorpher( new Character( 'A' ) );
      Character[] actual = (Character[]) morpher.morph( new Integer[] { null, null } );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_null()
   {
      assertNull( morpher.morph( null ) );
   }

   public void testMorph_StringArray_null_default()
   {
      Character[] expected = { null, null };
      morpher = new CharacterObjectArrayMorpher( null );
      Character[] actual = (Character[]) morpher.morph( new String[] { "A", "B" } );
      ArrayAssertions.assertEquals( expected, actual );
   }

   public void testMorph_strings()
   {
      String[] expected = { "A", "B" };
      Character[] actual = (Character[]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new Character[] { new Character( 'A' ), new Character( 'B' ) },
            actual );
   }

   public void testMorph_strings_twodims()
   {
      String[][] expected = { { "A" }, { "B" } };
      Character[][] actual = (Character[][]) morpher.morph( expected );
      ArrayAssertions.assertEquals( new Character[][] { { new Character( 'A' ) },
            { new Character( 'B' ) } }, actual );
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
      return Character[].class;
   }

   protected void setUp() throws Exception
   {
      morpher = new CharacterObjectArrayMorpher();
      morpherWithDefaultValue = new CharacterObjectArrayMorpher( new Character( 'A' ) );
      anotherMorpher = new CharacterObjectArrayMorpher();
      anotherMorpherWithDefaultValue = new CharacterObjectArrayMorpher( new Character( 'B' ) );
   }
}
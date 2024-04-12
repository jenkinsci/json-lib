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

package net.sf.ezmorph.primitive;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class ByteMorpherTest extends AbstractMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( ByteMorpherTest.class );
      suite.setName( "ByteMorpher Tests" );
      return suite;
   }

   private Morpher anotherMorpher;
   private Morpher anotherMorpherWithDefaultValue;
   private Morpher morpher;
   private Morpher morpherWithDefaultValue;

   public ByteMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testByteMorph_throwException()
   {
      try{
         ((ByteMorpher) getMorpher()).morph( String.valueOf( "A" ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testByteMorph_throwException_null()
   {
      try{
         ((ByteMorpher) getMorpher()).morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testByteMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      byte actual = ((ByteMorpher) getMorpherWithDefaultValue()).morph( expected );
      assertEquals( 0, actual );
   }

   public void testByteMorph_useDefault_null()
   {
      byte actual = ((ByteMorpher) getMorpherWithDefaultValue()).morph( null );
      assertEquals( 0, actual );
   }

   public void testByteMorphDecimalValue_Number()
   {
      Double expected = new Double( 3.1416d );
      byte actual = ((ByteMorpher) getMorpher()).morph( expected );
      assertEquals( 3, actual );
   }

   public void testByteMorphDecimalValue_Number_outOfRange()
   {
      byte actual = ((ByteMorpher) getMorpher()).morph( new Double( Double.MAX_VALUE ) );
      assertEquals( -1, actual );
   }

   public void testByteMorphDecimalValue_String()
   {
      String expected = "3.1416";
      byte actual = ((ByteMorpher) getMorpher()).morph( expected );
      assertEquals( 3, actual );
   }

   public void testByteMorphMaxValue_Number()
   {
      Byte expected = new Byte( Byte.MAX_VALUE );
      byte actual = ((ByteMorpher) getMorpher()).morph( expected );
      assertEquals( expected.byteValue(), actual );
   }

   public void testByteMorphMaxValue_String()
   {
      String expected = String.valueOf( new Byte( Byte.MAX_VALUE ) );
      byte actual = ((ByteMorpher) getMorpher()).morph( expected );
      assertEquals( expected, String.valueOf( actual ) );
   }

   public void testByteMorphMinValue_Number()
   {
      Byte expected = new Byte( Byte.MIN_VALUE );
      byte actual = ((ByteMorpher) getMorpher()).morph( expected );
      assertEquals( expected.byteValue(), actual );
   }

   public void testByteMorphMinValue_String()
   {
      String expected = String.valueOf( new Byte( Byte.MIN_VALUE ) );
      byte actual = ((ByteMorpher) getMorpher()).morph( expected );
      assertEquals( expected, String.valueOf( actual ) );
   }

   protected Morpher getMorpher()
   {
      return morpher;
   }

   protected Morpher getMorpherWithDefaultValue()
   {
      return morpherWithDefaultValue;
   }

   protected Class getMorphsToClass()
   {
      return Byte.TYPE;
   }

   protected Morpher getAnotherMorpher()
   {
      return anotherMorpher;
   }

   protected Morpher getAnotherMorpherWithDefaultValue()
   {
      return anotherMorpherWithDefaultValue;
   }

   protected void setUp() throws Exception
   {
      morpher = new ByteMorpher();
      morpherWithDefaultValue = new ByteMorpher( (byte) 0 );
      anotherMorpher = new ByteMorpher();
      anotherMorpherWithDefaultValue = new ByteMorpher( (byte) 1 );
   }
}
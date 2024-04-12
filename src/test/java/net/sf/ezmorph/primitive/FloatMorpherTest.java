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
public class FloatMorpherTest extends AbstractMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( FloatMorpherTest.class );
      suite.setName( "FloatMorpher Tests" );
      return suite;
   }

   private Morpher anotherMorpher;
   private Morpher anotherMorpherWithDefaultValue;
   private Morpher morpher;
   private Morpher morpherWithDefaultValue;

   public FloatMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testFloatMorph_throwException()
   {
      try{
         ((FloatMorpher) getMorpher()).morph( String.valueOf( "A" ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testFloatMorph_throwException_null()
   {
      try{
         ((FloatMorpher) getMorpher()).morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testFloatMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      float actual = ((FloatMorpher) getMorpherWithDefaultValue()).morph( expected );
      assertEquals( 0f, actual, 0f );
   }

   public void testFloatMorph_useDefault_null()
   {
      float actual = ((FloatMorpher) getMorpherWithDefaultValue()).morph( null );
      assertEquals( 0f, actual, 0f );
   }

   public void testFloatMorphDecimalValue_Number()
   {
      Double expected = new Double( 3.1416d );
      float actual = ((FloatMorpher) getMorpher()).morph( expected );
      assertEquals( 3.1416f, actual, 0f );
   }

   public void testFloatMorphDecimalValue_Number_outOfRange()
   {
      float actual = ((FloatMorpher) getMorpher()).morph( new Double( Double.MAX_VALUE ) );
      assertEquals( Float.POSITIVE_INFINITY, actual, 0f );
   }

   public void testFloatMorphDecimalValue_String()
   {
      String expected = "3.1416";
      float actual = ((FloatMorpher) getMorpher()).morph( expected );
      assertEquals( 3.1416f, actual, 0f );
   }

   public void testFloatMorphMaxValue_Number()
   {
      Float expected = new Float( Float.MAX_VALUE );
      float actual = ((FloatMorpher) getMorpher()).morph( expected );
      assertEquals( expected.floatValue(), actual, 0f );
   }

   public void testFloatMorphMaxValue_String()
   {
      String expected = String.valueOf( new Float( Float.MAX_VALUE ) );
      float actual = ((FloatMorpher) getMorpher()).morph( expected );
      assertEquals( expected, String.valueOf( actual ) );
   }

   public void testFloatMorphMinValue_Number()
   {
      Float expected = new Float( Float.MIN_VALUE );
      float actual = ((FloatMorpher) getMorpher()).morph( expected );
      assertEquals( expected.floatValue(), actual, 0f );
   }

   public void testFloatMorphMinValue_String()
   {
      String expected = String.valueOf( new Float( Float.MIN_VALUE ) );
      float actual = ((FloatMorpher) getMorpher()).morph( expected );
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
      return Float.TYPE;
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
      morpher = new FloatMorpher();
      morpherWithDefaultValue = new FloatMorpher( 0 );
      anotherMorpher = new FloatMorpher();
      anotherMorpherWithDefaultValue = new FloatMorpher( 1 );
   }
}
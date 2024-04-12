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
public class IntMorpherTest extends AbstractMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( IntMorpherTest.class );
      suite.setName( "IntMorpher Tests" );
      return suite;
   }

   private Morpher anotherMorpher;
   private Morpher anotherMorpherWithDefaultValue;
   private Morpher morpher;
   private Morpher morpherWithDefaultValue;

   public IntMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testIntMorph_throwException()
   {
      try{
         ((IntMorpher) getMorpher()).morph( String.valueOf( "A" ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testIntMorph_throwException_null()
   {
      try{
         ((IntMorpher) getMorpher()).morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testIntMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      int actual = ((IntMorpher) getMorpherWithDefaultValue()).morph( expected );
      assertEquals( 0, actual );
   }

   public void testIntMorph_useDefault_null()
   {
      int actual = ((IntMorpher) getMorpherWithDefaultValue()).morph( null );
      assertEquals( 0, actual );
   }

   public void testIntMorphDecimalValue_Number()
   {
      Double expected = new Double( 3.1416d );
      int actual = ((IntMorpher) getMorpher()).morph( expected );
      assertEquals( 3, actual );
   }

   public void testIntMorphDecimalValue_Number_outOfRange()
   {
      int actual = ((IntMorpher) getMorpher()).morph( new Double( Double.MAX_VALUE ) );
      assertEquals( Integer.MAX_VALUE, actual );
   }

   public void testIntMorphDecimalValue_String()
   {
      String expected = "3.1416";
      int actual = ((IntMorpher) getMorpher()).morph( expected );
      assertEquals( 3, actual );
   }

   public void testIntMorphMaxValue_Number()
   {
      Integer expected = new Integer( Integer.MAX_VALUE );
      int actual = ((IntMorpher) getMorpher()).morph( expected );
      assertEquals( expected.intValue(), actual );
   }

   public void testIntMorphMaxValue_String()
   {
      String expected = String.valueOf( new Integer( Integer.MAX_VALUE ) );
      int actual = ((IntMorpher) getMorpher()).morph( expected );
      assertEquals( expected, String.valueOf( actual ) );
   }

   public void testIntMorphMinValue_Number()
   {
      Integer expected = new Integer( Integer.MIN_VALUE );
      int actual = ((IntMorpher) getMorpher()).morph( expected );
      assertEquals( expected.intValue(), actual );
   }

   public void testIntMorphMinValue_String()
   {
      String expected = String.valueOf( new Integer( Integer.MIN_VALUE ) );
      int actual = ((IntMorpher) getMorpher()).morph( expected );
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
      return Integer.TYPE;
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
      morpher = new IntMorpher();
      morpherWithDefaultValue = new IntMorpher( 0 );
      anotherMorpher = new IntMorpher();
      anotherMorpherWithDefaultValue = new IntMorpher( 1 );
   }
}
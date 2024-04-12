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
public class LongMorpherTest extends AbstractMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( LongMorpherTest.class );
      suite.setName( "LongMorpher Tests" );
      return suite;
   }

   private Morpher anotherMorpher;
   private Morpher anotherMorpherWithDefaultValue;
   private Morpher morpher;
   private Morpher morpherWithDefaultValue;

   public LongMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testLongMorph_throwException()
   {
      try{
         ((LongMorpher) getMorpher()).morph( String.valueOf( "A" ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testLongMorph_throwException_null()
   {
      try{
         ((LongMorpher) getMorpher()).morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testLongMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      long actual = ((LongMorpher) getMorpherWithDefaultValue()).morph( expected );
      assertEquals( 0, actual );
   }

   public void testLongMorph_useDefault_null()
   {
      long actual = ((LongMorpher) getMorpherWithDefaultValue()).morph( null );
      assertEquals( 0, actual );
   }

   public void testLongMorphDecimalValue_Number()
   {
      Double expected = new Double( 3.1416d );
      long actual = ((LongMorpher) getMorpher()).morph( expected );
      assertEquals( 3, actual );
   }

   public void testLongMorphDecimalValue_Number_outOfRange()
   {
      long actual = ((LongMorpher) getMorpher()).morph( new Double( Double.MAX_VALUE ) );
      assertEquals( Long.MAX_VALUE, actual );
   }

   public void testLongMorphDecimalValue_String()
   {
      String expected = "3.1416";
      long actual = ((LongMorpher) getMorpher()).morph( expected );
      assertEquals( 3, actual );
   }

   public void testLongMorphMaxValue_Number()
   {
      Long expected = new Long( Long.MAX_VALUE );
      long actual = ((LongMorpher) getMorpher()).morph( expected );
      assertEquals( expected.longValue(), actual );
   }

   public void testLongMorphMaxValue_String()
   {
      String expected = String.valueOf( new Long( Long.MAX_VALUE ) );
      long actual = ((LongMorpher) getMorpher()).morph( expected );
      assertEquals( expected, String.valueOf( actual ) );
   }

   public void testLongMorphMinValue_Number()
   {
      Long expected = new Long( Long.MIN_VALUE );
      long actual = ((LongMorpher) getMorpher()).morph( expected );
      assertEquals( expected.longValue(), actual );
   }

   public void testLongMorphMinValue_String()
   {
      String expected = String.valueOf( new Long( Long.MIN_VALUE ) );
      long actual = ((LongMorpher) getMorpher()).morph( expected );
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
      return Long.TYPE;
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
      morpher = new LongMorpher();
      morpherWithDefaultValue = new LongMorpher( 0 );
      anotherMorpher = new LongMorpher();
      anotherMorpherWithDefaultValue = new LongMorpher( 1 );
   }
}
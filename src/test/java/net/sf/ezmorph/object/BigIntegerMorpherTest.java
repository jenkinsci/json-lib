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

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.Morpher;

public class BigIntegerMorpherTest extends AbstractObjectMorpherTestCase
{

   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BigIntegerMorpherTest.class );
      suite.setName( "BigDecimalMorpher Tests" );
      return suite;
   }

   private BigIntegerMorpher anotherMorpher;
   private BigIntegerMorpher anotherMorpherWithDefaultValue;
   private BigIntegerMorpher morpher;
   private BigIntegerMorpher morpherWithDefaultValue;

   public BigIntegerMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testBigIntegerMorph_BigDecimal()
   {
      Object actual = ((BigIntegerMorpher) getMorpherWithDefaultValue()).morph( MorphUtils.BIGDECIMAL_ZERO );
      assertEquals( BigInteger.ZERO, actual );
   }

   public void testBigIntegerMorph_BigInteger()
   {
      Object actual = ((BigIntegerMorpher) getMorpherWithDefaultValue()).morph( BigInteger.ZERO );
      assertEquals( BigInteger.ZERO, actual );
   }

   public void testBigIntegerMorph_Number()
   {
      Object actual = ((BigIntegerMorpher) getMorpher()).morph( new Byte( (byte) 1 ) );
      assertEquals( BigInteger.ONE, actual );
      actual = ((BigIntegerMorpher) getMorpher()).morph( new Short( (short) 1 ) );
      assertEquals( BigInteger.ONE, actual );
      actual = ((BigIntegerMorpher) getMorpher()).morph( new Integer( 1 ) );
      assertEquals( BigInteger.ONE, actual );
      actual = ((BigIntegerMorpher) getMorpher()).morph( new Long( 1L ) );
      assertEquals( BigInteger.ONE, actual );
      actual = ((BigIntegerMorpher) getMorpher()).morph( new Double( 1d ) );
      assertEquals( BigInteger.ONE, actual );
      actual = ((BigIntegerMorpher) getMorpher()).morph( new Float( 1f ) );
      assertEquals( BigInteger.ONE, actual );
      actual = ((BigIntegerMorpher) getMorpher()).morph( MorphUtils.BIGDECIMAL_ONE );
      assertEquals( BigInteger.ONE, actual );
   }

   public void testBigIntegerMorph_Number__Double_INFINITY()
   {
      try{
         ((BigIntegerMorpher) getMorpher()).morph( new Double( Double.POSITIVE_INFINITY ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBigIntegerMorph_Number__Double_NAN()
   {
      try{
         ((BigIntegerMorpher) getMorpher()).morph( new Double( Double.NaN ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBigIntegerMorph_Number__Float_INFINITY()
   {
      try{
         ((BigIntegerMorpher) getMorpher()).morph( new Float( Float.POSITIVE_INFINITY ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBigIntegerMorph_Number__Float_NAN()
   {
      try{
         ((BigIntegerMorpher) getMorpher()).morph( new Float( Float.NaN ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBigIntegerMorph_String__decimal()
   {
      Object actual = ((BigIntegerMorpher) getMorpherWithDefaultValue()).morph( "123.45" );
      assertEquals( new BigInteger( "123" ), actual );
   }

   public void testBigIntegerMorph_String__int()
   {
      Object actual = ((BigIntegerMorpher) getMorpherWithDefaultValue()).morph( "123" );
      assertEquals( new BigInteger( "123" ), actual );
   }

   public void testBigIntegerMorph_String_empty()
   {
      assertNull( ((BigIntegerMorpher) getMorpher()).morph( "" ) );
   }

   public void testBigIntegerMorph_String_null()
   {
      assertNull( ((BigIntegerMorpher) getMorpher()).morph( null ) );
   }

   public void testBigIntegerMorph_String_null2()
   {
      assertNull( ((BigIntegerMorpher) getMorpher()).morph( "null" ) );
   }

   public void testBigIntegerMorph_throwException()
   {
      try{
         ((BigIntegerMorpher) getMorpher()).morph( String.valueOf( "A" ) );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBigIntegerMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      Object actual = ((BigIntegerMorpher) getMorpherWithDefaultValue()).morph( expected );
      assertEquals( BigInteger.ZERO, actual );
   }

   public void testBigIntegerMorph_useDefault_null()
   {
      Object actual = ((BigIntegerMorpher) getMorpherWithDefaultValue()).morph( null );
      assertEquals( BigInteger.ZERO, actual );
   }

   protected Morpher getAnotherMorpher()
   {
      return anotherMorpher;
   }

   protected Morpher getAnotherMorpherWithDefaultValue()
   {
      return anotherMorpherWithDefaultValue;
   }

   protected Morpher getMorpher()
   {
      return morpher;
   }

   protected Morpher getMorpherWithDefaultValue()
   {
      return morpherWithDefaultValue;
   }

   protected void setUp() throws Exception
   {
      morpher = new BigIntegerMorpher();
      morpherWithDefaultValue = new BigIntegerMorpher( BigInteger.ZERO );
      anotherMorpher = new BigIntegerMorpher();
      anotherMorpherWithDefaultValue = new BigIntegerMorpher( BigInteger.ONE );
   }
}
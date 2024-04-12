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

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class NumberMorpherTest extends AbstractObjectMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( NumberMorpherTest.class );
      suite.setName( "NumberMorpher Tests" );
      return suite;
   }

   private NumberMorpher anotherMorpher;
   private NumberMorpher anotherMorpherWithDefaultValue;
   private NumberMorpher morpher;
   private NumberMorpher morpherWithDefaultValue;

   public NumberMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testBigDecimalConversion_Double()
   {
      morpher = new NumberMorpher( BigDecimal.class );
      BigDecimal expected = new BigDecimal( Double.MIN_VALUE );
      BigDecimal actual = (BigDecimal) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testBigDecimalConversion_null()
   {
      morpher = new NumberMorpher( BigDecimal.class );
      assertNull( morpher.morph( null ) );
   }

   public void testBigDecimalConversion_useDefault()
   {
      morpher = new NumberMorpher( BigDecimal.class );
      BigDecimal expected = MorphUtils.BIGDECIMAL_ONE;
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      BigDecimal actual = (BigDecimal) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testBigDecimalConversion_useDefault_null()
   {
      morpher = new NumberMorpher( BigDecimal.class, (BigDecimal) null );
      assertNull( morpher.morph( null ) );
   }

   public void testBigIntegerConversion_Long()
   {
      morpher = new NumberMorpher( BigInteger.class );
      BigInteger expected = BigInteger.valueOf( Long.MIN_VALUE );
      BigInteger actual = (BigInteger) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testBigIntegerConversion_null()
   {
      morpher = new NumberMorpher( BigInteger.class );
      assertNull( morpher.morph( null ) );
   }

   public void testBigIntegerConversion_useDefault()
   {
      morpher = new NumberMorpher( BigInteger.class );
      BigInteger expected = BigInteger.ONE;
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      BigInteger actual = (BigInteger) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testBigIntegerConversion_useDefault_null()
   {
      morpher = new NumberMorpher( BigInteger.class, (BigInteger) null );
      assertNull( morpher.morph( null ) );
   }

   public void testByteConversion_byte()
   {
      morpher = new NumberMorpher( Byte.TYPE );
      Byte expected = new Byte( Byte.MIN_VALUE );
      Byte actual = (Byte) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testByteConversion_Byte()
   {
      morpher = new NumberMorpher( Byte.class );
      Byte expected = new Byte( Byte.MIN_VALUE );
      Byte actual = (Byte) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testByteConversion_null()
   {
      morpher = new NumberMorpher( Byte.class );
      assertNull( morpher.morph( null ) );
   }

   public void testByteConversion_String()
   {
      morpher = new NumberMorpher( Byte.class );
      String expected = String.valueOf( Byte.MIN_VALUE );
      Byte actual = (Byte) morpher.morph( expected );
      assertEquals( expected, actual.toString() );
   }

   public void testByteConversion_useDefault()
   {
      Byte expected = new Byte( Byte.MIN_VALUE );
      morpher = new NumberMorpher( Byte.class, expected );
      Byte actual = (Byte) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testByteConversion_useDefault_null()
   {
      morpher = new NumberMorpher( Byte.class, (Byte) null );
      assertNull( morpher.morph( new Object() ) );
   }

   public void testDoubleConversion_double()
   {
      morpher = new NumberMorpher( Double.TYPE );
      Double expected = new Double( Double.MIN_VALUE );
      Double actual = (Double) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testDoubleConversion_Double()
   {
      morpher = new NumberMorpher( Double.class );
      Double expected = new Double( Double.MIN_VALUE );
      Double actual = (Double) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testDoubleConversion_null()
   {
      morpher = new NumberMorpher( Double.class );
      assertNull( morpher.morph( null ) );
   }

   public void testDoubleConversion_String()
   {
      morpher = new NumberMorpher( Double.class );
      String expected = String.valueOf( Double.MIN_VALUE );
      Double actual = (Double) morpher.morph( expected );
      assertEquals( expected, actual.toString() );
   }

   public void testDoubleConversion_useDefault()
   {
      morpher = new NumberMorpher( Double.class );
      Double expected = new Double( Double.MIN_VALUE );
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      Double actual = (Double) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testDoubleConversion_useDefault_null()
   {
      morpher = new NumberMorpher( Double.class, (Double) null );
      assertNull( morpher.morph( new Object() ) );
   }

   public void testFloatConversion_float()
   {
      morpher = new NumberMorpher( Float.TYPE );
      Float expected = new Float( Float.MIN_VALUE );
      Float actual = (Float) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testFloatConversion_Float()
   {
      morpher = new NumberMorpher( Float.class );
      Float expected = new Float( Float.MIN_VALUE );
      Float actual = (Float) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testFloatConversion_null()
   {
      morpher = new NumberMorpher( Float.class );
      assertNull( morpher.morph( null ) );
   }

   public void testFloatConversion_String()
   {
      morpher = new NumberMorpher( Float.class );
      String expected = String.valueOf( Float.MIN_VALUE );
      Float actual = (Float) morpher.morph( expected );
      assertEquals( expected, actual.toString() );
   }

   public void testFloatConversion_useDefault()
   {
      morpher = new NumberMorpher( Float.class );
      Float expected = new Float( Float.MIN_VALUE );
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      Float actual = (Float) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testFloatConversion_useDefault_null()
   {
      morpher = new NumberMorpher( Float.class, (Float) null );
      assertNull( morpher.morph( new Object() ) );
   }

   public void testIntegerConversion_int()
   {
      morpher = new NumberMorpher( Integer.TYPE );
      Integer expected = new Integer( Integer.MIN_VALUE );
      Integer actual = (Integer) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testIntegerConversion_Integer()
   {
      morpher = new NumberMorpher( Integer.class );
      Integer expected = new Integer( Integer.MIN_VALUE );
      Integer actual = (Integer) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testIntegerConversion_null()
   {
      morpher = new NumberMorpher( Integer.class );
      assertNull( morpher.morph( null ) );
   }

   public void testIntegerConversion_String()
   {
      morpher = new NumberMorpher( Integer.class );
      String expected = String.valueOf( Integer.MIN_VALUE );
      Integer actual = (Integer) morpher.morph( expected );
      assertEquals( expected, actual.toString() );
   }

   public void testIntegerConversion_useDefault()
   {
      morpher = new NumberMorpher( Integer.class );
      Integer expected = new Integer( Integer.MIN_VALUE );
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      Integer actual = (Integer) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testIntegerConversion_useDefault_null()
   {
      morpher = new NumberMorpher( Integer.class, (Integer) null );
      assertNull( morpher.morph( new Object() ) );
   }

   public void testlongConversion_long()
   {
      morpher = new NumberMorpher( Long.TYPE );
      Long expected = new Long( Long.MIN_VALUE );
      Long actual = (Long) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testLongConversion_Long()
   {
      morpher = new NumberMorpher( Long.class );
      Long expected = new Long( Long.MIN_VALUE );
      Long actual = (Long) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testLongConversion_null()
   {
      morpher = new NumberMorpher( Long.class );
      assertNull( morpher.morph( null ) );
   }

   public void testLongConversion_String()
   {
      morpher = new NumberMorpher( Long.class );
      String expected = String.valueOf( Long.MIN_VALUE );
      Long actual = (Long) morpher.morph( expected );
      assertEquals( expected, actual.toString() );
   }

   public void testLongConversion_useDefault()
   {
      morpher = new NumberMorpher( Long.class );
      Long expected = new Long( Long.MIN_VALUE );
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      Long actual = (Long) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testLongConversion_useDefault_null()
   {
      morpher = new NumberMorpher( Long.class, (Long) null );
      assertNull( morpher.morph( new Object() ) );
   }

   public void testNumbermorpher_incompatible_defaultValue()
   {
      try{
         morpher = new NumberMorpher( Integer.class, new Double( 0 ) );
         fail( "Should have thrown a ConversionException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testNumbermorpher_notSupported()
   {
      try{
         morpher = new NumberMorpher( String.class );
         fail( "Should have thrown a ConversionException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testNumbermorpher_notSupported_2()
   {
      try{
         morpher = new NumberMorpher( String.class, new Integer( 0 ) );
         fail( "Should have thrown a ConversionException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testNumbermorpher_unspecifiedClass()
   {
      try{
         morpher = new NumberMorpher( null );
         fail( "Should have thrown a ConversionException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testNumbermorpher_unspecifiedClass2()
   {
      try{
         morpher = new NumberMorpher( null, new Integer( 0 ) );
         fail( "Should have thrown a ConversionException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testShortConversion_null()
   {
      morpher = new NumberMorpher( Short.class );
      assertNull( morpher.morph( null ) );
   }

   public void testShortConversion_short()
   {
      morpher = new NumberMorpher( Short.TYPE );
      Short expected = new Short( Short.MIN_VALUE );
      Short actual = (Short) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testShortConversion_Short()
   {
      morpher = new NumberMorpher( Short.class );
      Short expected = new Short( Short.MIN_VALUE );
      Short actual = (Short) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testShortConversion_String()
   {
      morpher = new NumberMorpher( Short.class );
      String expected = String.valueOf( Short.MIN_VALUE );
      Short actual = (Short) morpher.morph( expected );
      assertEquals( expected, actual.toString() );
   }

   public void testShortConversion_useDefault()
   {
      morpher = new NumberMorpher( Short.class );
      Short expected = new Short( Short.MIN_VALUE );
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      Short actual = (Short) morpher.morph( new Object() );
      assertEquals( expected, actual );
   }

   public void testShortConversion_useDefault_null()
   {
      morpher = new NumberMorpher( Short.class, (Short) null );
      assertNull( morpher.morph( new Object() ) );
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
      morpher = new NumberMorpher( Integer.class );
      morpherWithDefaultValue = new NumberMorpher( Integer.class, new Integer( 0 ) );
      anotherMorpher = new NumberMorpher( Integer.class );
      anotherMorpherWithDefaultValue = new NumberMorpher( Integer.class, new Integer( 1 ) );
   }
}
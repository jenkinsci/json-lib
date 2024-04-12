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
public class BooleanMorpherTest extends AbstractMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BooleanMorpherTest.class );
      suite.setName( "BooleanMorpher Tests" );
      return suite;
   }

   private Morpher anotherMorpher;
   private Morpher anotherMorpherWithDefaultValue;
   private Morpher morpher;
   private Morpher morpherWithDefaultValue;

   public BooleanMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testBooleanMorph_noConversion()
   {
      boolean actual = ((BooleanMorpher) getMorpherWithDefaultValue()).morph( Boolean.TRUE );
      assertEquals( true, actual );
   }

   public void testBooleanMorph_throwException()
   {
      try{
         ((BooleanMorpher) getMorpher()).morph( "A" );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBooleanMorph_throwException_null()
   {
      try{
         ((BooleanMorpher) getMorpher()).morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBooleanMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      boolean actual = ((BooleanMorpher) getMorpherWithDefaultValue()).morph( expected );
      assertEquals( true, actual );
   }

   public void testBooleanMorph_useDefault_null()
   {
      boolean actual = ((BooleanMorpher) getMorpherWithDefaultValue()).morph( null );
      assertEquals( true, actual );
   }

   public void testBooleanMorphNumberValues_false()
   {
      assertFalse( ((BooleanMorpher) getMorpher()).morph( new Byte( (byte) 0 ) ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( new Short( (short) 0 ) ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( new Integer( 0 ) ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( new Long( 0 ) ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( new Float( 0 ) ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( new Double( 0 ) ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( BigInteger.ZERO ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( MorphUtils.BIGDECIMAL_ZERO ) );
   }

   public void testBooleanMorphNumberValues_true()
   {
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Byte( (byte) 1 ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Short( (short) 1 ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Integer( 1 ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Long( 1 ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Float( 1 ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( 1 ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( BigInteger.ONE ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( MorphUtils.BIGDECIMAL_ONE ) );

      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( Float.NEGATIVE_INFINITY ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( Float.POSITIVE_INFINITY ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( Float.NaN ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( Double.NEGATIVE_INFINITY ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( Double.POSITIVE_INFINITY ) ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( new Double( Double.NaN ) ) );
   }

   public void testBooleanMorphStringValues_false()
   {
      assertFalse( ((BooleanMorpher) getMorpher()).morph( "false" ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( "no" ) );
      assertFalse( ((BooleanMorpher) getMorpher()).morph( "off" ) );
   }

   public void testBooleanMorphStringValues_true()
   {
      assertTrue( ((BooleanMorpher) getMorpher()).morph( "true" ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( "yes" ) );
      assertTrue( ((BooleanMorpher) getMorpher()).morph( "on" ) );
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

   protected Class getMorphsToClass()
   {
      return Boolean.TYPE;
   }

   protected void setUp() throws Exception
   {
      morpher = new BooleanMorpher();
      morpherWithDefaultValue = new BooleanMorpher( true );
      anotherMorpher = new BooleanMorpher();
      anotherMorpherWithDefaultValue = new BooleanMorpher( false );
   }
}
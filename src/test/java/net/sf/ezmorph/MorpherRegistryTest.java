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

package net.sf.ezmorph;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import net.sf.ezmorph.object.MapToDateMorpher;
import net.sf.ezmorph.object.StringMorpher;
import net.sf.ezmorph.primitive.BooleanMorpher;
import net.sf.ezmorph.primitive.IntMorpher;
import net.sf.ezmorph.test.ArrayAssertions;

public class MorpherRegistryTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( MorpherRegistryTest.class );
      suite.setName( "MorpherRegistry Tests" );
      return suite;
   }

   private MorpherRegistry morpherRegistry;

   public MorpherRegistryTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testClear_Class()
   {
      MorphUtils.registerStandardMorphers( morpherRegistry );
      Morpher expected = StringMorpher.getInstance();
      Morpher actual = morpherRegistry.getMorpherFor( String.class );
      assertEquals( expected, actual );
      morpherRegistry.clear( String.class );
      actual = morpherRegistry.getMorpherFor( String.class );
      assertFalse( expected.equals( actual ) );
   }

   public void testMorph_array_of_objects()
   {
      MorphUtils.registerStandardObjectArrayMorphers( morpherRegistry );

      ArrayAssertions.assertEquals( new Byte[] { Byte.valueOf( "0" ), Byte.valueOf( "1" ) },
            (Byte[]) morpherRegistry.morph( Byte[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new Short[] { Short.valueOf( "0" ), Short.valueOf( "1" ) },
            (Short[]) morpherRegistry.morph( Short[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals(
            new Integer[] { Integer.valueOf( "0" ), Integer.valueOf( "1" ) },
            (Integer[]) morpherRegistry.morph( Integer[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new Long[] { Long.valueOf( "0" ), Long.valueOf( "1" ) },
            (Long[]) morpherRegistry.morph( Long[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new Float[] { Float.valueOf( "0" ), Float.valueOf( "1" ) },
            (Float[]) morpherRegistry.morph( Float[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new Double[] { Double.valueOf( "0" ), Double.valueOf( "1" ) },
            (Double[]) morpherRegistry.morph( Double[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new BigInteger[] { BigInteger.ZERO, BigInteger.ONE },
            (BigInteger[]) morpherRegistry.morph( BigInteger[].class, new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new BigDecimal[] { MorphUtils.BIGDECIMAL_ZERO,
            MorphUtils.BIGDECIMAL_ONE }, (BigDecimal[]) morpherRegistry.morph( BigDecimal[].class,
            new String[] { "0", "1" } ) );
      ArrayAssertions.assertEquals( new Boolean[] { Boolean.FALSE },
            (Boolean[]) morpherRegistry.morph( Boolean[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Character[] { new Character( '\0' ) },
            (Character[]) morpherRegistry.morph( Character[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Class[] { Object.class, boolean[].class },
            (Class[]) morpherRegistry.morph( Class[].class,
                  new String[] { "java.lang.Object", "[Z" } ) );
   }

   public void testMorph_array_of_objects__empty_string()
   {
      MorphUtils.registerStandardObjectArrayMorphers( morpherRegistry );

      ArrayAssertions.assertEquals( new Byte[] { null }, (Byte[]) morpherRegistry.morph(
            Byte[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Short[] { null }, (Short[]) morpherRegistry.morph(
            Short[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Integer[] { null }, (Integer[]) morpherRegistry.morph(
            Integer[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Long[] { null }, (Long[]) morpherRegistry.morph(
            Long[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Float[] { null }, (Float[]) morpherRegistry.morph(
            Float[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new Double[] { null }, (Double[]) morpherRegistry.morph(
            Double[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new BigInteger[] { null },
            (BigInteger[]) morpherRegistry.morph( BigInteger[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new BigDecimal[] { null },
            (BigDecimal[]) morpherRegistry.morph( BigDecimal[].class, new String[] { "" } ) );
   }

   public void testMorph_array_of_objects__null_string()
   {
      MorphUtils.registerStandardObjectArrayMorphers( morpherRegistry );

      ArrayAssertions.assertEquals( new Byte[] { null }, (Byte[]) morpherRegistry.morph(
            Byte[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new Short[] { null }, (Short[]) morpherRegistry.morph(
            Short[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new Integer[] { null }, (Integer[]) morpherRegistry.morph(
            Integer[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new Long[] { null }, (Long[]) morpherRegistry.morph(
            Long[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new Float[] { null }, (Float[]) morpherRegistry.morph(
            Float[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new Double[] { null }, (Double[]) morpherRegistry.morph(
            Double[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new BigInteger[] { null },
            (BigInteger[]) morpherRegistry.morph( BigInteger[].class, new String[] { "null" } ) );
      ArrayAssertions.assertEquals( new BigDecimal[] { null },
            (BigDecimal[]) morpherRegistry.morph( BigDecimal[].class, new String[] { "null" } ) );
   }

   public void testMorph_array_of_objects__numbers()
   {
      MorphUtils.registerStandardObjectArrayMorphers( morpherRegistry );

      ArrayAssertions.assertEquals( new Byte[] { new Byte( (byte) 0 ) },
            (Byte[]) morpherRegistry.morph( Byte[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new Short[] { new Short( (short) 0 ) },
            (Short[]) morpherRegistry.morph( Short[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new Integer[] { new Integer( 0 ) },
            (Integer[]) morpherRegistry.morph( Integer[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new Long[] { new Long( 0 ) }, (Long[]) morpherRegistry.morph(
            Long[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new Float[] { new Float( 0 ) },
            (Float[]) morpherRegistry.morph( Float[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new Double[] { new Double( 0 ) },
            (Double[]) morpherRegistry.morph( Double[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new BigInteger[] { BigInteger.ZERO },
            (BigInteger[]) morpherRegistry.morph( BigInteger[].class, new String[] { "a" } ) );
      ArrayAssertions.assertEquals( new BigDecimal[] { MorphUtils.BIGDECIMAL_ZERO },
            (BigDecimal[]) morpherRegistry.morph( BigDecimal[].class, new String[] { "a" } ) );
   }

   public void testMorph_array_of_primitives()
   {
      MorphUtils.registerStandardPrimitiveArrayMorphers( morpherRegistry );

      ArrayAssertions.assertEquals( new boolean[] { false }, (boolean[]) morpherRegistry.morph(
            boolean[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new char[] { '\0' }, (char[]) morpherRegistry.morph(
            char[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new byte[] { 0 }, (byte[]) morpherRegistry.morph( byte[].class,
            new String[] { "" } ) );
      ArrayAssertions.assertEquals( new short[] { 0 }, (short[]) morpherRegistry.morph(
            short[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new int[] { 0 }, (int[]) morpherRegistry.morph( int[].class,
            new String[] { "" } ) );
      ArrayAssertions.assertEquals( new long[] { 0 }, (long[]) morpherRegistry.morph( long[].class,
            new String[] { "" } ) );
      ArrayAssertions.assertEquals( new float[] { 0 }, (float[]) morpherRegistry.morph(
            float[].class, new String[] { "" } ) );
      ArrayAssertions.assertEquals( new double[] { 0 }, (double[]) morpherRegistry.morph(
            double[].class, new String[] { "" } ) );
   }

   public void testMorph_objects()
   {
      MorphUtils.registerStandardObjectMorphers( morpherRegistry );

      assertEquals( Boolean.FALSE, morpherRegistry.morph( Boolean.class, null ) );
      assertEquals( new Character( '\0' ), morpherRegistry.morph( Character.class, null ) );
      assertEquals( Object.class, morpherRegistry.morph( Class.class, "java.lang.Object" ) );
   }

   public void testMorph_objects__empty_string()
   {
      MorphUtils.registerStandardObjectMorphers( morpherRegistry );

      assertNull( morpherRegistry.morph( Byte.class, "" ) );
      assertNull( morpherRegistry.morph( Short.class, "" ) );
      assertNull( morpherRegistry.morph( Integer.class, "" ) );
      assertNull( morpherRegistry.morph( Long.class, "" ) );
      assertNull( morpherRegistry.morph( Float.class, "" ) );
      assertNull( morpherRegistry.morph( Double.class, "" ) );
      assertNull( morpherRegistry.morph( BigInteger.class, "" ) );
      assertNull( morpherRegistry.morph( BigDecimal.class, "" ) );
      assertEquals( "", morpherRegistry.morph( String.class, "" ) );
   }

   public void testMorph_objects__null()
   {
      MorphUtils.registerStandardObjectMorphers( morpherRegistry );

      assertEquals( (Byte) null, morpherRegistry.morph( Byte.class, null ) );
      assertEquals( (Short) null, morpherRegistry.morph( Short.class, null ) );
      assertEquals( (Integer) null, morpherRegistry.morph( Integer.class, null ) );
      assertEquals( (Long) null, morpherRegistry.morph( Long.class, null ) );
      assertEquals( (Float) null, morpherRegistry.morph( Float.class, null ) );
      assertEquals( (Double) null, morpherRegistry.morph( Double.class, null ) );
      assertEquals( (BigInteger) null, morpherRegistry.morph( BigInteger.class, null ) );
      assertEquals( (BigDecimal) null, morpherRegistry.morph( BigDecimal.class, null ) );
      assertEquals( (String) null, morpherRegistry.morph( String.class, null ) );
      assertEquals( (Class) null, morpherRegistry.morph( Class.class, null ) );
   }

   public void testMorph_objects__null_string()
   {
      MorphUtils.registerStandardObjectMorphers( morpherRegistry );

      assertNull( morpherRegistry.morph( Byte.class, "null" ) );
      assertNull( morpherRegistry.morph( Short.class, "null" ) );
      assertNull( morpherRegistry.morph( Integer.class, "null" ) );
      assertNull( morpherRegistry.morph( Long.class, "null" ) );
      assertNull( morpherRegistry.morph( Float.class, "null" ) );
      assertNull( morpherRegistry.morph( Double.class, "null" ) );
      assertNull( morpherRegistry.morph( BigInteger.class, "null" ) );
      assertNull( morpherRegistry.morph( BigDecimal.class, "null" ) );
      assertEquals( "null", morpherRegistry.morph( String.class, "null" ) );
      assertNull( morpherRegistry.morph( Class.class, "null" ) );
   }

   public void testMorph_primitives()
   {
      MorphUtils.registerStandardPrimitiveMorphers( morpherRegistry );

      assertEquals( Boolean.FALSE, morpherRegistry.morph( boolean.class, null ) );
      assertEquals( new Character( '\0' ), morpherRegistry.morph( char.class, null ) );
      assertEquals( new Byte( (byte) 0 ), morpherRegistry.morph( byte.class, null ) );
      assertEquals( new Short( (short) 0 ), morpherRegistry.morph( short.class, null ) );
      assertEquals( new Integer( 0 ), morpherRegistry.morph( int.class, null ) );
      assertEquals( new Long( 0 ), morpherRegistry.morph( long.class, null ) );
      assertEquals( new Float( 0 ), morpherRegistry.morph( float.class, null ) );
      assertEquals( new Double( 0 ), morpherRegistry.morph( double.class, null ) );
   }

   public void testMorph_severalMorphersForTargetClass()
   {
      Map map = new HashMap();
      map.put( "year", new Integer( 2007 ) );
      map.put( "month", new Integer( 5 ) );
      map.put( "day", new Integer( 17 ) );
      map.put( "hour", new Integer( 12 ) );
      map.put( "minutes", new Integer( 13 ) );
      map.put( "seconds", new Integer( 14 ) );
      map.put( "milliseconds", new Integer( 150 ) );

      MorpherRegistry morpherRegistry = new MorpherRegistry();
      morpherRegistry.registerMorpher( new DateMorpher( new String[] { "mm/dd/yyyy" } ) );
      morpherRegistry.registerMorpher( new MapToDateMorpher() );
      Date date = (Date) morpherRegistry.morph( Date.class, map );
      assertNotNull( date );
      Calendar c = Calendar.getInstance();
      c.setTime( date );
      assertEquals( 2007, c.get( Calendar.YEAR ) );
      assertEquals( 5, c.get( Calendar.MONTH ) );
      assertEquals( 17, c.get( Calendar.DATE ) );
      assertEquals( 12, c.get( Calendar.HOUR_OF_DAY ) );
      assertEquals( 13, c.get( Calendar.MINUTE ) );
      assertEquals( 14, c.get( Calendar.SECOND ) );
      assertEquals( 150, c.get( Calendar.MILLISECOND ) );
   }

   public void testRegistry()
   {
      Morpher morpher = new BooleanMorpher();
      morpherRegistry.registerMorpher( morpher );
      assertEquals( morpher, morpherRegistry.getMorpherFor( boolean.class ) );
      morpherRegistry.deregisterMorpher( morpher );
      assertTrue( !morpher.equals( morpherRegistry.getMorpherFor( boolean.class ) ) );
      assertEquals( IdentityObjectMorpher.getInstance(),
            morpherRegistry.getMorpherFor( boolean.class ) );
   }

   public void testRegistry_morphers_for_unknown_target_class()
   {
      Morpher[] morphers = morpherRegistry.getMorphersFor( int.class );
      assertEquals( 1, morphers.length );
      assertSame( IdentityObjectMorpher.getInstance(), morphers[0] );
   }

   public void testRegistry_standardMorphers()
   {
      MorphUtils.registerStandardMorphers( morpherRegistry );
      Morpher morpher = new IntMorpher( 1 );
      morpherRegistry.registerMorpher( morpher );
      Morpher[] morphers = morpherRegistry.getMorphersFor( int.class );

      assertEquals( 2, morphers.length );
      assertEquals( 0, ((IntMorpher) morphers[0]).getDefaultValue() );
      assertSame( morpher, morphers[1] );
   }

   public void testRegistry_wacky_morpher()
   {
      morpherRegistry.registerMorpher( new Morpher(){
         // no mroph() method
         public Class morphsTo()
         {
            return String.class;
         }

         public boolean supports( Class clazz )
         {
            return true;
         }
      } );

      try{
         morpherRegistry.morph( String.class, null );
         fail( "Expected a MorphException" );
      }
      catch( MorphException expected ){
         // OK
      }
   }

   protected void setUp() throws Exception
   {
      morpherRegistry = new MorpherRegistry();
   }
}
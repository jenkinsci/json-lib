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

package net.sf.ezmorph.test;

import java.util.List;

import junit.framework.Assert;

/**
 * Provides assertions on arrays (primitive and objects).<br>
 * All methods support multiple dimensional arrays.
 *
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class ArrayAssertions extends Assert
{
   /**
    * Asserts that two boolean[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( boolean[] expecteds, boolean[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a boolean[] is equal to an Object[] (presumably an Boolean[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( boolean[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two byte[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( byte[] expecteds, byte[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a byte[] is equal to an Object[] (presumably an Byte[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( byte[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two char[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( char[] expecteds, char[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a char[] is equal to an Object[] (presumably an Character[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( char[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two double[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( double[] expecteds, double[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a double[] is equal to an Object[] (presumably an Double[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( double[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two float[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( float[] expecteds, float[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a float[] is equal to an Object[] (presumably an Float[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( float[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two int[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( int[] expecteds, int[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a int[] is equal to an Object[] (presumably an Integer[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( int[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two Lists are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( List expecteds, List actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two long[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( long[] expecteds, long[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a long[] is equal to an Object[] (presumably an Long[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( long[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two objects are equal. If they are not an
    * AssertionFailedError is thrown.
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object expected, Object actual )
   {
      assertEquals( null, expected, actual );
   }

   /**
    * Asserts that Object[] (presumably an Boolean[]) is equal to an boolean[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, boolean[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Byte[]) is equal to an byte[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, byte[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Character[]) is equal to an char[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, char[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Double[]) is equal to an double[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, double[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Float[]) is equal to an float[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, float[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Integer[]) is equal to an int[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, int[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Long[]) is equal to an long[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, long[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two Object[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that Object[] (presumably an Short[]) is equal to an short[].
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( Object[] expecteds, short[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that a short[] is equal to an Object[] (presumably an Short[])
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( short[] expecteds, Object[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two short[] are equal.<br>
    *
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( short[] expecteds, short[] actuals )
   {
      assertEquals( null, expecteds, actuals );
   }

   /**
    * Asserts that two boolean[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, boolean[] expecteds, boolean[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i] );
      }
   }

   /**
    * Asserts that a boolean[] is equal to an Object[] (presumably an Boolean[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, boolean[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Boolean(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two byte[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, byte[] expecteds, byte[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i] );
      }
   }

   /**
    * Asserts that a byte[] is equal to an Object[] (presumably an Byte[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, byte[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Byte(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two char[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, char[] expecteds, char[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i] );
      }
   }

   /**
    * Asserts that a char[] is equal to an Object[] (presumably an Character[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, char[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Character(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two double[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, double[] expecteds, double[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i], 0d );
      }
   }

   /**
    * Asserts that a double[] is equal to an Object[] (presumably an Double[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, double[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Double(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two float[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, float[] expecteds, float[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i], 0f );
      }
   }

   /**
    * Asserts that a float[] is equal to an Object[] (presumably an Float[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, float[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Float(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two int[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, int[] expecteds, int[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i] );
      }
   }

   /**
    * Asserts that a int[] is equal to an Object[] (presumably an Integer[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, int[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Integer(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two Lists are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, List expecteds, List actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected list was null" );
      }
      if( actuals == null ){
         fail( header + "actual list was null" );
      }
      if( expecteds == actuals || expecteds.equals( actuals ) ){
         return;
      }
      if( actuals.size() != expecteds.size() ){
         fail( header + "list sizes differed, expected.size()=" + expecteds.size()
               + " actual.size()=" + actuals.size() );
      }

      int max = expecteds.size();
      for( int i = 0; i < max; i++ ){
         Object o1 = expecteds.get( i );
         Object o2 = actuals.get( i );

         // handle nulls
         if( o1 == null ){
            if( o2 == null ){
               return;
            }else{
               fail( header + "lists first differed at element [" + i + "];" );
            }
         }else{
            if( o2 == null ){
               fail( header + "lists first differed at element [" + i + "];" );
            }
         }

         if( o1.getClass()
               .isArray() && o2.getClass()
               .isArray() ){
            Object[] expected = (Object[]) o1;
            Object[] actual = (Object[]) o2;
            assertEquals( header + "lists first differed at element " + i + ";", expected, actual );
         }else if( List.class.isAssignableFrom( o1.getClass() )
               && List.class.isAssignableFrom( o2.getClass() ) ){
            assertEquals( header + "lists first differed at element [" + i + "];", (List) o1,
                  (List) o2 );
         }else{
            assertEquals( header + "lists first differed at element [" + i + "];", o1, o2 );
         }
      }
   }

   /**
    * Asserts that two long[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, long[] expecteds, long[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i] );
      }
   }

   /**
    * Asserts that a long[] is equal to an Object[] (presumably an Long[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, long[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Long(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two objects are equal. If they are not an
    * AssertionFailedError is thrown with the given message. *
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object expected, Object actual )
   {
      if( expected == null && actual == null )
         return;
      if( expected != null && expected.equals( actual ) )
         return;
      Class expectedClass = expected.getClass();
      Class actualClass = actual.getClass();
      if( expectedClass.isArray() && actualClass.isArray() ){
         Class expectedInnerType = expectedClass.getComponentType();
         Class actualInnerType = actualClass.getComponentType();
         if( expectedInnerType.isPrimitive() ){
            assertExpectedPrimitiveArrays( message, expected, actual, expectedInnerType,
                  actualInnerType );
         }else if( actualInnerType.isPrimitive() ){
            assertActualPrimitiveArrays( message, expected, actual, expectedInnerType,
                  actualInnerType );
         }else{
            assertEquals( message, (Object[]) expected, (Object[]) actual );
         }
      }else{
         failNotEquals( message, expected, actual );
      }
   }

   /**
    * Asserts that Object[] (presumably an Boolean[]) is equal to an boolean[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, boolean[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Boolean( actuals[i] ) );
      }
   }

   /**
    * Asserts that Object[] (presumably an Byte[]) is equal to an byte[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, byte[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Byte( actuals[i] ) );
      }
   }

   /**
    * Asserts that Object[] (presumably an Character[]) is equal to an char[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, char[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Character( actuals[i] ) );
      }
   }

   /**
    * Asserts that Object[] (presumably an Double[]) is equal to an double[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, double[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Double( actuals[i] ) );
      }
   }

   /**
    * Asserts that Object[] (presumably an Float[]) is equal to an float[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, float[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Float( actuals[i] ) );
      }
   }

   /**
    * Asserts that Object[] (presumably an Integer[]) is equal to an int[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, int[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Integer( actuals[i] ) );
      }
   }

   /**
    * Asserts that Object[] (presumably an Long[]) is equal to an long[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, long[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Long( actuals[i] ) );
      }
   }

   /**
    * Asserts that two Object[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, Object[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         Object o1 = expecteds[i];
         Object o2 = actuals[i];

         if( o1 == null ){
            if( o2 == null ){
               return;
            }else{
               fail( header + "arrays first differed at element [" + i + "];" );
            }
         }else{
            if( o2 == null ){
               fail( header + "arrays first differed at element [" + i + "];" );
            }
         }

         if( o1.getClass()
               .isArray() && o2.getClass()
               .isArray() ){
            Class type1 = o1.getClass()
                  .getComponentType();
            Class type2 = o2.getClass()
                  .getComponentType();
            if( type1.isPrimitive() ){
               if( type1 == Boolean.TYPE ){
                  if( type2 == Boolean.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (boolean[]) o1, (boolean[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (boolean[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Byte.TYPE ){
                  if( type2 == Byte.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (byte[]) o1, (byte[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (byte[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Short.TYPE ){
                  if( type2 == Short.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (short[]) o1, (short[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (short[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Integer.TYPE ){
                  if( type2 == Integer.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (int[]) o1, (int[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (int[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Long.TYPE ){
                  if( type2 == Long.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (long[]) o1, (long[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (long[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Float.TYPE ){
                  if( type2 == Float.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (float[]) o1, (float[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (float[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Double.TYPE ){
                  if( type2 == Double.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (double[]) o1, (double[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (double[]) o1, (Object[]) o2 );
                  }
               }else if( type1 == Character.TYPE ){
                  if( type2 == Character.TYPE ){
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (char[]) o1, (char[]) o2 );
                  }else{
                     assertEquals( header + "arrays first differed at element " + i + ";",
                           (char[]) o1, (Object[]) o2 );
                  }
               }
            }else if( type2.isPrimitive() ){
               if( type2 == Boolean.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (boolean[]) o2 );
               }else if( type2 == Byte.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (byte[]) o2 );
               }else if( type2 == Short.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (short[]) o2 );
               }else if( type2 == Integer.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (int[]) o2 );
               }else if( type2 == Long.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (long[]) o2 );
               }else if( type2 == Float.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (float[]) o2 );
               }else if( type2 == Double.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (double[]) o2 );
               }else if( type2 == Character.TYPE ){
                  assertEquals( header + "arrays first differed at element " + i + ";",
                        (Object[]) o1, (char[]) o2 );
               }
            }else{
               Object[] expected = (Object[]) o1;
               Object[] actual = (Object[]) o2;
               assertEquals( header + "arrays first differed at element " + i + ";", expected,
                     actual );
            }
         }else{
            assertEquals( header + "arrays first differed at element [" + i + "];", o1, o2 );
         }
      }
   }

   /**
    * Asserts that Object[] (presumably an Short[]) is equal to a short[].
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, Object[] expecteds, short[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               new Short( actuals[i] ) );
      }
   }

   /**
    * Asserts that a short[] is equal to an Object[] (presumably an Short[])
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, short[] expecteds, Object[] actuals )
   {
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", new Short(
               expecteds[i] ), actuals[i] );
      }
   }

   /**
    * Asserts that two short[] are equal.<br>
    *
    * @param message
    * @param expecteds
    * @param actuals
    */
   public static void assertEquals( String message, short[] expecteds, short[] actuals )
   {
      if( expecteds == actuals ){
         return;
      }
      String header = message == null ? "" : message + ": ";
      if( expecteds == null ){
         fail( header + "expected array was null" );
      }
      if( actuals == null ){
         fail( header + "actual array was null" );
      }
      if( actuals.length != expecteds.length ){
         fail( header + "array lengths differed, expected.length=" + expecteds.length
               + " actual.length=" + actuals.length );
      }

      for( int i = 0; i < expecteds.length; i++ ){
         assertEquals( header + "arrays first differed at element [" + i + "];", expecteds[i],
               actuals[i] );
      }
   }

   private static void assertActualPrimitiveArrays( String message, Object expected, Object actual,
         Class expectedInnerType, Class actualInnerType )
   {
      if( Boolean.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Boolean.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Boolean[]) expected, (boolean[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (boolean[]) actual );
         }
      }else if( Byte.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Byte.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Byte[]) expected, (byte[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (byte[]) actual );
         }
      }else if( Short.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Short.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Short[]) expected, (short[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (short[]) actual );
         }
      }else if( Integer.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Integer.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Integer[]) expected, (int[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (int[]) actual );
         }
      }else if( Long.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Long.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Long[]) expected, (long[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (long[]) actual );
         }
      }else if( Float.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Float.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Float[]) expected, (float[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (float[]) actual );
         }
      }else if( Double.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Double.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Double[]) expected, (double[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (double[]) actual );
         }
      }else if( Character.TYPE.isAssignableFrom( actualInnerType ) ){
         if( Character.class.isAssignableFrom( expectedInnerType ) ){
            assertEquals( message, (Character[]) expected, (char[]) actual );
         }else{
            assertEquals( message, (Object[]) expected, (char[]) actual );
         }
      }
   }

   private static void assertExpectedPrimitiveArrays( String message, Object expected,
         Object actual, Class expectedInnerType, Class actualInnerType )
   {
      if( Boolean.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Boolean.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (boolean[]) expected, (boolean[]) actual );
         }else if( Boolean.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (boolean[]) expected, (Boolean[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (boolean[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Byte.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Byte.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (byte[]) expected, (byte[]) actual );
         }else if( Byte.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (byte[]) expected, (Byte[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (byte[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Short.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Short.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (short[]) expected, (short[]) actual );
         }else if( Short.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (short[]) expected, (Short[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (short[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Integer.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Integer.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (int[]) expected, (int[]) actual );
         }else if( Integer.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (int[]) expected, (Integer[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (int[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Long.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Long.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (long[]) expected, (long[]) actual );
         }else if( Long.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (long[]) expected, (Long[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (long[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Float.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Float.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (float[]) expected, (float[]) actual );
         }else if( Float.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (float[]) expected, (Float[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (float[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Double.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Double.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (double[]) expected, (double[]) actual );
         }else if( Double.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (double[]) expected, (Double[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (double[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }else if( Character.TYPE.isAssignableFrom( expectedInnerType ) ){
         if( Character.TYPE.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (char[]) expected, (char[]) actual );
         }else if( Character.class.isAssignableFrom( actualInnerType ) ){
            assertEquals( message, (char[]) expected, (Character[]) actual );
         }else if( !actualInnerType.isPrimitive() ){
            assertEquals( message, (char[]) expected, (Object[]) actual );
         }else{
            failNotEquals( message, expected, actual );
         }
      }
   }

   private ArrayAssertions()
   {

   }
}
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

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class IntArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( IntArrayAssertionsTest.class );
      suite.setName( "IntArrayAssertions Tests" );
      return suite;
   }

   public IntArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_int_int()
   {
      int[] expecteds = new int[] { 1, 2 };
      int[] actuals = new int[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_int_int_actuals_is_null()
   {
      boolean errorThrown = false;
      int[] expecteds = new int[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (int[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_int_int_different_length()
   {
      int[] expecteds = new int[] { 1 };
      int[] actuals = new int[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_int_int_expecteds_is_null()
   {
      boolean errorThrown = false;
      int[] actuals = new int[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (int[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_int_Integer()
   {
      int[] expecteds = new int[] { 1, 2 };
      Integer[] actuals = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_int_Integer_actuals_is_null()
   {
      boolean errorThrown = false;
      int[] expecteds = new int[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (Integer[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_int_Integer_different_length()
   {
      int[] expecteds = new int[] { 1 };
      Integer[] actuals = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_int_Integer_expecteds_is_null()
   {
      boolean errorThrown = false;
      Integer[] actuals = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      try{
         ArrayAssertions.assertEquals( (int[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Integer_int()
   {
      Integer[] expecteds = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      int[] actuals = new int[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Integer_int_actuals_is_null()
   {
      boolean errorThrown = false;
      Integer[] expecteds = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (int[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Integer_int_different_length()
   {
      Integer[] expecteds = new Integer[] { new Integer( 1 ) };
      int[] actuals = new int[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Integer_int_expecteds_is_null()
   {
      boolean errorThrown = false;
      int[] actuals = new int[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (Integer[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Integer_Integer()
   {
      Integer[] expecteds = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      Integer[] actuals = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_int_int()
   {
      int[][] expecteds = new int[][] { { 1, 2 }, { 1, 2 } };
      int[][] actuals = new int[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_int_Integer()
   {
      int[][] expecteds = new int[][] { { 1, 2 }, { 1, 2 } };
      Integer[][] actuals = new Integer[][] { { new Integer( 1 ), new Integer( 2 ) },
            { new Integer( 1 ), new Integer( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Integer_int()
   {
      Integer[][] expecteds = new Integer[][] { { new Integer( 1 ), new Integer( 2 ) },
            { new Integer( 1 ), new Integer( 2 ) } };
      int[][] actuals = new int[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Integer_Integer()
   {
      Integer[][] expecteds = new Integer[][] { { new Integer( 1 ), new Integer( 2 ) },
            { new Integer( 1 ), new Integer( 2 ) } };
      Integer[][] actuals = new Integer[][] { { new Integer( 1 ), new Integer( 2 ) },
            { new Integer( 1 ), new Integer( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_int_double()
   {
      boolean errorThrown = false;
      Object expecteds = new int[] { 1, 2 };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_int_int()
   {
      Object expecteds = new int[] { 1, 2 };
      Object actuals = new int[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_int_Integer()
   {
      Object expecteds = new int[] { 1, 2 };
      Object actuals = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_int_Object_array()
   {
      Object expecteds = new int[] { 1, 2 };
      Object actuals = new Object[] { new Integer( 1 ), new Integer( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Integer_int()
   {
      Object expecteds = new Integer[] { new Integer( 1 ), new Integer( 2 ) };
      Object actuals = new int[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_int()
   {
      Object expecteds = new Object[] { new Integer( 1 ), new Integer( 2 ) };
      Object actuals = new int[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Integer( 1 ), new Integer( 2 ) };
      Object actuals = new Object[] { new Integer( 1 ), new Integer( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
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
public class FloatArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( FloatArrayAssertionsTest.class );
      suite.setName( "FloatArrayAssertions Tests" );
      return suite;
   }

   public FloatArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_float_float()
   {
      float[] expecteds = new float[] { 1, 2 };
      float[] actuals = new float[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_float_Float()
   {
      float[] expecteds = new float[] { 1, 2 };
      Float[] actuals = new Float[] { new Float( 1 ), new Float( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Float_float()
   {
      Float[] expecteds = new Float[] { new Float( 1 ), new Float( 2 ) };
      float[] actuals = new float[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Float_Float()
   {
      Float[] expecteds = new Float[] { new Float( 1 ), new Float( 2 ) };
      Float[] actuals = new Float[] { new Float( 1 ), new Float( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_float_float_actuals_is_null()
   {
      boolean errorThrown = false;
      float[] expecteds = new float[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (float[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_float_Float_actuals_is_null()
   {
      boolean errorThrown = false;
      float[] expecteds = new float[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (Float[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Float_float_actuals_is_null()
   {
      boolean errorThrown = false;
      Float[] expecteds = new Float[] { new Float( 1 ), new Float( 2 ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (float[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_float_float_different_length()
   {
      float[] expecteds = new float[] { 1 };
      float[] actuals = new float[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_float_Float_different_length()
   {
      float[] expecteds = new float[] { 1 };
      Float[] actuals = new Float[] { new Float( 1 ), new Float( 2 ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Float_float_different_length()
   {
      Float[] expecteds = new Float[] { new Float( 1 ) };
      float[] actuals = new float[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_float_float_expecteds_is_null()
   {
      boolean errorThrown = false;
      float[] actuals = new float[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (float[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_float_Float_expecteds_is_null()
   {
      boolean errorThrown = false;
      Float[] actuals = new Float[] { new Float( 1 ), new Float( 2 ) };
      try{
         ArrayAssertions.assertEquals( (float[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Float_float_expecteds_is_null()
   {
      boolean errorThrown = false;
      float[] actuals = new float[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (Float[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_multi_float_float()
   {
      float[][] expecteds = new float[][] { { 1, 2 }, { 1, 2 } };
      float[][] actuals = new float[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_float_Float()
   {
      float[][] expecteds = new float[][] { { 1, 2 }, { 1, 2 } };
      Float[][] actuals = new Float[][] { { new Float( 1 ), new Float( 2 ) },
            { new Float( 1 ), new Float( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Float_float()
   {
      Float[][] expecteds = new Float[][] { { new Float( 1 ), new Float( 2 ) },
            { new Float( 1 ), new Float( 2 ) } };
      float[][] actuals = new float[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Float_Float()
   {
      Float[][] expecteds = new Float[][] { { new Float( 1 ), new Float( 2 ) },
            { new Float( 1 ), new Float( 2 ) } };
      Float[][] actuals = new Float[][] { { new Float( 1 ), new Float( 2 ) },
            { new Float( 1 ), new Float( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_float_double()
   {
      boolean errorThrown = false;
      Object expecteds = new float[] { 1, 2 };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_float_float()
   {
      Object expecteds = new float[] { 1, 2 };
      Object actuals = new float[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_float_Float()
   {
      Object expecteds = new float[] { 1, 2 };
      Object actuals = new Float[] { new Float( 1 ), new Float( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Float_float()
   {
      Object expecteds = new Float[] { new Float( 1 ), new Float( 2 ) };
      Object actuals = new float[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_float_Object_array()
   {
      Object expecteds = new float[] { 1, 2 };
      Object actuals = new Object[] { new Float( 1 ), new Float( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_float()
   {
      Object expecteds = new Object[] { new Float( 1 ), new Float( 2 ) };
      Object actuals = new float[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Float( 1 ), new Float( 2 ) };
      Object actuals = new Object[] { new Float( 1 ), new Float( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
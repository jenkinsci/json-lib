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
public class DoubleArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( DoubleArrayAssertionsTest.class );
      suite.setName( "DoubleArrayAssertions Tests" );
      return suite;
   }

   public DoubleArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_double_double()
   {
      double[] expecteds = new double[] { 1, 2 };
      double[] actuals = new double[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_double_Double()
   {
      double[] expecteds = new double[] { 1, 2 };
      Double[] actuals = new Double[] { new Double( 1 ), new Double( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Double_double()
   {
      Double[] expecteds = new Double[] { new Double( 1 ), new Double( 2 ) };
      double[] actuals = new double[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Double_Double()
   {
      Double[] expecteds = new Double[] { new Double( 1 ), new Double( 2 ) };
      Double[] actuals = new Double[] { new Double( 1 ), new Double( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_double_double_actuals_is_null()
   {
      boolean errorThrown = false;
      double[] expecteds = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (double[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_double_Double_actuals_is_null()
   {
      boolean errorThrown = false;
      double[] expecteds = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (Double[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Double_double_actuals_is_null()
   {
      boolean errorThrown = false;
      Double[] expecteds = new Double[] { new Double( 1 ), new Double( 2 ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (double[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_double_double_different_length()
   {
      double[] expecteds = new double[] { 1 };
      double[] actuals = new double[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_double_Double_different_length()
   {
      double[] expecteds = new double[] { 1 };
      Double[] actuals = new Double[] { new Double( 1 ), new Double( 2 ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Double_double_different_length()
   {
      Double[] expecteds = new Double[] { new Double( 1 ) };
      double[] actuals = new double[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_double_double_expecteds_is_null()
   {
      boolean errorThrown = false;
      double[] actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (double[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_double_Double_expecteds_is_null()
   {
      boolean errorThrown = false;
      Double[] actuals = new Double[] { new Double( 1 ), new Double( 2 ) };
      try{
         ArrayAssertions.assertEquals( (double[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Double_double_expecteds_is_null()
   {
      boolean errorThrown = false;
      double[] actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (Double[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_multi_double_double()
   {
      double[][] expecteds = new double[][] { { 1, 2 }, { 1, 2 } };
      double[][] actuals = new double[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_double_Double()
   {
      double[][] expecteds = new double[][] { { 1, 2 }, { 1, 2 } };
      Double[][] actuals = new Double[][] { { new Double( 1 ), new Double( 2 ) },
            { new Double( 1 ), new Double( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Double_double()
   {
      Double[][] expecteds = new Double[][] { { new Double( 1 ), new Double( 2 ) },
            { new Double( 1 ), new Double( 2 ) } };
      double[][] actuals = new double[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Double_Double()
   {
      Double[][] expecteds = new Double[][] { { new Double( 1 ), new Double( 2 ) },
            { new Double( 1 ), new Double( 2 ) } };
      Double[][] actuals = new Double[][] { { new Double( 1 ), new Double( 2 ) },
            { new Double( 1 ), new Double( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_double_byte()
   {
      boolean errorThrown = false;
      Object expecteds = new double[] { 1, 2 };
      Object actuals = new byte[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_double_double()
   {
      Object expecteds = new double[] { 1, 2 };
      Object actuals = new double[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_double_Double()
   {
      Object expecteds = new double[] { 1, 2 };
      Object actuals = new Double[] { new Double( 1 ), new Double( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Double_double()
   {
      Object expecteds = new Double[] { new Double( 1 ), new Double( 2 ) };
      Object actuals = new double[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_double_Object_array()
   {
      Object expecteds = new double[] { 1, 2 };
      Object actuals = new Object[] { new Double( 1 ), new Double( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_double()
   {
      Object expecteds = new Object[] { new Double( 1 ), new Double( 2 ) };
      Object actuals = new double[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Double( 1 ), new Double( 2 ) };
      Object actuals = new Object[] { new Double( 1 ), new Double( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
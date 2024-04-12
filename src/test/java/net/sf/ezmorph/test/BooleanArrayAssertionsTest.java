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
public class BooleanArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BooleanArrayAssertionsTest.class );
      suite.setName( "BooleanArrayAssertions Tests" );
      return suite;
   }

   public BooleanArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_boolean_boolean()
   {
      boolean[] expecteds = new boolean[] { true, false };
      boolean[] actuals = new boolean[] { true, false };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_boolean_Boolean()
   {
      boolean[] expecteds = new boolean[] { true, false };
      Boolean[] actuals = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Boolean_boolean()
   {
      Boolean[] expecteds = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      boolean[] actuals = new boolean[] { true, false };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Boolean_Boolean()
   {
      Boolean[] expecteds = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      Boolean[] actuals = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_boolean_boolean_actuals_is_null()
   {
      boolean errorThrown = false;
      boolean[] expecteds = new boolean[] { true, false };
      try{
         ArrayAssertions.assertEquals( expecteds, (boolean[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_boolean_Boolean_actuals_is_null()
   {
      boolean errorThrown = false;
      boolean[] expecteds = new boolean[] { true, false };
      try{
         ArrayAssertions.assertEquals( expecteds, (Boolean[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Boolean_boolean_actuals_is_null()
   {
      boolean errorThrown = false;
      Boolean[] expecteds = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      try{
         ArrayAssertions.assertEquals( expecteds, (boolean[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_boolean_boolean_different_length()
   {
      boolean[] expecteds = new boolean[] { true };
      boolean[] actuals = new boolean[] { true, false };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_boolean_Boolean_different_length()
   {
      boolean[] expecteds = new boolean[] { true };
      Boolean[] actuals = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Boolean_boolean_different_length()
   {
      Boolean[] expecteds = new Boolean[] { Boolean.TRUE };
      boolean[] actuals = new boolean[] { true, false };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_boolean_boolean_expecteds_is_null()
   {
      boolean errorThrown = false;
      boolean[] actuals = new boolean[] { true, false };
      try{
         ArrayAssertions.assertEquals( (boolean[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_boolean_Boolean_expecteds_is_null()
   {
      boolean errorThrown = false;
      Boolean[] actuals = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      try{
         ArrayAssertions.assertEquals( (boolean[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Boolean_boolean_expecteds_is_null()
   {
      boolean errorThrown = false;
      boolean[] actuals = new boolean[] { true, false };
      try{
         ArrayAssertions.assertEquals( (Boolean[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_multi_boolean_boolean()
   {
      boolean[][] expecteds = new boolean[][] { { true, false }, { false, true } };
      boolean[][] actuals = new boolean[][] { { true, false }, { false, true } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_boolean_Boolean()
   {
      boolean[][] expecteds = new boolean[][] { { true, false }, { false, true } };
      Boolean[][] actuals = new Boolean[][] { { Boolean.TRUE, Boolean.FALSE },
            { Boolean.FALSE, Boolean.TRUE } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Boolean_boolean()
   {
      Boolean[][] expecteds = new Boolean[][] { { Boolean.TRUE, Boolean.FALSE },
            { Boolean.FALSE, Boolean.TRUE } };
      boolean[][] actuals = new boolean[][] { { true, false }, { false, true } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Boolean_Boolean()
   {
      Boolean[][] expecteds = new Boolean[][] { { Boolean.TRUE, Boolean.FALSE },
            { Boolean.FALSE, Boolean.TRUE } };
      Boolean[][] actuals = new Boolean[][] { { Boolean.TRUE, Boolean.FALSE },
            { Boolean.FALSE, Boolean.TRUE } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_boolean_boolean()
   {
      Object expecteds = new boolean[] { true, false };
      Object actuals = new boolean[] { true, false };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_boolean_Boolean()
   {
      Object expecteds = new boolean[] { true, false };
      Object actuals = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Boolean_boolean()
   {
      Object expecteds = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
      Object actuals = new boolean[] { true, false };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_boolean_double()
   {
      boolean errorThrown = false;
      Object expecteds = new boolean[] { true, false };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_boolean_Object_array()
   {
      Object expecteds = new boolean[] { true, false };
      Object actuals = new Object[] { Boolean.TRUE, Boolean.FALSE };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_boolean()
   {
      Object expecteds = new Object[] { Boolean.TRUE, Boolean.FALSE };
      Object actuals = new boolean[] { true, false };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { Boolean.TRUE, Boolean.FALSE };
      Object actuals = new Object[] { Boolean.TRUE, Boolean.FALSE };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
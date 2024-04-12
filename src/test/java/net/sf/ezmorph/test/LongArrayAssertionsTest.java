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
public class LongArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( LongArrayAssertionsTest.class );
      suite.setName( "LongArrayAssertions Tests" );
      return suite;
   }

   public LongArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_long_long()
   {
      long[] expecteds = new long[] { 1, 2 };
      long[] actuals = new long[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_long_Long()
   {
      long[] expecteds = new long[] { 1, 2 };
      Long[] actuals = new Long[] { new Long( 1 ), new Long( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Long_long()
   {
      Long[] expecteds = new Long[] { new Long( 1 ), new Long( 2 ) };
      long[] actuals = new long[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Long_Long()
   {
      Long[] expecteds = new Long[] { new Long( 1 ), new Long( 2 ) };
      Long[] actuals = new Long[] { new Long( 1 ), new Long( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_long_long_actuals_is_null()
   {
      boolean errorThrown = false;
      long[] expecteds = new long[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (long[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_long_Long_actuals_is_null()
   {
      boolean errorThrown = false;
      long[] expecteds = new long[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (Long[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Long_long_actuals_is_null()
   {
      boolean errorThrown = false;
      Long[] expecteds = new Long[] { new Long( 1 ), new Long( 2 ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (long[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_long_long_different_length()
   {
      long[] expecteds = new long[] { 1 };
      long[] actuals = new long[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_long_Long_different_length()
   {
      long[] expecteds = new long[] { 1 };
      Long[] actuals = new Long[] { new Long( 1 ), new Long( 2 ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Long_long_different_length()
   {
      Long[] expecteds = new Long[] { new Long( 1 ) };
      long[] actuals = new long[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_long_long_expecteds_is_null()
   {
      boolean errorThrown = false;
      long[] actuals = new long[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (long[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_long_Long_expecteds_is_null()
   {
      boolean errorThrown = false;
      Long[] actuals = new Long[] { new Long( 1 ), new Long( 2 ) };
      try{
         ArrayAssertions.assertEquals( (long[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Long_long_expecteds_is_null()
   {
      boolean errorThrown = false;
      long[] actuals = new long[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (Long[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_multi_long_long()
   {
      long[][] expecteds = new long[][] { { 1, 2 }, { 1, 2 } };
      long[][] actuals = new long[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_long_Long()
   {
      long[][] expecteds = new long[][] { { 1, 2 }, { 1, 2 } };
      Long[][] actuals = new Long[][] { { new Long( 1 ), new Long( 2 ) },
            { new Long( 1 ), new Long( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Long_long()
   {
      Long[][] expecteds = new Long[][] { { new Long( 1 ), new Long( 2 ) },
            { new Long( 1 ), new Long( 2 ) } };
      long[][] actuals = new long[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Long_Long()
   {
      Long[][] expecteds = new Long[][] { { new Long( 1 ), new Long( 2 ) },
            { new Long( 1 ), new Long( 2 ) } };
      Long[][] actuals = new Long[][] { { new Long( 1 ), new Long( 2 ) },
            { new Long( 1 ), new Long( 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_long_double()
   {
      boolean errorThrown = false;
      Object expecteds = new long[] { 1, 2 };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_long_long()
   {
      Object expecteds = new long[] { 1, 2 };
      Object actuals = new long[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_long_Long()
   {
      Object expecteds = new long[] { 1, 2 };
      Object actuals = new Long[] { new Long( 1 ), new Long( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Long_long()
   {
      Object expecteds = new Long[] { new Long( 1 ), new Long( 2 ) };
      Object actuals = new long[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_long_Object_array()
   {
      Object expecteds = new long[] { 1, 2 };
      Object actuals = new Object[] { new Long( 1 ), new Long( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_long()
   {
      Object expecteds = new Object[] { new Long( 1 ), new Long( 2 ) };
      Object actuals = new long[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Long( 1 ), new Long( 2 ) };
      Object actuals = new Object[] { new Long( 1 ), new Long( 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
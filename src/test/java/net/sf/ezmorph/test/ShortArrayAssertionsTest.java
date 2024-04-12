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
public class ShortArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( ShortArrayAssertionsTest.class );
      suite.setName( "ShortArrayAssertions Tests" );
      return suite;
   }

   public ShortArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_multi_short_short()
   {
      short[][] expecteds = new short[][] { { 1, 2 }, { 1, 2 } };
      short[][] actuals = new short[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_short_Short()
   {
      short[][] expecteds = new short[][] { { 1, 2 }, { 1, 2 } };
      Short[][] actuals = new Short[][] { { new Short( (short) 1 ), new Short( (short) 2 ) },
            { new Short( (short) 1 ), new Short( (short) 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Short_short()
   {
      Short[][] expecteds = new Short[][] { { new Short( (short) 1 ), new Short( (short) 2 ) },
            { new Short( (short) 1 ), new Short( (short) 2 ) } };
      short[][] actuals = new short[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Short_Short()
   {
      Short[][] expecteds = new Short[][] { { new Short( (short) 1 ), new Short( (short) 2 ) },
            { new Short( (short) 1 ), new Short( (short) 2 ) } };
      Short[][] actuals = new Short[][] { { new Short( (short) 1 ), new Short( (short) 2 ) },
            { new Short( (short) 1 ), new Short( (short) 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      Object actuals = new Object[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_short()
   {
      Object expecteds = new Object[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      Object actuals = new short[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_short_double()
   {
      boolean errorThrown = false;
      Object expecteds = new short[] { 1, 2 };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_short_Object_array()
   {
      Object expecteds = new short[] { 1, 2 };
      Object actuals = new Object[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_short_short()
   {
      Object expecteds = new short[] { 1, 2 };
      Object actuals = new short[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_short_Short()
   {
      Object expecteds = new short[] { 1, 2 };
      Object actuals = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Short_short()
   {
      Object expecteds = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      Object actuals = new short[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_short_short()
   {
      short[] expecteds = new short[] { 1, 2 };
      short[] actuals = new short[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_short_Short()
   {
      short[] expecteds = new short[] { 1, 2 };
      Short[] actuals = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Short_short()
   {
      Short[] expecteds = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      short[] actuals = new short[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Short_Short()
   {
      Short[] expecteds = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      Short[] actuals = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_short_short_actuals_is_null()
   {
      boolean errorThrown = false;
      short[] expecteds = new short[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (short[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_short_Short_actuals_is_null()
   {
      boolean errorThrown = false;
      short[] expecteds = new short[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (Short[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Short_short_actuals_is_null()
   {
      boolean errorThrown = false;
      Short[] expecteds = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (short[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_short_short_different_length()
   {
      short[] expecteds = new short[] { 1 };
      short[] actuals = new short[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_short_Short_different_length()
   {
      short[] expecteds = new short[] { 1 };
      Short[] actuals = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Short_short_different_length()
   {
      Short[] expecteds = new Short[] { new Short( (short) 1 ) };
      short[] actuals = new short[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_short_short_expecteds_is_null()
   {
      boolean errorThrown = false;
      short[] actuals = new short[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (short[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_short_Short_expecteds_is_null()
   {
      boolean errorThrown = false;
      Short[] actuals = new Short[] { new Short( (short) 1 ), new Short( (short) 2 ) };
      try{
         ArrayAssertions.assertEquals( (short[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }
   public void testAssertEquals_Short_short_expecteds_is_null()
   {
      boolean errorThrown = false;
      short[] actuals = new short[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (Short[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }
}
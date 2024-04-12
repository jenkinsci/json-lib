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
public class ArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( ArrayAssertionsTest.class );
      suite.setName( "ArrayAssertions Tests" );
      return suite;
   }

   public ArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_null_null()
   {
      // assert that original contract is not borken
      ArrayAssertions.assertEquals( (Object) null, (Object) null );
   }

   public void testAssertEquals_actuals_is_null()
   {
      boolean errorThrown = false;
      Object[] expecteds = new Object[] { new Object(), new Object() };
      try{
         ArrayAssertions.assertEquals( expecteds, (Object[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_different_length()
   {
      boolean errorThrown = false;
      Object[] expecteds = new Object[] { new Object(), new Object() };
      Object[] actuals = new Object[] { new Object() };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_expecteds_is_null()
   {
      boolean errorThrown = false;
      Object[] actuals = new Object[] { new Object(), new Object() };
      try{
         ArrayAssertions.assertEquals( (Object[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_multi_Object_Object_nulls()
   {
      Object[][] expecteds = new Object[][] { { null }, { null } };
      Object[][] actuals = new Object[][] { { null }, { null } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_null_elements()
   {
      boolean errorThrown = false;
      Object[] expecteds = new Object[] { null };
      Object[] actuals = new Object[] { new Object() };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_null_elements_2()
   {
      boolean errorThrown = false;
      Object[] expecteds = new Object[] { new Object() };
      Object[] actuals = new Object[] { null };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Object_Object_nulls()
   {
      Object[] expecteds = new Object[] { null };
      Object[] actuals = new Object[] { null };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
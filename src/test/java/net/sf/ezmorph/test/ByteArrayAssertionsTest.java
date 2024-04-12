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
public class ByteArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( ByteArrayAssertionsTest.class );
      suite.setName( "ByteArrayAssertions Tests" );
      return suite;
   }

   public ByteArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_byte_byte()
   {
      byte[] expecteds = new byte[] { 1, 2 };
      byte[] actuals = new byte[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_byte_Byte()
   {
      byte[] expecteds = new byte[] { 1, 2 };
      Byte[] actuals = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Byte_byte()
   {
      Byte[] expecteds = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      byte[] actuals = new byte[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Byte_Byte()
   {
      Byte[] expecteds = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      Byte[] actuals = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_byte_byte_actuals_is_null()
   {
      boolean errorThrown = false;
      byte[] expecteds = new byte[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (byte[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_byte_Byte_actuals_is_null()
   {
      boolean errorThrown = false;
      byte[] expecteds = new byte[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, (Byte[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Byte_byte_actuals_is_null()
   {
      boolean errorThrown = false;
      Byte[] expecteds = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (byte[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_byte_byte_different_length()
   {
      byte[] expecteds = new byte[] { 1 };
      byte[] actuals = new byte[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_byte_Byte_different_length()
   {
      byte[] expecteds = new byte[] { 1 };
      Byte[] actuals = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Byte_byte_different_length()
   {
      Byte[] expecteds = new Byte[] { new Byte( (byte) 1 ) };
      byte[] actuals = new byte[] { 1, 2 };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_byte_byte_expecteds_is_null()
   {
      boolean errorThrown = false;
      byte[] actuals = new byte[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (byte[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_byte_Byte_expecteds_is_null()
   {
      boolean errorThrown = false;
      Byte[] actuals = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      try{
         ArrayAssertions.assertEquals( (byte[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Byte_byte_expecteds_is_null()
   {
      boolean errorThrown = false;
      byte[] actuals = new byte[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( (Byte[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_multi_byte_byte()
   {
      byte[][] expecteds = new byte[][] { { 1, 2 }, { 1, 2 } };
      byte[][] actuals = new byte[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_byte_Byte()
   {
      byte[][] expecteds = new byte[][] { { 1, 2 }, { 1, 2 } };
      Byte[][] actuals = new Byte[][] { { new Byte( (byte) 1 ), new Byte( (byte) 2 ) },
            { new Byte( (byte) 1 ), new Byte( (byte) 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Byte_byte()
   {
      Byte[][] expecteds = new Byte[][] { { new Byte( (byte) 1 ), new Byte( (byte) 2 ) },
            { new Byte( (byte) 1 ), new Byte( (byte) 2 ) } };
      byte[][] actuals = new byte[][] { { 1, 2 }, { 1, 2 } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Byte_Byte()
   {
      Byte[][] expecteds = new Byte[][] { { new Byte( (byte) 1 ), new Byte( (byte) 2 ) },
            { new Byte( (byte) 1 ), new Byte( (byte) 2 ) } };
      Byte[][] actuals = new Byte[][] { { new Byte( (byte) 1 ), new Byte( (byte) 2 ) },
            { new Byte( (byte) 1 ), new Byte( (byte) 2 ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_byte_byte()
   {
      Object expecteds = new byte[] { 1, 2 };
      Object actuals = new byte[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_byte_Byte()
   {
      Object expecteds = new byte[] { 1, 2 };
      Object actuals = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Byte_byte()
   {
      Object expecteds = new Byte[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      Object actuals = new byte[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_byte_double()
   {
      boolean errorThrown = false;
      Object expecteds = new byte[] { 1, 2 };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_byte_Object_array()
   {
      Object expecteds = new byte[] { 1, 2 };
      Object actuals = new Object[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_byte()
   {
      Object expecteds = new Object[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      Object actuals = new byte[] { 1, 2 };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      Object actuals = new Object[] { new Byte( (byte) 1 ), new Byte( (byte) 2 ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
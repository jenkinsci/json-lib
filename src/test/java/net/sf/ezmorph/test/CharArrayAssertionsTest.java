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
public class CharArrayAssertionsTest extends TestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( CharArrayAssertionsTest.class );
      suite.setName( "CharArrayAssertions Tests" );
      return suite;
   }

   public CharArrayAssertionsTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testAssertEquals_char_char()
   {
      char[] expecteds = new char[] { 'A', 'B' };
      char[] actuals = new char[] { 'A', 'B' };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_char_char_actuals_is_null()
   {
      boolean errorThrown = false;
      char[] expecteds = new char[] { 'A', 'B' };
      try{
         ArrayAssertions.assertEquals( expecteds, (char[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_char_char_different_length()
   {
      char[] expecteds = new char[] { 'A' };
      char[] actuals = new char[] { 'A', 'B' };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_char_char_expecteds_is_null()
   {
      boolean errorThrown = false;
      char[] actuals = new char[] { 'A', 'B' };
      try{
         ArrayAssertions.assertEquals( (char[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_char_Character()
   {
      char[] expecteds = new char[] { 'A', 'B' };
      Character[] actuals = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_char_Character_actuals_is_null()
   {
      boolean errorThrown = false;
      char[] expecteds = new char[] { 'A', 'B' };
      try{
         ArrayAssertions.assertEquals( expecteds, (Character[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_char_Character_different_length()
   {
      char[] expecteds = new char[] { 'A' };
      Character[] actuals = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_char_Character_expecteds_is_null()
   {
      boolean errorThrown = false;
      Character[] actuals = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      try{
         ArrayAssertions.assertEquals( (char[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Character_char()
   {
      Character[] expecteds = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      char[] actuals = new char[] { 'A', 'B' };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_Character_char_actuals_is_null()
   {
      boolean errorThrown = false;
      Character[] expecteds = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      try{
         ArrayAssertions.assertEquals( expecteds, (char[]) null );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Character_char_different_length()
   {
      Character[] expecteds = new Character[] { new Character( 'A' ) };
      char[] actuals = new char[] { 'A', 'B' };
      boolean errorThrown = false;
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Character_char_expecteds_is_null()
   {
      boolean errorThrown = false;
      char[] actuals = new char[] { 'A', 'B' };
      try{
         ArrayAssertions.assertEquals( (Character[]) null, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_Character_Character()
   {
      Character[] expecteds = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      Character[] actuals = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_char_char()
   {
      char[][] expecteds = new char[][] { { 'A', 'B' }, { 'A', 'B' } };
      char[][] actuals = new char[][] { { 'A', 'B' }, { 'A', 'B' } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_char_Character()
   {
      char[][] expecteds = new char[][] { { 'A', 'B' }, { 'A', 'B' } };
      Character[][] actuals = new Character[][] { { new Character( 'A' ), new Character( 'B' ) },
            { new Character( 'A' ), new Character( 'B' ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Character_char()
   {
      Character[][] expecteds = new Character[][] { { new Character( 'A' ), new Character( 'B' ) },
            { new Character( 'A' ), new Character( 'B' ) } };
      char[][] actuals = new char[][] { { 'A', 'B' }, { 'A', 'B' } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_multi_Character_Character()
   {
      Character[][] expecteds = new Character[][] { { new Character( 'A' ), new Character( 'B' ) },
            { new Character( 'A' ), new Character( 'B' ) } };
      Character[][] actuals = new Character[][] { { new Character( 'A' ), new Character( 'B' ) },
            { new Character( 'A' ), new Character( 'B' ) } };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_char_char()
   {
      Object expecteds = new char[] { 'A', 'B' };
      Object actuals = new char[] { 'A', 'B' };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_char_Character()
   {
      Object expecteds = new char[] { 'A', 'B' };
      Object actuals = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_char_double()
   {
      boolean errorThrown = false;
      Object expecteds = new char[] { 'A', 'B' };
      Object actuals = new double[] { 1, 2 };
      try{
         ArrayAssertions.assertEquals( expecteds, actuals );
      }
      catch( AssertionFailedError expected ){
         errorThrown = true;
      }
      assertTrue( "Expected a failure", errorThrown );
   }

   public void testAssertEquals_OO_char_Object_array()
   {
      Object expecteds = new char[] { 'A', 'B' };
      Object actuals = new Object[] { new Character( 'A' ), new Character( 'B' ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Character_char()
   {
      Object expecteds = new Character[] { new Character( 'A' ), new Character( 'B' ) };
      Object actuals = new char[] { 'A', 'B' };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_char()
   {
      Object expecteds = new Object[] { new Character( 'A' ), new Character( 'B' ) };
      Object actuals = new char[] { 'A', 'B' };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }

   public void testAssertEquals_OO_Object_array_Object_array()
   {
      Object expecteds = new Object[] { new Character( 'A' ), new Character( 'B' ) };
      Object actuals = new Object[] { new Character( 'A' ), new Character( 'B' ) };
      ArrayAssertions.assertEquals( expecteds, actuals );
   }
}
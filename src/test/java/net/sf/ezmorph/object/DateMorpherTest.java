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

package net.sf.ezmorph.object;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class DateMorpherTest extends AbstractObjectMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( DateMorpherTest.class );
      suite.setName( "DateMorpher Tests" );
      return suite;
   }

   private DateMorpher anotherMorpher;
   private DateMorpher anotherMorpherWithDefaultValue;
   private DateMorpher morpher;
   private DateMorpher morpherWithDefaultValue;

   public DateMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testConstructor_illegalFormats()
   {
      try{
         new DateMorpher( null );
         fail( "Should have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }

      try{
         new DateMorpher( new String[0] );
         fail( "Should have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }

      try{
         new DateMorpher( null,false );
         fail( "Should have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }

      try{
         new DateMorpher( new String[0], false );
         fail( "Should have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_canNotParseDate()
   {
      DateMorpher morpher = new DateMorpher( new String[] { "dd/MM/yyyy" } );
      try{
         morpher.morph( "01-01-1976" );
         fail( "Should ve have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_matchFirstPattern()
   {
      Date expected = getUnixEpoch();
      Date actual = (Date) morpher.morph( "1970-1-1" );
      assertEquals( expected, actual );
   }

   public void testMorph_matchSecondPattern()
   {
      Date expected = getUnixEpoch();
      Date actual = (Date) morpher.morph( "1/1/1970" );
      assertEquals( expected, actual );
   }

   public void testMorph_noConversion()
   {
      Date expected = new Date();
      Date actual = (Date) morpher.morph( expected );
      assertEquals( expected, actual );
   }

   public void testMorph_notSupported()
   {
      try{
         morpher.morph( new Object[0] );
         fail( "Should have thrown a MorphException" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testMorph_null()
   {
      assertNull( morpher.morph( null ) );
   }

   public void testMorph_useDefault()
   {
      Date expected = new Date();
      morpher.setDefaultValue( expected );
      morpher.setUseDefault( true );
      Date actual = (Date) morpher.morph( "BOGUS" );
      assertEquals( expected, actual );
   }

   protected Morpher getAnotherMorpher()
   {
      return anotherMorpher;
   }

   protected Morpher getAnotherMorpherWithDefaultValue()
   {
      return anotherMorpherWithDefaultValue;
   }

   protected Morpher getMorpher()
   {
      return morpher;
   }

   protected Morpher getMorpherWithDefaultValue()
   {
      return morpherWithDefaultValue;
   }

   protected void setUp() throws Exception
   {
      morpher = new DateMorpher( new String[] { "yyyy-MM-dd", "MM/dd/yyyy" } );
      morpherWithDefaultValue = new DateMorpher( new String[] { "yyyy-MM-dd", "MM/dd/yyyy" },
            new Date() );
      anotherMorpher = new DateMorpher( new String[] { "yyyy-MM-dd", "MM/dd/yyyy" } );
      anotherMorpherWithDefaultValue = new DateMorpher(
            new String[] { "yyyy-MM-dd", "MM/dd/yyyy" }, getUnixEpoch() );
   }

   private Date getUnixEpoch()
   {
      Calendar c = Calendar.getInstance();
      c.set( Calendar.YEAR, 1970 );
      c.set( Calendar.MONTH, 0 );
      c.set( Calendar.DAY_OF_MONTH, 1 );
      c.set( Calendar.HOUR_OF_DAY, 0 );
      c.set( Calendar.MINUTE, 0 );
      c.set( Calendar.SECOND, 0 );
      c.set( Calendar.MILLISECOND, 0 );
      return c.getTime();
   }
}
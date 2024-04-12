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

package net.sf.ezmorph.primitive;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class CharMorpherTest extends AbstractMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( CharMorpherTest.class );
      suite.setName( "CharMorpher Tests" );
      return suite;
   }

   private Morpher anotherMorpher;
   private Morpher anotherMorpherWithDefaultValue;
   private Morpher morpher;
   private Morpher morpherWithDefaultValue;

   public CharMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testCharMorph()
   {
      String expected = String.valueOf( "A" );
      char actual = ((CharMorpher) getMorpher()).morph( expected );
      assertEquals( 'A', actual );
   }

   public void testCharMorph_throwException_emptyString()
   {
      try{
         ((CharMorpher) getMorpher()).morph( "" );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testCharMorph_throwException_null()
   {
      try{
         ((CharMorpher) getMorpher()).morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testCharMorph_useDefault()
   {
      String expected = String.valueOf( "" );
      char actual = new CharMorpher( 'A' ).morph( expected );
      assertEquals( 'A', actual );
   }

   public void testCharMorph_useDefault_null()
   {
      char actual = new CharMorpher( 'A' ).morph( null );
      assertEquals( 'A', actual );
   }

   protected Morpher getMorpher()
   {
      return morpher;
   }

   protected Morpher getMorpherWithDefaultValue()
   {
      return morpherWithDefaultValue;
   }

   protected Class getMorphsToClass()
   {
      return Character.TYPE;
   }

   protected Morpher getAnotherMorpher()
   {
      return anotherMorpher;
   }

   protected Morpher getAnotherMorpherWithDefaultValue()
   {
      return anotherMorpherWithDefaultValue;
   }

   protected void setUp() throws Exception
   {
      morpher = new CharMorpher();
      morpherWithDefaultValue = new CharMorpher( 'A' );
      anotherMorpher = new CharMorpher();
      anotherMorpherWithDefaultValue = new CharMorpher( 'B' );
   }
}
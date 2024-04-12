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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.Morpher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class BooleanObjectMorpherTest extends AbstractObjectMorpherTestCase
{
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite( BooleanObjectMorpherTest.class );
      suite.setName( "BooleanObjectMorpher Tests" );
      return suite;
   }

   private BooleanObjectMorpher anotherMorpher;
   private BooleanObjectMorpher anotherMorpherWithDefaultValue;
   private BooleanObjectMorpher morpher;
   private BooleanObjectMorpher morpherWithDefaultValue;

   public BooleanObjectMorpherTest( String name )
   {
      super( name );
   }

   // -----------------------------------------------------------------------

   public void testBooleanMorph_noConversion()
   {
      Boolean actual = (Boolean) new BooleanObjectMorpher( Boolean.TRUE ).morph( Boolean.TRUE );
      assertEquals( Boolean.TRUE, actual );
   }

   public void testBooleanMorph_throwException()
   {
      try{
         new BooleanObjectMorpher().morph( "A" );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBooleanMorph_throwException_null()
   {
      try{
         new BooleanObjectMorpher().morph( null );
         fail( "Should have thrown an Exception" );
      }
      catch( MorphException expected ){
         // ok
      }
   }

   public void testBooleanMorph_useDefault()
   {
      String expected = String.valueOf( "A" );
      Boolean actual = (Boolean) new BooleanObjectMorpher( Boolean.TRUE ).morph( expected );
      assertEquals( Boolean.TRUE, actual );
   }

   public void testBooleanMorph_useDefault_null()
   {
      Boolean actual = (Boolean) new BooleanObjectMorpher( Boolean.TRUE ).morph( null );
      assertEquals( Boolean.TRUE, actual );
   }

   public void testBooleanMorphStringValues_false()
   {
      assertEquals( Boolean.FALSE, new BooleanObjectMorpher().morph( "false" ) );
      assertEquals( Boolean.FALSE, new BooleanObjectMorpher().morph( "no" ) );
      assertEquals( Boolean.FALSE, new BooleanObjectMorpher().morph( "off" ) );
   }

   public void testBooleanMorphStringValues_true()
   {
      assertEquals( Boolean.TRUE, new BooleanObjectMorpher().morph( "true" ) );
      assertEquals( Boolean.TRUE, new BooleanObjectMorpher().morph( "yes" ) );
      assertEquals( Boolean.TRUE, new BooleanObjectMorpher().morph( "on" ) );
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
      morpher = new BooleanObjectMorpher();
      morpherWithDefaultValue = new BooleanObjectMorpher( Boolean.TRUE );
      anotherMorpher = new BooleanObjectMorpher();
      anotherMorpherWithDefaultValue = new BooleanObjectMorpher( Boolean.FALSE );
   }
}
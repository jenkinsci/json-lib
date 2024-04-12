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
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite for the EZMorph Object package.
 *
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class ObjectTestSuite extends TestCase
{
   /**
    * Command-line interface.
    */
   public static void main( String[] args )
   {
      TestRunner.run( suite() );
   }

   /**
    * Get the suite of tests
    */
   public static Test suite()
   {
      TestSuite suite = new TestSuite();
      suite.setName( "EZMorph-Object Tests" );
      suite.addTest( BooleanObjectMorpherTest.suite() );
      suite.addTest( CharacterObjectMorpherTest.suite() );
      suite.addTest( DateMorpherTest.suite() );
      suite.addTest( IdentityObjectMorpherTest.suite() );
      suite.addTest( BigDecimalMorpherTest.suite() );
      suite.addTest( BigIntegerMorpherTest.suite() );
      suite.addTest( NumberMorpherTest.suite() );
      suite.addTest( StringMorpherTest.suite() );
      suite.addTest( ClassMorpherTest.suite() );
      suite.addTest( MapToDateMorpherTest.suite() );
      suite.addTest( ObjectListMorpherTest.suite() );
      suite.addTest( SwitchingMorpherTest.suite() );

      return suite;
   }

   /**
    * Construct a new instance.
    */
   public ObjectTestSuite( String name )
   {
      super( name );
   }
}
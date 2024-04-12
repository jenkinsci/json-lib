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

package net.sf.ezmorph.array;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite for the EZMorph Array package.
 * 
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class ArrayTestSuite extends TestCase
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
      suite.setName( "EZMorph-Array Tests" );
      suite.addTest( BooleanArrayMorpherTest.suite() );
      suite.addTest( CharArrayMorpherTest.suite() );
      suite.addTest( ByteArrayMorpherTest.suite() );
      suite.addTest( ShortArrayMorpherTest.suite() );
      suite.addTest( IntArrayMorpherTest.suite() );
      suite.addTest( LongArrayMorpherTest.suite() );
      suite.addTest( FloatArrayMorpherTest.suite() );
      suite.addTest( DoubleArrayMorpherTest.suite() );
      suite.addTest( ObjectArrayMorpherTest.suite() );
      suite.addTest( BooleanObjectArrayMorpherTest.suite() );
      suite.addTest( CharacterObjectArrayMorpherTest.suite() );

      return suite;
   }

   /**
    * Construct a new instance.
    */
   public ArrayTestSuite( String name )
   {
      super( name );
   }
}

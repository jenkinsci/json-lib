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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite for the EZMorph Test package.
 * 
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class TestTestSuite extends TestCase
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
      suite.setName( "EZMorph-Test Tests" );
      suite.addTest( ArrayAssertionsTest.suite() );
      suite.addTest( BooleanArrayAssertionsTest.suite() );
      suite.addTest( ByteArrayAssertionsTest.suite() );
      suite.addTest( ShortArrayAssertionsTest.suite() );
      suite.addTest( IntArrayAssertionsTest.suite() );
      suite.addTest( LongArrayAssertionsTest.suite() );
      suite.addTest( FloatArrayAssertionsTest.suite() );
      suite.addTest( DoubleArrayAssertionsTest.suite() );
      suite.addTest( CharArrayAssertionsTest.suite() );

      return suite;
   }

   /**
    * Construct a new instance.
    */
   public TestTestSuite( String name )
   {
      super( name );
   }
}
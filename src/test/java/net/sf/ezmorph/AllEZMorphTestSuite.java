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

package net.sf.ezmorph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.ezmorph.array.ArrayTestSuite;
import net.sf.ezmorph.bean.BeanTestSuite;
import net.sf.ezmorph.object.ObjectTestSuite;
import net.sf.ezmorph.primitive.PrimitiveTestSuite;
import net.sf.ezmorph.test.TestTestSuite;

/**
 * Test suite for [ezmorph].
 *
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class AllEZMorphTestSuite extends TestCase
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
      suite.setName( "EZMorph (all) Tests" );
      suite.addTest( MorphTestSuite.suite() );
      suite.addTest( ArrayTestSuite.suite() );
      suite.addTest( ObjectTestSuite.suite() );
      suite.addTest( BeanTestSuite.suite() );
      suite.addTest( PrimitiveTestSuite.suite() );
      suite.addTest( TestTestSuite.suite() );
      return suite;
   }

   /**
    * Construct a new instance.
    */
   public AllEZMorphTestSuite( String name )
   {
      super( name );
   }
}
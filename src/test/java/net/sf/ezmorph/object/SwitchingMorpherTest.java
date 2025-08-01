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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.sample.BeanA;
import net.sf.ezmorph.object.sample.BeanB;
import net.sf.ezmorph.object.sample.WrapperA;
import net.sf.ezmorph.object.sample.WrapperB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
class SwitchingMorpherTest {
    private SwitchingMorpher morpher;

    // -----------------------------------------------------------------------

    @Test
    void testMorphWrapperAToBeanA() {
        WrapperA wrapper = new WrapperA();
        wrapper.setInteger("12");
        BeanA actual = (BeanA) morpher.morph(wrapper);
        BeanA expected = new BeanA();
        expected.setInteger(12);
        assertEquals(expected, actual);
    }

    @Test
    void testMorphWrapperBToBeanB() {
        WrapperB wrapper = new WrapperB();
        wrapper.setBool("false");
        BeanB actual = (BeanB) morpher.morph(wrapper);
        BeanB expected = new BeanB();
        expected.setBool(false);
        assertEquals(expected, actual);
    }

    @Test
    void testMorph_null() {
        assertNull(morpher.morph(null));
    }

    @BeforeEach
    void setUp() {
        Map<Class<?>, Class<?>> classMap = new HashMap<>();
        classMap.put(WrapperA.class, BeanA.class);
        classMap.put(WrapperB.class, BeanB.class);
        MorpherRegistry morpherRegistry = new MorpherRegistry();
        MorphUtils.registerStandardMorphers(morpherRegistry);
        morpherRegistry.registerMorpher(new BeanMorpher(BeanA.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(BeanB.class, morpherRegistry));
        morpher = new SwitchingMorpher(classMap, morpherRegistry);
    }
}

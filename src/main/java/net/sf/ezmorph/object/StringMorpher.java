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

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.ObjectMorpher;

/**
 * Morphs to a String.<br>
 * This morpher is a singleton.
 *
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public final class StringMorpher implements ObjectMorpher {
    private static final StringMorpher INSTANCE = new StringMorpher();

    /**
     * Returns the singleton instance
     */
    public static StringMorpher getInstance() {
        return INSTANCE;
    }

    private StringMorpher() {}

    public boolean equals(Object obj) {
        return INSTANCE == obj;
    }

    public int hashCode() {
        return 42 + getClass().hashCode();
    }

    public Object morph(Object value) {
        if (value == null) {
            return null;
        }

        if (!supports(value.getClass())) {
            throw new MorphException("Class not supported. " + value.getClass());
        }

        if (String.class.isAssignableFrom(value.getClass())) {
            return (String) value;
        }

        return String.valueOf(value);
    }

    public Class morphsTo() {
        return String.class;
    }

    public boolean supports(Class clazz) {
        return !clazz.isArray();
    }
}

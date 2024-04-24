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

import net.sf.ezmorph.Morpher;

/**
 * Base class for primitive value conversion.<br>
 *
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public abstract class AbstractPrimitiveMorpher implements Morpher {
    private boolean useDefault = false;

    public AbstractPrimitiveMorpher() {}

    /**
     * @param useDefault if morph() should return a default value if the value to
     *        be morphed is null
     */
    public AbstractPrimitiveMorpher(boolean useDefault) {
        this.useDefault = useDefault;
    }

    /**
     * Returns if this morpher will use a default value if the value to be
     * morphed is null
     */
    public boolean isUseDefault() {
        return useDefault;
    }

    /**
     * Returns true if the Morpher supports conversion from this Class.<br>
     * Supports any type that is not an Array.
     *
     * @param clazz the source Class
     * @return true if clazz is supported by this morpher, false otherwise.
     */
    public boolean supports(Class clazz) {
        return !clazz.isArray();
    }
}

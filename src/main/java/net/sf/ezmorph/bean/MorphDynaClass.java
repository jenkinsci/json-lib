/*
 * Copyright 2006-2007-2007 the original author or authors.
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

package net.sf.ezmorph.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public final class MorphDynaClass implements DynaClass, Serializable {
    private static final Comparator dynaPropertyComparator = new Comparator() {
        @Override
        public int compare(Object a, Object b) {
            if (a instanceof DynaProperty && b instanceof DynaProperty) {
                DynaProperty p1 = (DynaProperty) a;
                DynaProperty p2 = (DynaProperty) b;
                return p1.getName().compareTo(p2.getName());
            }
            return -1;
        }
    };

    private static final long serialVersionUID = -613214016860871560L;

    private Map attributes;
    private Class beanClass;
    private DynaProperty[] dynaProperties;
    private String name;
    private Map properties = new HashMap();
    private Class type;

    public MorphDynaClass(Map attributes) {
        this(null, null, attributes);
    }

    public MorphDynaClass(Map attributes, boolean exceptionOnEmptyAttributes) {
        this(null, null, attributes, exceptionOnEmptyAttributes);
    }

    public MorphDynaClass(String name, Class type, Map attributes) {
        this(name, type, attributes, false);
    }

    public MorphDynaClass(String name, Class type, Map attributes, boolean exceptionOnEmptyAttributes) {
        if (name == null) {
            name = "MorphDynaClass";
        }
        if (type == null) {
            type = MorphDynaBean.class;
        }
        if (!MorphDynaBean.class.isAssignableFrom(type)) {
            throw new MorphException("MorphDynaBean is not assignable from " + type.getName());
        }
        if (attributes == null || attributes.isEmpty()) {
            if (exceptionOnEmptyAttributes) {
                throw new MorphException("Attributes map is null or empty.");
            } else {
                attributes = new HashMap();
            }
        }
        this.name = name;
        this.type = type;
        this.attributes = attributes;
        process();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof MorphDynaClass)) {
            return false;
        }

        MorphDynaClass other = (MorphDynaClass) obj;

        if (!Objects.equals(name, other.name) || !Objects.equals(type, other.type)) {
            return false;
        }

        if (dynaProperties.length != other.dynaProperties.length) {
            return false;
        }

        for (int i = 0; i < dynaProperties.length; i++) {
            DynaProperty a = dynaProperties[i];
            DynaProperty b = other.dynaProperties[i];
            if (!Objects.equals(a.getName(), b.getName()) || !Objects.equals(a.getType(), b.getType())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public DynaProperty[] getDynaProperties() {
        return dynaProperties;
    }

    @Override
    public DynaProperty getDynaProperty(String propertyName) {
        if (propertyName == null) {
            throw new MorphException("Unnespecified bean property name");
        }
        return (DynaProperty) properties.get(propertyName);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(type);
        for (DynaProperty dynaProperty : dynaProperties) {
            result = 31 * result + Objects.hashCode(dynaProperty.getName());
            result = 31 * result + Objects.hashCode(dynaProperty.getType());
        }
        return result;
    }

    @Override
    public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
        return newInstance(null);
    }

    public DynaBean newInstance(MorpherRegistry morpherRegistry) throws IllegalAccessException, InstantiationException {
        if (morpherRegistry == null) {
            morpherRegistry = new MorpherRegistry();
            MorphUtils.registerStandardMorphers(morpherRegistry);
        }
        MorphDynaBean dynaBean = (MorphDynaBean) getBeanClass().newInstance();
        dynaBean.setDynaBeanClass(this);
        dynaBean.setMorpherRegistry(morpherRegistry);
        for (Object o : attributes.keySet()) {
            String key = (String) o;
            dynaBean.set(key, null);
        }
        return dynaBean;
    }

    @Override
    public String toString() {
        return String.format("%s[name=%s, type=%s, attributes=%s]", getClass().getSimpleName(), name, type, attributes);
    }

    protected Class getBeanClass() {
        if (this.beanClass == null) {
            process();
        }
        return this.beanClass;
    }

    private void process() {
        this.beanClass = this.type;

        try {
            Iterator entries = attributes.entrySet().iterator();
            dynaProperties = new DynaProperty[attributes.size()];
            int i = 0;
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String pname = (String) entry.getKey();
                Object pclass = entry.getValue();
                DynaProperty dynaProperty = null;
                if (pclass instanceof String) {
                    Class klass = (Class) Class.forName((String) pclass);
                    if (klass.isArray() && klass.getComponentType().isArray()) {
                        throw new MorphException("Multidimensional arrays are not supported");
                    }
                    dynaProperty = new DynaProperty(pname, klass);
                } else if (pclass instanceof Class) {
                    Class klass = (Class) pclass;
                    if (klass.isArray() && klass.getComponentType().isArray()) {
                        throw new MorphException("Multidimensional arrays are not supported");
                    }
                    dynaProperty = new DynaProperty(pname, klass);
                } else {
                    throw new MorphException("Type must be String or Class");
                }
                properties.put(dynaProperty.getName(), dynaProperty);
                dynaProperties[i++] = dynaProperty;
            }
        } catch (ClassNotFoundException cnfe) {
            throw new MorphException(cnfe);
        }

        // keep properties sorted by name
        Arrays.sort(dynaProperties, 0, dynaProperties.length, dynaPropertyComparator);
    }
}

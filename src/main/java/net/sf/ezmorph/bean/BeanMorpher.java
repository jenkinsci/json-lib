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

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.ObjectMorpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Converts a JavaBean into another JavaBean or DynaBean.<br>
 * This Morpher will try to match every property from the target JavaBean's
 * class to the properties of the source JavaBean. If any target property
 * differs in type from the source property, it will try to morph it. If a
 * Morpher is not found for that type, the conversion will be aborted with a
 * MorphException; this may be changed by setting the Morpher to be lenient, in
 * that way it will ignore the property (the resulting value will be null).
 *
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public final class BeanMorpher implements ObjectMorpher {
    private static final Logger logger = Logger.getLogger(BeanMorpher.class.getName());
    private final Class beanClass;
    private boolean lenient;
    private final MorpherRegistry morpherRegistry;

    /**
     * @param beanClass the target class to morph to
     * @param morpherRegistry a registry of morphers
     */
    public BeanMorpher(Class beanClass, MorpherRegistry morpherRegistry) {
        this(beanClass, morpherRegistry, false);
    }

    /**
     * @param beanClass the target class to morph to
     * @param morpherRegistry a registry of morphers
     * @param lenient if an exception should be raised if no morpher is found for
     *        a target property
     */
    public BeanMorpher(Class beanClass, MorpherRegistry morpherRegistry, boolean lenient) {
        validateClass(beanClass);
        if (morpherRegistry == null) {
            throw new MorphException("morpherRegistry is null");
        }
        this.beanClass = beanClass;
        this.morpherRegistry = morpherRegistry;
        this.lenient = lenient;
    }

    @Override
    public Object morph(Object sourceBean) {
        if (sourceBean == null) {
            return null;
        }
        if (!supports(sourceBean.getClass())) {
            throw new MorphException(
                    "unsupported class: " + sourceBean.getClass().getName());
        }

        Object targetBean = null;

        try {
            targetBean = beanClass.newInstance();
            PropertyDescriptor[] targetPds = PropertyUtils.getPropertyDescriptors(beanClass);
            for (PropertyDescriptor targetPd : targetPds) {
                String name = targetPd.getName();
                if (targetPd.getWriteMethod() == null) {
                    logger.log(
                            Level.INFO,
                            "Property '" + beanClass.getName() + "." + name + "' has no write method. SKIPPED.");
                    continue;
                }

                Class sourceType = null;
                if (sourceBean instanceof DynaBean) {
                    DynaBean dynaBean = (DynaBean) sourceBean;
                    DynaProperty dynaProperty = dynaBean.getDynaClass().getDynaProperty(name);
                    if (dynaProperty == null) {
                        logger.log(Level.WARNING, "DynaProperty '" + name + "' does not exist. SKIPPED.");
                        continue;
                    }
                    sourceType = dynaProperty.getType();
                } else {
                    PropertyDescriptor sourcePd = PropertyUtils.getPropertyDescriptor(sourceBean, name);
                    if (sourcePd == null) {
                        logger.log(
                                Level.WARNING,
                                "Property '" + sourceBean.getClass().getName() + "." + name
                                        + "' does not exist. SKIPPED.");
                        continue;
                    } else if (sourcePd.getReadMethod() == null) {
                        logger.log(
                                Level.WARNING,
                                "Property '" + sourceBean.getClass().getName() + "." + name
                                        + "' has no read method. SKIPPED.");
                        continue;
                    }
                    sourceType = sourcePd.getPropertyType();
                }

                Class targetType = targetPd.getPropertyType();
                Object value = PropertyUtils.getProperty(sourceBean, name);
                setProperty(targetBean, name, sourceType, targetType, value);
            }
        } catch (MorphException me) {
            throw me;
        } catch (Exception e) {
            throw new MorphException(e);
        }

        return targetBean;
    }

    @Override
    public Class morphsTo() {
        return beanClass;
    }

    @Override
    public boolean supports(Class clazz) {
        return !clazz.isArray();
    }

    private void setProperty(Object targetBean, String name, Class sourceType, Class targetType, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (targetType.isAssignableFrom(sourceType)) {
            if (value == null && targetType.isPrimitive()) {
                value = morpherRegistry.morph(targetType, value);
            }
            PropertyUtils.setProperty(targetBean, name, value);
        } else {
            if (targetType.equals(Object.class)) {
                // no conversion
                PropertyUtils.setProperty(targetBean, name, value);
            } else {
                if (value == null) {
                    if (targetType.isPrimitive()) {
                        PropertyUtils.setProperty(targetBean, name, morpherRegistry.morph(targetType, value));
                    }
                } else {
                    if (IdentityObjectMorpher.getInstance() == morpherRegistry.getMorpherFor(targetType)) {
                        if (!lenient) {
                            throw new MorphException("Can't find a morpher for target class " + targetType.getName()
                                    + " (" + name + ")");
                        } else {
                            logger.log(
                                    Level.INFO,
                                    "Can't find a morpher for target class " + targetType.getName() + " (" + name
                                            + ") SKIPPED");
                        }
                    } else {
                        PropertyUtils.setProperty(targetBean, name, morpherRegistry.morph(targetType, value));
                    }
                }
            }
        }
    }

    private void validateClass(Class clazz) {
        if (clazz == null) {
            throw new MorphException("target class is null");
        } else if (clazz.isPrimitive()) {
            throw new MorphException("target class is a primitive");
        } else if (clazz.isArray()) {
            throw new MorphException("target class is an array");
        } else if (clazz.isInterface()) {
            throw new MorphException("target class is an interface");
        } else if (DynaBean.class.isAssignableFrom(clazz)) {
            throw new MorphException("target class is a DynaBean");
        } else if (Number.class.isAssignableFrom(clazz)
                || Boolean.class.isAssignableFrom(clazz)
                || Character.class.isAssignableFrom(clazz)) {
            throw new MorphException("target class is a wrapper");
        } else if (String.class.isAssignableFrom(clazz)) {
            throw new MorphException("target class is a String");
        } else if (Collection.class.isAssignableFrom(clazz)) {
            throw new MorphException("target class is a Collection");
        } else if (Map.class.isAssignableFrom(clazz)) {
            throw new MorphException("target class is a Map");
        }
    }
}

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ezmorph.beanutils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONException;

/**
 * Utility class to replace commons-beanutils PropertyUtils functionality
 * using native Java reflection and java.beans APIs.
 */
public class PropertyUtils {

    private static final Map<Class<?>, PropertyDescriptor[]> propertyDescriptorCache = new HashMap<>();

    /**
     * Get property descriptors for a given class.
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass) {
        if (beanClass == null) {
            return new PropertyDescriptor[0];
        }

        PropertyDescriptor[] cached = propertyDescriptorCache.get(beanClass);
        if (cached != null) {
            return cached;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            propertyDescriptorCache.put(beanClass, descriptors);
            return descriptors;
        } catch (IntrospectionException e) {
            throw new JSONException("Failed to get property descriptors for " + beanClass.getName(), e);
        }
    }

    /**
     * Get property descriptors for a given bean instance.
     */
    public static PropertyDescriptor[] getPropertyDescriptors(final Object bean) {
        if (bean == null) {
            return new PropertyDescriptor[0];
        }
        return Arrays.stream(getPropertyDescriptors(bean.getClass()))
                .filter(descriptor -> !"class".equals(descriptor.getName()))
                .toArray(PropertyDescriptor[]::new);
    }

    /**
     * Get property descriptor for a specific property.
     */
    public static PropertyDescriptor getPropertyDescriptor(Object bean, String propertyName) {
        if (bean == null || propertyName == null) {
            return null;
        }

        PropertyDescriptor[] descriptors = getPropertyDescriptors(bean.getClass());
        for (PropertyDescriptor descriptor : descriptors) {
            if (propertyName.equals(descriptor.getName())) {
                return descriptor;
            }
        }
        return null;
    }

    /**
     * Get property value from a bean.
     */
    public static Object getProperty(Object bean, String propertyName) {
        if (bean == null || propertyName == null) {
            return null;
        }

        // Handle DynaBean objects
        if (bean instanceof DynaBean) {
            return ((DynaBean) bean).get(propertyName);
        }

        // Handle Map objects
        if (bean instanceof Map) {
            return ((Map<?, ?>) bean).get(propertyName);
        }

        PropertyDescriptor descriptor = getPropertyDescriptor(bean, propertyName);
        if (descriptor == null) {
            throw new JSONException("Property '" + propertyName + "' not found in "
                    + bean.getClass().getName());
        }

        Method readMethod = descriptor.getReadMethod();
        if (readMethod == null) {
            throw new JSONException("Property '" + propertyName + "' has no read method in "
                    + bean.getClass().getName());
        }

        try {
            readMethod.setAccessible(true);
            return readMethod.invoke(bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JSONException(
                    "Failed to get property '" + propertyName + "' from "
                            + bean.getClass().getName(),
                    e);
        }
    }

    /**
     * Set property value on a bean.
     */
    public static void setProperty(Object bean, String propertyName, Object value) throws NoSuchMethodException {
        setSimpleProperty(bean, propertyName, value);
    }

    /**
     * Set simple property value on a bean.
     */
    public static void setSimpleProperty(Object bean, String propertyName, Object value) throws NoSuchMethodException {
        if (bean == null || propertyName == null) {
            return;
        }

        // Handle DynaBean objects
        if (bean instanceof DynaBean) {
            ((DynaBean) bean).set(propertyName, value);
            return;
        }

        // Handle Map objects
        if (bean instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) bean;
            map.put(propertyName, value);
            return;
        }

        PropertyDescriptor descriptor = getPropertyDescriptor(bean, propertyName);
        if (descriptor == null) {
            throw new NoSuchMethodException("Property '" + propertyName + "' not found in "
                    + bean.getClass().getName());
        }

        Method writeMethod = descriptor.getWriteMethod();
        if (writeMethod == null) {
            throw new NoSuchMethodException("Property '" + propertyName + "' has no write method in "
                    + bean.getClass().getName());
        }

        try {
            writeMethod.setAccessible(true);
            writeMethod.invoke(bean, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JSONException(
                    "Failed to set property '" + propertyName + "' on "
                            + bean.getClass().getName(),
                    e);
        }
    }
}

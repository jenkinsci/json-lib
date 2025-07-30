/*
 * Copyright 2002-2009 the original author or authors.
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

package net.sf.json.groovy;

import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.MissingMethodException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * A Groovy builder for JSON values.
 *
 * <pre>
 * def books1 = builder.books {
 * book = [title: "The Definitive Guide to Grails", author: "Graeme Rocher"]
 * book = [title: "The Definitive Guide to Grails", author: "Graeme Rocher"]
 * }
 *
 * def books2 = builder.books {
 * book = new Book(title: "The Definitive Guide to Grails",
 * author: "Graeme Rocher")
 * book = new Book(title: "The Definitive Guide to Grails",
 * author: "Graeme Rocher")
 * }
 *
 * def books3 = builder.books {
 * book = {
 * title = "The Definitive Guide to Grails"
 * author= "Graeme Rocher"
 * }
 * book = {
 * title = "The Definitive Guide to Grails"
 * author= "Graeme Rocher"
 * }
 * }
 *
 * def books4 = builder.books {
 * book {
 * title = "The Definitive Guide to Grails"
 * author= "Graeme Rocher"
 * }
 * book {
 * title = "The Definitive Guide to Grails"
 * author= "Graeme Rocher"
 * }
 * }
 *
 * def books5 = builder.books {
 * 2.times {
 * book = {
 * title = "The Definitive Guide to Grails"
 * author= "Graeme Rocher"
 * }
 * }
 * }
 *
 * def books6 = builder.books {
 * 2.times {
 * book {
 * title = "The Definitive Guide to Grails"
 * author= "Graeme Rocher"
 * }
 * }
 * }
 *
 *
 * all 6 books variables output the same JSON
 *
 * {"books": {
 * "book": [{
 * "title": "The Definitive Guide to Grails",
 * "author": "Graeme Rocher"
 * },{
 * "title": "The Definitive Guide to Grails",
 * "author": "Graeme Rocher"
 * }]
 * }
 * }
 * </pre>
 *
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public class JsonGroovyBuilder extends GroovyObjectSupport {
    private static final String JSON = "json";
    private JSON current;
    private Map properties;
    private Stack stack;
    private JsonConfig jsonConfig;

    public JsonGroovyBuilder() {
        stack = new Stack();
        properties = new HashMap();
        jsonConfig = new JsonConfig();
    }

    public JsonConfig getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(JsonConfig jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    @Override
    public Object getProperty(String name) {
        if (!stack.isEmpty()) {
            Object top = stack.peek();
            if (top instanceof JSONObject) {
                JSONObject json = (JSONObject) top;
                if (json.containsKey(name)) {
                    return json.get(name);
                } else {
                    return _getProperty(name);
                }
            } else {
                return _getProperty(name);
            }
        } else {
            return _getProperty(name);
        }
    }

    @Override
    public Object invokeMethod(String name, Object arg) {
        if (JSON.equals(name) && stack.isEmpty()) {
            return createObject(name, arg);
        }

        Object[] args = (Object[]) arg;
        if (args.length == 0) {
            throw new MissingMethodException(name, getClass(), args);
        }

        Object value = null;
        if (args.length > 1) {
            JSONArray array = new JSONArray();
            stack.push(array);
            for (Object o : args) {
                if (o instanceof Closure closure) {
                    append(name, createObject(closure));
                } else if (o instanceof Map map) {
                    append(name, createObject(map));
                } else if (o instanceof List list) {
                    append(name, createArray(list));
                } else {
                    _append(name, o, (JSON) stack.peek());
                }
            }
            stack.pop();
        } else {
            if (args[0] instanceof Closure) {
                value = createObject((Closure) args[0]);
            } else if (args[0] instanceof Map) {
                value = createObject((Map) args[0]);
            } else if (args[0] instanceof List) {
                value = createArray((List) args[0]);
            }
        }

        if (stack.isEmpty()) {
            JSONObject object = new JSONObject();
            object.accumulate(name, current);
            current = object;
        } else {
            JSON top = (JSON) stack.peek();
            if (top instanceof JSONObject) {
                append(name, current == null ? value : current);
            }
        }

        return current;
    }

    @Override
    public void setProperty(String name, Object value) {
        if (value instanceof GString) {
            value = value.toString();
            try {
                value = JSONSerializer.toJSON(value);
            } catch (JSONException jsone) {
                // its a String literal
            }
        } else if (value instanceof Closure) {
            value = createObject((Closure) value);
        } else if (value instanceof Map) {
            value = createObject((Map) value);
        } else if (value instanceof List) {
            value = createArray((List) value);
        }

        append(name, value);
    }

    private Object _getProperty(String name) {
        if (properties.containsKey(name)) {
            return properties.get(name);
        } else {
            return super.getProperty(name);
        }
    }

    private void append(String key, Object value) {
        Object target = null;
        if (!stack.isEmpty()) {
            target = stack.peek();
            current = (JSON) target;
            _append(key, value, current);
        } else {
            properties.put(key, value);
        }
    }

    private void _append(String key, Object value, JSON target) {
        if (target instanceof JSONObject) {
            ((JSONObject) target).accumulate(key, value, jsonConfig);
        } else if (target instanceof JSONArray) {
            ((JSONArray) target).element(value, jsonConfig);
        }
    }

    private JSON createArray(List list) {
        JSONArray array = new JSONArray();
        stack.push(array);
        for (Object element : list) {
            if (element instanceof Closure closure) {
                element = createObject(closure);
            } else if (element instanceof Map map) {
                element = createObject(map);
            } else if (element instanceof List l) {
                element = createArray(l);
            }
            array.element(element);
        }
        stack.pop();
        return array;
    }

    private JSON createObject(Closure closure) {
        JSONObject object = new JSONObject();
        stack.push(object);
        closure.setDelegate(this);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
        stack.pop();
        return object;
    }

    private JSON createObject(Map map) {
        JSONObject object = new JSONObject();
        stack.push(object);
        for (Object o : map.entrySet()) {
            Map.Entry property = (Map.Entry) o;
            String key = String.valueOf(property.getKey());
            Object value = property.getValue();
            if (value instanceof Closure closure) {
                value = createObject(closure);
            } else if (value instanceof Map m) {
                value = createObject(m);
            } else if (value instanceof List list) {
                value = createArray(list);
            }
            object.element(key, value);
        }
        stack.pop();
        return object;
    }

    private JSON createObject(String name, Object arg) {
        Object[] args = (Object[]) arg;
        if (args.length == 0) {
            throw new MissingMethodException(name, getClass(), args);
        }

        if (args.length == 1) {
            if (args[0] instanceof Closure) {
                return createObject((Closure) args[0]);
            } else if (args[0] instanceof Map) {
                return createObject((Map) args[0]);
            } else if (args[0] instanceof List) {
                return createArray((List) args[0]);
            } else {
                throw new JSONException("Unsupported type");
            }
        } else {
            JSONArray array = new JSONArray();
            stack.push(array);
            for (Object o : args) {
                if (o instanceof Closure closure) {
                    append(name, createObject(closure));
                } else if (o instanceof Map map) {
                    append(name, createObject(map));
                } else if (o instanceof List list) {
                    append(name, createArray(list));
                } else {
                    _append(name, o, (JSON) stack.peek());
                }
            }
            stack.pop();
            return array;
        }
    }
}

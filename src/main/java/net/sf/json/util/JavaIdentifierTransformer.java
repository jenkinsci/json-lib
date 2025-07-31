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

package net.sf.json.util;

import net.sf.json.JSONException;

/**
 * Transforms a string into a valid Java identifier.<br>
 * There are five predefined strategies:
 * <ul>
 * <li>NOOP: does not perform transformation.</li>
 * <li>CAMEL_CASE: follows the camel case convention, deletes non
 * JavaIdentifierPart chars.</li>
 * <li>UNDERSCORE: transform whitespace and non JavaIdentifierPart chars to
 * '_'.</li>
 * <li>WHITESPACE: deletes whitespace and non JavaIdentifierPart chars.</li>
 * <li>STRICT: always throws a JSONException, does not perform transformation.</li>
 * </ul>
 *
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public abstract class JavaIdentifierTransformer {
    /** CamelCase transformer 'camel case' =gt; 'camelCase' */
    public static final JavaIdentifierTransformer CAMEL_CASE = new CamelCaseJavaIdentifierTransformer();
    /** Noop transformer '@invalid' =&gt; '@invalid' */
    public static final JavaIdentifierTransformer NOOP = new NoopJavaIdentifierTransformer();
    /** Strict transformer '@invalid' =&gt; JSONException */
    public static final JavaIdentifierTransformer STRICT = new StrictJavaIdentifierTransformer();
    /** Underscore transformer 'under score' =&gt; 'under_score' */
    public static final JavaIdentifierTransformer UNDERSCORE = new UnderscoreJavaIdentifierTransformer();
    /** Whitespace transformer 'white space' =&gt; 'whitespace' */
    public static final JavaIdentifierTransformer WHITESPACE = new WhiteSpaceJavaIdentifierTransformer();

    public abstract String transformToJavaIdentifier(String str);

    /**
     * Removes all non JavaIdentifier chars from the start of the string.
     *
     * @throws JSONException if the resulting string has zero length.
     */
    protected final String shaveOffNonJavaIdentifierStartChars(String str) {
        String str2 = str;
        // shave off first char if not valid
        boolean ready = false;
        while (!ready) {
            if (!Character.isJavaIdentifierStart(str2.charAt(0))) {
                str2 = str2.substring(1);
                if (str2.isEmpty()) {
                    throw new JSONException("Can't convert '" + str + "' to a valid Java identifier");
                }
            } else {
                ready = true;
            }
        }
        return str2;
    }

    private static final class CamelCaseJavaIdentifierTransformer extends JavaIdentifierTransformer {
        @Override
        public String transformToJavaIdentifier(String str) {
            if (str == null) {
                return null;
            }

            String str2 = shaveOffNonJavaIdentifierStartChars(str);

            StringBuilder sb = new StringBuilder();
            boolean toUpperCaseNextChar = false;
            for (char ch : str2.toCharArray()) {
                if (!Character.isJavaIdentifierPart(ch) || Character.isWhitespace(ch)) {
                    toUpperCaseNextChar = true;
                } else {
                    if (toUpperCaseNextChar) {
                        sb.append(Character.toUpperCase(ch));
                        toUpperCaseNextChar = false;
                    } else {
                        sb.append(ch);
                    }
                }
            }
            return sb.toString();
        }
    }

    private static final class NoopJavaIdentifierTransformer extends JavaIdentifierTransformer {
        @Override
        public String transformToJavaIdentifier(String str) {
            return str;
        }
    }

    private static final class StrictJavaIdentifierTransformer extends JavaIdentifierTransformer {
        @Override
        public String transformToJavaIdentifier(String str) {
            throw new JSONException("'" + str + "' is not a valid Java identifier.");
        }
    }

    private static final class UnderscoreJavaIdentifierTransformer extends JavaIdentifierTransformer {
        @Override
        public String transformToJavaIdentifier(String str) {
            if (str == null) {
                return null;
            }
            String str2 = shaveOffNonJavaIdentifierStartChars(str);

            StringBuilder sb = new StringBuilder();
            boolean toUnderScorePreviousChar = false;
            for (char ch : str2.toCharArray()) {
                if (!Character.isJavaIdentifierPart(ch) || Character.isWhitespace(ch)) {
                    toUnderScorePreviousChar = true;
                } else {
                    if (toUnderScorePreviousChar) {
                        sb.append("_");
                        toUnderScorePreviousChar = false;
                    }
                    sb.append(ch);
                }
            }
            if (sb.charAt(sb.length() - 1) == '_') {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }

    private static final class WhiteSpaceJavaIdentifierTransformer extends JavaIdentifierTransformer {
        @Override
        public String transformToJavaIdentifier(String str) {
            if (str == null) {
                return null;
            }
            String str2 = shaveOffNonJavaIdentifierStartChars(str);
            StringBuilder sb = new StringBuilder();
            for (char ch : str2.toCharArray()) {
                if (Character.isJavaIdentifierPart(ch) && !Character.isWhitespace(ch)) {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }
}

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

package net.sf.json.sample;

import java.util.Set;
import java.util.HashSet;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class EnumBean
{
   private JsonEnum jsonEnum;
   private String string;
   private Set<JsonEnum> enums = new HashSet<JsonEnum>();

   public JsonEnum getJsonEnum()
   {
      return jsonEnum;
   }

   public String getString()
   {
      return string;
   }

   public Set<JsonEnum> getEnums()
   {
      return enums;
   }

   public void setJsonEnum( JsonEnum jsonEnum )
   {
      this.jsonEnum = jsonEnum;
   }

   public void setString( String string )
   {
      this.string = string;
   }

   public void setEnums( Set<JsonEnum> enums )
   {
      this.enums = enums;
   }
}
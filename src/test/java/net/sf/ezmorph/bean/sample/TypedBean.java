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

package net.sf.ezmorph.bean.sample;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class TypedBean
{
   private int[] parray;
   private TypedBean pbean;
   private Boolean pboolean;
   private Byte pbyte;
   private Character pchar;
   private Class pclass;
   private Double pdouble;
   private Float pfloat;
   private Integer pint;
   private List plist;
   private Long plong;
   private Map pmap;
   private Short pshort;
   private String pstring;

   public int[] getParray()
   {
      return parray;
   }

   public TypedBean getPbean()
   {
      return pbean;
   }

   public Boolean getPboolean()
   {
      return pboolean;
   }

   public Byte getPbyte()
   {
      return pbyte;
   }

   public Character getPchar()
   {
      return pchar;
   }

   public Class getPclass()
   {
      return pclass;
   }

   public Double getPdouble()
   {
      return pdouble;
   }

   public Float getPfloat()
   {
      return pfloat;
   }

   public Integer getPint()
   {
      return pint;
   }

   public List getPlist()
   {
      return plist;
   }

   public Long getPlong()
   {
      return plong;
   }

   public Map getPmap()
   {
      return pmap;
   }

   public Short getPshort()
   {
      return pshort;
   }

   public String getPstring()
   {
      return pstring;
   }

   public void setParray( int[] parray )
   {
      this.parray = parray;
   }

   public void setPbean( TypedBean pbean )
   {
      this.pbean = pbean;
   }

   public void setPboolean( Boolean pboolean )
   {
      this.pboolean = pboolean;
   }

   public void setPbyte( Byte pbyte )
   {
      this.pbyte = pbyte;
   }

   public void setPchar( Character pchar )
   {
      this.pchar = pchar;
   }

   public void setPclass( Class pclass )
   {
      this.pclass = pclass;
   }

   public void setPdouble( Double pdouble )
   {
      this.pdouble = pdouble;
   }

   public void setPfloat( Float pfloat )
   {
      this.pfloat = pfloat;
   }

   public void setPint( Integer pint )
   {
      this.pint = pint;
   }

   public void setPlist( List plist )
   {
      this.plist = plist;
   }

   public void setPlong( Long plong )
   {
      this.plong = plong;
   }

   public void setPmap( Map pmap )
   {
      this.pmap = pmap;
   }

   public void setPshort( Short pshort )
   {
      this.pshort = pshort;
   }

   public void setPstring( String pstring )
   {
      this.pstring = pstring;
   }

   public String toString()
   {
      return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
   }
}
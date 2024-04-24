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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Andres Almiray <a href="mailto:aalmiray@users.sourceforge.net">aalmiray@users.sourceforge.net</a>
 */
public class ObjectBean {
    private Object parray;
    private Object pbean;
    private Object pboolean;
    private Object pbyte;
    private Object pchar;
    private Object pclass;
    private Object pdouble;
    private Object pfloat;
    private Object pint;
    private Object plist;
    private Object plong;
    private Object pmap;
    private Object pshort;
    private Object pstring;

    public Object getParray() {
        return parray;
    }

    public Object getPbean() {
        return pbean;
    }

    public Object getPboolean() {
        return pboolean;
    }

    public Object getPbyte() {
        return pbyte;
    }

    public Object getPchar() {
        return pchar;
    }

    public Object getPclass() {
        return pclass;
    }

    public Object getPdouble() {
        return pdouble;
    }

    public Object getPfloat() {
        return pfloat;
    }

    public Object getPint() {
        return pint;
    }

    public Object getPlist() {
        return plist;
    }

    public Object getPlong() {
        return plong;
    }

    public Object getPmap() {
        return pmap;
    }

    public Object getPshort() {
        return pshort;
    }

    public Object getPstring() {
        return pstring;
    }

    public void setParray(Object parray) {
        this.parray = parray;
    }

    public void setPbean(Object bean) {
        this.pbean = bean;
    }

    public void setPboolean(Object pboolean) {
        this.pboolean = pboolean;
    }

    public void setPbyte(Object pbyte) {
        this.pbyte = pbyte;
    }

    public void setPchar(Object pchar) {
        this.pchar = pchar;
    }

    public void setPclass(Object pclass) {
        this.pclass = pclass;
    }

    public void setPdouble(Object pdouble) {
        this.pdouble = pdouble;
    }

    public void setPfloat(Object pfloat) {
        this.pfloat = pfloat;
    }

    public void setPint(Object pint) {
        this.pint = pint;
    }

    public void setPlist(Object plist) {
        this.plist = plist;
    }

    public void setPlong(Object plong) {
        this.plong = plong;
    }

    public void setPmap(Object pmap) {
        this.pmap = pmap;
    }

    public void setPshort(Object pshort) {
        this.pshort = pshort;
    }

    public void setPstring(Object pstring) {
        this.pstring = pstring;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

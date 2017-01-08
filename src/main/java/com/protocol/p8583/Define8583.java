package com.protocol.p8583;

import com.protocol.p8583.field.Field;

import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 11/16/13
 * Time: 11:11 AM
 */
public interface Define8583 {
    public void defineFieldBody(LinkedHashMap<Integer, Field> fieldBody);

    public void defineFieldHeader(LinkedHashMap<Integer, Field> fieldHeader);

    public Integer getFieldTotal();
}

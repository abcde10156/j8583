package com.protocol.demo.abc;

import com.protocol.p8583.Define8583;
import com.protocol.p8583.field.*;

import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 11/16/13
 * Time: 11:06 AM
 */
public class Define8583ABC implements Define8583 {

    public static final int TPDU = -2;

    public static final int MESSAGE_TYPE = -1;

    @Override
    public void defineFieldBody(LinkedHashMap<Integer, Field> fieldBody) {
        fieldBody.put(2, new FieldBcd(19));
        fieldBody.put(3, new FieldBcd(6));
        fieldBody.put(4, new FieldBcd(12));
        fieldBody.put(8, new FieldAsc(11));
        fieldBody.put(11, new FieldBcd(6));
        fieldBody.put(12, new FieldAsc(6));
        fieldBody.put(14, new FieldBcd(4));
        fieldBody.put(22, new FieldBcd(3));
        fieldBody.put(24, new FieldBcd(3));
        fieldBody.put(25, new FieldBcd(1));
        fieldBody.put(32, new FieldVarBcd2(4));
        fieldBody.put(39, new FieldAsc(2));
        fieldBody.put(41, new FieldAsc(8));
        fieldBody.put(42, new FieldAsc(15));
        fieldBody.put(44, new FieldVar2(99));
        fieldBody.put(48, new FieldVar3(999));
        fieldBody.put(60, new FieldVar3(99));
        Field f611 = new FieldAsc(2);
        Field f612 = new FieldVar2(10);
        Field f613 = new FieldVar2(10);
        Field f614 = new FieldAsc(4);
        FieldComp fieldComp = new FieldComp(4, f611, f612, f613, f614);

        fieldBody.put(61, fieldComp);
        fieldBody.put(62, new FieldVar2(99));
        fieldBody.put(63, new FieldVar2(99));
        fieldBody.put(64, new FieldMac(2));
    }

    @Override
    public void defineFieldHeader(LinkedHashMap<Integer, Field> fieldHeader) {
        fieldHeader.put(-2, new FieldBcd(10));    //TPDU
        fieldHeader.put(-1, new FieldBcd(4));     //信息类型
        fieldHeader.put(1, new FieldBitMap(getFieldTotal()));    //位图
    }

    @Override
    public Integer getFieldTotal() {
        return 64;
    }
}

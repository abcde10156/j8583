package com.protocol.p8583.field;


import com.protocol.utils.StringUtils;

import java.nio.ByteBuffer;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:41
 */
public class FieldAsc extends AbstractFieldCommon {

    public FieldAsc() {
    }

    public FieldAsc(int fieldLen) {
        this(fieldLen, false);
    }

    public FieldAsc(int fieldLen, boolean joinBitMap) {
        super(fieldLen, joinBitMap);
    }

    @Override
    public byte[] encodeToBytes() {
        reqEncodeValue = StringUtils.rightPadWithBytes(reqSrcValue, fieldLen, ' ', getEncoding());
        reqEncodeByte = str2byte(reqEncodeValue);
        return reqEncodeByte;
    }


    @Override
    public String getFieldType() {
        return ASC;
    }

    @Override
    public void decode(ByteBuffer byteBuffer) {
        ByteBuffer buffer = ByteBuffer.allocate(fieldLen);
        for (int i = 0; i < fieldLen; i++) {
            buffer.put(byteBuffer.get());
        }
        byte[] resp = buffer.array();

        respSrcBytes = resp;
        respSrcValue = byte2Str(respSrcBytes);
        respDecodeBytes = resp;
        respDecodeValue = byte2Str(resp);
    }
}

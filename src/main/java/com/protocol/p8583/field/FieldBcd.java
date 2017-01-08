package com.protocol.p8583.field;

import com.protocol.utils.BcdUtils;
import com.protocol.utils.StringUtils;

import java.nio.ByteBuffer;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:41
 */
public class FieldBcd extends AbstractFieldCommon {

    public FieldBcd() {
    }

    public FieldBcd(int fieldLen) {
        this(fieldLen, false);
    }

    public FieldBcd(int fieldLen, boolean joinBitMap) {
        super(fieldLen, joinBitMap);
    }

    @Override
    public byte[] encodeToBytes() {
        String str = StringUtils.leftPadWithBytes(reqSrcValue, fieldLen, '0', getEncoding());

        reqEncodeByte = BcdUtils.str2Bcd(str);
        reqEncodeValue = byte2Str(reqEncodeByte);
        return reqEncodeByte;
    }


    @Override
    public String getFieldType() {
        return BCD;
    }


    @Override
    public int getEncodeLen() {
        return getFieldLen() / 100 + 1;
    }

    @Override
    public void decode(ByteBuffer byteBuffer) {
        int arrayLen = fieldLen / 2 + fieldLen % 2;
        ByteBuffer buffer = ByteBuffer.allocate(arrayLen);
        for (int i = 0; i < arrayLen; i++) {
            buffer.put(byteBuffer.get());
        }

        byte[] srcBytes = buffer.array();
        respSrcBytes = srcBytes;
        respSrcValue = byte2Str(srcBytes);

        String result = BcdUtils.bcd2Str(srcBytes);
        respDecodeBytes = str2byte(result);
        respDecodeValue = result;
    }
}

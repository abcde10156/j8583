package com.protocol.p8583.field;

import com.protocol.utils.BcdUtils;
import com.protocol.utils.ByteUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:41
 */
public class FieldVar2 extends AbstractFieldCommon {

    public FieldVar2() {
    }

    public FieldVar2(int fieldLen) {
        super(fieldLen, true);
    }

    public FieldVar2(int fieldLen, boolean joinBitMap) {
        super(fieldLen, joinBitMap);
    }

    @Override
    public byte[] encodeToBytes() {
        List<Byte> bytes = new ArrayList<Byte>();
        byte[] len_by = BcdUtils.str2Bcd(getWriteLenStrValue());
        bytes.addAll(ByteUtil.byteArray2List(len_by));
        bytes.addAll(ByteUtil.byteArray2List(str2byte(reqSrcValue)));

        reqEncodeByte = ByteUtil.list2ByteArray(bytes);
        reqEncodeValue = byte2Str(reqEncodeByte);
        return reqEncodeByte;
    }

    public String getWriteLenStrValue() {
        return reqSrcBytes.length + "";
    }

    @Override
    public String getFieldType() {
        return VAR;
    }

    @Override
    public void decode(ByteBuffer byteBuffer) {
        String len_str = BcdUtils.bcd2Str(readLenBytes(byteBuffer));
        Integer len_data = Integer.valueOf(len_str);
        ByteBuffer dataBuffer = ByteBuffer.allocate(len_data);
        for (int i = 0; i < len_data; i++) {
            dataBuffer.put(byteBuffer.get());
        }
        byte[] response = dataBuffer.array();
        respSrcBytes = response;
        respSrcValue = byte2Str(respSrcBytes);
        respDecodeBytes = response;
        respDecodeValue = byte2Str(respDecodeBytes);
    }

    public byte[] readLenBytes(ByteBuffer byteBuffer) {
        byte len = byteBuffer.get();
        return new byte[]{len};
    }
}

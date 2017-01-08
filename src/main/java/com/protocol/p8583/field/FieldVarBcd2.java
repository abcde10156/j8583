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
public class FieldVarBcd2 extends AbstractFieldCommon {

    public FieldVarBcd2() {
    }

    public FieldVarBcd2(int fieldLen) {
        this(fieldLen, true);
    }

    public FieldVarBcd2(int fieldLen, boolean joinBitMap) {
        super(fieldLen, joinBitMap);
    }

    @Override
    public byte[] encodeToBytes() {
        List<Byte> bytes = new ArrayList<Byte>();
        byte[] len_by = BcdUtils.str2Bcd(getWriteLenStrValue());
        bytes.addAll(ByteUtil.byteArray2List(len_by));
        byte[] data_bcd = BcdUtils.str2Bcd(getReqSrcValue());
        bytes.addAll(ByteUtil.byteArray2List(data_bcd));
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
        int j = len_data / 2 + len_data % 2;
        ByteBuffer dataBuffer = ByteBuffer.allocate(j);
        for (int i = 0; i < j; i++) {
            dataBuffer.put(byteBuffer.get());
        }
        byte[] response = dataBuffer.array();
        respSrcBytes = response;
        respSrcValue = byte2Str(response);

        String value = BcdUtils.bcd2Str(dataBuffer.array());
        respDecodeBytes = str2byte(value);
        respDecodeValue = value;
    }

    public byte[] readLenBytes(ByteBuffer byteBuffer) {
        byte len = byteBuffer.get();
        return new byte[]{len};
    }
}

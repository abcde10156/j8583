package com.protocol.p8583.field;

import com.protocol.utils.BcdUtils;
import com.protocol.utils.ByteUtil;
import com.protocol.utils.Log;
import com.protocol.utils.MacUtils;
import org.slf4j.Logger;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:41
 */
public class FieldMac extends AbstractFieldAutoFill {

    private static Logger logger = Log.makeLogger(FieldMac.class);

    private List<Integer> joinMacFields = new ArrayList<Integer>();

    public FieldMac(Integer... joinMacInfo) {
        super(8, true);
        for (Integer integer : joinMacInfo) {
            joinMacFields.add(integer);
        }
    }

    public void clearBytes() {
        reqSrcBytes = new byte[]{};
        reqEncodeByte = new byte[]{};
        reqSrcValue = "";
    }

    @Override
    public void addRequestBytes() {
        if (!protocol8583.isNeedMac()) {
            clearBytes();
            return;
        }
        List<Byte> byteList = new ArrayList<Byte>();
        Map<Integer, Field> data = protocol8583.getRequestData();
        for (Integer fieldId : joinMacFields) {
            Field field = data.get(fieldId);
            byte[] macData = field.getReqSrcBytes();
            byteList.addAll(ByteUtil.byteArray2List(macData));
        }
        byte[] macSrcData = ByteUtil.list2ByteArray(byteList);
        String macData = MacUtils.doMacANSI_x919_asc(protocol8583.getZmk(), protocol8583.getZak(), macSrcData);
        byte[] macBytes = BcdUtils.hex2byte(macData.getBytes());
        reqEncodeByte = macBytes;
        reqSrcBytes = macBytes;
        protocol8583.addRequestData(macBytes);
        logger.info(requestInfo());
    }

    @Override
    public String getFieldType() {
        return VAR;
    }

    @Override
    public boolean isAutoFill() {
        return true;
    }

    @Override
    public void decode(ByteBuffer byteBuffer) {
        ByteBuffer buffer = ByteBuffer.allocate(fieldLen);
        for (int i = 0; i < fieldLen; i++) {
            buffer.put(byteBuffer.get());
        }

        byte[] response = buffer.array();
        respSrcBytes = response;
        respSrcValue = byte2Str(response);
        respDecodeBytes = buffer.array();
        respDecodeValue = byte2Str(response);
    }
}

package com.protocol.p8583.field;

import com.protocol.p8583.Protocol8583;
import com.protocol.utils.ByteUtil;
import com.protocol.utils.Log;
import org.slf4j.Logger;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:41
 */
public class FieldBitMap extends AbstractFieldAutoFill {

    private StringBuilder reqBitStr;

    private StringBuilder respBitStr;

    private static Logger logger = Log.makeLogger(Protocol8583.class);

    private int fieldTotal;

    private int fieldLength;

    public FieldBitMap(int fieldTotal) {
        super(fieldTotal / 8, false);
        this.fieldTotal = fieldTotal;
        this.fieldLength = fieldTotal / 8;
    }


    @Override
    public void addRequestBytes() {
        byte[] result = new byte[fieldLength];
        StringBuilder blockBuilder = new StringBuilder(fieldLength);
        reqBitStr = new StringBuilder();
        Map<Integer, Field> dataMap = protocol8583.getRequestData();
        for (int i = 1; i <= fieldTotal; i++) {
            Field field = dataMap.get(i);
            if (field == null) {
                blockBuilder.append(STR_ZERO);
                reqBitStr.append(STR_ZERO);
            } else if (field.getReqSrcBytes() == null) {
                blockBuilder.append(STR_ZERO);
                reqBitStr.append(STR_ZERO);
            } else {
                blockBuilder.append(STR_ONE);
                reqBitStr.append(STR_ONE);
            }
        }
        if (protocol8583.isNeedMac()) {
            reqBitStr.replace(fieldTotal - 1, fieldTotal, STR_ONE);
        }
        int j = reqBitStr.length() / 8;
        for (int i = 0; i < j; i++) {
            String subStr = reqBitStr.substring(i * 8, i * 8 + 8);
            byte bit = ByteUtil.makeIntFromBinary(subStr);
            result[i] = bit;
        }
        reqEncodeByte = result;
        protocol8583.addRequestData(result);
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
        respBitStr = new StringBuilder();
        for (int i = 0; i < fieldLength; i++) {
            String str = ByteUtil.makeBinaryStr(byteBuffer.get());
            respBitStr.append(str);
            for (int j = 0; j < 8; j++) {
                String ss = str.substring(j, j + 1);
                if (STR_ONE.equals(ss)) {
                    continue;
                }
                int datafieldIndex = 8 * i + j + 1;
                protocol8583.removeResponseField(datafieldIndex);
            }
        }
        protocol8583.removeRedundancyHeaderField();
        logger.info(responseInfo());
    }

    public String requestInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(protocol8583.desc() + "订单号:" + protocol8583.getParamDTO().getBankOrderNo() + ":" + STR_LEFT_SPLIT1);
        builder.append("request fieldId :" + getFieldId());
        builder.append(",bitStr:" + reqBitStr);
        builder.append(",fieldType:" + getFieldType());
        builder.append(",reqSrcValue:" + getReqSrcValue());
        builder.append(",reqEncodeValue:" + reqEncodeValue);
        builder.append(",encoding:" + getEncoding());
        builder.append(",reqSrcBytes:" + ByteUtil.showByteStr(getReqSrcBytes()));
        builder.append(",reqEncodeByte:" + ByteUtil.showByteStr(reqEncodeByte));

        builder.append(STR_RIGHT_SPLIT1);
        return builder.toString();
    }

    @Override
    public String responseInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(protocol8583.desc() + "订单号:" + protocol8583.getParamDTO().getBankOrderNo() + ":" + STR_LEFT_SPLIT1);
        builder.append("response fieldId :" + getFieldId());
        builder.append(",bitStr:" + respBitStr.toString());
        builder.append(",fieldType:" + getFieldType());
        builder.append(",respSrcValue:" + respSrcValue);
        builder.append(",respDecodeValue:" + respDecodeValue);
        builder.append(",encoding:" + getEncoding());
        builder.append(",respSrcBytes:" + ByteUtil.showByteStr(respSrcBytes));
        builder.append(",respDecodeBytes:" + ByteUtil.showByteStr(respDecodeBytes));
        builder.append(STR_RIGHT_SPLIT1);
        return builder.toString();
    }
}

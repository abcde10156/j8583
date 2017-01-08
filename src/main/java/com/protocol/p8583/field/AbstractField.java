package com.protocol.p8583.field;

import com.protocol.p8583.Protocol8583;
import com.protocol.utils.ByteUtil;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:39
 */
public abstract class AbstractField implements Field {

    protected byte[] reqSrcBytes;

    protected String reqSrcValue;

    protected byte[] reqEncodeByte;

    protected String reqEncodeValue;

    protected boolean joinBitMap = true;

    protected Protocol8583 protocol8583;

    protected int fieldLen = 0;

    protected byte[] respSrcBytes;

    protected String respSrcValue;

    protected byte[] respDecodeBytes;

    protected String respDecodeValue;

    protected Field parentField;

    public int getFieldLen() {
        return fieldLen;
    }

    public void setFieldLen(int fieldLen) {
        this.fieldLen = fieldLen;
    }

    protected AbstractField() {
    }

    public AbstractField(int fieldLen) {
        this(fieldLen, true);
    }

    public AbstractField(int fieldLen, boolean joinBitMap) {
        this.fieldLen = fieldLen;
        this.joinBitMap = joinBitMap;
    }

    protected Integer fieldId;

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }


    public String getEncoding() {
        return protocol8583.getEncoding();
    }


    @Override
    public byte[] getReqSrcBytes() {
        return reqSrcBytes;
    }

    @Override
    public void setReqSrcValue(String reqSrcValue) {
        this.reqSrcValue = reqSrcValue;
        this.reqSrcBytes = str2byte(reqSrcValue);
    }


    public byte[] str2byte(String str) {
        return ByteUtil.getEncodingByte(str, getEncoding());
    }

    public String byte2Str(byte[] bytes) {
        return ByteUtil.getEncodingString(bytes, getEncoding());
    }

    @Override
    public void setProtocol(Protocol8583 i8583Request) {
        this.protocol8583 = i8583Request;
    }

    public String getReqSrcValue() {
        return reqSrcValue;
    }


    public String requestInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(protocol8583.desc() + "订单号:" + protocol8583.getParamDTO().getBankOrderNo() + ":" + STR_LEFT_SPLIT1);
        builder.append("request:");
        if (parentField != null) {
            builder.append("parent fieldId :" + parentField.getFieldId());
        }
        builder.append(",fieldId :" + getFieldId());
        builder.append(",fieldType:" + getFieldType());
        builder.append(",reqSrcValue:" + getReqSrcValue());
        builder.append(",reqEncodeValue:" + reqEncodeValue);
        builder.append(",encoding:" + getEncoding());
        builder.append(",reqSrcBytes:" + ByteUtil.showByteStr(getReqSrcBytes()));
        builder.append(",reqEncodeByte:" + ByteUtil.showByteStr(reqEncodeByte));
        builder.append(STR_RIGHT_SPLIT1);
        return builder.toString();
    }

    public String responseInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(protocol8583.desc() + "订单号:" + protocol8583.getParamDTO().getBankOrderNo() + ":" + STR_LEFT_SPLIT1);

        builder.append("response:");
        if (parentField != null) {
            builder.append("parent fieldId :" + parentField.getFieldId());
        }
        builder.append(",fieldId :" + getFieldId());
        builder.append(",fieldType:" + getFieldType());
        builder.append(",respSrcValue:" + respSrcValue);
        builder.append(",respDecodeValue:" + respDecodeValue);
        builder.append(",encoding:" + getEncoding());
        builder.append(",respSrcBytes:" + ByteUtil.showByteStr(respSrcBytes));
        builder.append(",respDecodeBytes:" + ByteUtil.showByteStr(respDecodeBytes));
        builder.append(STR_RIGHT_SPLIT1);
        return builder.toString();

    }

    @Override
    public boolean isAutoFill() {
        return false;
    }

    public boolean joinBitMap() {
        return joinBitMap;
    }

    @Override
    public void verify() {
    }

    public int getEncodeLen() {
        return fieldLen;
    }

    @Override
    public String getRespDecodeValue() {
        return respDecodeValue;
    }

    @Override
    public void setParentField(Field parentField) {
        this.parentField = parentField;
    }
}

package com.protocol.p8583.field;

import com.protocol.p8583.Protocol8583;

import java.nio.ByteBuffer;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:39
 */
public interface Field {

    String BCD = "BCD";

    String ASC = "ASC";

    String VAR = "VAR";

    String COMP = "COMP";

    public static final String STR_ZERO = "0";

    public static final String STR_ONE = "1";

    public static final String STR_LEFT_SPLIT1 = "[";

    public static final String STR_RIGHT_SPLIT1 = "]";


    public void addRequestBytes();

    public void setReqSrcValue(String value);

    public String getFieldType();

    public byte[] getReqSrcBytes();

    public void setProtocol(Protocol8583 i8583Request);

    public void setFieldId(Integer id);

    public Integer getFieldId();

    /**
     * 是否自动填写字段的指
     *
     * @return
     */
    public boolean isAutoFill();

    /**
     * 校验数据格式
     */
    public void verify();

    public int getEncodeLen();

    public void decode(ByteBuffer byteBuffer);

//    public void setRespSrcBytes(byte[] response);

//    public void setRespDecodeBytes(byte[] response);

    public String responseInfo();

    public String requestInfo();

    public String getRespDecodeValue();

    public void setParentField(Field parentField);
}

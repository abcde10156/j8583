package com.protocol.p8583.field;

import com.protocol.utils.ByteUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:41
 */
public class FieldComp extends AbstractField {

    LinkedHashMap<Integer, Field> fields = new LinkedHashMap<Integer, Field>();

    List<Byte> bytes = new ArrayList<Byte>();

    private int subLen = 0;

    public FieldComp() {
        this(0, new Field[]{});
    }


    public FieldComp(int subLen, Field... fields) {
        int i = 0;
        for (Field field : fields) {
            i++;
            this.fields.put(i, field);
        }
        this.subLen = subLen;
    }

    public void addSubField(int subIndex, String srcValue) {
        if (subIndex < 0 || subIndex > subLen) {
            throw new RuntimeException("illegal param,subIndex's value must > 0 and < " + subLen);
        }
        Field subField = fields.get(subIndex);
        if (subField == null) {
            throw new RuntimeException("subField not exists,subIndex:" + subIndex);
        }
        subField.setParentField(this);
        subField.setFieldId(subIndex);
        subField.setProtocol(protocol8583);
        subField.setReqSrcValue(srcValue);

        bytes.addAll(ByteUtil.byteArray2List(subField.getReqSrcBytes()));
        reqSrcBytes = ByteUtil.list2ByteArray(bytes);
    }

    @Override
    public void addRequestBytes() {
        List<Byte> bytes = new ArrayList<Byte>();
        for (Map.Entry<Integer, Field> entry : fields.entrySet()) {
            Field field = entry.getValue();
            if (field == null) {
                continue;
            }
            entry.getValue().addRequestBytes();
        }
    }

    @Override
    public void decode(ByteBuffer byteBuffer) {
        int i = 1;
        for (Map.Entry<Integer, Field> entry : fields.entrySet()) {
            Field field = entry.getValue();
            if (field == null) {
                continue;
            }
            field.setParentField(this);
            field.setFieldId(i);
            field.setProtocol(this.protocol8583);
            field.decode(byteBuffer);
            i++;
        }
    }

    @Override
    public String getFieldType() {
        return COMP;
    }


    public Field getSubField(Integer index) {
        return fields.get(index);
    }


    public String requestInfo() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Field> entry : fields.entrySet()) {
            String subInfo = entry.getValue().requestInfo();
            builder.append("\n").append("parent Id : " + getFieldId() + "  ").append(subInfo);
        }
        return builder.toString();
    }

    public String responseInfo() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Field> entry : fields.entrySet()) {
            String subInfo = entry.getValue().responseInfo();
            builder.append("\n").append("parent Id : " + getFieldId() + "  ").append(subInfo);
        }
        return builder.toString();

    }
}

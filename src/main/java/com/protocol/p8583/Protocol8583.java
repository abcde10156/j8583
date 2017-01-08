package com.protocol.p8583;


import com.protocol.Protocol;
import com.protocol.dto.Request;
import com.protocol.dto.Response;
import com.protocol.p8583.field.Field;
import com.protocol.p8583.field.FieldComp;
import com.protocol.utils.ByteUtil;
import com.protocol.utils.Log;
import org.slf4j.Logger;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:42
 */
public abstract class Protocol8583<REQ extends Request, RESP extends Response> extends Protocol<REQ, RESP> {


    private static Logger logger = Log.makeLogger(Protocol8583.class);

    protected Map<Integer, Field> requestData = new LinkedHashMap<Integer, Field>();

    protected Map<Integer, Field> responseHeader = new LinkedHashMap<Integer, Field>();

    protected Map<Integer, Field> responseData = new LinkedHashMap<Integer, Field>();

    protected LinkedHashMap<Integer, Field> defineFieldHeader;

    protected LinkedHashMap<Integer, Field> defineFieldBody;

    protected Define8583 define8583;

    protected List<Byte> dataByte = new LinkedList<Byte>();

    protected String zmk;

    protected String zak;


    protected Protocol8583(REQ paramDTO, RESP resultDTO, Define8583 define8583) {
        super(paramDTO, resultDTO);
        this.defineFieldHeader = new LinkedHashMap<Integer, Field>();
        this.defineFieldBody = new LinkedHashMap<Integer, Field>();
        define8583.defineFieldHeader(defineFieldHeader);
        define8583.defineFieldBody(defineFieldBody);
        this.define8583 = define8583;
        initRequestData();
        initResponseData();
    }

    private void initRequestData() {
        requestData.putAll(defineFieldHeader);
        requestData.putAll(defineFieldBody);
        for (Map.Entry<Integer, Field> entry : requestData.entrySet()) {
            Field field = entry.getValue();
            field.setFieldId(entry.getKey());
            field.setProtocol(this);
        }
    }

    private void initResponseData() {
        responseHeader.putAll(defineFieldHeader);
        for (Map.Entry<Integer, Field> entry : responseHeader.entrySet()) {
            Field field = entry.getValue();
            field.setFieldId(entry.getKey());
            field.setProtocol(this);
        }
        responseData.putAll(defineFieldBody);
    }


    protected void addFiled(Integer index, String str) {
        checkFiledLength(index);
        Field field = requestData.get(index);
        if (field == null) {
            throw new RuntimeException("Field" + index + " undefined");
        }
        if (field.isAutoFill()) {
            throw new RuntimeException("this Field is AutoFill,not allow to add");
        }
        field.setReqSrcValue(str);
        requestData.put(index, field);
    }

    protected void addSubField(Integer parentIndex, Integer subIndex, String str) {
        checkFiledLength(parentIndex);
        Field field = defineFieldBody.get(parentIndex);
        if (field == null) {
            throw new RuntimeException("Field" + parentIndex + " undefined");
        }
        if (!(field instanceof FieldComp)) {
            throw new RuntimeException("Field is not a instants of FieldComp,class name : " + field.getClass().getName());
        }
        FieldComp fieldComp = (FieldComp) field;
        fieldComp.addSubField(subIndex, str);
        requestData.put(parentIndex, fieldComp);
    }

    private void checkFiledLength(Integer index) {
        if (index > define8583.getFieldTotal()) {
            throw new RuntimeException("add FiledId(" + index + ") error, max field len is : " + define8583.getFieldTotal());
        }
    }

    @Override
    protected byte[] makeRequestContent(REQ req) {
        putRequestParam();
        for (Map.Entry<Integer, Field> entry : requestData.entrySet()) {
            Field field = entry.getValue();
            field.addRequestBytes();
        }
        return ByteUtil.list2ByteArray(dataByte);
    }

    @Override
    protected RESP makeResponseObject(byte[] resp_body) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(resp_body);
        for (Map.Entry<Integer, Field> entry : responseHeader.entrySet()) {
            Field field = entry.getValue();
            field.decode(byteBuffer);
        }
        for (Map.Entry<Integer, Field> entry : responseData.entrySet()) {
            Integer keyIndex = entry.getKey();
            Field field = entry.getValue();
            field.setFieldId(keyIndex);
            field.decode(byteBuffer);
            String info = field.responseInfo();
            logger.info(info);
        }
        return toResult();
    }


    public String getZmk() {
        return zmk;
    }

    protected void setZmk(String zmk) {
        this.zmk = zmk;
    }

    public String getZak() {
        return zak;
    }

    protected void setZak(String zak) {
        this.zak = zak;
    }

    public Map<Integer, Field> getRequestData() {
        return requestData;
    }

    public void addRequestData(byte[] data) {
        dataByte.addAll(ByteUtil.byteArray2List(data));
    }

    public Field getFieldInfo(Integer index) {
        Field field = responseData.get(index);
        field.setFieldId(index);
        return field;
    }

    public void removeRedundancyHeaderField() {
        for (Integer integer : responseHeader.keySet()) {
            responseData.remove(integer);
        }
    }

    public void removeResponseField(Integer index) {
        responseData.remove(index);
    }

    public Define8583 getDefine8583() {
        return define8583;
    }

    protected abstract void putRequestParam();

    protected abstract RESP toResult();

    public abstract boolean isNeedMac();


}

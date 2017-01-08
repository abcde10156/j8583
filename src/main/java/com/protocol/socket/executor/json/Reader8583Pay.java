package com.protocol.socket.executor.json;

import com.protocol.demo.abc.ConfigABC;
import com.protocol.demo.abc.Define8583ABC;
import com.protocol.demo.abc.ProtocolAbcPay;
import com.protocol.dto.RequestPay;
import com.protocol.dto.ResponsePay;
import com.protocol.p8583.Define8583;
import com.protocol.p8583.field.Field;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 5/15/14
 * Time: 10:05 PM
 */
public class Reader8583Pay extends ProtocolAbcPay {
    public Reader8583Pay(RequestPay paramDTO, ResponsePay resultDTO, Define8583 define8583, ConfigABC config) {
        super(paramDTO, resultDTO, define8583, config);
    }

    @Override
    protected void addRequestData() {
        addFiled(Define8583ABC.MESSAGE_TYPE, "0300");
        addFiled(2, "600002");
        addFiled(3, "190000");
        addFiled(11, "600011");
        addFiled(22, "012");
        addFiled(25, "08");
//        addFiled(64,"F64");
//        addFiled(65,"F65");
    }


    @Override
    protected ResponsePay toResult() {
        Map<Integer, Field> map = responseData;
        resultDTO.setBankData(map.get(42).getRespDecodeValue());
        return resultDTO;
    }
}

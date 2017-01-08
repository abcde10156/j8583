package com.protocol.demo.abc;

import com.protocol.dto.RequestReg;
import com.protocol.dto.ResponseReg;
import com.protocol.p8583.Define8583;
import com.protocol.p8583.Protocol8583;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 4/22/14
 * Time: 11:11 PM
 */
public class ProtocolSign extends Protocol8583<RequestReg, ResponseReg> {

    public ProtocolSign(RequestReg paramDTO, ResponseReg resultDTO, Define8583 define8583) {
        super(paramDTO, resultDTO, define8583);
    }

    @Override
    public void putRequestParam() {
        addFiled(-2, "6000360000");
        addFiled(-1, "0300");
        addFiled(2, paramDTO.getBankCardName());
//        addSubField(62,1,paramDTO.get("user1"));
//        addSubField(62,2,paramDTO.get("user2"));
//        addSubField(62,3,paramDTO.get("user3"));
//        addSubField(62,4,paramDTO.get("user4"));
//        setZmk("12345678123456781234567812345678");
//        setZak("12345678123456781234567812345678");
    }

    @Override
    public ResponseReg toResult() {

        return resultDTO;
    }

    @Override
    public boolean isNeedMac() {
        return false;
    }


}

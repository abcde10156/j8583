package com.protocol.demo.abc;

import com.protocol.dto.RequestReg;
import com.protocol.dto.ResponseReg;
import com.protocol.p8583.Define8583;
import com.protocol.p8583.field.Field;

import java.util.Map;

/**
 * User:
 * Date: 14-4-22
 * Time: 16:01
 */
public class ProtocolAbcSign extends ProtocolABC<RequestReg, ResponseReg> {
    public ProtocolAbcSign(RequestReg paramDTO, ResponseReg resultDTO, Define8583 define8583, ConfigABC config) {
        super(paramDTO, resultDTO, define8583, config);
    }

    @Override
    protected void addRequestData() {
        addFiled(Define8583ABC.MESSAGE_TYPE, "0300");
        addFiled(2, paramDTO.getBankCardId());
        addFiled(3, "320000");
        addFiled(11, paramDTO.getFlowId());
        addFiled(22, "012");
        addFiled(25, "08");
        addFiled(41, paramDTO.getTermId());
        addFiled(42, paramDTO.getMerId());
        addFiled(44, paramDTO.getBankCardName());
        addFiled(48, paramDTO.getMobileId());
        addFiled(60, "A00201");
        addFiled(62, paramDTO.getFlowId());

        addSubField(61, 1, "11");
        addSubField(61, 2, "22");
        addSubField(61, 3, "33");
        addSubField(61, 4, "4444");

        addFiled(63, "EMTP");
    }


    @Override
    protected ResponseReg toResult() {
        Map<Integer, Field> map = responseData;
        System.out.println("map = " + map);
//        resultDTO.setBankData(map.get(44).getRespDecodeValue());
        return resultDTO;
    }

    @Override
    public String desc() {
        return "农业银行信用卡签约";
    }

    @Override
    public boolean isNeedMac() {
        return false;
    }
}

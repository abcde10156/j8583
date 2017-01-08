package com.protocol.demo.abc;

import com.protocol.dto.RequestPay;
import com.protocol.dto.ResponsePay;
import com.protocol.p8583.Define8583;
import com.protocol.p8583.field.Field;

import java.util.Map;

/**
 * User:
 * Date: 14-4-22
 * Time: 16:01
 */
public class ProtocolAbcPay extends ProtocolABC<RequestPay, ResponsePay> {
    public ProtocolAbcPay(RequestPay paramDTO, ResponsePay resultDTO, Define8583 define8583, ConfigABC config) {
        super(paramDTO, resultDTO, define8583, config);
    }

    @Override
    protected void addRequestData() {
        addFiled(Define8583ABC.MESSAGE_TYPE, "0300");
        addFiled(2, paramDTO.getCardId());
        addFiled(3, "190000");
        addFiled(11, paramDTO.getFlowId());
        addFiled(22, "012");
        addFiled(25, "08");
        addFiled(41, paramDTO.getTermId());
        addFiled(42, paramDTO.getMerId());
        addFiled(44, System.currentTimeMillis() + "");
        addFiled(48, paramDTO.getMobileId());
        addFiled(60, "A00201");
        addSubField(61, 1, "1");
        addSubField(61, 2, "2");
        addSubField(61, 3, "3");
        addSubField(61, 4, "4");
        addFiled(62, paramDTO.getFlowId());

        addFiled(63, "EMTP");
    }


    @Override
    protected ResponsePay toResult() {
        Map<Integer, Field> map = responseData;
        resultDTO.setBankData(map.get(11).getRespDecodeValue());
        return resultDTO;
    }

    @Override
    public String desc() {
        return "农行支付";
    }

    @Override
    public boolean isNeedMac() {
        return true;
    }
}

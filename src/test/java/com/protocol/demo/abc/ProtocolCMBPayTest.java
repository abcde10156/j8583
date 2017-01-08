package com.protocol.demo.abc;

import com.protocol.demo.cmb.ConfigCMB;
import com.protocol.demo.cmb.Define8583CMB;
import com.protocol.demo.cmb.ProtocolCMBPay;
import com.protocol.dto.RequestPay;
import com.protocol.dto.ResponsePay;
import com.protocol.utils.ByteUtil;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 5/15/14
 * Time: 9:14 PM
 */
public class ProtocolCMBPayTest {
    @Test
    public void testEncode() {
        ConfigCMB configABC = new ConfigCMB();
        RequestPay request = new RequestPay();
        ResponsePay response = new ResponsePay();
        Define8583CMB define8583ABC = new Define8583CMB();

        configABC.setTpdu("6000120000");
        request.setBankOrderNo(System.currentTimeMillis() + "");
        request.setZmk("12345678123456781234567812345678");
        request.setZak("12345678123456781234567812345678");
        request.setCardId("1234567890");
        request.setFlowId("123456");
        request.setTermId("654321");
        request.setMobileId("12345678901");
        request.setMerId("1234567");
        ProtocolCMBPay protocolAbcPay = new ProtocolCMBPay(request, response, define8583ABC, configABC);
        byte[] data = protocolAbcPay.makeRequest();
        ByteUtil.printByte(data);
        protocolAbcPay.makeResponse(data);
        System.out.println("response = " + response.getBankData());
    }


}

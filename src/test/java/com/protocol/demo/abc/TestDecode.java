package com.protocol.demo.abc;

import com.protocol.dto.RequestPay;
import com.protocol.dto.ResponsePay;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: luotao
 * Date: 5/16/14
 * Time: 9:25 PM
 */
public class TestDecode {

    @Test
    public void testDecode() throws Exception {
        byte[] bb = new byte[]{96, 0, 21, 0, 0, 3, 0, 96, 32, 4, -128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 96, 0, 2, 25, 0, 0, 96, 0, 17, 0, 18, 8, 0, 0, 96, 0, 17, 0, 18, 8};

        ConfigABC configABC = new ConfigABC();
        RequestPay request = new RequestPay();
        ResponsePay response = new ResponsePay();
        Define8583ABC define8583ABC = new Define8583ABC();

        ProtocolAbcPay protocolAbcPay = new ProtocolAbcPay(request, response, define8583ABC, configABC);

        protocolAbcPay.makeResponse(bb);
    }
}

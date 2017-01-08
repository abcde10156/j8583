package com.protocol.socket.executor.json;

import com.protocol.demo.abc.ConfigABC;
import com.protocol.demo.abc.Define8583ABC;
import com.protocol.dto.RequestPay;
import com.protocol.dto.ResponsePay;
import com.protocol.socket.SocketConfig;
import com.protocol.socket.executor.base.Executor8583;
import com.protocol.utils.ByteUtil;

import java.net.Socket;

/**
 * User:
 * Date: 14-5-8
 * Time: 16:14
 */
public class Executor8583Pay extends Executor8583 {

    public Executor8583Pay(Socket client, SocketConfig socketConfig) throws Exception {
        super(client, socketConfig);
    }

    @Override
    public byte[] execute(byte[] request) {
        ByteUtil.printByte(request);
        Define8583ABC define8583ABC = new Define8583ABC();
        RequestPay requestObj = new RequestPay();
        requestObj.setZmk("12345678123456781234567812345678");
        requestObj.setZak("12345678123456781234567812345678");
        ResponsePay response = new ResponsePay();
        ConfigABC configABC = new ConfigABC();
        configABC.setTpdu("6000150000");
        Reader8583Pay protocolAbcPay = new Reader8583Pay(requestObj, response, define8583ABC, configABC);
        protocolAbcPay.makeResponse(request);

        byte[] responseByte = protocolAbcPay.makeRequest();
        ByteUtil.printByte(responseByte);
        return responseByte;
    }
}

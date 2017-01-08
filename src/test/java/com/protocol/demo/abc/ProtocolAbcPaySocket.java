package com.protocol.demo.abc;

import com.protocol.communicator.CommunicateSocketLgCon;
import com.protocol.dto.RequestPay;
import com.protocol.dto.ResponsePay;
import org.junit.Test;

/**
 * socket客户端demo，模拟了向服务器发送请求的过程，运行com.protocol.socket.SocketServer8583类启动socket服务端
 * 8583 MAC ansix9.19 8583协议 java
 * 面向对象的8583协议，java实现，基于插件式的开发思想，功能强大且灵活，代码扩展性强，8583各域能自定义编码和解码规则,包含标准的银联MAC加密算法(ansi x9.19,ansi x9.9)及一个完整的socket客户端请求服务端的demo，修改mac运算规则时，只要重写com.protocol.p8583.field.FieldMac类就可以了。
 * User:
 * Date: 5/15/14
 * Time: 9:14 PM
 */
public class ProtocolAbcPaySocket {
    @Test
    public void testEncode() {
        ConfigABC configABC = new ConfigABC();
        RequestPay request = new RequestPay();
        ResponsePay response = new ResponsePay();
        Define8583ABC define8583ABC = new Define8583ABC();

        configABC.setTpdu("6000120000");
        request.setZmk("12345678123456781234567812345678");
        request.setZak("12345678123456781234567812345678");
        request.setCardId("1234567890");
        request.setFlowId("123456");
        request.setTermId("654321");
        request.setMobileId("12345678901");
        request.setMerId("1234567");
        request.setBankOrderNo(System.currentTimeMillis() + "");
        ProtocolAbcPay protocolAbcPay = new ProtocolAbcPay(request, response, define8583ABC, configABC);

        CommunicateSocketLgCon con = new CommunicateSocketLgCon(protocolAbcPay, "127.0.0.1", "8091", "20000", "20000");

        con.request();
        System.out.println("response.getBankData() = " + response.getBankData());
    }


}

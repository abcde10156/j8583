package com.protocol.communicator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * User:
 * Date: 14-3-6
 * Time: 11:08
 */
public class DefaultSocketProcess implements SocketProcessor {

    private static final Logger log = LoggerFactory.getLogger(CommunicateSocketLgCon.class);


    @Override
    public byte[] readTcpResponse(DataInputStream resp) {
        try {
            int len = resp.readUnsignedShort();
            byte[] bb = new byte[len];
            resp.readFully(bb);
            String result = new String(bb);
            log.info("response by upop : " + result);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream(bb.length);
            byteArray.write(bb);
            byteArray.flush();
            byteArray.close();
            return byteArray.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    @Override
    public byte[] writeTcpHeader(byte[] requestData) {
        byte[] allData = new byte[requestData.length + 2];
        byte[] lenBytes = new byte[2];
        lenBytes[0] = (byte) (requestData.length >>> 8);
        lenBytes[1] = (byte) (requestData.length >>> 0);

        System.arraycopy(lenBytes, 0, allData, 0, 2);
        System.arraycopy(requestData, 0, allData, 2, requestData.length);
        return allData;
    }
}

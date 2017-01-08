package com.protocol.communicator;

import java.io.DataInputStream;

/**
 * User:
 * Date: 14-1-22
 * Time: 11:16
 */
public interface SocketProcessor {
    public byte[] readTcpResponse(DataInputStream tcpResponse);

    public byte[] writeTcpHeader(byte[] requestData);
}

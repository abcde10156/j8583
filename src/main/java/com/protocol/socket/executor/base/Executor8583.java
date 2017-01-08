package com.protocol.socket.executor.base;

import com.protocol.socket.SocketConfig;
import com.protocol.socket.executor.json.Executor8583Pay;

import java.net.Socket;

/**
 * User:
 * Date: 14-5-8
 * Time: 16:45
 */
public class Executor8583 extends Executor {

    public Executor8583(Socket client, SocketConfig socketConfig) throws Exception {
        super(client, socketConfig);
    }

    @Override
    public Executor selectCommand(byte[] request) throws Exception {
        return new Executor8583Pay(client, socketConfig);
    }

    @Override
    public byte[] execute(byte[] request) {
        throw new RuntimeException("not implement");
    }
}

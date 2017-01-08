package com.protocol.socket;

import com.protocol.socket.executor.base.Executor8583;

import java.net.Socket;

/**
 * User:
 * Date: 14-5-6
 * Time: 16:06
 */
public class SocketServer8583 extends SocketServer {

    public SocketServer8583() {
    }

    public SocketServer8583(SocketConfig socketConfig) {
        super(socketConfig);
    }

    @Override
    protected Runnable makeExecutor(Socket client) throws Exception {
        return new Executor8583(client, socketConfig);
    }

    public static void main(String[] args) throws Exception {
        SocketConfig config = new SocketConfig();
        config.setInitThreadSize(4);
        config.setMaxThreadSize(4);
        config.setKeepAliveTime(0);
        config.setPort(8091);
        SocketServer8583 serverSocket = new SocketServer8583(config);
        serverSocket.startup();
    }
}

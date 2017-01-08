package com.protocol.communicator;


import com.protocol.Protocol;
import com.protocol.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * User:
 * socket长连接
 * Date: 14-1-10
 * Time: 16:48
 */
public class CommunicateSocketLgCon<RESP extends Response> extends Communicate<RESP> {


    private static final Logger log = LoggerFactory.getLogger(CommunicateSocketLgCon.class);

    private String ip;

    private int port;

    private int timeout_conn;

    private int timeout_read;

    private SocketProcessor socketProcess;

    public CommunicateSocketLgCon(Protocol protocol, String ip, String port, String timeout_conn, String timeout_read) {
        super(protocol);
        this.ip = ip;
        this.port = Integer.valueOf(port);
        this.timeout_conn = Integer.valueOf(timeout_conn);
        this.timeout_read = Integer.valueOf(timeout_read);
        socketProcess = new DefaultSocketProcess();
    }

    public CommunicateSocketLgCon(Protocol protocol, String ip, String port
            , String timeout_conn, String timeout_read, SocketProcessor socketProcess) {
        super(protocol);
        this.ip = ip;
        this.port = Integer.valueOf(port);
        this.timeout_conn = Integer.valueOf(timeout_conn);
        this.timeout_read = Integer.valueOf(timeout_read);
        this.socketProcess = socketProcess;
    }


    @Override
    protected byte[] doSendRequest(byte[] request_data) {
        Socket client = null;
        OutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            log.info("socket info,ip:" + ip);
            log.info("socket info,port:" + port);
            log.info("socket timeout_conn:" + timeout_conn);
            log.info("socket timeout_read:" + timeout_read);
            client = new Socket();

            client.connect(new InetSocketAddress(ip, Integer.valueOf(port)), Integer.valueOf(timeout_conn));
            client.setSoTimeout(Integer.valueOf(timeout_read));
            dataOutputStream = client.getOutputStream();
            InputStream inputStream = client.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            log.info("do request ");


            byte[] bb = socketProcess.writeTcpHeader(request_data);
            dataOutputStream.write(bb);
            dataOutputStream.flush();
            byte[] response_data = socketProcess.readTcpResponse(dataInputStream);


            log.info("do complete ");
            return response_data;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw new RuntimeException(e.toString());
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                throw new RuntimeException(e.toString());
            }
        }
    }
}

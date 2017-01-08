package com.protocol.socket.executor.base;

import com.protocol.socket.SocketConfig;
import com.protocol.utils.ExceptionUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * User:
 * Date: 14-5-8
 * Time: 16:45
 */
public abstract class Executor implements Runnable {

    protected DataInputStream input;
    protected DataOutputStream output;
    protected SocketConfig socketConfig;
    protected Socket client;

    public Executor(Socket client, SocketConfig socketConfig) throws Exception {
        this.client = client;
        input = new DataInputStream(client.getInputStream());
        output = new DataOutputStream(client.getOutputStream());
        this.socketConfig = socketConfig;
    }


    @Override
    public void run() {
        try {
            byte[] request = readRequest();
            Executor executor = selectCommand(request);
            byte[] response = executor.execute(request);
            writeResponse(response);
        } catch (Exception e) {
            ExceptionUtils.printException10(e);
            writeResponseError(e);
            throw new RuntimeException(e.toString());
        } finally {
            closeIOStream();
        }
    }


    protected byte[] readRequest() {
        try {
            int aaa = input.available();
            System.out.println("aaa = " + aaa);
            int len = input.readUnsignedShort();
            byte[] bb = new byte[len];
            input.readFully(bb);
            return bb;
        } catch (IOException e) {
            ExceptionUtils.printException10(e);
            return null;
        }
    }

    protected void closeIOStream() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.flush();
                output.close();
            }
            client.close();
        } catch (IOException e) {
            writeResponseError(e);
            ExceptionUtils.printException10(e);
        }
    }

    protected void writeResponse(byte[] response) {
        try {
            output.writeShort(response.length);
            output.write(response);
        } catch (IOException e) {
            ExceptionUtils.printException10(e);
        }
    }

    protected void writeResponseError(Throwable e) {
        try {
            StringBuilder builder = new StringBuilder();
            StackTraceElement[] elements = e.getStackTrace();
            for (StackTraceElement element : elements) {
                builder.append(element.toString()).append("\n");
            }
            byte[] msg = builder.toString().getBytes();
            output.writeShort(msg.length);
            output.write(msg);
        } catch (IOException ee) {
            ExceptionUtils.printException10(ee);
        }
    }

    protected abstract byte[] execute(byte[] request) throws Exception;

    protected abstract Executor selectCommand(byte[] request) throws Exception;
}

package com.protocol.socket;

import com.protocol.utils.ExceptionUtils;
import com.protocol.utils.Log;
import org.slf4j.Logger;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User:
 * Date: 14-5-6
 * Time: 16:06
 */
public abstract class SocketServer {

    private static final Logger logger = Log.makeLogger(SocketServer.class);


    protected ThreadPoolExecutor producerPool;

    protected SocketConfig socketConfig;

    public SocketServer() {
    }

    public SocketServer(SocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }

    public void setSocketConfig(SocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }


    public void startup() {
        if (socketConfig == null) {
            logger.error("socketConfig is null !!");
            throw new RuntimeException("socketConfig is null !!");
        }
        // 构造一个线程池
        producerPool = new ThreadPoolExecutor(
                socketConfig.getInitThreadSize()
                , socketConfig.getMaxThreadSize()
                , socketConfig.getKeepAliveTime()
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(socketConfig.getMaxThreadSize()),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        java.net.ServerSocket server;
        try {
            server = new java.net.ServerSocket(socketConfig.getPort());
            logger.info("socket server startup OK , port : " + socketConfig.getPort());
            while (true) {
                try {
                    Socket client = server.accept();
                    producerPool.execute(makeExecutor(client));
                } catch (Exception e) {
                    ExceptionUtils.printException10(e);
                }
            }
        } catch (Exception e) {
            ExceptionUtils.printException10(e);
        }
    }

    protected abstract Runnable makeExecutor(Socket client) throws Exception;

    public void shutDown() {
        if (producerPool != null) {
            producerPool.shutdown();
        }
    }
}

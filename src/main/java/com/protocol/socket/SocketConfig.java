package com.protocol.socket;

/**
 * User:
 * Date: 14-5-8
 * Time: 10:08
 */
public class SocketConfig {
    private int initThreadSize;

    private int maxThreadSize;

    private int keepAliveTime;

    private int port;

    private String remoteUrl;


    public int getInitThreadSize() {
        return initThreadSize;
    }

    public void setInitThreadSize(int initThreadSize) {
        this.initThreadSize = initThreadSize;
    }

    public int getMaxThreadSize() {
        return maxThreadSize;
    }

    public void setMaxThreadSize(int maxThreadSize) {
        this.maxThreadSize = maxThreadSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }
}

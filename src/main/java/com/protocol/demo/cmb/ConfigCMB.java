package com.protocol.demo.cmb;

/**
 * User:
 * Date: 14-4-22
 * Time: 16:02
 */
public class ConfigCMB {

    private String ip;

    private String port;

    private String timeout_conn;

    private String timeout_read;

    private String tpdu;

    public String getTpdu() {
        return tpdu;
    }

    public void setTpdu(String tpdu) {
        this.tpdu = tpdu;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTimeout_conn() {
        return timeout_conn;
    }

    public void setTimeout_conn(String timeout_conn) {
        this.timeout_conn = timeout_conn;
    }

    public String getTimeout_read() {
        return timeout_read;
    }

    public void setTimeout_read(String timeout_read) {
        this.timeout_read = timeout_read;
    }
}

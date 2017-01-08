package com.protocol.dto;


/**
 * User:
 * Date: 14-4-23
 * Time: 11:44
 */
public class Request {
    private String bankOrderNo;
    /**
     * 商户号
     */
    private String merId;
    /**
     * 银行订单号
     */
    private String orderId;

    /**
     * 银行特殊参数
     */
    private String bankData;
    /**
     * 备注
     */
    private String remark;


    /**
     * 终端号
     */
    private String termId;

    /**
     * 流水号
     */
    private String flowId;

    private String zak;

    private String zmk;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getBankData() {
        return bankData;
    }

    public void setBankData(String bankData) {
        this.bankData = bankData;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }


    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getZak() {
        return zak;
    }

    public void setZak(String zak) {
        this.zak = zak;
    }

    public String getZmk() {
        return zmk;
    }

    public void setZmk(String zmk) {
        this.zmk = zmk;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }
}

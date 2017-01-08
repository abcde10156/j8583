package com.protocol.dto;


import java.util.Date;

/**
 * User:
 * Date: 14-4-23
 * Time: 11:44
 */
public class Response {
    /**
     * 银行订单号
     */
    private String bankOrderId;

    /**
     * 错误代码
     */
    private String errorCode;
    /**
     * 错误描述
     */
    private String errorDesc;
    /**
     * 银行交易日期
     */
    private Date bankTradeTime;
    /**
     * 银行商户号
     */
    private String bankMerchantNo;
    /**
     * 银行特殊参数
     */
    private String bankData;


    public String getBankOrderId() {
        return bankOrderId;
    }

    public void setBankOrderId(String bankOrderId) {
        this.bankOrderId = bankOrderId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getBankMerchantNo() {
        return bankMerchantNo;
    }

    public void setBankMerchantNo(String bankMerchantNo) {
        this.bankMerchantNo = bankMerchantNo;
    }


    public Date getBankTradeTime() {
        return bankTradeTime;
    }

    public void setBankTradeTime(Date bankTradeTime) {
        this.bankTradeTime = bankTradeTime;
    }

    public String getBankData() {
        return bankData;
    }

    public void setBankData(String bankData) {
        this.bankData = bankData;
    }


}

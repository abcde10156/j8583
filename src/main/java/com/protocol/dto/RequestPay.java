package com.protocol.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**

 */
public class RequestPay extends Request implements Serializable {

    private static final long serialVersionUID = 7377508937592477135L;

    /**
     * 银行订单号
     */
    private String bankOrderNo;
    /**
     * 银行卡号
     */
    private String cardId;
    /**
     * 持卡人姓名
     */
    private String cardName;
    /**
     * 信用卡CVV
     */
    private String cvv;
    /**
     * 卡有效期
     */
    private String expiredDate;

    /**
     * 持卡人证件号码
     */
    private String idNo;

    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 手机号
     */
    private String mobileId;


    /**
     * 短信验证码
     */
    private String verifyCode;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }
}

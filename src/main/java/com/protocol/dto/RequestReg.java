package com.protocol.dto;

import java.io.Serializable;

/**

 */
public class RequestReg extends Request implements Serializable {

    private static final long serialVersionUID = -3829162032354176522L;

    /**
     * 银行卡号
     */
    private String bankCardId;
    /**
     * 持卡人姓名
     */
    private String bankCardName;
    /**
     * 手机号
     */
    private String mobileId;

    /**
     * 证件号码
     */
    private String idNo;
    /**
     * 信用卡CVV2
     */
    private String cvv;
    /**
     * 信用卡有效期
     */
    private String cardValid;


    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }


    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardValid() {
        return cardValid;
    }

    public void setCardValid(String cardValid) {
        this.cardValid = cardValid;
    }


}

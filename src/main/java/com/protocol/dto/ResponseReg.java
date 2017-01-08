package com.protocol.dto;


import java.io.Serializable;

/**

 */
public class ResponseReg extends Response implements Serializable {

    private static final long serialVersionUID = 5760383602288889542L;

    /**
     * 银行卡号
     */
    private String bankCardNo;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }


}

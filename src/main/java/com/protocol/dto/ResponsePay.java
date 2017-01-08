package com.protocol.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**

 */
public class ResponsePay extends Response implements Serializable {

    private static final long serialVersionUID = 6653752149684690632L;

    /**
     * 银行返回流水号
     */
    private String bankFlowId;

    /**
     * 消费金额
     */
    private BigDecimal amount;
    /**
     * 授权码
     */
    private String authorizeNo;

    /**
     * 授权时间
     */
    private Date authorizeDate;

    /**
     * 授权金额
     */
    private BigDecimal authorizeAmount;

    /**
     * 清算日期
     */
    private Date settDate;


    public String getAuthorizeNo() {
        return authorizeNo;
    }

    public void setAuthorizeNo(String authorizeNo) {
        this.authorizeNo = authorizeNo;
    }

    public String getBankFlowId() {
        return bankFlowId;
    }

    public void setBankFlowId(String bankFlowId) {
        this.bankFlowId = bankFlowId;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(Date authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public BigDecimal getAuthorizeAmount() {
        return authorizeAmount;
    }

    public void setAuthorizeAmount(BigDecimal authorizeAmount) {
        this.authorizeAmount = authorizeAmount;
    }

    public Date getSettDate() {
        return settDate;
    }

    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }


}

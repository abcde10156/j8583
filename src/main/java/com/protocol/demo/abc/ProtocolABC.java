package com.protocol.demo.abc;

import com.protocol.dto.Request;
import com.protocol.dto.Response;
import com.protocol.p8583.Define8583;
import com.protocol.p8583.Protocol8583;

/**
 * User:
 * Date: 14-4-22
 * Time: 14:54
 */
public abstract class ProtocolABC<REQ extends Request, RESP extends Response>
        extends Protocol8583<REQ, RESP> {

    protected REQ paramIn;

    protected RESP resultDTO;

    protected ConfigABC config;

    public ProtocolABC(REQ paramDTO, RESP resultDTO, Define8583 define8583, ConfigABC config) {
        super(paramDTO, resultDTO, define8583);
        this.paramIn = paramDTO;
        this.resultDTO = resultDTO;
        this.config = config;
    }

    @Override
    protected void putRequestParam() {
        addFiled(Define8583ABC.TPDU, config.getTpdu());
        setZmk(paramIn.getZmk());
        setZak(paramIn.getZak());
        addRequestData();

    }


    @Override
    public String getEncoding() {
        return ENC_GBK;
    }

    @Override
    public boolean isNeedMac() {
        return true;
    }

    protected abstract void addRequestData();
}

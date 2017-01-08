package com.protocol.p8583.field;

import com.protocol.utils.Log;
import org.slf4j.Logger;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:39
 */
public abstract class AbstractFieldCommon extends AbstractField implements Field {

    private static Logger logger = Log.makeLogger(AbstractFieldCommon.class);

    protected AbstractFieldCommon() {
    }

    protected AbstractFieldCommon(int fieldLen) {
        super(fieldLen);
    }

    protected AbstractFieldCommon(int fieldLen, boolean joinBitMap) {
        super(fieldLen, joinBitMap);
    }

    @Override
    public void addRequestBytes() {
        if (getReqSrcBytes() == null) {
            return;
        }
        byte[] requestBytes = encodeToBytes();
        protocol8583.addRequestData(requestBytes);
        logger.info(requestInfo());
    }

    public abstract byte[] encodeToBytes();
}

package com.protocol.p8583.field;

import com.protocol.utils.Log;
import org.slf4j.Logger;

/**
 * User:
 * Date: 13-11-15
 * Time: 13:39
 */
public abstract class AbstractFieldAutoFill extends AbstractField implements Field {

    private static Logger logger = Log.makeLogger(AbstractFieldAutoFill.class);

    protected AbstractFieldAutoFill() {
    }

    protected AbstractFieldAutoFill(int fieldLen) {
        super(fieldLen);
    }

    protected AbstractFieldAutoFill(int fieldLen, boolean joinBitMap) {
        super(fieldLen, joinBitMap);
    }
}

package com.protocol.communicator;


import com.protocol.Protocol;
import com.protocol.dto.Response;
import com.protocol.utils.ByteUtil;
import com.protocol.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User:
 * Date: 14-1-10
 * Time: 16:48
 */
public abstract class Communicate<RESP extends Response> {

    private static final Logger logger = LoggerFactory.getLogger(Communicate.class);

    protected Protocol<?, RESP> protocol;

    public Communicate(Protocol<?, RESP> protocol) {
        this.protocol = protocol;
    }

    public RESP request() {
        byte[] request_data = protocol.makeRequest();
        byte[] response_data = sendRequest(request_data);
        ByteUtil.printByte(response_data);
        RESP resp = protocol.makeResponse(response_data);
        return resp;
    }

    public byte[] sendRequest(byte[] request_data) {
        try {
            return doSendRequest(request_data);
        } catch (Exception e) {
            logger.error(protocol.desc() + "通信异常");
            ExceptionUtils.printException(e, 10);
            throw new RuntimeException(e.toString());
        }
    }

    protected abstract byte[] doSendRequest(byte[] request_data);
}

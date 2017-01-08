package com.protocol;


import com.protocol.dto.Request;
import com.protocol.dto.Response;
import com.protocol.utils.ByteUtil;
import com.protocol.utils.ExceptionUtils;
import com.protocol.utils.Log;
import org.slf4j.Logger;


/**
 * User: luotao
 * Date: 14-1-10
 * Time: 15:49
 */
public abstract class Protocol<REQ extends Request, RESP extends Response> {

    protected Logger logger = Log.makeLogger(getClass());


    public static final String ENC_UTF8 = "UTF-8";

    public static final String ENC_GBK = "GBK";

    protected REQ paramDTO;

    protected RESP resultDTO;

    protected byte[] req_body;

    protected byte[] resp_body;

    public RESP getResultDTO() {
        return resultDTO;
    }

    protected Protocol(REQ paramDTO, RESP resultDTO) {
        this.paramDTO = paramDTO;
        this.resultDTO = resultDTO;
    }

    public byte[] makeRequest() {
        try {
            logger.info(paramDTO.toString());
            this.req_body = makeRequestContent(paramDTO);
            logger.info(desc() + "发送的数据 : " + getEncodeRequest());
            return req_body;
        } catch (Exception e) {
            ExceptionUtils.printException(e, 10);
            throw new RuntimeException(e);
        }
    }

    public RESP makeResponse(byte[] resp_body) {
        try {
            this.resp_body = resp_body;
            logger.info(desc() + "响应的数据 : " + getEncodeResponse());
            RESP resp = makeResponseObject(resp_body);
            logger.info(resp.toString());
            return resp;
        } catch (Exception e) {
            ExceptionUtils.printException(e, 10);
            throw new RuntimeException(e);
        }
    }

    public void setResp_body(byte[] resp_body) {
        this.resp_body = resp_body;
    }

    public RESP makeResponse() {
        return makeResponse(this.resp_body);
    }

    protected String getEncodeRequest() {
        return ByteUtil.getEncodingString(req_body, getEncoding());
    }

    protected String getEncodeResponse() {
        return ByteUtil.getEncodingString(resp_body, getEncoding());
    }

    protected abstract byte[] makeRequestContent(REQ REQ) throws Exception;

    protected abstract RESP makeResponseObject(byte[] resp_body) throws Exception;

    public REQ getParamDTO() {
        return paramDTO;
    }

    public String desc() {
        return "";
    }

    public String getEncoding() {
        return ENC_UTF8;
    }
}

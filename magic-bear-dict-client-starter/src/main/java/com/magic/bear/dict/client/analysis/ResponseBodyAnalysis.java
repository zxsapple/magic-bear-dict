package com.magic.bear.dict.client.analysis;

import com.magic.bear.dict.client.util.ObjTransferUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
public class ResponseBodyAnalysis implements ResponseBodyAdvice {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2, Class arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {

        try {
            return ObjTransferUtil.transferDictObj(body);
        } catch (Throwable e) {
            log.error("字典转换发生异常", e);
            return body;
        }

    }

    @Override
    public boolean supports(MethodParameter arg0, Class arg1) {
        return true;
    }

}

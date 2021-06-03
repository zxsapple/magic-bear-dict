package com.magic.bear.dict.client.analysis;

import com.magic.bear.dict.client.config.InitDictTransferConfig;
import com.magic.bear.dict.client.dto.DictTransferDto;
import com.magic.bear.dict.client.dto.DynamicBean;
import com.magic.bear.dict.client.dto.NewPropertyInfoDto;
import com.magic.bear.dict.client.util.DictMapGenerator;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@ControllerAdvice
public class ResponseBodyAnalysis implements ResponseBodyAdvice {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1,
                                  MediaType arg2, Class arg3, ServerHttpRequest arg4,
                                  ServerHttpResponse arg5) {
        long s = System.currentTimeMillis();
        Object o = generatorNewBean(body);
        long e = System.currentTimeMillis();
        System.out.println(e-s);
        return o;
    }

    @Override
    public boolean supports(MethodParameter arg0, Class arg1) {
        return true;
    }
    /**
     * 父对象+额外属性 生成子对象
     */
    public <T> T generatorNewBean(T originBean) {
        DictTransferDto dictTransferDto = InitDictTransferConfig.CLASS_CACHE.get(originBean.getClass());

        if (dictTransferDto == null) {
            return originBean;
        }
        DynamicBean<T> dynamicBean = new DynamicBean<T>((Class<T>) originBean.getClass(),dictTransferDto.getPropertyMap());

        dictTransferDto.getPropertyMap().forEach((k, v) -> {
            try {
                if (!dictTransferDto.getTargetSet().contains(k)) {
                    // 添加原有属性的值
                    Object sourceValue = FieldUtils.readField(originBean, k, true);
                    dynamicBean.setValue(k, sourceValue);

                    // 添加新增属性的值
                    if (dictTransferDto.getOrigin2TargetMap().containsKey(k)) {
                        NewPropertyInfoDto newPropertyInfoDto = dictTransferDto.getOrigin2TargetMap().get(k);
                        if (sourceValue != null) {
                            Map<String, String> kv = DictMapGenerator.dictMap.get(newPropertyInfoDto.getType());
                            if (kv != null) {
                                String targetValue = kv.get(sourceValue.toString());
                                dynamicBean.setValue(newPropertyInfoDto.getTargetKey(), targetValue);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return dynamicBean.getTarget();
    }

}

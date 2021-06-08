package com.magic.bear.dict.client.analysis;

import com.magic.bear.dict.client.config.InitDictTransferConfig;
import com.magic.bear.dict.client.dto.DictTransferDto;
import com.magic.bear.dict.client.dto.DynamicBean;
import com.magic.bear.dict.client.dto.NewPropertyInfoDto;
import com.magic.bear.dict.client.util.DictMapGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ResponseBodyAnalysis implements ResponseBodyAdvice {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2, Class arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {

        try {
            return transferDictBean(body);
        } catch (Exception e) {
            log.error("字典转换发生异常", e);
            return body;
        }

    }

    @Override
    public boolean supports(MethodParameter arg0, Class arg1) {
        return true;
    }
    /**
     * 父对象+额外属性 生成子对象
     */
    public <T> T generatorNewBean(T originBean, DictTransferDto dictTransferDto) throws IllegalAccessException {
        DynamicBean<T> dynamicBean = new DynamicBean<T>((Class<T>) originBean.getClass(),dictTransferDto.getPropertyMap());

        for (String k : dictTransferDto.getPropertyMap().keySet()) {

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

        }

        return dynamicBean.getTarget();
    }
    //标准化返参 , map set 内不支持转换
    public Object transferDictBean(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Class<?> objClz = obj.getClass();
        //obj 本身为list
        if (List.class.isAssignableFrom(objClz)) {
            List<Object> list = (List<Object>) obj;
            if (!CollectionUtils.isEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    Object newBean = transferDictBean(list.get(i));
                    //设置新值
                    list.set(i,newBean);
                }
            }
            return list;
        }

        if (objClz.getName().startsWith("java") || objClz.isPrimitive()) {
            return obj;
        }

        DictTransferDto dictTransferDto = InitDictTransferConfig.getTransferConfigByClz(objClz);
        if (dictTransferDto == null) {
            //当前class 不是待转 需要迭代其属性
            List<Field> allFieldsList = FieldUtils.getAllFieldsList(objClz);
            for (Field field : allFieldsList) {

                Class<?> fieldClz = field.getType();
                if (List.class.isAssignableFrom(fieldClz)) {

                    List<Object> list = (List<Object>) FieldUtils.readField(field, obj, true);
//                    if (CollectionUtils.isEmpty(list)) continue;
//                    for (int i = 0; i < list.size(); i++) {
//                        Object newBean = transferDictBean(list.get(i));
//                        //设置新值
//                        list.set(i,newBean);
//                    }
                    transferDictBean(list);
                    //java 基础类型 &java自带类 泛型为Object除外
                } else if (Object.class.equals(fieldClz) || (!fieldClz.getName().startsWith("java") && !fieldClz.isPrimitive())) {
                    Object fieldObject = FieldUtils.readField(field, obj, true);
                    Object newFieldObject = transferDictBean(fieldObject);
                    //设置新值
                    FieldUtils.writeField(field, obj, newFieldObject);
                }


            }
        }else{
            obj = generatorNewBean(obj, dictTransferDto);
        }

        return obj;
    }
}

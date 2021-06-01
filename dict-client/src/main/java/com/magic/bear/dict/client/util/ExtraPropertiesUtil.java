package com.magic.bear.dict.client.util;

import com.alibaba.fastjson.JSON;
import com.magic.bear.dict.client.annotation.DictConvert;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxs
 * @date 2021/6/1 14:09
 * @desc
 */
@Slf4j
public class ExtraPropertiesUtil {


    //后期改为服务获取
    private static Map<String, Map<String, String>> dictMap = new HashMap<>();

    static {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("0", "禁用");
        statusMap.put("1", "开启");
        dictMap.put("status", statusMap);
        Map<String, String> sourceMap = new HashMap<>();

        sourceMap.put("tb", "淘宝");
        sourceMap.put("jd", "京东");
        sourceMap.put("pdd", "拼多多");
        dictMap.put("source", sourceMap);

    }

    /**
     * 反射获取额外属性
     */
    public static Map<String, Object> getExtraProp(Object originBean) {

        Map<String, Object> addProperties = new HashMap<>();
        Field[] declaredFields = originBean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            DictConvert annotation = field.getAnnotation(DictConvert.class);
            if (annotation == null) {
                continue;
            }
            String type = annotation.type();

            String sourceKey = field.getName();

            String targetKey = sourceKey + "Str";

            if (StringUtils.isEmpty(type)) {
                type = sourceKey;
            }

            field.setAccessible(true);

            Object sourceValue = null;
            try {
                sourceValue = field.get(originBean);
            } catch (IllegalAccessException e) {
                log.error("属性获取值失败 {}", field.getName());
            }
            String targetValue = null;
            if (sourceValue != null) {
                Map<String, String> kv = dictMap.get(type);
                if (kv != null) {
                    targetValue = kv.get(sourceValue.toString());
                }
            }
            addProperties.put(targetKey, targetValue);
        }
        return addProperties;
    }

}

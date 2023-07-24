package com.magic.bear.dict.client.util;

import com.magic.bear.dict.client.config.InitDictTransferConfig;
import com.magic.bear.dict.client.config.SingleDictMap;
import com.magic.bear.dict.client.dto.DictTransferDto;
import com.magic.bear.dict.client.dto.DynamicBean;
import com.magic.bear.dict.client.dto.NewPropertyInfoDto;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author zxs
 * @date 2023/7/24 13:18
 * @desc 对象转换工具
 */
public class ObjTransferUtil {

    /**
     * 标准化返参 ,java自带类型只支持list ,map set等不支持转换
     * 递归添加参数
     */
    public static Object transferDictObj(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Class<?> objClz = obj.getClass();
        //obj 本身为list
        if (List.class.isAssignableFrom(objClz)) {
            List<Object> list = (List<Object>) obj;
            if (!CollectionUtils.isEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    Object newObj = transferDictObj(list.get(i));
                    //设置新值
                    list.set(i, newObj);
                }
            }
            return list;
        }
        //其他java类型或基础类型 都不转换
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

                    List<?> list = (List<?>) FieldUtils.readField(field, obj, true);
                    transferDictObj(list);
                    //java 基础类型 &java自带类 泛型为Object除外
                } else if (Object.class.equals(fieldClz) || (!fieldClz.getName().startsWith("java") && !fieldClz.isPrimitive())) {
                    Object fieldObject = FieldUtils.readField(field, obj, true);
                    Object newFieldObject = transferDictObj(fieldObject);
                    //设置新值
                    FieldUtils.writeField(field, obj, newFieldObject);
                }


            }
        }else{
            obj = generatorNewBean(obj, dictTransferDto);
        }

        return obj;
    }
    /**
     * 父对象+额外属性 生成子对象
     */
    public static <T> T generatorNewBean(T originBean, DictTransferDto dictTransferDto) throws IllegalAccessException {
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
                        Map<String, String> kv = SingleDictMap.getDictMap().get(newPropertyInfoDto.getType());
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
}

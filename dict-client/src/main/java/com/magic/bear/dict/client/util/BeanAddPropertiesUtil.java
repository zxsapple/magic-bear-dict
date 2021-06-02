package com.magic.bear.dict.client.util;

import com.magic.bear.dict.client.annotation.DictConvert;
import com.magic.bear.dict.client.dto.NewPropertyInfoDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zxs
 * @date 2021/6/1 14:09
 * @desc
 */
@Slf4j
public class BeanAddPropertiesUtil {
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
     * 父对象+额外属性 生成子对象
     */
    public static <T> T generatorNewBean(T originBean) {

        //原有字段 + 新增字段
        Map<String, Class> propertyMap = new HashMap<>();
        //原有字段与新字段关系
        Map<String, NewPropertyInfoDto> origin2TargetMap = new HashMap<>();
        //待转新字段
        Set<String> targetSet = new HashSet<>();
        //原来所有field
        List<Field> allFieldsList = FieldUtils.getAllFieldsList(originBean.getClass());

        for (Field field : allFieldsList) {
            propertyMap.put(field.getName(), field.getType());
            DictConvert annotation = field.getAnnotation(DictConvert.class);
            if (annotation == null) {
                continue;
            }
            String type = annotation.type();

            String sourceKey = field.getName();

            String targetKey = annotation.targetKey();

            //使用默认值
            if (StringUtils.isEmpty(type)) {
                type = sourceKey;
            }
            if (StringUtils.isEmpty(targetKey)) {
                targetKey = sourceKey + "Str";
            }

            NewPropertyInfoDto newPropertyInfoDto = new NewPropertyInfoDto();

            newPropertyInfoDto.setType(type);
            newPropertyInfoDto.setTargetKey(targetKey);

            origin2TargetMap.put(sourceKey, newPropertyInfoDto);
            targetSet.add(targetKey);
            propertyMap.put(targetKey, String.class);
        }


        DynamicBean<T> dynamicBean = new DynamicBean<T>((Class<T>) originBean.getClass(), propertyMap);

        propertyMap.forEach((k, v) -> {
            try {
                if (!targetSet.contains(k)) {
                    // 添加原有属性的值
                    Object sourceValue = FieldUtils.readField(originBean, k, true);
                    dynamicBean.setValue(k, sourceValue);

                    // 添加新增属性的值
                    if (origin2TargetMap.containsKey(k)) {
                        NewPropertyInfoDto newPropertyInfoDto = origin2TargetMap.get(k);
                        if (sourceValue != null) {
                            Map<String, String> kv = dictMap.get(newPropertyInfoDto.getType());
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

    public static class DynamicBean<T> {

        /**
         * 目标对象
         */
        private T target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class<T> superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }

        /**
         * bean 添加属性和值
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         */
        public T getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         */
        private T generateBean(Class<T> superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator = new BeanGenerator();
            if (null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return (T) generator.create();
        }

    }
}

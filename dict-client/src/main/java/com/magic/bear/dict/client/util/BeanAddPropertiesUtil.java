package com.magic.bear.dict.client.util;

import com.magic.bear.dict.client.annotation.DictConvert;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        //原有字段
        Map<String, Class> propertyMap = new HashMap<>();
        //新增字段
        Map<String, String> addProperties = new HashMap<>();
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
            propertyMap.put(targetKey, String.class);
        }


        DynamicBean<T> dynamicBean = new DynamicBean<T>((Class<T>) originBean.getClass(), propertyMap);

        propertyMap.forEach((k, v) -> {
            try {

                if (addProperties.containsKey(k)) {
                    // 添加新增的属性的值
                    dynamicBean.setValue(k, addProperties.get(k));

                }else{
                    // 添加原有属性的值
                    dynamicBean.setValue(k, FieldUtils.readField(originBean, k, true));
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

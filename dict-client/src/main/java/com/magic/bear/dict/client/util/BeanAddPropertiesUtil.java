package com.magic.bear.dict.client.util;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
/**
 * @author zxs
 * @date 2021/6/1 14:09
 * @desc
 */
public class BeanAddPropertiesUtil {

    /**
     * 父对象+额外属性 生成子对象
     */
    public static <T> T generatorNewBean(T dest) {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        //字段
        Map<String, Class> propertyMap = new HashMap<>();
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }

        Map<String, Object> addProperties = ExtraPropertiesUtil.getExtraProp(dest);


        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));

        DynamicBean<T> dynamicBean = new DynamicBean<T>((Class<T>) dest.getClass(), propertyMap);

        // 添加原有属性的值
        propertyMap.forEach((k, v) -> {
            try {
                // 防新旧冲突
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 添加新增的属性的值
        addProperties.forEach(dynamicBean::setValue);

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

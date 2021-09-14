package com.magic.bear.dict.client.dto;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

public class DynamicBean<T> {

    /**
     * 目标对象
     */
    private T target;

    /**
     * 属性集合
     */
    private BeanMap beanMap;

    public DynamicBean(Class<T> superclass, Map<String, Class<?>> propertyMap) {
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
    private T generateBean(Class<T> superclass, Map<String, Class<?>> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        if (null != superclass) {
            generator.setSuperclass(superclass);
        }
        BeanGenerator.addProperties(generator, propertyMap);
        return (T) generator.create();
    }

}
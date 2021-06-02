package com.magic.bear.dict.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zxs
 * @date 2020/12/9 17:48
 * @desc 字典转换
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictConvert {
    //类型 如果不申明即用字段本身
    String type() default "";

    //需要转换的目标key 默认为字段本身+Str 如 status --> statusStr
    String targetKey() default "";
}

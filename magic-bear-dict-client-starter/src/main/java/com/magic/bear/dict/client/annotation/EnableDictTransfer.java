package com.magic.bear.dict.client.annotation;

import com.magic.bear.dict.client.analysis.ResponseBodyAnalysis;
import com.magic.bear.dict.client.config.InitDictTransferConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zxs
 * @date 2021/6/3 9:37
 * @desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Import({InitDictTransferConfig.class, ResponseBodyAnalysis.class})
public @interface EnableDictTransfer {

    String[] basePackages() default "";

}

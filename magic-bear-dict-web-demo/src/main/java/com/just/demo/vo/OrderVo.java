package com.just.demo.vo;

import com.magic.bear.dict.client.annotation.DictConvert;
import com.magic.bear.dict.client.annotation.OpenDict;
import lombok.Data;

/**
 * @author zxs
 * @date 2021/6/3 9:48
 * @desc
 */
@Data
@OpenDict
public class OrderVo {

    Long orderId;

    @DictConvert
    Integer status;

    @DictConvert
    String source;

    String goodType;
}

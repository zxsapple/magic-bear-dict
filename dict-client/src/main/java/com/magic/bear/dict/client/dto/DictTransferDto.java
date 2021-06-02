package com.magic.bear.dict.client.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author zxs
 * @date 2021/6/2 16:03
 * @desc
 */
@Data
public class DictTransferDto {
    //新实体所有的字段
    Map<String, Class> propertyMap;

    Map<String, NewPropertyInfoDto> origin2TargetMap;
}

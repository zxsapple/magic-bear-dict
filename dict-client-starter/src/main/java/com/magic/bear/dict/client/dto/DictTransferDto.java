package com.magic.bear.dict.client.dto;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author zxs
 * @date 2021/6/2 16:03
 * @desc
 */
@Data
public class DictTransferDto {
    //新实体所有的字段
    Map<String, Class<?>> propertyMap;

    //原有字段与新字段关系
    Map<String, NewPropertyInfoDto> origin2TargetMap;

    //待转新字段
    Set<String> targetSet;
}

package com.magic.bear.dict.client.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author zxs
 * @date 2021/6/2 16:05
 * @desc
 */
@Data
public class NewPropertyInfoDto {

    //目标字段key
    String targetKey;

    //字典类型
    String type;

    //待转新字段
    Set<String> targetSet;
}

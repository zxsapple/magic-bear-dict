package com.magic.bear.dict.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxs
 * @date 2021/6/15 14:31
 * @desc
 */
@Data
public class BaseEntity {
    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String modifier;

    /**
     * 更新时间
     */
    private Date modifyTime;
}

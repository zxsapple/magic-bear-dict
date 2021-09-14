package com.magic.bear.dict.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 用户_系统 权限表
 * </p>
 *
 * @author jobob
 * @since 2021-06-15
 */
@Data
public class SysUserApp {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 系统
     */
    private String appId;

    /**
     * 用户
     */
    private Integer userId;



}

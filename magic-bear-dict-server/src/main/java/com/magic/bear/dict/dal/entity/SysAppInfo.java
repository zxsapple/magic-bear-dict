package com.magic.bear.dict.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * 系统信息
 */
@Data
public class SysAppInfo extends BaseEntity {

    /**
     * app唯一标识
     */
    @TableId(type = IdType.AUTO)
    private String appId;

    /**
     * app描述
     */
    private String appDescription;

    /**
     * 备注
     */
    private String remark;


}

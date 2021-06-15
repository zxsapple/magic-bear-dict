package com.magic.bear.dict.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-06-15
 */
@Data
public class SysUserInfo extends BaseEntity {

    @TableId(value = "dict_type_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 状态（1-正常 0-停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


}

package com.magic.bear.dict.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author jobob
 * @since 2021-06-15
 */
@Data
public class SysDictType extends BaseEntity{

    /**
     * 字典主键
     */
    @TableId(value = "dict_type_id", type = IdType.AUTO)
    private Long dictTypeId;

    /**
     * 字典名称
     */
    private String dictTypeName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 备注
     */
    private String remark;


}

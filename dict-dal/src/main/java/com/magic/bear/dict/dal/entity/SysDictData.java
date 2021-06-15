package com.magic.bear.dict.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author jobob
 * @since 2021-06-15
 */
@Data
public class SysDictData extends BaseEntity {


    @TableId(value = "dict_data_id", type = IdType.AUTO)
    private Long dictDataId;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 系统
     */
    private String appId;

    /**
     * 是否默认（1-是 0-否）
     */
    private Integer isDefault;

    /**
     * 状态（1-正常 0-停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


}

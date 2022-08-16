package com.magic.bear.dict.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.magic.bear.dict.dal.entity.SysDictType;

/**
 * @author zxs
 * @date 2022/7/21 16:39
 * @desc
 */
public interface IDictTypeService extends IService<SysDictType> {
    Page<SysDictType> pageList(SysDictType queryWrapper, int pageNum, int pageSize);
}

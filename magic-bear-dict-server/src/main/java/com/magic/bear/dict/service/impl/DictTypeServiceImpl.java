package com.magic.bear.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.magic.bear.dict.dal.entity.SysDictType;
import com.magic.bear.dict.dal.mapper.SysDictTypeMapper;
import com.magic.bear.dict.service.IDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author zxs
 * @date 2022/7/21 16:40
 * @desc
 */
@Service
public class DictTypeServiceImpl  extends ServiceImpl<SysDictTypeMapper, SysDictType> implements IDictTypeService {
    @Override
    public Page<SysDictType> pageList(SysDictType dictType, int pageNum, int pageSize) {
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(dictType.getDictType())) {
            queryWrapper.like(SysDictType::getDictType, dictType.getDictType());
        }
        if (!StringUtils.isEmpty(dictType.getDictTypeName())) {
            queryWrapper.like(SysDictType::getDictTypeName, dictType.getDictTypeName());
        }
        if (!StringUtils.isEmpty(dictType.getAppId())) {
            queryWrapper.eq(SysDictType::getAppId, dictType.getAppId());
        }
        Page<SysDictType> producePage = new Page<>(pageNum, pageSize);
        return super.page(producePage, queryWrapper);

    }
}

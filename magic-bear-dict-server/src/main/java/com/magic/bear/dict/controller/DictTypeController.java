package com.magic.bear.dict.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.magic.bear.dict.dal.entity.SysDictType;
import com.magic.bear.dict.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxs
 * @date 2022/7/21 16:35
 * @desc
 */
@RestController
@RequestMapping("/magic/bear/dictType")
public class DictTypeController {

    @Autowired
    private IDictTypeService dictTypeService;

    @PostMapping("/list")
    public List<SysDictType> list(SysDictType dictType) {

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
        List<SysDictType> list = dictTypeService.list(queryWrapper);
        return list;
    }

}

package com.magic.bear.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.magic.bear.dict.dal.entity.SysDictType;
import com.magic.bear.dict.dal.mapper.SysDictTypeMapper;
import com.magic.bear.dict.service.IDictTypeService;
import org.springframework.stereotype.Service;

/**
 * @author zxs
 * @date 2022/7/21 16:40
 * @desc
 */
@Service
public class DictTypeServiceImpl  extends ServiceImpl<SysDictTypeMapper, SysDictType> implements IDictTypeService {
}

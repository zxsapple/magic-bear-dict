package com.magic.bear.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.magic.bear.dict.dal.entity.SysUserApp;
import com.magic.bear.dict.dal.mapper.SysUserAppMapper;
import com.magic.bear.dict.service.IUserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxs
 * @date 2021/6/15 13:44
 * @desc
 */
@Service
public class UserAppServiceImpl extends ServiceImpl<SysUserAppMapper, SysUserApp> implements IUserAppService {

    @Autowired
    private SysUserAppMapper sysUserAppMapper;

}

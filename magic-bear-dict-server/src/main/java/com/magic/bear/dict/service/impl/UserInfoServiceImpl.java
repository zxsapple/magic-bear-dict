package com.magic.bear.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.magic.bear.dict.dal.entity.SysUserInfo;
import com.magic.bear.dict.dal.mapper.SysUserInfoMapper;
import com.magic.bear.dict.service.IUserInfoService;

/**
 * @author zxs
 * @date 2022/8/16 9:03
 * @desc
 */
public class UserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements IUserInfoService {

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}

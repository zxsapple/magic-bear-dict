package com.magic.bear.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.magic.bear.dict.dal.entity.SysDictData;
import com.magic.bear.dict.dal.entity.SysUserInfo;
import com.magic.bear.dict.dal.mapper.SysUserInfoMapper;
import com.magic.bear.dict.service.IUserInfoService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxs
 * @date 2022/8/16 9:03
 * @desc
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements IUserInfoService {

    private String salt = "5164c442561a48a6a241f24ed73fbf81";
    @Autowired
    SysUserInfoMapper sysUserInfoMapper;
    @Override
    public boolean login(String username, String password) {

        String md5pwd= MD5Encoder.encode((password + salt).getBytes());

        LambdaQueryWrapper<SysUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserInfo::getUsername, username).eq(SysUserInfo::getPassword, md5pwd).eq(SysUserInfo::getStatus, 1);
        Integer count = sysUserInfoMapper.selectCount(queryWrapper);

        return count == 1;
    }
}

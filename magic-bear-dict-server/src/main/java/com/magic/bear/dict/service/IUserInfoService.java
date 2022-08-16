package com.magic.bear.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.magic.bear.dict.dal.entity.SysUserApp;
import com.magic.bear.dict.dal.entity.SysUserInfo;

/**
 * @author zxs
 * @date 2022/8/16 9:02
 * @desc
 */
public interface IUserInfoService extends IService<SysUserInfo> {
    boolean login(String username, String password);
}

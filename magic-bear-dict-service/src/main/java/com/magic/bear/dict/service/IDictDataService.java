package com.magic.bear.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.magic.bear.dict.dal.entity.SysDictData;

import java.util.Map;

/**
 * @author zxs
 * @date 2021/6/15 14:38
 * @desc
 */
public interface IDictDataService extends IService<SysDictData> {

    /**
     * 估计appId 获取字典集map
     */
    Map<String, Map<String, String>> getAppDictMap(String appId);
}

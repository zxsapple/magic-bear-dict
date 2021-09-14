package com.magic.bear.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.magic.bear.dict.dal.entity.SysDictData;
import com.magic.bear.dict.dal.mapper.SysDictDataMapper;
import com.magic.bear.dict.service.IDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zxs
 * @date 2021/6/15 14:39
 * @desc
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements IDictDataService {

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public Map<String, Map<String, String>> getAppDictMap(String appId) {
        QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<SysDictData>();
        queryWrapper.eq("app_id", appId).eq("status", 1);
        List<SysDictData> list = sysDictDataMapper.selectList(queryWrapper);
        Map<String, List<SysDictData>> collect = list.stream().collect(Collectors.groupingBy(SysDictData::getDictType));
        Map<String, Map<String, String>> distMap = new HashMap<>(collect.size());
        collect.forEach((key,dictList)->{
            Map<String, String> typeMap = new HashMap<>(dictList.size());
            for (SysDictData sysDictData : dictList) {
                typeMap.put(sysDictData.getDictValue(), sysDictData.getDictLabel());
            }
            distMap.put(key, typeMap);

        });
        return distMap;
    }
}

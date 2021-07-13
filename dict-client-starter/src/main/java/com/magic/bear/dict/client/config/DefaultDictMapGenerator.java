package com.magic.bear.dict.client.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxs
 * @date 2021/7/13 20:23
 * @desc 默认的模板实现
 */
public class DefaultDictMapGenerator extends AddDictMapTemplate{
    @Override
    public Map<String, Map<String, String>> generatorDictMap() {
        //TODO 从远程server 获取字典集
        return new HashMap<>();
    }

}

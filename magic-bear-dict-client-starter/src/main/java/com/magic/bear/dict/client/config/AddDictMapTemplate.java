package com.magic.bear.dict.client.config;

import java.util.Map;

/**
 * @author zxs
 * @date 2021/7/13 19:34
 * @desc 添加字典集的模板 实现此模板可以向 SingleDictMap 添加数据
 */
public abstract class AddDictMapTemplate {
   public abstract Map<String, Map<String, String>> generatorDictMap();

    public void addDictMap() {

        Map<String, Map<String, String>> map = generatorDictMap();
        if (map == null) {
            throw new RuntimeException(this.getClass().getName() + " 返回map为空");
        }
        SingleDictMap.getDictMap().putAll(map);
    }
}

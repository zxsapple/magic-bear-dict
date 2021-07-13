package com.magic.bear.dict.client.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxs
 * @date 2021/7/13 19:32
 * @desc 单例 字典map
 */
public class SingleDictMap {
    // 通过实现 AddDictMapTemplate 模板添数据,可以实现多个
    private static volatile Map<String, Map<String, String>> dictMap;

    private SingleDictMap() {}

    public static Map<String, Map<String, String>> getDictMap() {

        if (dictMap == null) {
            synchronized (SingleDictMap.class) {
                if (dictMap == null) {
                    dictMap = new HashMap<>();
                }
            }
        }
        return dictMap;
    }

}

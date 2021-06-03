package com.magic.bear.dict.client.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxs
 * @date 2021/6/1 14:09
 * @desc 字典map
 */
@Slf4j
public class DictMapGenerator {
    //后期改为服务获取
    public static Map<String, Map<String, String>> dictMap = new HashMap<>();

    static {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("0", "禁用");
        statusMap.put("1", "开启");
        dictMap.put("status", statusMap);
        Map<String, String> sourceMap = new HashMap<>();

        sourceMap.put("tb", "淘宝");
        sourceMap.put("jd", "京东");
        sourceMap.put("pdd", "拼多多");
        dictMap.put("source", sourceMap);

    }



}

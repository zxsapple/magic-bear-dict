package com.just.demo.config;

import com.magic.bear.dict.client.config.AddDictMapTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxs
 * @date 2021/7/13 19:46
 * @desc 实现 AddDictMapTemplate 示例
 *  可以通过配置文件/数据库/文件流/远程服务 获取map
 */
public class ExampleDictMapGenerator extends AddDictMapTemplate {
    @Override
    public Map<String, Map<String, String>> generatorDictMap() {
        Map<String, Map<String, String>> dictMap = new HashMap<>();

        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("0", "禁用");
        statusMap.put("1", "开启");
        dictMap.put("status", statusMap);
        Map<String, String> sourceMap = new HashMap<>();

        sourceMap.put("tb", "淘宝");
        sourceMap.put("jd", "京东");
        sourceMap.put("pdd", "拼多多");
        dictMap.put("source", sourceMap);
        return dictMap;
    }
}

package com.magic.bear.dict.controller;

import com.magic.bear.dict.service.IDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zxs
 * @date 2021/6/15 13:26
 * @desc 供client-starter查询字典集
 */
@RestController
@RequestMapping("/client/dict")
public class DictApiController {

    @Autowired
    private IDictDataService dictDataService;
    /**
     * 根据appId 获取字典集map
     */
    @RequestMapping("/getAppDictMap/{appId}")
    public Map<String, Map<String, String>> getAppDictMap(@PathVariable String appId) {
        return dictDataService.getAppDictMap(appId);
    }
}

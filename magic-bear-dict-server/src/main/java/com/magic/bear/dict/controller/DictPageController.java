package com.magic.bear.dict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zxs
 * @date 2022/8/15 10:22
 * @desc
 */
@Controller
public class DictPageController {
    @GetMapping("/")
    public String test() {
        return "index.html";
    }
}

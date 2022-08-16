package com.magic.bear.dict.controller;

import com.magic.bear.dict.service.IUserInfoService;
import com.magic.bear.dict.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zxs
 * @date 2022/8/16 9:00
 * @desc
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping("login")
    public ResultVO login(String username, String password) {

        userInfoService.login(username, password);

        return null;
    }
}

package com.just.demo.controller;

import com.just.demo.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zxs
 * @date 2021/3/31 9:46
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("test")
public class DemoController {


    @RequestMapping("getOrderList")
    public OrderVo getOrderList()  {
        OrderVo orderVo = new OrderVo();
        orderVo.setGoodType("手机");
        orderVo.setOrderId(456489L);
        orderVo.setSource("jd");
        orderVo.setStatus(0);
        return orderVo;
    }
}

package com.just.demo.controller;

import com.just.demo.vo.OrderVo;
import com.just.demo.vo.PageInfo;
import com.just.demo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author zxs
 * @date 2021/3/31 9:46
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("test")
public class DemoController {


    /**
     * 1000个对象,一个对象2个待转字段
     * asm class有缓存转换时间 4ms,第一次无缓存生成class大概需要增加50ms
     *
     */

    @RequestMapping("getOrderPageList")
    public Result<PageInfo<OrderVo>> getOrderPageList() {
        OrderVo orderVo1 = new OrderVo();
        orderVo1.setGoodType("手机");
        orderVo1.setOrderId(456489L);
        orderVo1.setSource("jd");
        orderVo1.setStatus(0);

        OrderVo orderVo2 = new OrderVo();
        orderVo2.setGoodType("文件");
        orderVo2.setOrderId(456490L);
        orderVo2.setSource("tb");
        orderVo2.setStatus(1);
        OrderVo orderVo3 = new OrderVo();
        orderVo3.setGoodType("文件");
        orderVo3.setOrderId(456490L);
        orderVo3.setSource("tb");
        orderVo3.setStatus(1);


        List<OrderVo> list = Arrays.asList(orderVo1, orderVo2, orderVo3);
        PageInfo<OrderVo> pageInfo = new PageInfo<>();
        pageInfo.setListInfo(list);
        pageInfo.setTotal(100);
        Result<PageInfo<OrderVo>> result = new Result<>();
        result.setCode(200);
        result.setData(pageInfo);
        result.setMessage("success");
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("getOrder")
    public Result<OrderVo> getOrder() {
        OrderVo orderVo1 = new OrderVo();
        orderVo1.setGoodType("手机");
        orderVo1.setOrderId(456489L);
        orderVo1.setSource("jd");
        orderVo1.setStatus(0);

        Result<OrderVo> result = new Result<>();
        result.setCode(200);
        result.setData(orderVo1);
        result.setMessage("success");
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("getResultOrderList")
    public Result<List<OrderVo>> getResultOrderList() {
        OrderVo orderVo1 = new OrderVo();
        orderVo1.setGoodType("手机");
        orderVo1.setOrderId(456489L);
        orderVo1.setSource("jd");
        orderVo1.setStatus(0);

        OrderVo orderVo2 = new OrderVo();
        orderVo2.setGoodType("文件");
        orderVo2.setOrderId(456490L);
        orderVo2.setSource("tb");
        orderVo2.setStatus(1);
        OrderVo orderVo3 = new OrderVo();
        orderVo3.setGoodType("文件");
        orderVo3.setOrderId(456490L);
        orderVo3.setSource("tb");
        orderVo3.setStatus(1);
        List<OrderVo> list = Arrays.asList(orderVo1, orderVo2, orderVo3);

        Result<List<OrderVo>> result = new Result<>();
        result.setCode(200);
        result.setData(list);
        result.setMessage("success");
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("getSingleOrder")
    public OrderVo getSingleOrder() {
        OrderVo orderVo1 = new OrderVo();
        orderVo1.setGoodType("手机");
        orderVo1.setOrderId(456489L);
        orderVo1.setSource("jd");
        orderVo1.setStatus(0);


        return orderVo1;
    }
    @RequestMapping("getOrderList")
    public List<OrderVo> getOrderList() {
        OrderVo orderVo1 = new OrderVo();
        orderVo1.setGoodType("手机");
        orderVo1.setOrderId(456489L);
        orderVo1.setSource("jd");
        orderVo1.setStatus(0);
        OrderVo orderVo2 = new OrderVo();
        orderVo2.setGoodType("文件");
        orderVo2.setOrderId(456490L);
        orderVo2.setSource("tb");
        orderVo2.setStatus(1);
        OrderVo orderVo3 = new OrderVo();
        orderVo3.setGoodType("文件");
        orderVo3.setOrderId(456490L);
        orderVo3.setSource("tb");
        orderVo3.setStatus(1);
        List<OrderVo> list = Arrays.asList(orderVo1, orderVo2, orderVo3);
        return list;
    }
    @RequestMapping("getMaps")
    public List<Map<String, String>> getMaps() {

        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        Map<String, String> map2 = new HashMap<>();
        map2.put("d", "1");
        map2.put("e", "2");
        map2.put("f", "3");
        return Arrays.asList(map, map2);

    }
    @RequestMapping("getOrderSet")
    public Set<OrderVo> getOrderSet() {
        OrderVo orderVo1 = new OrderVo();
        orderVo1.setGoodType("手机");
        orderVo1.setOrderId(456489L);
        orderVo1.setSource("jd");
        orderVo1.setStatus(0);
        OrderVo orderVo2 = new OrderVo();
        orderVo2.setGoodType("文件");
        orderVo2.setOrderId(456490L);
        orderVo2.setSource("tb");
        orderVo2.setStatus(1);
        OrderVo orderVo3 = new OrderVo();
        orderVo3.setGoodType("文件");
        orderVo3.setOrderId(456490L);
        orderVo3.setSource("tb");
        orderVo3.setStatus(1);
        Set<OrderVo> set = new HashSet<>();
        set.add(orderVo1);
        set.add(orderVo2);
        set.add(orderVo3);
        return set;
    }
    @RequestMapping("getJavaType")
    public Object getJavaType() {

        return 1;
    }


}

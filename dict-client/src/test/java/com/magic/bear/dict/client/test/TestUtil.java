package com.magic.bear.dict.client.test;

import com.alibaba.fastjson.JSON;
import com.magic.bear.dict.client.util.BeanAddPropertiesUtil;
import com.magic.bear.dict.client.util.ExtraPropertiesUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zxs
 * @date 2021/6/1 10:35
 * @desc
 */
public class TestUtil {


    public static void main(String[] args) throws IllegalAccessException {

        Student student = new Student("lily", 18, 1, "pdd");
        student.setSex("男");
        Student student2 = new Student("sam", 19, 0, "tb");
        Student student3 = new Student("lucy", null, null, "tb");
        student2.setSex("男");
        student3.setSex("男");
        List<Student> list = Arrays.asList(student, student2, student3);
        PageInfo<Student> pageInfo = new PageInfo<>();
        pageInfo.setListInfo(list);
        pageInfo.setTotal(100);
        Result<PageInfo<Student>> result = new Result<>();
        result.setCode(200);
        result.setData(pageInfo);
        result.setMessage("success");
        result.setSuccess(true);
        long start = System.currentTimeMillis();
        System.out.println();
        System.out.println(JSON.toJSONString(BeanAddPropertiesUtil.generatorNewBean(student3)));
        System.out.println(System.currentTimeMillis()-start);
    }





}

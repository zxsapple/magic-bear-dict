package com.magic.bear.dict.client.test;

import com.magic.bear.dict.client.annotation.DictConvert;
import com.magic.bear.dict.client.annotation.OpenDict;

@OpenDict
public class Student extends Person{
    public Student() {
    }

    public Student(String name, Integer age, Integer status, String source) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.source = source;
    }

    private String name;

    private Integer age;

    //0-禁用 1-开启
    @DictConvert
    private Integer status;

    // tb jd pdd
    @DictConvert
    private String source;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
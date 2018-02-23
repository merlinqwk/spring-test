package com.merlin.practice.model;

import java.io.Serializable;

/**
 * Created by qwk on 2018-02-07 10:18
 **/
public class Student implements Serializable{

    private static final long serialVersionUID = -669259594768650851L;

    private Integer id;
    private String name;
    private String sex;

    public Student() {
    }

    public Student(Integer id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

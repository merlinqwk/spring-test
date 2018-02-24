package com.merlin.practice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by qwk on 2018-02-24 09:55
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class HelloTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void getName(){
        String name;
        name = helloService.getStoreName();
        System.out.print("storeName:" + name);
    }
}

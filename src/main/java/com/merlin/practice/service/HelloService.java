package com.merlin.practice.service;

import com.merlin.practice.dao.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qwk on 2018-02-24 09:09
 **/
@Service
public class HelloService {

    @Autowired
    private HelloMapper helloMapper;

    public String getStoreName(){
        return helloMapper.getName();
    }
}

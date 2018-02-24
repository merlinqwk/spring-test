package com.merlin.practice.service;

import com.merlin.practice.dao.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qwk on 2018-02-24 09:09
 **/
@Service
public class HelloService {

    @Autowired
    private HelloDao helloDao;

    public String getStoreName(){
        return helloDao.getName();
    }
}

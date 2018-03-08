package com.merlin.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qwk on 2018-03-07 16:37
 **/
@RestController
public class RedisCacheController {

    @Autowired
    private JedisConnectionFactory connectionFactory;

}

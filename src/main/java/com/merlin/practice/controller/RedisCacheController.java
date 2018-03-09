package com.merlin.practice.controller;

import com.merlin.practice.model.KeyValue;
import com.merlin.practice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * Created by qwk on 2018-03-09 10:56
 **/
@RestController
public class RedisCacheController {
    @Autowired
    private JedisConnectionFactory connectionFactory;

    @ResponseBody
    @PostMapping("withoutPipeline")
    public String withoutPipeline(@RequestBody KeyValue keyValue){
        Jedis jedis = connectionFactory.getShardInfo().createResource();
        try{
            String result = jedis.hget(keyValue.getKey() + ":" + keyValue.getId(),keyValue.getKey() + keyValue.getId());
            return result;
        }finally {
            jedis.close();
        }
    }

    @ResponseBody
    @PostMapping("withPipeline")
    public String withPipeline(@RequestBody KeyValue keyValue){
        Jedis jedis = connectionFactory.getShardInfo().createResource();
        try{
            Pipeline pipeline = jedis.pipelined();
            Response<String> response =  pipeline.hget(keyValue.getKey() + ":" + keyValue.getId(),keyValue.getKey() + keyValue.getId());
            pipeline.sync();
            String result = response.get();
            return result;
        }finally {
            jedis.close();
        }
    }

}

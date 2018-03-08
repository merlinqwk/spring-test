package com.merlin.practice.controller;

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
 * Created by qwk on 2018-03-07 16:37
 **/
@RestController
public class RedisCacheController {

    @Autowired
    private JedisConnectionFactory connectionFactory;

    @ResponseBody
    @PostMapping("/withoutPipeline")
    public String withoutPipeline(@RequestBody String key,Integer id){
        Jedis jedis = connectionFactory.getShardInfo().createResource();
        try{
            String result = jedis.hget(key + ":" + id,key + id);
            return result;
        }finally {
            jedis.close();
        }
    }

    @ResponseBody
    @PostMapping("/withPipeline")
    public String withPipeline(@RequestBody String key,Integer id){
        Jedis jedis = connectionFactory.getShardInfo().createResource();
        try{
            Pipeline pipeline = jedis.pipelined();
            Response<String> response =  pipeline.hget(key + ":" +id,key + id);
            pipeline.sync();
            String result = response.get();
            return result;
        }finally {
            jedis.close();
        }
    }


}

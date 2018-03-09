package com.merlin.practice.service;

import com.merlin.practice.controller.RedisCacheController;
import com.merlin.practice.model.KeyValue;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by qwk on 2018-03-07 16:50
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class RedisTest {

    @Autowired
    private JedisConnectionFactory connectionFactory;
    @Autowired
    private RedisCacheController redisCacheController;

    @Test
    public void jedisTest(){
        Jedis jedis = connectionFactory.getShardInfo().createResource();

        jedis.select(0);
        jedis.flushDB();

        Map<String,String> mapData = new HashMap<>();

        //without pipeline hmset

        long start = System.currentTimeMillis();
        for (int i = 0;i < 10000;i++){
            mapData.clear();
            mapData.put("key"+i,"value" + i);
            jedis.hmset("key:" + i,mapData);
        }
        long end = System.currentTimeMillis();
        System.out.println("dbSize:[" + jedis.dbSize() + "]...");
        System.out.println("hmset without pipeline used:[" + (end - start) + "]  seconds");


        //with pipeline hmset
        jedis.select(8);
        jedis.flushDB();
        Pipeline pipeline = jedis.pipelined();
        start = System.currentTimeMillis();
        for (int i = 0;i < 10000;i++){
            mapData.clear();
            mapData.put("key" + i,"value" + i);
            pipeline.hmset("key:" + i,mapData);
        }
        pipeline.sync();
        end = System.currentTimeMillis();
        System.out.println("dbSize:[" + jedis.dbSize() + "]...");
        System.out.println("hmset without pipeline used:[" + (end - start)  + "]  seconds");

        //without pipeline hgetAll
        Set<String> keys = jedis.keys("key*");
        Map<String,Map<String,String>> result = new HashMap<>();
        start = System.currentTimeMillis();
        for (String key : keys){
            result.put(key,jedis.hgetAll(key));
        }
        end = System.currentTimeMillis();
        System.out.println("result size :[ " + result.size() + "]..");
        System.out.println("without pipeline get result used : [" + (end - start)  + "] seconds");

        result.clear();

        //with pipeline hgetAll
        Map<String,Response<Map<String,String>>> response = new HashMap<>();
        start = System.currentTimeMillis();
        for (String key : keys){
            response.put(key,pipeline.hgetAll(key));
        }
        pipeline.sync();
        for (String key : keys){
            result.put(key,response.get(key).get());
        }
        end = System.currentTimeMillis();
        System.out.println("result size : [" + result.size() + "]..");
        System.out.println("with pipeline get result used : [" + (end - start)  + "] seconds");
        jedis.disconnect();
    }

    @Test
    public void testWithoutPipeline(){
        KeyValue keyValue = new KeyValue("key",0);
//        long start = System.currentTimeMillis();
//        for (int i = 0;i < 10000;i++){
//            redisCacheController.withoutPipeline("key",0);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("WithoutPipeline cost time : [" + (end - start) + "] ..");
        System.out.println("withoutPipeline key0 value = " + redisCacheController.withoutPipeline(keyValue));
    }

    @Test
    public void testWithPipeline(){
        KeyValue keyValue = new KeyValue("key",0);
//        long start = System.currentTimeMillis();
//        for (int i = 0;i < 10000;i++){
//            redisCacheController.withPipeline(keyValue);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("-------------WithPipeline cost time : [" + (end - start) + "] ..");
        System.out.println("withPipeline key0 value = " + redisCacheController.withPipeline(keyValue));
    }

}

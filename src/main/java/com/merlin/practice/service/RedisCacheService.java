package com.merlin.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by qwk on 2018-03-09 14:12
 **/
@Service
public class RedisCacheService {

    @Autowired
    private JedisPool jedisPool;
    private Integer dataExpireTime = -1;

    public void putObject(String key,Object data){
        Jedis jedis;
        Pipeline pipeline;
        jedis = jedisPool.getResource();
        try{
            pipeline = jedis.pipelined();
            pipeline.setex(key,dataExpireTime,data.toString());
        }finally {
            closeJedis(jedis);
        }

    }

    public Object getObject(String key){
        Jedis jedis;
        Pipeline pipeline;
        jedis = jedisPool.getResource();
        try{
            pipeline = jedis.pipelined();
            Object object = pipeline.get(key);
            return object;
        }finally {
            closeJedis(jedis);
        }
    }

    public List<Map> getListWithPipeline(String keyHead){
        Jedis jedis = jedisPool.getResource();
        Pipeline pipeline = jedis.pipelined();
        List<Map> result = new ArrayList<>();
        try{
            Set<String> keys = jedis.keys(keyHead + "*");
            List<Response<Map<String,String>>> responseList = new ArrayList<>();
            for (String key : keys){
                responseList.add(pipeline.hgetAll(key));
            }
            pipeline.sync();
            int size = responseList.size();
            for (int i = 0;i < size;i++){
                result.add(responseList.get(i).get());
            }
            return result;
        }finally {
            closeJedis(jedis);
        }
    }

    private void closeJedis(Jedis jedis){
        jedis.close();
    }

    public Integer getDateExpireTime() {
        return dataExpireTime;
    }

    public void setDateExpireTime(Integer dateExpireTime) {
        this.dataExpireTime = dateExpireTime;
    }
}

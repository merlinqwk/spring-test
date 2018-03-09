package com.merlin.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Created by qwk on 2018-03-09 14:12
 **/
@Service
public class RedisCacheService {

    @Autowired
    private JedisConnectionFactory connectionFactory;
    private Jedis jedis;
    private Pipeline pipeline;
    private Integer dataExpireTime = -1;

    public void putObject(String key,Object data){
        try{
            jedis = connectionFactory.getShardInfo().createResource();
            pipeline = jedis.pipelined();
            pipeline.setex(key,dataExpireTime,data.toString());
        }finally {
            jedis.close();
        }

    }

    public Object getObject(String key){
        try{
            jedis = connectionFactory.getShardInfo().createResource();
            pipeline = jedis.pipelined();
            Object object = pipeline.get(key);
            return object;
        }finally {
            jedis.close();
        }
    }

    public Integer getDateExpireTime() {
        return dataExpireTime;
    }

    public void setDateExpireTime(Integer dateExpireTime) {
        this.dataExpireTime = dateExpireTime;
    }
}

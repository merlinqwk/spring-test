package com.merlin.practice.util;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by qwk on 2018-02-26 15:39
 **/
public class MybatisCache implements Cache {


    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}

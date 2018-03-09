package com.merlin.practice.model;

import java.io.Serializable;

/**
 * Created by qwk on 2018-03-09 11:20
 **/
public class KeyValue implements Serializable {
    private static final long serialVersionUID = -6069934424535498184L;

    private String key;
    private Integer id;

    public KeyValue() {
    }

    public KeyValue(String key, int id) {
        this.key = key;
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

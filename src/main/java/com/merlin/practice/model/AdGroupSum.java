package com.merlin.practice.model;

import java.io.Serializable;

/**
 * Created by qwk on 2018-02-26 11:32
 **/
public class AdGroupSum implements Serializable {
    private static final long serialVersionUID = 6453192379630078697L;
    private Long id;
    private Long adId;
    private Long groupId;
    private Long playNum;
    private String adName;

    public AdGroupSum() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getPlayNum() {
        return playNum;
    }

    public void setPlayNum(Long playNum) {
        this.playNum = playNum;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
}

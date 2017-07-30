package com.example.khanh.listenwritedemo.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 7/29/2017.
 */

public class Applications {
    private String des;
    @SerializedName("icon_link")
    private String iconLink;
    @SerializedName("playStore_link")
    private String playStoreLink;
    private String pid;
    private String name;

    public Applications(String des, String iconLink, String playStoreLink, String pid, String name) {
        this.des = des;
        this.iconLink = iconLink;
        this.playStoreLink = playStoreLink;
        this.pid = pid;
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getPlayStoreLink() {
        return playStoreLink;
    }

    public void setPlayStoreLink(String playStoreLink) {
        this.playStoreLink = playStoreLink;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

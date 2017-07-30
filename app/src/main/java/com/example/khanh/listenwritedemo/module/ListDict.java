package com.example.khanh.listenwritedemo.module;

/**
 * Created by Administrator on 7/29/2017.
 */

public class ListDict {
    private String name;
    private String url;

    public ListDict(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

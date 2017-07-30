package com.example.khanh.listenwritedemo.module;

/**
 * Created by Administrator on 7/29/2017.
 */

public class ListLang {
    private String code;
    private String name;

    public ListLang(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.khanh.listenwritedemo.module;

import java.io.Serializable;

/**
 * Created by Administrator on 7/27/2017.
 */

public class Language implements Serializable{
    private String name;
    private String code;

    public Language(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

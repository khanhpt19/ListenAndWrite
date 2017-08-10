package com.example.khanh.listenwritedemo.request;

import android.content.Context;

import com.example.khanh.listenwritedemo.module.Config;
import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Administrator on 7/29/2017.
 */

public class TaskConfig extends TaskBase<Config>{
    public TaskConfig(Context context) {
        super(context);
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.myjson.com/bins/18ce3x";
    }

    @Override
    protected Config genDataFromJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type type = new TypeToken<Config>() {}.getType();

        return gson.fromJson(json, type);
    }

}

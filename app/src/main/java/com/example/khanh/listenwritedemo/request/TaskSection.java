package com.example.khanh.listenwritedemo.request;

import android.content.Context;

import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 7/24/2017.
 */


public class TaskSection extends TaskBase<ArrayList<Section>> {
    private String code;
    public TaskSection(Context context,String code) {
        super(context);
        this.code=code;
    }

    @Override
    protected String getBaseUrl() {
        return "http://s1.yobimind.com/write_sentence/api/topic/"+code;
    }

    @Override
    protected ArrayList<Section> genDataFromJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type type = new TypeToken<List<Section>>() {}.getType();

        return gson.fromJson(json, type);
    }

}
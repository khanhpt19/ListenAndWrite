package com.example.khanh.listenwritedemo.request;

import android.content.Context;

import com.example.khanh.listenwritedemo.module.Admob;
import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/28/2017.
 */

public class TaskAdmob extends TaskBase<ArrayList<Admob>> {
    public TaskAdmob(Context context) {
        super(context);
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.myjson.com/bins/m12z1";
    }

    @Override
    protected ArrayList<Admob> genDataFromJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type type = new TypeToken<List<Admob>>() {}.getType();

        return gson.fromJson(json, type);
    }

}
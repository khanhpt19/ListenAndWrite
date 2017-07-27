package com.example.khanh.listenwritedemo.request;

import android.content.Context;

import com.example.khanh.listenwritedemo.module.Language;
import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 7/11/2017.
 */

public class TaskLanguage extends TaskBase<ArrayList<Language>> {

    public TaskLanguage(Context context) {
        super(context);
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.myjson.com/bins/jx5y5";
    }

    @Override
    protected ArrayList<Language> genDataFromJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type type = new TypeToken<List<Language>>() {}.getType();

        return gson.fromJson(json, type);
    }

}

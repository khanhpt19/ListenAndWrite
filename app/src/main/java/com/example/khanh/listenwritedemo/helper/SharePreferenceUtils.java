package com.example.khanh.listenwritedemo.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 7/29/2017.
 */

public class SharePreferenceUtils {
    private static SharedPreferences sharedpreferences;
    private static SharedPreferences.Editor editor;
    public static void setString(Context context,String fileName, String json){
        editor=context.getSharedPreferences(fileName,context.MODE_PRIVATE).edit();
        editor.putString(fileName,json);
        editor.commit();
    }

    public static String getString(Context context, String fileName){
        sharedpreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedpreferences.getString(fileName,null);
    }
}

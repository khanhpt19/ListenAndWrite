package com.example.khanh.listenwritedemo.helper;

/**
 * Created by Administrator on 7/29/2017.
 */

public class StringUtils {
    public static boolean isEmpty(String s){
        if(s==null || s.isEmpty()){
            return true;
        }
        return false;
    }
}

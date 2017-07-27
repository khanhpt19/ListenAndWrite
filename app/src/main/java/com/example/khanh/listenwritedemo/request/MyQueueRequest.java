package com.example.khanh.listenwritedemo.request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by suppo on 7/29/2016.
 */
public class MyQueueRequest {
    private static RequestQueue requestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}

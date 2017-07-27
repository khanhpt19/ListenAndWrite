package com.example.khanh.listenwritedemo.request;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by chungbn on 03/10/2015.
 */
public abstract class TaskBase<T extends Object> {
    private static final String TAG = TaskBase.class.getSimpleName();
    protected Context mContext;
    private StringRequest request;

    public TaskBase(Context context) {
        this.mContext = context;
    }

    /* Create request to server*/
    public void request(final Response.Listener<T> listener, final Response.ErrorListener errorListener) {
        String url = getBaseUrl();
        Log.d(TAG, "URL " + url);
        final JSONObject json = genParams();
        request = new StringRequest(getMethod(), url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response = decodeData(response);
                Log.i(TAG, "response:" + response);
                try {
                    listener.onResponse(genDataFromJSON(response));
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onResponse(genDataFromJSON(null));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
                errorListener.onErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = getHeader();
                return header != null ? header : super.getHeaders();
            }

            @Override
            public String getBodyContentType() {
                return getBodyType();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if (json != null) {
                    Log.d(TAG, "Params:" + json.toString());
                    return json.toString().getBytes(Charset.forName("UTF-8"));
                } else {
                    return null;
                }
            }
        };
        MyQueueRequest.getRequestQueue(mContext).add(request);
    }

    protected abstract String getBaseUrl();

    protected abstract T genDataFromJSON(String json);

    protected JSONObject genParams() {
        return null;
    }

    protected Map<String, String> getHeader() {
        return null;
    }

    protected int getMethod() {
        return Request.Method.GET;
    }

    protected String getBodyType() {
        return "application/json";
    }

    protected String decodeData(String data) {
        return data;
    }

    /* Cancel request*/
    public void cancel() {
        if (request != null) {
            if (!request.isCanceled()) {
                request.cancel();
                Log.i("Request", "Cancel Request");
            }
        }
    }
}

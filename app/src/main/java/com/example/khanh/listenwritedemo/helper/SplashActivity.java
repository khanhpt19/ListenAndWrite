package com.example.khanh.listenwritedemo.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khanh.listenwritedemo.MainActivity;
import com.example.khanh.listenwritedemo.module.Config;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.request.TaskConfig;
import com.google.gson.Gson;
import com.victor.loading.newton.NewtonCradleLoading;

import org.json.JSONObject;

/**
 * Created by chungbn on 02/12/2015.
 */
public class SplashActivity extends AppCompatActivity {
    private Config config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSetting();

        NewtonCradleLoading newtonCradleLoading;
        newtonCradleLoading = (NewtonCradleLoading)findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.start();
        newtonCradleLoading.setLoadingColor(R.color.colorWhite);
        newtonCradleLoading.setLoadingColor(getResources().getColor(R.color.colorWhite));

    }

    private void getSetting() {
        TaskConfig taskConfig = new TaskConfig(getApplicationContext());
        taskConfig.request(new Response.Listener<Config>() {
            @Override
            public void onResponse(Config response) {
                config=response;
                Log.d("config", new Gson().toJson(response));
                SharePreferenceUtils.setString(getBaseContext(),"SPLASH",new Gson().toJson(config));
                executeResponse();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void executeResponse()  {
        if(config.getUpdateSetting().getSwitchAppEnable()){
            showSwitchAppDialog(config.getUpdateSetting().getSwitchAppTitle(), config.getUpdateSetting().getSwitchAppMsg(),
                    config.getUpdateSetting().getSwitchAppPackage(), config.getUpdateSetting().getSwitchAppForce());
        }
        else{
            int appUpdateVersion = config.getUpdateSetting().getUpdateVersion();
            PackageInfo pInfo = null;
            try {
                pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (pInfo.versionCode < appUpdateVersion && config.getUpdateSetting().getUpdateEnable()) {
                showDialogNotifyUpdate(config.getUpdateSetting().getUpdateTitle(),
                        config.getUpdateSetting().getUpdateMsg(), config.getUpdateSetting().getUpdateForce());
            } else {
                openApplication();
            }
        }
    }

    private void showSwitchAppDialog(String title, String msg,
                                     final String packageName, final boolean isForceUpdate) {
        if (msg == null || msg.length() == 0) {
            msg = "Please update to the latest version.";
        }
        if (title == null || title.length() == 0) {
            msg = "New Update Available";
        }
        String negTitle = isForceUpdate ? "Quit" : "Later";

        // 1. Instantiate an AlertDialog.Builder with its constructor
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(msg).setTitle(title);
        // Add the buttons
        builder.setPositiveButton("Update Now", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                finish();
                AppUtils.openStore(getApplicationContext(),packageName);
            }
        });
        builder.setNegativeButton(negTitle, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                if (isForceUpdate) {
                    finish();
                } else {
                    openApplication();
                }
            }
        });

        // 3. Get the AlertDialog from create()
        android.app.AlertDialog dialog = builder.create();
        if (isForceUpdate) {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    public void showDialogNotifyUpdate(String title, String message, final boolean isForce) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SplashActivity.this);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        String negTitle = isForce ? "Quit" : "Later";
        dialog.setMessage(message);
        dialog.setPositiveButton("Update Now", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                openStore();
            }
        });
        dialog.setNegativeButton(negTitle, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (isForce) {
                    finish();
                } else {
                    checkLanguageSetting();
                }
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void openStore() {
        String appPackageName = getPackageName();
        int versionCode = 0;
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        AppUtils.openStore(getApplicationContext(),appPackageName);
        finish();
    }

    private void checkLanguageSetting() {
//        final DataPrefs dataPrefs = DataPrefs.newInstance(this);
//        boolean isSet =  !StringUtils.isEmpty(dataPrefs.getLangNative());
//        if(isSet){
//            openApplication();
//        }else{
//            try {
//                final JSONArray json = new JSONArray(dataPrefs.getDataSettingLang());
//                int length = json.length();
//                String[] items = new String[length];
//                for(int i = 0;i<length;i++){
//                    items[i] = json.getJSONObject(i).getString("name");
//                }
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Choose your native language");
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        try {
//                            JSONObject jsonObject = json.getJSONObject(which);
//                            dataPrefs.setLangNative(jsonObject.getString("code"), jsonObject.toString());
//                            openApplication();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                builder.setCancelable(false);
//                if(!this.isFinishing()){
//                    builder.show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        openApplication();
    }

    private void openApplication() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }


}

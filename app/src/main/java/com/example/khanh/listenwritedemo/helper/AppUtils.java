package com.example.khanh.listenwritedemo.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.Random;

/**
 * Created by chungbn on 05/10/2015.
 */
public class AppUtils {
    public static void rateApp(Activity activity) {
        openStore(activity, activity.getPackageName());
    }

    public static void openStore(Context context, String appPackageName) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName+ "&referrer=utm_source%3Dvocabulary_memorize_android")));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName+ "&referrer=utm_source%3Dvocabulary_memorize_android")));
        }
    }

    public static void openApp(Context context, String packageId) {
        if(isPackageInstalled(packageId, context)){
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageId));
        }else{
            openStore(context, packageId);
        }
    }
    private static boolean isPackageInstalled(String packageId, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}

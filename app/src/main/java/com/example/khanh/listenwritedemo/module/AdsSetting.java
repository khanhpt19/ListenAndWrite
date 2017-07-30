package com.example.khanh.listenwritedemo.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 7/29/2017.
 */

public class AdsSetting {
    private int dsecond;
    private int isEnable;
    private int timeperday;
    @SerializedName("admob_inter")
    private String admobInter;
    @SerializedName("admob_banner")
    private String admobBanner;
    @SerializedName("fb_inter")
    private String fbInter;
    @SerializedName("fb_native")
    private String fbNative;
    @SerializedName("fb_banner")
    private String fbBanner;
    @SerializedName("ads_cat")
    private String adsCat;

    public AdsSetting(int dsecond, int isEnable, int timeperday, String admobInter,
                      String admobBanner, String fbInter, String fbNative, String fbBanner, String adsCat) {
        this.dsecond = dsecond;
        this.isEnable = isEnable;
        this.timeperday = timeperday;
        this.admobInter = admobInter;
        this.admobBanner = admobBanner;
        this.fbInter = fbInter;
        this.fbNative = fbNative;
        this.fbBanner = fbBanner;
        this.adsCat = adsCat;
    }

    public int getDsecond() {
        return dsecond;
    }

    public void setDsecond(int dsecond) {
        this.dsecond = dsecond;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getTimeperday() {
        return timeperday;
    }

    public void setTimeperday(int timeperday) {
        this.timeperday = timeperday;
    }

    public String getAdmobInter() {
        return admobInter;
    }

    public void setAdmobInter(String admobInter) {
        this.admobInter = admobInter;
    }

    public String getAdmobBanner() {
        return admobBanner;
    }

    public void setAdmobBanner(String admobBanner) {
        this.admobBanner = admobBanner;
    }

    public String getFbInter() {
        return fbInter;
    }

    public void setFbInter(String fbInter) {
        this.fbInter = fbInter;
    }

    public String getFbNative() {
        return fbNative;
    }

    public void setFbNative(String fbNative) {
        this.fbNative = fbNative;
    }

    public String getFbBanner() {
        return fbBanner;
    }

    public void setFbBanner(String fbBanner) {
        this.fbBanner = fbBanner;
    }

    public String getAdsCat() {
        return adsCat;
    }

    public void setAdsCat(String adsCat) {
        this.adsCat = adsCat;
    }
}

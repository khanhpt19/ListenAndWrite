package com.example.khanh.listenwritedemo.module;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/28/2017.
 */

public class Admob {
    private int dsecond;
    private int isEnable;
    private int timeperday;
    private String admob_inter;
    private String admob_banner;
    private String fb_inter;
    private String fb_native;
    private String fb_banner;
    private String ads_cat;
    private String des;
    private String icon_link;
    private String name;
    private  String pid;
    private String playStore_link;
    private ArrayList<Practice> rcm_app;

    public Admob(int dsecond, int isEnable, int timeperday, String admob_inter, String admob_banner, String fb_inter, String fb_native, String fb_banner,
                 String ads_cat, String des, String icon_link, String name, String pid, String playStore_link, ArrayList<Practice> phrases) {
        this.dsecond = dsecond;
        this.isEnable = isEnable;
        this.timeperday = timeperday;
        this.admob_inter = admob_inter;
        this.admob_banner = admob_banner;
        this.fb_inter = fb_inter;
        this.fb_native = fb_native;
        this.fb_banner = fb_banner;
        this.ads_cat = ads_cat;
        this.des = des;
        this.icon_link = icon_link;
        this.name = name;
        this.pid = pid;
        this.playStore_link = playStore_link;
        this.rcm_app = phrases;
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

    public String getAdmob_inter() {
        return admob_inter;
    }

    public void setAdmob_inter(String admob_inter) {
        this.admob_inter = admob_inter;
    }

    public String getAdmob_banner() {
        return admob_banner;
    }

    public void setAdmob_banner(String admob_banner) {
        this.admob_banner = admob_banner;
    }

    public String getFb_inter() {
        return fb_inter;
    }

    public void setFb_inter(String fb_inter) {
        this.fb_inter = fb_inter;
    }

    public String getFb_native() {
        return fb_native;
    }

    public void setFb_native(String fb_native) {
        this.fb_native = fb_native;
    }

    public String getFb_banner() {
        return fb_banner;
    }

    public void setFb_banner(String fb_banner) {
        this.fb_banner = fb_banner;
    }

    public String getAds_cat() {
        return ads_cat;
    }

    public void setAds_cat(String ads_cat) {
        this.ads_cat = ads_cat;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIcon_link() {
        return icon_link;
    }

    public void setIcon_link(String icon_link) {
        this.icon_link = icon_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPlayStore_link() {
        return playStore_link;
    }

    public void setPlayStore_link(String playStore_link) {
        this.playStore_link = playStore_link;
    }

    public ArrayList<Practice> getRcm_app() {
        return rcm_app;
    }

    public void setRcm_app(ArrayList<Practice> phrases) {
        this.rcm_app = phrases;
    }
}

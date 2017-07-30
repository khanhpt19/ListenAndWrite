package com.example.khanh.listenwritedemo.module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/29/2017.
 */

public class Config {
    @SerializedName("ads_setting")
    private AdsSetting adsSetting;
    @SerializedName("update_setting")
    private UpdateSetting updateSetting;
    @SerializedName("rcm_app")
    private ArrayList<Applications> rcmApp;
    @SerializedName("list_dict")
    private ArrayList<ListDict> listDict;
    @SerializedName("list_lang")
    private ArrayList<ListLang>listLang;

    public Config(AdsSetting adsSetting, UpdateSetting updateSetting,
                  ArrayList<Applications> rcmApp, ArrayList<ListDict> listDict, ArrayList<ListLang> listLang) {
        this.adsSetting = adsSetting;
        this.updateSetting = updateSetting;
        this.rcmApp = rcmApp;
        this.listDict = listDict;
        this.listLang = listLang;
    }

    public AdsSetting getAdsSetting() {
        return adsSetting;
    }

    public void setAdsSetting(AdsSetting adsSetting) {
        this.adsSetting = adsSetting;
    }

    public UpdateSetting getUpdateSetting() {
        return updateSetting;
    }

    public void setUpdateSetting(UpdateSetting updateSetting) {
        this.updateSetting = updateSetting;
    }

    public ArrayList<Applications> getRcmApp() {
        return rcmApp;
    }

    public void setRcmApp(ArrayList<Applications> rcmApp) {
        this.rcmApp = rcmApp;
    }

    public ArrayList<ListDict> getListDict() {
        return listDict;
    }

    public void setListDict(ArrayList<ListDict> listDict) {
        this.listDict = listDict;
    }

    public ArrayList<ListLang> getListLang() {
        return listLang;
    }

    public void setListLang(ArrayList<ListLang> listLang) {
        this.listLang = listLang;
    }
}

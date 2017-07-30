package com.example.khanh.listenwritedemo.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 7/29/2017.
 */

public class UpdateSetting {
    @SerializedName("switch_app_enable")
    private Boolean switchAppEnable;
    @SerializedName("switch_app_force")
    private Boolean switchAppForce;
    @SerializedName("switch_app_title")
    private String switchAppTitle;
    @SerializedName("switch_app_msg")
    private String switchAppMsg;
    @SerializedName("switch_app_link")
    private String switchAppLink;
    @SerializedName("switch_app_package")
    private String switchAppPackage;
    @SerializedName("update_enable")
    private Boolean updateEnable;
    @SerializedName("update_force")
    private Boolean updateForce;
    @SerializedName("update_title")
    private String updateTitle;
    @SerializedName("update_msg")
    private String updateMsg;
    @SerializedName("update_version")
    private int updateVersion;

    public UpdateSetting(Boolean switchAppEnable, Boolean switchAppForce, String switchAppTitle, String switchAppMsg, String switchAppLink,
                         String switchAppPackage, Boolean updateEnable, Boolean updateForce, String updateTitle, String updateMsg, int updateVersion) {
        this.switchAppEnable = switchAppEnable;
        this.switchAppForce = switchAppForce;
        this.switchAppTitle = switchAppTitle;
        this.switchAppMsg = switchAppMsg;
        this.switchAppLink = switchAppLink;
        this.switchAppPackage = switchAppPackage;
        this.updateEnable = updateEnable;
        this.updateForce = updateForce;
        this.updateTitle = updateTitle;
        this.updateMsg = updateMsg;
        this.updateVersion = updateVersion;
    }

    public Boolean getSwitchAppEnable() {
        return switchAppEnable;
    }

    public void setSwitchAppEnable(Boolean switchAppEnable) {
        this.switchAppEnable = switchAppEnable;
    }

    public Boolean getSwitchAppForce() {
        return switchAppForce;
    }

    public void setSwitchAppForce(Boolean switchAppForce) {
        this.switchAppForce = switchAppForce;
    }

    public String getSwitchAppTitle() {
        return switchAppTitle;
    }

    public void setSwitchAppTitle(String switchAppTitle) {
        this.switchAppTitle = switchAppTitle;
    }

    public String getSwitchAppMsg() {
        return switchAppMsg;
    }

    public void setSwitchAppMsg(String switchAppMsg) {
        this.switchAppMsg = switchAppMsg;
    }

    public String getSwitchAppLink() {
        return switchAppLink;
    }

    public void setSwitchAppLink(String switchAppLink) {
        this.switchAppLink = switchAppLink;
    }

    public String getSwitchAppPackage() {
        return switchAppPackage;
    }

    public void setSwitchAppPackage(String switchAppPackage) {
        this.switchAppPackage = switchAppPackage;
    }

    public Boolean getUpdateEnable() {
        return updateEnable;
    }

    public void setUpdateEnable(Boolean updateEnable) {
        this.updateEnable = updateEnable;
    }

    public Boolean getUpdateForce() {
        return updateForce;
    }

    public void setUpdateForce(Boolean updateForce) {
        this.updateForce = updateForce;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }

    public int getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(int updateVersion) {
        this.updateVersion = updateVersion;
    }
}

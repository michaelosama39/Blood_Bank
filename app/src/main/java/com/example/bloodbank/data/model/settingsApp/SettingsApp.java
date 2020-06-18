
package com.example.bloodbank.data.model.settingsApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SettingsApp {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private SettingsAppData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SettingsAppData getData() {
        return data;
    }

    public void setData(SettingsAppData data) {
        this.data = data;
    }

}

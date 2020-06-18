
package com.example.bloodbank.data.model.favoruitePosts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoruitePosts {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private FavoruitePostsData data;

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

    public FavoruitePostsData getData() {
        return data;
    }

    public void setData(FavoruitePostsData data) {
        this.data = data;
    }

}

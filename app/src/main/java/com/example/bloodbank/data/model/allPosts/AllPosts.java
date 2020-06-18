
package com.example.bloodbank.data.model.allPosts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllPosts {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AllPostsPagination data;

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

    public AllPostsPagination getData() {
        return data;
    }

    public void setData(AllPostsPagination data) {
        this.data = data;
    }

}

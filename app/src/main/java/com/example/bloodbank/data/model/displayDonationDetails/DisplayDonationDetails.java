
package com.example.bloodbank.data.model.displayDonationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayDonationDetails {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DisplayDonationDetailsData data;

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

    public DisplayDonationDetailsData getData() {
        return data;
    }

    public void setData(DisplayDonationDetailsData data) {
        this.data = data;
    }

}

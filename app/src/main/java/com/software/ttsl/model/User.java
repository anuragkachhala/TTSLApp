package com.software.ttsl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User
{

    @SerializedName("userId")
    private String userId;
    @SerializedName("password")

    private String password;

    @SerializedName("tokenId")
    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

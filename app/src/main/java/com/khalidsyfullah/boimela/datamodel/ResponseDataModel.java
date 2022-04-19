package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDataModel {

    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("Set-Cookie")
    @Expose
    private String cookie;
    @SerializedName("message")
    @Expose
    private String message;

    public ResponseDataModel(String cookie) {
        this.cookie = cookie;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

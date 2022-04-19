package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CookieDataModel {

    @SerializedName("refreshToken")
    @Expose
    String refreshToken;
    @SerializedName("Max-Age")
    @Expose
    String maxAge;
    @SerializedName("Path")
    @Expose
    String path;
    @SerializedName("Expires")
    @Expose
    String expire;

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public String getPath() {
        return path;
    }

    public String getExpire() {
        return expire;
    }
}

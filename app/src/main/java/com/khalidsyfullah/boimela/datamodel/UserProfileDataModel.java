package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileDataModel {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("user")
    @Expose
    private UserDataModel user;


    public UserProfileDataModel(String message, UserDataModel user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDataModel getUser() {
        return user;
    }

    public void setUser(UserDataModel user) {
        this.user = user;
    }
}

package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorDetailsDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("author")
    @Expose
    AuthorDataModel author;

    public AuthorDetailsDataModel(String message, AuthorDataModel author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthorDataModel getAuthor() {
        return author;
    }

    public void setAuthors(AuthorDataModel author) {
        this.author = author;
    }
}

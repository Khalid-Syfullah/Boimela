package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorsDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("authors")
    @Expose
    AuthorCountDataModel authors;

    public AuthorsDataModel(String message, AuthorCountDataModel authors) {
        this.message = message;
        this.authors = authors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthorCountDataModel getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorCountDataModel authors) {
        this.authors = authors;
    }
}

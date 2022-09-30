package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PopularAuthorsDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("popularAuthors")
    @Expose
    ArrayList<AuthorDataModel> popularAuthors;

    public PopularAuthorsDataModel(String message, ArrayList<AuthorDataModel> popularAuthors) {
        this.message = message;
        this.popularAuthors = popularAuthors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<AuthorDataModel> getPopularAuthors() {
        return popularAuthors;
    }

    public void setPopularAuthors(ArrayList<AuthorDataModel> popularAuthors) {
        this.popularAuthors = popularAuthors;
    }
}

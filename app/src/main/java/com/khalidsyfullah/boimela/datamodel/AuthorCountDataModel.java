package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuthorCountDataModel {

    @SerializedName("pageCount")
    @Expose
    String pageCount;

    @SerializedName("itemCount")
    @Expose
    String itemCount;

    @SerializedName("items")
    @Expose
    ArrayList<AuthorDataModel> authors;

    public AuthorCountDataModel(String pageCount, String itemCount, ArrayList<AuthorDataModel> authors) {
        this.pageCount = pageCount;
        this.itemCount = itemCount;
        this.authors = authors;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public ArrayList<AuthorDataModel> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<AuthorDataModel> authors) {
        this.authors = authors;
    }
}

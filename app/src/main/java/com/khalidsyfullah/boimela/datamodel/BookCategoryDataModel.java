package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookCategoryDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("categorys")
    @Expose
    ArrayList<CategoryDataModel> categorys;

    public BookCategoryDataModel(String message, ArrayList<CategoryDataModel> categorys) {
        this.message = message;
        this.categorys = categorys;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CategoryDataModel> getCategorys() {
        return categorys;
    }

    public void setCategorys(ArrayList<CategoryDataModel> categorys) {
        this.categorys = categorys;
    }
}

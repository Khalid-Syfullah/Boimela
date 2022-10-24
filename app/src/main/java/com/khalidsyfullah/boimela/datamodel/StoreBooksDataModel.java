package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StoreBooksDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("home")
    @Expose
    ArrayList<StoreBooksCollectionDataModel> bookCollections;

    public StoreBooksDataModel(String message, ArrayList<StoreBooksCollectionDataModel> bookCollections) {
        this.message = message;
        this.bookCollections = bookCollections;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<StoreBooksCollectionDataModel> getBookCollections() {
        return bookCollections;
    }

    public void setBookCollections(ArrayList<StoreBooksCollectionDataModel> bookCollections) {
        this.bookCollections = bookCollections;
    }
}

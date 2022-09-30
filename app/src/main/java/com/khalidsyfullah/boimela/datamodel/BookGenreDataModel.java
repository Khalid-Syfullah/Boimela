package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookGenreDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("bookCollections")
    @Expose
    ArrayList<BookGenreItemDataModel> bookCollections;

    public BookGenreDataModel(String message, ArrayList<BookGenreItemDataModel> bookCollections) {
        this.message = message;
        this.bookCollections = bookCollections;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BookGenreItemDataModel> getBookCollections() {
        return bookCollections;
    }

    public void setBookCollections(ArrayList<BookGenreItemDataModel> bookCollections) {
        this.bookCollections = bookCollections;
    }
}

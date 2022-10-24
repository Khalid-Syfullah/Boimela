package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryBooksDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("books")
    @Expose
    ArrayList<BookDataModel> books;

    public CategoryBooksDataModel(String message, ArrayList<BookDataModel> books) {
        this.message = message;
        this.books = books;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BookDataModel> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookDataModel> books) {
        this.books = books;
    }
}

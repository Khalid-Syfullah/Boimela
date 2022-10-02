package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookGetDetailsDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("book")
    @Expose
    BookDetailsDataModel book;

    public BookGetDetailsDataModel(String message, BookDetailsDataModel book) {
        this.message = message;
        this.book = book;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookDetailsDataModel getBook() {
        return book;
    }

    public void setBook(BookDetailsDataModel book) {
        this.book = book;
    }
}

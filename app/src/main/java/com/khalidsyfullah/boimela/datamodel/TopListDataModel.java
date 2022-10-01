package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopListDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("books")
    @Expose
    ArrayList<BookDataModel> books;

    @SerializedName("authors")
    @Expose
    ArrayList<AuthorDataModel> authors;

    @SerializedName("publishers")
    @Expose
    ArrayList<PublisherDataModel> publishers;

    public TopListDataModel(String message, ArrayList<BookDataModel> books, ArrayList<AuthorDataModel> authors, ArrayList<PublisherDataModel> publishers) {
        this.message = message;
        this.books = books;
        this.authors = authors;
        this.publishers = publishers;
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

    public ArrayList<AuthorDataModel> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<AuthorDataModel> authors) {
        this.authors = authors;
    }

    public ArrayList<PublisherDataModel> getPublishers() {
        return publishers;
    }

    public void setPublishers(ArrayList<PublisherDataModel> publishers) {
        this.publishers = publishers;
    }
}

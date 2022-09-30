package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookGenreItemDataModel {

    @SerializedName("_id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("books")
    @Expose
    ArrayList<BookDataModel> books;

    public BookGenreItemDataModel(String id, String name, ArrayList<BookDataModel> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BookDataModel> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookDataModel> books) {
        this.books = books;
    }
}

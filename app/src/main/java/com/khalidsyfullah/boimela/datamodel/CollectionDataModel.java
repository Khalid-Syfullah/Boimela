package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectionDataModel {

    @SerializedName("_id")
    @Expose
    String id;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("count")
    @Expose
    String count;

    @SerializedName("createdAt")
    @Expose
    String createdAt;

    @SerializedName("updatedAt")
    @Expose
    String updatedAt;

    @SerializedName("books")
    @Expose
    ArrayList<BookDataModel> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ArrayList<BookDataModel> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookDataModel> books) {
        this.books = books;
    }
}

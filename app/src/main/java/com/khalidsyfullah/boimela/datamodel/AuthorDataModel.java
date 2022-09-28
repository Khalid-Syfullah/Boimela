package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuthorDataModel {

    @SerializedName("_id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("birth")
    @Expose
    String birth;

    @SerializedName("death")
    @Expose
    String death;

    @SerializedName("location")
    @Expose
    String location;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("books")
    @Expose
    ArrayList<BookDataModel> books;


    public AuthorDataModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public AuthorDataModel(String name, String image, String birth, String death, String location, String description, ArrayList<BookDataModel> books) {
        this.name = name;
        this.image = image;
        this.birth = birth;
        this.death = death;
        this.location = location;
        this.description = description;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<BookDataModel> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookDataModel> books) {
        this.books = books;
    }
}

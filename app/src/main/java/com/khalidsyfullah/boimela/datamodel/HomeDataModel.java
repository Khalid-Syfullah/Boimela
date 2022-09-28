package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeDataModel {

    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("home")
    @Expose
    String[] home;

    @SerializedName("collection")
    @Expose
    CollectionDataModel collectionDataModel;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getHome() {
        return home;
    }

    public void setHome(String[] home) {
        this.home = home;
    }

    public CollectionDataModel getCollectionDataModel() {
        return collectionDataModel;
    }

    public void setCollectionDataModel(CollectionDataModel collectionDataModel) {
        this.collectionDataModel = collectionDataModel;
    }
}

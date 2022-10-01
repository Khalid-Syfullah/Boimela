package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublisherCountDataModel {

    @SerializedName("pageCount")
    @Expose
    String pageCount;

    @SerializedName("itemCount")
    @Expose
    String itemCount;

    @SerializedName("items")
    @Expose
    ArrayList<PublisherDataModel> items;


    public PublisherCountDataModel(String pageCount, String itemCount, ArrayList<PublisherDataModel> items) {
        this.pageCount = pageCount;
        this.itemCount = itemCount;
        this.items = items;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public ArrayList<PublisherDataModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<PublisherDataModel> items) {
        this.items = items;
    }
}

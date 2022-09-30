package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookSeriesCountDataModel {

    @SerializedName("pageCount")
    @Expose
    String pageCount;

    @SerializedName("itemCount")
    @Expose
    String itemCount;

    @SerializedName("items")
    @Expose
    ArrayList<BookSeriesItemDataModel> items;

    public BookSeriesCountDataModel(String pageCount, String itemCount, ArrayList<BookSeriesItemDataModel> items) {
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

    public ArrayList<BookSeriesItemDataModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<BookSeriesItemDataModel> items) {
        this.items = items;
    }
}

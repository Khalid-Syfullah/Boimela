package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookSeriesDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("series")
    @Expose
    BookSeriesCountDataModel series;

    public BookSeriesDataModel(String message, BookSeriesCountDataModel series) {
        this.message = message;
        this.series = series;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookSeriesCountDataModel getSeries() {
        return series;
    }

    public void setSeries(BookSeriesCountDataModel series) {
        this.series = series;
    }
}

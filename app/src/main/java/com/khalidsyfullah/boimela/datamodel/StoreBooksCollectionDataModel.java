package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StoreBooksCollectionDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("series")
    @Expose
    ArrayList<BookSeriesDataModel> series;

    @SerializedName("bookCollections")
    @Expose
    ArrayList<BookGenreItemDataModel> bookCollections;

    @SerializedName("popularWriter")
    @Expose
    WriterDataModel writerDataModel;

    public StoreBooksCollectionDataModel(String message, ArrayList<BookSeriesDataModel> series, ArrayList<BookGenreItemDataModel> bookCollections, WriterDataModel writerDataModel) {
        this.message = message;
        this.series = series;
        this.bookCollections = bookCollections;
        this.writerDataModel = writerDataModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BookSeriesDataModel> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<BookSeriesDataModel> series) {
        this.series = series;
    }

    public ArrayList<BookGenreItemDataModel> getBookCollections() {
        return bookCollections;
    }

    public void setBookCollections(ArrayList<BookGenreItemDataModel> bookCollections) {
        this.bookCollections = bookCollections;
    }

    public WriterDataModel getWriterDataModel() {
        return writerDataModel;
    }

    public void setWriterDataModel(WriterDataModel writerDataModel) {
        this.writerDataModel = writerDataModel;
    }
}

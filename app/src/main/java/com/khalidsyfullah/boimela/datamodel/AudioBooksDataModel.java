package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AudioBooksDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("audioBook")
    @Expose
    ArrayList<BookDataModel> audioBooks;

    public AudioBooksDataModel(String message, ArrayList<BookDataModel> audioBooks) {
        this.message = message;
        this.audioBooks = audioBooks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BookDataModel> getAudioBooks() {
        return audioBooks;
    }

    public void setAudioBooks(ArrayList<BookDataModel> audioBooks) {
        this.audioBooks = audioBooks;
    }
}

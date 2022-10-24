package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublisherDetailsDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("publisher")
    @Expose
    PublisherDataModel publisher;

    public PublisherDetailsDataModel(String message, PublisherDataModel publisher) {
        this.message = message;
        this.publisher = publisher;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PublisherDataModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDataModel publisher) {
        this.publisher = publisher;
    }
}

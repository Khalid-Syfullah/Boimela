package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublishersDataModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("publishers")
    @Expose
    PublisherCountDataModel publishers;

    public PublishersDataModel(String message, PublisherCountDataModel publishers) {
        this.message = message;
        this.publishers = publishers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PublisherCountDataModel getPublishers() {
        return publishers;
    }

    public void setPublishers(PublisherCountDataModel publishers) {
        this.publishers = publishers;
    }
}

package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OPFItemDataModel {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("href")
    @Expose
    private String href;

    @SerializedName("media-type")
    @Expose
    private String mediaType;

    public OPFItemDataModel(String id) {
        this.id = id;
    }


    public OPFItemDataModel(String id, String href, String mediaType) {
        this.id = id;
        this.href = href;
        this.mediaType = mediaType;
    }

    public String getId() {
        return id;
    }

    public String getHref() {
        return href;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}

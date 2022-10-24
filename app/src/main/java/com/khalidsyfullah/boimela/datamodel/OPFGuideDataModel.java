package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OPFGuideDataModel {


    @SerializedName("title")
    @Expose
    private String id;

    @SerializedName("href")
    @Expose
    private String href;

    @SerializedName("type")
    @Expose
    private String type;

    public OPFGuideDataModel(String id, String href, String type) {
        this.id = id;
        this.href = href;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

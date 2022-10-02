package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryDataModel {

    @SerializedName("_id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String categoryName;

    @SerializedName("image")
    @Expose
    String categoryImage;

    @SerializedName("type")
    @Expose
    int type;


    public SubCategoryDataModel(String id, String categoryName, String categoryImage, int type) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.type = type;
    }
    public SubCategoryDataModel(String categoryName, String categoryImage, int type) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

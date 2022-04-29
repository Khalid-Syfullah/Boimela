package com.khalidsyfullah.boimela.datamodel;

public class CategoryDataModel {

    String categoryName, categoryImage;
    int type;

    public CategoryDataModel(String categoryName, String categoryImage, int type) {
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
}

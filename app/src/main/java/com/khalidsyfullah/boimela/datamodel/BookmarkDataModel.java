package com.khalidsyfullah.boimela.datamodel;

public class BookmarkDataModel {

    private String title, page;
    private int type;

    public BookmarkDataModel(String title, String page, int type) {
        this.title = title;
        this.page = page;
        this.type = type;
    }

    public BookmarkDataModel(String title, int type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

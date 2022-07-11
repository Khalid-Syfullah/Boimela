package com.khalidsyfullah.boimela.datamodel;

public class ContentDataModel {

    private String title, page, href;
    private int type;

    public ContentDataModel(String title, String page, String href, int type) {
        this.title = title;
        this.page = page;
        this.href = href;
        this.type = type;
    }

    public ContentDataModel(String title, int type) {
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

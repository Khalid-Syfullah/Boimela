package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookDetailsDataModel {


    @SerializedName("_id")
    @Expose
    String _id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("price")
    @Expose
    int price;

    @SerializedName("numberOfReader")
    @Expose
    int numberOfReader;

    @SerializedName("like")
    @Expose
    int like;

    @SerializedName("page")
    @Expose
    int page;

    @SerializedName("rating")
    @Expose
    int rating;

    @SerializedName("numberOfRating")
    @Expose
    int numberOfRating;

    @SerializedName("award")
    @Expose
    int award;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("writer")
    @Expose
    WriterDataModel writer;

    @SerializedName("publisher")
    @Expose
    PublisherDataModel publisher;

    @SerializedName("category")
    @Expose
    CategoryDataModel categoryDataModel;

    @SerializedName("subCategory")
    @Expose
    SubCategoryDataModel subCategoryDataModel;

    @SerializedName("language")
    @Expose
    String language;

    @SerializedName("year")
    @Expose
    int year;

    @SerializedName("edition")
    @Expose
    String edition;

    @SerializedName("pdfFile")
    @Expose
    String pdfFile;

    @SerializedName("audioFile")
    @Expose
    String audioFile;

    @SerializedName("comments")
    @Expose
    ArrayList<ReviewDataModel> comments;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfReader() {
        return numberOfReader;
    }

    public void setNumberOfReader(int numberOfReader) {
        this.numberOfReader = numberOfReader;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(int numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WriterDataModel getWriter() {
        return writer;
    }

    public void setWriter(WriterDataModel writer) {
        this.writer = writer;
    }

    public PublisherDataModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDataModel publisher) {
        this.publisher = publisher;
    }

    public CategoryDataModel getCategoryDataModel() {
        return categoryDataModel;
    }

    public void setCategoryDataModel(CategoryDataModel categoryDataModel) {
        this.categoryDataModel = categoryDataModel;
    }

    public SubCategoryDataModel getSubCategoryDataModel() {
        return subCategoryDataModel;
    }

    public void setSubCategoryDataModel(SubCategoryDataModel subCategoryDataModel) {
        this.subCategoryDataModel = subCategoryDataModel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public ArrayList<ReviewDataModel> getComments() {
        return comments;
    }

    public void setComments(ArrayList<ReviewDataModel> comments) {
        this.comments = comments;
    }
}

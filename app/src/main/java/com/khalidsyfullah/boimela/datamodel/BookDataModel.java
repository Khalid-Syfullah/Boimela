package com.khalidsyfullah.boimela.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class BookDataModel{


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

    private int amount, discount, quantity, available, pages, review, type, progress;

    private String isBn, title, author, category, subcategory, genre,
            quality, fileName, bookUrl, audioUrl, phone,
            imageA, imageB, imageC;

    private ArrayList<BookDataModel> books;


    public BookDataModel(String image, String title, String category, String author, int rating, int review, int progress, String fileName, String bookUrl, String audioUrl) {
        this.image = image;
        this.title = title;
        this.category = category;
        this.author = author;
        this.rating = rating;
        this.review = review;
        this.progress = progress;
        this.fileName = fileName;
        this.bookUrl = bookUrl;
        this.audioUrl = audioUrl;
    }

    public BookDataModel(String image, String title, String category, String author, int rating, int review, String fileName, String bookUrl, String audioUrl) {
        this.image = image;
        this.title = title;
        this.category = category;
        this.author = author;
        this.rating = rating;
        this.review = review;
        this.fileName = fileName;
        this.bookUrl = bookUrl;
        this.audioUrl = audioUrl;
    }

    public BookDataModel(String _id, String image, String name, String category, String author, int rating, int numberOfRating) {
        this._id = _id;
        this.image = image;
        this.name = name;
        this.category = category;
        this.author = author;
        this.rating = rating;
        this.numberOfRating = numberOfRating;
    }

    public BookDataModel(String image, String title, String category, String author, int rating, int review) {
        this.image = image;
        this.title = title;
        this.category = category;
        this.author = author;
        this.rating = rating;
        this.review = review;
    }

    public BookDataModel(String _id, String title, String imageA, String imageB, String imageC, int type) {
        this._id = _id;
        this.title = title;
        this.imageA = imageA;
        this.imageB = imageB;
        this.imageC = imageC;
        this.type = type;
    }

    public BookDataModel(String title, String imageA, String imageB, String imageC, int type) {
        this.title = title;
        this.imageA = imageA;
        this.imageB = imageB;
        this.imageC = imageC;
        this.type = type;
    }

    public BookDataModel(String image, String title, String author, int type) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public BookDataModel(String name, String author, String phone) {
        this.name = name;
        this.author = author;
        this.phone = phone;
    }


    public BookDataModel(String category, String subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    public BookDataModel(String image) {
        this.image = image;
    }


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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


    public String getIsBn() {
        return isBn;
    }

    public void setIsBn(String isBn) {
        this.isBn = isBn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageA() {
        return imageA;
    }

    public void setImageA(String imageA) {
        this.imageA = imageA;
    }

    public String getImageB() {
        return imageB;
    }

    public void setImageB(String imageB) {
        this.imageB = imageB;
    }

    public String getImageC() {
        return imageC;
    }

    public void setImageC(String imageC) {
        this.imageC = imageC;
    }

    public ArrayList<BookDataModel> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookDataModel> books) {
        this.books = books;
    }
}

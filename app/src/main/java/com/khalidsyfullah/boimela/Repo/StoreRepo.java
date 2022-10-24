package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.allAuthors;
import static com.khalidsyfullah.boimela.global.StaticData.biographyBooks;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeriesBooks;
import static com.khalidsyfullah.boimela.global.StaticData.categories;
import static com.khalidsyfullah.boimela.global.StaticData.categoryBooks;
import static com.khalidsyfullah.boimela.global.StaticData.fictionBooks;
import static com.khalidsyfullah.boimela.global.StaticData.historyBooks;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirSmall;
import static com.khalidsyfullah.boimela.global.StaticData.nonFictionBooks;
import static com.khalidsyfullah.boimela.global.StaticData.novelBooks;
import static com.khalidsyfullah.boimela.global.StaticData.poetryBooks;
import static com.khalidsyfullah.boimela.global.StaticData.popularAuthors;
import static com.khalidsyfullah.boimela.global.StaticData.popularBooks;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeries;
import static com.khalidsyfullah.boimela.global.StaticData.editorsChoiceBooks;
import static com.khalidsyfullah.boimela.global.StaticData.newReleasedBooks;
import static com.khalidsyfullah.boimela.global.StaticData.allPublishers;
import static com.khalidsyfullah.boimela.global.StaticData.religiousBooks;
import static com.khalidsyfullah.boimela.global.StaticData.scienceFictionBooks;
import static com.khalidsyfullah.boimela.global.StaticData.shortStoryBooks;
import static com.khalidsyfullah.boimela.global.StaticData.topBooks;
import static com.khalidsyfullah.boimela.global.StaticData.topAuthors;
import static com.khalidsyfullah.boimela.global.StaticData.topPublishers;
import static com.khalidsyfullah.boimela.global.StaticData.translatedBooks;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookCategoryDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGenreDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGenreItemDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesCountDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesItemDataModel;
import com.khalidsyfullah.boimela.datamodel.CategoryBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.CategoryDataModel;
import com.khalidsyfullah.boimela.datamodel.CollectionDataModel;
import com.khalidsyfullah.boimela.datamodel.HomeDataModel;
import com.khalidsyfullah.boimela.datamodel.PopularAuthorsDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherCountDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.PublishersDataModel;
import com.khalidsyfullah.boimela.datamodel.TopListDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreRepo {
    private final Application application;
    private RestAPI restAPI;


    public StoreRepo(Application application) {

        this.application = application;
        restAPI = RetrofitClient.createRetrofitClient();


        fetchPopularAuthors();
        fetchPopularBooks();
        fetchBookCollections();
        fetchBookSeries();
        fetchEditorsChoiceBooks();
        fetchNewReleasesBooks();
        fetchAllPublishers();
        fetchTopBooks();
        fetchTopAuthors();
        fetchTopPublishers();
        fetchCategories();

    }


    private void fetchPopularAuthors() {

        Log.d("StoreRoutes","Fetching Popular Authors....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<PopularAuthorsDataModel> booksCall = restAPI.getPopularAuthors();

        booksCall.enqueue(new Callback<PopularAuthorsDataModel>() {
            @Override
            public void onResponse(Call<PopularAuthorsDataModel> call, Response<PopularAuthorsDataModel> response) {

                if(response.code() == 200){

                    PopularAuthorsDataModel popularAuthorsDataModel = response.body();

                    ArrayList<AuthorDataModel> authorDataModels = popularAuthorsDataModel.getPopularAuthors();
                    if(authorDataModels.size() != 0) {

                        Log.d("StoreRoutes", "Message (Popular Authors): " + response.body().getMessage());
                        Log.d("StoreRoutes", "Author Name: " + authorDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Author Description: " + authorDataModels.get(0).getDescription());

                        popularAuthors.setValue(authorDataModels);
                    }

                    else{
                        Log.d("StoreRoutes","No Popular Authors found...");

                    }
                }
                else{
                    Log.d("StoreRoutes","Popular Authors Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<PopularAuthorsDataModel> call, Throwable t) {
                Log.d("StoreRoutes","No response from server...");

            }
        });

    }

    private void fetchPopularBooks() {

        Log.d("StoreRoutes","Fetching Popular Books....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[0]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){


                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("StoreRoutes","Message (Popular Books): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        popularBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("StoreRoutes","No Popular Books found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Popular Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("StoreRoutes","No response from server...");

            }
        });

    }

    private void fetchBookCollections() {

        Log.d("StoreRoutes","Fetching Book Collections....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<BookGenreDataModel> booksCall = restAPI.getGenreBooks();

        booksCall.enqueue(new Callback<BookGenreDataModel>() {
            @Override
            public void onResponse(Call<BookGenreDataModel> call, Response<BookGenreDataModel> response) {

                if(response.code() == 200){

                    ArrayList<BookGenreItemDataModel> bookGenreItemDataModels = response.body().getBookCollections();

                    if(bookGenreItemDataModels.size() != 0) {

                        ArrayList<BookDataModel> translatedBooksDataModels = bookGenreItemDataModels.get(0).getBooks();
                        ArrayList<BookDataModel> scienceFictionDataModels = bookGenreItemDataModels.get(1).getBooks();
                        ArrayList<BookDataModel> nonFictionDataModels = bookGenreItemDataModels.get(2).getBooks();
                        ArrayList<BookDataModel> religiousBooksDataModels = bookGenreItemDataModels.get(3).getBooks();
                        ArrayList<BookDataModel> poetryBooksDataModels = bookGenreItemDataModels.get(4).getBooks();
                        ArrayList<BookDataModel> novelBooksDataModels = bookGenreItemDataModels.get(5).getBooks();
                        ArrayList<BookDataModel> shortStoriesDataModels = bookGenreItemDataModels.get(6).getBooks();
                        ArrayList<BookDataModel> biographyDataModels = bookGenreItemDataModels.get(7).getBooks();
                        ArrayList<BookDataModel> fictionBookDataModels = bookGenreItemDataModels.get(8).getBooks();
                        ArrayList<BookDataModel> historyBookDataModels = bookGenreItemDataModels.get(9).getBooks();

                        Log.d("StoreRoutes","Message (Book Collections): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Genre Name: " + bookGenreItemDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Genre DataModel Size: " + bookGenreItemDataModels.size());

                        translatedBooks.setValue(translatedBooksDataModels);
                        scienceFictionBooks.setValue(scienceFictionDataModels);
                        nonFictionBooks.setValue(nonFictionDataModels);
                        religiousBooks.setValue(religiousBooksDataModels);
                        poetryBooks.setValue(poetryBooksDataModels);
                        novelBooks.setValue(novelBooksDataModels);
                        shortStoryBooks.setValue(shortStoriesDataModels);
                        biographyBooks.setValue(biographyDataModels);
                        fictionBooks.setValue(fictionBookDataModels);
                        historyBooks.setValue(historyBookDataModels);

                        if(translatedBooksDataModels.size() == 0){
                            Log.d("StoreRoutes","No Translated Books found...");
                        }
                        if(scienceFictionDataModels.size() == 0){
                            Log.d("StoreRoutes","No Science Fiction Books found...");
                        }
                        if(nonFictionDataModels.size() == 0){
                            Log.d("StoreRoutes","No Non-Fiction Books found...");
                        }
                        if(religiousBooksDataModels.size() == 0){
                            Log.d("StoreRoutes","No Religious Books found...");
                        }
                        if(poetryBooksDataModels.size() == 0){
                            Log.d("StoreRoutes","No Poetry Books found...");
                        }
                        if(novelBooksDataModels.size() == 0){
                            Log.d("StoreRoutes","No Novel Books found...");
                        }
                        if(shortStoriesDataModels.size() == 0){
                            Log.d("StoreRoutes","No Short Story Books found...");
                        }
                        if(biographyDataModels.size() == 0){
                            Log.d("StoreRoutes","No Biography Books found...");
                        }
                        if(fictionBookDataModels.size() == 0){
                            Log.d("StoreRoutes","No Fiction Books found...");
                        }
                        if(historyBookDataModels.size() == 0){
                            Log.d("StoreRoutes","No History Books found...");
                        }
                    }
                    else{
                        Log.d("StoreRoutes","No Book Collections found...");

                    }
                }

                else{
                    Log.d("StoreRoutes","Book Collections Response Error: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<BookGenreDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }


    private void fetchBookSeries() {

        Log.d("StoreRoutes","Fetching Book Series....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<BookSeriesDataModel> booksCall = restAPI.getBookSeries("1","100");

        booksCall.enqueue(new Callback<BookSeriesDataModel>() {
            @Override
            public void onResponse(Call<BookSeriesDataModel> call, Response<BookSeriesDataModel> response) {

                if(response.code() == 200){


                    BookSeriesDataModel bookSeriesDataModel = response.body();

                    BookSeriesCountDataModel bookSeriesCountDataModel = bookSeriesDataModel.getSeries();

                    ArrayList<BookSeriesItemDataModel> bookSeriesItemDataModels = bookSeriesCountDataModel.getItems();

                    if(bookSeriesItemDataModels.size() != 0) {

                        ArrayList<BookDataModel> bookSeriesDataModels = new ArrayList<>();

                        for(int i=0;i<bookSeriesItemDataModels.size();i++){

                            ArrayList<BookDataModel> bookDataModels = bookSeriesItemDataModels.get(i).getBooks();

                            if(bookDataModels.size() >= 3) {
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getId(), bookSeriesItemDataModels.get(i).getName(), imageDirSmall+ bookDataModels.get(0).getImage(), imageDirSmall+ bookDataModels.get(1).getImage(), imageDirSmall+ bookDataModels.get(2).getImage(), i);
                                bookSeriesDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 2){
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getId(), bookSeriesItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), imageDirSmall+bookDataModels.get(1).getImage(), null, i);
                                bookSeriesDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 1){
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getId(), bookSeriesItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), null, null, i);
                                bookSeriesDataModels.add(bookDataModel);

                            }
                            else{
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getId(), bookSeriesItemDataModels.get(i).getName(), null, null, null, i);
                                bookSeriesDataModels.add(bookDataModel);
                            }

                        }
                        Log.d("StoreRoutes","Message (Book Series): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Series Name: " + bookSeriesItemDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Series DataModel Size: " + bookSeriesDataModels.size());

                        bookSeries.setValue(bookSeriesDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Book Series found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Book Series Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<BookSeriesDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Book Series: No response from server...");

            }
        });

    }

    private void fetchBookSeriesBooks() {

        Log.d("StoreRoutes","Fetching Books from Book Series....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(CURRENT_BOOK_ID);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){


                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("StoreRoutes","Message (Books from Book Series): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        bookSeriesBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("StoreRoutes","No Books found from Book Series...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Books from Book Series Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Books from Book Series: No response from server...");

            }
        });

    }

    private void fetchEditorsChoiceBooks() {

        Log.d("StoreRoutes","Fetching Editors' Choice Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[4]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("StoreRoutes", "Message (Editors' Choice Books): " + response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        editorsChoiceBooks.setValue(collectionDataModel.getBooks());
                    }

                    else{
                        Log.d("StoreRoutes","No Editors' Choice Books found...");
                    }

                }
                else{
                    Log.d("StoreRoutes","Editors' Choice Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("StoreRoutes","No response from server...");

            }
        });

    }

    private void fetchNewReleasesBooks() {

        Log.d("StoreRoutes","Fetching Newly Released Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[5]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        ArrayList<BookDataModel> bookDataModels = collectionDataModel.getBooks();
                        ArrayList<BookDataModel> newReleasedBookDataModels = new ArrayList<>();

                        for (int i=0;i<bookDataModels.size();i++) {
                            BookDataModel bookDataModel = new BookDataModel(bookDataModels.get(i).getImage(), bookDataModels.get(i).getName(), bookDataModels.get(i).getWriter().getName(), (i+1)%10);
                            newReleasedBookDataModels.add(bookDataModel);
                        }

                        Log.d("StoreRoutes", "Message (Newly Released Books): " + response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        newReleasedBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("StoreRoutes","No Newly Released Books found...");

                    }
                }
                else{
                    Log.d("StoreRoutes","New Releases Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("StoreRoutes","No response from server...");

            }
        });

    }

    private void fetchAllPublishers() {

        Log.d("StoreRoutes","Fetching All Publishers....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<PublishersDataModel> booksCall = restAPI.getAllPublishers("1","100");

        booksCall.enqueue(new Callback<PublishersDataModel>() {
            @Override
            public void onResponse(Call<PublishersDataModel> call, Response<PublishersDataModel> response) {

                if(response.code() == 200){


                    PublishersDataModel publishersDataModel = response.body();

                    PublisherCountDataModel publisherCountDataModel = publishersDataModel.getPublishers();

                    ArrayList<PublisherDataModel> publisherDataModels = publisherCountDataModel.getItems();

                    if(publisherDataModels.size() != 0) {


                        Log.d("StoreRoutes","Message (All Publishers): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Publisher Name: " + publisherDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Publisher DataModel Size: " + publisherDataModels.size());

                        allPublishers.setValue(publisherDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","All Publishers: No Publisher found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","All Publishers Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<PublishersDataModel> call, Throwable t) {
                Log.d("StoreRoutes","All Publishers: No response from server...");

            }
        });

    }

    private void fetchTopBooks() {

        Log.d("StoreRoutes","Fetching Top Books....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<TopListDataModel> booksCall = restAPI.getTopList("true","false","false");

        booksCall.enqueue(new Callback<TopListDataModel>() {
            @Override
            public void onResponse(Call<TopListDataModel> call, Response<TopListDataModel> response) {

                if(response.code() == 200){


                    ArrayList<BookDataModel> bookDataModels = response.body().getBooks();

                    if(bookDataModels.size() != 0) {

                        Log.d("StoreRoutes","Message (Top Books): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + bookDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + bookDataModels.get(0).getWriter().getName());
                        Log.d("StoreRoutes", "Book DataModel Size: " + bookDataModels.size());

                        topBooks.setValue(bookDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Top Books found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Top Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<TopListDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Top Books: No response from server...");

            }
        });

    }

    private void fetchTopAuthors() {

        Log.d("StoreRoutes","Fetching Top Authors....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<TopListDataModel> booksCall = restAPI.getTopList("false","true","false");

        booksCall.enqueue(new Callback<TopListDataModel>() {
            @Override
            public void onResponse(Call<TopListDataModel> call, Response<TopListDataModel> response) {

                if(response.code() == 200){


                    ArrayList<AuthorDataModel> authorDataModels = response.body().getAuthors();

                    if(authorDataModels.size() != 0) {

                        Log.d("StoreRoutes","Message (Top Authors): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Author Name: " + authorDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Author DataModel Size: " + authorDataModels.size());

                        topAuthors.setValue(authorDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Top Authors found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Top Authors Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<TopListDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Top Authors: No response from server...");

            }
        });

    }

    private void fetchTopPublishers() {

        Log.d("StoreRoutes","Fetching Top Publishers....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<TopListDataModel> booksCall = restAPI.getTopList("false","false","true");

        booksCall.enqueue(new Callback<TopListDataModel>() {
            @Override
            public void onResponse(Call<TopListDataModel> call, Response<TopListDataModel> response) {

                if(response.code() == 200){


                    ArrayList<PublisherDataModel> publisherDataModels = response.body().getPublishers();

                    if(publisherDataModels.size() != 0) {

                        Log.d("StoreRoutes","Message (Top Publishers): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Publishers Name: " + publisherDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Publishers DataModel Size: " + publisherDataModels.size());

                        topPublishers.setValue(publisherDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Top Publishers found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Top Publishers Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<TopListDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Top Publishers: No response from server...");

            }
        });

    }

    private void fetchTopList() {

        Log.d("StoreRoutes","Fetching Top List....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<TopListDataModel> booksCall = restAPI.getTopList("true","true","true");

        booksCall.enqueue(new Callback<TopListDataModel>() {
            @Override
            public void onResponse(Call<TopListDataModel> call, Response<TopListDataModel> response) {

                if(response.code() == 200){


                    ArrayList<BookDataModel> bookDataModels = response.body().getBooks();
                    ArrayList<AuthorDataModel> authorDataModels = response.body().getAuthors();
                    ArrayList<PublisherDataModel> publisherDataModels = response.body().getPublishers();

                    if(bookDataModels.size() != 0) {

                        Log.d("StoreRoutes","Message (Top Books): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + bookDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + bookDataModels.get(0).getWriter().getName());

                        topBooks.setValue(bookDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Top Books found...");
                    }

                    if(authorDataModels.size() != 0) {

                        Log.d("StoreRoutes","Message (Top Authors): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Author Name: " + authorDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Author DataModel Size: " + authorDataModels.size());

                        topAuthors.setValue(authorDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Top Authors found...");
                    }

                    if(publisherDataModels.size() != 0) {

                        Log.d("StoreRoutes","Message (Top Publishers): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Publishers Name: " + publisherDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Publishers DataModel Size: " + publisherDataModels.size());

                        topPublishers.setValue(publisherDataModels);
                    }
                    else{
                        Log.d("StoreRoutes","No Top Publishers found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Top Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<TopListDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Top List: No response from server...");

            }
        });

    }

    private void fetchCategories() {

        Log.d("StoreRoutes","Fetching Categories....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<BookCategoryDataModel> booksCall = restAPI.getAllCategories();

        booksCall.enqueue(new Callback<BookCategoryDataModel>() {
            @Override
            public void onResponse(Call<BookCategoryDataModel> call, Response<BookCategoryDataModel> response) {

                if(response.code() == 200){

                    ArrayList<CategoryDataModel> categoryDataModels = response.body().getCategorys();

                    if(categoryDataModels.size() != 0) {

                        ArrayList<CategoryDataModel> categoryDataModels1 = new ArrayList<>();

                        for(int i=0;i<categoryDataModels.size();i++){

                            CategoryDataModel categoryDataModel = new CategoryDataModel(categoryDataModels.get(i).getId(), categoryDataModels.get(i).getCategoryName(), categoryDataModels.get(i).getCategoryImage(), i%10);
                            categoryDataModels1.add(categoryDataModel);
                        }

                        Log.d("StoreRoutes","Message (Categories): "+response.body().getMessage());
                        Log.d("StoreRoutes", "Category Name: " + categoryDataModels1.get(0).getCategoryName());
                        Log.d("StoreRoutes", "Category DataModel Size: " + categoryDataModels1.size());

                        categories.setValue(categoryDataModels1);
                    }
                    else{
                        Log.d("StoreRoutes","No Category found...");
                    }
                }
                else{
                    Log.d("StoreRoutes","Category Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<BookCategoryDataModel> call, Throwable t) {
                Log.d("StoreRoutes","Categories: No response from server...");

            }
        });

    }

    private void fetchCategoryBooks() {

        Log.d("StoreRoutes","Fetching Category Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<CategoryBooksDataModel> booksCall = restAPI.getCategoryBooksByID(CURRENT_BOOK_ID);

        booksCall.enqueue(new Callback<CategoryBooksDataModel>() {
            @Override
            public void onResponse(Call<CategoryBooksDataModel> call, Response<CategoryBooksDataModel> response) {

                if(response.code() == 200){

                    ArrayList<BookDataModel> bookDataModels = response.body().getBooks();

                    if(bookDataModels.size() != 0) {

                        Log.d("StoreRoutes", "Message (Category Books): " + response.body().getMessage());
                        Log.d("StoreRoutes", "Book Name: " + bookDataModels.get(0).getName());
                        Log.d("StoreRoutes", "Author Name: " + bookDataModels.get(0).getWriter().getName());

                        categoryBooks.setValue(bookDataModels);
                    }

                    else{
                        Log.d("StoreRoutes","No Category Books found...");
                    }

                }
                else{
                    Log.d("StoreRoutes","Category Books Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<CategoryBooksDataModel> call, Throwable t) {
                Log.d("StoreRoutes","No response from server...");

            }
        });

    }


    public MutableLiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return popularBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getNonFictionBooks() {
        return nonFictionBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getFictionBooks() {
        return fictionBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getShortStoryBooks() {
        return shortStoryBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBiographyBooks() {
        return biographyBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getReligiousBooks() {
        return religiousBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getPoetryBooks() {
        return poetryBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBookSeries() {
        return bookSeries;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBooksOfBookSeries() {

        fetchBookSeriesBooks();
        return bookSeriesBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getEditorsChoiceBooks() {
        return editorsChoiceBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getNewReleasedBooks() {
        return newReleasedBooks;
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getPopularAuthors() {
        return popularAuthors;
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getAllAuthors() {
        return allAuthors;
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getAllPublishers() {
        return allPublishers;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getTopBooks() {
        return topBooks;
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getTopAuthors() {
        return topAuthors;
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getTopPublishers() {
        return topPublishers;
    }

    public MutableLiveData<ArrayList<CategoryDataModel>> getCategories() {
        return categories;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getCategoryBooks() {

        fetchCategoryBooks();
        return categoryBooks;
    }


}

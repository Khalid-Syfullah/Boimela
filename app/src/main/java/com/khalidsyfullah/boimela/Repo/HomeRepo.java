package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.allAuthors;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeriesBooks;
import static com.khalidsyfullah.boimela.global.StaticData.genreBooks;
import static com.khalidsyfullah.boimela.global.StaticData.homeRouteIDs;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirSmall;
import static com.khalidsyfullah.boimela.global.StaticData.weeklyBooks;
import static com.khalidsyfullah.boimela.global.StaticData.bestSellerBooks;
import static com.khalidsyfullah.boimela.global.StaticData.popularBooks;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeries;
import static com.khalidsyfullah.boimela.global.StaticData.audioBooks;
import static com.khalidsyfullah.boimela.global.StaticData.topRatedBooks;
import static com.khalidsyfullah.boimela.global.StaticData.genres;
import static com.khalidsyfullah.boimela.global.StaticData.editorsChoiceBooks;
import static com.khalidsyfullah.boimela.global.StaticData.newReleasedBooks;
import static com.khalidsyfullah.boimela.global.StaticData.upcomingBooks;
import static com.khalidsyfullah.boimela.global.StaticData.popularAuthors;
import static com.khalidsyfullah.boimela.global.StaticData.allPublishers;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AudioBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorCountDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGenreDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGenreItemDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesCountDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesItemDataModel;
import com.khalidsyfullah.boimela.datamodel.CollectionDataModel;
import com.khalidsyfullah.boimela.datamodel.HomeDataModel;
import com.khalidsyfullah.boimela.datamodel.PopularAuthorsDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherCountDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.PublishersDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeRepo {
    private final Application application;
    private RestAPI restAPI;


    public HomeRepo(Application application) {

        this.application = application;
        restAPI = RetrofitClient.createRetrofitClient();
        fetchHomeRoutes();
        fetchWeeklyBooks();
        fetchBestSellerBooks();
        fetchPopularBooks();
        fetchBookSeries();
        fetchAudioBooks();
        fetchTopRatedBooks();
        fetchGenres();
        fetchEditorsChoiceBooks();
        fetchNewReleasesBooks();
        fetchAllAuthors();
        fetchUpcomingBooks();
        fetchAllPublishers();

    }

    private void fetchHomeRoutes(){
        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> call = restAPI.getHomeRoutes();
        call.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                HomeDataModel homeDataModel = response.body();
                if(response.isSuccessful()){
                    Log.d("HomeRoutes","message: "+homeDataModel.getMessage());

                    homeRouteIDs = homeDataModel.getHome();
                    for(int i=0;i<homeRouteIDs.length;i++){
                        Log.d("HomeRoutes", "home: "+"#"+i+": "+homeRouteIDs[i]);
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {

            }
        });
    }

    private void fetchWeeklyBooks() {

        Log.d("HomeRoutes","Fetching Weekly Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[2]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes", "Message (Weekly Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        ArrayList<SliderDataModel> sliderDataModels = new ArrayList<>();
                        ArrayList<BookDataModel> weeklyBookDataModels = collectionDataModel.getBooks();
                        for(int i=0;i<weeklyBookDataModels.size();i++){

                            BookDataModel bookDataModel = weeklyBookDataModels.get(i);
                            SliderDataModel sliderDataModel = new SliderDataModel(bookDataModel.getName(), bookDataModel.getImage(), bookDataModel.get_id());
                            sliderDataModels.add(sliderDataModel);

                        }
                        weeklyBooks.setValue(sliderDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","No Weekly Books found...");

                    }
                }
                else{
                    Log.d("HomeRoutes","Weekly Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchBestSellerBooks() {

        Log.d("HomeRoutes","Fetching Best Seller Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[1]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes", "Message (Best Seller Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());
                        Log.d("HomeRoutes", "Book Rating: " + collectionDataModel.getBooks().get(0).getRating());

                        bestSellerBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("HomeRoutes","No Best Seller Books found...");

                    }
                }
                else{
                    Log.d("HomeRoutes","Best Seller Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchPopularBooks() {

        Log.d("HomeRoutes","Fetching Popular Books....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[0]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){


                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes","Message (Popular Books): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        popularBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("HomeRoutes","No Popular Books found...");
                    }
                }
                else{
                    Log.d("HomeRoutes","Popular Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchBookSeries() {

        Log.d("HomeRoutes","Fetching Book Series....");


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
                        Log.d("HomeRoutes","Message (Book Series): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Series Name: " + bookSeriesItemDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Series DataModel Size: " + bookSeriesDataModels.size());

                        bookSeries.setValue(bookSeriesDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","No Book Series found...");
                    }
                }
                else{
                    Log.d("HomeRoutes","Book Series Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<BookSeriesDataModel> call, Throwable t) {
                Log.d("HomeRoutes","Book Series: No response from server...");

            }
        });

    }

    private void fetchBookSeriesBooks() {

        Log.d("HomeRoutes","Fetching Books from Book Series....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(CURRENT_BOOK_ID);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){


                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes","Message (Books from Book Series): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        bookSeriesBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("HomeRoutes","No Books found from Book Series...");
                    }
                }
                else{
                    Log.d("HomeRoutes","Books from Book Series Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","Books from Book Series: No response from server...");

            }
        });

    }

    private void fetchAudioBooks() {

        Log.d("HomeRoutes","Fetching Audiobooks....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<AudioBooksDataModel> booksCall = restAPI.getAudioBooks();

        booksCall.enqueue(new Callback<AudioBooksDataModel>() {
            @Override
            public void onResponse(Call<AudioBooksDataModel> call, Response<AudioBooksDataModel> response) {

                if(response.code() == 200){


                    ArrayList<BookDataModel> bookDataModels = response.body().getAudioBooks();

                    if(bookDataModels.size() != 0) {

                        Log.d("HomeRoutes","Message (Popular Books): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + bookDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + bookDataModels.get(0).getWriter().getName());

                        audioBooks.setValue(bookDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","No Popular Books found...");
                    }
                }
                else{
                    Log.d("HomeRoutes","Audiobooks Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AudioBooksDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchTopRatedBooks() {

        Log.d("HomeRoutes","Fetching Top Rated Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[3]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        ArrayList<BookDataModel> bookDataModels = collectionDataModel.getBooks();
                        ArrayList<BookDataModel> topRatedBookDataModels = new ArrayList<>();

                        for (int i=0;i<bookDataModels.size();i++) {
                            BookDataModel bookDataModel = new BookDataModel(bookDataModels.get(i).getImage(), bookDataModels.get(i).getName(), bookDataModels.get(i).getWriter().getName(), (i+1)%10);
                            topRatedBookDataModels.add(bookDataModel);
                        }
                        Log.d("HomeRoutes", "Message (Top Rated Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        topRatedBooks.setValue(topRatedBookDataModels);
                    }

                    else{
                        Log.d("HomeRoutes","No Top Rated books found...");

                    }
                }
                else{
                    Log.d("HomeRoutes","Top Rated Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchGenres() {

        Log.d("HomeRoutes","Fetching Genres....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<BookGenreDataModel> booksCall = restAPI.getGenreBooks();

        booksCall.enqueue(new Callback<BookGenreDataModel>() {
            @Override
            public void onResponse(Call<BookGenreDataModel> call, Response<BookGenreDataModel> response) {

                if(response.code() == 200){

                    ArrayList<BookGenreItemDataModel> bookGenreItemDataModels = response.body().getBookCollections();

                    if(bookGenreItemDataModels.size() != 0) {

                        ArrayList<BookDataModel> genreBooksDataModels = new ArrayList<>();

                        for(int i=0; i < bookGenreItemDataModels.size();i++){

                            ArrayList<BookDataModel> bookDataModels = bookGenreItemDataModels.get(i).getBooks();

                            if(bookDataModels.size() >= 3) {
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getId(), bookGenreItemDataModels.get(i).getName(), imageDirSmall+ bookDataModels.get(0).getImage(), imageDirSmall+ bookDataModels.get(1).getImage(), imageDirSmall+ bookDataModels.get(2).getImage(), i);
                                genreBooksDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 2){
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getId(), bookGenreItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), imageDirSmall+bookDataModels.get(1).getImage(), null, i);
                                genreBooksDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 1){
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getId(), bookGenreItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), null, null, i);
                                genreBooksDataModels.add(bookDataModel);

                            }
                            else{
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getId(), bookGenreItemDataModels.get(i).getName(), null, null, null, i);
                                genreBooksDataModels.add(bookDataModel);
                            }

                        }

                        Log.d("HomeRoutes","Message (Genres): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Genre Name: " + bookGenreItemDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Genre DataModel Size: " + bookGenreItemDataModels.size());

                        genres.setValue(genreBooksDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","No Genres found...");

                    }
                }

                else{
                    Log.d("HomeRoutes","Genres Response Error: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<BookGenreDataModel> call, Throwable t) {
                Log.d("HomeRoutes","Genres: No response from server...");

            }
        });

    }

    private void fetchGenreBooks() {

        Log.d("HomeRoutes","Fetching Genre Books....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(CURRENT_BOOK_ID);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){


                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes","Message (Genre Books): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        genreBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("HomeRoutes","Genre Books: No Books found from Genre...");
                    }
                }
                else{
                    Log.d("HomeRoutes","Genre Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","Genre Books: No response from server...");

            }
        });

    }

    private void fetchEditorsChoiceBooks() {

        Log.d("HomeRoutes","Fetching Editors' Choice Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[4]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes", "Message (Editors' Choice Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        editorsChoiceBooks.setValue(collectionDataModel.getBooks());
                    }

                    else{
                        Log.d("HomeRoutes","No Editors' Choice Books found...");
                    }

                }
                else{
                    Log.d("HomeRoutes","Editors' Choice Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchNewReleasesBooks() {

        Log.d("HomeRoutes","Fetching Newly Released Books....");

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

                        Log.d("HomeRoutes", "Message (Newly Released Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        newReleasedBooks.setValue(collectionDataModel.getBooks());
                    }
                    else{
                        Log.d("HomeRoutes","No Newly Released Books found...");

                    }
                }
                else{
                    Log.d("HomeRoutes","New Releases Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchPopularAuthors() {

        Log.d("HomeRoutes","Fetching Popular Authors....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<PopularAuthorsDataModel> booksCall = restAPI.getPopularAuthors();

        booksCall.enqueue(new Callback<PopularAuthorsDataModel>() {
            @Override
            public void onResponse(Call<PopularAuthorsDataModel> call, Response<PopularAuthorsDataModel> response) {

                if(response.code() == 200){

                    PopularAuthorsDataModel popularAuthorsDataModel = response.body();

                    ArrayList<AuthorDataModel> authorDataModels = popularAuthorsDataModel.getPopularAuthors();
                    if(authorDataModels.size() != 0) {

                        Log.d("HomeRoutes", "Message (Popular Authors): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Author Name: " + authorDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Author Description: " + authorDataModels.get(0).getDescription());

                        popularAuthors.setValue(authorDataModels);
                    }

                    else{
                        Log.d("HomeRoutes","No Popular Authors found...");

                    }
                }
                else{
                    Log.d("HomeRoutes","Popular Authors Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<PopularAuthorsDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchUpcomingBooks() {

        Log.d("HomeRoutes","Fetching Upcoming Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[6]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes", "Message (Upcoming Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());
                        Log.d("HomeRoutes", "Upcoming Books DataModel Size: " + collectionDataModel.getBooks().size());

                        upcomingBooks.setValue(collectionDataModel.getBooks());
                    }

                    else{
                        Log.d("HomeRoutes","No Upcoming Books found...");

                    }
                }
                else{
                    Log.d("HomeRoutes","Upcoming Books Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

            }
        });

    }

    private void fetchAllAuthors() {

        Log.d("HomeRoutes","Fetching All Authors....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<AuthorsDataModel> booksCall = restAPI.getAllAuthors("1","100");

        booksCall.enqueue(new Callback<AuthorsDataModel>() {
            @Override
            public void onResponse(Call<AuthorsDataModel> call, Response<AuthorsDataModel> response) {

                if(response.code() == 200){


                    AuthorsDataModel authorsDataModel = response.body();

                    AuthorCountDataModel authorCountDataModel = authorsDataModel.getAuthors();

                    ArrayList<AuthorDataModel> authorDataModels = authorCountDataModel.getAuthors();

                    if(authorDataModels.size() != 0) {


                        Log.d("HomeRoutes","Message (All Authors): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Author Name: " + authorDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Author DataModel Size: " + authorDataModels.size());

                        allAuthors.setValue(authorDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","All Authors: No Author found...");
                    }
                }
                else{
                    Log.d("HomeRoutes","All Authors Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthorsDataModel> call, Throwable t) {
                Log.d("HomeRoutes","All Authors: No response from server...");

            }
        });

    }

    private void fetchAllPublishers() {

        Log.d("HomeRoutes","Fetching All Publishers....");


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


                        Log.d("HomeRoutes","Message (All Publishers): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Publisher Name: " + publisherDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Publisher DataModel Size: " + publisherDataModels.size());

                        allPublishers.setValue(publisherDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","All Publishers: No Publisher found...");
                    }
                }
                else{
                    Log.d("HomeRoutes","All Publishers Response Error: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<PublishersDataModel> call, Throwable t) {
                Log.d("HomeRoutes","All Publishers: No response from server...");

            }
        });

    }

    public MutableLiveData<ArrayList<SliderDataModel>> getWeeklyBooks() {
        return weeklyBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBestSellerBooks() {
        return bestSellerBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return popularBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBookSeries() {
        return bookSeries;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBooksOfBookSeries() {

        fetchBookSeriesBooks();
        return bookSeriesBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getAudioBooks() {
        return audioBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getTopRatedBooks() {
        return topRatedBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getGenres() {
        return genres;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getGenreBooks() {

        fetchGenreBooks();
        return genreBooks;
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

    public MutableLiveData<ArrayList<BookDataModel>> getUpcomingBooks() {
        return upcomingBooks;
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getAllPublishers() {
        return allPublishers;
    }





}

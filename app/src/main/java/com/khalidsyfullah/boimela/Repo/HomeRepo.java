package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirSmall;
import static com.khalidsyfullah.boimela.global.StaticData.weeklyBooks;
import static com.khalidsyfullah.boimela.global.StaticData.bestSellerBooks;
import static com.khalidsyfullah.boimela.global.StaticData.popularBooks;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeries;
import static com.khalidsyfullah.boimela.global.StaticData.audioBooks;
import static com.khalidsyfullah.boimela.global.StaticData.topRatedBooks;
import static com.khalidsyfullah.boimela.global.StaticData.genreBooks;
import static com.khalidsyfullah.boimela.global.StaticData.editorsChoiceBooks;
import static com.khalidsyfullah.boimela.global.StaticData.newReleasedBooks;
import static com.khalidsyfullah.boimela.global.StaticData.upcomingBooks;
import static com.khalidsyfullah.boimela.global.StaticData.popularAuthors;
import static com.khalidsyfullah.boimela.global.StaticData.publishers;
import static com.khalidsyfullah.boimela.global.StaticData.reviews;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AudioBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
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
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
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
        fetchWeeklyBooks();
        fetchBestSellerBooks();
        fetchPopularBooks();
        fetchBookSeries();
        fetchAudioBooks();
        fetchTopRatedBooks();
        fetchGenreBooks();
        fetchEditorsChoiceBooks();
        fetchNewReleasesBooks();
        fetchPopularAuthors();
        fetchUpcomingBooks();
        fetchPublishers();

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
        Call<BookSeriesDataModel> booksCall = restAPI.getBookSeries();

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
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getName(), imageDirSmall+ bookDataModels.get(0).getImage(), imageDirSmall+ bookDataModels.get(1).getImage(), imageDirSmall+ bookDataModels.get(2).getImage(), i);
                                bookSeriesDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 2){
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), imageDirSmall+bookDataModels.get(1).getImage(), null, i);
                                bookSeriesDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 1){
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), null, null, i);
                                bookSeriesDataModels.add(bookDataModel);

                            }
                            else{
                                BookDataModel bookDataModel = new BookDataModel(bookSeriesItemDataModels.get(i).getName(), null, null, null, i);
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

    private void fetchGenreBooks() {

        Log.d("HomeRoutes","Fetching Genre Books....");

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
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getName(), imageDirSmall+ bookDataModels.get(0).getImage(), imageDirSmall+ bookDataModels.get(1).getImage(), imageDirSmall+ bookDataModels.get(2).getImage(), i);
                                genreBooksDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 2){
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), imageDirSmall+bookDataModels.get(1).getImage(), null, i);
                                genreBooksDataModels.add(bookDataModel);
                            }
                            else if(bookDataModels.size() == 1){
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getName(), imageDirSmall+bookDataModels.get(0).getImage(), null, null, i);
                                genreBooksDataModels.add(bookDataModel);

                            }
                            else{
                                BookDataModel bookDataModel = new BookDataModel(bookGenreItemDataModels.get(i).getName(), null, null, null, i);
                                genreBooksDataModels.add(bookDataModel);
                            }

                        }

                        Log.d("HomeRoutes","Message (Genre Books): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Genre Name: " + bookGenreItemDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Genre DataModel Size: " + bookGenreItemDataModels.size());

                        genreBooks.setValue(genreBooksDataModels);
                    }
                    else{
                        Log.d("HomeRoutes","No Genre Books found...");

                    }
                }

                else{
                    Log.d("HomeRoutes","Genre Books Response Error: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<BookGenreDataModel> call, Throwable t) {
                Log.d("HomeRoutes","No response from server...");

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

    private void fetchPublishers() {

        Log.d("HomeRoutes","Fetching Publishers....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<PublishersDataModel> booksCall = restAPI.getAllPublishers("1","5");

        booksCall.enqueue(new Callback<PublishersDataModel>() {
            @Override
            public void onResponse(Call<PublishersDataModel> call, Response<PublishersDataModel> response) {

                if(response.code() == 200){


                    PublishersDataModel publishersDataModel = response.body();

                    PublisherCountDataModel publisherCountDataModel = publishersDataModel.getPublishers();

                    ArrayList<PublisherDataModel> publisherDataModels = publisherCountDataModel.getItems();

                    if(publisherDataModels.size() != 0) {


                        Log.d("HomeRoutes","Message (Publishers): "+response.body().getMessage());
                        Log.d("HomeRoutes", "Publisher Name: " + publisherDataModels.get(0).getName());
                        Log.d("HomeRoutes", "Publisher DataModel Size: " + publisherDataModels.size());

                        publishers.setValue(publisherDataModels);
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
            public void onFailure(Call<PublishersDataModel> call, Throwable t) {
                Log.d("HomeRoutes","Book Series: No response from server...");

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

    public MutableLiveData<ArrayList<BookDataModel>> getAudioBooks() {
        return audioBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getTopRatedBooks() {
        return topRatedBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getGenreBooks() {
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

    public MutableLiveData<ArrayList<BookDataModel>> getUpcomingBooks() {
        return upcomingBooks;
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getPublishers() {
        return publishers;
    }


    public LiveData<ArrayList<ReviewDataModel>> fetchReviews(){

        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        Call getReviewCall = restAPI.getReviews(StaticData.accessToken.getValue());
        getReviewCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if(response.code() == 200){


                    ArrayList<ReviewDataModel> reviewDataModelsFetch = (ArrayList<ReviewDataModel>) response.body();
                    ArrayList<ReviewDataModel> reviewDataModels = new ArrayList<>();

                    if(reviewDataModelsFetch != null) {
                        for (int i = 0; i < reviewDataModelsFetch.size(); i++) {

                            ReviewDataModel reviewDataModel = reviewDataModelsFetch.get(i);

                            if (reviewDataModel.getBookId().equals(CURRENT_BOOK_ID)) {

                                reviewDataModel.setLike(reviewDataModel.getLikes().length);
                                reviewDataModel.setDislike(reviewDataModel.getDislikes().length);

                                reviewDataModels.add(reviewDataModel);
                            }

                        }
                    }

                    reviews.setValue(reviewDataModels);

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        return reviews;
    }

    public void setReviews(ArrayList<ReviewDataModel> reviews) {
        StaticData.reviews.setValue(reviews);
    }


}

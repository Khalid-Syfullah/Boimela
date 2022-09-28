package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AuthDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.CollectionDataModel;
import com.khalidsyfullah.boimela.datamodel.HomeDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RemoteRepo {
    private final Application application;
    private RestAPI restAPI;
    private MutableLiveData<ArrayList<SliderDataModel>> sliderData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ReviewDataModel>> reviews = new MutableLiveData<>();
    private MutableLiveData<ArrayList<BookDataModel>> bestSellerBooks = new MutableLiveData<>();
    private MutableLiveData<ArrayList<BookDataModel>> popularBooks = new MutableLiveData<>();

    public RemoteRepo(Application application) {

        this.application = application;
        restAPI = RetrofitClient.createRetrofitClient();
        fetchBestSellerBooks();
        fetchPopularBooks();

    }



    private void fetchBestSellerBooks() {

        Log.d("HomeRoutes","Fetching Best Seller Books....");

        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[0]);

        booksCall.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                if(response.code() == 200){

                    CollectionDataModel collectionDataModel = response.body().getCollectionDataModel();

                    if(collectionDataModel.getBooks().size() != 0) {

                        Log.d("HomeRoutes", "Message (Best Seller Books): " + response.body().getMessage());
                        Log.d("HomeRoutes", "Book Name: " + collectionDataModel.getBooks().get(0).getName());
                        Log.d("HomeRoutes", "Author Name: " + collectionDataModel.getBooks().get(0).getWriter().getName());

                        bestSellerBooks.setValue(collectionDataModel.getBooks());
                    }
                }
                else{
                    Log.d("HomeRoutes","No Best Seller Books found...");

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {

            }
        });

    }

    private void fetchPopularBooks() {

        Log.d("HomeRoutes","Fetching Popular Books....");


        restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> booksCall = restAPI.getCollectionByID(StaticData.homeRouteIDs[1]);

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

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {

            }
        });

    }


    public MutableLiveData<ArrayList<BookDataModel>> getBestSellerBooks() {
        return bestSellerBooks;
    }

    public MutableLiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return popularBooks;
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
        this.reviews.setValue(reviews);
    }


}

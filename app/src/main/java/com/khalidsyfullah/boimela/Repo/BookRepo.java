package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.bookDetails;
import static com.khalidsyfullah.boimela.global.StaticData.reviews;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGetDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepo {

    private final Application application;
    private RestAPI restAPI;

    public BookRepo(Application application) {
        this.application = application;
        this.restAPI = RetrofitClient.createRetrofitClient();
        
        fetchBookDetails();
    }

    private void fetchBookDetails() {

        Log.d("BookDetailsRoutes","Fetching Book Details....");
        Log.d("BookDetailsRoutes","CURRENT BOOK ID: "+CURRENT_BOOK_ID);

        restAPI = RetrofitClient.createRetrofitClient();
        Call<BookGetDetailsDataModel> booksCall = restAPI.getBookDetailsByID(CURRENT_BOOK_ID);

        booksCall.enqueue(new Callback<BookGetDetailsDataModel>() {
            @Override
            public void onResponse(Call<BookGetDetailsDataModel> call, Response<BookGetDetailsDataModel> response) {

                if(response.code() == 200){

                    BookDetailsDataModel bookDetailsDataModel = response.body().getBook();

                    if(bookDetailsDataModel != null) {

                        Log.d("BookDetailsRoutes", "Message (Book Details): " + response.body().getMessage());
                        Log.d("BookDetailsRoutes", "Book Name: " + bookDetailsDataModel.getName());
                        Log.d("BookDetailsRoutes", "Author Name: " + bookDetailsDataModel.getWriter().getName());
                        Log.d("BookDetailsRoutes", "Book Rating: " + bookDetailsDataModel.getRating());

                        bookDetails.setValue(bookDetailsDataModel);
                    }
                    else{
                        Log.d("BookDetailsRoutes","Book Details not found...");

                    }
                }
                else{
                    Log.d("BookDetailsRoutes","Book Details Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<BookGetDetailsDataModel> call, Throwable t) {
                Log.d("BookDetailsRoutes","No response from server: "+t.getMessage());

            }
        });

    }
    

    private LiveData<ArrayList<ReviewDataModel>> fetchReviews(){

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

    public MutableLiveData<BookDetailsDataModel> getBookDetails() {
        return bookDetails;
    }

    public MutableLiveData<ArrayList<ReviewDataModel>> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewDataModel> reviews) {
        StaticData.reviews.setValue(reviews);
    }
}

package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_AUTHOR_ID;
import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.authorDetails;
import static com.khalidsyfullah.boimela.global.StaticData.bookDetails;
import static com.khalidsyfullah.boimela.global.StaticData.reviews;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGetDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorRepo {

    private final Application application;
    private RestAPI restAPI;

    public AuthorRepo(Application application) {
        this.application = application;
        this.restAPI = RetrofitClient.createRetrofitClient();

    }

    private void fetchAuthorDetails() {

        Log.d("AuthorDetailsRoutes","Fetching Author Details....");
        Log.d("AuthorDetailsRoutes","CURRENT AUTHOR ID: "+CURRENT_AUTHOR_ID);

        restAPI = RetrofitClient.createRetrofitClient();
        Call<AuthorDetailsDataModel> booksCall = restAPI.getAuthorDetailsByID(CURRENT_AUTHOR_ID);

        booksCall.enqueue(new Callback<AuthorDetailsDataModel>() {
            @Override
            public void onResponse(Call<AuthorDetailsDataModel> call, Response<AuthorDetailsDataModel> response) {

                if(response.code() == 200){

                     AuthorDataModel authorDataModel = response.body().getAuthor();

                    if(authorDataModel != null) {

                        Log.d("AuthorDetailsRoutes", "Message (Author Details): " + response.body().getMessage());
                        Log.d("AuthorDetailsRoutes", "Author Name: " + authorDataModel.getName());
                        Log.d("AuthorDetailsRoutes", "Author Description: " + authorDataModel.getDescription());

                        authorDetails.setValue(authorDataModel);

                    }
                    else{
                        Log.d("AuthorDetailsRoutes","Author Details not found...");

                    }
                }
                else{
                    Log.d("AuthorDetailsRoutes","Author Details Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<AuthorDetailsDataModel> call, Throwable t) {
                Log.d("AuthorDetailsRoutes","No response from server: "+t.getMessage());

            }
        });

    }
    

    public MutableLiveData<AuthorDataModel> getAuthorDetails() {
        fetchAuthorDetails();
        return authorDetails;
    }



}

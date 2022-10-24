package com.khalidsyfullah.boimela.Repo;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_AUTHOR_ID;
import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_PUBLISHER_ID;
import static com.khalidsyfullah.boimela.global.StaticData.authorDetails;
import static com.khalidsyfullah.boimela.global.StaticData.publisherDetails;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDetailsDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublisherRepo {

    private final Application application;
    private RestAPI restAPI;

    public PublisherRepo(Application application) {
        this.application = application;
        this.restAPI = RetrofitClient.createRetrofitClient();

    }

    private void fetchPublisherDetails() {

        Log.d("PublisherDetailsRoutes","Fetching Publisher Details....");
        Log.d("PublisherDetailsRoutes","CURRENT PUBLISHER ID: "+CURRENT_PUBLISHER_ID);

        restAPI = RetrofitClient.createRetrofitClient();
        Call<PublisherDetailsDataModel> booksCall = restAPI.getPublisherDetailsByID(CURRENT_PUBLISHER_ID);

        booksCall.enqueue(new Callback<PublisherDetailsDataModel>() {
            @Override
            public void onResponse(Call<PublisherDetailsDataModel> call, Response<PublisherDetailsDataModel> response) {

                if(response.code() == 200){

                     PublisherDataModel publisherDataModel = response.body().getPublisher();

                    if(publisherDataModel != null) {

                        Log.d("PublisherDetailsRoutes", "Message (Publisher Details): " + response.body().getMessage());
                        Log.d("PublisherDetailsRoutes", "Publisher Name: " + publisherDataModel.getName());
                        Log.d("PublisherDetailsRoutes", "Publisher Location: " + publisherDataModel.getLocation());

                        publisherDetails.setValue(publisherDataModel);

                    }
                    else{
                        Log.d("PublisherDetailsRoutes","Publisher Details not found...");

                    }
                }
                else{
                    Log.d("PublisherDetailsRoutes","Publisher Details Response Error: "+response.code());

                }
            }

            @Override
            public void onFailure(Call<PublisherDetailsDataModel> call, Throwable t) {
                Log.d("PublisherDetailsRoutes","No response from server: "+t.getMessage());

            }
        });

    }
    

    public MutableLiveData<PublisherDataModel> getPublisherDetails() {
        fetchPublisherDetails();
        return publisherDetails;
    }



}

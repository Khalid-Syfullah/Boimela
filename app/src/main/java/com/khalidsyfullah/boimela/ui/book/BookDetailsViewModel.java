package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.khalidsyfullah.boimela.Repo.RemoteRepo;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsViewModel extends AndroidViewModel {

    RemoteRepo remoteRepo;

    public BookDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        remoteRepo = new RemoteRepo(application);
    }

    public LiveData<ArrayList<ReviewDataModel>> getReviews(){
        return remoteRepo.fetchReviews();
    }

}
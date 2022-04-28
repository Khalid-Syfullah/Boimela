package com.khalidsyfullah.boimela.ui.book;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ReviewDataModel>> reviews = new MutableLiveData<>();

    public BookDetailsViewModel() {

    }

}
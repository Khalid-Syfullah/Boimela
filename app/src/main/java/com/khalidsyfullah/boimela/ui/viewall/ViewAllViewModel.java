package com.khalidsyfullah.boimela.ui.viewall;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import java.util.ArrayList;

public class ViewAllViewModel extends AndroidViewModel {

    private HomeRepo homeRepo;

    public ViewAllViewModel(@NonNull Application application) {
        super(application);

        homeRepo = new HomeRepo(application);
    }

    public MutableLiveData<ArrayList<SliderDataModel>> getWeeklyBooks() {
        return homeRepo.getWeeklyBooks();
    }


}
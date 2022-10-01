package com.khalidsyfullah.boimela.ui.viewall;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;

import java.util.ArrayList;

public class ViewAllViewModel extends AndroidViewModel {

    private HomeRepo homeRepo;

    public ViewAllViewModel(@NonNull Application application) {
        super(application);

        homeRepo = new HomeRepo(application);
    }

    public LiveData<ArrayList<BookDataModel>> getBestSellerBooks() {
        return homeRepo.getBestSellerBooks();
    }

    public LiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return homeRepo.getPopularBooks();
    }

}
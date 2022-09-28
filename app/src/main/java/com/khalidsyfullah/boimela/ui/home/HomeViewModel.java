package com.khalidsyfullah.boimela.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khalidsyfullah.boimela.Repo.RemoteRepo;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    private RemoteRepo remoteRepo;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        remoteRepo = new RemoteRepo(application);
    }

    public LiveData<ArrayList<BookDataModel>> getBestSellerBooks() {
        return remoteRepo.getBestSellerBooks();
    }

    public LiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return remoteRepo.getPopularBooks();
    }

}
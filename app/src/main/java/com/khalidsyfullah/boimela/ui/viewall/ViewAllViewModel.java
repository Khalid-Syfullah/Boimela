package com.khalidsyfullah.boimela.ui.viewall;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalidsyfullah.boimela.Repo.RemoteRepo;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;

import java.util.ArrayList;

public class ViewAllViewModel extends AndroidViewModel {

    private RemoteRepo remoteRepo;

    public ViewAllViewModel(@NonNull Application application) {
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
package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalidsyfullah.boimela.Repo.BookRepo;
import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PublisherDetailsViewModel extends AndroidViewModel {

    BookRepo bookRepo;

    public PublisherDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        bookRepo = new BookRepo(application);
    }

    public LiveData<ArrayList<ReviewDataModel>> getReviews(){
        return bookRepo.getReviews();
    }

}
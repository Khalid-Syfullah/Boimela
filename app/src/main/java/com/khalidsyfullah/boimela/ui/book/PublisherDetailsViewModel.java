package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalidsyfullah.boimela.Repo.BookRepo;
import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.Repo.PublisherRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PublisherDetailsViewModel extends AndroidViewModel {

    PublisherRepo publisherRepo;

    public PublisherDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        publisherRepo = new PublisherRepo(application);
    }

    public LiveData<PublisherDataModel> getPublisherDetails(){
        return publisherRepo.getPublisherDetails();
    }
}
package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalidsyfullah.boimela.Repo.AuthorRepo;
import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;

import org.jetbrains.annotations.NotNull;

public class AuthorDetailsViewModel extends AndroidViewModel {

    AuthorRepo authorRepo;

    public AuthorDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        authorRepo = new AuthorRepo(application);
    }

    public LiveData<AuthorDataModel> getAuthorDetails(){
        return authorRepo.getAuthorDetails();
    }


}
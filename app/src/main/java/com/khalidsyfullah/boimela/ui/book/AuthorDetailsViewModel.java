package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.khalidsyfullah.boimela.Repo.HomeRepo;

import org.jetbrains.annotations.NotNull;

public class AuthorDetailsViewModel extends AndroidViewModel {

    HomeRepo homeRepo;

    public AuthorDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        homeRepo = new HomeRepo(application);
    }


}
package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalidsyfullah.boimela.Repo.RemoteRepo;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AuthorDetailsViewModel extends AndroidViewModel {

    RemoteRepo remoteRepo;

    public AuthorDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        remoteRepo = new RemoteRepo(application);
    }


}
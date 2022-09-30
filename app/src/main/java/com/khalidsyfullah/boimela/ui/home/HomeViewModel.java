package com.khalidsyfullah.boimela.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khalidsyfullah.boimela.Repo.RemoteRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    private RemoteRepo remoteRepo;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        remoteRepo = new RemoteRepo(application);
    }

    public MutableLiveData<ArrayList<SliderDataModel>> getWeeklyBooks() {
        return remoteRepo.getWeeklyBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBestSellerBooks() {
        return remoteRepo.getBestSellerBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return remoteRepo.getPopularBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBookSeries() {
        return remoteRepo.getBookSeries();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getAudioBooks() {
        return remoteRepo.getAudioBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getTopRatedBooks() {
        return remoteRepo.getTopRatedBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getGenreBooks() {
        return remoteRepo.getGenreBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getEditorsChoiceBooks() {
        return remoteRepo.getEditorsChoiceBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getNewReleasedBooks() {
        return remoteRepo.getNewReleasedBooks();
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getPopularAuthors() {
        return remoteRepo.getPopularAuthors();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getUpcomingBooks() {
        return remoteRepo.getUpcomingBooks();
    }

}
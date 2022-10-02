package com.khalidsyfullah.boimela.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    private HomeRepo homeRepo;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        homeRepo = new HomeRepo(application);
    }

    public MutableLiveData<ArrayList<SliderDataModel>> getWeeklyBooks() {
        return homeRepo.getWeeklyBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBestSellerBooks() {
        return homeRepo.getBestSellerBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getPopularBooks() {
        return homeRepo.getPopularBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBookSeries() {
        return homeRepo.getBookSeries();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBooksOfBookSeries() {
        return homeRepo.getBooksOfBookSeries();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getAudioBooks() {
        return homeRepo.getAudioBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getTopRatedBooks() {
        return homeRepo.getTopRatedBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getGenres() {
        return homeRepo.getGenres();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getGenreBooks() {
        return homeRepo.getGenreBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getEditorsChoiceBooks() {
        return homeRepo.getEditorsChoiceBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getNewReleasedBooks() {
        return homeRepo.getNewReleasedBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getUpcomingBooks() {
        return homeRepo.getUpcomingBooks();
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getPopularAuthors() {
        return homeRepo.getPopularAuthors();
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getAllAuthors() {
        return homeRepo.getAllAuthors();
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getAllPublishers() {
        return homeRepo.getAllPublishers();
    }



}
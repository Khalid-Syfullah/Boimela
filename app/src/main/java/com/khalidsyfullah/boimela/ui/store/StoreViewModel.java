package com.khalidsyfullah.boimela.ui.store;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.Repo.StoreRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.CategoryDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;

import java.util.ArrayList;

public class StoreViewModel extends AndroidViewModel {

    private StoreRepo storeRepo;

    public StoreViewModel(@NonNull Application application) {
        super(application);

        storeRepo = new StoreRepo(application);
    }


    public MutableLiveData<ArrayList<BookDataModel>> getNonFictionBooks() {
        return storeRepo.getNonFictionBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getFictionBooks() {
        return storeRepo.getFictionBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getShortStoryBooks() {
        return storeRepo.getShortStoryBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBiographyBooks() {
        return storeRepo.getBiographyBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getReligiousBooks() {
        return storeRepo.getReligiousBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getPoetryBooks() {
        return storeRepo.getPoetryBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBookSeries() {
        return storeRepo.getBookSeries();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getBooksOfBookSeries() {
        return storeRepo.getBooksOfBookSeries();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getEditorsChoiceBooks() {
        return storeRepo.getEditorsChoiceBooks();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getNewReleasedBooks() {
        return storeRepo.getNewReleasedBooks();
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getPopularAuthors() {
        return storeRepo.getPopularAuthors();
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getAllAuthors() {
        return storeRepo.getAllAuthors();
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getAllPublishers() {
        return storeRepo.getAllPublishers();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getTopBooks() {
        return storeRepo.getTopBooks();
    }

    public MutableLiveData<ArrayList<AuthorDataModel>> getTopAuthors() {
        return storeRepo.getTopAuthors();
    }

    public MutableLiveData<ArrayList<PublisherDataModel>> getTopPublishers() {
        return storeRepo.getTopPublishers();
    }

    public MutableLiveData<ArrayList<CategoryDataModel>> getCategories() {
        return storeRepo.getCategories();
    }

    public MutableLiveData<ArrayList<BookDataModel>> getCategoryBooks() {
        return storeRepo.getCategoryBooks();
    }


}
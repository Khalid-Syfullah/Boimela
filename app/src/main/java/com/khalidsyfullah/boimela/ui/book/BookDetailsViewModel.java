package com.khalidsyfullah.boimela.ui.book;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.khalidsyfullah.boimela.Repo.BookRepo;
import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BookDetailsViewModel extends AndroidViewModel {

    BookRepo bookRepo;

    public BookDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        bookRepo = new BookRepo(application);
    }

    public LiveData<BookDetailsDataModel> getBookDetails(){
        return bookRepo.getBookDetails();
    }

    public LiveData<ArrayList<ReviewDataModel>> getReviews(){
        return bookRepo.getReviews();
    }

}
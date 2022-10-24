package com.khalidsyfullah.boimela.ui.epub;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.khalidsyfullah.boimela.Repo.HomeRepo;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.ArrayList;

public class ReaderViewModel extends AndroidViewModel {


    public ReaderViewModel(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<String> getCurrentContentName(String currentContent) {

        StaticData.currentContentMutable.setValue(currentContent);
        return StaticData.currentContentMutable;
    }



}
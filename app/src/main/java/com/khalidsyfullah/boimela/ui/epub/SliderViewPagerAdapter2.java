package com.khalidsyfullah.boimela.ui.epub;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import java.util.ArrayList;

public class SliderViewPagerAdapter2 extends FragmentStatePagerAdapter {

    private ArrayList<SliderDataModel> sliderDataModels;

    public SliderViewPagerAdapter2(@NonNull FragmentManager fm, ArrayList<SliderDataModel> sliderDataModels) {
        super(fm);
        this.sliderDataModels = sliderDataModels;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();

        bundle.putString("title", sliderDataModels.get(position).getTitle());
        bundle.putString("imgUrl", sliderDataModels.get(position).getImgUrl());

        SliderFragment2 sliderFragment2 = new SliderFragment2();
        sliderFragment2.setArguments(bundle);
        return sliderFragment2;
    }

    @Override
    public int getCount() {
        return sliderDataModels.size();
    }



}




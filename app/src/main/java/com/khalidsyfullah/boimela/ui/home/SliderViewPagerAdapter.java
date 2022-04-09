package com.khalidsyfullah.boimela.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import java.util.ArrayList;

public class SliderViewPagerAdapter extends FragmentStatePagerAdapter {

    int NUM_PAGES = 0;
    ArrayList<SliderDataModel> sliderDataModels;

    public SliderViewPagerAdapter(FragmentManager fm, ArrayList<SliderDataModel> sliderDataModels) {
        super(fm);
        this.sliderDataModels = sliderDataModels;
        NUM_PAGES = sliderDataModels.size();
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();

        bundle.putString("title",sliderDataModels.get(position).getTitle());
        bundle.putString("imgUrl",sliderDataModels.get(position).getImgUrl());

        SliderFragment sliderFragment = new SliderFragment();
        sliderFragment.setArguments(bundle);
        return sliderFragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}


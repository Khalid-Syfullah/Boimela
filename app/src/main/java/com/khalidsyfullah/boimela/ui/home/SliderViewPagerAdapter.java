package com.khalidsyfullah.boimela.ui.home;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import java.util.ArrayList;

public class SliderViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<SliderDataModel> sliderDataModels;

    public SliderViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<SliderDataModel> sliderDataModels) {
        super(fm);
        this.sliderDataModels = sliderDataModels;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();

        bundle.putString("title", sliderDataModels.get(position).getTitle());
        bundle.putString("imgUrl", sliderDataModels.get(position).getImgUrl());

        SliderFragment sliderFragment = new SliderFragment();
        sliderFragment.setArguments(bundle);
        return sliderFragment;
    }

    @Override
    public int getCount() {
        return sliderDataModels.size();
    }



}


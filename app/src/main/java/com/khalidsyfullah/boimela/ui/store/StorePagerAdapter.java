package com.khalidsyfullah.boimela.ui.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StorePagerAdapter extends FragmentPagerAdapter {


    String [] page = null;
    public StorePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        page = new String[]{"Featured", "Top", "Genres"};

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new StoreViewPager1();
        }
        else if(position == 1){
            return new StoreViewPager2();
        }
        else{
            return new StoreViewPager3();

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return page[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}

package com.khalidsyfullah.boimela.ui.store;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khalidsyfullah.boimela.R;

public class StorePagerAdapter2 extends FragmentStatePagerAdapter {

    String [] page = null;
    public StorePagerAdapter2(@NonNull FragmentManager fm, Activity activity) {
        super(fm);
        page = new String[]{activity.getResources().getString(R.string.books), activity.getResources().getString(R.string.authors), activity.getResources().getString(R.string.publishers)};

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new StoreViewPager2A();
        }
        else if(position == 1){
            return new StoreViewPager2B();
        }
        else{
            return new StoreViewPager2C();

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

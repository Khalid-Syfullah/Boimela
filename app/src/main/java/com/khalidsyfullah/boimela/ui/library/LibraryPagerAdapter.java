package com.khalidsyfullah.boimela.ui.library;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.ui.store.StoreViewPager1;
import com.khalidsyfullah.boimela.ui.store.StoreViewPager2;
import com.khalidsyfullah.boimela.ui.store.StoreViewPager3;

public class LibraryPagerAdapter extends FragmentStatePagerAdapter {


    String [] page = null;
    public LibraryPagerAdapter(@NonNull FragmentManager fm, Activity activity) {
        super(fm);
        page = new String[]{activity.getResources().getString(R.string.now_reading), activity.getResources().getString(R.string.bookshelf)};

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new LibraryViewPager1();
        }
        else{
            return new LibraryViewPager2();

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return page[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}

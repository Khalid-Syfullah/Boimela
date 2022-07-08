package com.khalidsyfullah.boimela.ui.epub;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khalidsyfullah.boimela.R;

public class DrawerPagerAdapter extends FragmentStatePagerAdapter {

    String [] page = null;
    final int NUM_PAGES = 2;
    public DrawerPagerAdapter(@NonNull FragmentManager fm, Activity activity) {
        super(fm);
        page = new String[]{activity.getResources().getString(R.string.chapters), activity.getResources().getString(R.string.bookmarks)};
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {

            return new DrawerViewPager1();
        }
        else {
            return new DrawerViewPager2();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return page[position];
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}

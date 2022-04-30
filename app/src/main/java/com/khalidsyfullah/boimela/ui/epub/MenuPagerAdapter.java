package com.khalidsyfullah.boimela.ui.epub;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.ui.library.LibraryViewPager1;
import com.khalidsyfullah.boimela.ui.library.LibraryViewPager2;

public class MenuPagerAdapter extends FragmentStatePagerAdapter {

    final int NUM_PAGES = 6;
    public MenuPagerAdapter(@NonNull FragmentManager fm, Activity activity) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new FragmentViewPager1();
        else if(position == 1)
            return new FragmentViewPager2();
        else if(position == 2)
            return new FragmentViewPager3();
        else if(position == 3)
            return new FragmentViewPager4();
        else if(position == 4)
            return new FragmentViewPager5();
        else
            return new FragmentViewPager6();
    }


    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}

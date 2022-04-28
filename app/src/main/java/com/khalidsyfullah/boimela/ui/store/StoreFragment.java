package com.khalidsyfullah.boimela.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.khalidsyfullah.boimela.R;

public class StoreFragment extends Fragment {


    ViewPager viewPager;
    StorePagerAdapter storePagerAdapter;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_store, container, false);

        viewPager = root.findViewById(R.id.store_viewpager);
        tabLayout = root.findViewById(R.id.store_tab_layout);


        storePagerAdapter = new StorePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(storePagerAdapter);


        return root;
    }
}

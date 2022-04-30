package com.khalidsyfullah.boimela.ui.library;

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
import com.khalidsyfullah.boimela.ui.store.StorePagerAdapter;

public class LibraryFragment extends Fragment {

    ViewPager viewPager;
    LibraryPagerAdapter libraryPagerAdapter;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_library, container, false);

        viewPager = root.findViewById(R.id.library_viewpager);
        tabLayout = root.findViewById(R.id.library_tab_layout);
        libraryPagerAdapter = new LibraryPagerAdapter(getChildFragmentManager(), getActivity());
        viewPager.setAdapter(libraryPagerAdapter);

        return root;
    }
}

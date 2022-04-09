package com.khalidsyfullah.boimela.ui.epub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;

public class FragmentViewPager4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_4, container, false);
    }
}
package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.DrawerViewPager1.contentsAdapter;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.contentDataModels;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.ContentDataModel;
import com.khalidsyfullah.boimela.ui.home.BookScrollAdapter;
import com.khalidsyfullah.boimela.ui.library.BookAdapter2;

import java.util.ArrayList;

public class FragmentViewPager1 extends Fragment {

    public static RecyclerView contentRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_1, container, false);

        contentRecycler = root.findViewById(R.id.reader_1_recyclerview);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contentRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
//        contentsAdapter = new ContentsAdapter(getActivity(),ReaderActivity.contentDataModels);
        contentRecycler.setAdapter(contentsAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();



//
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                contentRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
//                contentsAdapter = new ContentsAdapter(getActivity(),ReaderActivity.contentDataModels);
//                contentRecycler.setAdapter(contentsAdapter);
//                Log.d("EPUB","Size: "+ReaderActivity.contentDataModels.size());
//            }
//        };
//
//        handler.postDelayed(runnable, 2000);


    }


}
package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.contentDataModels;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;

import java.util.ArrayList;

public class DrawerViewPager1 extends Fragment {

    public static RecyclerView drawerContentRecycler;
    public static ContentsAdapter contentsAdapter;

    private ImageView emptyImage;
    private TextView emptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(
                R.layout.viewpager_drawer_book_reader_1, container, false);

        drawerContentRecycler = root.findViewById(R.id.drawer_reader_1_recyclerview);
        emptyImage = root.findViewById(R.id.drawer_reader_1_empty_image);
        emptyText = root.findViewById(R.id.drawer_reader_1_empty_title);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drawerContentRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        drawerContentRecycler.setAdapter(contentsAdapter);
        Log.d("EPUB","Size: "+ReaderActivity.contentDataModels.size());



    }

    @Override
    public void onResume() {
        super.onResume();



//        contentDataModels = new ArrayList<>();
//
//        contentDataModels.add(new ContentDataModel("Contents","01",1));
//        contentDataModels.add(new ContentDataModel("Part 1","20",1));
//        contentDataModels.add(new ContentDataModel("1. Chapter 1","37",2));
//        contentDataModels.add(new ContentDataModel("2. Chapter 2","65",2));
//        contentDataModels.add(new ContentDataModel("Part 2","66",1));
//        contentDataModels.add(new ContentDataModel("3. Chapter 3","66",2));
//        contentDataModels.add(new ContentDataModel("4. Chapter 4","78",2));

//        if(ReaderActivity.contentDataModels.size() == 0){
//            emptyText.setVisibility(View.VISIBLE);
//            emptyImage.setVisibility(View.VISIBLE);
//            contentRecycler.setVisibility(View.GONE);
//        }
//        else{
//            emptyText.setVisibility(View.GONE);
//            emptyImage.setVisibility(View.GONE);
//            contentRecycler.setVisibility(View.VISIBLE);
//        }


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
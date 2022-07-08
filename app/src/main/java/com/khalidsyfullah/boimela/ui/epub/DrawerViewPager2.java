package com.khalidsyfullah.boimela.ui.epub;

import android.os.Bundle;
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

public class DrawerViewPager2 extends Fragment {

    public static RecyclerView bookmarkRecycler;
    public static BookmarkAdapter bookmarkAdapter;
    private ImageView emptyImage;
    private TextView emptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(
                R.layout.viewpager_drawer_book_reader_2, container, false);

        bookmarkRecycler = root.findViewById(R.id.drawer_reader_2_recyclerview);
        emptyImage = root.findViewById(R.id.drawer_reader_2_empty_image);
        emptyText = root.findViewById(R.id.drawer_reader_2_empty_title);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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

        if(ReaderActivity.bookmarkDataModels.size() == 0){
            emptyText.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.VISIBLE);
            bookmarkRecycler.setVisibility(View.GONE);
        }
        else{
            emptyText.setVisibility(View.GONE);
            emptyImage.setVisibility(View.GONE);
            bookmarkRecycler.setVisibility(View.VISIBLE);
        }

        bookmarkRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        bookmarkAdapter = new BookmarkAdapter(getActivity(),ReaderActivity.bookmarkDataModels);
        bookmarkRecycler.setAdapter(bookmarkAdapter);
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
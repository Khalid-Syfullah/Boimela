package com.khalidsyfullah.boimela.ui.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.ui.home.AuthorAdapter;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;
import com.khalidsyfullah.boimela.ui.home.BookScrollAdapter;
import com.khalidsyfullah.boimela.ui.home.BookSeriesAdapter;
import com.khalidsyfullah.boimela.ui.home.TopRatedBookAdapter;

import java.util.ArrayList;

public class LibraryViewPager1 extends Fragment {

    private RecyclerView currentlyReadingRecycler, recentlyReadRecycler, recentlyReadRecycler2;
    private BookAdapter2 currentlyReadingAdapter;
    private BookScrollAdapter recentlyReadAdapter;
    private ArrayList<BookDataModel> currentlyReadingDataModels, recentlyReadDataModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_library_1, container, false);

        currentlyReadingRecycler = root.findViewById(R.id.library_1_currently_reading_recyclerview);
        recentlyReadRecycler = root.findViewById(R.id.library_1_recently_completed_recyclerview);
        recentlyReadRecycler2 = root.findViewById(R.id.library_1_recently_completed_recyclerview2);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        currentlyReadingDataModels = new ArrayList<>();
        recentlyReadDataModels = new ArrayList<>();

        currentlyReadingDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6, 42, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        currentlyReadingDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4.5F, 6, 25, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        currentlyReadingDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4.5F, 6, 68, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        recentlyReadDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6, 42, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        recentlyReadDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4.5F, 6, 25, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        recentlyReadDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4.5F, 6, 68, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        currentlyReadingRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        currentlyReadingAdapter = new BookAdapter2(getActivity(),currentlyReadingDataModels);
        currentlyReadingRecycler.setAdapter(currentlyReadingAdapter);

        recentlyReadRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        recentlyReadAdapter = new BookScrollAdapter(getActivity(),recentlyReadDataModels);
        recentlyReadRecycler.setAdapter(recentlyReadAdapter);


    }
}

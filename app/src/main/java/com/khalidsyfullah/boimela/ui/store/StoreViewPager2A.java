package com.khalidsyfullah.boimela.ui.store;

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
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;

import java.util.ArrayList;

public class StoreViewPager2A extends Fragment {

    private RecyclerView topBooksRecycler;
    private ArrayList<BookDataModel> bookDataModels;
    private TopBooksAdapter topBooksAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_store_2a, container, false);

        topBooksRecycler = root.findViewById(R.id.store_2a_recycler_view);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        bookDataModels = new ArrayList<>();

        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));


        topBooksRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        topBooksAdapter = new TopBooksAdapter(getActivity(),bookDataModels);
        topBooksRecycler.setAdapter(topBooksAdapter);
    }
}

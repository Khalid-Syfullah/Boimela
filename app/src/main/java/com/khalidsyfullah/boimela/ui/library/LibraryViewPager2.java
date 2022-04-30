package com.khalidsyfullah.boimela.ui.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

public class LibraryViewPager2 extends Fragment {

    private RecyclerView bookshelfRecycler;
    private BookshelfAdapter bookshelfAdapter;
    private ArrayList<BookDataModel> bookshelfDataModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_library_2, container, false);

        bookshelfRecycler = root.findViewById(R.id.library_2_bookshelf_recyclerview);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        bookshelfDataModels = new ArrayList<>();

        bookshelfDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        bookshelfDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
        bookshelfDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        bookshelfDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));


        bookshelfRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        bookshelfAdapter = new BookshelfAdapter(getActivity(),bookshelfDataModels);
        bookshelfRecycler.setAdapter(bookshelfAdapter);


    }
}

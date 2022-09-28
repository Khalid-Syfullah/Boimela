package com.khalidsyfullah.boimela.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;
import com.khalidsyfullah.boimela.ui.home.SliderViewPagerAdapter;
import com.khalidsyfullah.boimela.ui.search.SearchAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private EditText searchView;
    private TextView clearText;
    private ImageView filterSearchImage;
    private RecyclerView searchResultRecycler;
    private ArrayList<BookDataModel> bookDataModels;
    private SearchAdapter searchAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = root.findViewById(R.id.search_searchview);
        clearText = root.findViewById(R.id.search_clear_text);
        filterSearchImage = root.findViewById(R.id.search_filter_image);
        searchResultRecycler = root.findViewById(R.id.search_recycler);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookDataModels = new ArrayList<>();

        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        searchResultRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        searchAdapter = new SearchAdapter(getActivity(),bookDataModels);
        searchResultRecycler.setAdapter(searchAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

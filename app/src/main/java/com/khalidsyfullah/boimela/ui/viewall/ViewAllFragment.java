package com.khalidsyfullah.boimela.ui.viewall;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;

import java.util.ArrayList;

public class ViewAllFragment extends Fragment {

    private EditText searchView;
    private TextView clearText;
    private RecyclerView viewAllRecycler;
    private ArrayList<BookDataModel> bookDataModels;
    private ArrayList<AuthorDataModel> authorDataModels;
    private ArrayList<PublisherDataModel> publisherDataModels;
    private ViewAllBooksAdapter viewAllBooksAdapter;
    private ViewAllAuthorsAdapter viewAllAuthorsAdapter;
    private ViewAllPublishersAdapter viewAllPublishersAdapter;
    private ConstraintLayout viewAllEmptyConstraintLayout;
    private ProgressBar viewAllProgressBar;
    private String searchString="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_view_all, container, false);
        searchView = root.findViewById(R.id.view_all_searchview);
        clearText = root.findViewById(R.id.view_all_clear_text);
        viewAllRecycler = root.findViewById(R.id.view_all_recycler);
        viewAllEmptyConstraintLayout = root.findViewById(R.id.view_all_empty_container);
        viewAllProgressBar = root.findViewById(R.id.view_all_progress_bar);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookDataModels = new ArrayList<>();
        authorDataModels = new ArrayList<>();
        publisherDataModels = new ArrayList<>();

        int id = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(id);
        if (searchEditText != null) {
            searchEditText.setGravity(Gravity.CENTER);
            searchEditText.setBackground(null);

        }

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                searchString = editable.toString();

            }
        });

        Bundle bundle = getArguments();
        String type = null;

        if(bundle.getString("type") != null){

            type = bundle.getString("type");

            if (type.equals("books")) {

                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
                bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

                if(bookDataModels.size() == 0){
                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                    viewAllRecycler.setVisibility(View.GONE);
                }
                else{
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                    layoutManager.setGapStrategy(2);
                    viewAllRecycler.setLayoutManager(layoutManager);
                    viewAllRecycler.setHasFixedSize(true);
                    viewAllBooksAdapter = new ViewAllBooksAdapter(getActivity(), bookDataModels);
                    viewAllRecycler.setAdapter(viewAllBooksAdapter);
                }



            }
            else if (type.equals("authors")) {

                authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
                authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
                authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
                authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
                authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
                authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
                authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
                authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
                authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
                authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
                authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
                authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
                authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
                authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
                authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
                authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
                authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
                authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
                authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
                authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
                authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
                authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
                authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
                authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));

                if(authorDataModels.size() == 0){
                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                    viewAllRecycler.setVisibility(View.GONE);
                }
                else {
                    viewAllRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    viewAllAuthorsAdapter = new ViewAllAuthorsAdapter(getActivity(), authorDataModels);
                    viewAllRecycler.setAdapter(viewAllAuthorsAdapter);
                }
            }
            else if (type.equals("publishers")) {

                publisherDataModels.add(new PublisherDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
                publisherDataModels.add(new PublisherDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
                publisherDataModels.add(new PublisherDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
                publisherDataModels.add(new PublisherDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
                publisherDataModels.add(new PublisherDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
                publisherDataModels.add(new PublisherDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
                publisherDataModels.add(new PublisherDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
                publisherDataModels.add(new PublisherDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
                publisherDataModels.add(new PublisherDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
                publisherDataModels.add(new PublisherDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
                publisherDataModels.add(new PublisherDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
                publisherDataModels.add(new PublisherDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
                publisherDataModels.add(new PublisherDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
                publisherDataModels.add(new PublisherDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
                publisherDataModels.add(new PublisherDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
                publisherDataModels.add(new PublisherDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));

                if(publisherDataModels.size() == 0){
                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                    viewAllRecycler.setVisibility(View.GONE);
                }
                else {
                    viewAllRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    viewAllPublishersAdapter = new ViewAllPublishersAdapter(getActivity(), publisherDataModels);
                    viewAllRecycler.setAdapter(viewAllPublishersAdapter);
                }
            }
        }





    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

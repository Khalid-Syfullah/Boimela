package com.khalidsyfullah.boimela.ui.book;

import static com.khalidsyfullah.boimela.global.StaticData.bookUrl;
import static com.khalidsyfullah.boimela.global.StaticData.fileName;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.bookLanguage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.ui.epub.ReaderActivity;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorDetailsFragment extends Fragment {




    private TextView authorName, authorBirthday, authorLocation, authorBooksTitle, authorDescription;
    private ProgressBar authorDetailsProgress;
    private ImageView authorImage, backBtn;
    private RecyclerView authorRecycler;
    private AuthorDataModel authorDataModel;
    private ArrayList<BookDataModel> bookDataModels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_author_details,container,false);

        authorName = root.findViewById(R.id.author_details_title);
        authorImage = root.findViewById(R.id.author_details_image);
        authorBirthday = root.findViewById(R.id.author_details_birthday);
        authorLocation = root.findViewById(R.id.author_details_location);
        authorBooksTitle = root.findViewById(R.id.author_details_books_title);
        authorDescription = root.findViewById(R.id.author_details_full_details_text);
        authorRecycler = root.findViewById(R.id.author_details_books_recycler_view);
        backBtn = root.findViewById(R.id.author_details_back);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        authorBooksTitle.setText(getActivity().getResources().getString(R.string.books) + " ("+bookDataModels.size()+")");
        authorRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        BookAdapter bookAdapter = new BookAdapter(getActivity(), bookDataModels, 1, "AuthorDetailsFragment");
        authorRecycler.setAdapter(bookAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigateUp();
            }
        });

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }


}
package com.khalidsyfullah.boimela.ui.book;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_AUTHOR_ID;
import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.audioUrl;
import static com.khalidsyfullah.boimela.global.StaticData.authorDetails;
import static com.khalidsyfullah.boimela.global.StaticData.bookDetails;
import static com.khalidsyfullah.boimela.global.StaticData.bookUrl;
import static com.khalidsyfullah.boimela.global.StaticData.fileDir;
import static com.khalidsyfullah.boimela.global.StaticData.fileName;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirBig;
import static com.khalidsyfullah.boimela.global.StaticData.mediaUrl;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.bookLanguage;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.destination;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.ui.epub.ReaderActivity;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;
import com.squareup.picasso.Picasso;

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



    private AuthorDetailsViewModel authorDetailsViewModel;
    private TextView authorName, authorBirthday, authorLocation, authorBooksTitle, authorDescription;
    private ImageView authorImage, backBtn;
    private RecyclerView authorRecycler;
    private AuthorDataModel authorDataModel;
    private ArrayList<BookDataModel> bookDataModels;
    private ConstraintLayout authorDetailsConstraintLayout;
    private ProgressBar authorDetailsProgress;

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
        authorDetailsProgress = root.findViewById(R.id.author_details_progress_bar);
        authorDetailsConstraintLayout = root.findViewById(R.id.author_details_constraint_layout);

        authorDetailsViewModel = new ViewModelProvider(getActivity()).get(AuthorDetailsViewModel.class);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authorDetails = new MutableLiveData<>();

        authorDetailsProgress.setVisibility(View.VISIBLE);
        authorDetailsConstraintLayout.setVisibility(View.GONE);

//        bookDataModels = new ArrayList<>();
//
//        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigateUp();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();


        if(bundle.getString("author_id") != null) {

            CURRENT_AUTHOR_ID = bundle.getString("author_id");

            authorDetailsViewModel.getAuthorDetails().observe(getViewLifecycleOwner(), new Observer<AuthorDataModel>() {

                @Override
                public void onChanged(AuthorDataModel authorDataModel) {

                    authorDetailsProgress.setVisibility(View.GONE);
                    authorDetailsConstraintLayout.setVisibility(View.VISIBLE);

                    authorName.setText(authorDataModel.getName());
                    Picasso.get().load(imageDirBig + authorDataModel.getImage()).placeholder(R.drawable.book_not_found).into(authorImage);
                    authorBirthday.setText(String.valueOf(authorDataModel.getBirth() + " - " +authorDataModel.getDeath()));
                    authorLocation.setText(authorDataModel.getLocation());
                    authorDescription.setText(authorDataModel.getDescription());

                    if(authorDataModel.getBooks() != null){

                        authorBooksTitle.setText(getActivity().getResources().getString(R.string.books) + " ("+authorDataModel.getBooks().size()+")");

                        if(authorDataModel.getBooks().size() != 0){

                            Log.d("AuthorDetailsFragment","Author Books DataModel Size: "+authorDataModel.getBooks().size());

                            ArrayList<BookDataModel> authorBooks = new ArrayList<>();

                            for(int i=0;i<authorDataModel.getBooks().size();i++){
                                BookDataModel bookDataModel = new BookDataModel(authorDataModel.getBooks().get(i).get_id(), authorDataModel.getBooks().get(i).getImage(), authorDataModel.getBooks().get(i).getName(), authorDataModel.getBooks().get(i).getCategory(), authorName.getText().toString(), authorDataModel.getBooks().get(i).getRating(), authorDataModel.getBooks().get(i).getNumberOfRating());
                                authorBooks.add(bookDataModel);
                            }
                            authorRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
                            BookAdapter bookAdapter = new BookAdapter(getActivity(), authorBooks, 1, "AuthorDetailsFragment");
                            authorRecycler.setAdapter(bookAdapter);
                            authorRecycler.setVisibility(View.VISIBLE);

                        }
                        else{
                            authorRecycler.setVisibility(View.GONE);
                        }
                    }
                    else{
                        authorRecycler.setVisibility(View.GONE);
                    }

                }

            });

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }


}
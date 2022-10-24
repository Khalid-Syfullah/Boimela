package com.khalidsyfullah.boimela.ui.book;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_AUTHOR_ID;
import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_PUBLISHER_ID;
import static com.khalidsyfullah.boimela.global.StaticData.publisherDetails;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirBig;
import static com.khalidsyfullah.boimela.global.StaticData.publisherDetails;

import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.NavigableMap;

public class PublisherDetailsFragment extends Fragment {



    private PublisherDetailsViewModel publisherDetailsViewModel;
    private TextView publisherName, publisherLocation, publisherBooksTitle;
    private ProgressBar publisherDetailsProgress;
    private ImageView publisherImage, backBtn;
    private RecyclerView publisherRecycler;
    private ConstraintLayout publisherDetailsConstraintLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_publisher_details,container,false);

        publisherName = root.findViewById(R.id.publisher_details_title);
        publisherImage = root.findViewById(R.id.publisher_details_image);
        publisherLocation = root.findViewById(R.id.publisher_details_location);
        publisherRecycler = root.findViewById(R.id.publisher_details_books_recycler_view);
        publisherBooksTitle = root.findViewById(R.id.publisher_details_books_title);
        publisherDetailsProgress = root.findViewById(R.id.publisher_details_progress_bar);
        publisherDetailsConstraintLayout = root.findViewById(R.id.publisher_details_constraint_layout);
        backBtn = root.findViewById(R.id.publisher_details_back);
        
        publisherDetailsViewModel = new ViewModelProvider(getActivity()).get(PublisherDetailsViewModel.class);
        
        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        publisherDetails = new MutableLiveData<>();

        publisherDetailsProgress.setVisibility(View.VISIBLE);
        publisherDetailsConstraintLayout.setVisibility(View.GONE);
        
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

//        publisherBooksTitle.setText(getActivity().getResources().getString(R.string.books) + " ("+bookDataModels.size()+")");
//        publisherRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
//        BookAdapter bookAdapter = new BookAdapter(getActivity(), bookDataModels, 1, "publisherDetailsFragment");
//        publisherRecycler.setAdapter(bookAdapter);

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

        if(bundle.getString("publisher_id") != null) {

            CURRENT_PUBLISHER_ID = bundle.getString("publisher_id");

            publisherDetailsViewModel.getPublisherDetails().observe(getViewLifecycleOwner(), new Observer<PublisherDataModel>() {

                @Override
                public void onChanged(PublisherDataModel publisherDataModel) {

                    publisherDetailsProgress.setVisibility(View.GONE);
                    publisherDetailsConstraintLayout.setVisibility(View.VISIBLE);
            
                    publisherName.setText(publisherDataModel.getName());
                    Picasso.get().load(imageDirBig + publisherDataModel.getImage()).placeholder(R.drawable.book_not_found).into(publisherImage);
                    publisherLocation.setText(publisherDataModel.getLocation());

                    if(publisherDataModel.getBooks() != null){

                        publisherBooksTitle.setText(getActivity().getResources().getString(R.string.books) + " ("+publisherDataModel.getBooks().size()+")");

                        if(publisherDataModel.getBooks().size() != 0){

                            Log.d("PublisherDetailFragment","Publisher Books DataModel Size: "+publisherDataModel.getBooks().size());

                            ArrayList<BookDataModel> authorBooks = new ArrayList<>();

                            for(int i=0;i<publisherDataModel.getBooks().size();i++){

                                if(publisherDataModel.getBooks().get(i).getWriter() != null) {
                                    BookDataModel bookDataModel = new BookDataModel(publisherDataModel.getBooks().get(i).get_id(), publisherDataModel.getBooks().get(i).getImage(), publisherDataModel.getBooks().get(i).getName(), publisherDataModel.getBooks().get(i).getCategory(), publisherDataModel.getBooks().get(i).getWriter().getName(), publisherDataModel.getBooks().get(i).getRating(), publisherDataModel.getBooks().get(i).getNumberOfRating());
                                    authorBooks.add(bookDataModel);
                                }
                            }

                            publisherRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
                            BookAdapter bookAdapter = new BookAdapter(getActivity(), publisherDataModel.getBooks(), 1, "PublisherDetailsFragment");
                            publisherRecycler.setAdapter(bookAdapter);
                            publisherRecycler.setVisibility(View.VISIBLE);

                        }
                        else{
                            publisherRecycler.setVisibility(View.GONE);
                        }
                    }
                    else{
                        publisherRecycler.setVisibility(View.GONE);
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
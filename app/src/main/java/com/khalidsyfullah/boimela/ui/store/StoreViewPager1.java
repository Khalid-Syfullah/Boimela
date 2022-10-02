package com.khalidsyfullah.boimela.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.ui.home.AuthorAdapter;
import com.khalidsyfullah.boimela.ui.home.BookAdapter;
import com.khalidsyfullah.boimela.ui.home.BookGenreAdapter;
import com.khalidsyfullah.boimela.ui.home.BookScrollAdapter;
import com.khalidsyfullah.boimela.ui.home.BookSeriesAdapter;
import com.khalidsyfullah.boimela.ui.home.HomeViewModel;
import com.khalidsyfullah.boimela.ui.home.PublisherAdapter;
import com.khalidsyfullah.boimela.ui.home.SliderViewPagerAdapter;
import com.khalidsyfullah.boimela.ui.home.TopRatedBookAdapter;

import java.util.ArrayList;

public class StoreViewPager1 extends Fragment {

    private StoreViewModel storeViewModel;


    private TextView titlePopularAuthors, titleNonFiction, titleFiction, titleSeries, titleShortStories,
            titleBiography, titleReligious, titleNewReleases, titlePoetry, titleEditorsChoice, titlePublishers,
            viewAllPopularAuthors, viewAllNonFiction, viewAllFiction, viewAllSeries, viewAllShortStories,
            viewAllBiography, viewAllReligious, viewAllNewReleases, viewAllPoetry, viewAllEditorsChoice, viewAllPublishers;
    private RecyclerView popularAuthorRecycler, nonFictionRecycler, fictionRecycler, seriesRecycler,
            shortStoriesRecycler, biographyRecycler, religiousRecycler, newReleasesRecycler,
            poetryRecycler, editorsChoiceRecycler, publishersRecycler;

    private BookAdapter nonFictionAdapter, fictionAdapter, shortStoriesAdapter, biographyAdapter,
            religiousAdapter, poetryAdapter;
    private AuthorAdapter authorAdapter;
    private PublisherAdapter publisherAdapter;
    private BookSeriesAdapter seriesAdapter;
    private TopRatedBookAdapter newReleasesAdapter, editorsChoiceAdapter;
    private ArrayList<AuthorDataModel> popularAuthorDataModels;
    private ArrayList<PublisherDataModel> publisherDataModels;
    private ArrayList<BookDataModel> nonFictionDataModels, fictionDataModels, seriesDataModels,
            shortStoriesDataModels, biographyDataModels, religiousDataModels, newReleasesDataModels,
            poetryDataModels, editorsChoiceDataModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_store_1, container, false);

        titlePopularAuthors = root.findViewById(R.id.store_1_best_seller_title);
        titleNonFiction = root.findViewById(R.id.store_1_best_seller_title2);
        titleFiction = root.findViewById(R.id.store_1_best_seller_title3);
        titleSeries = root.findViewById(R.id.store_1_best_seller_title4);
        titleShortStories = root.findViewById(R.id.store_1_best_seller_title5);
        titleBiography = root.findViewById(R.id.store_1_best_seller_title6);
        titleReligious = root.findViewById(R.id.store_1_best_seller_title7);
        titleNewReleases = root.findViewById(R.id.store_1_best_seller_title8);
        titlePoetry = root.findViewById(R.id.store_1_best_seller_title9);
        titleEditorsChoice = root.findViewById(R.id.store_1_best_seller_title10);
        titlePublishers = root.findViewById(R.id.store_1_best_seller_title11);

        popularAuthorRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview);
        nonFictionRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview2);
        fictionRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview3);
        seriesRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview4);
        shortStoriesRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview5);
        biographyRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview6);
        religiousRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview7);
        newReleasesRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview8);
        poetryRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview9);
        editorsChoiceRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview10);
        publishersRecycler = root.findViewById(R.id.store_1_best_seller_recyclerview11);

        viewAllPopularAuthors = root.findViewById(R.id.store_1_best_seller_viewall);
        viewAllNonFiction = root.findViewById(R.id.store_1_best_seller_viewall2);
        viewAllFiction = root.findViewById(R.id.store_1_best_seller_viewall3);
        viewAllSeries = root.findViewById(R.id.store_1_best_seller_viewall4);
        viewAllShortStories = root.findViewById(R.id.store_1_best_seller_viewall5);
        viewAllBiography = root.findViewById(R.id.store_1_best_seller_viewall6);
        viewAllReligious = root.findViewById(R.id.store_1_best_seller_viewall7);
        viewAllNewReleases = root.findViewById(R.id.store_1_best_seller_viewall8);
        viewAllPoetry = root.findViewById(R.id.store_1_best_seller_viewall9);
        viewAllEditorsChoice = root.findViewById(R.id.store_1_best_seller_viewall10);
        viewAllPublishers = root.findViewById(R.id.store_1_best_seller_viewall11);

        storeViewModel = new ViewModelProvider(this).get(StoreViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        popularAuthorDataModels = new ArrayList<>();
        nonFictionDataModels = new ArrayList<>();
        fictionDataModels = new ArrayList<>();
        seriesDataModels = new ArrayList<>();
        shortStoriesDataModels = new ArrayList<>();
        biographyDataModels = new ArrayList<>();
        religiousDataModels = new ArrayList<>();
        newReleasesDataModels = new ArrayList<>();
        poetryDataModels = new ArrayList<>();
        editorsChoiceDataModels = new ArrayList<>();
        publisherDataModels = new ArrayList<>();

//        popularAuthorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//        popularAuthorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//        popularAuthorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        popularAuthorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//
//        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        nonFictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//
//        fictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

//        seriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        seriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        seriesDataModels.add(new BookDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        seriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        seriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        seriesDataModels.add(new BookDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        seriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        seriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        seriesDataModels.add(new BookDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//
//        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        shortStoriesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//
//        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        biographyDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//
//        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        religiousDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//
//        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//
//        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        poetryDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

//        publisherDataModels.add(new PublisherDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//        publisherDataModels.add(new PublisherDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//        publisherDataModels.add(new PublisherDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        publisherDataModels.add(new PublisherDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//

        popularAuthorRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        authorAdapter = new AuthorAdapter(getActivity(),popularAuthorDataModels, "StoreFragment");
        popularAuthorRecycler.setAdapter(authorAdapter);

        fictionRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        fictionAdapter = new BookAdapter(getActivity(),fictionDataModels,1, "StoreFragment");
        fictionRecycler.setAdapter(fictionAdapter);

        nonFictionRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        nonFictionAdapter = new BookAdapter(getActivity(),nonFictionDataModels, 1, "StoreFragment");
        nonFictionRecycler.setAdapter(nonFictionAdapter);

        seriesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        seriesAdapter = new BookSeriesAdapter(getActivity(),seriesDataModels,"StoreFragment");
        seriesRecycler.setAdapter(seriesAdapter);

        shortStoriesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        shortStoriesAdapter = new BookAdapter(getActivity(), shortStoriesDataModels, 1, "StoreFragment");
        shortStoriesRecycler.setAdapter(shortStoriesAdapter);

        biographyRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        biographyAdapter = new BookAdapter(getActivity(),biographyDataModels,1, "StoreFragment");
        biographyRecycler.setAdapter(biographyAdapter);

        religiousRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        religiousAdapter = new BookAdapter(getActivity(),religiousDataModels,1, "StoreFragment");
        religiousRecycler.setAdapter(religiousAdapter);

        newReleasesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        newReleasesAdapter = new TopRatedBookAdapter(getActivity(),newReleasesDataModels);
        newReleasesRecycler.setAdapter(newReleasesAdapter);

        poetryRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        poetryAdapter = new BookAdapter(getActivity(),poetryDataModels,1, "StoreFragment");
        poetryRecycler.setAdapter(poetryAdapter);

        editorsChoiceRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        editorsChoiceAdapter = new TopRatedBookAdapter(getActivity(),editorsChoiceDataModels);
        editorsChoiceRecycler.setAdapter(editorsChoiceAdapter);

        publishersRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        publisherAdapter = new PublisherAdapter(getActivity(),publisherDataModels, "StoreFragment");
        publishersRecycler.setAdapter(publisherAdapter);

        viewAllPopularAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","popular-authors");
                bundle.putString("type","authors");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllNonFiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","non-fiction");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllFiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","fiction");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","series");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllShortStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","short-stories");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllBiography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","biography");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllReligious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","religious");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllNewReleases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","new-releases");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllPoetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","poetry");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllEditorsChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","editors-choice");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
        viewAllPublishers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment","store");
                bundle.putString("query","publishers");
                bundle.putString("type","publishers");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });

        storeViewModel.getPopularAuthors().observe(getViewLifecycleOwner(), new Observer<ArrayList<AuthorDataModel>>() {
            @Override
            public void onChanged(ArrayList<AuthorDataModel> authorDataModels) {

                authorAdapter.setAuthorDataModels(authorDataModels);

                if(authorDataModels.size() != 0) {
                    titlePopularAuthors.setVisibility(View.VISIBLE);
                    popularAuthorRecycler.setVisibility(View.VISIBLE);
                    viewAllPopularAuthors.setVisibility(View.VISIBLE);
                }
                else{
                    titlePopularAuthors.setVisibility(View.GONE);
                    popularAuthorRecycler.setVisibility(View.GONE);
                    viewAllPopularAuthors.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getNonFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                nonFictionAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleNonFiction.setVisibility(View.VISIBLE);
                    nonFictionRecycler.setVisibility(View.VISIBLE);
                    viewAllNonFiction.setVisibility(View.VISIBLE);
                }
                else{
                    titleNonFiction.setVisibility(View.GONE);
                    nonFictionRecycler.setVisibility(View.GONE);
                    viewAllNonFiction.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                fictionAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleFiction.setVisibility(View.VISIBLE);
                    fictionRecycler.setVisibility(View.VISIBLE);
                    viewAllFiction.setVisibility(View.VISIBLE);
                }
                else{
                    titleFiction.setVisibility(View.GONE);
                    fictionRecycler.setVisibility(View.GONE);
                    viewAllFiction.setVisibility(View.GONE);
                }
            }
        });


        storeViewModel.getShortStoryBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                shortStoriesAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleShortStories.setVisibility(View.VISIBLE);
                    shortStoriesRecycler.setVisibility(View.VISIBLE);
                    viewAllShortStories.setVisibility(View.VISIBLE);
                }
                else{
                    titleShortStories.setVisibility(View.GONE);
                    shortStoriesRecycler.setVisibility(View.GONE);
                    viewAllShortStories.setVisibility(View.GONE);
                }
            }
        });


        storeViewModel.getBiographyBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                biographyAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleBiography.setVisibility(View.VISIBLE);
                    biographyRecycler.setVisibility(View.VISIBLE);
                    viewAllBiography.setVisibility(View.VISIBLE);
                }
                else{
                    titleBiography.setVisibility(View.GONE);
                    biographyRecycler.setVisibility(View.GONE);
                    viewAllBiography.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getReligiousBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                religiousAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleReligious.setVisibility(View.VISIBLE);
                    religiousRecycler.setVisibility(View.VISIBLE);
                    viewAllReligious.setVisibility(View.VISIBLE);
                }
                else{
                    titleReligious.setVisibility(View.GONE);
                    religiousRecycler.setVisibility(View.GONE);
                    viewAllReligious.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getPoetryBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                poetryAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titlePoetry.setVisibility(View.VISIBLE);
                    poetryRecycler.setVisibility(View.VISIBLE);
                    viewAllPoetry.setVisibility(View.VISIBLE);
                }
                else{
                    titlePoetry.setVisibility(View.GONE);
                    poetryRecycler.setVisibility(View.GONE);
                    viewAllPoetry.setVisibility(View.GONE);
                }
            }
        });


        storeViewModel.getBookSeries().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                seriesAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleSeries.setVisibility(View.VISIBLE);
                    seriesRecycler.setVisibility(View.VISIBLE);
                    viewAllSeries.setVisibility(View.VISIBLE);
                }
                else{
                    titleSeries.setVisibility(View.GONE);
                    seriesRecycler.setVisibility(View.GONE);
                    viewAllSeries.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getNewReleasedBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                newReleasesAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleNewReleases.setVisibility(View.VISIBLE);
                    newReleasesRecycler.setVisibility(View.VISIBLE);
                    viewAllNewReleases.setVisibility(View.VISIBLE);
                }
                else{
                    titleNewReleases.setVisibility(View.GONE);
                    newReleasesRecycler.setVisibility(View.GONE);
                    viewAllNewReleases.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getEditorsChoiceBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                editorsChoiceAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleEditorsChoice.setVisibility(View.VISIBLE);
                    editorsChoiceRecycler.setVisibility(View.VISIBLE);
                    viewAllEditorsChoice.setVisibility(View.VISIBLE);
                }
                else{
                    titleEditorsChoice.setVisibility(View.GONE);
                    editorsChoiceRecycler.setVisibility(View.GONE);
                    viewAllEditorsChoice.setVisibility(View.GONE);
                }
            }
        });

        storeViewModel.getAllPublishers().observe(getViewLifecycleOwner(), new Observer<ArrayList<PublisherDataModel>>() {
            @Override
            public void onChanged(ArrayList<PublisherDataModel> publisherDataModels) {

                publisherAdapter.setPublisherDataModels(publisherDataModels);

                if(publisherDataModels.size() != 0) {
                    titlePublishers.setVisibility(View.VISIBLE);
                    publishersRecycler.setVisibility(View.VISIBLE);
                    viewAllPublishers.setVisibility(View.VISIBLE);
                }
                else{
                    titlePublishers.setVisibility(View.GONE);
                    publishersRecycler.setVisibility(View.GONE);
                    viewAllPublishers.setVisibility(View.GONE);
                }
            }
        });




    }
}

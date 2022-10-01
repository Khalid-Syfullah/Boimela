package com.khalidsyfullah.boimela.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.ui.slider.SliderTimer;
import com.khalidsyfullah.boimela.ui.slider.SpeedSlowScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView titleBestSeller, titlePopularBooks, titleSeries, titleAudioBook, titleTopRated, titleGenres, titleEditorsChoice, titleNewReleases, titlePopularAuthors, titleUpcoming;
    private TextView viewAllBestSeller, viewAllPopularBooks, viewAllSeries, viewAllAudioBook, viewAllTopRated, viewAllGenres, viewAllEditorsChoice, viewAllNewReleases, viewAllPopularAuthors, viewAllUpcoming;
    private RecyclerView bestSellerRecycler, popularBooksRecycler, seriesRecycler, audiobookRecycler, topRatedRecycler, genresRecycler, editorsChoiceRecycler, newReleasesRecycler, popularAuthorsRecycler, upcomingRecycler1, upcomingRecycler2;
    private BookAdapter bestSellerAdapter, popularAdapter, audioBooksAdapter, editorsChoiceAdapter;
    private BookSeriesAdapter bookSeriesAdapter;
    private TopRatedBookAdapter topRatedBookAdapter, newReleasesAdapter;
    private BookGenreAdapter bookGenreAdapter;
    private AuthorAdapter popularAuthorsAdapter;
    private BookScrollAdapter bookScrollAdapterA, bookScrollAdapterB;
    private ArrayList<BookDataModel> bestSellerDataModels, popularDataModels, bookSeriesDataModels, topRatedBookDataModels, bookGenreDataModels, audioBooksDataModels, editorsChoiceDataModels, newReleaseDataModels, upcomingDataModelsA, upcomingDataModelsB;
    private ArrayList<SliderDataModel> sliderDataModels;
    private ArrayList<AuthorDataModel> authorDataModels;
    private ViewPager sliderViewPager;
    private SliderViewPagerAdapter sliderViewPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sliderViewPager = root.findViewById(R.id.home_top_selection_viewpager);
        bestSellerRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview);
        popularBooksRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview2);
        seriesRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview3);
        audiobookRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview4);
        topRatedRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview5);
        genresRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview6);
        editorsChoiceRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview7);
        newReleasesRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview8);
        popularAuthorsRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview9);
        upcomingRecycler1 = root.findViewById(R.id.dashboard_best_seller_recyclerview10);
        upcomingRecycler2 = root.findViewById(R.id.dashboard_best_seller_recyclerview11);

        titleBestSeller = root.findViewById(R.id.dashboard_best_seller_title);
        titlePopularBooks = root.findViewById(R.id.dashboard_best_seller_title2);
        titleSeries = root.findViewById(R.id.dashboard_best_seller_title3);
        titleAudioBook = root.findViewById(R.id.dashboard_best_seller_title4);;
        titleTopRated = root.findViewById(R.id.dashboard_best_seller_title5);
        titleGenres = root.findViewById(R.id.dashboard_best_seller_title6);
        titleEditorsChoice = root.findViewById(R.id.dashboard_best_seller_title7);
        titleNewReleases = root.findViewById(R.id.dashboard_best_seller_title8);
        titlePopularAuthors = root.findViewById(R.id.dashboard_best_seller_title9);
        titleUpcoming = root.findViewById(R.id.dashboard_best_seller_title10);

        viewAllBestSeller = root.findViewById(R.id.dashboard_best_seller_viewall);
        viewAllPopularBooks = root.findViewById(R.id.dashboard_best_seller_viewall2);
        viewAllSeries = root.findViewById(R.id.dashboard_best_seller_viewall3);
        viewAllAudioBook = root.findViewById(R.id.dashboard_best_seller_viewall4);;
        viewAllTopRated = root.findViewById(R.id.dashboard_best_seller_viewall5);
        viewAllGenres = root.findViewById(R.id.dashboard_best_seller_viewall6);
        viewAllEditorsChoice = root.findViewById(R.id.dashboard_best_seller_viewall7);
        viewAllNewReleases = root.findViewById(R.id.dashboard_best_seller_viewall8);
        viewAllPopularAuthors = root.findViewById(R.id.dashboard_best_seller_viewall9);
        viewAllUpcoming = root.findViewById(R.id.dashboard_best_seller_viewall10);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();

        checkForPermissions();


        sliderDataModels = new ArrayList<>();
        bestSellerDataModels = new ArrayList<>();
        popularDataModels = new ArrayList<>();
        bookSeriesDataModels = new ArrayList<>();
        topRatedBookDataModels = new ArrayList<>();
        bookGenreDataModels = new ArrayList<>();
        audioBooksDataModels = new ArrayList<>();
        newReleaseDataModels = new ArrayList<>();
        editorsChoiceDataModels = new ArrayList<>();
        authorDataModels = new ArrayList<>();
        upcomingDataModelsA = new ArrayList<>();
        upcomingDataModelsB = new ArrayList<>();

//        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
//        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));

//        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        bestSellerDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookSeriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 3));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 4));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 5));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookSeriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 3));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 4));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 5));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookSeriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 3));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 4));
//        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 5));
//
//        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//
//        bookGenreDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookGenreDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        bookGenreDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookGenreDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        bookGenreDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookGenreDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//        bookGenreDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
//        bookGenreDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
//
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//
//        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
//        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
//
//        authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//        authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//        authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
//        authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//        authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//        authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
//        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
//        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));


        sliderViewPagerAdapter = new SliderViewPagerAdapter(getChildFragmentManager(), sliderDataModels);
        sliderViewPager.setAdapter(sliderViewPagerAdapter);

        bestSellerRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        bestSellerAdapter = new BookAdapter(getActivity(),bestSellerDataModels,1, "HomeFragment");
        bestSellerRecycler.setAdapter(bestSellerAdapter);

        popularBooksRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        popularAdapter = new BookAdapter(getActivity(),popularDataModels,1, "HomeFragment");
        popularBooksRecycler.setAdapter(popularAdapter);

        seriesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        bookSeriesAdapter = new BookSeriesAdapter(getActivity(),bookSeriesDataModels);
        seriesRecycler.setAdapter(bookSeriesAdapter);

        topRatedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        topRatedBookAdapter = new TopRatedBookAdapter(getActivity(),topRatedBookDataModels);
        topRatedRecycler.setAdapter(topRatedBookAdapter);

        genresRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        bookGenreAdapter = new BookGenreAdapter(getActivity(),bookGenreDataModels);
        genresRecycler.setAdapter(bookGenreAdapter);

        audiobookRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        audioBooksAdapter = new BookAdapter(getActivity(),audioBooksDataModels,1, "HomeFragment");
        audiobookRecycler.setAdapter(audioBooksAdapter);

        editorsChoiceRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        editorsChoiceAdapter = new BookAdapter(getActivity(),editorsChoiceDataModels,1, "HomeFragment");
        editorsChoiceRecycler.setAdapter(editorsChoiceAdapter);

        newReleasesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        newReleasesAdapter = new TopRatedBookAdapter(getActivity(),newReleaseDataModels);
        newReleasesRecycler.setAdapter(newReleasesAdapter);

        popularAuthorsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        popularAuthorsAdapter = new AuthorAdapter(getActivity(),authorDataModels, "HomeFragment");
        popularAuthorsRecycler.setAdapter(popularAuthorsAdapter);

        LinearLayoutManager linearLayoutManagerA = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        upcomingRecycler1.setLayoutManager(linearLayoutManagerA);
        bookScrollAdapterA = new BookScrollAdapter(getActivity(),upcomingDataModelsA);
        upcomingRecycler1.setAdapter(bookScrollAdapterA);

        LinearLayoutManager linearLayoutManagerB = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        upcomingRecycler2.setLayoutManager(linearLayoutManagerB);
        bookScrollAdapterB = new BookScrollAdapter(getActivity(),upcomingDataModelsB);
        upcomingRecycler2.setAdapter(bookScrollAdapterB);


        viewAllBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","best-seller");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllPopularBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","popular-books");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","series");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllAudioBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","audiobooks");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","top-rated");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","genres");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllEditorsChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","editors-choice");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllNewReleases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","new-releases");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllPopularAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","popular-authors");
                bundle.putString("type","authors");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });

        viewAllUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment","home");
                bundle.putString("query","upcoming");
                bundle.putString("type","books");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
            }
        });


        homeViewModel.getWeeklyBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<SliderDataModel>>() {
            @Override
            public void onChanged(ArrayList<SliderDataModel> sliderDataModels) {

                sliderViewPagerAdapter.setSliderDataModels(sliderDataModels);

                if(sliderDataModels.size() != 0) {
                    sliderViewPager.setVisibility(View.VISIBLE);
                }
                else{
                    sliderViewPager.setVisibility(View.GONE);
                }
            }
        });


        homeViewModel.getBestSellerBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                bestSellerAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleBestSeller.setVisibility(View.VISIBLE);
                    bestSellerRecycler.setVisibility(View.VISIBLE);
                    viewAllBestSeller.setVisibility(View.VISIBLE);
                }
                else{
                    titleBestSeller.setVisibility(View.GONE);
                    bestSellerRecycler.setVisibility(View.GONE);
                    viewAllBestSeller.setVisibility(View.GONE);
                }
            }
        });

        homeViewModel.getPopularBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                popularAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titlePopularBooks.setVisibility(View.VISIBLE);
                    popularBooksRecycler.setVisibility(View.VISIBLE);
                    viewAllPopularBooks.setVisibility(View.VISIBLE);
                }
                else{
                    titlePopularBooks.setVisibility(View.GONE);
                    popularBooksRecycler.setVisibility(View.GONE);
                    viewAllPopularBooks.setVisibility(View.GONE);
                }
            }
        });

        homeViewModel.getBookSeries().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                bookSeriesAdapter.setBookDataModels(bookDataModels);

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



        homeViewModel.getAudioBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                audioBooksAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleAudioBook.setVisibility(View.VISIBLE);
                    audiobookRecycler.setVisibility(View.VISIBLE);
                    viewAllAudioBook.setVisibility(View.VISIBLE);
                }
                else{
                    titleAudioBook.setVisibility(View.GONE);
                    audiobookRecycler.setVisibility(View.GONE);
                    viewAllAudioBook.setVisibility(View.GONE);
                }
            }
        });

        homeViewModel.getTopRatedBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                topRatedBookAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleTopRated.setVisibility(View.VISIBLE);
                    topRatedRecycler.setVisibility(View.VISIBLE);
                    viewAllTopRated.setVisibility(View.VISIBLE);
                }
                else{
                    titleTopRated.setVisibility(View.GONE);
                    topRatedRecycler.setVisibility(View.GONE);
                    viewAllTopRated.setVisibility(View.GONE);
                }
            }
        });

        homeViewModel.getGenreBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                bookGenreAdapter.setBookDataModels(bookDataModels);

                if(bookDataModels.size() != 0) {
                    titleGenres.setVisibility(View.VISIBLE);
                    genresRecycler.setVisibility(View.VISIBLE);
                    viewAllGenres.setVisibility(View.VISIBLE);
                }
                else{
                    titleGenres.setVisibility(View.GONE);
                    genresRecycler.setVisibility(View.GONE);
                    viewAllGenres.setVisibility(View.GONE);
                }
            }
        });

        homeViewModel.getEditorsChoiceBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
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

        homeViewModel.getNewReleasedBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
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

        homeViewModel.getPopularAuthors().observe(getViewLifecycleOwner(), new Observer<ArrayList<AuthorDataModel>>() {
            @Override
            public void onChanged(ArrayList<AuthorDataModel> authorDataModels) {

                popularAuthorsAdapter.setAuthorDataModels(authorDataModels);

                if(authorDataModels.size() != 0) {
                    titlePopularAuthors.setVisibility(View.VISIBLE);
                    popularAuthorsRecycler.setVisibility(View.VISIBLE);
                    viewAllPopularAuthors.setVisibility(View.VISIBLE);
                }
                else{
                    titlePopularAuthors.setVisibility(View.GONE);
                    popularAuthorsRecycler.setVisibility(View.GONE);
                    viewAllPopularAuthors.setVisibility(View.GONE);
                }
            }
        });

        homeViewModel.getUpcomingBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
            @Override
            public void onChanged(ArrayList<BookDataModel> bookDataModels) {


                if(bookDataModels.size() != 0) {

                    for(int i=0;i<bookDataModels.size()/2;i++){
                        upcomingDataModelsA.add(bookDataModels.get(i));
                        bookScrollAdapterA.setBookDataModels(upcomingDataModelsA);

                    }
                    for(int i=bookDataModels.size()/2;i<bookDataModels.size();i++){
                        upcomingDataModelsB.add(bookDataModels.get(i));
                        bookScrollAdapterB.setBookDataModels(upcomingDataModelsB);

                    }
                    Log.d("HomeRoutes", "Upcoming DataModelA Size: " + upcomingDataModelsA.size());
                    Log.d("HomeRoutes", "Upcoming DataModelB Size: " + upcomingDataModelsB.size());

                    if(upcomingDataModelsA.size() != 0) {
                        upcomingRecycler1.setVisibility(View.VISIBLE);
                    }
                    else{
                        upcomingRecycler1.setVisibility(View.GONE);
                    }
                    if(upcomingDataModelsB.size() != 0) {
                        upcomingRecycler2.setVisibility(View.VISIBLE);
                    }
                    else{
                        upcomingRecycler2.setVisibility(View.GONE);
                    }

                    titleUpcoming.setVisibility(View.VISIBLE);
                    viewAllUpcoming.setVisibility(View.VISIBLE);
                }
                else{
                    titleUpcoming.setVisibility(View.GONE);
                    upcomingRecycler1.setVisibility(View.GONE);
                    upcomingRecycler2.setVisibility(View.GONE);
                    viewAllUpcoming.setVisibility(View.GONE);
                }
            }
        });





        Handler upcomingBooksHandlerA = new Handler(Looper.getMainLooper());

        Runnable runnable1 = new Runnable() {

            @Override
            public void run() {
                upcomingRecycler1.smoothScrollBy(8, 0);
                upcomingBooksHandlerA.postDelayed(this, 50);
            }
        };

        Handler upcomingBooksHandlerB = new Handler(Looper.getMainLooper());

        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                upcomingRecycler2.smoothScrollBy(10, 0);
                upcomingBooksHandlerB.postDelayed(this, 50);
            }
        };

        upcomingBooksHandlerA.postDelayed(runnable1, 2000);
        upcomingBooksHandlerB.postDelayed(runnable2, 0);

        upcomingRecycler1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = linearLayoutManagerA.findLastCompletelyVisibleItemPosition();
                if(lastItem == linearLayoutManagerA.getItemCount()-1){
                    upcomingBooksHandlerA.removeCallbacks(runnable1);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            upcomingRecycler1.setAdapter(null);
                            upcomingRecycler1.setAdapter(bookScrollAdapterA);
                            postHandler.post(runnable1);
                        }
                    }, 2000);
                }
            }
        });


        upcomingRecycler2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = linearLayoutManagerB.findLastCompletelyVisibleItemPosition();
                if(lastItem == linearLayoutManagerB.getItemCount()-1){
                    upcomingBooksHandlerB.removeCallbacks(runnable2);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            upcomingRecycler2.setAdapter(null);
                            upcomingRecycler2.setAdapter(bookScrollAdapterB);
                            postHandler.post(runnable2);
                        }
                    }, 2000);
                }
            }
        });



        final int paddingPx = 300;
        final float MIN_SCALE = 0.8f;
        final float MAX_SCALE = 1.0f;

        ViewPager.PageTransformer transformer = new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
                float pageWidthPx = pagerWidthPx - 2 * paddingPx;

                float maxVisiblePages = pagerWidthPx / pageWidthPx;
                float center = maxVisiblePages / 2f;

                float scale;
                if (position + 0.5f < center - 0.5f || position > center) {
                    scale = MIN_SCALE;
                } else {
                    float coef;
                    if (position + 0.5f < center) {
                        coef = (position + 1 - center) / 0.5f;
                    } else {
                        coef = (center - position) / 0.5f;
                    }
                    scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
                }
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
        };

        sliderViewPager.setPageTransformer(true, transformer);

        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            SpeedSlowScroller scroller = new SpeedSlowScroller(getActivity());
            mScroller.set(sliderViewPager, scroller);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new SliderTimer(sliderViewPager, sliderDataModels.size(), getActivity()), 4000, 6000);
        } catch (Exception ignored) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void checkForPermissions()
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                // This part I didn't implement,because for my case it isn't needed
                Log.i("Permissions","Unexpected flow");
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }


}
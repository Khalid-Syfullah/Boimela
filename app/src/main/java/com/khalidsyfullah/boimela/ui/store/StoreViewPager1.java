package com.khalidsyfullah.boimela.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.khalidsyfullah.boimela.ui.home.PublisherAdapter;
import com.khalidsyfullah.boimela.ui.home.SliderViewPagerAdapter;
import com.khalidsyfullah.boimela.ui.home.TopRatedBookAdapter;

import java.util.ArrayList;

public class StoreViewPager1 extends Fragment {

    private TextView viewAllPopularAuthors, viewAllNonFiction, viewAllFiction, viewAllSeries, viewAllShortStories,
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

        popularAuthorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
        popularAuthorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
        popularAuthorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        popularAuthorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));

        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        nonFictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        fictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        fictionDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        seriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        seriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
        seriesDataModels.add(new BookDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        seriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        seriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
        seriesDataModels.add(new BookDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        seriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        seriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
        seriesDataModels.add(new BookDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));


        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        shortStoriesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        biographyDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        religiousDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
        newReleasesDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));

        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "TinTin", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        poetryDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4, 6, "1984","https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

        publisherDataModels.add(new PublisherDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        publisherDataModels.add(new PublisherDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        publisherDataModels.add(new PublisherDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        publisherDataModels.add(new PublisherDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
        publisherDataModels.add(new PublisherDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        publisherDataModels.add(new PublisherDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        publisherDataModels.add(new PublisherDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        publisherDataModels.add(new PublisherDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
        publisherDataModels.add(new PublisherDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        publisherDataModels.add(new PublisherDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        publisherDataModels.add(new PublisherDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        publisherDataModels.add(new PublisherDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));


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
        seriesAdapter = new BookSeriesAdapter(getActivity(),seriesDataModels);
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


    }
}

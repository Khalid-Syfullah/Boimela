package com.khalidsyfullah.boimela.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.ui.epub.ReaderActivity;
import com.khalidsyfullah.boimela.ui.slider.SliderTimer;
import com.khalidsyfullah.boimela.ui.slider.SpeedSlowScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {


    private Button button;
    private RecyclerView bestSellerRecycler, popularRecycler, seriesRecycler, audiobookRecycler, topRatedRecycler, genresRecycler, editorsChoiceRecycler, newReleasesRecycler, popularAuthorsRecycler, upcomingRecycler1, upcomingRecycler2;
    private BookAdapter bestSellerAdapter, popularAdapter, audioBooksAdapter, editorsChoiceAdapter;
    private BookSeriesAdapter bookSeriesAdapter;
    private TopRatedBookAdapter topRatedBookAdapter, newReleasesAdapter;
    private BookGenreAdapter bookGenreAdapter;
    private AuthorAdapter authorAdapter;
    private BookScrollAdapter bookScrollAdapterA, bookScrollAdapterB;
    private ArrayList<BookDataModel> bestSellerDataModels, popularDataModels, bookSeriesDataModels, topRatedBookDataModels, bookGenreDataModels, audioBooksDataModels, editorsChoiceDataModels, newReleaseDataModels, upcomingDataModelsA, upcomingDataModelsB;
    private ArrayList<SliderDataModel> sliderDataModels;
    private ArrayList<AuthorDataModel> authorDataModels;
    private ViewPager sliderViewPager;
    private SliderViewPagerAdapter sliderViewPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        button = root.findViewById(R.id.home_navigate_button);
        sliderViewPager = root.findViewById(R.id.home_top_selection_viewpager);
        bestSellerRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview);
        popularRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview2);
        seriesRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview3);
        audiobookRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview4);
        topRatedRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview5);
        genresRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview6);
        editorsChoiceRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview7);
        newReleasesRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview8);
        popularAuthorsRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview9);
        upcomingRecycler1 = root.findViewById(R.id.dashboard_best_seller_recyclerview10);
        upcomingRecycler2 = root.findViewById(R.id.dashboard_best_seller_recyclerview11);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ReaderActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

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

        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));

        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));

        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        popularDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));

        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        audioBooksDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));

        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        bookSeriesDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));
        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 3));
        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 4));
        bookSeriesDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 5));

        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
        topRatedBookDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
        topRatedBookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));

        bookGenreDataModels.add(new BookDataModel("Feluda", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", 1));
        bookGenreDataModels.add(new BookDataModel("Kakababu", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", 2));

        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        editorsChoiceDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));

        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Satyagit Roy", 6));
        newReleaseDataModels.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Kakababu", "Satyagit Roy", 6));
        newReleaseDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Tintin", "Satyagit Roy", 6));

        authorDataModels.add(new AuthorDataModel("Zafor Iqbal","https://dailyasianage.com/library/1545504095_2.jpg"));
        authorDataModels.add(new AuthorDataModel("Anisul Hoque","https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
        authorDataModels.add(new AuthorDataModel("Rakib Hasan","https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
        authorDataModels.add(new AuthorDataModel("Selina Hossain","https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));

        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsA.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsA.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));

        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif"));
        upcomingDataModelsB.add(new BookDataModel("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg"));
        upcomingDataModelsB.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif"));


        sliderViewPagerAdapter = new SliderViewPagerAdapter(getChildFragmentManager(), sliderDataModels);
        sliderViewPager.setAdapter(sliderViewPagerAdapter);

        bestSellerRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        bestSellerAdapter = new BookAdapter(getActivity(),bestSellerDataModels,1);
        bestSellerRecycler.setAdapter(bestSellerAdapter);

        popularRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        popularAdapter = new BookAdapter(getActivity(),popularDataModels,1);
        popularRecycler.setAdapter(popularAdapter);

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
        audioBooksAdapter = new BookAdapter(getActivity(),audioBooksDataModels,1);
        audiobookRecycler.setAdapter(audioBooksAdapter);

        editorsChoiceRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        editorsChoiceAdapter = new BookAdapter(getActivity(),editorsChoiceDataModels,1);
        editorsChoiceRecycler.setAdapter(editorsChoiceAdapter);

        newReleasesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        newReleasesAdapter = new TopRatedBookAdapter(getActivity(),newReleaseDataModels);
        newReleasesRecycler.setAdapter(newReleasesAdapter);

        popularAuthorsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        authorAdapter = new AuthorAdapter(getActivity(),authorDataModels);
        popularAuthorsRecycler.setAdapter(authorAdapter);



        LinearLayoutManager linearLayoutManagerA = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        upcomingRecycler1.setLayoutManager(linearLayoutManagerA);
        bookScrollAdapterA = new BookScrollAdapter(getActivity(),upcomingDataModelsA);
        upcomingRecycler1.setAdapter(bookScrollAdapterA);

        LinearLayoutManager linearLayoutManagerB = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        upcomingRecycler2.setLayoutManager(linearLayoutManagerB);
        bookScrollAdapterB = new BookScrollAdapter(getActivity(),upcomingDataModelsB);
        upcomingRecycler2.setAdapter(bookScrollAdapterB);



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


}
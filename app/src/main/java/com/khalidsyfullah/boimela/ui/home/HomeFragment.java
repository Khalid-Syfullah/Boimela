package com.khalidsyfullah.boimela.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.ui.epub.BookReaderActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {


    Button button;
    private RecyclerView bestSellerRecycler;
    private BookAdapter bestSellerAdapter;
    private ArrayList<BookDataModel> bestSellerDataModels;
    private ArrayList<SliderDataModel> sliderDataModels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        button = root.findViewById(R.id.home_navigate_button);
        bestSellerRecycler = root.findViewById(R.id.dashboard_best_seller_recyclerview);
        ViewPager mPager = root.findViewById(R.id.home_top_selection_viewpager);

        sliderDataModels = new ArrayList<>();
        bestSellerDataModels = new ArrayList<>();

        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));


        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));
        bestSellerDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif","Feluda", "Novel", "Satyagit Roy", 4.5F, 6));

        SliderViewPagerAdapter pagerAdapter = new SliderViewPagerAdapter(getActivity().getSupportFragmentManager(), sliderDataModels);
        mPager.setAdapter(pagerAdapter);

        bestSellerRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        bestSellerAdapter = new BookAdapter(getActivity(),bestSellerDataModels,1);
        bestSellerRecycler.setAdapter(bestSellerAdapter);

        final int paddingPx = 300;
        final float MIN_SCALE = 0.8f;
        final float MAX_SCALE = 1f;

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

        mPager.setPageTransformer(true, transformer);

        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            SpeedSlowScroller2 scroller = new SpeedSlowScroller2(getActivity());
            mScroller.set(mPager, scroller);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new SliderTimer2(mPager, sliderDataModels.size(), getActivity()), 4000, 6000);
        } catch (Exception ignored) {
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), BookReaderActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return root;
    }

}

class SliderTimer extends TimerTask {
    private ViewPager viewPager;
    private int size;
    private Activity activity;

    public SliderTimer(ViewPager viewPager, int size, Activity activity) {
        this.viewPager = viewPager;
        this.size = size;
        this.activity = activity;
    }

    @Override
    public void run() {
        activity.runOnUiThread(() -> {
            if (viewPager.getCurrentItem() < size - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            } else {
                viewPager.setCurrentItem(0, true);
            }
        });
    }
}

class SpeedSlowScroller extends Scroller {

    private int mDuration = 2500;

    public SpeedSlowScroller(Context context) {
        super(context);
    }

    public SpeedSlowScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public SpeedSlowScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
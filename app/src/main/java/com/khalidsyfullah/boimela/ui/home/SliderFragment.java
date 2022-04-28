package com.khalidsyfullah.boimela.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.khalidsyfullah.boimela.R;
import com.squareup.picasso.Picasso;

public class SliderFragment extends Fragment {

    String arg = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.slider_layout, container, false);

        TextView bookTitleText = root.findViewById(R.id.slider_title);
        ImageView sliderImage = root.findViewById(R.id.slider_image);


        if (getArguments().getString("imgUrl") != null) {

            arg = getArguments().getString("imgUrl");
            bookTitleText.setText(getArguments().getString("title"));
            Picasso.get().load(arg).into(sliderImage);

        }

        return root;
    }
}


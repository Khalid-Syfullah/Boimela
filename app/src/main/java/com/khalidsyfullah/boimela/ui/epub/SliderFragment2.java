package com.khalidsyfullah.boimela.ui.epub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;
import com.squareup.picasso.Picasso;

public class SliderFragment2 extends Fragment {

    String arg = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.slider_layout_2, container, false);

        TextView bookTitleText = root.findViewById(R.id.slider_2_title);
        ImageView sliderImage = root.findViewById(R.id.slider_2_image);


        if (getArguments().getString("imgUrl") != null) {

            arg = getArguments().getString("imgUrl");
            bookTitleText.setText(getArguments().getString("title"));
            Picasso.get().load(arg).into(sliderImage);

        }

        return root;
    }
}


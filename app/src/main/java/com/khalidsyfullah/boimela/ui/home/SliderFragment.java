package com.khalidsyfullah.boimela.ui.home;

import static com.khalidsyfullah.boimela.global.StaticData.imageDirSmall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
        ConstraintLayout sliderConstraintLayout = root.findViewById(R.id.slider_constraint_layout);


        if (getArguments().getString("imgUrl") != null) {

            arg = getArguments().getString("imgUrl");
            bookTitleText.setText(getArguments().getString("title"));
//            Picasso.get().load(arg).into(sliderImage);
            Picasso.get().load(imageDirSmall + arg).placeholder(R.drawable.book_not_found_red).into(sliderImage);

        }

        sliderConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_book_details);
            }
        });

        return root;
    }
}


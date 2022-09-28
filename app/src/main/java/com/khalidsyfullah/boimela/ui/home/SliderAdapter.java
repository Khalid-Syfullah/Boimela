package com.khalidsyfullah.boimela.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;

class SliderViewHolder extends SliderViewAdapter.ViewHolder {
    // Adapter class for initializing
    // the views of our slider view.
    View itemView;
    ImageView imageViewBackground;
    TextView bookTitle;
    ConstraintLayout sliderConstraintLayout;

    public SliderViewHolder(View itemView) {
        super(itemView);
        imageViewBackground = itemView.findViewById(R.id.slider_image);
        bookTitle = itemView.findViewById(R.id.slider_title);
        imageViewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
        sliderConstraintLayout = itemView.findViewById(R.id.slider_constraint_layout);
        this.itemView = itemView;
    }
}


public class SliderAdapter extends SliderViewAdapter<SliderViewHolder> {

    // list for storing urls of images.
    private List<SliderDataModel> sliderViewDataModels;
    private Activity activity;

    // Constructor
    public SliderAdapter(Activity activity, ArrayList<SliderDataModel> sliderViewDataModels) {
        this.activity = activity;
        this.sliderViewDataModels = sliderViewDataModels;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, final int position) {

        SliderDataModel sliderViewDataModel = this.sliderViewDataModels.get(position);

        viewHolder.bookTitle.setText(sliderViewDataModel.getTitle());
        Picasso.get().load(sliderViewDataModel.getImgUrl()).into(viewHolder.imageViewBackground);

        Log.d("SliderView",sliderViewDataModel.getImgUrl());
        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sliderViewDataModel.getLink()));
                activity.startActivity(browserIntent);
            }
        });

        viewHolder.sliderConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_book_details);
            }
        });
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return sliderViewDataModels.size();
    }

    public void setSliderViewDataModels(ArrayList<SliderDataModel> sliderViewDataModels){
        this.sliderViewDataModels = sliderViewDataModels;
        notifyDataSetChanged();
    }
}

package com.khalidsyfullah.boimela.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class PublisherViewHolder extends RecyclerView.ViewHolder {

    TextView publisherName;
    ImageView publisherImage;
    ConstraintLayout publisherConstraintLayout;

    public PublisherViewHolder(@NonNull View itemView) {
        super(itemView);

        publisherName = itemView.findViewById(R.id.recyclerview_circular_title);
        publisherImage = itemView.findViewById(R.id.recyclerview_circular_image);
        publisherConstraintLayout = itemView.findViewById(R.id.recyclerview_circular_constraint_layout);
    }
}

public class PublisherAdapter extends RecyclerView.Adapter<PublisherViewHolder>{

    private ArrayList<PublisherDataModel> publisherDataModels;
    private Activity activity;
    private String fragmentName;

    public PublisherAdapter(Activity activity, ArrayList<PublisherDataModel> publisherDataModels, String fragmentName) {

        this.activity = activity;
        this.publisherDataModels = publisherDataModels;
        this.fragmentName = fragmentName;
    }

    @NonNull
    @Override
    public PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_circular, parent, false);
        PublisherViewHolder publisherViewHolder = new PublisherViewHolder(view);
        return publisherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PublisherViewHolder holder, int position) {

        PublisherDataModel publisherDataModel = publisherDataModels.get(position);

        holder.publisherName.setText(publisherDataModel.getName());
        Picasso.get().load(publisherDataModel.getImage()).into(holder.publisherImage);
        holder.publisherName.setSelected(true);
        holder.publisherConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentName.equals("HomeFragment")) {
                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_publisher_details);
                }
                else if(fragmentName.equals("StoreFragment")) {
                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_publisher_details);
                }
                else if(fragmentName.equals("SearchFragment")) {
                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_search_to_navigation_publisher_details);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return publisherDataModels.size();
    }
}




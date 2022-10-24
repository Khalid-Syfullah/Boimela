package com.khalidsyfullah.boimela.ui.store;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_PUBLISHER_ID;

import android.app.Activity;
import android.os.Bundle;
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
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class TopPublishersViewHolder extends RecyclerView.ViewHolder {

    TextView publisherName, publisherNumber;
    ImageView publisherImage, publisherNumberBackground;
    ConstraintLayout publisherConstraintLayout;

    public TopPublishersViewHolder(@NonNull View itemView) {
        super(itemView);

        publisherNumber = itemView.findViewById(R.id.recyclerview_top_publishers_number);
        publisherName = itemView.findViewById(R.id.recyclerview_top_publishers_title);
        publisherImage = itemView.findViewById(R.id.recyclerview_top_publishers_image);
        publisherNumberBackground = itemView.findViewById(R.id.recyclerview_top_publishers_number_background2);
        publisherConstraintLayout = itemView.findViewById(R.id.recyclerview_top_publishers_constraint_layout_2);
    }
}

public class TopPublishersAdapter extends RecyclerView.Adapter<TopPublishersViewHolder>{

    private ArrayList<PublisherDataModel> topPublishersDataModels;
    private Activity activity;

    public TopPublishersAdapter(Activity activity, ArrayList<PublisherDataModel> topPublishersDataModels) {

        this.activity = activity;
        this.topPublishersDataModels = topPublishersDataModels;
    }

    @NonNull
    @Override
    public TopPublishersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_top_publishers, parent, false);
        TopPublishersViewHolder TopPublishersViewHolder = new TopPublishersViewHolder(view);
        return TopPublishersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopPublishersViewHolder holder, int position) {

        PublisherDataModel publisherDataModel = topPublishersDataModels.get(position);

        holder.publisherNumber.setText(String.valueOf(position+1));
        holder.publisherName.setText(publisherDataModel.getName());
        Picasso.get().load(StaticData.imageDirSmall+publisherDataModel.getImage()).placeholder(R.drawable.book_not_found).into(holder.publisherImage);

        if(position == 0){
            holder.publisherNumber.setTextColor(activity.getResources().getColor(R.color.white));
            holder.publisherNumberBackground.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_orange));
        }
        else{
            holder.publisherNumber.setTextColor(activity.getResources().getColor(R.color.black));
            holder.publisherNumberBackground.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_dark_white));

        }

        holder.publisherConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CURRENT_PUBLISHER_ID = publisherDataModel.getId();
                Bundle bundle = new Bundle();
                bundle.putString("publisher_id",publisherDataModel.getId());

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_publisher_details, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topPublishersDataModels.size();
    }

    public void setTopPublishersDataModels(ArrayList<PublisherDataModel> publisherDataModels) {
        this.topPublishersDataModels = publisherDataModels;
        notifyDataSetChanged();
    }
}





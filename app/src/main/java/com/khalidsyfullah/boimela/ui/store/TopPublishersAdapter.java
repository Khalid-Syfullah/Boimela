package com.khalidsyfullah.boimela.ui.store;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class TopPublishersViewHolder extends RecyclerView.ViewHolder {

    TextView publisherName, publisherNumber;
    ImageView publisherImage, publisherNumberBackground;

    public TopPublishersViewHolder(@NonNull View itemView) {
        super(itemView);

        publisherNumber = itemView.findViewById(R.id.recyclerview_top_authors_number);
        publisherName = itemView.findViewById(R.id.recyclerview_top_authors_title);
        publisherImage = itemView.findViewById(R.id.recyclerview_top_authors_image);
        publisherNumberBackground = itemView.findViewById(R.id.recyclerview_top_authors_number_background2);

    }
}

public class TopPublishersAdapter extends RecyclerView.Adapter<TopPublishersViewHolder>{

    private ArrayList<AuthorDataModel> topPublishersDataModels;
    private Activity activity;

    public TopPublishersAdapter(Activity activity, ArrayList<AuthorDataModel> topPublishersDataModels) {

        this.activity = activity;
        this.topPublishersDataModels = topPublishersDataModels;
    }

    @NonNull
    @Override
    public TopPublishersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_top_authors, parent, false);
        TopPublishersViewHolder TopPublishersViewHolder = new TopPublishersViewHolder(view);
        return TopPublishersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopPublishersViewHolder holder, int position) {

        AuthorDataModel authorDataModel = topPublishersDataModels.get(position);

        holder.publisherNumber.setText(String.valueOf(position+1));
        holder.publisherName.setText(authorDataModel.getName());
        Picasso.get().load(authorDataModel.getImage()).into(holder.publisherImage);

        if(position == 0){
            holder.publisherNumber.setTextColor(activity.getResources().getColor(R.color.white));
            holder.publisherNumberBackground.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_orange));
        }
        else{
            holder.publisherNumber.setTextColor(activity.getResources().getColor(R.color.black));
            holder.publisherNumberBackground.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_dark_white));

        }
    }

    @Override
    public int getItemCount() {
        return topPublishersDataModels.size();
    }
}





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

class TopAuthorsViewHolder extends RecyclerView.ViewHolder {

    TextView authorName, authorNumber;
    ImageView authorImage, authorNumberBackground;

    public TopAuthorsViewHolder(@NonNull View itemView) {
        super(itemView);

        authorNumber = itemView.findViewById(R.id.recyclerview_top_authors_number);
        authorName = itemView.findViewById(R.id.recyclerview_top_authors_title);
        authorImage = itemView.findViewById(R.id.recyclerview_top_authors_image);
        authorNumberBackground = itemView.findViewById(R.id.recyclerview_top_authors_number_background2);

    }
}

public class TopAuthorsAdapter extends RecyclerView.Adapter<TopAuthorsViewHolder>{

    private ArrayList<AuthorDataModel> topAuthorsDataModels;
    private Activity activity;

    public TopAuthorsAdapter(Activity activity, ArrayList<AuthorDataModel> topAuthorsDataModels) {

        this.activity = activity;
        this.topAuthorsDataModels = topAuthorsDataModels;
    }

    @NonNull
    @Override
    public TopAuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_top_authors, parent, false);
        TopAuthorsViewHolder TopAuthorsViewHolder = new TopAuthorsViewHolder(view);
        return TopAuthorsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopAuthorsViewHolder holder, int position) {

        AuthorDataModel authorDataModel = topAuthorsDataModels.get(position);

        holder.authorNumber.setText(String.valueOf(position+1));
        holder.authorName.setText(authorDataModel.getName());
        Picasso.get().load(authorDataModel.getImage()).into(holder.authorImage);

        if(position == 0){
            holder.authorNumber.setTextColor(activity.getResources().getColor(R.color.white));
            holder.authorNumberBackground.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_orange));
        }
        else{
            holder.authorNumber.setTextColor(activity.getResources().getColor(R.color.black));
            holder.authorNumberBackground.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_dark_white));

        }
    }

    @Override
    public int getItemCount() {
        return topAuthorsDataModels.size();
    }
}




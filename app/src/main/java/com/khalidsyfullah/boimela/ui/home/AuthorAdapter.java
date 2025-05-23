package com.khalidsyfullah.boimela.ui.home;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_AUTHOR_ID;
import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;

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
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class AuthorViewHolder extends RecyclerView.ViewHolder {

    TextView authorName;
    ImageView authorImage;
    ConstraintLayout authorConstraintLayout;

    public AuthorViewHolder(@NonNull View itemView) {
        super(itemView);

        authorName = itemView.findViewById(R.id.recyclerview_circular_title);
        authorImage = itemView.findViewById(R.id.recyclerview_circular_image);
        authorConstraintLayout = itemView.findViewById(R.id.recyclerview_circular_constraint_layout);
    }
}

public class AuthorAdapter extends RecyclerView.Adapter<AuthorViewHolder>{

    private ArrayList<AuthorDataModel> authorDataModels;
    private Activity activity;
    private String fragmentName;

    public AuthorAdapter(Activity activity, ArrayList<AuthorDataModel> authorDataModels, String fragmentName) {

        this.activity = activity;
        this.authorDataModels = authorDataModels;
        this.fragmentName = fragmentName;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_circular, parent, false);
        AuthorViewHolder authorViewHolder = new AuthorViewHolder(view);
        return authorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {

        AuthorDataModel authorDataModel = authorDataModels.get(position);

        holder.authorName.setText(authorDataModel.getName());
        Picasso.get().load(StaticData.imageDirSmall+authorDataModel.getImage()).into(holder.authorImage);
        holder.authorName.setSelected(true);
        holder.authorConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CURRENT_AUTHOR_ID = authorDataModel.getId();
                Bundle bundle = new Bundle();
                bundle.putString("author_id",authorDataModel.getId());


                if(fragmentName.equals("HomeFragment")) {
                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_author_details, bundle);
                }
                else if(fragmentName.equals("StoreFragment")) {
                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_author_details, bundle);
                }
                else if(fragmentName.equals("SearchFragment")) {
                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_search_to_navigation_author_details, bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return authorDataModels.size();
    }


    public void setAuthorDataModels(ArrayList<AuthorDataModel> authorDataModels) {
        this.authorDataModels = authorDataModels;
        notifyDataSetChanged();
    }
}





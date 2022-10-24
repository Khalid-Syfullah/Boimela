package com.khalidsyfullah.boimela.ui.viewall;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_AUTHOR_ID;

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
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ViewAllAuthorsViewHolder extends RecyclerView.ViewHolder {

    TextView authorName;
    ImageView authorImage;
    ConstraintLayout authorConstraintLayout;

    public ViewAllAuthorsViewHolder(@NonNull View itemView) {
        super(itemView);

        authorName = itemView.findViewById(R.id.recyclerview_view_all_authors_title);
        authorImage = itemView.findViewById(R.id.recyclerview_view_all_authors_image);
        authorConstraintLayout = itemView.findViewById(R.id.recyclerview_view_all_authors_constraint_layout);
    }
}

public class ViewAllAuthorsAdapter extends RecyclerView.Adapter<ViewAllAuthorsViewHolder>{

    private ArrayList<AuthorDataModel> viewAllAuthorsDataModels;
    private Activity activity;

    public ViewAllAuthorsAdapter(Activity activity, ArrayList<AuthorDataModel> viewAllAuthorsDataModels) {

        this.activity = activity;
        this.viewAllAuthorsDataModels = viewAllAuthorsDataModels;
    }

    @NonNull
    @Override
    public ViewAllAuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_view_all_authors, parent, false);
        ViewAllAuthorsViewHolder ViewAllAuthorsViewHolder = new ViewAllAuthorsViewHolder(view);
        return ViewAllAuthorsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAuthorsViewHolder holder, int position) {

        AuthorDataModel authorDataModel = viewAllAuthorsDataModels.get(position);

        holder.authorName.setText(authorDataModel.getName());
        Picasso.get().load(StaticData.imageDirSmall+authorDataModel.getImage()).into(holder.authorImage);
        holder.authorName.setSelected(true);

        holder.authorConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CURRENT_AUTHOR_ID = authorDataModel.getId();
                Bundle bundle = new Bundle();
                bundle.putString("author_id",authorDataModel.getId());

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_view_all_to_navigation_author_details, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllAuthorsDataModels.size();
    }

    public void setAuthorDataModels(ArrayList<AuthorDataModel> authorDataModels) {
        this.viewAllAuthorsDataModels = authorDataModels;
        notifyDataSetChanged();
    }
}





package com.khalidsyfullah.boimela.ui.viewall;

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
        Picasso.get().load(authorDataModel.getImage()).into(holder.authorImage);

        holder.authorConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_author_details);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllAuthorsDataModels.size();
    }
}





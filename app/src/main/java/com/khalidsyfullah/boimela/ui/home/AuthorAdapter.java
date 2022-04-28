package com.khalidsyfullah.boimela.ui.home;

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

class AuthorViewHolder extends RecyclerView.ViewHolder {

    TextView authorName;
    ImageView authorImage;

    public AuthorViewHolder(@NonNull View itemView) {
        super(itemView);

        authorName = itemView.findViewById(R.id.recyclerview_circular_title);
        authorImage = itemView.findViewById(R.id.recyclerview_circular_image);

    }
}

public class AuthorAdapter extends RecyclerView.Adapter<AuthorViewHolder>{

    private ArrayList<AuthorDataModel> authorDataModels;
    private Activity activity;

    public AuthorAdapter(Activity activity, ArrayList<AuthorDataModel> authorDataModels) {

        this.activity = activity;
        this.authorDataModels = authorDataModels;
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
        Picasso.get().load(authorDataModel.getImage()).into(holder.authorImage);
        holder.authorName.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return authorDataModels.size();
    }
}





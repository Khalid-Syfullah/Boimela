package com.khalidsyfullah.boimela.ui.search;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class SearchViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle, bookAuthor, bookReview;
    RatingBar bookRating;
    ImageView bookImage;
    TextView bookRead, bookPlay;
    ConstraintLayout bookConstraintLayout;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.recyclerview_search_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_search_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_search_author);
        bookRating = itemView.findViewById(R.id.recyclerview_search_rating);
        bookReview = itemView.findViewById(R.id.recyclerview_search_review);
        bookRead = itemView.findViewById(R.id.recyclerview_search_open);
        bookPlay = itemView.findViewById(R.id.recyclerview_search_play);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_search_constraint_layout);

    }

}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public SearchAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_book_search, parent, false);
        SearchViewHolder searchViewHolder = new SearchViewHolder(view);
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

        holder.bookTitle.setText(bookDataModel.getTitle());
        holder.bookAuthor.setText(bookDataModel.getAuthor());
        holder.bookRating.setRating(bookDataModel.getRating());
        holder.bookReview.setText(String.valueOf(bookDataModel.getReview())+" "+activity.getResources().getString(R.string.review));
        Picasso.get().load(bookDataModel.getImage()).into(holder.bookImage);

    }

    @Override
    public int getItemCount() {
        return bookDataModels.size();
    }


    public void setBookDataModels(ArrayList<BookDataModel> bookDataModels) {
        this.bookDataModels = bookDataModels;
        notifyDataSetChanged();
    }

}
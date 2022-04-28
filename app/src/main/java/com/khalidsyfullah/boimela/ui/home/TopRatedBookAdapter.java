package com.khalidsyfullah.boimela.ui.home;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class TopRatedBookViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle, bookAuthor, bookReview;
    RatingBar bookRating;
    ImageView bookImage;
    ConstraintLayout bookConstraintLayout;
    CardView bookCardView;

    public TopRatedBookViewHolder(@NonNull View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.recyclerview_top_rated_book_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_top_rated_book_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_top_rated_book_author);
        bookRating = itemView.findViewById(R.id.recyclerview_top_rated_book_rating);
        bookReview = itemView.findViewById(R.id.recyclerview_top_rated_book_review);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_top_rated_book_constraint_layout);
        bookCardView = itemView.findViewById(R.id.recyclerview_top_rated_book_cardview);
    }

}

public class TopRatedBookAdapter extends RecyclerView.Adapter<TopRatedBookViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public TopRatedBookAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public TopRatedBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_top_rated_book,parent,false);
        TopRatedBookViewHolder topRatedBookViewHolder = new TopRatedBookViewHolder(view);
        return topRatedBookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedBookViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);


        if(bookDataModel.getType() == 1){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.green));
        }
        else if(bookDataModel.getType() == 2){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.blue));
        }
        else if(bookDataModel.getType() == 3){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.orange));
        }
        else if(bookDataModel.getType() == 4){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.grey));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.yellow));
        }
        else if(bookDataModel.getType() == 5){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.teal));
        }
        else if(bookDataModel.getType() == 6){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.black));
            holder.bookAuthor.setTextColor(activity.getResources().getColor(R.color.grey));
            holder.bookReview.setVisibility(View.GONE);
            holder.bookRating.setVisibility(View.GONE);
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
            holder.bookCardView.setElevation(0);

        }

        holder.bookTitle.setText(bookDataModel.getTitle());
        holder.bookAuthor.setText(bookDataModel.getAuthor());
        holder.bookRating.setRating(bookDataModel.getRating());
        holder.bookReview.setText(String.valueOf(bookDataModel.getReview()) +" "+activity.getResources().getString(R.string.reviews));
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
package com.khalidsyfullah.boimela.ui.home;


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

class BookSeriesViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle;
    ImageView bookImageA, bookImageB, bookImageC;
    ConstraintLayout bookConstraintLayout;

    public BookSeriesViewHolder(@NonNull View itemView) {
        super(itemView);

        bookTitle = itemView.findViewById(R.id.book_series_title);
        bookImageA = itemView.findViewById(R.id.book_series_imageviewA);
        bookImageB = itemView.findViewById(R.id.book_series_imageviewA2);
        bookImageC = itemView.findViewById(R.id.book_series_imageviewA3);

        bookConstraintLayout = itemView.findViewById(R.id.book_series_constraint_layout);

    }

}

public class BookSeriesAdapter extends RecyclerView.Adapter<BookSeriesViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public BookSeriesAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public BookSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_book_series, parent, false);
        BookSeriesViewHolder bookSeriesViewHolder = new BookSeriesViewHolder(view);
        return bookSeriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookSeriesViewHolder holder, int position) {

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

        holder.bookTitle.setText(bookDataModel.getTitle());
        Picasso.get().load(bookDataModel.getImageA()).into(holder.bookImageA);
        Picasso.get().load(bookDataModel.getImageB()).into(holder.bookImageB);
        Picasso.get().load(bookDataModel.getImageC()).into(holder.bookImageC);



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
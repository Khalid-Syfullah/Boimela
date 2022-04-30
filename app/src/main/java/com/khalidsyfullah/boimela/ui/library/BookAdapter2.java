package com.khalidsyfullah.boimela.ui.library;


import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class BookViewHolder2 extends RecyclerView.ViewHolder{

    TextView bookTitle, bookAuthor, bookProgressNumber;
    ImageView bookImage;
    ProgressBar bookProgressBar;
    ConstraintLayout bookConstraintLayout;

    public BookViewHolder2(@NonNull View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.recyclerview_currently_reading_book_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_currently_reading_book_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_currently_reading_book_author);
        bookProgressNumber = itemView.findViewById(R.id.recyclerview_currently_reading_book_progress);
        bookProgressBar = itemView.findViewById(R.id.recyclerview_currently_reading_book_progress_bar);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_currently_reading_book_constraint_layout);

    }

}

public class BookAdapter2 extends RecyclerView.Adapter<BookViewHolder2>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public BookAdapter2(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public BookViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_currently_reading_book, parent, false);
        BookViewHolder2 bookViewHolder2 = new BookViewHolder2(view);
        return bookViewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder2 holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

        holder.bookTitle.setText(bookDataModel.getTitle());
        holder.bookAuthor.setText(bookDataModel.getAuthor());
        holder.bookProgressNumber.setText(bookDataModel.getProgress()+"%");
        holder.bookProgressBar.setProgress(bookDataModel.getProgress());
        Picasso.get().load(bookDataModel.getImage()).into(holder.bookImage);

        if(bookDataModel.getProgress() >= 0 && bookDataModel.getProgress() <= 20){
            holder.bookProgressBar.setProgressTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.orange)));
        }
        else if(bookDataModel.getProgress() > 20 && bookDataModel.getProgress() <= 40){
            holder.bookProgressBar.setProgressTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.yellow)));

        }
        else if(bookDataModel.getProgress() > 40 && bookDataModel.getProgress() <= 60){
            holder.bookProgressBar.setProgressTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.green)));

        }
        else if(bookDataModel.getProgress() > 60 && bookDataModel.getProgress() <= 80){
            holder.bookProgressBar.setProgressTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue)));

        }
        else if(bookDataModel.getProgress() > 80){
            holder.bookProgressBar.setProgressTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.teal)));
        }


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
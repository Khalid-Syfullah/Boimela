package com.khalidsyfullah.boimela.ui.library;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class BookshelfViewHolder extends RecyclerView.ViewHolder{

    ImageView bookImageA, bookImageB, bookImageC;
    ConstraintLayout bookConstraintLayout;

    public BookshelfViewHolder(@NonNull View itemView) {
        super(itemView);

        bookImageA = itemView.findViewById(R.id.recyclerview_bookshelf_imageviewA);
        bookImageB = itemView.findViewById(R.id.recyclerview_bookshelf_imageviewA2);
        bookImageC = itemView.findViewById(R.id.recyclerview_bookshelf_imageviewA3);

        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_bookshelf_constraint_layout);

    }

}

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfViewHolder>{

    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public BookshelfAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {

        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public BookshelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_bookshelf, parent, false);
        BookshelfViewHolder bookshelfViewHolder = new BookshelfViewHolder(view);
        return bookshelfViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookshelfViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

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
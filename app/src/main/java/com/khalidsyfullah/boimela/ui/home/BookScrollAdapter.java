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
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class BookScrollViewHolder extends RecyclerView.ViewHolder {


    ImageView image;

    public BookScrollViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.recyclerview_upcoming_book_image);

    }
}

public class BookScrollAdapter extends RecyclerView.Adapter<BookScrollViewHolder>{

    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public BookScrollAdapter(Activity activity, ArrayList<BookDataModel> bookDataModels) {

        this.activity = activity;
        this.bookDataModels = bookDataModels;
    }

    @NonNull
    @Override
    public BookScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_upcoming_book, parent, false);
        BookScrollViewHolder bookScrollViewHolder = new BookScrollViewHolder(view);
        return bookScrollViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookScrollViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

        Picasso.get().load(StaticData.imageDirSmall+bookDataModel.getImage()).placeholder(R.drawable.book_slider).into(holder.image);
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





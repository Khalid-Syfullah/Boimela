package com.khalidsyfullah.boimela.ui.home;


import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeriesBooks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
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
    private String fragmentName;

    public BookSeriesAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels, String fragmentName) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
        this.fragmentName = fragmentName;
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
        Picasso.get().load(bookDataModel.getImageA()).placeholder(R.drawable.books).into(holder.bookImageA);
        Picasso.get().load(bookDataModel.getImageB()).placeholder(R.drawable.books).into(holder.bookImageB);
        Picasso.get().load(bookDataModel.getImageC()).placeholder(R.drawable.books).into(holder.bookImageC);

        holder.bookConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CURRENT_BOOK_ID = bookDataModel.get_id();

                Bundle bundle = new Bundle();
                bundle.putString("query","series");
                bundle.putString("type","books");
                bundle.putString("book_id",bookDataModel.get_id());

                bookSeriesBooks = new MutableLiveData<>();

                if(fragmentName.equals("HomeFragment")){

                    bundle.putString("fragment","home");

                    StaticData.fileName = bookDataModel.getFileName();
                    StaticData.bookUrl = bookDataModel.getBookUrl();
                    StaticData.audioUrl = bookDataModel.getAudioUrl();

                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);
                }
                else if(fragmentName.equals("StoreFragment")){

                    bundle.putString("fragment","store");

                    StaticData.fileName = bookDataModel.getFileName();
                    StaticData.bookUrl = bookDataModel.getBookUrl();
                    StaticData.audioUrl = bookDataModel.getAudioUrl();

                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);
                }

            }
        });

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
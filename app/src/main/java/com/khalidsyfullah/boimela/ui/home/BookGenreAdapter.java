package com.khalidsyfullah.boimela.ui.home;


import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.bookSeriesBooks;
import static com.khalidsyfullah.boimela.global.StaticData.genreBooks;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

class BookGenreViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle;
    ImageView bookImageA, bookImageB, bookImageC;
    ConstraintLayout bookConstraintLayout;

    public BookGenreViewHolder(@NonNull View itemView) {
        super(itemView);

        bookTitle = itemView.findViewById(R.id.recyclerview_book_genre_title);
        bookImageA = itemView.findViewById(R.id.recyclerview_book_genre_imageviewA);
        bookImageB = itemView.findViewById(R.id.recyclerview_book_genre_imageviewA2);
        bookImageC = itemView.findViewById(R.id.recyclerview_book_genre_imageviewA3);

        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_book_genre_constraint_layout);

    }

}

public class BookGenreAdapter extends RecyclerView.Adapter<BookGenreViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public BookGenreAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public BookGenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_book_genre, parent, false);

        BookGenreViewHolder bookGenreViewHolder = new BookGenreViewHolder(view);
        return bookGenreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookGenreViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);


        if(bookDataModel.getType() == 1){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.light_blue));
        }
        else if(bookDataModel.getType() == 2){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.light_lime));
        }
        else if(bookDataModel.getType() == 3){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.light_red));
        }
        else if(bookDataModel.getType() == 4){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.grey));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.dark_blue));
        }
        else if(bookDataModel.getType() == 5){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.theme_6));
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
                bundle.putString("fragment","home");
                bundle.putString("type","books");
                bundle.putString("query","genres");
                bundle.putString("book_id",bookDataModel.get_id());

                genreBooks = new MutableLiveData<>();

                StaticData.fileName = bookDataModel.getFileName();
                StaticData.bookUrl = bookDataModel.getBookUrl();
                StaticData.audioUrl = bookDataModel.getAudioUrl();

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_view_all, bundle);


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
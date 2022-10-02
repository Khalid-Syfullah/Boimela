package com.khalidsyfullah.boimela.ui.home;


import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirSmall;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;

import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class BookViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle, bookAuthor, bookReview;
    RatingBar bookRating;
    ImageView bookImage;
    ConstraintLayout bookConstraintLayout;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.recyclerview_book_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_book_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_book_author);
        bookRating = itemView.findViewById(R.id.recyclerview_book_rating);
        bookReview = itemView.findViewById(R.id.recyclerview_book_review);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_book_constraint_layout);

    }

}

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;
    private int type;
    private String fragmentName;

    public BookAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels, int type, String fragmentName) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
        this.type = type;
        this.fragmentName = fragmentName;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        if(type == 1){
            view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_book_horizontal, parent, false);
        }
        else {
            view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_book_horizontal,parent,false);
        }

        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

        holder.bookTitle.setText(bookDataModel.getName());
        if(bookDataModel.getWriter() != null) {
            holder.bookAuthor.setText(bookDataModel.getWriter().getName());
        }
        else{
            holder.bookAuthor.setText(bookDataModel.getAuthor());

        }
        holder.bookRating.setRating(bookDataModel.getRating());
        holder.bookReview.setText(bookDataModel.getNumberOfRating() +" "+activity.getResources().getString(R.string.reviews));
//        Picasso.get().load(bookDataModel.getImage()).into(holder.bookImage);

        Picasso.get().load(imageDirSmall + bookDataModel.getImage()).placeholder(R.drawable.book_not_found).into(holder.bookImage);

        holder.bookTitle.setSelected(true);
        holder.bookAuthor.setSelected(true);

        holder.bookConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CURRENT_BOOK_ID = bookDataModel.get_id();
                Bundle bundle = new Bundle();
                bundle.putString("book_id",bookDataModel.get_id());


                if(fragmentName.equals("HomeFragment")){

                    StaticData.fileName = bookDataModel.getFileName();
                    StaticData.bookUrl = bookDataModel.getBookUrl();
                    StaticData.audioUrl = bookDataModel.getAudioUrl();

                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_book_details, bundle);
                }
                else if(fragmentName.equals("StoreFragment")){

                    StaticData.fileName = bookDataModel.getFileName();
                    StaticData.bookUrl = bookDataModel.getBookUrl();
                    StaticData.audioUrl = bookDataModel.getAudioUrl();

                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_book_details, bundle);
                }

                else if(fragmentName.equals("SearchFragment")){

                    StaticData.fileName = bookDataModel.getFileName();
                    StaticData.bookUrl = bookDataModel.getBookUrl();
                    StaticData.audioUrl = bookDataModel.getAudioUrl();

                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_search_to_navigation_book_details, bundle);
                }

                else if(fragmentName.equals("AuthorDetailsFragment")){

                    StaticData.fileName = bookDataModel.getFileName();
                    StaticData.bookUrl = bookDataModel.getBookUrl();
                    StaticData.audioUrl = bookDataModel.getAudioUrl();

                    Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_author_details_to_navigation_book_details, bundle);
                }

            }
        });


    }

    @Override
    public int getItemCount() {

        if(bookDataModels.size() < 10) {
            return bookDataModels.size();
        }
        else{
            return 10;
        }
    }


    public void setBookDataModels(ArrayList<BookDataModel> bookDataModels) {
        this.bookDataModels = bookDataModels;
        notifyDataSetChanged();
    }

}
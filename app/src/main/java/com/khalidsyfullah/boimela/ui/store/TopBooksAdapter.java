package com.khalidsyfullah.boimela.ui.store;


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

class TopBooksViewHolder extends RecyclerView.ViewHolder{

    TextView bookNumber, bookTitle, bookAuthor, bookReview;
    RatingBar bookRating;
    ImageView bookImage;
    TextView bookRead, bookPlay;
    ConstraintLayout bookConstraintLayout;

    public TopBooksViewHolder(@NonNull View itemView) {
        super(itemView);

        bookNumber = itemView.findViewById(R.id.recyclerview_top_books_number);
        bookImage = itemView.findViewById(R.id.recyclerview_top_books_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_top_books_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_top_books_author);
        bookRating = itemView.findViewById(R.id.recyclerview_top_books_rating);
        bookReview = itemView.findViewById(R.id.recyclerview_top_books_review);
        bookRead = itemView.findViewById(R.id.recyclerview_top_books_open);
        bookPlay = itemView.findViewById(R.id.recyclerview_top_books_play);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_top_books_constraint_layout);

    }

}

public class TopBooksAdapter extends RecyclerView.Adapter<TopBooksViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public TopBooksAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public TopBooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_top_books, parent, false);
        TopBooksViewHolder topBooksViewHolder = new TopBooksViewHolder(view);
        return topBooksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopBooksViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

        holder.bookNumber.setText(String.valueOf(position+1));
        holder.bookTitle.setText(bookDataModel.getName());
        if(bookDataModel.getWriter() != null) {
            holder.bookAuthor.setText(bookDataModel.getWriter().getName());
        }
        else{
            holder.bookAuthor.setText(bookDataModel.getAuthor());

        }
        holder.bookRating.setRating(bookDataModel.getRating());
        holder.bookReview.setText(bookDataModel.getNumberOfRating() +" "+activity.getResources().getString(R.string.reviews));
        Picasso.get().load(imageDirSmall + bookDataModel.getImage()).placeholder(R.drawable.book_not_found).into(holder.bookImage);

        holder.bookRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CURRENT_BOOK_ID = bookDataModel.get_id();
                Bundle bundle = new Bundle();
                bundle.putString("book_id",bookDataModel.get_id());

                StaticData.fileName = bookDataModel.getFileName();
                StaticData.bookUrl = bookDataModel.getBookUrl();
                StaticData.audioUrl = bookDataModel.getAudioUrl();

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_book_details, bundle);


            }
        });

        holder.bookConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CURRENT_BOOK_ID = bookDataModel.get_id();
                Bundle bundle = new Bundle();
                bundle.putString("book_id",bookDataModel.get_id());

                StaticData.fileName = bookDataModel.getFileName();
                StaticData.bookUrl = bookDataModel.getBookUrl();
                StaticData.audioUrl = bookDataModel.getAudioUrl();

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_book_details, bundle);



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
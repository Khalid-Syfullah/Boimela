package com.khalidsyfullah.boimela.ui.viewall;


import android.app.Activity;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ViewAllBooksViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle, bookAuthor, bookReview;
    RatingBar bookRating;
    ImageView bookImage;
    ConstraintLayout bookConstraintLayout;

    public ViewAllBooksViewHolder(@NonNull View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.recyclerview_view_all_books_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_view_all_books_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_view_all_books_author);
        bookRating = itemView.findViewById(R.id.recyclerview_view_all_books_rating);
        bookReview = itemView.findViewById(R.id.recyclerview_view_all_books_review);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_view_all_books_constraint_layout);

    }

}

public class ViewAllBooksAdapter extends RecyclerView.Adapter<ViewAllBooksViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;
    private String fragmentName, type;

    public ViewAllBooksAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public ViewAllBooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_view_all_books, parent, false);
        ViewAllBooksViewHolder viewAllBooksViewHolder = new ViewAllBooksViewHolder(view);
        return viewAllBooksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllBooksViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);

        holder.bookTitle.setText(bookDataModel.getName());
        holder.bookAuthor.setText(bookDataModel.getAuthor());
        holder.bookRating.setRating(bookDataModel.getRating());
        holder.bookReview.setText(String.valueOf(bookDataModel.getNumberOfRating())+" "+activity.getResources().getString(R.string.review));
        Picasso.get().load(bookDataModel.getImage()).into(holder.bookImage);

        holder.bookConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_view_all_to_navigation_book_details);
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
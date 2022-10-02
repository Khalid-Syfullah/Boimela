package com.khalidsyfullah.boimela.ui.viewall;


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

        if(bookDataModel.getTitle() != null) {
            holder.bookTitle.setText(bookDataModel.getTitle());
        }
        else{
            holder.bookTitle.setText(bookDataModel.getName());
        }

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
            public void onClick(View view) {

                CURRENT_BOOK_ID = bookDataModel.get_id();
                Bundle bundle = new Bundle();
                bundle.putString("book_id",bookDataModel.get_id());

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_view_all_to_navigation_book_details, bundle);
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
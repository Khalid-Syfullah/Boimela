package com.khalidsyfullah.boimela.ui.home;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.NavigableMap;

class TopRatedBookViewHolder extends RecyclerView.ViewHolder{

    TextView bookTitle, bookAuthor, bookReview;
    RatingBar bookRating;
    ImageView bookImage;
    ConstraintLayout bookConstraintLayout;
    CardView bookCardView;

    public TopRatedBookViewHolder(@NonNull View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.recyclerview_top_rated_book_image);
        bookTitle = itemView.findViewById(R.id.recyclerview_top_rated_book_title);
        bookAuthor = itemView.findViewById(R.id.recyclerview_top_rated_book_author);
        bookRating = itemView.findViewById(R.id.recyclerview_top_rated_book_rating);
        bookReview = itemView.findViewById(R.id.recyclerview_top_rated_book_review);
        bookConstraintLayout = itemView.findViewById(R.id.recyclerview_top_rated_book_constraint_layout);
        bookCardView = itemView.findViewById(R.id.recyclerview_top_rated_book_cardview);
    }

}

public class TopRatedBookAdapter extends RecyclerView.Adapter<TopRatedBookViewHolder>{
    
    private ArrayList<BookDataModel> bookDataModels;
    private Activity activity;

    public TopRatedBookAdapter(Activity activity, ArrayList<BookDataModel> booksDataModels) {
        
        this.activity = activity;
        this.bookDataModels = booksDataModels;
    }

    @NonNull
    @Override
    public TopRatedBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_top_rated_book,parent,false);
        TopRatedBookViewHolder topRatedBookViewHolder = new TopRatedBookViewHolder(view);
        return topRatedBookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedBookViewHolder holder, int position) {

        BookDataModel bookDataModel = bookDataModels.get(position);


        if(bookDataModel.getType() == 1 || bookDataModel.getType() == 9){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.green));
        }
        if(bookDataModel.getType() == 2 || bookDataModel.getType() == 8){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.blue));
        }
        if(bookDataModel.getType() == 3 || bookDataModel.getType() == 7){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.orange));
        }
        if(bookDataModel.getType() == 4 || bookDataModel.getType() == 6){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.grey));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.yellow));
        }
        else if(bookDataModel.getType() == 5){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.white));
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.teal));
        }
        else if(bookDataModel.getType() == 6){
            holder.bookTitle.setTextColor(activity.getResources().getColor(R.color.black));
            holder.bookAuthor.setTextColor(activity.getResources().getColor(R.color.grey));
            holder.bookReview.setVisibility(View.GONE);
            holder.bookRating.setVisibility(View.GONE);
            holder.bookConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
            holder.bookCardView.setElevation(0);

        }

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
        Picasso.get().load(StaticData.imageDirSmall+ bookDataModel.getImage()).placeholder(R.drawable.book_slider).into(holder.bookImage);

        holder.bookConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_home_to_navigation_book_details);
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
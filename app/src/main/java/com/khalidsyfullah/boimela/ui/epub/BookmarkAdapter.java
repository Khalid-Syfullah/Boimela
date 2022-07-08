package com.khalidsyfullah.boimela.ui.epub;


import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.BookmarkDataModel;
import com.khalidsyfullah.boimela.datamodel.ContentDataModel;

import java.util.ArrayList;

class BookmarkViewHolder extends RecyclerView.ViewHolder{

    TextView contentTitle, contentPage;


    public BookmarkViewHolder(@NonNull View itemView) {
        super(itemView);

        contentTitle = itemView.findViewById(R.id.recyclerview_contents_title);
        contentPage = itemView.findViewById(R.id.recyclerview_contents_page);


    }

}

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkViewHolder>{
    
    private ArrayList<BookmarkDataModel> bookmarkDataModels;
    private Activity activity;

    public BookmarkAdapter(Activity activity, ArrayList<BookmarkDataModel> bookmarkDataModels) {
        
        this.activity = activity;
        this.bookmarkDataModels = bookmarkDataModels;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_contents, parent, false);
        BookmarkViewHolder bookmarkViewHolder = new BookmarkViewHolder(view);
        return bookmarkViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {

        BookmarkDataModel bookmarkDataModel = bookmarkDataModels.get(position);

        holder.contentTitle.setText(bookmarkDataModel.getTitle());
        holder.contentPage.setText(bookmarkDataModel.getPage());

        if(bookmarkDataModel.getType() == 1){
            holder.contentTitle.setTextAppearance(R.style.TextViewB);
            holder.contentTitle.setTextColor(activity.getResources().getColor(R.color.black));

        }
        else if(bookmarkDataModel.getType() == 2){
            holder.contentTitle.setTextAppearance(R.style.TextViewB2);
            holder.contentTitle.setTextColor(activity.getResources().getColor(R.color.grey));

        }

    }

    @Override
    public int getItemCount() {
        return bookmarkDataModels.size();
    }


}
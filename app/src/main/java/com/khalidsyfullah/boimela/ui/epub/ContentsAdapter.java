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
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.ContentDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ContentsViewHolder extends RecyclerView.ViewHolder{

    TextView contentTitle, contentPage;


    public ContentsViewHolder(@NonNull View itemView) {
        super(itemView);

        contentTitle = itemView.findViewById(R.id.recyclerview_contents_title);
        contentPage = itemView.findViewById(R.id.recyclerview_contents_page);


    }

}

public class ContentsAdapter extends RecyclerView.Adapter<ContentsViewHolder>{
    
    private ArrayList<ContentDataModel> contentDataModels;
    private Activity activity;

    public ContentsAdapter(Activity activity, ArrayList<ContentDataModel> contentDataModels) {
        
        this.activity = activity;
        this.contentDataModels = contentDataModels;
    }

    @NonNull
    @Override
    public ContentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_contents, parent, false);
        ContentsViewHolder contentsViewHolder = new ContentsViewHolder(view);
        return contentsViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ContentsViewHolder holder, int position) {

        ContentDataModel contentDataModel = contentDataModels.get(position);

        holder.contentTitle.setText(contentDataModel.getTitle());
        holder.contentPage.setText(contentDataModel.getPage());

        if(contentDataModel.getType() == 1){
            holder.contentTitle.setTextAppearance(R.style.TextViewB);
            holder.contentTitle.setTextColor(activity.getResources().getColor(R.color.black));

        }
        else if(contentDataModel.getType() == 2){
            holder.contentTitle.setTextAppearance(R.style.TextViewB2);
            holder.contentTitle.setTextColor(activity.getResources().getColor(R.color.grey));

        }

    }

    @Override
    public int getItemCount() {
        return contentDataModels.size();
    }


}
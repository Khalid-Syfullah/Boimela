package com.khalidsyfullah.boimela.ui.store;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.CategoryDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CategoryViewHolder extends RecyclerView.ViewHolder {

    TextView categoryName;
    ImageView categoryImage;
    ConstraintLayout categoryConstraintLayout;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryName = itemView.findViewById(R.id.recyclerview_category_title);
        categoryImage = itemView.findViewById(R.id.recyclerview_category_imageviewA);
        categoryConstraintLayout = itemView.findViewById(R.id.recyclerview_category_constraint_layout);
    }
}

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    private ArrayList<CategoryDataModel> categoryDataModels;
    private Activity activity;

    public CategoryAdapter(Activity activity, ArrayList<CategoryDataModel> categoryDataModels) {

        this.activity = activity;
        this.categoryDataModels = categoryDataModels;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_category, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        CategoryDataModel categoryDataModel = categoryDataModels.get(position);

        holder.categoryName.setText(categoryDataModel.getCategoryName());

        if(categoryDataModel.getType() == 1 || categoryDataModel.getType() == 9){
            holder.categoryName.setTextColor(activity.getResources().getColor(R.color.white));
            holder.categoryConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.green));
            Picasso.get().load(R.drawable.book_3).placeholder(R.drawable.book_3).into(holder.categoryImage);

        }
        else if(categoryDataModel.getType() == 2 || categoryDataModel.getType() == 8){
            holder.categoryName.setTextColor(activity.getResources().getColor(R.color.white));
            holder.categoryConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.blue));
            Picasso.get().load(R.drawable.book_1).placeholder(R.drawable.book_1).into(holder.categoryImage);

        }
        else if(categoryDataModel.getType() == 3 || categoryDataModel.getType() == 7){
            holder.categoryName.setTextColor(activity.getResources().getColor(R.color.white));
            holder.categoryConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.orange));
            Picasso.get().load(R.drawable.book_4).placeholder(R.drawable.book_4).into(holder.categoryImage);

        }
        else if(categoryDataModel.getType() == 4 || categoryDataModel.getType() == 6){
            holder.categoryName.setTextColor(activity.getResources().getColor(R.color.grey));
            holder.categoryConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.yellow));
            Picasso.get().load(R.drawable.book_2).placeholder(R.drawable.book_2).into(holder.categoryImage);

        }
        else if(categoryDataModel.getType() == 5){
            holder.categoryName.setTextColor(activity.getResources().getColor(R.color.white));
            holder.categoryConstraintLayout.setBackgroundColor(activity.getResources().getColor(R.color.teal));
            Picasso.get().load(R.drawable.book_3).placeholder(R.drawable.book_3).into(holder.categoryImage);

        }

        holder.categoryConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StaticData.categoryBooks = new MutableLiveData<>();

                CURRENT_BOOK_ID = categoryDataModel.getId();
                Bundle bundle = new Bundle();
                bundle.putString("book_id",categoryDataModel.getId());
                bundle.putString("fragment","store");
                bundle.putString("query","category-books");
                bundle.putString("type","books");

                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_view_all, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDataModels.size();
    }


    public void setCategoryDataModels(ArrayList<CategoryDataModel> categoryDataModels) {
        this.categoryDataModels = categoryDataModels;
        notifyDataSetChanged();
    }
}





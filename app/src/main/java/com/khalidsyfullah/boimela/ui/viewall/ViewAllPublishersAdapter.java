package com.khalidsyfullah.boimela.ui.viewall;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ViewAllPublishersViewHolder extends RecyclerView.ViewHolder {

    TextView publisherName;
    ImageView publisherImage;
    ConstraintLayout publisherConstraintLayout;

    public ViewAllPublishersViewHolder(@NonNull View itemView) {
        super(itemView);

        publisherName = itemView.findViewById(R.id.recyclerview_view_all_publishers_title);
        publisherImage = itemView.findViewById(R.id.recyclerview_view_all_publishers_image);
        publisherConstraintLayout = itemView.findViewById(R.id.recyclerview_view_all_publishers_constraint_layout_2);
    }
}

public class ViewAllPublishersAdapter extends RecyclerView.Adapter<ViewAllPublishersViewHolder>{

    private ArrayList<PublisherDataModel> viewAllPublishersDataModels;
    private Activity activity;

    public ViewAllPublishersAdapter(Activity activity, ArrayList<PublisherDataModel> viewAllPublishersDataModels) {

        this.activity = activity;
        this.viewAllPublishersDataModels = viewAllPublishersDataModels;
    }

    @NonNull
    @Override
    public ViewAllPublishersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_view_all_publishers, parent, false);
        ViewAllPublishersViewHolder ViewAllPublishersViewHolder = new ViewAllPublishersViewHolder(view);
        return ViewAllPublishersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllPublishersViewHolder holder, int position) {

        PublisherDataModel publisherDataModel = viewAllPublishersDataModels.get(position);

        holder.publisherName.setText(publisherDataModel.getName());
        Picasso.get().load(publisherDataModel.getImage()).into(holder.publisherImage);

        holder.publisherConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(activity, R.id.nav_host_fragment_main).navigate(R.id.action_navigation_store_to_navigation_publisher_details);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllPublishersDataModels.size();
    }
}





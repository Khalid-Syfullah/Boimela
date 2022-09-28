package com.khalidsyfullah.boimela.ui.book;


import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.gms.common.util.ArrayUtils;
import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.global.StaticData;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class ReviewViewHolder extends RecyclerView.ViewHolder{

    TextView userName, commentTitle, commentBody, commentTime, like, dislike;
    ImageView userImage, likeImage, dislikeImage;
    RatingBar ratingBar;
    CardView reviewCardView;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);

        userImage = itemView.findViewById(R.id.recyclerview_review_user_image);
        userName = itemView.findViewById(R.id.recyclerview_review_user_name);
        commentTitle = itemView.findViewById(R.id.recyclerview_review_comment_title);
        commentBody = itemView.findViewById(R.id.recyclerview_review_comment_body);
        commentTime = itemView.findViewById(R.id.recyclerview_review_comment_time);
        like = itemView.findViewById(R.id.recyclerview_review_like);
        dislike = itemView.findViewById(R.id.recyclerview_review_dislike);
        likeImage = itemView.findViewById(R.id.recyclerview_review_like_image);
        dislikeImage = itemView.findViewById(R.id.recyclerview_review_dislike_image);
        ratingBar = itemView.findViewById(R.id.recyclerview_review_rating_bar);

    }

}

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {


    private ArrayList<ReviewDataModel> reviewDataModels;
    private Context context;



    public ReviewAdapter( ArrayList<ReviewDataModel> reviewDataModels) {

        this.reviewDataModels = reviewDataModels;

    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_review, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        context = parent.getContext();
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        ReviewDataModel reviewDataModel = reviewDataModels.get(position);

        holder.userName.setText(reviewDataModel.getName());
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.parseColor("#52341E"))
                .endConfig()
                .buildRound(reviewDataModel.getName().toUpperCase().substring(0, 1), Color.parseColor("#FFFFFF"));
        holder.userImage.setImageDrawable(drawable);

        holder.commentTitle.setText(reviewDataModel.getCommentTitle());
        holder.commentBody.setText(reviewDataModel.getCommentBody());
        holder.commentTime.setText(reviewDataModel.getCommentTime().split("T")[0]);

        holder.like.setText(String.valueOf(reviewDataModel.getLikes().length));
        holder.dislike.setText(String.valueOf(reviewDataModel.getDislikes().length));
        holder.ratingBar.setRating(reviewDataModel.getRating());
//      Like Dislike icon setup


        if(hasAlreadyLiked(reviewDataModel)){
            holder.like.setTextColor(context.getResources().getColor(R.color.yellowish));
            holder.like.setTypeface(holder.like.getTypeface(), Typeface.BOLD);
            holder.likeImage.setImageResource(R.drawable.ic_liked);
            holder.dislike.setTextColor(context.getResources().getColor(R.color.black));
            holder.dislike.setTypeface(holder.dislike.getTypeface(), Typeface.NORMAL);
            holder.dislikeImage.setImageResource(R.drawable.dislike);

        }
        else if(hasAlreadyDisliked(reviewDataModel)){
            holder.dislike.setTextColor(context.getResources().getColor(R.color.yellowish));
            holder.dislike.setTypeface(holder.dislike.getTypeface(), Typeface.BOLD);
            holder.dislikeImage.setImageResource(R.drawable.ic_disliked);
            holder.like.setTextColor(context.getResources().getColor(R.color.black));
            holder.like.setTypeface(holder.like.getTypeface(), Typeface.NORMAL);
            holder.likeImage.setImageResource(R.drawable.like);
        }
        else {
            holder.dislike.setTextColor(context.getResources().getColor(R.color.black));
            holder.dislike.setTypeface(holder.like.getTypeface(), Typeface.NORMAL);
            holder.dislikeImage.setImageResource(R.drawable.dislike);
            holder.like.setTextColor(context.getResources().getColor(R.color.black));
            holder.like.setTypeface(holder.like.getTypeface(), Typeface.NORMAL);
            holder.likeImage.setImageResource(R.drawable.like);
        }

        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.likeImage.setEnabled(false);
                holder.dislikeImage.setEnabled(false);

                ReviewDataModel reviewDataModel1 = new ReviewDataModel(reviewDataModel.get_id(), StaticData.currentUserData.getValue().getCurrentUser().getId());
                RestAPI restAPI = RetrofitClient.createRetrofitClient();
                if(hasAlreadyLiked(reviewDataModel)){
                    holder.like.setText(String.valueOf(Integer.valueOf(holder.like.getText().toString())-1));

                }
                else if(hasAlreadyDisliked(reviewDataModel)){
                    holder.like.setText(String.valueOf(Integer.valueOf(holder.like.getText().toString())+1));
                    holder.dislike.setText(String.valueOf(Integer.valueOf(holder.dislike.getText().toString())-1));
                }
                else {
                    holder.like.setText(String.valueOf(Integer.valueOf(holder.like.getText().toString())+1));
                }

                Log.d("Like", "ReviewID: " + reviewDataModel.get_id() + " UserID: " + StaticData.currentUserData.getValue().getCurrentUser().getId());

                Call<ArrayList<ReviewDataModel>> likeCall = restAPI.updateLike(StaticData.currentUserData.getValue().getToken(), reviewDataModel1);
                likeCall.enqueue(new Callback<ArrayList<ReviewDataModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ReviewDataModel>> call, Response<ArrayList<ReviewDataModel>> response) {
                        if (response.code() == 200) {
                            holder.likeImage.setEnabled(true);
                            holder.dislikeImage.setEnabled(true);

                            getReviews();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ReviewDataModel>> call, Throwable t) {
                        Log.d("ReviewAdapter", "Response failure");
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        holder.likeImage.setEnabled(true);
                        holder.dislikeImage.setEnabled(true);

                        holder.like.setText(String.valueOf(reviewDataModel.getLikes().length));
                        holder.dislike.setText(String.valueOf(reviewDataModel.getDislikes().length));


                    }
                });
            }
        });

        holder.dislikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.likeImage.setEnabled(false);
                holder.dislikeImage.setEnabled(false);

                ReviewDataModel reviewDataModel1 = new ReviewDataModel(reviewDataModel.get_id(), StaticData.currentUserData.getValue().getCurrentUser().getId());

                RestAPI restAPI = RetrofitClient.createRetrofitClient();

                Log.d("Dislike", "ReviewID: " + reviewDataModel.get_id() + " UserID: " + StaticData.currentUserData.getValue().getCurrentUser().getId());

                if(hasAlreadyLiked(reviewDataModel)){
                    holder.like.setText(String.valueOf(Integer.valueOf(holder.like.getText().toString())-1));
                    holder.dislike.setText(String.valueOf(Integer.valueOf(holder.dislike.getText().toString())+1));
                }
                else if(hasAlreadyDisliked(reviewDataModel)){
                    holder.dislike.setText(String.valueOf(Integer.valueOf(holder.dislike.getText().toString())-1));
                }
                else {
                    holder.dislike.setText(String.valueOf(Integer.valueOf(holder.dislike.getText().toString())+1));
                }
                Call<ArrayList<ReviewDataModel>> dislikeCall = restAPI.updateDislike(StaticData.currentUserData.getValue().getToken(), reviewDataModel1);
                dislikeCall.enqueue(new Callback<ArrayList<ReviewDataModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ReviewDataModel>> call, Response<ArrayList<ReviewDataModel>> response) {

                        if (response.code() == 200) {
                            holder.likeImage.setEnabled(true);
                            holder.dislikeImage.setEnabled(true);
                            getReviews();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ReviewDataModel>> call, Throwable t) {
                        Log.d("ReviewAdapter", "Response failure");
                        holder.likeImage.setEnabled(true);
                        holder.dislikeImage.setEnabled(true);
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        holder.like.setText(String.valueOf(reviewDataModel.getLikes().length));
                        holder.dislike.setText(String.valueOf(reviewDataModel.getDislikes().length));
                    }
                });

            }
        });


    }
    @Override
    public int getItemCount() {
        return reviewDataModels.size();

    }




    private boolean hasAlreadyDisliked(ReviewDataModel reviewDataModel) {

        return ArrayUtils.contains(reviewDataModel.getDislikes(), StaticData.currentUserData.getValue().getCurrentUser().getId());
    }

    private boolean hasAlreadyLiked(ReviewDataModel reviewDataModel) {
        return ArrayUtils.contains(reviewDataModel.getLikes(), StaticData.currentUserData.getValue().getCurrentUser().getId());
    }

  

    public void setReviewDataModels(ArrayList<ReviewDataModel> reviewDataModels) {
        this.reviewDataModels = reviewDataModels;
        notifyDataSetChanged();
    }

    private void getReviews() {


        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        Call<ArrayList<ReviewDataModel>> getReviewCall = restAPI.getReviews(StaticData.currentUserData.getValue().getToken());
        getReviewCall.enqueue(new Callback<ArrayList<ReviewDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ReviewDataModel>> call, Response<ArrayList<ReviewDataModel>> response) {

                if (response.code() == 200) {

                    ArrayList<ReviewDataModel> reviewDataModelsFetch = (ArrayList<ReviewDataModel>) response.body();
                    ArrayList<ReviewDataModel> reviewDataModels = new ArrayList<>();

                    for(int i=0;i<reviewDataModelsFetch.size();i++){

                        ReviewDataModel reviewDataModel = reviewDataModelsFetch.get(i);

                        if(reviewDataModel.getBookId().equals(CURRENT_BOOK_ID)) {

                            reviewDataModel.setLike(reviewDataModel.getLikes().length);
                            reviewDataModel.setDislike(reviewDataModel.getDislikes().length);

                            reviewDataModels.add(reviewDataModel);
                            ReviewAdapter.this.setReviewDataModels(reviewDataModels);
                        }

                    }

                } else {
                    Log.d("ReviewAdapter", "Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ReviewDataModel>> call, Throwable t) {
                Log.d("ReviewAdapter", "Response failed");
            }
        });
    }

}

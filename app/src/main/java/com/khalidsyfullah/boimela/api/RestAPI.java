package com.khalidsyfullah.boimela.api;



import com.khalidsyfullah.boimela.datamodel.AudioBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGenreDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesDataModel;
import com.khalidsyfullah.boimela.datamodel.HomeDataModel;
import com.khalidsyfullah.boimela.datamodel.PopularAuthorsDataModel;
import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestAPI {

    @POST("/api/v1/auth/login")
    Call<UserDataModel> loginUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/auth/refreshToken")
    Call<ResponseDataModel> refreshToken(@Header("Cookie") String cookie);

    @POST("/api/v1/auth/signup")
    Call<UserDataModel> signupUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/home")
    Call<BookDataModel> getHomeBooks(@Body AuthDataModel authDataModel);

    @GET("/api/v1/auth/resetPassword")
    Call<UserDataModel> resetPassword(@Body UserDataModel userDataModel);

    @GET("/api/v1/home/")
    Call<HomeDataModel> getHomeRoutes();

    @GET("/api/v1/collection/id/{collection_id}")
    Call<HomeDataModel> getCollectionByID(@Path("collection_id") String collectionID);

    @GET("/api/v1/series?page=1&limit=5")
    Call<BookSeriesDataModel> getBookSeries();

    @GET("/api/v1/home/audio")
    Call<AudioBooksDataModel> getAudioBooks();

    @GET("/api/v1/home/class")
    Call<BookGenreDataModel> getGenreBooks();

    @GET("/api/v1/home/popularAuthor")
    Call<PopularAuthorsDataModel> getPopularAuthors();

    @GET("/api/user/review")
    Call<ArrayList<ReviewDataModel>> getReviews(@Header("auth-token") String authToken);

    @POST("/api/user/review")
    Call<ReviewDataModel> uploadNewReview(@Header("auth-token") String authToken,
                                          @Body ReviewDataModel reviewDataModel);

    @PATCH("/api/user/review/like")
    Call<ArrayList<ReviewDataModel>> updateLike(@Header("auth-token") String authToken,
                                                @Body ReviewDataModel reviewDataModel);

    @PATCH("/api/user/review/dislike")
    Call<ArrayList<ReviewDataModel>> updateDislike(@Header("auth-token") String authToken,
                                                   @Body ReviewDataModel reviewDataModel);

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);

    @Streaming
    @GET
    Call<ResponseBody> downloadAudio(@Url String fileUrl);

}

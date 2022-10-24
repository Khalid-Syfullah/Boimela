package com.khalidsyfullah.boimela.api;



import com.khalidsyfullah.boimela.datamodel.AudioBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.AuthorsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookCategoryDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGetDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGenreDataModel;
import com.khalidsyfullah.boimela.datamodel.BookSeriesDataModel;
import com.khalidsyfullah.boimela.datamodel.CategoryBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.HomeDataModel;
import com.khalidsyfullah.boimela.datamodel.PopularAuthorsDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.PublishersDataModel;
import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.datamodel.StoreBooksDataModel;
import com.khalidsyfullah.boimela.datamodel.TopListDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.datamodel.UserProfileDataModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestAPI {

    @POST("/api/v1/auth/login")
    Call<UserDataModel> loginUser(@Body UserDataModel userDataModel);

    @GET("/api/v1/profile")
    Call<UserProfileDataModel> userProfile(@Header("Authorization") String authToken);

    @POST("/api/v1/auth/refreshToken")
    Call<ResponseDataModel> refreshToken(@Header("Cookie") String cookie);

    @POST("/api/v1/auth/signup")
    Call<UserDataModel> signupUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/home")
    Call<BookDataModel> getHomeBooks(@Body AuthDataModel authDataModel);

    @GET("/api/v1/auth/resetPassword")
    Call<UserDataModel> resetPassword(@Body UserDataModel userDataModel);

    @GET("/api/v1/home")
    Call<HomeDataModel> getHomeRoutes();

    @GET("/api/v1/collection/id/{collection_id}")
    Call<HomeDataModel> getCollectionByID(@Path("collection_id") String collectionID);

    @GET("/api/v1/series")
    Call<BookSeriesDataModel> getBookSeries(@Query("page") String page, @Query("limit") String limit);

    @GET("/api/v1/home/audio")
    Call<AudioBooksDataModel> getAudioBooks();

    @GET("/api/v1/home/class")
    Call<BookGenreDataModel> getGenreBooks();

    @GET("/api/v1/home/allClass")
    Call<StoreBooksDataModel> getStoreBooks();

    @GET("/api/v1/home/popularAuthor")
    Call<PopularAuthorsDataModel> getPopularAuthors();

    @GET("/api/v1/author")
    Call<AuthorsDataModel> getAllAuthors(@Query("page") String page, @Query("limit") String limit);

    @GET("/api/v1/publisher")
    Call<PublishersDataModel> getAllPublishers(@Query("page") String page, @Query("limit") String limit);

    @GET("/api/v1/home/topList")
    Call<TopListDataModel> getTopList(@Query("books") String books, @Query("authors") String authors, @Query("publishers") String publishers);

    @GET("/api/v1/category")
    Call<BookCategoryDataModel> getAllCategories();

    @GET("/api/v1/category/{category_id}")
    Call<CategoryBooksDataModel> getCategoryBooksByID(@Path("category_id") String categoryID);

    @GET("/api/v1/book/{book_id}")
    Call<BookGetDetailsDataModel> getBookDetailsByID(@Path("book_id") String bookID);

    @GET("/api/v1/author/{author_id}")
    Call<AuthorDetailsDataModel> getAuthorDetailsByID(@Path("author_id") String authorID);

    @GET("/api/v1/publisher/{publisher_id}")
    Call<PublisherDetailsDataModel> getPublisherDetailsByID(@Path("publisher_id") String publisherID);

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

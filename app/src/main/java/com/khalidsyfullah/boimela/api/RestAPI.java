package com.khalidsyfullah.boimela.api;



import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestAPI {

    @POST("/api/v1/auth/login")
    Call<UserDataModel> loginUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/auth/refreshToken")
    Call<ResponseDataModel> refreshToken(@Header("Cookie") String cookie);

    @POST("/api/v1/auth/signup")
    Call<UserDataModel> signupUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/auth/resetPassword")
    Call<UserDataModel> resetPassword(@Body UserDataModel userDataModel);

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);

    @Streaming
    @GET
    Call<ResponseBody> downloadAudio(@Url String fileUrl);

}

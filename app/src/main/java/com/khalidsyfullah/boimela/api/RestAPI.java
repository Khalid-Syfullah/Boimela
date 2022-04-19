package com.khalidsyfullah.boimela.api;



import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestAPI {

    @POST("/api/v1/auth/login")
    Call<UserDataModel> loginUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/auth/refreshToken")
    Call<ResponseDataModel> refreshToken(@Header("Cookie") String cookie);

    @POST("/api/v1/auth/signup")
    Call<UserDataModel> signupUser(@Body UserDataModel userDataModel);

    @POST("/api/v1/auth/resetPassword")
    Call<UserDataModel> resetPassword(@Body UserDataModel userDataModel);

}

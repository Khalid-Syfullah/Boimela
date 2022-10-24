package com.khalidsyfullah.boimela.Repo;


import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.bookDetails;
import static com.khalidsyfullah.boimela.global.StaticData.currentUserData;

import android.util.Log;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.BookGetDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.LoginDataModel;
import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.datamodel.UserProfileDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepo {

    private RestAPI restAPI;
    public AuthRepo() {
        restAPI = RetrofitClient.createRetrofitClient();
    }

    public void loginUser(UserDataModel userDataModel, RemoteRequestInterface requestInterface){

        restAPI = RetrofitClient.createRetrofitClient();
        Call<UserDataModel> call=restAPI.loginUser(userDataModel);
        call.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {


                if(response.code()==200){
                    Log.d("AuthRepo", "Login success: "+response.code());
                    Log.d("AuthRepo", response.headers().get("Set-Cookie"));
                    List<String> cookieList = response.headers().values("Set-Cookie");

                    String refreshTokenPart = (cookieList.get(0).split(";"))[0];
                    String refreshToken = refreshTokenPart.split("=")[1];

                    StaticData.cookie.setValue(response.headers().get("Set-Cookie"));
                    StaticData.refreshToken.setValue(refreshToken);
                    requestInterface.onSuccess(response.body().getMessage());

                }

                else{


                    try {
                        JSONObject jsonObject = new JSONObject( response.errorBody().string() );
                        requestInterface.onFailure(jsonObject.getString("message"));

                    } catch (JSONException e) {
                        requestInterface.onFailure(e.toString());
                    } catch (IOException e) {
                        requestInterface.onFailure(e.toString());
                    }
                }


            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {
                requestInterface.onFailure(t.getMessage());

            }
        });

    }

    public void refreshToken(String cookie, RemoteRequestInterface remoteRequestInterface){

        restAPI = RetrofitClient.createRetrofitClient();
        Call<ResponseDataModel> call = restAPI.refreshToken(cookie);
        call.enqueue(new Callback<ResponseDataModel>() {
            @Override
            public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {

                Log.d("AuthRepo", "Access Token success: "+response.code());

                if(response.code()==200){
                    Log.d("AuthRepo", "Access Token success: "+response.code());
                    StaticData.accessToken.setValue(response.body().getAccessToken());
                    Log.d("AuthRepo","Access Token: "+StaticData.accessToken.getValue());
                    remoteRequestInterface.onSuccess(response.body().getMessage());
                }

                else {
                    Log.d("AuthRepo", "Access Token Failure: "+response.code());

                    try {
                        JSONObject jsonObject = new JSONObject( response.errorBody().string() );
                        remoteRequestInterface.onFailure(jsonObject.getString("message"));

                    } catch (JSONException e) {
                        remoteRequestInterface.onFailure(e.toString());
                    } catch (IOException e) {
                        remoteRequestInterface.onFailure(e.toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                Log.d("AuthRepo", "Access Token Failure: "+t.getMessage());
                remoteRequestInterface.onFailure(t.getMessage());

            }
        });
    }

    public void userProfile(String accessToken, RemoteRequestInterface remoteRequestInterface) {

        restAPI = RetrofitClient.createRetrofitClient();
        Call<UserProfileDataModel> call = restAPI.userProfile(accessToken);

        call.enqueue(new Callback<UserProfileDataModel>() {
            @Override
            public void onResponse(Call<UserProfileDataModel> call, Response<UserProfileDataModel> response) {

                if(response.code() == 200){

                    UserDataModel userDataModel = response.body().getUser();

                    if(userDataModel != null) {

                        Log.d("AuthRepo", "Message (User Profile): " + response.body().getMessage());
                        Log.d("AuthRepo", "User Name: " + userDataModel.getName());
                        Log.d("AuthRepo", "User Phone: " + userDataModel.getPhone());

                        currentUserData.setValue(new LoginDataModel(userDataModel, StaticData.refreshToken.getValue()));
                        remoteRequestInterface.onSuccess(response.body().getMessage());

                    }
                    else{
                        remoteRequestInterface.onFailure(response.body().getMessage());

                    }
                }
                else {

                    try {
                        JSONObject jsonObject = new JSONObject( response.errorBody().string() );
                        remoteRequestInterface.onFailure(jsonObject.getString("message"));

                    } catch (JSONException e) {
                        remoteRequestInterface.onFailure(e.toString());
                    } catch (IOException e) {
                        remoteRequestInterface.onFailure(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileDataModel> call, Throwable t) {
                remoteRequestInterface.onFailure(t.getMessage());

            }
        });

    }


    public void signupUser(UserDataModel userDataModel, RemoteRequestInterface requestInterface) {
        Call<UserDataModel> call=restAPI.signupUser(userDataModel);
        call.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {

                if(response.code()==200){

                    requestInterface.onSuccess(response.body().getMessage());
                }

                else {

                    try {
                        JSONObject jsonObject = new JSONObject( response.errorBody().string() );
                        requestInterface.onFailure(jsonObject.getString("message"));

                    } catch (JSONException e) {
                        requestInterface.onFailure(e.toString());
                    } catch (IOException e) {
                        requestInterface.onFailure(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {
                requestInterface.onFailure(t.getMessage());

            }
        });
    }

    public void resetPassword(UserDataModel userDataModel, RemoteRequestInterface requestInterface) {
        Call<UserDataModel> call = restAPI.resetPassword(userDataModel);

        call.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {

                if(response.code()==200){

                    requestInterface.onSuccess(response.body().getMessage());
                }

                else {

                    try {
                        JSONObject jsonObject = new JSONObject( response.errorBody().string() );
                        requestInterface.onFailure(jsonObject.getString("message"));

                    } catch (JSONException e) {
                        requestInterface.onFailure(e.toString());
                    } catch (IOException e) {
                        requestInterface.onFailure(e.toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {

                requestInterface.onFailure(t.getMessage());
            }
        });

    }
}

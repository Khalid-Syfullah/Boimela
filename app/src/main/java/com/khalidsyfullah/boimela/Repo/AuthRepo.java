package com.khalidsyfullah.boimela.Repo;


import android.util.Log;

import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
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
        Call<UserDataModel> call=restAPI.loginUser(userDataModel);
        call.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {

                if(response.code()==200){
                    Log.d("Cookie", response.headers().get("Set-Cookie"));
                    List<String> cookieList = response.headers().values("Set-Cookie");

                    String refreshTokenPart = (cookieList.get(0).split(";"))[0];
                    String refreshToken = refreshTokenPart.split("=")[1];

                    StaticData.cookie.setValue(response.headers().get("Set-Cookie"));
                    StaticData.refreshToken.setValue(refreshToken);

                    requestInterface.onSuccess("Success");

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

        Call<ResponseDataModel> call = restAPI.refreshToken(cookie);
        call.enqueue(new Callback<ResponseDataModel>() {
            @Override
            public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {


                if(response.code()==200){

                    StaticData.accessToken.setValue(response.body().getAccessToken());
                    Log.d("AccessToken","Access Token: "+StaticData.accessToken.getValue());
                    remoteRequestInterface.onSuccess(response.body().getMessage());
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
            public void onFailure(Call<ResponseDataModel> call, Throwable t) {

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

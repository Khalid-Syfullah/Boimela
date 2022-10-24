package com.khalidsyfullah.boimela.ui.splash;

import static com.khalidsyfullah.boimela.global.StaticData.LOGIN_USER_PASS;
import static com.khalidsyfullah.boimela.global.StaticData.LOGIN_USER_PHONE;
import static com.khalidsyfullah.boimela.global.StaticData.homeRouteIDs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khalidsyfullah.boimela.Repo.AuthRepo;
import com.khalidsyfullah.boimela.Repo.RemoteRequestInterface;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.HomeDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.khalidsyfullah.boimela.ui.auth.AuthActivity;
import com.khalidsyfullah.boimela.ui.home.HomeViewModel;
import com.khalidsyfullah.boimela.ui.navigation.NavigationActivity;
import com.khalidsyfullah.boimela.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splash_logo_rays);
        progressBar = findViewById(R.id.splash_progressbar);
        loginBtn = findViewById(R.id.splash_login_btn);
        signupBtn = findViewById(R.id.splash_signup_btn);


        Animation rotateAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.accelerate_rotate);
        imageView.startAnimation(rotateAnimation);

        progressBar.setVisibility(View.VISIBLE);
        getHomeRoutes();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    private void getHomeRoutes(){

        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        Call<HomeDataModel> call = restAPI.getHomeRoutes();
        call.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {

                HomeDataModel homeDataModel = response.body();
                if(response.isSuccessful()){


                    Log.d("HomeRoutes","message: "+homeDataModel.getMessage());

                    homeRouteIDs = homeDataModel.getHome();
                    for(int i=0;i<homeRouteIDs.length;i++){
                        Log.d("HomeRoutes", "home: "+"#"+i+": "+homeRouteIDs[i]);
                    }


                    Handler handler =new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            checkSharedPrefs();

//                progressBar.setVisibility(View.GONE);
//                Intent intent =new Intent(SplashActivity.this, AuthActivity.class);
//                startActivity(intent);
//                finish();

                        }
                    },2000);

                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {

            }
        });
    }


    private void checkSharedPrefs() {

        SharedPreferences sharedPreferences = getSharedPreferences(StaticData.LOGIN_SHARED_PREFS, MODE_PRIVATE);
        String phone, password;
        if (sharedPreferences.contains(LOGIN_USER_PHONE) && sharedPreferences.contains(LOGIN_USER_PASS)) {

            phone = sharedPreferences.getString(LOGIN_USER_PHONE, "");
            password = sharedPreferences.getString(LOGIN_USER_PASS, "");

            AuthRepo authRepo = new AuthRepo();
            UserDataModel userDataModel = new UserDataModel("+88"+phone, password);

            authRepo.loginUser(userDataModel, new RemoteRequestInterface() {
                @Override
                public void onSuccess(String msg) {

                    Log.d("Cookie","Saved Cookie: "+ StaticData.cookie.getValue());
                    authRepo.refreshToken(StaticData.cookie.getValue(), new RemoteRequestInterface() {
                        @Override
                        public void onSuccess(String msg) {

                            authRepo.userProfile(StaticData.accessToken.getValue(), new RemoteRequestInterface() {
                                @Override
                                public void onSuccess(String msg) {
                                    Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finishAffinity();
                                    Log.d("SplashActivity","User Profile read success!");
                                }

                                @Override
                                public void onFailure(String msg) {
                                    progressBar.setVisibility(View.GONE);
                                    loginBtn.setVisibility(View.VISIBLE);
                                    signupBtn.setVisibility(View.VISIBLE);
                                    StaticData.failureAlertDialog(SplashActivity.this,"Login Failed: "+msg);
                                    Log.d("SplashActivity","User Profile read failure!");
                                }
                            });



                        }

                        @Override
                        public void onFailure(String msg) {

                            progressBar.setVisibility(View.GONE);
                            loginBtn.setVisibility(View.VISIBLE);
                            signupBtn.setVisibility(View.VISIBLE);
                            StaticData.failureAlertDialog(SplashActivity.this,"Login Failed: "+msg);
                            Log.d("SplashActivity","User Profile read failure, no connection!");

                        }
                    });
                }

                @Override
                public void onFailure(String msg) {

                    progressBar.setVisibility(View.GONE);
                    loginBtn.setVisibility(View.VISIBLE);
                    signupBtn.setVisibility(View.VISIBLE);

                    StaticData.failureAlertDialog(SplashActivity.this,"Connection failed: "+msg);

                }
            });


        }

        else{

            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finishAffinity();
//            loginBtn.setVisibility(View.VISIBLE);
//            signupBtn.setVisibility(View.VISIBLE);
        }


    }




}
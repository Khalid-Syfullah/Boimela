package com.khalidsyfullah.boimela.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.khalidsyfullah.boimela.ui.auth.AuthActivity;
import com.khalidsyfullah.boimela.ui.navigation.NavigationActivity;
import com.khalidsyfullah.boimela.R;

public class SplashActivity extends AppCompatActivity {


    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splash_logo_rays);
        progressBar = findViewById(R.id.splash_progressbar);




        Animation rotateAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.accelerate_rotate);
        imageView.startAnimation(rotateAnimation);

        Handler handler =new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.GONE);
                Intent intent =new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}
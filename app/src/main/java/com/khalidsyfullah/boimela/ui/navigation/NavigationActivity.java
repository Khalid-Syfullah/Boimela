package com.khalidsyfullah.boimela.ui.navigation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.LoginDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.khalidsyfullah.boimela.ui.auth.AuthActivity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        View headerView = navigationView.getHeaderView(0);
        ImageView headerImage = headerView.findViewById(R.id.nav_header_image);
        TextView headerTitle = headerView.findViewById(R.id.nav_header_title);
        TextView headerSubtitle = headerView.findViewById(R.id.nav_header_subtitle);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);

        MenuItem loginItem = navigationView.getMenu().findItem(R.id.login);
        MenuItem logoutItem = navigationView.getMenu().findItem(R.id.logout);

        if(StaticData.currentUserData.getValue() == null) {
            headerSubtitle.setVisibility(View.GONE);
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
        }
        else{
            headerSubtitle.setVisibility(View.VISIBLE);
            loginItem.setVisible(false);
            logoutItem.setVisible(true);

            StaticData.currentUserData.observe(this, new Observer<LoginDataModel>() {
                @Override
                public void onChanged(LoginDataModel loginDataModel) {
                    headerTitle.setText(loginDataModel.getCurrentUser().getName());
                    headerSubtitle.setText(loginDataModel.getCurrentUser().getPhone());
                    TextDrawable drawable = TextDrawable.builder()
                            .beginConfig()
                            .textColor(Color.parseColor("#52341E"))
                            .endConfig()
                            .buildRound(headerTitle.getText().toString().toUpperCase().substring(0, 1), Color.parseColor("#FFFFFF"));
                    headerImage.setImageDrawable(drawable);

                }
            });
        }


        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if (Build.VERSION.SDK_INT > 30) {
            boolean canWriteSettings = Settings.System.canWrite(getApplicationContext());
            if (!canWriteSettings) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                startActivity(intent);
            }

        }



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.english){
                    StaticData.languageAlertDialog(NavigationActivity.this,"en");
                }
                else if(id == R.id.bangla){
                    StaticData.languageAlertDialog(NavigationActivity.this,"bn");
                }

                else if (id == R.id.shareApp){
                    Intent shareIntent=new Intent((Intent.ACTION_SEND));
                    shareIntent.setType("text/plain");
                    String shareBody="Boimela: +\n http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
                    String shareSub="Boimela";
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(shareIntent,"Share Using"));

                }
                else if (id == R.id.emailUs){

                    String email = "boimela@gmail.com";
                    String subject = "Boimela - User";
                    String body = "[REPLACE THE TEXT HERE]";
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, body);
                    startActivityForResult(intent,200);
                }

                else if(id == R.id.sendReview){
                    Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSd3RqJLIIQ5iot2rqdhBJIEogKZQj8h484rMWbAF4spy1fWBQ/viewform?usp=sf_link");
                    Intent review = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(review);
                }

                else if(id == R.id.rateApp) {

                    Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    startActivity(goToMarket);


                }

                else if(id == R.id.login){
                    Intent intent = new Intent(NavigationActivity.this, AuthActivity.class);
                    startActivity(intent);
                }

                else if(id == R.id.logout){
                    logoutAlertDialog(getResources().getString(R.string.logout),getResources().getString(R.string.are_you_sure),getResources().getString(R.string.logout),getResources().getString(R.string.cancel));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void logoutAlertDialog(String title, String message, String submit, String cancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this, R.style.CustomAlertDialog);

        View view = LayoutInflater.from(NavigationActivity.this).inflate(R.layout.alert_custom_title, null);
        TextView titleText = view.findViewById(R.id.alert_custom_title);
        TextView messageText = view.findViewById(R.id.alert_custom_message);
        Button submitBtn = view.findViewById(R.id.alert_custom_title_submit_btn);
        Button cancelBtn = view.findViewById(R.id.alert_custom_title_cancel_btn);

        titleText.setText(title);
        messageText.setText(message);
        submitBtn.setText(submit);
        cancelBtn.setText(cancel);

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences loginSharedPrefs = getSharedPreferences(StaticData.LOGIN_SHARED_PREFS, MODE_PRIVATE);
                loginSharedPrefs.edit().clear().apply();
                alertDialog.dismiss();
                finishAffinity();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
                alertDialog.dismiss();
            }
        });
    }




}
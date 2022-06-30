package com.khalidsyfullah.boimela.ui.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.global.StaticData;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        boolean canWriteSettings = Settings.System.canWrite(getApplicationContext());
        if(!canWriteSettings) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(intent);
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
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }




}
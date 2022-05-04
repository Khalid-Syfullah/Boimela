package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.webView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.khalidsyfullah.boimela.R;



public class ReaderActivity extends AppCompatActivity {

    public static boolean volumeButtonAction = false;
    private int currentBrightness = 0, screenBrightnessValue = 0;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_reader);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_reader);
        NavigationUI.setupWithNavController(navigationView, navController);

        boolean canWriteSettings = Settings.System.canWrite(getApplicationContext());
        if(canWriteSettings) {

            Settings.System.putInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            try {
                currentBrightness = Settings.System.getInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                screenBrightnessValue = currentBrightness;

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

        }else
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(intent);
        }




    }



    @Override
    protected void onResume() {
        super.onResume();



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    if(volumeButtonAction) {
                        webView.pageUp(false);
                    }
                    else{
                        boolean canWriteSettings = Settings.System.canWrite(getApplicationContext());
                        if(canWriteSettings) {

                            try {
                                if(screenBrightnessValue <= 255) {
                                    screenBrightnessValue = Settings.System.getInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) + 10;

                                }
                                else if(screenBrightnessValue > 255){
                                    screenBrightnessValue = 255;

                                }
                            } catch (Settings.SettingNotFoundException e) {
                                e.printStackTrace();
                            }
                            Settings.System.putInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                            Settings.System.putInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue);

                        }else
                        {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                            startActivity(intent);
                        }
                    }
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    if(volumeButtonAction) {
                        webView.pageDown(false);
                    }
                    else{
                        boolean canWriteSettings = Settings.System.canWrite(getApplicationContext());
                        if(canWriteSettings) {

                            if(screenBrightnessValue >= 0) {
                                try {
                                    screenBrightnessValue = Settings.System.getInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) - 10;
                                } catch (Settings.SettingNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(screenBrightnessValue < 0){
                                screenBrightnessValue = 0;

                            }
                            Settings.System.putInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                            Settings.System.putInt(getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue);

                        }else
                        {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                            startActivity(intent);
                        }
                    }
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
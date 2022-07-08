package com.khalidsyfullah.boimela.global;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.ui.navigation.NavigationActivity;

import java.util.Locale;

public class StaticData {

    public static MutableLiveData<String> cookie = new MutableLiveData<>();
    public static MutableLiveData<String> refreshToken = new MutableLiveData<>();
    public static MutableLiveData<String> accessToken = new MutableLiveData<>();

    public static final String LOGIN_SHARED_PREFS = "login_pref";
    public static final String LOGIN_USER_PHONE = "login_phone";
    public static final String LOGIN_USER_PASS = "login_pass";

    public static MediaPlayer mediaPlayer;
    public static String mediaTitle="";
    public static String mediaStatus="";
    public static String mediaUrl="";
    public static String fileName="";
    public static String bookUrl="";
    public static String audioUrl="";

    public static boolean isMediaActive=false;
    public static boolean isMediaReset=true;

    public static void languageAlertDialog(Context ctx, String lang){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(ctx.getResources().getString(R.string.are_you_sure));
        builder.setCancelable(false);
        builder.setPositiveButton(ctx.getResources().getString(R.string.change_language), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setLocale(ctx, lang);
            }
        })
                .setNegativeButton(ctx.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    public static void setLocale(Context ctx, String localeString) {

        Locale myLocale = new Locale(localeString);
        Resources resources = ctx.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = myLocale;
        resources.updateConfiguration(configuration, displayMetrics);

        SharedPreferences localePreferences = ctx.getSharedPreferences("Language",MODE_PRIVATE);
        SharedPreferences.Editor localePreferencesEditor = localePreferences.edit();
        localePreferencesEditor.putString("Current_Language", localeString);
        localePreferencesEditor.apply();

        Intent intent =new Intent(ctx, NavigationActivity.class);
        ctx.startActivity(intent);
    }



    public static void successAlertDialog(Context ctx, String success) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ctx, R.style.CustomAlertDialog);

        View view = LayoutInflater.from(ctx).inflate(R.layout.alert_custom_title, null);
        TextView titleText = view.findViewById(R.id.alert_custom_title);
        TextView messageText = view.findViewById(R.id.alert_custom_message);
        ImageView successImg = view.findViewById(R.id.alert_custom_image);
        Button submitBtn = view.findViewById(R.id.alert_custom_title_submit_btn);
        Button cancelBtn = view.findViewById(R.id.alert_custom_title_cancel_btn);
        Button successBtn = view.findViewById(R.id.alert_custom_title_submit_btn2);

        titleText.setText("title");
        messageText.setText("message");
        submitBtn.setText("Yes");
        cancelBtn.setText("No");

        builder.setView(view);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

        titleText.setText(success);
        titleText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        successBtn.setText("Ok");
        successImg.setImageResource(R.drawable.ic_check_circle);

        messageText.setVisibility(View.GONE);
        submitBtn.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.GONE);
        successImg.setVisibility(View.VISIBLE);
        successBtn.setVisibility(View.VISIBLE);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager im = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
                alertDialog.dismiss();


            }
        });

        successBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();


            }
        });
    }

    public static void failureAlertDialog(Context ctx, String failure){
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(ctx,R.style.CustomAlertDialog);

        View view = LayoutInflater.from(ctx).inflate(R.layout.alert_custom_title,null);
        TextView titleText = view.findViewById(R.id.alert_custom_title);
        TextView messageText = view.findViewById(R.id.alert_custom_message);
        ImageView successImg = view.findViewById(R.id.alert_custom_image);
        Button submitBtn = view.findViewById(R.id.alert_custom_title_submit_btn);
        Button cancelBtn = view.findViewById(R.id.alert_custom_title_cancel_btn);
        Button successBtn = view.findViewById(R.id.alert_custom_title_submit_btn2);

        titleText.setText("title");
        messageText.setText("message");
        submitBtn.setText("Yes");
        cancelBtn.setText("No");

        builder.setView(view);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

        titleText.setText(failure);
        titleText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        successBtn.setText("Ok");
        successImg.setImageResource(R.drawable.ic_close);

        messageText.setVisibility(View.GONE);
        submitBtn.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.GONE);
        successImg.setVisibility(View.VISIBLE);
        successBtn.setVisibility(View.VISIBLE);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager im = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
                alertDialog.dismiss();

            }
        });

        successBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();


            }
        });


    }



}

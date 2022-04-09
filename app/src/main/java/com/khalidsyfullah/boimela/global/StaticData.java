package com.khalidsyfullah.boimela.global;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AlertDialog;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.ui.navigation.NavigationActivity;

import java.util.Locale;

public class StaticData {

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
}

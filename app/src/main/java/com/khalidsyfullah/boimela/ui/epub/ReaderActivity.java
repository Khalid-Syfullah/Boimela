package com.khalidsyfullah.boimela.ui.epub;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.khalidsyfullah.boimela.R;



public class ReaderActivity extends AppCompatActivity {



    private MenuViewPager viewPager;
    private MenuPagerAdapter menuPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_reader);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_reader);
        NavigationUI.setupWithNavController(navigationView, navController);

        viewPager = findViewById(R.id.reader_menu_viewpager);
        menuPagerAdapter = new MenuPagerAdapter(getSupportFragmentManager(), ReaderActivity.this);
        viewPager.setAdapter(menuPagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();



    }
}
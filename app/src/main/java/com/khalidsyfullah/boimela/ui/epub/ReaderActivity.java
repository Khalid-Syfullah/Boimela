package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.global.StaticData.bookUrl;
import static com.khalidsyfullah.boimela.global.StaticData.fileName;
import static com.khalidsyfullah.boimela.ui.epub.DrawerViewPager1.contentsAdapter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.BookmarkDataModel;
import com.khalidsyfullah.boimela.datamodel.ContentDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReaderActivity extends AppCompatActivity {

    public static boolean volumeButtonAction = false;
    private int currentBrightness = 0, screenBrightnessValue = 0;

    private TextView headerText, pageText, expandButton, progressSliderLeftText, progressSliderCenterText, progressSliderRightText;
    private ImageView headerBack, headerMenu, expandIcon, menuIcon, audioIcon, themeIcon, screenIcon, textIcon, fontIcon;
    private SeekBar pageSeekBar;
    private ProgressBar progressBar;
    private ProgressDialog mProgressDialog;
    private CardView headerCardView;
    public static WebView webView;
    private ConstraintLayout mainConstraintLayout, menuConstraintLayout, pageConstraintLayout, menuTopConstraintLayout, progressSliderConstraintLayout;
    private ViewPager viewPager, drawerViewPager;
    private MenuViewPager viewPager2;
    private MenuPagerAdapter menuPagerAdapter;
    private DrawerPagerAdapter drawerPagerAdapter;
    private SliderViewPagerAdapter2 sliderViewPagerAdapter;
    private ArrayList<SliderDataModel> sliderDataModels;
    private NavigationView drawerNavigationView;
    private DrawerLayout drawerLayout;
    private ViewGroup parent;
    private File file;
    public static ArrayList<ContentDataModel> contentDataModels;
    public static ArrayList<BookmarkDataModel> bookmarkDataModels;
    public static boolean isFormattingSupported = true, isLegacyFormattingSupported = false;

    private boolean isGranted = false;

    private String line, line1 = "", finalstr = "";
    private int i = 0;

    public static String letterSpacing="0px";
    public static String wordSpacing="1px";
    public static String textIndent="10px";
    public static String lineHeight="2";
    public static String fontFamily="bookerly";
    public static String fontSize="65%";
    public static String fontWeight="300";
    public static String textAlignment="justify";
    public static String paddingLeft="30px";
    public static String paddingRight="30px";
    public static String border="";
    public static String backgroundColorBody ="white";
    public static String colorBody="black";
    public static String colorH1="black";
    public static String colorH2="black";
    public static String colorH3="black";
    public static String colorP="black";
    public static String baseUrl="";
    public static String rootDir="";
    public static String subrootDir="";
    public static String href="";

    public static String input;

    public static String data;

    public static String start =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\" epub:prefix=\"z3998: http://www.daisy.org/z3998/2012/vocab/structure/\">" +
            "<head>" +
            "<style type=\"text/css\">" +
            "@font-face {" +
            "font-family: WebViewFont;" +
            "src: url(\"file:///android_asset/georgia.ttf\")" +
            "}"

            +


            "h1 {" +
            "color: "+colorH1+";" +
            "letter-spacing: "+letterSpacing+";\n" +
            "word-spacing: "+wordSpacing+";"+
            "line-height: "+lineHeight+";"+
            "}"

            +


            "h2 {" +
            "color: "+colorH2+";" +
            "letter-spacing: "+letterSpacing+";\n" +
            "word-spacing: "+wordSpacing+";"+
            "line-height: "+lineHeight+";"+
            "}"

            +

            "h3 {" +
            "color: "+colorH3+";" +
            "letter-spacing: "+letterSpacing+";\n" +
            "word-spacing: "+wordSpacing+";"+
            "line-height: "+lineHeight+";"+
            "}"

            +

            "p {" +
            "color: "+colorP+";" +
            "letter-spacing: "+letterSpacing+";\n" +
            "word-spacing: "+wordSpacing+";"+
            "text-indent: "+textIndent+";"+
            "line-height: "+lineHeight+";"+

            "}"

            +

            "body {" +

            "font-family: WebViewFont;" +
            "font-size: "+ fontSize +";" +
            "font-weight: "+ fontWeight +";"+
            "text-align: "+textAlignment+";" +
            "padding-left: "+paddingLeft+";"+
            "padding-right: "+paddingRight+";"+
            "border: "+border+";"+
            "background-color: "+ backgroundColorBody +";"+

            "}" +

            "</style>" +
            "</head>" +
            "<body>";
    public static String end = "</body></html>";
    public static String destination="";
    public static String bookLanguage = "";

    public static Book book;
    public static EpubReader epubReader;

    private String TAG_EPUB="EPUB_ACTIVITY";
    private boolean seekbarVisibility = true, pageVisibility = true, menuVisibility = false, isInitialized = false;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_reader);
        parent = (ViewGroup) findViewById(android.R.id.content);

        drawerLayout = findViewById(R.id.reader_drawer_layout);
        headerCardView = findViewById(R.id.reader_header_cardview);
        headerBack = findViewById(R.id.reader_header_back);
        headerText = findViewById(R.id.reader_header_title);
        headerMenu = findViewById(R.id.reader_header_menu);
        webView = findViewById(R.id.reader_webview);
        pageText = findViewById(R.id.reader_menu_page_text);
        expandButton = findViewById(R.id.reader_menu_expand_button);
        expandIcon = findViewById(R.id.reader_menu_expand_image);
        pageSeekBar = findViewById(R.id.reader_menu_page_seek_bar);
        mainConstraintLayout = findViewById(R.id.reader_constraint_layout);
        menuConstraintLayout = findViewById(R.id.reader_menu_constraint_layout);
        menuTopConstraintLayout = findViewById(R.id.reader_menu_top_layout);
        pageConstraintLayout = findViewById(R.id.reader_menu_constraint_layout_2);
        menuIcon = findViewById(R.id.reader_menu_btn);
        audioIcon = findViewById(R.id.reader_audio_btn);
        themeIcon = findViewById(R.id.reader_theme_btn);
        screenIcon = findViewById(R.id.reader_screen_btn);
        textIcon = findViewById(R.id.reader_text_btn);
        fontIcon = findViewById(R.id.reader_font_btn);
        viewPager = findViewById(R.id.reader_menu_viewpager);
        viewPager2 = findViewById(R.id.reader_menu_viewpager2);
        drawerNavigationView = findViewById(R.id.reader_nav_view);
        progressBar = findViewById(R.id.reader_progressbar);
        progressSliderConstraintLayout = findViewById(R.id.progress_slider_constraint_layout);
        progressSliderLeftText = findViewById(R.id.progress_slider_left_text);
        progressSliderCenterText = findViewById(R.id.progress_slider_center_text);
        progressSliderRightText = findViewById(R.id.progress_slider_right_text);

        headerText.setSelected(true);
        progressSliderLeftText.setSelected(true);
        progressSliderCenterText.setSelected(true);
        progressSliderRightText.setSelected(true);


        View header = drawerNavigationView.getHeaderView(0);
        drawerViewPager = header.findViewById(R.id.reader_drawer_viewpager);



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

        headerCardView.setVisibility(View.GONE);
        menuConstraintLayout.setVisibility(View.GONE);
        menuTopConstraintLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        sliderDataModels = new ArrayList<>();
        contentDataModels = new ArrayList<>();
        bookmarkDataModels = new ArrayList<>();

        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));

//
//
//        contentDataModels.add(new ContentDataModel("Contents","01",1));
//        contentDataModels.add(new ContentDataModel("Part 1","20",1));
//        contentDataModels.add(new ContentDataModel("1. Chapter 1","37",2));
//        contentDataModels.add(new ContentDataModel("2. Chapter 2","65",2));
//        contentDataModels.add(new ContentDataModel("Part 2","66",1));
//        contentDataModels.add(new ContentDataModel("3. Chapter 3","66",2));
//        contentDataModels.add(new ContentDataModel("4. Chapter 4","78",2));

//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//        contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));

        contentsAdapter = new ContentsAdapter(ReaderActivity.this, contentDataModels);

        drawerPagerAdapter = new DrawerPagerAdapter(getSupportFragmentManager(), ReaderActivity.this);
        drawerViewPager.setAdapter(drawerPagerAdapter);

        menuPagerAdapter = new MenuPagerAdapter(getSupportFragmentManager(), ReaderActivity.this);
        viewPager2.setAdapter(menuPagerAdapter);

        sliderViewPagerAdapter = new SliderViewPagerAdapter2(getSupportFragmentManager(), sliderDataModels);
        viewPager.setAdapter(sliderViewPagerAdapter);





        final Handler handler = new Handler(Looper.getMainLooper());
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String currentTime = sdf.format(Calendar.getInstance().getTime().getTime());
                progressSliderRightText.setText(currentTime);
                handler.postDelayed(this, delay);
            }
        }, delay);



        final int paddingPx = 300;
        final float MIN_SCALE = 0.8f;
        final float MAX_SCALE = 1.0f;

        ViewPager.PageTransformer transformer = new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
                float pageWidthPx = pagerWidthPx - 2 * paddingPx;

                float maxVisiblePages = pagerWidthPx / pageWidthPx;
                float center = maxVisiblePages / 2f;

                float scale;
                if (position + 0.5f < center - 0.5f || position > center) {
                    scale = MIN_SCALE;
                } else {
                    float coef;
                    if (position + 0.5f < center) {
                        coef = (position + 1 - center) / 0.5f;
                    } else {
                        coef = (center - position) / 0.5f;
                    }
                    scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
                }
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
        };

        viewPager.setPageTransformer(true, transformer);

        checkForPermissions();





        final GestureDetector gestureDetector = new GestureDetector(ReaderActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if(menuVisibility) {
                    toggle(menuVisibility, headerCardView, menuConstraintLayout, parent);
                    menuVisibility = false;
                }
                else{
                    toggle(menuVisibility, headerCardView, menuConstraintLayout, parent);
                    menuVisibility = true;
                }
//                if(menuVisibility){
//                    slideDown(menuConstraintLayout);
//                    menuVisibility = false;
//                }
//                else if(!menuVisibility){
//                    slideUp(menuConstraintLayout);
//                    menuVisibility = true;
//                }

                Log.d("WebView","TAPPED");

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }
        });


        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gestureDetector.onTouchEvent(event);
            }
        });

//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//
//                Log.d("WebView", "Progress: "+newProgress);
//            }
//        });
//        webView.setWebViewClient(new WebViewClient() {
//
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                Log.d("WebView", "Page Started");
//
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Log.d("WebView", "Page Finished");
//                //view.scrollBy(0, view.getContentHeight());
//
//
//            }
//
//        });

        webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                float maxScrollY = webView.getContentHeight() * webView.getScale() - webView.getMeasuredHeight();

                Log.d("WebView", "Max Scroll Y: "+maxScrollY);
                Log.d("WebView", "X: "+scrollX + " Y: "+scrollY+ " old_X: "+oldScrollX + " old_Y: "+oldScrollY);
                Log.d("WebView", "Progress: "+ (scrollY * 100 / maxScrollY) + "%");

                progressSliderCenterText.setText(String.format("%.1f",(scrollY * 100 / maxScrollY))+"%");
            }
        });

        headerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        headerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });


        menuConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        pageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pageVisibility) {
                    toggle2(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getResources().getString(R.string.expand));
                    expandIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up));
                    ViewGroup.LayoutParams params = menuConstraintLayout.getLayoutParams();
                    params.height = 1000;
                    menuConstraintLayout.setLayoutParams(params);
                    ViewGroup.LayoutParams params2 = menuTopConstraintLayout.getLayoutParams();
                    params2.height = 180;
                    menuTopConstraintLayout.setLayoutParams(params2);
                    pageVisibility = false;

                }
                else{
                    toggle2(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getResources().getString(R.string.collapse));
                    expandIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_down));
                    ViewGroup.LayoutParams params = menuConstraintLayout.getLayoutParams();
                    params.height = 1460;
                    menuConstraintLayout.setLayoutParams(params);
                    ViewGroup.LayoutParams params2 = menuTopConstraintLayout.getLayoutParams();
                    params2.height = 640;
                    menuTopConstraintLayout.setLayoutParams(params2);
                    pageVisibility = true;

                }
            }
        });

        expandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pageVisibility) {
                    toggle2(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getResources().getString(R.string.expand));
                    expandIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up));
                    ViewGroup.LayoutParams params = menuConstraintLayout.getLayoutParams();
                    params.height = 1000;
                    menuConstraintLayout.setLayoutParams(params);
                    ViewGroup.LayoutParams params2 = menuTopConstraintLayout.getLayoutParams();
                    params2.height = 600;
                    menuTopConstraintLayout.setLayoutParams(params2);
                    pageVisibility = false;

                }
                else{
                    toggle2(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getResources().getString(R.string.collapse));
                    expandIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_down));
                    ViewGroup.LayoutParams params = menuConstraintLayout.getLayoutParams();
                    params.height = 1200;
                    menuConstraintLayout.setLayoutParams(params);
                    ViewGroup.LayoutParams params2 = menuTopConstraintLayout.getLayoutParams();
                    params2.height = 400;
                    menuTopConstraintLayout.setLayoutParams(params2);
                    pageVisibility = true;

                }
            }
        });

        pageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0, true);
                menuIcon.setBackground(getResources().getDrawable(R.drawable.button_bordered_selected));
                audioIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                themeIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                screenIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                textIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                fontIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));

            }
        });

        audioIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1, true);
                menuIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                audioIcon.setBackground(getResources().getDrawable(R.drawable.button_bordered_selected));
                themeIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                screenIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                textIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                fontIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));

            }
        });

        themeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(2, true);
                menuIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                audioIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                themeIcon.setBackground(getResources().getDrawable(R.drawable.button_bordered_selected));
                screenIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                textIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                fontIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));

            }
        });

        screenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(3, true);
                menuIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                audioIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                themeIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                screenIcon.setBackground(getResources().getDrawable(R.drawable.button_bordered_selected));
                textIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                fontIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));

            }
        });

        textIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(4, true);
                menuIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                audioIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                themeIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                screenIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                textIcon.setBackground(getResources().getDrawable(R.drawable.button_bordered_selected));
                fontIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));

            }
        });

        fontIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(5, true);
                menuIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                audioIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                themeIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                screenIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                textIcon.setBackground(getResources().getDrawable(R.drawable.button_transparent));
                fontIcon.setBackground(getResources().getDrawable(R.drawable.button_bordered_selected));

            }
        });

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



    private void checkForPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ReaderActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(ReaderActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Log.i(TAG_EPUB,"Unexpected flow");
            } else {
                ActivityCompat.requestPermissions(ReaderActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }
        } else {

            checkStatus();


        }
    }

    private void checkStatus(){

        file = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".epub");

        if(!file.exists()){
            loadData();
        }
        else{
            showEbook();
        }

    }



    private void loadData(){


        //destination = Environment.getExternalStorageDirectory().getAbsolutePath()+"/android/data/com.khalidsyfullah.boimela/";
        //destination = Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela/";
        //Log.d(TAG_EPUB,destination);

//            progressBar.setVisibility(View.VISIBLE);
        // declare the dialog as a member field of your activity

// instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(ReaderActivity.this, R.style.CustomProgressDialog);
        mProgressDialog.setMessage("Downloading E-Book");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);


        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        restAPI.downloadFileWithDynamicUrlAsync(bookUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("Download", "server contacted and has file");

                    mProgressDialog.show();


                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                            Log.d("Download", "file download was a success? " + writtenToDisk);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            mProgressDialog.dismiss();

                            showEbook();


                        }
                    }.execute();
                }
                else {
                    Log.d("Download", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            file = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".epub");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.setMax(100);
                    mProgressDialog.setProgress((int) (fileSizeDownloaded*100/fileSize));
                    Log.d("Download", "File download: " + fileSizeDownloaded + " of " + fileSize+ " "+fileSizeDownloaded/fileSize*100+ "%");
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    private void showEbook(){


//                                progressBar.setVisibility(View.GONE);
//                                AssetManager assetManager = getActivity().getAssets();
//                                InputStream fileInputStream = null;
//                                try {
//                                    fileInputStream = assetManager.open("the-alchemist.epub");
//                                    // Load Book from fileInputStream
//                                    Book book = (new EpubReader()).readEpub(fileInputStream);
//                                    data = new String(book.getContents().get(3).getData());


//        OPEN EPUB
        destination = Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela";
        file = new File(destination + File.separator + fileName + ".epub");
        //file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + fileName + ".epub");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);


//            LOAD EPUB USING EPUBLIB FROM FILEINPUTSTREAM
            epubReader = new EpubReader();
            book = epubReader.readEpub(fileInputStream);
            progressSliderCenterText.setText(book.getTitle());
            headerText.setText(book.getTitle());

//            data = new String(book.getContents().get(0).getData());

//            LOAD EPUB MANUALLY FROM FILE EXTRACTION
//            OPEN DIRECTORY TO EXTRACT EPUB
            destination = Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela/EPUB/"+fileName;
            File file2 = new File(destination);
            unzip(file, file2);
//            unpackZip(destination,"abc.epub");


//            PARSE ROOT DIRECTORY NAME
            file2 = new File(destination+"/META-INF/container.xml");
            fileInputStream = new FileInputStream(file2);
            String parsedDir = xmlParser(fileInputStream, "rootfile", "full-path");
            Log.d("XMLParser","Parsed: "+parsedDir);

            String[] rootFileSeparated = parsedDir. split("/");
            rootDir = rootFileSeparated[0];
            String opfUrl = rootFileSeparated[1];
            Log.d("XMLParser","fullDir: "+destination+ File.separator + rootDir);

//            PARSE INDEX FILE NAME
            String indexHref = book.getTableOfContents().getTocReferences().get(0).getCompleteHref();
            Log.d("XMLParser","Parsed BeginningHref: "+indexHref);

//            LOOK FOR # VALUE
            String[] hrefHashSeparated = indexHref.split("#");
            Log.d("XMLParser","Parsed hrefHashSeparated[0]: "+hrefHashSeparated[0]);
            Log.d("XMLParser","Parsed hrefHashSeparated[1]: "+(hrefHashSeparated.length > 1 ? hrefHashSeparated[1] : "Does not exist"));
            Log.d("XMLParser","Parsed hrefHashSeparated Length: "+hrefHashSeparated.length);

//            LOOK FOR / VALUE
            String[] hrefSlashSeparated = indexHref.split("/");
            Log.d("XMLParser","Parsed hrefSlashSeparated[0]: "+hrefSlashSeparated[0]);
            Log.d("XMLParser","Parsed hrefSlashSeparated[1]: "+ (hrefSlashSeparated.length > 1 ? hrefSlashSeparated[1] : "Does not exist"));
            Log.d("XMLParser","Parsed hrefSlashSeparated Length: "+hrefSlashSeparated.length);
            Log.d("XMLParser","BaseUrl: "+baseUrl);

//            GET SUBROOT FROM INDEX FILE HREF
            if(hrefSlashSeparated.length > 1){
                href = hrefSlashSeparated[1];
                subrootDir = hrefSlashSeparated[0];
                baseUrl = "file://"+destination + File.separator + rootDir + File.separator + subrootDir + File.separator;

            }
            else{
                href = hrefSlashSeparated[0];
                subrootDir = "";
                baseUrl = "file://"+destination + File.separator + rootDir + File.separator;

            }


//             DATA PARSING


////            REPLACE <HEAD> TAG
//            File f1 = new File(destination+ File.separator + rootDir, hrefHashSeparated[0]);
//            FileInputStream fileInputStream2 = new FileInputStream(f1);
//            byte [] bytes = new byte[(int) f1.length()];
//            fileInputStream2.read(bytes);
//            data = new String(bytes);
//            data = data.replaceFirst("(?s)(<head>)(.*?)(</head>)","$1$3");
//            Log.d("XMLParser", "DATA: "+data);

//            PARSE BODY OF HTML
            File f1 = new File(destination+ File.separator + rootDir, hrefHashSeparated[0]);
            data = getHtmlFromWeb(f1);

//            UPDATE WEBVIEW
            String customizedData = updateData(data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);

            if(hrefHashSeparated.length > 1){

                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected Void doInBackground(Void... voids) {

                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = new FileOutputStream(f1);
                            fileOutputStream.write(customizedData.getBytes(StandardCharsets.UTF_8));
                            isFormattingSupported = false;
                            isLegacyFormattingSupported = true;

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                        Toast.makeText(ReaderActivity.this, "This EPUB doesn't support text formatting", Toast.LENGTH_SHORT).show();

                        if(hrefSlashSeparated.length > 1) {

                            webView.loadUrl(baseUrl + hrefSlashSeparated[1]);
                        }
                        else{
                            webView.loadUrl(baseUrl + hrefSlashSeparated[0]);
                        }
                        progressSliderLeftText.setText(book.getTableOfContents().getTocReferences().get(0).getTitle());
                    }
                }.execute();


            }
            else{

//                isFormattingSupported = true;
//                webView.loadDataWithBaseURL(baseUrl, customizedData, "text/html", "utf-8", null);

                new AsyncTask<Void, Void, Void>(){


                    @Override
                    protected Void doInBackground(Void... voids) {

                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = new FileOutputStream(f1);
                            fileOutputStream.write(customizedData.getBytes(StandardCharsets.UTF_8));
                            isFormattingSupported = false;
                            isLegacyFormattingSupported = true;

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);

                        if(hrefSlashSeparated.length > 1) {
                            webView.loadUrl(baseUrl + hrefSlashSeparated[1]);
                        }
                        else{
                            webView.loadUrl(baseUrl + hrefSlashSeparated[0]);
                        }
                        progressSliderLeftText.setText(book.getTableOfContents().getTocReferences().get(0).getTitle());

                    }

                }.execute();



            }


            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setSupportZoom(false);
            webView.getSettings().setBuiltInZoomControls(false);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.setVerticalScrollBarEnabled(true);
            webView.setHorizontalScrollBarEnabled(true);
//            webView.getSettings().setDefaultFontSize(26);
            webView.getSettings().setTextZoom(144);
            //webView.getSettings().setFixedFontFamily("file:///android_asset//times-new-roman-regularttf");


//            webView.loadDataWithBaseURL(baseUrl, customizedData, "text/html", "utf-8", null);


//            // Log the book's authors
//            Log.d("EPUB author", " : " + book.getMetadata().getAuthors());
//
//            // Log the book's title
//            Log.d("EPUB title", " : " + book.getTitle());
//
//            // Log the tale of contents
            logTableOfContents(book.getTableOfContents().getTocReferences(), 0);
//
            Log.d("EPUB","Model Size: "+ contentDataModels.size());

//            Handler handler = new Handler();
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    contentDataModels.add(new ContentDataModel("Contents","01","1984.xhtml#_idParaDest-26",1));
//                    contentsAdapter = new ContentsAdapter(ReaderActivity.this, contentDataModels);
//                    contentsAdapter.setContentDataModels(contentDataModels);
//                    Log.d("EPUB","Handler Model Size: "+ReaderActivity.contentDataModels.size());
//                }
//            };

//            handler.postDelayed(runnable, 2000);




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    private String xmlParser(InputStream inputStream, String tag, String attribute) throws XmlPullParserException, IOException {

        String value = "";
        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myParser = xmlFactoryObject.newPullParser();

        myParser.setInput(inputStream, null);

        int event = myParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT)  {
            String name=myParser.getName();
            switch (event){
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.END_TAG:
                    if(name.equals(tag)){
                        value = myParser.getAttributeValue(null,attribute);
                    }
                    break;
            }
            event = myParser.next();
        }

        return value;
    }

    public static String getHtmlFromWeb(File file) {

        String body = "";
        try {

            Document doc = Jsoup.parse(file);
            doc.outputSettings().syntax( Document.OutputSettings.Syntax.xml);
            body = doc.body().html();
        } catch (IOException e) {
        }
        Log.d("JSOUP","Body: " +body);
        return body;
    }


    public static String updateData(String input, String backgroundColorBody, String colorBody, String colorH1, String colorH2, String colorH3, String colorP, String letterSpacing, String wordSpacing, String lineHeight, String textIndent, String fontFamily, String fontSize, String fontWeight, String textAlignment, String paddingLeft, String paddingRight, String border){

        start = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\" epub:prefix=\"z3998: http://www.daisy.org/z3998/2012/vocab/structure/\">" +
                "<head>" +


//                "<meta charset=\"UTF-8\" />" +
//
//                "<meta content=\"note1\" name=\"CAS-UB-Publication-Class\" />" +
//                "<meta content=\"CAS-UB Backend System Installed on 2012-12-25\" name=\"Generator-Signature\" />"+
//                "<title>EPUBリーダの数式サポート―iBooks3.0のMathML表示がかなり進化</title>"+
//                "<link href=\"themes/cas-common_epub.css\" rel=\"stylesheet\" type=\"text/css\" />"+
//                "<link href=\"themes/normal-serif/common.css\" rel=\"stylesheet\" type=\"text/css\" />"+
//                "<link href=\"themes/normal-serif/generic.css\" rel=\"stylesheet\" type=\"text/css\" />"+
//                "<link href=\"styles/style.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                "<style type=\"text/css\">" +
                "@font-face {" +
                "font-family: WebViewFont;" +
                "src: url(\"file:///android_asset/"+fontFamily+".ttf\")" +
                "}"

                +


                "h1 {" +
                "color: "+colorH1+";" +
                "letter-spacing: "+letterSpacing+";\n" +
                "word-spacing: "+wordSpacing+";"+
                "line-height: "+lineHeight+";"+
                "}"

                +


                "h2 {" +
                "color: "+colorH2+";" +
                "letter-spacing: "+letterSpacing+";\n" +
                "word-spacing: "+wordSpacing+";"+
                "line-height: "+lineHeight+";"+
                "}"

                +

                "h3 {" +
                "color: "+colorH3+";" +
                "letter-spacing: "+letterSpacing+";\n" +
                "word-spacing: "+wordSpacing+";"+
                "line-height: "+lineHeight+";"+
                "}"

                +

                "p {" +
                "color: "+colorP+";" +
                "letter-spacing: "+letterSpacing+";\n" +
                "word-spacing: "+wordSpacing+";"+
                "text-indent: "+textIndent+";"+
                "line-height: "+lineHeight+";"+

                "}"
                +

                "img {" +
                "max-width: 100%;" +
                "height: auto;" +
                "}"

                +

                "body {" +

                "font-family: WebViewFont;" +
                "font-size: "+ fontSize +";" +
                "font-weight: "+ fontWeight +";"+
                "text-align: "+textAlignment+";" +
                "padding-left: "+paddingLeft+";"+
                "padding-right: "+paddingRight+";"+
                "border: "+border+";"+
                "color: "+ colorBody + ";"+
                "background-color: "+ backgroundColorBody +";"+

                "}" +

                "</style>" +
                "</head>" +
                "<body>";

        end = "</body></html>";


        return start + input + end;
    }

    public static void updateLegacyWebView(String updateData){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {

                File f1 = new File(destination+ File.separator + rootDir, href.split("#")[0]);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(f1);
                    fileOutputStream.write(updateData.getBytes(StandardCharsets.UTF_8));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                webView.reload();


            }
        }.execute();

    }

    private void logTableOfContents(List<TOCReference> tocReferences, int depth) {
        if (tocReferences == null) {
            return;
        }

        for (TOCReference tocReference : tocReferences) {
            StringBuilder tocString = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                tocString.append("\t");
            }
            tocString.append(tocReference.getTitle());
            Log.i("TOC", tocString.toString());
            Log.i("TOC","TOC Children: " + tocReference.getChildren());
            Log.i("TOC","TOC Href: " + tocReference.getCompleteHref());
            Log.d("TOC", "Length: "+tocString.length());
            Log.d("TOC","Size: "+contentDataModels.size());

            contentDataModels.add(new ContentDataModel(tocString.toString(), String.valueOf(tocString.length()), tocReference.getCompleteHref() ,depth+1));

//            try {
//                InputStream is = tocReference.getResource().getInputStream();
//                BufferedReader r = new BufferedReader(new InputStreamReader(is));
//
//                while ((line = r.readLine()) != null) {
//                    // line1 = Html.fromHtml(line).toString();
//                    Log.v("line" + i, Html.fromHtml(line).toString());
//                    // line1 = (tocString.append(Html.fromHtml(line).toString()+
//                    // "\n")).toString();
//                    line1 = line1.concat(Html.fromHtml(line).toString());
//                }
//                finalstr = finalstr.concat("\n").concat(line1);
//                // Log.v("Content " + i, finalstr);
//                i++;
//            } catch (IOException e) {
//
//            }

            logTableOfContents(tocReference.getChildren(), depth + 1);
        }


        Log.d("TOC","Total Size: "+contentDataModels.size());
        //Log.d("TOC","Href: "+contentDataModels.get(0).getHref());
        contentsAdapter.setContentDataModels(contentDataModels);



//        webView.loadDataWithBaseURL("", finalstr, "text/html", "UTF-8", "");
    }













    private void toggle(boolean visible, View headerLayout, View menuLayout, ViewGroup parent) {

        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(600);
        transition.addTarget(headerLayout);
        transition.addTarget(menuLayout);
        TransitionManager.beginDelayedTransition(parent, transition);
        headerLayout.setVisibility(visible ? View.GONE : View.VISIBLE);
        menuLayout.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

    private void toggle2(boolean visible, View menuLayout, ViewGroup parent) {

        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(600);
        transition.addTarget(menuLayout);

        TransitionManager.beginDelayedTransition(parent, transition);
        menuLayout.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

    private void toggleUp(boolean visible, View redLayout, ViewGroup parent) {

        Transition transition = new Slide(Gravity.TOP);
        transition.setDuration(600);
        transition.addTarget(redLayout);

        TransitionManager.beginDelayedTransition(parent, transition);
        redLayout.setVisibility(visible ? View.GONE : View.VISIBLE);
    }







    // slide the view from below itself to the current position
    public void slideUp(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void toggleFade(boolean visible, View redLayout, ViewGroup parent) {

        Transition transition = new Fade();
        transition.setDuration(600);
        transition.addTarget(redLayout);

        TransitionManager.beginDelayedTransition(parent, transition);
        redLayout.setVisibility(visible ? View.GONE : View.VISIBLE);
    }


    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    private boolean unpackZip(String path, String zipname)
    {
        InputStream is;
        ZipInputStream zis;
        try
        {
//            File file = new File(path);
//            if(file.exists()){
//                deleteRecursive(file);
//            }

            String filename;
//            is = new FileInputStream(path + zipname);
            is = getAssets().open(zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            File fmd = new File(path);
            fmd.mkdirs();

            while ((ze = zis.getNextEntry()) != null)
            {
                filename = ze.getName();

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    fmd = new File(path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(path + filename);

                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    void dirChecker(String destination, String dir) {
        File f = new File(destination, dir);

        if (!f.isDirectory()) {
            boolean success = f.mkdirs();
            if (!success) {
                Log.w(TAG_EPUB, "Failed to create folder " + f.getName());
            }
        }
    }

    public static void unzip(File zipFile, File targetDirectory) throws IOException {
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            /* if time should be restored as well
            long time = ze.getTime();
            if (time > 0)
                file.setLastModified(time);
            */
            }
        } finally {
            zis.close();
        }
    }

    void unzip_old(InputStream stream, String destination) {
        dirChecker(destination, "");
        byte[] buffer = new byte[1024*10];
        try {
            ZipInputStream zin = new ZipInputStream(stream);
            ZipEntry ze = null;

            while ((ze = zin.getNextEntry()) != null) {
                Log.v(TAG_EPUB, "Unzipping " + ze.getName());

                if (ze.isDirectory()) {
                    dirChecker(destination, ze.getName());
                } else {
                    File f = new File(destination, ze.getName());
                    if (!f.exists()) {
                        boolean success = f.createNewFile();
                        if (!success) {
                            Log.w(TAG_EPUB, "Failed to create file " + f.getName());
                            continue;
                        }
                        FileOutputStream fout = new FileOutputStream(f);
                        int count;
                        while ((count = zin.read(buffer)) != -1) {
                            fout.write(buffer, 0, count);
                        }
                        zin.closeEntry();
                        fout.close();

                    }
                }

            }
            zin.close();

        } catch (Exception e) {
            Log.e(TAG_EPUB, "unzip", e);
        }

    }

    public void SetProgressSliderVisibility(boolean isVisible) {
        if(isVisible) {
            progressSliderConstraintLayout.setVisibility(View.VISIBLE);
        }
        else{
            progressSliderConstraintLayout.setVisibility(View.GONE);
        }
    }

    public void SetProgressSliderLeftText(String text) {
        if(text != null) {
            progressSliderLeftText.setText(text);
        }
    }

    public void SetProgressSliderCenterText(String text) {
        if(text != null) {
            progressSliderCenterText.setText(text);
        }
    }

    public void SetProgressSliderRightText(String text) {
        if(text != null) {
            progressSliderRightText.setText(text);
        }
    }


}
package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.FragmentViewPager1.contentRecycler;
import static com.khalidsyfullah.boimela.ui.epub.FragmentViewPager1.contentsAdapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.ContentDataModel;
import com.khalidsyfullah.boimela.datamodel.SliderDataModel;
import com.khalidsyfullah.boimela.ui.home.SliderViewPagerAdapter;
import com.khalidsyfullah.boimela.ui.slider.SliderTimer;
import com.khalidsyfullah.boimela.ui.slider.SpeedSlowScroller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;


public class ReaderFragment extends Fragment implements View.OnClickListener {


    private TextView pageText, expandButton;
    private ImageView expandIcon, menuIcon, audioIcon, themeIcon, screenIcon, textIcon, fontIcon;
    private SeekBar pageSeekBar;
    public static WebView webView;
    private ConstraintLayout menuConstraintLayout, pageConstraintLayout, menuTopConstraintLayout;
    private ViewPager viewPager;
    private SliderViewPagerAdapter2 sliderViewPagerAdapter;
    private ArrayList<SliderDataModel> sliderDataModels;
    private MenuViewPager viewPager2;
    private MenuPagerAdapter menuPagerAdapter;
    private ViewGroup parent;
    public static ArrayList<ContentDataModel> contentDataModels = new ArrayList<>();

    private String line, line1 = "", finalstr = "";
    private int i = 0;

    public static String letterSpacing="0px";
    public static String wordSpacing="1px";
    public static String textIndent="10px";
    public static String lineHeight="3";
    public static String fontFamily="bookerly";
    public static String fontSize="60%";
    public static String fontWeight="300";
    public static String textAlignment="justify";
    public static String paddingLeft="30px";
    public static String paddingRight="30px";
    public static String border="4px dotted blue";
    public static String backgroundColorBody ="grey";
    public static String colorBody="black";
    public static String colorH1="black";
    public static String colorH2="black";
    public static String colorH3="black";
    public static String colorP="black";

    public static String input;

    public static byte[] bytes;

    public static String start = "<html>" +
            "<head>" +
            "<style type=\"text/css\">" +
            "@font-face {" +
            "font-family: MyFont;" +
            "src: url(\"file:///android_asset/bookerly.ttf\")" +
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

            "font-family: "+ fontFamily +";" +
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

    private String TAG_EPUB="EPUB_ACTIVITY";
    private boolean seekbarVisibility = true, pageVisibility = true, menuVisibility = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_reader, container, false);
        webView = root.findViewById(R.id.reader_webview);
        pageText = root.findViewById(R.id.reader_menu_page_text);
        expandButton = root.findViewById(R.id.reader_menu_expand_button);
        expandIcon = root.findViewById(R.id.reader_menu_expand_image);
        pageSeekBar = root.findViewById(R.id.reader_menu_page_seek_bar);
        menuConstraintLayout = root.findViewById(R.id.reader_menu_constraint_layout);
        menuTopConstraintLayout = root.findViewById(R.id.reader_menu_top_layout);
        pageConstraintLayout = root.findViewById(R.id.reader_menu_constraint_layout_2);
        menuIcon = root.findViewById(R.id.reader_menu_btn);
        audioIcon = root.findViewById(R.id.reader_audio_btn);
        themeIcon = root.findViewById(R.id.reader_theme_btn);
        screenIcon = root.findViewById(R.id.reader_screen_btn);
        textIcon = root.findViewById(R.id.reader_text_btn);
        fontIcon = root.findViewById(R.id.reader_font_btn);
        viewPager = root.findViewById(R.id.reader_menu_viewpager);
        viewPager2 = root.findViewById(R.id.reader_menu_viewpager2);

        parent = container;

        checkForPermissions();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if(menuVisibility) {
                    toggle(menuVisibility, menuConstraintLayout, parent);
                    menuVisibility = false;
                }
                else{
                    toggle(menuVisibility, menuConstraintLayout, parent);
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
                    toggle(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getActivity().getResources().getString(R.string.expand));
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
                    toggle(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getActivity().getResources().getString(R.string.collapse));
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
                    toggle(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getActivity().getResources().getString(R.string.expand));
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
                    toggle(pageVisibility, pageConstraintLayout, parent);
                    expandButton.setText(getActivity().getResources().getString(R.string.collapse));
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();

        sliderDataModels = new ArrayList<>();
        contentDataModels = new ArrayList<>();

        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Feluda","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Kakababu","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589747006l/12733425.jpg", "Fruits"));
        sliderDataModels.add(new SliderDataModel("Tintin", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/17958d12aa34_51326.gif", "Fruits"));


        sliderViewPagerAdapter = new SliderViewPagerAdapter2(getChildFragmentManager(), sliderDataModels);
        viewPager.setAdapter(sliderViewPagerAdapter);

        menuPagerAdapter = new MenuPagerAdapter(getChildFragmentManager(), getActivity());
        viewPager2.setAdapter(menuPagerAdapter);

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



    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            default:
                break;
        }
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

    private void toggle(boolean visible, View redLayout, ViewGroup parent) {

        Transition transition = new Slide(Gravity.BOTTOM);
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
            is = getActivity().getAssets().open(zipname);
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

    void unzip(InputStream stream, String destination) {
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

    void dirChecker(String destination, String dir) {
        File f = new File(destination, dir);

        if (!f.isDirectory()) {
            boolean success = f.mkdirs();
            if (!success) {
                Log.w(TAG_EPUB, "Failed to create folder " + f.getName());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void checkForPermissions()
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                // This part I didn't implement,because for my case it isn't needed
                Log.i(TAG_EPUB,"Unexpected flow");
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission is already granted, call the function that does what you need

            onFileWritePermissionGranted();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void onFileWritePermissionGranted()
    {
        destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/boimela/";

        //destination = Environment.getExternalStorageDirectory().getAbsolutePath()+"/android/data/com.khalidsyfullah.boimela/";
        //destination = Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela/";
        Log.d(TAG_EPUB,destination);

        try {
            AssetManager assetManager = getActivity().getAssets();

            InputStream inputStream = assetManager.open("david-copperfield.epub");
            // Load Book from inputStream
            Book book = (new EpubReader()).readEpub(inputStream);

            //unzip(inputStream, destination);

            unpackZip(destination,"3.epub");

            File f1 = new File(destination+"OEBPS/","ch01.xhtml");
            FileInputStream fileInputStream = new FileInputStream(f1);
            bytes = new byte[(int) f1.length()];
            fileInputStream.read(bytes);
            input = new String(bytes);
            Log.d(TAG_EPUB,input);
            String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
            webView.loadDataWithBaseURL(destination+"", data, "text/html", "utf-8", null);


            // Log the book's authors
            Log.d("EPUB author", " : " + book.getMetadata().getAuthors());

            // Log the book's title
            Log.d("EPUB title", " : " + book.getTitle());

            // Log the tale of contents
            logTableOfContents(book.getTableOfContents().getTocReferences(), 0);

            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.setVerticalScrollBarEnabled(true);
            webView.setHorizontalScrollBarEnabled(true);
            webView.getSettings().setDefaultFontSize(26);
//            webView.getSettings().setFixedFontFamily("file:///android_asset//times-new-roman.ttf");

            webView.getSettings().setTextZoom(144);

            logTableOfContents(book.getTableOfContents().getTocReferences(), 0);




        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String changeWebView(byte[] bytes, String backgroundColorBody, String colorBody, String colorH1, String colorH2, String colorH3, String colorP, String letterSpacing, String wordSpacing, String lineHeight, String textIndent, String fontFamily, String fontSize, String fontWeight, String textAlignment, String paddingLeft, String paddingRight, String border){

        start = "<html>" +
                "<head>" +
                "<style type=\"text/css\">" +
                "@font-face {" +
                "font-family: "+fontFamily+";" +
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

                "body {" +

                "font-family: "+ fontFamily +";" +
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

        input = new String(bytes);

        end = "</body></html>";


        return start + input + end;
    }

    @SuppressWarnings("unused")
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
            Log.d("TOC", "Length: "+tocString.length());
            contentDataModels.add(new ContentDataModel(tocString.toString(),1));
            Log.d("TOC","Size: "+contentDataModels.size());

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
//        webView.loadDataWithBaseURL("", finalstr, "text/html", "UTF-8", "");
    }
}
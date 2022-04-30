package com.khalidsyfullah.boimela.ui.epub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ReaderFragment extends Fragment {

    String line;
    String line1 = "";
    String finalstr = "";
    String destination="";
    String TAG_EPUB="EPUB_ACTIVITY";
    int i = 0;
    private WebView webView;
    private ConstraintLayout menuConstraintLayout;
    private boolean menuVisibility = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_reader, container, false);
        webView = root.findViewById(R.id.reader_webview);
        menuConstraintLayout = root.findViewById(R.id.reader_menu_constraint_layout);

        checkForPermissions();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if(menuVisibility){
                    menuConstraintLayout.setVisibility(View.INVISIBLE);
                    menuVisibility = false;
                }
                else if(!menuVisibility){
                    menuConstraintLayout.setVisibility(View.VISIBLE);
                    menuVisibility = true;
                }

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
                if(menuVisibility){
                    menuConstraintLayout.setVisibility(View.INVISIBLE);
                    menuVisibility = false;
                }
                else if(!menuVisibility){
                    menuConstraintLayout.setVisibility(View.VISIBLE);
                    menuVisibility = true;
                }
                Log.d("MenuConstraint","TAPPED");

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    void onFileWritePermissionGranted()
    {
        destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/boimela/";

        //destination = Environment.getExternalStorageDirectory().getAbsolutePath()+"/android/data/com.khalidsyfullah.boimela/";
        //destination = Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela";
        Log.d(TAG_EPUB,destination);

        try {
            AssetManager assetManager = getActivity().getAssets();

            InputStream inputStream = assetManager.open("david-copperfield.epub");
            unzip(inputStream, destination);

            File f1 = new File(destination+"epub/text/","chapter-1.xhtml");
            FileInputStream fileInputStream = new FileInputStream(f1);
            byte[] bytes = new byte[(int) f1.length()];
            fileInputStream.read(bytes);
            String input = new String(bytes);
            String start = "<html>" +
                    "<head>" +
                    "<style type=\"text/css\">" +
                    "@font-face {" +
                    "font-family: MyFont;" +
                    "src: url(\"file:///android_asset/bookerly.ttf\")" +
                    "}" +
                    "h1 {color:red;}\n" +
                    "h2 {color:red;}\n" +
                    "h3 {color:red;}\n" +
                    "p {" +
                    "color:white;" +

                    "letter-spacing: 0px;\n" +
                    "word-spacing: 1px;"+
                    "text-indent: 10px;"+
                    "line-height: 3;"+
                    "}" +
                    "body {" +

                    "font-family: MyFont;" +
                    "font-size: 60%;" +
                    "font-weight: 400;"+
                    "text-align: justify;" +
                    "padding-left: 30px;"+
                    "padding-right: 30px;"+
                    "border: 4px dotted blue;"+
                    "background-color: black;"+

                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>";
            String end = "</body></html>";
            String myHtmlString = start + input + end;

            Log.d(TAG_EPUB,input);
            webView.loadDataWithBaseURL(destination+"", myHtmlString, "text/html", "utf-8", null);


            //Book book = (new EpubReader()).readEpub(inputStream);


//            int inputSize = inputStream.available();
//            byte[] byteArray = new byte[inputSize];
//            inputStream.read(byteArray);
//            inputStream.close();
//
//            String inputString = new String(byteArray);
//            Log.d("EPUB",inputString);
//            FileOutputStream fileOutputStream = new FileOutputStream("kdjfdjkf");

            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.setVerticalScrollBarEnabled(true);
            webView.setHorizontalScrollBarEnabled(true);
            webView.getSettings().setDefaultFontSize(26);
            webView.getSettings().setFixedFontFamily("file:///android_asset//times-new-roman.ttf");

//            webView.getSettings().setTextZoom(144);

//            logTableOfContents(book.getTableOfContents().getTocReferences(), 0);
//



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
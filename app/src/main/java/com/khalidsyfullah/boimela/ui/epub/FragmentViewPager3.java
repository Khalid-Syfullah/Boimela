package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.backgroundColorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.border;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.changeWebView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH1;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH2;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH3;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorP;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontFamily;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontSize;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontWeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.letterSpacing;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.lineHeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.paddingLeft;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.paddingRight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.textAlignment;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.textIndent;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.webView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.wordSpacing;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.global.StaticData;

import java.util.Set;

public class FragmentViewPager3 extends Fragment {

    private SeekBar brightnessSeekSeekBar, contrastSeekBar, blueLightSeekBar;
    private ImageView themeImage, themeImage2, themeImage3, themeImage4, themeImage5, themeImage6;
    private TextView screenTime, screenTime2, screenTime3, screenTime4;
    private boolean isScreenOn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_3, container, false);

        brightnessSeekSeekBar = root.findViewById(R.id.reader_3_brightness_seek_bar);
        contrastSeekBar = root.findViewById(R.id.reader_3_contrast_seek_bar);
        blueLightSeekBar = root.findViewById(R.id.reader_3_blue_light_fliter_seek_bar);
        themeImage = root.findViewById(R.id.reader_3_theme_image);
        themeImage2 = root.findViewById(R.id.reader_3_theme_image_2);
        themeImage3 = root.findViewById(R.id.reader_3_theme_image_3);
        themeImage4 = root.findViewById(R.id.reader_3_theme_image_4);
        themeImage5 = root.findViewById(R.id.reader_3_theme_image_5);
        themeImage6 = root.findViewById(R.id.reader_3_theme_image_6);
        screenTime = root.findViewById(R.id.reader_3_screen_timeout_text);
        screenTime2 = root.findViewById(R.id.reader_3_screen_timeout_text_2);
        screenTime3 = root.findViewById(R.id.reader_3_screen_timeout_text_3);
        screenTime4 = root.findViewById(R.id.reader_3_screen_timeout_text_4);

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        brightnessSeekSeekBar.setMax(255);
        brightnessSeekSeekBar.setKeyProgressIncrement(1);
        blueLightSeekBar.setMin(10);
        blueLightSeekBar.setMax(100);
        blueLightSeekBar.setKeyProgressIncrement(1);
        contrastSeekBar.setMin(20);
        contrastSeekBar.setMax(220);
        contrastSeekBar.setKeyProgressIncrement(15);
    }

    @Override
    public void onResume() {
        super.onResume();


        brightnessSeekSeekBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {


                    boolean canWriteSettings = Settings.System.canWrite(getActivity());
                    if(canWriteSettings) {

                        int screenBrightnessValue = progress*255/100;
                        Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                        Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue);

                    }else
                    {
                        // Show Can modify system settings panel to let user add WRITE_SETTINGS permission for this app.
                        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                }
            }
        );

        themeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorBody = "FFFFFF";
                colorBody = "000000";
                colorH1 = "000000";
                colorH2 = "000000";
                colorH3 = "000000";
                colorP = "000000";

                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");

            }
        });

        themeImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorBody = "A4BBE7";
                colorBody = "000000";
                colorH1 = "000000";
                colorH2 = "000000";
                colorH3 = "000000";
                colorP = "000000";

                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");


            }
        });

        themeImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorBody = "E6D3AC";
                colorBody = "000000";
                colorH1 = "000000";
                colorH2 = "000000";
                colorH3 = "000000";
                colorP = "000000";

                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");


            }
        });

        themeImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorBody = "989581";
                colorBody = "FFFFFF";
                colorH1 = "FFFFFF";
                colorH2 = "FFFFFF";
                colorH3 = "FFFFFF";
                colorP = "FFFFFF";

                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");

            }
        });

        themeImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorBody = "3A363C";
                colorBody = "FFFFFF";
                colorH1 = "FFFFFF";
                colorH2 = "FFFFFF";
                colorH3 = "FFFFFF";
                colorP = "FFFFFF";

                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
            }
        });

        themeImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorBody = "000000";
                colorBody = "FFFFFF";
                colorH1 = "FFFFFF";
                colorH2 = "FFFFFF";
                colorH3 = "FFFFFF";
                colorP = "FFFFFF";

                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
            }
        });



        contrastSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {

                        colorBody = Integer.toHexString(progress) + Integer.toHexString(progress) + Integer.toHexString(progress);
                        colorH1 = Integer.toHexString(progress) + Integer.toHexString(progress) + Integer.toHexString(progress);
                        colorH2 = Integer.toHexString(progress) + Integer.toHexString(progress) + Integer.toHexString(progress);
                        colorH3 = Integer.toHexString(progress) + Integer.toHexString(progress) + Integer.toHexString(progress);
                        colorP = Integer.toHexString(progress) + Integer.toHexString(progress) + Integer.toHexString(progress);

//                        if(progress < 110) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//
//                        }
//                        else if(progress > 110 && progress < 130) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//                        else if(progress > 130 && progress < 150) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//                        else if(progress > 150 && progress < 170) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//                        else if(progress > 170 && progress < 190) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//                        else if(progress > 190 && progress < 210) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//                        else if(progress > 210 && progress < 230) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//                        else if(progress > 230 && progress < 255) {
//                            colorBody = "8080" + Integer.toHexString(255 - progress);
//                            colorH1 = "8080" + Integer.toHexString(255 - progress);
//                            colorH2 = "8080" + Integer.toHexString(255 - progress);
//                            colorH3 = "8080" + Integer.toHexString(255 - progress);
//                            colorP = "8080" + Integer.toHexString(255 - progress);
//                        }
//

                        String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                        webView.loadData(data, "text/html", "utf-8");

                    }
                }
        );

        blueLightSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {


                        backgroundColorBody = "FFF9"+Integer.toHexString(255-progress);

                        colorBody = "000000";
                        colorH1 = "000000";
                        colorH2 = "000000";
                        colorH3 = "000000";
                        colorP = "000000";

                        String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                        webView.loadData(data, "text/html", "utf-8");


                    }
                }
        );


        screenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isScreenOn) {
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    isScreenOn = false;

                }
                screenTimeout(3000);
                screenTime.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                screenTime2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));


            }
        });

        screenTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isScreenOn) {
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    isScreenOn = false;

                }
                screenTimeout(4000);
                screenTime.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                screenTime3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        screenTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isScreenOn) {
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    isScreenOn = false;

                }
                screenTimeout(5000);
                screenTime.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                screenTime4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        screenTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                isScreenOn = true;

                screenTime.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                screenTime4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));

            }
        });

    }

    private void screenTimeout(int timeout){
        try {
            int mSystemScreenOffTimeOut = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, timeout);
        } catch (Exception e) {
        }
    }

    public static Bitmap contrastAndBrightnessControler(Bitmap bitmap, float contrast, float brightness) {

        ColorMatrix cmatrix = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });
        Bitmap ret =Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cmatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return ret;
    }
}
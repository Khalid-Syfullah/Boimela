package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.isFormattingSupported;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.volumeButtonAction;
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
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentViewPager4 extends Fragment {

    private TextView fullScreenText, fullScreenText2, orientationText, orientationText2, scrollViewText, scrollViewText2,
            autoScrollingText, autoScrollingText2, volumeButtonText, volumeButtonText2;
    private SeekBar speedSeekBar;
    private boolean isAutoScrollingEnabled = false, isAutoScrollingStarted = false;
    private Timer repeatTask;
    private Animation anim;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(R.layout.viewpager_book_reader_4, container, false);

        fullScreenText  = root.findViewById(R.id.reader_4_full_screen_text);
        fullScreenText2  = root.findViewById(R.id.reader_4_full_screen_text_2);
        orientationText  = root.findViewById(R.id.reader_4_orientation_text);
        orientationText2  = root.findViewById(R.id.reader_4_orientation_text_2);
        autoScrollingText  = root.findViewById(R.id.reader_4_auto_scrolling_text);
        autoScrollingText2  = root.findViewById(R.id.reader_4_auto_scrolling_text_2);
        scrollViewText  = root.findViewById(R.id.reader_4_scrollview_text);
        scrollViewText2  = root.findViewById(R.id.reader_4_scrollview_text_2);
        volumeButtonText  = root.findViewById(R.id.reader_4_volume_button_action_text);
        volumeButtonText2  = root.findViewById(R.id.reader_4_volume_button_action_text_2);
        speedSeekBar = root.findViewById(R.id.reader_4_speed_seekbar);
        speedSeekBar.setEnabled(false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anim = AnimationUtils.loadAnimation(getActivity(),
                android.R.anim.slide_in_left);

        fullScreenText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullScreenText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fullScreenText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            }
        });

        fullScreenText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullScreenText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fullScreenText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        });

        orientationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orientationText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                orientationText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

            }
        });

        orientationText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orientationText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                orientationText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

            }
        });

        scrollViewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            webView.pageDown(false);
                        }
                        else if(event.getAction() == MotionEvent.ACTION_UP) {
                                webView.pageUp(false);
                        }

                        return false;

                    }
                });

                scrollViewText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                scrollViewText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        scrollViewText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {


                        if(event.getAction() == MotionEvent.ACTION_MOVE) {

                            Animation slideOutToLeft = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_to_left);
                            webView.startAnimation(slideOutToLeft);
                            webView.scrollBy(0, 360);

//                            getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

//                            ObjectAnimator anim = ObjectAnimator.ofInt(webView, String.valueOf(View.TRANSLATION_X), webView.getScrollY()+10);
//                            anim.setDuration(100);
//                            anim.start();
                        }
                        return true;

                    }
                });

                webView.setVerticalScrollBarEnabled(false);
                webView.setHorizontalScrollBarEnabled(false);

                scrollViewText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                scrollViewText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            }
        });

        autoScrollingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAutoScrollingEnabled = true;
                speedSeekBar.setEnabled(true);


                if(isAutoScrollingEnabled) {
                    isAutoScrollingStarted = true;

                    if(isAutoScrollingStarted) {
                        repeatTask = new Timer();
                        repeatTask.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        webView.scrollBy(0,5);
                                    }
                                });
                            }
                        }, 100, 100);
                    }

                }


                autoScrollingText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                autoScrollingText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        autoScrollingText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAutoScrollingEnabled = false;
                speedSeekBar.setEnabled(false);

                if(isAutoScrollingStarted) {
                    repeatTask.cancel();
                    isAutoScrollingStarted = false;

                }
                autoScrollingText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                autoScrollingText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            }
        });


        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(isAutoScrollingStarted){

                    repeatTask.cancel();
                    repeatTask = new Timer();
                    repeatTask.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    webView.scrollBy(0,progress);
                                }
                            });
                        }
                    }, 100, 100);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        volumeButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volumeButtonAction = true;
                volumeButtonText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                volumeButtonText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        volumeButtonText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volumeButtonAction = false;
                volumeButtonText.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                volumeButtonText2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));

            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
    }


}
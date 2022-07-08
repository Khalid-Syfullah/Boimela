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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;

public class FragmentViewPager6 extends Fragment {

    private SeekBar fontSizeSeekBar;
    private TextView fontStyleBtn, fontStyleBtn2, fontStyleBtn3, fontStyleBtn4, fontStyleBtn5, progressSliderOn, progressSliderOff;
    private int p = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_6, container, false);

        fontSizeSeekBar = root.findViewById(R.id.reader_6_font_size_seekbar);
        fontStyleBtn = root.findViewById(R.id.reader_6_font_style_text);
        fontStyleBtn2 = root.findViewById(R.id.reader_6_font_style_text_2);
        fontStyleBtn3 = root.findViewById(R.id.reader_6_font_style_text_3);
        fontStyleBtn4 = root.findViewById(R.id.reader_6_font_style_text_4);
        fontStyleBtn5 = root.findViewById(R.id.reader_6_font_style_text_5);
        progressSliderOn = root.findViewById(R.id.reader_6_progress_slider_show_text);
        progressSliderOff = root.findViewById(R.id.reader_6_progress_slider_hide_text);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fontSizeSeekBar.setProgress(25);

        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress <= 20){
                    p = 50;
                    fontSizeSeekBar.setProgress(0);

                }
                else if(progress > 20 && progress < 40){
                    p = 60;
                    fontSizeSeekBar.setProgress(25);

                }
                else if(progress > 40 && progress < 60){
                    p = 65;
                    fontSizeSeekBar.setProgress(50);

                }
                else if(progress > 60 && progress < 80){
                    p = 70;
                    fontSizeSeekBar.setProgress(75);

                }
                else{
                    p = 80;
                    fontSizeSeekBar.setProgress(100);

                }

                fontSize = p+"%";
                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fontStyleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fontFamily = "times-new-roman";
                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));


            }
        });

        fontStyleBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fontFamily = "Bookerly-Bold";
                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fontFamily = "Bookerly-BoldItalic";
                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fontFamily = "Bookerly-Display";
                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fontFamily = "Bookerly-RegularItalic";
                String data = changeWebView(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));

            }
        });

        progressSliderOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressSliderOn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                progressSliderOff.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        progressSliderOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressSliderOn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                progressSliderOff.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
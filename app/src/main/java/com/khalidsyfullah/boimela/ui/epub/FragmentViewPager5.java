package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.backgroundColorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.border;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.bytes;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.changeWebView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.colorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.colorH1;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.colorH2;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.colorH3;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.colorP;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.fontFamily;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.fontSize;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.fontWeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.letterSpacing;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.lineHeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.paddingLeft;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.paddingRight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.textAlignment;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.textIndent;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.webView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderFragment.wordSpacing;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;

public class FragmentViewPager5 extends Fragment {

    private SeekBar pageMarginSeekBar, lineSpacingSeekBar, wordSpacingSeekBar;
    private TextView textAlignmentBtn, textAlignmentBtn2, textAlignmentBtn3, textAlignmentBtn4;
    int p = 0;
    double p2 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(R.layout.viewpager_book_reader_5, container, false);

        pageMarginSeekBar = root.findViewById(R.id.reader_5_page_margin_seekbar);
        lineSpacingSeekBar = root.findViewById(R.id.reader_5_line_spacing_seekbar);
        wordSpacingSeekBar = root.findViewById(R.id.reader_5_word_spacing_seekbar);
        textAlignmentBtn = root.findViewById(R.id.reader_5_text_alignment_btn);
        textAlignmentBtn2 = root.findViewById(R.id.reader_5_text_alignment_btn3);
        textAlignmentBtn3 = root.findViewById(R.id.reader_5_text_alignment_btn4);
        textAlignmentBtn4 = root.findViewById(R.id.reader_5_text_alignment_btn2);

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageMarginSeekBar.setKeyProgressIncrement(1);
        lineSpacingSeekBar.setKeyProgressIncrement(1);

        pageMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress <= 25){
                    p = 30;
                    pageMarginSeekBar.setProgress(0);

                }
                else if(progress > 25 && progress < 75){
                    p = 50;
                    pageMarginSeekBar.setProgress(50);

                }
                else{
                    p = 75;
                    pageMarginSeekBar.setProgress(100);

                }


                paddingLeft = String.valueOf(p);
                paddingRight = String.valueOf(p);
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lineSpacingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if(progress <= 25){
                    p2 = 3;
                    lineSpacingSeekBar.setProgress(0);

                }
                else if(progress > 25 && progress < 75){
                    p2 = 3.5;
                    lineSpacingSeekBar.setProgress(50);

                }
                else{
                    p2 = 4;
                    lineSpacingSeekBar.setProgress(100);

                }


                lineHeight = String.valueOf(p2);
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        wordSpacingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress <= 25){
                    p = 1;
                    wordSpacingSeekBar.setProgress(0);

                }
                else if(progress > 25 && progress < 75){
                    p = 2;
                    wordSpacingSeekBar.setProgress(50);

                }
                else{
                    p = 5;
                    wordSpacingSeekBar.setProgress(100);

                }


                wordSpacing = String.valueOf(p+"px");
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textAlignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textAlignment = "left";
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");
                textAlignmentBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                textAlignmentBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        textAlignmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textAlignment = "center";
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");

                textAlignmentBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                textAlignmentBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        textAlignmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textAlignment = "right";
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");

                textAlignmentBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                textAlignmentBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        textAlignmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textAlignment = "justify";
                String data = changeWebView(bytes, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                webView.loadData(data, "text/html", "utf-8");

                textAlignmentBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                textAlignmentBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
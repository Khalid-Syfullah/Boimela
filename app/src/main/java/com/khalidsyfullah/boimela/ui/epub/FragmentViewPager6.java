package com.khalidsyfullah.boimela.ui.epub;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.backgroundColorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.baseUrl;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.bookLanguage;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.border;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.isLegacyFormattingSupported;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.updateData;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH1;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH2;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH3;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorP;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontFamily;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontSize;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontWeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.isFormattingSupported;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.letterSpacing;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.lineHeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.paddingLeft;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.paddingRight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.textAlignment;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.textIndent;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.updateLegacyWebView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.webView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.wordSpacing;

import android.app.Activity;
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
    private TextView fontStyleBtn, fontStyleBtn2, fontStyleBtn3, fontStyleBtn4, fontStyleBtn5,
            fontStyleBtn6, fontStyleBtn7, fontStyleBtn8, fontStyleBtn9, fontStyleBtn10,
            fontStyleBtn11, fontStyleBtn12, fontStyleBtn13, fontStyleBtn14, fontStyleBtn15,
            progressSliderOn, progressSliderOff;
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
        fontStyleBtn6 = root.findViewById(R.id.reader_6_font_style_text_6);
        fontStyleBtn7 = root.findViewById(R.id.reader_6_font_style_text_7);
        fontStyleBtn8 = root.findViewById(R.id.reader_6_font_style_text_8);
        fontStyleBtn9 = root.findViewById(R.id.reader_6_font_style_text_9);
        fontStyleBtn10 = root.findViewById(R.id.reader_6_font_style_text_10);
        fontStyleBtn11 = root.findViewById(R.id.reader_6_font_style_text_11);
        fontStyleBtn12 = root.findViewById(R.id.reader_6_font_style_text_12);
        fontStyleBtn13 = root.findViewById(R.id.reader_6_font_style_text_13);
        fontStyleBtn14 = root.findViewById(R.id.reader_6_font_style_text_14);
        fontStyleBtn15 = root.findViewById(R.id.reader_6_font_style_text_15);

        progressSliderOn = root.findViewById(R.id.reader_6_progress_slider_show_text);
        progressSliderOff = root.findViewById(R.id.reader_6_progress_slider_hide_text);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(bookLanguage.equals("en")){

            fontStyleBtn.setText(getActivity().getResources().getString(R.string.font_en_1));
            fontStyleBtn2.setText(getActivity().getResources().getString(R.string.font_en_2));
            fontStyleBtn3.setText(getActivity().getResources().getString(R.string.font_en_3));
            fontStyleBtn4.setText(getActivity().getResources().getString(R.string.font_en_4));
            fontStyleBtn5.setText(getActivity().getResources().getString(R.string.font_en_5));
            fontStyleBtn6.setText(getActivity().getResources().getString(R.string.font_en_6));
            fontStyleBtn7.setText(getActivity().getResources().getString(R.string.font_en_7));
            fontStyleBtn8.setText(getActivity().getResources().getString(R.string.font_en_8));
            fontStyleBtn9.setText(getActivity().getResources().getString(R.string.font_en_9));
            fontStyleBtn10.setText(getActivity().getResources().getString(R.string.font_en_10));
            fontStyleBtn11.setText(getActivity().getResources().getString(R.string.font_en_11));
            fontStyleBtn12.setText(getActivity().getResources().getString(R.string.font_en_12));
            fontStyleBtn13.setText(getActivity().getResources().getString(R.string.font_en_13));
            fontStyleBtn14.setText(getActivity().getResources().getString(R.string.font_en_14));
            fontStyleBtn15.setText(getActivity().getResources().getString(R.string.font_en_15));

            fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

        }
        else{

            fontStyleBtn.setText(getActivity().getResources().getString(R.string.font_bn_1));
            fontStyleBtn2.setText(getActivity().getResources().getString(R.string.font_bn_2));
            fontStyleBtn3.setText(getActivity().getResources().getString(R.string.font_bn_3));
            fontStyleBtn4.setText(getActivity().getResources().getString(R.string.font_bn_4));
            fontStyleBtn5.setText(getActivity().getResources().getString(R.string.font_bn_5));
            fontStyleBtn6.setText(getActivity().getResources().getString(R.string.font_bn_6));
            fontStyleBtn7.setText(getActivity().getResources().getString(R.string.font_bn_7));
            fontStyleBtn8.setText(getActivity().getResources().getString(R.string.font_bn_8));
            fontStyleBtn9.setText(getActivity().getResources().getString(R.string.font_bn_9));
            fontStyleBtn10.setText(getActivity().getResources().getString(R.string.font_bn_10));
            fontStyleBtn11.setText(getActivity().getResources().getString(R.string.font_bn_11));
            fontStyleBtn12.setText(getActivity().getResources().getString(R.string.font_bn_12));
            fontStyleBtn13.setText(getActivity().getResources().getString(R.string.font_bn_13));
            fontStyleBtn14.setText(getActivity().getResources().getString(R.string.font_bn_14));
            fontStyleBtn15.setText(getActivity().getResources().getString(R.string.font_bn_15));

            fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
        }



        fontSizeSeekBar.setProgress(50);

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
                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }            }

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

                if(bookLanguage.equals("en")) {
                    fontFamily = "arial";
                }
                else{
                    fontFamily = "adorsholipi";
                }
                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));


            }
        });

        fontStyleBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "baskerville";
                }
                else{
                    fontFamily = "akaash";
                }
                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "bookerly";
                }
                else{
                    fontFamily = "aponalohit";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "calibri";
                }
                else{
                    fontFamily = "bangla";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "courier-new";
                }
                else{
                    fontFamily = "bensen";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        fontStyleBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "garamond";
                }
                else{
                    fontFamily = "bensen-handwriting";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "georgia";
                }
                else{
                    fontFamily = "kalpurush";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "helvetica";
                }
                else{
                    fontFamily = "lohit";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }
                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
            }
        });

        fontStyleBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "monotype-corsiva";
                }
                else{
                    fontFamily = "mitra";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "palatino-linotype";
                }
                else{
                    fontFamily = "mukti";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "segoeui";
                }
                else{
                    fontFamily = "nikosh";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "segoeui-light";
                }
                else{
                    fontFamily = "nikosh-light";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "tahoma";
                }
                else{
                    fontFamily = "sagar";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        fontStyleBtn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "times-new-roman";
                }
                else{
                    fontFamily = "siyamrupali";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });


        fontStyleBtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bookLanguage.equals("en")) {
                    fontFamily = "verdana";
                }
                else{
                    fontFamily = "solaimanlipi";
                }                String data = updateData(ReaderActivity.data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);
                if(isFormattingSupported) {
                    webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
                }
                else if(isLegacyFormattingSupported){
                    updateLegacyWebView(data);
                }

                fontStyleBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn2.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn3.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn4.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn5.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn6.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn7.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn8.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn9.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn10.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn11.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn12.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn13.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn14.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                fontStyleBtn15.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));

            }
        });

        progressSliderOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity = getActivity();
                if(activity instanceof ReaderActivity) {
                    ((ReaderActivity) activity).SetProgressSliderVisibility(true);
                }
                progressSliderOn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
                progressSliderOff.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));

            }
        });

        progressSliderOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity = getActivity();
                if(activity instanceof ReaderActivity) {
                    ((ReaderActivity) activity).SetProgressSliderVisibility(false);
                }

                progressSliderOn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered));
                progressSliderOff.setBackground(getActivity().getResources().getDrawable(R.drawable.button_bordered_selected));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!isFormattingSupported && !isLegacyFormattingSupported){
            fontSizeSeekBar.setEnabled(false);
            fontStyleBtn.setEnabled(false);
            fontStyleBtn2.setEnabled(false);
            fontStyleBtn3.setEnabled(false);
            fontStyleBtn4.setEnabled(false);
            fontStyleBtn5.setEnabled(false);
        }
        else{
            fontSizeSeekBar.setEnabled(true);
            fontStyleBtn.setEnabled(true);
            fontStyleBtn2.setEnabled(true);
            fontStyleBtn3.setEnabled(true);
            fontStyleBtn4.setEnabled(true);
            fontStyleBtn5.setEnabled(true);
        }
    }
}
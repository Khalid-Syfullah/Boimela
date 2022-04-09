package com.khalidsyfullah.boimela.ui.epub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.khalidsyfullah.boimela.R;

public class FragmentViewPager2 extends Fragment {

    SeekBar seekBar2;
    int step = 2;
    int p = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_2, container, false);
        seekBar2 = root.findViewById(R.id.seekBar2);

        seekBar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser)
                    {


                        if(progress <= 25){
                            p = 0;
                        }
                        else if(progress > 25 && progress < 75){
                            p = 50;
                        }
                        else{
                            p = 100;
                        }
                        seekBar2.setProgress(p);
                    }
                }
        );
        return root;
    }
}
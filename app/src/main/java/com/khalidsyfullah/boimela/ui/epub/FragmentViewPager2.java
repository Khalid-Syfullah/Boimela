package com.khalidsyfullah.boimela.ui.epub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.khalidsyfullah.boimela.R;

public class FragmentViewPager2 extends Fragment {

    ImageView bookImage;
    SeekBar seekBar2;
    int step = 2;
    int p = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_2, container, false);

        bookImage = root.findViewById(R.id.reader_2_book_image);


//        seekBar2 = root.findViewById(R.id.reader_3_brightness_seek_bar);
//
//        seekBar2.setOnSeekBarChangeListener(
//                new SeekBar.OnSeekBarChangeListener()
//                {
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//
//
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {}
//
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress,
//                                                  boolean fromUser)
//                    {
//
//
//                        if(progress <= 25){
//                            p = 0;
//                        }
//                        else if(progress > 25 && progress < 75){
//                            p = 50;
//                        }
//                        else{
//                            p = 100;
//                        }
//                        seekBar2.setProgress(p);
//                    }
//                }
//        );
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_reader).navigate(R.id.action_nav_reader_to_nav_player);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
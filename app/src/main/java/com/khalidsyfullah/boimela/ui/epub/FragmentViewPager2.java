package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.global.StaticData.audioUrl;
import static com.khalidsyfullah.boimela.global.StaticData.fileName;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirBig;
import static com.khalidsyfullah.boimela.global.StaticData.isMediaActive;
import static com.khalidsyfullah.boimela.global.StaticData.isMediaReset;
import static com.khalidsyfullah.boimela.global.StaticData.mediaPlayer;
import static com.khalidsyfullah.boimela.global.StaticData.mediaStatus;
import static com.khalidsyfullah.boimela.global.StaticData.mediaTitle;
import static com.khalidsyfullah.boimela.global.StaticData.mediaUrl;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.global.StaticData;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentViewPager2 extends Fragment {

    private TextView audiobookTitle, authorTitle, audioTitle;
    private ImageView bookImage, rewindImage, playImage, forwardImage;
    private SeekBar audioSeekBar;
    private Handler seekBarHandler;
    private SharedPreferences mediaPreferences;
    private SharedPreferences.Editor mediaPreferencesEditor;
    private String mediaPref = "Audio";
    private boolean initPlayer = true;

    private File file;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  (ViewGroup) inflater.inflate(
                R.layout.viewpager_book_reader_2, container, false);

        bookImage = root.findViewById(R.id.reader_2_book_image);
        audiobookTitle = root.findViewById(R.id.reader_2_audiobook_title);
        authorTitle = root.findViewById(R.id.reader_2_author_title);
        audioTitle = root.findViewById(R.id.reader_2_audio_title);
        audioSeekBar = root.findViewById(R.id.reader_2_audio_seek_bar);
        rewindImage = root.findViewById(R.id.reader_2_audio_rewind);
        playImage = root.findViewById(R.id.reader_2_audio_play);
        forwardImage = root.findViewById(R.id.reader_2_audio_forward);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateMediaUi();

        audioTitle.setText(StaticData.bookName);
        Picasso.get().load(imageDirBig + StaticData.bookImgUrl).placeholder(R.drawable.audio_play_bordered).into(bookImage);
        authorTitle.setText(StaticData.authorName);

        seekBarHandler = new Handler();

        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    audioSeekBar.setProgress(mCurrentPosition);
                }
                seekBarHandler.postDelayed(this, 1000);
            }
        });




        bookImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                checkForPermissions();
            }
        });

        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isMediaActive){
                    pauseAudio();
                }
                else{
                    playAudio();
                }
            }

        });


        rewindImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    if (currentPosition - 10000 >= 0) {
                        mediaPlayer.seekTo(currentPosition - 10000);
                    } else {
                        mediaPlayer.seekTo(0);
                    }
                }
            }
        });

        forwardImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    if (currentPosition + 10000 <= mediaPlayer.getDuration()) {
                        mediaPlayer.seekTo(currentPosition + 10000);
                    } else {
                        mediaPlayer.seekTo(mediaPlayer.getDuration());
                    }
                }
            }
        });


        audioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    void checkForPermissions()
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Log.i("FragmentViewPager2","Unexpected flow");
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }
        } else {

            if(mediaPlayer == null) {
                checkStatus();

            }
            else{
//                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_reader).navigate(R.id.action_nav_reader_to_nav_player);
                Intent intent = new Intent(getActivity(), AudioPlayerActivity.class);
                getActivity().startActivity(intent);
            }
        }
    }

    private void checkStatus(){

        if(Build.VERSION.SDK_INT > 30) {
            file = new File(Environment.getDataDirectory().getAbsolutePath() + "/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".mp3");
        }
        else{
            file = new File(Environment.getDownloadCacheDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".mp3");
        }

        if(!file.exists()){
            loadMedia();
        }
        else{
//            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_reader).navigate(R.id.action_nav_reader_to_nav_player);
            Intent intent = new Intent(getActivity(), AudioPlayerActivity.class);
            getActivity().startActivity(intent);
        }


    }


    private void loadMedia(){

        progressDialog = new ProgressDialog(getActivity(), R.style.CustomProgressDialog);
        progressDialog.setMessage("Downloading Audiobook...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);



        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        restAPI.downloadFileWithDynamicUrlAsync(audioUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    progressDialog.show();
                    Log.d("FragmentViewPager2", "Connected to Server!");
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                            Log.d("FragmentViewPager2", "file download was a success? " + writtenToDisk);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            progressDialog.dismiss();
//                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_reader).navigate(R.id.action_nav_reader_to_nav_player);
                            Intent intent = new Intent(getActivity(), AudioPlayerActivity.class);
                            getActivity().startActivity(intent);

                        }
                    }.execute();
                }
                else {
                    Log.d("FragmentViewPager2", "Download Failed!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("FragmentViewPager2", "No response from server!");

            }
        });




    }



    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + fileName + ".mp3");
            mediaUrl = file.getPath();

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
                    progressDialog.setIndeterminate(false);
                    progressDialog.setMax(100);
                    progressDialog.setProgress((int) (fileSizeDownloaded*100/fileSize));
                    Log.d("FragmentViewPager2", "Audio download: " + fileSizeDownloaded + " of " + fileSize+ " "+fileSizeDownloaded/fileSize*100+ "%");
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


    private void updateMediaUi(){
        if(isMediaActive) {
            mediaTitle = audioTitle.getText().toString();
            mediaStatus = getResources().getString(R.string.paused);
            playImage.setImageResource(R.drawable.pause_black);

            if(mediaPlayer != null){
                if(!mediaPlayer.isPlaying()){
                    prepareMedia();
                }
            }

        }
    }





    private void prepareMedia(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

            if(initPlayer) {
                audioSeekBar.setMax(mediaPlayer.getDuration() / 1000);

                initPlayer = false;
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayerComplete) {

                    stopAudio();
                    Log.d("FragmentViewPager2","MediaPlayer: Completed");
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void playAudio(){

        if(mediaPlayer != null) {
            try {
                playImage.setImageResource(R.drawable.pause_black);
                mediaStatus = getResources().getString(R.string.now_playing);
                isMediaActive = true;

                mediaTitle = audioTitle.getText().toString();
                mediaStatus = getResources().getString(R.string.now_playing);
                mediaUrl = file.getPath();

                updateMediaPreferences();

                if (isMediaReset) {
                    prepareMedia();
                    mediaPlayer.start();
                    isMediaReset = false;
                } else {
                    if (mediaPlayer != null) {
                        if (!mediaPlayer.isPlaying()) {
                            mediaPlayer.start();
                        }
                    }
                }

                audioTitle.setText(mediaTitle);
                audiobookTitle.setText(mediaStatus);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        else{
            checkForPermissions();
        }
    }

    private void pauseAudio(){

        try {
            playImage.setImageResource(R.drawable.audio_play_bordered);
            mediaStatus = getResources().getString(R.string.ready_to_play);
            audiobookTitle.setText(mediaStatus);

            isMediaActive = false;
            isMediaReset = false;

            updateMediaPreferences();

            if(mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    private void stopAudio(){
        try {
            playImage.setImageResource(R.drawable.stop_black);
            mediaStatus = getResources().getString(R.string.ready_to_play);
            audiobookTitle.setText(mediaStatus);

            isMediaActive = false;
            isMediaReset = true;

            updateMediaPreferences();

            if(mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void updateMediaPreferences(){
        mediaPreferences = getActivity().getSharedPreferences(mediaPref, Context.MODE_PRIVATE);
        mediaPreferencesEditor = mediaPreferences.edit();

        mediaPreferencesEditor.putString("mediaTitle", mediaTitle);
        mediaPreferencesEditor.putString("mediaStatus", mediaStatus);
        mediaPreferencesEditor.putString("mediaUrl", mediaUrl);
        mediaPreferencesEditor.putBoolean("isMediaActive", isMediaActive);
        mediaPreferencesEditor.putBoolean("isMediaReset", isMediaReset);

        mediaPreferencesEditor.apply();
    }



}
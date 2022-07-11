package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.global.StaticData.audioUrl;
import static com.khalidsyfullah.boimela.global.StaticData.fileName;
import static com.khalidsyfullah.boimela.global.StaticData.isMediaActive;
import static com.khalidsyfullah.boimela.global.StaticData.isMediaReset;
import static com.khalidsyfullah.boimela.global.StaticData.mediaPlayer;
import static com.khalidsyfullah.boimela.global.StaticData.mediaStatus;
import static com.khalidsyfullah.boimela.global.StaticData.mediaTitle;
import static com.khalidsyfullah.boimela.global.StaticData.mediaUrl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioPlayerActivity extends Activity {



    private ImageView audioImage, settingsImage, hideImage, rewindImage, fastRewindImage, playImage, fastForwardImage, forwardImage, repeatImage;
    private TextView audiobookTitle, authorTitle, audioTitle, voiceTitle, currentTimeStamp, audioTimeStamp, audioSpeedText;
    private SeekBar audioSeekBar;
    private boolean initPlayer = true, isRepeat = false;
    private File file;
    private Handler seekBarHandler;
    private SharedPreferences mediaPreferences;
    private SharedPreferences.Editor mediaPreferencesEditor;
    private String mediaPref = "Audio";
    private int minutes=0, seconds=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_audio_player);

        settingsImage = findViewById(R.id.audio_player_settings_image);
        hideImage = findViewById(R.id.audio_player_hide_image);
        audiobookTitle = findViewById(R.id.audio_player_audiobook_title);
        authorTitle = findViewById(R.id.audio_player_author_title);
        audioTitle = findViewById(R.id.audio_player_audio_title);
        voiceTitle = findViewById(R.id.audio_player_voice_title);
        audioImage = findViewById(R.id.audio_player_image);
        audioSeekBar = findViewById(R.id.audio_player_seek_bar);
        currentTimeStamp = findViewById(R.id.audio_player_current_timestamp);
        audioTimeStamp = findViewById(R.id.audio_player_audio_timestamp);
        rewindImage = findViewById(R.id.audio_player_audio_rewind);
        fastRewindImage = findViewById(R.id.audio_player_audio_fast_rewind);
        playImage = findViewById(R.id.audio_player_audio_play);
        fastForwardImage = findViewById(R.id.audio_player_audio_fast_forward);
        forwardImage = findViewById(R.id.audio_player_audio_forward);
        audioSpeedText = findViewById(R.id.audio_player_speed);
        repeatImage = findViewById(R.id.audio_player_repeat);

        rewindImage.setEnabled(false);
        fastRewindImage.setEnabled(false);
        playImage.setEnabled(false);
        fastForwardImage.setEnabled(false);
        forwardImage.setEnabled(false);
        audioSpeedText.setEnabled(false);
        repeatImage.setEnabled(false);

        rewindImage.setImageResource(R.drawable.audio_rewind_disabled);
        fastRewindImage.setImageResource(R.drawable.audio_fast_rewind_disabled);
        playImage.setImageResource(R.drawable.play_disabled);
        fastForwardImage.setImageResource(R.drawable.audio_fast_forward_disabled);
        forwardImage.setImageResource(R.drawable.audio_forward_disabled);
        repeatImage.setImageResource(R.drawable.audio_repeat_disabled);


        checkForPermissions();

        seekBarHandler = new Handler();

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    audioSeekBar.setProgress(mCurrentPosition);
                    minutes = mCurrentPosition / 60;
                    seconds = mCurrentPosition % 60;
                    if(minutes < 10 && seconds < 10){
                        currentTimeStamp.setText("0"+minutes + ":0" + seconds);

                    }
                    else if(minutes < 10){
                        currentTimeStamp.setText("0"+minutes + ":" + seconds);

                    }
                    else if(seconds < 10){
                        currentTimeStamp.setText(minutes + ":0" + seconds);

                    }
                    else{
                        currentTimeStamp.setText(minutes + ":" + seconds);

                    }
                }
                seekBarHandler.postDelayed(this, 1000);
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
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition - 10000 >= 0) {
                    mediaPlayer.seekTo(currentPosition - 10000);
                } else {
                    mediaPlayer.seekTo(0);
                }
            }
        });

        fastRewindImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if(mediaPlayer.getPlaybackParams().getSpeed() - 0.25f >= 0.1f) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(mediaPlayer.getPlaybackParams().getSpeed() - 0.25f));
                }
                else{
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(0.25f));

                }
                audioSpeedText.setText(mediaPlayer.getPlaybackParams().getSpeed() + "x");

            }
        });

        fastRewindImage.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition - 5000 >= 0) {
                    mediaPlayer.seekTo(currentPosition - 5000);
                } else {
                    mediaPlayer.seekTo(0);
                }
                return false;
            }
        });

        fastForwardImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if(mediaPlayer.getPlaybackParams().getSpeed() + 0.25f <= 2.0f) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(mediaPlayer.getPlaybackParams().getSpeed() + 0.25f));
                }
                else{
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(2.0f));

                }
                audioSpeedText.setText(mediaPlayer.getPlaybackParams().getSpeed() + "x");
            }
        });


        fastForwardImage.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition + 5000 <= mediaPlayer.getDuration()) {
                    mediaPlayer.seekTo(currentPosition + 5000);
                } else {
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }
                return false;
            }
        });

        forwardImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition + 10000 <= mediaPlayer.getDuration()) {
                    mediaPlayer.seekTo(currentPosition + 10000);
                } else {
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }
            }
        });


        repeatImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (isRepeat) {

                    mediaPlayer.setLooping(false);
                    isRepeat = false;
                    repeatImage.setImageResource(R.drawable.audio_repeat_disabled);

                }
                else {

                    mediaPlayer.setLooping(true);
                    isRepeat = true;
                    repeatImage.setImageResource(R.drawable.audio_repeat);
                }
            }
        });





        settingsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        hideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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



    private void checkForPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(AudioPlayerActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(AudioPlayerActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Log.i("AudioPlayerActivity","Unexpected flow");
            } else {
                ActivityCompat.requestPermissions(AudioPlayerActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }
        } else {

            checkStatus();


        }
    }

    private void checkStatus(){

        file = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".mp3");

        if(!file.exists()){
            loadMedia();
        }
        else{
            updateMediaUi();
        }

    }


    private void loadMedia(){

        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        restAPI.downloadFileWithDynamicUrlAsync(audioUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("Download", "Connected to Server!");
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                            Log.d("Download", "file download was a success? " + writtenToDisk);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            updateMediaUi();

                        }
                    }.execute();
                }
                else {
                    Log.d("Download", "Download Failed!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Download", "No response from server!");

            }
        });




    }



    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            file = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".mp3");
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
//                    mProgressDialog.setIndeterminate(false);
//                    mProgressDialog.setMax(100);
//                    mProgressDialog.setProgress((int) (fileSizeDownloaded*100/fileSize));
                    Log.d("Download", "Audio download: " + fileSizeDownloaded + " of " + fileSize+ " "+fileSizeDownloaded/fileSize*100+ "%");
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

        mediaUrl = file.getPath();

        if(isMediaActive) {
            mediaTitle = audioTitle.getText().toString();
            mediaStatus = getResources().getString(R.string.paused);

            rewindImage.setEnabled(true);
            fastRewindImage.setEnabled(true);
            playImage.setEnabled(true);
            fastForwardImage.setEnabled(true);
            forwardImage.setEnabled(true);
            audioSpeedText.setEnabled(true);
            repeatImage.setEnabled(true);


            rewindImage.setImageResource(R.drawable.audio_rewind_white);
            fastRewindImage.setImageResource(R.drawable.audio_fast_rewind);
            playImage.setImageResource(R.drawable.pause);
            fastForwardImage.setImageResource(R.drawable.audio_fast_forward);
            forwardImage.setImageResource(R.drawable.audio_forward_white);

            if(mediaPlayer != null){
                if(!mediaPlayer.isPlaying()){
                    prepareMedia();
                }
            }

        }
        else{
            rewindImage.setEnabled(true);
            fastRewindImage.setEnabled(true);
            playImage.setEnabled(true);
            fastForwardImage.setEnabled(true);
            forwardImage.setEnabled(true);
            audioSpeedText.setEnabled(true);
            repeatImage.setEnabled(true);

            rewindImage.setImageResource(R.drawable.audio_rewind_white);
            fastRewindImage.setImageResource(R.drawable.audio_fast_rewind);
            playImage.setImageResource(R.drawable.play_white);
            fastForwardImage.setImageResource(R.drawable.audio_fast_forward);
            forwardImage.setImageResource(R.drawable.audio_forward_white);
            repeatImage.setImageResource(R.drawable.audio_repeat_disabled);
            prepareMedia();

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
                currentTimeStamp.setText("00:00");
                minutes = (mediaPlayer.getDuration() / 1000) / 60;
                seconds = (mediaPlayer.getDuration() / 1000) % 60;

                if(minutes < 10 && seconds < 10){
                    audioTimeStamp.setText("0"+minutes + ":0" + seconds);

                }
                else if(minutes < 10){
                    audioTimeStamp.setText("0"+minutes + ":" + seconds);

                }
                else if(seconds < 10){
                    audioTimeStamp.setText(minutes + ":0" + seconds);

                }
                else{
                    audioTimeStamp.setText(minutes + ":" + seconds);

                }
                initPlayer = false;
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayerComplete) {

                    stopAudio();
                    Log.d("AudioPlayerFragment","MediaPlayer: Completed");
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void playAudio(){
        try {
            playImage.setImageResource(R.drawable.pause);
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
            }
            else {
                if(mediaPlayer != null) {
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

    private void pauseAudio(){

        try {
            playImage.setImageResource(R.drawable.play_white);
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
            playImage.setImageResource(R.drawable.play_white);
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
        mediaPreferences = getSharedPreferences(mediaPref, Context.MODE_PRIVATE);
        mediaPreferencesEditor = mediaPreferences.edit();

        mediaPreferencesEditor.putString("mediaTitle", mediaTitle);
        mediaPreferencesEditor.putString("mediaStatus", mediaStatus);
        mediaPreferencesEditor.putString("mediaUrl", mediaUrl);
        mediaPreferencesEditor.putBoolean("isMediaActive", isMediaActive);
        mediaPreferencesEditor.putBoolean("isMediaReset", isMediaReset);

        mediaPreferencesEditor.apply();
    }




}
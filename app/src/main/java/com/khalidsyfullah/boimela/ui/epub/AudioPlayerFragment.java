package com.khalidsyfullah.boimela.ui.epub;

import static com.khalidsyfullah.boimela.global.StaticData.isMediaActive;
import static com.khalidsyfullah.boimela.global.StaticData.isMediaReset;
import static com.khalidsyfullah.boimela.global.StaticData.mediaPlayer;
import static com.khalidsyfullah.boimela.global.StaticData.mediaStatus;
import static com.khalidsyfullah.boimela.global.StaticData.mediaTitle;
import static com.khalidsyfullah.boimela.global.StaticData.mediaUrl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioPlayerFragment extends Fragment {


    SharedPreferences mediaPreferences;
    SharedPreferences.Editor mediaPreferencesEditor;
    String mediaPref = "Audio";
    ImageView audioImage, settingsImage, hideImage, rewindImage, fastRewindImage, playImage, fastForwardImage, forwardImage, shuffleImage, repeatImage;
    TextView audiobookTitle, authorTitle, audioTitle, voiceTitle, currentTimeStamp, audioTimeStamp;
    SeekBar audioSeekBar;
    private File file;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_audio_player,container,false);

        settingsImage = root.findViewById(R.id.audio_player_settings_image);
        hideImage = root.findViewById(R.id.audio_player_hide_image);
        audiobookTitle = root.findViewById(R.id.audio_player_audiobook_title);
        authorTitle = root.findViewById(R.id.audio_player_author_title);
        audioTitle = root.findViewById(R.id.audio_player_audio_title);
        voiceTitle = root.findViewById(R.id.audio_player_voice_title);
        audioImage = root.findViewById(R.id.audio_player_image);
        audioSeekBar = root.findViewById(R.id.audio_player_seek_bar);
        currentTimeStamp = root.findViewById(R.id.audio_player_current_timestamp);
        audioTimeStamp = root.findViewById(R.id.audio_player_audio_timestamp);
        rewindImage = root.findViewById(R.id.audio_player_audio_rewind);
        fastRewindImage = root.findViewById(R.id.audio_player_audio_fast_rewind);
        playImage = root.findViewById(R.id.audio_player_audio_play);
        fastForwardImage = root.findViewById(R.id.audio_player_audio_fast_forward);
        forwardImage = root.findViewById(R.id.audio_player_audio_forward);
        shuffleImage = root.findViewById(R.id.audio_player_shuffle);
        repeatImage = root.findViewById(R.id.audio_player_repeat);

        rewindImage.setEnabled(false);
        fastRewindImage.setEnabled(false);
        playImage.setEnabled(false);
        fastForwardImage.setEnabled(false);
        forwardImage.setEnabled(false);
        shuffleImage.setEnabled(false);
        repeatImage.setEnabled(false);

        rewindImage.setImageResource(R.drawable.audio_rewind_disabled);
        fastRewindImage.setImageResource(R.drawable.audio_fast_rewind_disabled);
        playImage.setImageResource(R.drawable.play_disabled);
        fastForwardImage.setImageResource(R.drawable.audio_fast_forward_disabled);
        forwardImage.setImageResource(R.drawable.audio_forward_disabled);
        shuffleImage.setImageResource(R.drawable.audio_shuffle_disabled);
        repeatImage.setImageResource(R.drawable.audio_repeat_disabled);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadMedia();

        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isMediaActive){
                    stopAudio();
                }
                else{
                    playAudio();
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
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_reader).navigateUp();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    private void loadMedia(){

        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        restAPI.downloadFileWithDynamicUrlAsync("https://boimelafoundation.com/starboy.mp3").enqueue(new Callback<ResponseBody>() {
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
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + "starboy.mp3");
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
        if(isMediaActive) {
            mediaTitle = audioTitle.getText().toString();
            mediaStatus = getResources().getString(R.string.paused);
            playImage.setImageResource(R.drawable.stop);

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
            shuffleImage.setEnabled(true);
            repeatImage.setEnabled(true);

            rewindImage.setImageResource(R.drawable.audio_rewind_white);
            fastRewindImage.setImageResource(R.drawable.audio_fast_rewind);
            playImage.setImageResource(R.drawable.play_white);
            fastForwardImage.setImageResource(R.drawable.audio_fast_forward);
            forwardImage.setImageResource(R.drawable.audio_forward_white);
            shuffleImage.setImageResource(R.drawable.audio_shuffle);
            repeatImage.setImageResource(R.drawable.audio_repeat);
            prepareMedia();

        }
    }





    private void prepareMedia(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

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
            playImage.setImageResource(R.drawable.stop);
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
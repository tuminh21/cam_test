package com.example.cam_test;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

public class VideoStream {

    private static final String TAG = "VideoStream";
    private MediaPlayer mediaPlayer;
    private boolean isStreaming = false;

    // Bắt đầu stream video
    public void startStreaming(String streamUrl, SurfaceHolder surfaceHolder) throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(streamUrl);
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "Media player prepared");
                mediaPlayer.start();
                isStreaming = true;
            }
        });
        mediaPlayer.prepareAsync();
    }

    // Dừng stream video
    public void stopStreaming() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isStreaming = false;
        }
    }

    // Kiểm tra xem có đang stream video hay không
    public boolean isStreaming() {
        return isStreaming;
    }
}

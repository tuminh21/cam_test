package com.example.cam_test;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.io.InputStream;
import com.example.cam_test.VideoStream;



public class MainActivity extends AppCompatActivity {

    private SurfaceView videoSurfaceView;
    private SurfaceHolder videoSurfaceHolder;
    private VideoStream videoStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo SurfaceView và SurfaceHolder
        videoSurfaceView = findViewById(R.id.videoSurfaceView);
        videoSurfaceHolder = videoSurfaceView.getHolder();

        // Khởi tạo đối tượng VideoStream và bắt đầu streaming
        videoStream = new VideoStream();
        try {
            videoStream.startStreaming("http://192.168.0.102:8081", videoSurfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hủy kết nối khi ứng dụng bị tắt
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoStream != null && videoStream.isStreaming()) {
            videoStream.stopStreaming();
        }
    }

}

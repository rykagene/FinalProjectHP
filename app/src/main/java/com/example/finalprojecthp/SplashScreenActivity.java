package com.example.finalprojecthp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreenActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_splash_screen);

        videoView = findViewById(R.id.videoView);

        Intent intent = new Intent(SplashScreenActivity.this, main_movies.class);
        startActivity(intent);


        finish();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.splashscreen;
//        Uri videoUri = Uri.parse(videoPath);
//        videoView.setVideoURI(videoUri);
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                startNextActivity();
//            }
//        });
//        videoView.start();
//    }

    private void startNextActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, main_movies.class);
        startActivity(intent);


        finish();
    }
}

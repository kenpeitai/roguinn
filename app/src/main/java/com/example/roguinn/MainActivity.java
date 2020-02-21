package com.example.roguinn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.inputmethod.InputConnectionCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer music = MediaPlayer.create(this,R.raw.btn_click);
       final VideoView videoView_01 =  findViewById(R.id.video_01);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.cccp;
        videoView_01.setVideoURI(Uri.parse(uri));
        videoView_01.start();
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.start();
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        videoView_01.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        TextView textView=findViewById(R.id.text_01);
        TextView textView2=findViewById(R.id.text_02);
        TextView textView3=findViewById(R.id.text_03);
        TextView textView4=findViewById(R.id.text_04);
        textView.bringToFront();
        textView2.bringToFront();
        textView3.bringToFront();
        textView4.bringToFront();

        Typeface typeface=Typeface.createFromAsset(getAssets(),"RazorSlice.ttf");

    }


}

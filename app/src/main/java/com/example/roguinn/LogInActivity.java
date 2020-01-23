package com.example.roguinn;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogInActivity extends AppCompatActivity {
    String edit_account;
    String edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button button = findViewById(R.id.clear);
        ImageView imageView = findViewById(R.id.facebook);
        ImageView imageView1 = findViewById(R.id.social);
        ImageView imageView2 = findViewById(R.id.google);
        ImageView imageView3 = findViewById(R.id.youtube);
        EditText editText = findViewById(R.id.edit_account);
        EditText editText1 = findViewById(R.id.edit_password);
        final TextView info =findViewById(R.id.info);
        info.bringToFront();
        editText.bringToFront();
        editText1.bringToFront();


        final VideoView videoView_02 = findViewById(R.id.video_02);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.flag;
        videoView_02.setVideoURI(Uri.parse(uri));
        videoView_02.start();


        videoView_02.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        final MediaPlayer music_click = MediaPlayer.create(this, R.raw.g_ui_slide);
        final MediaPlayer music = MediaPlayer.create(this, R.raw.btn_click);
        final MediaPlayer music1 = MediaPlayer.create(this, R.raw.blyat);
        final MediaPlayer music2 = MediaPlayer.create(this, R.raw.cukablyat);
        final MediaPlayer music3 = MediaPlayer.create(this, R.raw.blyat2);
        final MediaPlayer music4 = MediaPlayer.create(this, R.raw.blyat1);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "你不能使用这么小布尔乔亚的功能！", Toast.LENGTH_SHORT).show();
                music1.start();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "我们怀疑你受到资本主义的侵蚀！", Toast.LENGTH_SHORT).show();
                music2.start();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "警告！我们怀疑你是西方的间谍！", Toast.LENGTH_SHORT).show();
                music3.start();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "你不能使用这么小布尔乔亚的功能！", Toast.LENGTH_SHORT).show();
                music4.start();
            }
        });
        final Intent intent= new Intent(LogInActivity.this,GameActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.start();

                String s1;
                String arr[]=null;
                String fin_account=edit_account;
                String fin_password=edit_password;
                FileInputStream in = null;
                BufferedReader reader = null;
                StringBuilder content = new StringBuilder();
                try {
                    in = openFileInput("data");
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    while ((line = reader.readLine())!= null){content.append(line);}
                    reader.close();
                    Log.d("----------------->","IO处理完毕");
                    s1 = content.toString();
                    arr=s1.split("|");
                    if (fin_account.length()==0){
                    info.setText("请您输入КГБ工号");
                }else if(fin_password.length()==0){
                    info.setText("请您输入密匙");
                }else{Log.d("----------------->","for即将开始");
                    for (int i = 0; i <arr.length ; i++) {
                        String arr1[]=arr[i].split(",");
                         if (arr1[0].equals(fin_account)){
                            Log.d("----------------->","账号对了");
                            if (arr1[1] .equals(fin_password)) {
                                Log.d("----------------->","密码对了");

                                startActivity(intent);
                            }else {
                                break;
                            }
                        }
                    }
                    info.setText("工号或密匙输入错误");
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        TextView textView = findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_click.start();
                Intent intent1 = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edit_account=s.toString();


                }




        });

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               edit_password = s.toString();

            }
        });

    }
}


package com.example.roguinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;

import android.os.Bundle;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] account = new String[1];
        final String[] password = new String[1];
        final String[] password_confirm = new String[1];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        final EditText editText = findViewById(R.id.register_account);
        final EditText editText1 = findViewById(R.id.register_password);
        final EditText editText2 = findViewById(R.id.register_password_confirm);
        editText.bringToFront();
        editText1.bringToFront();
        editText2.bringToFront();
        final TextView textView_error = findViewById(R.id.password_is_too_short);
        final TextView textView_alexist = findViewById(R.id.name_is_already_exist);
        textView_error.setText("      ");
        textView_alexist.setText("      ");
        textView_error.bringToFront();
        textView_alexist.bringToFront();


        final VideoView videoView = findViewById(R.id.soviet);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.soviet;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
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


                if (s.toString().length() != 0) {
                    account[0] = s.toString();
                }


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
                if (s.length() < 6) {
                    textView_error.setText("密匙长度不得小于6位!");
                }
                if (s.length() >= 6) {
                    textView_error.setText("      ");
                    password[0] = s.toString();
                }

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password_confirm[0] = s.toString();


            }
        });

        final Button button_r = findViewById(R.id.btn_register);
        button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
               
                if (editText.getText().toString().isEmpty() || editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "您还没有输入完整信息", Toast.LENGTH_SHORT).show();
                } else if (!password[0].equals(password_confirm[0])) {
                    Toast.makeText(RegisterActivity.this, "两次输入的密匙不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                } else {
                    String data = getData();
                    if (isExist(account[0])) {
                        textView_alexist.setText("此工号已存在");
                    } else {
                        textView_alexist.setText("             ");
                        Register(account[0], password[0]);
                        startActivity(new Intent(RegisterActivity.this, GameActivity.class));
                    }
                }
            }
        });


    }

    public boolean isExist(String account) {
        File f = new File(Environment.getDataDirectory().getPath() +"/data/com.example.roguinn/files/data1");
        if (!f.exists()) {
            return false;
        }
        String s = getData();
        String arr[] = s.split("!");
        for (int i = 0; i < arr.length; i++) {
            String arr1[] = arr[i].split(",");
            if (arr1[0].equals(account)) {
               // Log.d("----------------->", "发现重名");
                return true;
            }
        }
        return false;
    }

    public void Register(String account, String password) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        String accountInfo = account + "," + password + "!";
        try {
            out = openFileOutput("data1", MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(accountInfo);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        String s1 = null;
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data1");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
           // Log.d("----------------->", "IO处理完毕");
            s1 = content.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s1;
    }
}

package com.example.langlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class LangSelect extends AppCompatActivity {

    Button lang;
    LinearLayout langSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_select);
        lang = findViewById(R.id.button);
        langSel = findViewById(R.id.lang_sel);
        langSel.setTranslationY(300);
        langSel.setAlpha(0);
        langSel.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
    }

    public void logout(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


    public void send_data(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        String file = view.getTag().toString();
        Intent intent = new Intent(getApplicationContext(),TotalWords.class);
        intent.putExtra("file",file);
        startActivity(intent);
    }
    public void back(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        finish();
    }


}
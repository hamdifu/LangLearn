package com.example.langlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class TotalWords extends AppCompatActivity {
    String language;
    EditText w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_words);
        Bundle b = getIntent().getExtras();
        language = b.getString("file");
        w = findViewById(R.id.num);
    }

    public void logout(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void back(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        finish();
    }
    public void send_data(int words){
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        Intent intent = new Intent(getApplicationContext(),Flashcard.class);
        intent.putExtra("file",language);
        intent.putExtra("words",words);
        startActivity(intent);
    }

    public void go(View view) {
        int words=Integer.parseInt(w.getText().toString());
        send_data(words);
    }

    public void send_num(View view) {

        int words = Integer.parseInt(view.getTag().toString());
        send_data(words);
    }
}
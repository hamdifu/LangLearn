package com.example.langlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Flashcard extends AppCompatActivity {
    ArrayList<JSONObject> data= new ArrayList<>();
    TextView lang;
    TextView eng,score;
    int index =0;
    Button testButton;
    ImageButton prev;
    boolean x=true;
    String language;
    int words;
    RelativeLayout card,done;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        Bundle b = getIntent().getExtras();
        language = b.getString("file");
        words = b.getInt("words");
        String file = language + ".json";
        try {
            JSONObject json = new JSONObject(JSONDataFromAssets(file));
            JSONArray jsonArray = json.getJSONArray("word");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);
                data.add(j);
            }
            Collections.shuffle(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lang = findViewById(R.id.word);
        score = findViewById(R.id.score);
        testButton = (Button) findViewById(R.id.button);
        eng = findViewById(R.id.lang);
        card = findViewById(R.id.flip_card);
        done = findViewById(R.id.done);
        prev = findViewById(R.id.back);
        mp = MediaPlayer.create(this, R.raw.studysong);
        mp.start();
        score.setText(1+"/"+words);
        setData();
    }

    public void setData(){
        try {
            eng.setText(language);
            lang.setText(data.get(index).getString("lang"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void logout(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        mp.stop();
    }

    public void back(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        finish();
        mp.stop();
    }
    private String JSONDataFromAssets(String file){
        String json = null;
        try {
            InputStream inputStream=getAssets().open(file);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;

    }

    public void flip(View view) {
        try {
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(card,"scaleX",1f,0f);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(card,"scaleX",0f,1f);
            if(index<words) {
                MediaPlayer m = MediaPlayer.create(this, R.raw.cardflip);
                m.start();
                if (x) {
                    anim1.start();
                    anim2.start();
                    lang.setText(data.get(index).getString("english"));
                    if(index==words-1)
                        testButton.setText("Finish");
                    else
                        testButton.setText("Next word");
                    eng.setText("English");
                    index++;
                    x = !x;

                } else {
                    anim1.start();
                    anim2.start();
                    setData();
//                    card.setBackgroundColor(getColor(R.color.yellow));

                    testButton.setText("Flip card");
                    int sc = index+1;
                    score.setText(sc+"/"+words);
                    x = !x;
                }
            }
            else{
                MediaPlayer m = MediaPlayer.create(this, R.raw.cheer);
                m.start();
                card.setVisibility(View.INVISIBLE);
                testButton.setVisibility(View.INVISIBLE);
                prev.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
                done.setTranslationY(300);
                done.setAlpha(0);
                done.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void prev_card(View view) {
        MediaPlayer m = MediaPlayer.create(this, R.raw.button);
        m.start();
        if(index!=0){
            index--;
            x=false;
            flip(view);
        }
    }
}
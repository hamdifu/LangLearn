package com.example.langlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

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
    boolean x=true;
    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        Bundle b = getIntent().getExtras();
        language = b.getString("file");
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
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void back(View view) {
        finish();
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
            if(index<500) {
                if (x) {
                    lang.setText(data.get(index).getString("english"));
                    eng.setText("English");
                    index++;
                    x = !x;
                    testButton.setText("Next word");
                } else {
                    setData();
                    testButton.setText("Flip card");
                    int sc = index+1;
                    score.setText(sc+"/500");
                    x = !x;
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
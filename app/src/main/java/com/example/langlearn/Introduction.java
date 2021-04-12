package com.example.langlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Introduction extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dots;
    SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        viewPager = findViewById(R.id.viewPager);
        dots = findViewById(R.id.dots);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);


    }

    public void next(View view) {
    }
}
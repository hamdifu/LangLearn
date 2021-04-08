package com.example.langlearn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

public class MainActivity extends FragmentActivity {
    ImageView sun,dayLand,nightLand;
    View daySky,nightSky;
    DayNightSwitch dayNightSwitch;
    ViewPager viewPager;

    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sun = findViewById(R.id.sun);
        dayLand = findViewById(R.id.day);
        nightLand = findViewById(R.id.night);
        daySky = findViewById(R.id.day_bg);
        nightSky = findViewById(R.id.night_bg);
        dayNightSwitch = findViewById(R.id.day_night_switch);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        sun.setTranslationY(-110);

        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                if(is_night) {
                    sun.animate().translationY(110).setDuration(1000);
                    dayLand.animate().alpha(0).setDuration(1300);
                    daySky.animate().alpha(0).setDuration(1300);
                }
                else{
                    sun.animate().translationY(-110).setDuration(1000);
                    dayLand.animate().alpha(1).setDuration(1300);
                    daySky.animate().alpha(1).setDuration(1300);
                }

            }
        });
        viewPager.setTranslationY(300);
        viewPager.setAlpha(v);
        viewPager.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
    }

private class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 1:
//                ((ImageView).findViewById(R.id.tab)).setImageResource(R.drawable.two);
//                i.setImageResource(R.drawable.two);
                return LoginTabFragment.newInstance();

            case 0:
//                ((ImageView).findViewById(R.id.tab)).setImageResource(R.drawable.one);
                return SignupTabFragment.newInstance();
            default: return LoginTabFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
}

//public void logout(View view){
//    FirebaseAuth.getInstance().signOut();
//    startActivity(new Intent(getApplicationContext(),MainActivity.class));
//}
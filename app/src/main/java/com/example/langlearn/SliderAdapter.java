package com.example.langlearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    int[] images={
            R.raw.first,
            R.raw.second,
            R.raw.third,
    };
    String[] headings={"Learn words that natives use.","Challenge your memory.","Listen to the songs best for concentration."};

    int[] description={R.string.first_slide_desc, R.string.second_slide_desc, R.string.third_slide_desc};

    public SliderAdapter(Context context) {//context means on which activity we want to display this
        this.context = context;
    }


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //for requesting the system to use the xml design(service of the design)
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // now we are telling which design
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);
        LottieAnimationView l = view.findViewById(R.id.slider_image);
        TextView h = view.findViewById(R.id.slider_heading);
        TextView d = view.findViewById(R.id.slide_desc);
        String s = headings[position] + ' ';
        l.setAnimation(images[position]);
        h.setText(s);
        d.setText(description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

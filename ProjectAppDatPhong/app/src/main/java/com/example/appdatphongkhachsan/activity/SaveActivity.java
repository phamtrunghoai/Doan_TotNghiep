package com.example.appdatphongkhachsan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.adapter.slideSaveAdapter;
import com.example.appdatphongkhachsan.model.slidesave;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class SaveActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private slideSaveAdapter slideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        viewPager = findViewById(R.id.viewpagersave);
        circleIndicator = findViewById(R.id.circlesave);
        slideAdapter = new slideSaveAdapter(this, getListslide());
        viewPager.setAdapter(slideAdapter);
        circleIndicator.setViewPager(viewPager);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }
    private List<slidesave> getListslide(){
        List<slidesave> list = new ArrayList<>();
        list.add(new slidesave(R.drawable.slidera));
        list.add(new slidesave(R.drawable.sliderb));
        list.add(new slidesave(R.drawable.sliderc));
        list.add(new slidesave(R.drawable.slidere));
        return list;
    }
}
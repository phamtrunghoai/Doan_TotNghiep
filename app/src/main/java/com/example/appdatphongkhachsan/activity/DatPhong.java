package com.example.appdatphongkhachsan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.adapter.ViewpagerAdapter;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;
import com.google.android.material.tabs.TabLayout;

public class DatPhong extends AppCompatActivity {
    Toolbar toolbarDatPhong;
    Button buttonMap;
    TabLayout MtabLayout;
    ViewPager MviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_phong);
        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
            KhoiTaoDatPhong();
            actionToolbar();
            Adater();
            ButtonMap();
        }else {
            checkConnectInternet.ShowToast(getApplicationContext(),"Kiểm tra lại kết nối của bạn!");
        }
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarDatPhong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDatPhong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Adater(){
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        MviewPager.setAdapter(viewpagerAdapter);
        MtabLayout.setupWithViewPager(MviewPager);
    }
    private void ButtonMap(){
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BtnMAP = new Intent(DatPhong.this, Map_GG.class);
                startActivity(BtnMAP);
            }
        });
    }
    private void KhoiTaoDatPhong() {
        buttonMap = (Button) findViewById(R.id.btnMap);
        MtabLayout = (TabLayout) findViewById(R.id.tablayout_datphong);
        MviewPager = (ViewPager) findViewById(R.id.viewpager_datphong);
        toolbarDatPhong =(Toolbar) findViewById(R.id.toolbarDatPhong);

    }
}
package com.example.appdatphongkhachsan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.adapter.MainAdapter;
import com.example.appdatphongkhachsan.adapter.khachsanAdapter;
import com.example.appdatphongkhachsan.adapter.menuAdapter;
import com.example.appdatphongkhachsan.model.khachsan;
import com.example.appdatphongkhachsan.model.menuapp;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Toolbar toolbar;
   // Button btnsignnhap, btnsignky;
    RecyclerView recyclerViewManHinhChinh, recyclerViewTinh;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayoutApp;
    menuAdapter menuApp;
    ArrayList<menuapp> arrayMenuApp;
    ArrayList<khachsan> arrayKhachSan;
    khachsanAdapter khachSanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KhoiTao();
        ActionToolBar();

        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
            onClickBottomNavigationView();
            setViewPager();
            //onCLickButton();
            onClcikItemListView();


        }else {
            checkConnectInternet.ShowToast(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối internet!!!");
        }
    }
//    private void onCLickButton(){
//        btnsignnhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent Dnhap = new Intent(MainActivity.this, Sign_in.class);
//                startActivity(Dnhap);
//            }
//        });
//        btnsignky.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent DKi = new Intent(MainActivity.this, Sign_in.class);
//            }
//        });
//    }

    private void onClcikItemListView() {
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        if (checkConnectInternet.haveNetworkConnection(getApplicationContext())) {
                            Intent it = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(it);
                        } else {
                            checkConnectInternet.ShowToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối!!!");
                        }
                        drawerLayoutApp.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 1: {
                        Intent it = new Intent(MainActivity.this, DatPhong.class);
                        startActivity(it);
                        break;
                    }
                    case 2: {
                        if (checkConnectInternet.haveNetworkConnection(getApplicationContext())) {
                            Intent it = new Intent(MainActivity.this, LichSuDatPhong.class);
                            startActivity(it);
                        } else {
                            checkConnectInternet.ShowToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối!!!");
                        }
                        drawerLayoutApp.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 3: {
                        if (checkConnectInternet.haveNetworkConnection(getApplicationContext())) {
                            Intent it = new Intent(MainActivity.this,LienHe.class);
                            System.out.println("ok");
                            startActivity(it);

                        } else {
                            checkConnectInternet.ShowToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối!!!");
                        }
                        drawerLayoutApp.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 4: {
                        if (checkConnectInternet.haveNetworkConnection(getApplicationContext())) {
                            Intent it = new Intent(MainActivity.this, ThongTinApp.class);
                            startActivity(it);
                        } else {
                            checkConnectInternet.ShowToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối!!!");
                        }
                        drawerLayoutApp.closeDrawer(GravityCompat.START);
                        break;
                    }
                }
            }
        });
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutApp.openDrawer(GravityCompat.START);
            }
        });
    }
private void onClickBottomNavigationView(){

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.actionhome:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.actionsave:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.actionnotification:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.actionaccount:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
        }
    });
}
private void setViewPager(){
    MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    bottomNavigationView.getMenu().findItem(R.id.actionhome).setChecked(true);
                    break;
                case 1:
                    bottomNavigationView.getMenu().findItem(R.id.actionsave).setChecked(true);
                    break;
                case 2:
                    bottomNavigationView.getMenu().findItem(R.id.actionnotification).setChecked(true);
                    break;
                case 3:
                    bottomNavigationView.getMenu().findItem(R.id.actionaccount).setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });
}


    private void KhoiTao() {
        viewPager = (ViewPager) findViewById(R.id.viewmainnn);
        bottomNavigationView = findViewById(R.id.nav_bottom);
        toolbar =(Toolbar) findViewById(R.id.toolbarTrangChinh);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recylerViewApp);
        recyclerViewTinh = (RecyclerView) findViewById(R.id.recylerViewin4);
        navigationView = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        listViewManHinhChinh  = (ListView) findViewById(R.id.listViewManHinhChinh);
        drawerLayoutApp = (DrawerLayout) findViewById(R.id.drawerLayoutApp);

        arrayMenuApp = new ArrayList<>();
        menuapp menu1 = new menuapp("https://image.flaticon.com/icons/png/512/8/8811.png","Trang Chủ");
        menuapp menu2 = new menuapp("https://image.flaticon.com/icons/png/512/38/38363.png","Đặt Phòng");
        menuapp menu3 = new menuapp("https://image.flaticon.com/icons/png/512/61/61122.png","Lịch Sử");
        menuapp menu4 = new menuapp("https://image.freepik.com/free-icon/info-logo-circle_318-947.jpg","Thông Tin");
        menuapp menu5 = new menuapp("https://image.flaticon.com/icons/png/512/14/14628.png","Liên Hệ");
        menuapp menu6 = new menuapp("https://st.quantrimang.com/photos/image/2020/07/30/Hinh-Nen-Trang-10.jpg","Tài Khoản");
        arrayMenuApp.add(menu1);
        arrayMenuApp.add(menu2);
        arrayMenuApp.add(menu3);
        arrayMenuApp.add(menu5);
        arrayMenuApp.add(menu4);
        arrayMenuApp.add(menu6);

        menuApp = new menuAdapter(arrayMenuApp, getApplicationContext());
        listViewManHinhChinh.setAdapter(menuApp);

    }
}
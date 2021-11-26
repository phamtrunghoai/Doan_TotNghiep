package com.example.appdatphongkhachsan.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appdatphongkhachsan.framnet.AllHotel_frament;
import com.example.appdatphongkhachsan.framnet.FreePay_fragment;
import com.example.appdatphongkhachsan.framnet.Giamgia_fragment;
import com.example.appdatphongkhachsan.framnet.Home24h_fragment;


public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    public ViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AllHotel_frament();
            case 1:
                return new Home24h_fragment();
            case 2:
                return new Giamgia_fragment();
            case 3:
                return new FreePay_fragment();
            default:
                return new AllHotel_frament();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Tất cả";
                break;
            case 1:
                title = "24h hủy miễn phí";
                break;
            case 2:
                title = "Giảm giá";
                break;
            case 3:
                title = "Thanh toán khi nhận phòng";
                break;

        }
        return title;
    }
}

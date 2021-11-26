package com.example.appdatphongkhachsan.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appdatphongkhachsan.framnet.AccountFragment;
import com.example.appdatphongkhachsan.framnet.NotificationFragment;
import com.example.appdatphongkhachsan.framnet.SaveFragment;
import com.example.appdatphongkhachsan.framnet.TrangchuFragment;

public class MainAdapter extends FragmentStatePagerAdapter {
    public MainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TrangchuFragment();
            case 1:
                return new SaveFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new AccountFragment();
            default:
                return new TrangchuFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}

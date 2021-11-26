package com.example.appdatphongkhachsan.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appdatphongkhachsan.framnet.LoginFragment;
import com.example.appdatphongkhachsan.framnet.SignUpFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int toolTabs;

    public LoginAdapter(FragmentManager fm, Context context, int toolTabs){

        super(fm);
        this.context = context;
        this.toolTabs = toolTabs;
    }

    @Override
    public int getCount() {
        return toolTabs;
    }

    public Fragment getItem(int position){

        switch (position){
            case 0:
                LoginFragment loginTabFragment = new LoginFragment();
                return loginTabFragment;
            case 1:
                SignUpFragment signUpTabFragment = new SignUpFragment();
                return signUpTabFragment;
            default:
                return null;
        }
    }
}

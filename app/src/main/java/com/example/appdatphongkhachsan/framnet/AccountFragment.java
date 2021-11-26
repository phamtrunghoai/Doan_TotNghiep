package com.example.appdatphongkhachsan.framnet;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.activity.Sign_in;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;

public class AccountFragment extends Fragment {

    TextView txtname, txtxphone, txtemail;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    Button btnsignnhap,btnsignky, btnlogout;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String SHARED_STATUS = "STATUS";
    public static final String KEY_NAME = "ho_ten";
    public static final String KEY_EMAIL = "email";
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup t = (ViewGroup) inflater.inflate(R.layout.fragment_account,container,false);
        btnsignnhap = (Button) t.findViewById(R.id.btnnhap);
        btnsignky = t.findViewById(R.id.btnki);
        txtname = t.findViewById(R.id.txtviewaccount);
        txtemail = t.findViewById(R.id.txtviewemail);
        relativeLayout = t.findViewById(R.id.relanhap);
        linearLayout = t.findViewById(R.id.lineraccou);
        btnlogout = t.findViewById(R.id.btnout);

        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if(name != null || email != null){
            relativeLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            txtname.setText("Name: " + name);
            txtemail.setText("Email: " + email);
        }


        if(checkConnectInternet.haveNetworkConnection(getContext())){
            //GetdataUser();
            onCLickButton();
        }else {
            checkConnectInternet.ShowToast(getContext(),"Kiểm tra lại kết nối của bạn!");
        }
        return t;
    }

    private void onCLickButton(){
        btnsignnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Dnhap = new Intent(AccountFragment.this.getContext(), Sign_in.class);
                startActivity(Dnhap);
            }
        });
        btnsignky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DKi = new Intent(AccountFragment.this.getContext(), Sign_in.class);
                startActivity(DKi);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                System.out.println("logout");

            }

        });
    }
}
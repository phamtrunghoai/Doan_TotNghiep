package com.example.appdatphongkhachsan.framnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.activity.DatPhong;
import com.example.appdatphongkhachsan.activity.Sign_in;


public class SaveFragment extends Fragment {
    TextView textVieww;
    Button buttonn;
    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup t = (ViewGroup) inflater.inflate(R.layout.fragment_save,container,false);
        textVieww = t.findViewById(R.id.txtsave);
        buttonn = t.findViewById(R.id.btnsave);
        this.buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveFragment.this.getContext(), DatPhong.class);
                startActivity(intent);
            }
        });
        this.textVieww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveFragment.this.getContext(), Sign_in.class);
                startActivity(intent);
            }
        });
        return t;
    }
}
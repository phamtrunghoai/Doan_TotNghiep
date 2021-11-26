package com.example.appdatphongkhachsan.framnet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.activity.Sign_in;
import com.example.appdatphongkhachsan.adapter.khachsanAdapter;
import com.example.appdatphongkhachsan.model.khachsan;
import com.example.appdatphongkhachsan.ultil.Server;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrangchuFragment extends Fragment {

    RelativeLayout relative;
    TextView textView;
    Button btnsignnhap,btnsignky, btnout;
    ArrayList<khachsan> arrayKhachSan;
    khachsanAdapter khachSanAdapter;
    ViewFlipper viewFlipperSlider;
    RecyclerView recyclerViewManHinhChinh, recyclerViewTinh;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String KEY_NAME = "ho_ten";
    public static final String KEY_EMAIL = "email";

    public TrangchuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup t = (ViewGroup) inflater.inflate(R.layout.fragment_trangchu,container,false);
        btnsignnhap = (Button) t.findViewById(R.id.btnnhap);
        btnsignky = t.findViewById(R.id.btnki);
        btnout= t.findViewById(R.id.btnout);
        textView = t.findViewById(R.id.txtsignin);
        relative = t.findViewById(R.id.relanhap1);
        relative.setVisibility(View.VISIBLE);
        arrayKhachSan  = new ArrayList<>();
        khachSanAdapter = new khachsanAdapter(getContext(),arrayKhachSan);
        viewFlipperSlider = (ViewFlipper) t.findViewById(R.id.viewflipperSlider);
        recyclerViewManHinhChinh = (RecyclerView) t.findViewById(R.id.recylerViewApp);
        recyclerViewTinh = (RecyclerView) t.findViewById(R.id.recylerViewin4);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(getContext(),1, RecyclerView.HORIZONTAL,false));
        recyclerViewManHinhChinh.setAdapter(khachSanAdapter);
        recyclerViewTinh.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        recyclerViewTinh.setAdapter(khachSanAdapter);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(getContext(),1, RecyclerView.HORIZONTAL,false));
        recyclerViewManHinhChinh.setAdapter(khachSanAdapter);
        recyclerViewTinh.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        recyclerViewTinh.setAdapter(khachSanAdapter);
//        System.out.println("vao day ne");

        if(checkConnectInternet.haveNetworkConnection(getContext())){
            GetDuLieuKhachSan();
            ActionViewFlipper();
            onCLickButton();
            getSharedPreferences();
        }else {
            checkConnectInternet.ShowToast(getContext(),"Kiểm tra lại kết nối của bạn!");
        }
        return t;
    }
    private void getSharedPreferences(){
         sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
         String name = sharedPreferences.getString(KEY_NAME, null);
         String email = sharedPreferences.getString(KEY_EMAIL, null);

         if(name != null || email != null){
             relative.setVisibility(View.GONE);
         }
    }
    private void GetDuLieuKhachSan() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongDanKhachSan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    String txtmaks = "", txttenks = "",txtGia="",txtSdt = "",txtEmail = "", txthinhanh = "", txtdiachi = "", txtmota = "";

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            txtmaks = jsonObject.getString("id");
                            txttenks = jsonObject.getString("ten_ks");
                            txtGia = jsonObject.getString("gia");
                            txtSdt = jsonObject.getString("sdt");
                            txtEmail = jsonObject.getString("owner_id");
                            txthinhanh = jsonObject.getString("hinh_anh1");
                            txtmota = jsonObject.getString("mo_ta");
                            txtdiachi = jsonObject.getString("dia_chi");
                            arrayKhachSan.add(new khachsan(txtmaks, txttenks,txtGia,txtSdt,txtEmail, txtdiachi, txthinhanh, txtmota));
                            khachSanAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrangchuFragment.this.getContext(), " loi " + error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void ActionViewFlipper() {
        ArrayList<Integer> sliderApp = new ArrayList<>();
        sliderApp.add(R.drawable.slidera);
        sliderApp.add(R.drawable.sliderb);
        sliderApp.add(R.drawable.sliderc);
        sliderApp.add(R.drawable.sliderd);
        sliderApp.add(R.drawable.slidere);
        for(int i = 0 ; i < sliderApp.size(); i++){
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(sliderApp.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperSlider.addView(imageView);
        }
        viewFlipperSlider.setFlipInterval(5000);
        viewFlipperSlider.setAutoStart(true);
        Animation animation_slider_in = AnimationUtils.loadAnimation(getContext(),R.anim.slider_in_right);
        Animation animation_slider_out = AnimationUtils.loadAnimation(getContext(),R.anim.slider_out_right);
        viewFlipperSlider.setInAnimation(animation_slider_in);
        viewFlipperSlider.setOutAnimation(animation_slider_out);
    }
    private void onCLickButton(){
        btnsignnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Dnhap = new Intent(TrangchuFragment.this.getContext(), Sign_in.class);
                startActivity(Dnhap);
            }
        });
        btnsignky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DKi = new Intent(TrangchuFragment.this.getContext(), Sign_in.class);
                startActivity(DKi);
            }
        });
//        btnout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.commit();
//            }
//        });
    }
}
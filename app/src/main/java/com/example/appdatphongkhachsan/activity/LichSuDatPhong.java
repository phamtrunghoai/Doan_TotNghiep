package com.example.appdatphongkhachsan.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.adapter.LichSuAdapter;
import com.example.appdatphongkhachsan.model.LichSu;
import com.example.appdatphongkhachsan.ultil.Server;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LichSuDatPhong extends AppCompatActivity {
    String makh = "";
    Toolbar toolbarLichSu;
    ListView listViewLichSu;
    ArrayList<LichSu> arrayListLichSu;
    LichSuAdapter lichSuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu_datphong);
        KhoiTao();
        ActionToolBar();
        loadThongTinKhachHang();
        getLichSuDatPhong();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarLichSu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLichSu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void KhoiTao() {
        toolbarLichSu = findViewById(R.id.toolbarLichSu);
        listViewLichSu = findViewById(R.id.listViewLichSu);

        arrayListLichSu = new ArrayList<>();
        lichSuAdapter = new LichSuAdapter(arrayListLichSu,getApplicationContext());
        listViewLichSu.setAdapter(lichSuAdapter);
    }

    private void getLichSuDatPhong() {

        if(makh.length() > 0){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,Server.DuongDanLichSuPhong,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if (response != null) {
                        String makh="", maphong="",tenks="",maks="",vitriphong="",loaiphong="",giaphong="",ngayden="",ngaydi="";
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                makh = jsonObject.getString("user_id");
                                maphong = jsonObject.getString("phong_id");
                                tenks = jsonObject.getString("ten_ks");
                                maks = jsonObject.getString("ks_id");
                                vitriphong = jsonObject.getString("vi_tri_phong");
                                loaiphong = jsonObject.getString("loai_phong");
                                giaphong = jsonObject.getString("gia_phong");
                                ngayden =jsonObject.getString("ngay_den");
                                ngaydi = jsonObject.getString("ngay_di");
                                arrayListLichSu.add(new LichSu(makh,maphong,tenks,maks,vitriphong,loaiphong,giaphong,ngayden,ngaydi));
                                lichSuAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    checkConnectInternet.ShowToast(getApplicationContext(),error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String > hasMap =new HashMap<String,String>();
                    hasMap.put("makh",makh);
                    return hasMap;
                }
            };
            requestQueue.add(jsonArrayRequest);
        }
    }

    public void loadThongTinKhachHang(){
        SharedPreferences preferences = getSharedPreferences("thongtinkhachhang",MODE_PRIVATE);
        makh = preferences.getString("cmnd","");
    }
}
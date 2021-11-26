package com.example.appdatphongkhachsan.framnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.adapter.DatPhongAdapter;
import com.example.appdatphongkhachsan.model.khachsan;
import com.example.appdatphongkhachsan.ultil.Server;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home24h_fragment extends Fragment {

    DatPhongAdapter datPhongAdapter;
    ArrayList<khachsan> arrayKhachSan;
    ListView listViewKhachSan24h;
    public Home24h_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup t = (ViewGroup) inflater.inflate(R.layout.fragment_home24h_fragment, container, false);
        listViewKhachSan24h = t.findViewById(R.id.listViewDatPhong24h);
        arrayKhachSan = new ArrayList<>();
        datPhongAdapter = new DatPhongAdapter(getContext(), arrayKhachSan);
        listViewKhachSan24h.setAdapter(datPhongAdapter);
        if(checkConnectInternet.haveNetworkConnection(getContext())){
            getDataKhachSan();

        }else {
            checkConnectInternet.ShowToast(getContext(),"Kiểm tra lại kết nối của bạn!");
        }
        return t;
    }
    private void getDataKhachSan() {
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
                            datPhongAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkConnectInternet.ShowToast(getContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
package com.example.appdatphongkhachsan.framnet;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.activity.MainActivity;
import com.example.appdatphongkhachsan.ultil.Server;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONObject;
import org.json.JSONTokener;

public class LoginFragment extends Fragment {

    EditText txtname, txtpass;
    Button btnsignin;
    float v = 0;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
//    public static final String SHARED_STATUS = "STATUS";
    public static final String KEY_NAME = "ho_ten";
    public static final String KEY_EMAIL = "email";
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup t = (ViewGroup) inflater.inflate(R.layout.fragment_login,container,false);
        txtname = t.findViewById(R.id.txtusername);
        txtpass = t.findViewById(R.id.txtpass);
        btnsignin = t.findViewById(R.id.btnsignin);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if(name != null){
            Intent intent = new Intent(LoginFragment.this.getContext(), MainActivity.class);
            startActivity(intent);
        }


        if(checkConnectInternet.haveNetworkConnection(getContext())){
           onclickLogin();

        }else {
            checkConnectInternet.ShowToast(getContext(),"Bạn hãy kiểm tra lại kết nối internet!!!");
        }

    return t;
    }
    public void onclickLogin(){
        this.btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email, password;
                email = String.valueOf(txtname.getText());
                password = String.valueOf(txtpass.getText());



                if (!email.equals("") && !password.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "email";
                            field[1] = "mat_khau";
                            field[2] = "role";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = email;
                            data[1] = password;
                            data[2] = "3";
                            PutData putData;
                            putData = new PutData(Server.DuongDanLogin, "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
//                                    System.out.println(putData.getData());
                                    String result = putData.getResult();
//                                    System.out.println(result);
                                    Toast.makeText(LoginFragment.this.getContext(),result , Toast.LENGTH_SHORT).show();
                                    if (result.equals("Username or Password wrong")){
                                        Toast.makeText(LoginFragment.this.getContext(),"Không ĐC" , Toast.LENGTH_SHORT).show();
                                    } else {

                                        try {
//                                            System.out.println(result.getClass().getName());
                                            JSONObject json =(JSONObject) new JSONTokener(result).nextValue();
//                                            System.out.println((String)json.getString("ho_ten"));
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(KEY_NAME, json.getString("ho_ten"));
                                            editor.putString(KEY_EMAIL, json.getString("email"));
                                            editor.apply();
                                            Intent intent= new Intent(LoginFragment.this.getContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                        catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }


                                }
                            }

                        }
                    });
                }
                else if (email.equals("") && password.equals("")) {
                    Toast.makeText(LoginFragment.this.getContext(), "Bạn cần nhập User name và Password", Toast.LENGTH_SHORT).show();
                }
                else if (email.equals("") || email == null) {
                    Toast.makeText(LoginFragment.this.getContext(), "Bạn cần nhập User name", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals("") || password == null) {
                    Toast.makeText(LoginFragment.this.getContext(), "Bạn cần nhập Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
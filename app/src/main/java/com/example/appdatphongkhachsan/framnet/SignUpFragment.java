package com.example.appdatphongkhachsan.framnet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.activity.Sign_in;
import com.example.appdatphongkhachsan.ultil.Server;
import com.example.appdatphongkhachsan.ultil.checkConnectInternet;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {
    EditText passcode1, passcode2, passcode3, passcode4;
    RelativeLayout boxx1, boxx2;
    EditText edtfullname, edtemail, edtpassword, edtphone, edtadres;
    Button signup, btnGui;
    static int code;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup t = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up,container,false);
        signup = t.findViewById(R.id.btnsignup);
        edtfullname = t.findViewById(R.id.txtfullname);
        edtemail = t.findViewById(R.id.txtemail);
        edtpassword = t.findViewById(R.id.txtpass);
        edtphone = t.findViewById(R.id.txtphone);
        edtadres = t.findViewById(R.id.txtadres);
        boxx1 = t.findViewById(R.id.box1);
        boxx2 = t.findViewById(R.id.box2);
        passcode1 = t.findViewById(R.id.edt1);
        passcode2 = t.findViewById(R.id.edt2);
        passcode3 = t.findViewById(R.id.edt3);
        passcode4 = t.findViewById(R.id.edt4);
        btnGui = t.findViewById(R.id.btngui);
        boxx1.setVisibility(View.VISIBLE);
        boxx2.setVisibility(View.GONE);
        Toast.makeText(SignUpFragment.this.getContext(), "error", Toast.LENGTH_LONG).show();

        passcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    passcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    passcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    passcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if(checkConnectInternet.haveNetworkConnection(getContext())){
            BtnObclick();
        }else {
            checkConnectInternet.ShowToast(getContext(),"Bạn hãy kiểm tra lại kết nối internet!!!");
        }

        return t;
    }
    private void BtnObclick() {
        this.btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(code);
                checkcode();
            }
        });


        this.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname, email, password, phone, adress;
                fullname = String.valueOf(edtfullname.getText());
                email = String.valueOf(edtemail.getText());
                password = String.valueOf(edtpassword.getText());
                phone = String.valueOf(edtphone.getText());
                adress = String.valueOf(edtadres.getText());


                if (!fullname.equals("") && !email.equals("") && !password.equals("") && !phone.equals("") && !adress.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            sendVeriyf();
                        }
                    });
                } else {
                    register();
                    validateFullname();
                    validateEmail();
                    validatePass();
                    validatePhone();
                    validateAdress();
                    return;
                }
            }
        });
    }
    public  void register(){
        if (!validateFullname() | !validateEmail() | !validatePass() | !validatePhone() | !validateAdress()){
            return;
        }
        String regfullname = edtfullname.getText().toString();
        String regemail = edtemail.getText().toString();
        String regpass = edtpassword.getText().toString();
        String regrephone = edtphone.getText().toString();
        String regreadress = edtadres.getText().toString();
    }
    private Boolean validateFullname(){
        String val = edtfullname.getText().toString();
        if (val.isEmpty()){
            edtfullname.setError("Field cannot be empty");
            return false;
        } else {
            edtfullname.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = edtfullname.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            edtemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)){
            edtemail.setError("Invalid emali addres");
            return false;
        }else {
            edtemail.setError(null);
            return true;
        }
    }
    private Boolean validatePass(){
        String val = edtpassword.getText().toString();
        String passwordVal =  "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()){
            edtpassword.setError("Field cannot be empty");
            return false;
        }else if (!val.matches(passwordVal)){
            edtpassword.setError("Password is too weak");
            return false;
        }else {
            edtpassword.setError(null);
            return true;
        }
    }
    private Boolean validatePhone() {
        String val = edtphone.getText().toString();
        if(!Pattern.matches("[a-zA-Z]+", val)) {
            return edtphone.length() > 6 && edtphone.length() <= 13;
        }
        return false;
    }
    private Boolean validateAdress(){
        String val = edtadres.getText().toString();
        if (val.isEmpty()){
            edtadres.setError("Field cannot be empty");
            return false;
        } else {
            edtadres.setError(null);
            return true;
        }
    }
    private void sendVeriyf(){
        Random random = new Random();
        code = random.nextInt(8999)+1000;
        System.out.println(code);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongDanVerify, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SignUpFragment.this.getContext(), response, Toast.LENGTH_SHORT).show();
                boxx1.setVisibility(View.GONE);
                boxx2.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpFragment.this.getContext(), "error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("email",edtemail.getText().toString());
                param.put("code", String.valueOf(code));

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void checkcode(){
        String inputcode;
        inputcode = passcode1.getText().toString()+passcode2.getText().toString()+passcode3.getText().toString()+passcode4.getText().toString();
        System.out.println(code);
        System.out.println(inputcode);

        if(inputcode.equals(String.valueOf(code))){
            final String fullname, email, password, phone, adress;
            fullname = String.valueOf(edtfullname.getText());
            email = String.valueOf(edtemail.getText());
            password = String.valueOf(edtpassword.getText());
            phone = String.valueOf(edtphone.getText());
            adress = String.valueOf(edtadres.getText());

            String[] field = new String[6];
            field[0] = "ho_ten";
            field[1] = "email";
            field[2] = "mat_khau";
            field[3] = "sdt";
            field[4] = "dia_chi";
            field[5] = "role";
            //Creating array for data
            String[] data = new String[6];
            data[0] = fullname;
            data[1] = email;
            data[2] = password;
            data[3] = phone;
            data[4] = adress;
            data[5] = "3";

            PutData putData;
            putData = new PutData(Server.DuongDanSignup, "POST", field, data);

            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    System.out.println(result);
                    //System.out.println("full name" +fullname.toString());
                    Toast.makeText(SignUpFragment.this.getContext(), result, Toast.LENGTH_SHORT).show();
                    System.out.println(result);
                    if (result.toString().equals("Sign up Success")) {
                        Intent intent = new Intent(SignUpFragment.this.getContext(), Sign_in.class);
                        startActivity(intent);
                    } else {
//                        System.out.println("full name" + fullname.toString());
                        Toast.makeText(SignUpFragment.this.getContext(), "Not Sign up Success", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            Toast.makeText(SignUpFragment.this.getContext(), "Success", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(SignUpFragment.this.getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
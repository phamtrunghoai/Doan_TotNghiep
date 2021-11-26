package com.example.appdatphongkhachsan.model;

import java.io.Serializable;

public class user implements Serializable {
    private String ho_ten;
    private String email;
    private String sdt;
    private String diachi;

    public user(String ho_ten, String email, String sdt, String diachi) {
        this.ho_ten = ho_ten;
        this.email = email;
        this.sdt = sdt;
        this.diachi = diachi;
    }
    public user() {
        this.ho_ten ="";
        this.sdt = "";
        this.email = "";
        this.diachi = "";
    }


    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String maks) {
        this.ho_ten = maks;
    }


    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }


}


package com.example.peteyecareapp.model;

import java.util.Date;

public class Wifi {
    private long id;
    private String ssid;
    private String password_wifi;
    private Date data_criacao;

    public Wifi(long id, String ssid, String password_wifi, Date data_criacao) {
        this.id = id;
        this.ssid = ssid;
        this.password_wifi = password_wifi;
        this.data_criacao = data_criacao;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword_wifi() {
        return password_wifi;
    }

    public void setPassword_wifi(String password_wifi) {
        this.password_wifi = password_wifi;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}

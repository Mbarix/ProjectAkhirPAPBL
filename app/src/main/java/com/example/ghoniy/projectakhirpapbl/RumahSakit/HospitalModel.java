package com.example.ghoniy.projectakhirpapbl.RumahSakit;

public class HospitalModel {
    private String nama, address, web, tlpn,open, gambar, latlong;

    public HospitalModel(String nama, String address, String web, String tlpn, String open, String gambar, String latlong) {
        this.nama = nama;
        this.address = address;
        this.web = web;
        this.tlpn = tlpn;
        this.open = open;
        this.gambar = gambar;
        this.latlong = latlong;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTlpn() {
        return tlpn;
    }

    public void setTlpn(String tlpn) {
        this.tlpn = tlpn;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }
}

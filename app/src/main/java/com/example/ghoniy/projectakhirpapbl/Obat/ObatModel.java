package com.example.ghoniy.projectakhirpapbl.Obat;

public class ObatModel {
    private String namaObat;
    private String penjelasan;
    private String gejala;
    private String gambarObat;

    public ObatModel(){}

    public ObatModel(String namaObat, String penjelasan, String gejala, String gambarObat) {
        this.namaObat = namaObat;
        this.penjelasan = penjelasan;
        this.gejala = gejala;
        this.gambarObat = gambarObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public void setGambarObat(String gambarObat) {
        this.gambarObat = gambarObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public String getGejala() {
        return gejala;
    }

    public String getGambarObat() {
        return gambarObat;
    }
}

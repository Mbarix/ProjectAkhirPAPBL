package com.example.ghoniy.projectakhirpapbl.Penyakit;

public class PenyakitModel {
    private String namaPenyakit;
    private String gejala;
    private String penjelasan;

    public PenyakitModel(){}

    public PenyakitModel(String namaPenyakit, String gejala, String penjelasan) {
        this.namaPenyakit = namaPenyakit;
        this.gejala = gejala;
        this.penjelasan = penjelasan;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public String getGejala() {
        return gejala;
    }

    public String getPenjelasan() {
        return penjelasan;
    }
}

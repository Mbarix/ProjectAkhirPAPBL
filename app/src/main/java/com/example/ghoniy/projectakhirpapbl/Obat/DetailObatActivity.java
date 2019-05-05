package com.example.ghoniy.projectakhirpapbl.Obat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ghoniy.projectakhirpapbl.R;

public class DetailObatActivity extends AppCompatActivity {
    private String namaObat, penjelasan, macamPenyakit, gambarObat;
    private ImageView imageObat;
    private TextView textNamaObat, textPenjelasanObat, textMacamPenyakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);

        //jadi kan sebelum activity ini dibuat melalui intent, kita mengirim data
        //script ini buat nangkep data tersebut
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                namaObat = null;
                penjelasan = null;
                macamPenyakit = null;
                gambarObat = null;
            } else {
                namaObat = extras.getString("namaObat");
                penjelasan = extras.getString("penjelasan");
                macamPenyakit = extras.getString("macamPenyakit");
                gambarObat = extras.getString("gambarObat");
            }
        } else {
            namaObat = (String) savedInstanceState.getSerializable("namaObat");
            penjelasan = (String) savedInstanceState.getSerializable("penjelasan");
            macamPenyakit = (String) savedInstanceState.getSerializable("macamPenyakit");
            gambarObat = (String) savedInstanceState.getSerializable("gambarObat");
        }

        //inisialisasi view
        imageObat = (ImageView) findViewById(R.id.gambarObat);
        textNamaObat = (TextView) findViewById(R.id.textNamaObatDetail);
        textPenjelasanObat = (TextView) findViewById(R.id.textPenjelasanDetail);
        textMacamPenyakit = (TextView) findViewById(R.id.textMacamPenyakitDetail);

        //set nilai ke view
        textNamaObat.setText(namaObat);
        textPenjelasanObat.setText(penjelasan);
        textMacamPenyakit.setText(macamPenyakit);

        //library glide buat nampilin gambar lewat url yang disimpen di variabel gambarObat
        Glide.with(imageObat.getContext())
                .load(gambarObat)
                .into(imageObat);
    }
}

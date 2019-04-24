package com.example.ghoniy.projectakhirpapbl.Penyakit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ghoniy.projectakhirpapbl.R;

public class SearchPenyakitActivity extends AppCompatActivity {
    private String[] SPINNERLIST = {"Pusing", "Batuk", "Menggigil", "Muntah", "Perut Perih", "Sering Bersendawa", "Hidung Berlendir", "Demam", "Mual", "Tidak Selera Makan", "Diare", "Perut Membesar", "Tenggorokan Membesar", "Suara Berubah", "Kesulitan Menelan Makanan", "Sulit Menelan", "Kelelahan", "Sakit Kepala", "Berat Badan Turun", "Sering Lapar", "Sering Haus", "Demam Tinggi", "Lemah", "Nafsu Makan Turun", "Sakit Perut", "Bercak Merah", "Nyeri Otot", "Kesemutan", "Telinga Berdengung", "Gangguan Penglihatan", "Sering Pingsan", "Lemas", "Nafas Pendek", "Dehidrasi", "Mata Merah", "Bercak", "BAB Berdarah", "Pantat Nyeri", "Bengkak pada Anus", "Batuk Darah", "Sesak Nafas", "Nafsu Makan Hilang", "Radang Tenggorokan", "Sendi Kemerahan", "Sendi Nyeri", "Sendi Panas", "Nyeri Pinggang", "Badan Lemas", "Kaki Membengkak", "Berat Badan Turun"};
    private Button cari;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_penyakit);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spinner = (Spinner) findViewById(R.id.spinner_penyakit);
        spinner.setAdapter(arrayAdapter);

        cari = (Button) findViewById(R.id.button_cari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PenyakitActivity.class)
                        .putExtra("gejala", spinner.getSelectedItem().toString());
                view.getContext().startActivity(intent);
            }
        });
    }
}

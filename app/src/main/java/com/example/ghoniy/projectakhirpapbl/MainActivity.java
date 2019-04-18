package com.example.ghoniy.projectakhirpapbl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.ghoniy.projectakhirpapbl.Obat.ObatActivity;

public class MainActivity extends AppCompatActivity {
    private CardView cariPenyakit, cariObat, cariRumahSakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cariPenyakit = (CardView) findViewById(R.id.cardCariPenyakit);
        cariObat = (CardView) findViewById(R.id.cardCariObat);
        cariRumahSakit = (CardView) findViewById(R.id.cardCariRumahSakit);

        //ketika card cari penyakit diklik
        cariPenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //ketika card cari obat diklik
        cariObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ObatActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        //ketika card cari rumah sakit diklik
        cariRumahSakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

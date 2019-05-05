package com.example.ghoniy.projectakhirpapbl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.ghoniy.projectakhirpapbl.Obat.ObatActivity;
import com.example.ghoniy.projectakhirpapbl.Penyakit.SearchPenyakitActivity;
import com.example.ghoniy.projectakhirpapbl.RumahSakit.HospitalMapsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CardView cariPenyakit, cariObat, cariRumahSakit;
    private Intent intent;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cariPenyakit = (CardView) findViewById(R.id.cardCariPenyakit);
        cariObat = (CardView) findViewById(R.id.cardCariObat);
        cariRumahSakit = (CardView) findViewById(R.id.cardCariRumahSakit);

        checkAndRequestPermissions();

        //ketika card cari penyakit diklik
        cariPenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), SearchPenyakitActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        //ketika card cari obat diklik
        cariObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), ObatActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        //ketika card cari rumah sakit diklik
        cariRumahSakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HospitalMapsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public boolean checkAndRequestPermissions() {
        int permissionLocC = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionLocF = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionInternet = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocC != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (permissionLocF != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionInternet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}

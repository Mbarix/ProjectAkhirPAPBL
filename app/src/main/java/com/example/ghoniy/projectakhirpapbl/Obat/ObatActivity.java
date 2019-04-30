package com.example.ghoniy.projectakhirpapbl.Obat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ghoniy.projectakhirpapbl.Penyakit.PenyakitActivity;
import com.example.ghoniy.projectakhirpapbl.Penyakit.PenyakitAdapter;
import com.example.ghoniy.projectakhirpapbl.Penyakit.PenyakitModel;
import com.example.ghoniy.projectakhirpapbl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ObatActivity extends AppCompatActivity {
    private String url;
    private RecyclerView recyclerView;
    private ObatAdapter obatAdapter;
    private List<ObatModel> obatList = new ArrayList<>();
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private EditText pencarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat);

        url = "http://amamipro.000webhostapp.com/json/getobat.php";
        getData();

        pencarian = (EditText) findViewById(R.id.cariObat);
        pencarian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });
    }

    public void getData() {
        ArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        ObatModel obat = new ObatModel();
                        JSONObject object = response.getJSONObject(i);
                        String namaObat = object.getString("obat");
                        String penjelasan = object.getString("penjelasan");
                        String penyakit = object.getString("gejala");
                        String urlGambar = object.getString("gambar");
                        obat.setNamaObat(namaObat);
                        obat.setPenjelasan(penjelasan);
                        obat.setGejala(penyakit);
                        obat.setGambarObat(urlGambar);
                        obatList.add(obat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setAdapter(obatList);
                obatAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue = Volley.newRequestQueue(ObatActivity.this);
        requestQueue.add(ArrayRequest);
    }

    public void setAdapter(List<ObatModel> obatList) {
        //Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewObat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        obatAdapter = new ObatAdapter(ObatActivity.this, obatList);
        recyclerView.setAdapter(obatAdapter);
    }

    private void search(String cari){
        List<ObatModel> obatFiltered = new ArrayList<>();
        for(ObatModel data : obatList){
            if(data.getNamaObat().toLowerCase().contains(cari.toLowerCase())||data.getGejala().toLowerCase().contains(cari.toLowerCase())){
                obatFiltered.add(data);
            }
        }
        obatAdapter.addItem(obatFiltered);
        recyclerView.setAdapter(obatAdapter);
    }
}

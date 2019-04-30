package com.example.ghoniy.projectakhirpapbl.Penyakit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ghoniy.projectakhirpapbl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


public class PenyakitActivity extends AppCompatActivity {
    private String gejala;
    private String url;
    private RecyclerView recyclerView;
    private PenyakitAdapter penyakitAdapter;
    private List<PenyakitModel> penyakitList = new ArrayList<>();
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                gejala = null;
            } else {
                gejala = extras.getString("gejala");
            }
        } else {
            gejala = (String) savedInstanceState.getSerializable("gejala");
        }

        url = "http://amamipro.000webhostapp.com/json/getpenyakit.php?gejala="+gejala;

        //Recyclerview
        getData();

    }

    public void getData() {
        ArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i<response.length();i++) {
                    try {
                        PenyakitModel penyakit = new PenyakitModel();
                        JSONObject object = response.getJSONObject(i);
                        String namaPenyakit = object.getString("penyakit");
                        String penjelasan = object.getString("penjelasan");
                        String gejala = object.getString("gejala");
                        penyakit.setNamaPenyakit(namaPenyakit);
                        penyakit.setPenjelasan(penjelasan);
                        penyakit.setGejala(gejala);
                        penyakitList.add(penyakit);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setAdapter(penyakitList);
                penyakitAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue = Volley.newRequestQueue(PenyakitActivity.this);
        requestQueue.add(ArrayRequest);
    }

    public void setAdapter (List<PenyakitModel> penyakitList) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPenyakit);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        penyakitAdapter = new PenyakitAdapter(PenyakitActivity.this, penyakitList);
        recyclerView.setAdapter(penyakitAdapter);
    }
}

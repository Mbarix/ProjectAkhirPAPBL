package com.example.ghoniy.projectakhirpapbl.Penyakit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ghoniy.projectakhirpapbl.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PenyakitActivity extends AppCompatActivity {
    private String gejala;
    private String url;
    private RecyclerView recyclerView;
    private PenyakitAdapter penyakitAdapter;
    private List<PenyakitModel> penyakitList = new ArrayList<>();
    private OkHttpClient client = new OkHttpClient();

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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPenyakit);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        penyakitAdapter = new PenyakitAdapter(PenyakitActivity.this, penyakitList);
        recyclerView.setAdapter(penyakitAdapter);
    }

    private void getData(){
        Request request = new Request.Builder()
                .url(url)
                .cacheControl(new CacheControl.Builder().noCache().build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ON FAILURE", e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " +  response);
                } else {
                    final String result = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parse(result);
                        }
                    });
                }
            }
        });

    }

    public void parse(String strjson){
        try {
            Log.d("STRJSON",strjson);

            // Getting JSON Array node
            JSONArray penyakitArray = new JSONArray(strjson);

            // looping through All comments
            for (int i = 0; i < penyakitArray.length(); i++) {
                PenyakitModel penyakit = new PenyakitModel();
                JSONObject object = penyakitArray.getJSONObject(i);
                String namaPenyakit = object.getString("penyakit");
                String penjelasan = object.getString("penjelasan");
                String gejala = object.getString("gejala");
                penyakit.setNamaPenyakit(namaPenyakit);
                penyakit.setPenjelasan(penjelasan);
                penyakit.setGejala(gejala);
                penyakitList.add(penyakit);
            }
            penyakitAdapter.notifyDataSetChanged();
        } catch (Throwable t) {
            Log.e("Tes DOT Indonesia", "Could not parse malformed JSON: \"" + strjson + "\"");
        }
    }
}

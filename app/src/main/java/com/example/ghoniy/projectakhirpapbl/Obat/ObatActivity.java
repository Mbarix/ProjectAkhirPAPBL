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
        getData(); //nangkep data obat pake volley

        //ini ketika kita ketik di fitur pencarian
        pencarian = (EditText) findViewById(R.id.cariObat);
        pencarian.addTextChangedListener(new TextWatcher() {
            //ketiga method ini wajib dioverride karena emang maunya TextWatcher, biar ga error
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //yang kita pake cuma ini aja, jadi sesuai namanya, method ini kepake ketika kita selesai ngetik method ini baru dipanggil
            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });
    }

    public void getData() {
        //pake library volley, karena json datanya dalam array, jadi JSONarray yang kita pake dulu
        ArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        ObatModel obat = new ObatModel();
                        //terus tiap index arraynya diambil JSONobjectnya
                        JSONObject object = response.getJSONObject(i);
                        String namaObat = object.getString("obat"); //ini ambil nilai json object dengan key "obat"
                        String penjelasan = object.getString("penjelasan"); //ini ambil nilai json object dengan key "penjelasan"
                        String penyakit = object.getString("gejala"); //ini ambil nilai json object dengan key "gejala"
                        String urlGambar = object.getString("gambar"); //ini ambil nilai json object dengan key "gambar"
                        //tiap data diatas yang udah didapet, di set ke class ObatModel lewat object obat
                        obat.setNamaObat(namaObat);
                        obat.setPenjelasan(penjelasan);
                        obat.setGejala(penyakit);
                        obat.setGambarObat(urlGambar);
                        obatList.add(obat); //object obat disimpen di arraylist
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setAdapter(obatList); //manggil method setAdapter guna adapter agar menampilkan data ke recyclerview
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
        //Recyclerview obat
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewObat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //orientasi recycler viewnya vertikal
        recyclerView.setLayoutManager(layoutManager);
        obatAdapter = new ObatAdapter(ObatActivity.this, obatList);
        recyclerView.setAdapter(obatAdapter); //setAdapter ini method panggilan dari recyclerview (bukan method setAdapter dari class ini)
                                              //gunanya buat ngeset adapternya ke recyclerview, jadi data dari model disalurin lewat adapternya (adapter ibarat jembatan penghubung)
    }

    //ini buat pencarian
    private void search(String cari){
        //bikin list baru buat nampung data hasil search
        List<ObatModel> obatFiltered = new ArrayList<>();
        for(ObatModel data : obatList){ //ini namanya foreach, jadi yang kiri itu object yang kita gunain, yang kanan list sebelum hasil search
                                        //jadi bakal diulang sebanyak panjang list sebelum search (hasil seluruhnya)
            //edittext disesuaikan dengan nama obat atau macam penyakit ditiap index obatList
            if(data.getNamaObat().toLowerCase().contains(cari.toLowerCase())||data.getGejala().toLowerCase().contains(cari.toLowerCase())){
                obatFiltered.add(data); //kalo ada ditambahin ke list yang baru
            }
        }
        obatAdapter.addItem(obatFiltered); //nah list yang baru tadi ditaro buat ngegantiin list sebelumnya
        recyclerView.setAdapter(obatAdapter); //dan disetAdapter guna recyclernya berubah sesuasi list yang baru
    }
}

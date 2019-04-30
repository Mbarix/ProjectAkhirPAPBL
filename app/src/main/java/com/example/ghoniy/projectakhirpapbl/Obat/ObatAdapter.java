package com.example.ghoniy.projectakhirpapbl.Obat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghoniy.projectakhirpapbl.Penyakit.PenyakitActivity;
import com.example.ghoniy.projectakhirpapbl.R;

import java.util.ArrayList;
import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ViewHolder> {
    List<ObatModel> listObat = new ArrayList<>();
    Activity activity;
    private String gambarObat, penjelasan, namaObat, macamPenyakit;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaObat, gejala;
        private CardView cardView;

        public ViewHolder(View v) {
            super(v);
            namaObat =(TextView)v.findViewById(R.id.namaObat);
            gejala =(TextView)v.findViewById(R.id.macamPenyakit);
            cardView = (CardView)v.findViewById(R.id.cardViewObat);
        }
    }

    public ObatAdapter(Activity activity, List<ObatModel> listObat) {
        this.listObat = listObat;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return listObat.size();
    }

    @Override
    public ObatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.obat, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ObatAdapter.ViewHolder holder, final int position) {
        holder.namaObat.setText(listObat.get(position).getNamaObat());
        holder.gejala.setText(listObat.get(position).getGejala());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gambarObat = listObat.get(position).getGambarObat();
                penjelasan = listObat.get(position).getPenjelasan();
                namaObat = listObat.get(position).getNamaObat();
                macamPenyakit = listObat.get(position).getGejala();
                Intent intent = new Intent(view.getContext(), DetailObatActivity.class)
                        .putExtra("penjelasan", penjelasan)
                        .putExtra("gambarObat", gambarObat)
                        .putExtra("namaObat", namaObat)
                        .putExtra("macamPenyakit", macamPenyakit);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void addItem(List<ObatModel> list){
        this.listObat = list;
        notifyDataSetChanged();
    }
}

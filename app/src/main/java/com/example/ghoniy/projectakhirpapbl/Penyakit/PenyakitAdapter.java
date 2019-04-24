package com.example.ghoniy.projectakhirpapbl.Penyakit;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.ghoniy.projectakhirpapbl.Obat.ObatAdapter;
import com.example.ghoniy.projectakhirpapbl.Obat.ObatModel;
import com.example.ghoniy.projectakhirpapbl.R;

import java.util.ArrayList;
import java.util.List;

public class PenyakitAdapter extends RecyclerView.Adapter<PenyakitAdapter.ViewHolder> {
    List<PenyakitModel> listPenyakit = new ArrayList<>();
    Activity activity;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaPenyakit, penjelasan, gejala;

        public ViewHolder(View v) {
            super(v);
            namaPenyakit =(TextView)v.findViewById(R.id.namaPenyakit);
            penjelasan =(TextView)v.findViewById(R.id.penjelasanPenyakit);
            gejala =(TextView)v.findViewById(R.id.gejalaPenyakit);
        }
    }

    public PenyakitAdapter(Activity activity, List<PenyakitModel> listPenyakit) {
        this.listPenyakit = listPenyakit;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return listPenyakit.size();
    }

    @Override
    public PenyakitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.penyakit, parent, false);
        PenyakitAdapter.ViewHolder vh = new PenyakitAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PenyakitAdapter.ViewHolder holder, int position) {
        holder.namaPenyakit.setText(listPenyakit.get(position).getNamaPenyakit());
        holder.penjelasan.setText(listPenyakit.get(position).getPenjelasan());
        holder.gejala.setText(listPenyakit.get(position).getGejala());
    }
}

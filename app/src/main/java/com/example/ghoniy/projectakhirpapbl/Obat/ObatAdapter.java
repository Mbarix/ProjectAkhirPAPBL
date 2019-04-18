package com.example.ghoniy.projectakhirpapbl.Obat;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghoniy.projectakhirpapbl.R;

import java.util.ArrayList;
import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ViewHolder> {
    List<ObatModel> listObat = new ArrayList<>();
    Activity activity;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaObat, penjelasan, gejala;
        private ImageView gambarObat;

        public ViewHolder(View v) {
            super(v);
            namaObat =(TextView)v.findViewById(R.id.textNamaObatDetail);
            penjelasan =(TextView)v.findViewById(R.id.textPenjelasan);
            gejala =(TextView)v.findViewById(R.id.textGejalaObatDetail);
            gambarObat = (ImageView)v.findViewById(R.id.gambarObat);
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
    public void onBindViewHolder(ObatAdapter.ViewHolder holder, int position) {
        holder.namaObat.setText(listObat.get(position).getNamaObat());
        holder.penjelasan.setText(listObat.get(position).getPenjelasan());
        holder.gejala.setText(listObat.get(position).getGejala());

        //glide library for handle image
        holder.gambarObat.setImageResource(listObat.get(position).getGambarObat());
//        Glide.with(holder.avatar.getContext())
//                .load(listComment.get(position).getAvatarImg())
//                .into(holder.avatar);
    }
}

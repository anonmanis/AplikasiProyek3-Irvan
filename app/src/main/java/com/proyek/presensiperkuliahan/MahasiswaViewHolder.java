package com.proyek.presensiperkuliahan;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
    public TextView Nama;
    public TextView NPM;

    public MahasiswaViewHolder(View itemView) {
        super(itemView);
        Nama = itemView.findViewById(R.id.tvNama);
        NPM = itemView.findViewById(R.id.tvNPM);
    }

    public void bindToMahasiswa(mahasiswa mhsw){
        Nama.setText(mhsw.Nama);
        NPM.setText(mhsw.MhswID);
    }
}

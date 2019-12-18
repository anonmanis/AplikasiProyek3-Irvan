package com.proyek.presensiperkuliahan;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
    public TextView Nama;
    public TextView NPM;
    public TextView status;
    public Switch swtKhdrn;

    public MahasiswaViewHolder(View itemView) {
        super(itemView);
        Nama = itemView.findViewById(R.id.tvNama);
        NPM = itemView.findViewById(R.id.tvNPM);
        status = itemView.findViewById(R.id.tvA);
        swtKhdrn = itemView.findViewById(R.id.swtPresensi);
    }

    public void bindToMahasiswa(mahasiswa mhsw){
        Nama.setText(mhsw.Nama);
        NPM.setText(mhsw.MhswID);
        status.setText(swtKhdrn.getText().toString());
        swtKhdrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swtKhdrn.isChecked()) {
                    String statusSwitch1 = swtKhdrn.getTextOn().toString();
                    status.setText(statusSwitch1);
                } else {
                    String statusSwitch1 = swtKhdrn.getTextOff().toString();
                    status.setText(statusSwitch1);
                }
            }
        });
    }
}

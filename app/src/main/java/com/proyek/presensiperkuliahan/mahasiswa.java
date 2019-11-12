package com.proyek.presensiperkuliahan;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class mahasiswa {
    public String Nama;
    public String MhswID;

    public mahasiswa() {
    }

    public mahasiswa(String Nama, String MhswID) {
        this.Nama = Nama;
        this.MhswID = MhswID;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Nama", Nama);
        result.put("MhswID", MhswID);
        return result;
    }
}

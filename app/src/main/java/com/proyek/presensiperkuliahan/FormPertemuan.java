package com.proyek.presensiperkuliahan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormPertemuan extends AppCompatActivity {
    private TextView npm, nama;
    private FirebaseDatabase getDatabase;
    private DatabaseReference getRefenence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pertemuan);

        npm = findViewById(R.id.tvNPM);
        nama = findViewById(R.id.tvNama);

        getDatabase = FirebaseDatabase.getInstance();
        getRefenence = getDatabase.getReference();

        getRefenence.child("PresensiMhs").addChildEventListener(new ChildEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Mengambil daftar item dari database, setiap kali ada turunannya
                FormPertemuan mahasiswa = dataSnapshot.getValue(FormPertemuan.class);
                npm.setText("NIM : "+ mahasiswa.npm);
                nama.setText("Nama : "+ mahasiswa.nama);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //......
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //......
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //.....
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Digunakan untuk menangani kejadian Error
                Log.e("MyListData", "Error: ", databaseError.toException());
            }
        });
    }
}

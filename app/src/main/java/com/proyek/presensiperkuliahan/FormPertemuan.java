package com.proyek.presensiperkuliahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FormPertemuan extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<mahasiswa, MahasiswaViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pertemuan);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_mahasiswa);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<mahasiswa>()
                .setQuery(query, mahasiswa.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<mahasiswa, MahasiswaViewHolder>(options){
            @Override
            public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MahasiswaViewHolder(inflater.inflate(R.layout.data_mahasiswa, parent, false));
            }
            @Override
            protected void onBindViewHolder(MahasiswaViewHolder holder, int position, final mahasiswa model) {
                holder.bindToMahasiswa(model);
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("PresensiMhs");
        return query;
    }

    public void submitPrtmn (View view){
        EditText etPertmuan = (EditText) findViewById(R.id.etPertemuan);
        EditText etMatkul = (EditText) findViewById(R.id.etMatakuliah);
        EditText etMateri = (EditText) findViewById(R.id.etMateri);
        TextView statKhdrn = (TextView) findViewById(R.id.tvA);
        TextView tvMhswId1 = (TextView) findViewById(R.id.tvNPM);

        DatabaseReference myRef = mDatabase.child("PresensiMhs").child(tvMhswId1.getText().toString()).child("Matakuliah").child(etMatkul.getText().toString()).child("Pertemuan").child(etPertmuan.getText().toString());

        myRef.child("Materi").setValue(etMateri.getText().toString());
        myRef.child("StatusPresensi").setValue(statKhdrn.getText().toString());

        Intent intent = new Intent(FormPertemuan.this, MainActivity.class);
        Toast.makeText(getApplicationContext(), "Data Berhasil Di-input", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

}

package phongson.com.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import phongson.com.R;
import phongson.com.adapter.BinhLuanAdapter;
import phongson.com.model.BinhLuan;

public class BinhLuanActivity extends AppCompatActivity {

    ImageButton btnBinhLuan;
    EditText edtBinhLuan;
    ListView listViewBinhLuan;
    ArrayList<BinhLuan> listBinhLuan;
    BinhLuanAdapter adapter;
    String linkTinTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        Intent intent = getIntent();
        linkTinTuc = intent.getStringExtra("linkTinTuc");
        setDataBinhLuan();
        addConTrols();
        addEvent();
    }

    private void setDataBinhLuan() {
        MainActivity.mDatabase.child("Binh Luan").child(linkTinTuc).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                listBinhLuan.add(binhLuan);
                adapter = new BinhLuanAdapter(BinhLuanActivity.this,R.layout.item_binhluan,listBinhLuan);
                listViewBinhLuan.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                listBinhLuan.add(binhLuan);
                adapter = new BinhLuanAdapter(BinhLuanActivity.this,R.layout.item_binhluan,listBinhLuan);
                listViewBinhLuan.setAdapter(adapter);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                listBinhLuan.add(binhLuan);
                adapter = new BinhLuanAdapter(BinhLuanActivity.this,R.layout.item_binhluan,listBinhLuan);
                listViewBinhLuan.setAdapter(adapter);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addEvent() {
        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BinhLuan binhLuan = new BinhLuan("123",TinActivity.ID_USER,linkTinTuc,edtBinhLuan.getText().toString());
                MainActivity.mDatabase.child("Binh Luan").child(linkTinTuc).setValue(binhLuan);
            }
        });
    }

    private void addConTrols() {
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        edtBinhLuan = findViewById(R.id.edtBinhLuan);
        listViewBinhLuan = findViewById(R.id.listviewBinhLuan);
    }
}

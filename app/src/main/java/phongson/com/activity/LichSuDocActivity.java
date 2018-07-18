package phongson.com.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import phongson.com.R;
import phongson.com.adapter.LichSuDocAdapter;
import phongson.com.model.LichSuDoc;

public class LichSuDocActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<LichSuDoc> list;
    LichSuDocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_doc);
        listView = findViewById(R.id.listview);
        setkey();
        list = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeData(list.get(position).getIdUser());
            }
        });

        MainActivity.mDatabase.child("TinDaLuu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LichSuDoc lichSuDoc = dataSnapshot.getValue(LichSuDoc.class);
                list.add(lichSuDoc);
                adapter = new LichSuDocAdapter(LichSuDocActivity.this, R.layout.item_lichsudoc, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                LichSuDoc lichSuDoc = dataSnapshot.getValue(LichSuDoc.class);
                list.add(lichSuDoc);
                adapter = new LichSuDocAdapter(LichSuDocActivity.this, R.layout.item_lichsudoc, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setkey() {
        MainActivity.mDatabase.child("TinDaLuu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LichSuDoc lichSuDoc = dataSnapshot.getValue(LichSuDoc.class);
                lichSuDoc.setIdUser(dataSnapshot.getKey());
                list.add(lichSuDoc);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void removeData(String key) {
        MainActivity.mDatabase.child("TinDaLuu").child(key).removeValue();
    }
}

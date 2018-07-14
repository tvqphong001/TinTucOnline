package phongson.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import phongson.com.R;
import phongson.com.adapter.ChonChuyenMucAdapter;
import phongson.com.model.ChuyenMuc;

public class ChonChuyenMucActivity extends AppCompatActivity {

    ArrayList<ChuyenMuc> listChuyenMuc;
    public static ArrayList<ChuyenMuc> listChonChuyenMuc;
    Button btnThayDoi;
    public static ArrayList<Integer> ViTri = new ArrayList<>();
    String p1 = "Trang Chủ";
    String p2 = "Thời Sự";
    String p3 = "Giải Trí";
    String p4 = "Giáo Dục";
    String p5 = "Thể Thao";
    String p6 = "Sức Khỏe";
    String p7 = "Công Nghệ";
    String p8 = "Video";
    ArrayList<String> tab = new ArrayList<String>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));
    ChuyenMuc chuyenMuc = new ChuyenMuc("1","Trang Chủ",1);
    ChuyenMuc chuyenMuc1 = new ChuyenMuc("2","Thời Sự",1);
    ChuyenMuc chuyenMuc2 = new ChuyenMuc("3","Giải Trí",1);
    ChuyenMuc chuyenMuc3 = new ChuyenMuc("4","Giáo Dục",1);
    ChuyenMuc chuyenMuc4 = new ChuyenMuc("5","Thể Thao",1);
    ChuyenMuc chuyenMuc5 = new ChuyenMuc("6","Sức Khỏe",1);
    ChuyenMuc chuyenMuc6 = new ChuyenMuc("7","Công Nghệ",1);
    ChuyenMuc chuyenMuc7 = new ChuyenMuc("8","Video",1);
    ListView listView;
    ChonChuyenMucAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chuyen_muc);
        addControl();
        addEvents();

    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(kiemtrachon(position)==1)
                {
                    view.setBackgroundColor(Color.WHITE);
                    xoaChiTiet(listChuyenMuc.get(position));
                }
                else {
                    view.setBackgroundColor(Color.GRAY);
                    themVT(position);
                    themCTMH(listChuyenMuc.get(position));
                }
            }
        });
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChonChuyenMucActivity.this,MainActivity.class);
                intent.putExtra("ThayDoi",1);
                startActivity(intent);
            }
        });
    }
    private void themCTMH(ChuyenMuc chuyenMuc) {
        listChonChuyenMuc.add(chuyenMuc);
    }
    private void themVT(int position) {
        ViTri.add(position);
    }
    private void addControl() {
        ViTri.clear();
        listChuyenMuc = new ArrayList<>();
        listChonChuyenMuc = new ArrayList<>();
        listChonChuyenMuc.add(chuyenMuc);
        listChuyenMuc.addAll(Arrays.asList(chuyenMuc1,chuyenMuc2,chuyenMuc3,chuyenMuc4,chuyenMuc5,chuyenMuc6,chuyenMuc7));
        listView = findViewById(R.id.listview);
        btnThayDoi = findViewById(R.id.btnThayDoi);
        adapter = new ChonChuyenMucAdapter(this,R.layout.item_chuyenmuc,listChuyenMuc);
        listView.setAdapter(adapter);
    }
    private void xoaChiTiet(ChuyenMuc chuyenMuc) {
        String machuyenmuc = chuyenMuc.getIdUser();
        for (int i = listChonChuyenMuc.size()-1;i>=0;i--)
        {
            if (machuyenmuc.equals(listChonChuyenMuc.get(i).getIdUser()))
            {
                listChonChuyenMuc.remove(i);
                break;
            }
        }
    }
    private int kiemtrachon(int position) {
        for (int i = ViTri.size()-1;i>=0;i--)
        {
            if (ViTri.get(i)==position) {
                ViTri.remove(i);
                return 1;
            }
        }
        return 0;
    }
}

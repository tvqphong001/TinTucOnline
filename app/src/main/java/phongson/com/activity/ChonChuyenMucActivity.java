package phongson.com.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import phongson.com.R;
import phongson.com.adapter.ChonChuyenMucAdapter;
import phongson.com.model.ChuyenMuc;

public class ChonChuyenMucActivity extends AppCompatActivity {

    ArrayList<ChuyenMuc> listChuyenMuc;
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
    ChuyenMuc chuyenMuc = new ChuyenMuc("1","Trang Chu",1);
    ChuyenMuc chuyenMuc1 = new ChuyenMuc("1","Thời Sự",1);
    ChuyenMuc chuyenMuc2 = new ChuyenMuc("1","Giai Tri",1);
    ChuyenMuc chuyenMuc3 = new ChuyenMuc("1","Giao Duc",1);
    ChuyenMuc chuyenMuc4 = new ChuyenMuc("1","The Thao",1);
    ChuyenMuc chuyenMuc5 = new ChuyenMuc("1","Suc Khoe",1);
    ChuyenMuc chuyenMuc6 = new ChuyenMuc("1","Cong Nghe",1);
    ChuyenMuc chuyenMuc7 = new ChuyenMuc("1","Video",1);
    ListView listView;
    ChonChuyenMucAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chuyen_muc);
        addControl();
        // addEvents();

    }

//    private void addEvents() {
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(kiemtrachon(position)==1)
//                {
////                    for (int i = 0; i <lvDSMonHoc.getCount(); i++) {
////
////                        if(position == i ){
////                            lvDSMonHoc.getChildAt(i).setBackgroundColor(Color.WHITE);
////                        }
////
//////                    else{
//////                        lvDSMonHoc.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//////                    }
////                    }
//                    view.setBackgroundColor(Color.WHITE);
//                    xoaChiTiet(listChuyenMuc.get(position));
//                }
//                else {
////                    for (int i = 0; i < lvDSMonHoc.getCount(); i++) {
////                        if (position == i) {
////                            lvDSMonHoc.getChildAt(i).setBackgroundColor(Color.GRAY);
////                            themVT(position);
////                            Toast.makeText(DangKyMHActivity.this, "Chon", Toast.LENGTH_SHORT).show();
////                            themCTMH(TrangChuActivity.listMonHoc.get(position));
////                        }
////
//////                    else{
//////                        lvDSMonHoc.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//////                    }
////                    }
//                    view.setBackgroundColor(Color.GRAY);
//                    themVT(position);
//                    themCTMH(listChuyenMuc.get(position));
//                }
//            }
//        });
//    }
//    private void themCTMH(MonHoc monHoc) {
//        int mamh = monHoc.getMaMH();
//        int mssv = sinhVien.getMaSV();
//        int sotc = monHoc.getTinChi();
//        String tenMH = monHoc.getTenMH();
//        ChiTietDKMH chiTietDKMH = new ChiTietDKMH();
//        chiTietDKMH.setMSSV(mssv);
//        chiTietDKMH.setMSMH(mamh);
//        chiTietDKMH.setTenMH(tenMH);
//        chiTietDKMH.setSoTC(sotc);
//        listChiTietDK.add(chiTietDKMH);
//
//
//    }
    private void themVT(int position) {
        ViTri.add(position);
    }
    private void addControl() {
        listChuyenMuc = new ArrayList<>();
        listChuyenMuc.addAll(Arrays.asList(chuyenMuc,chuyenMuc1,chuyenMuc2,chuyenMuc3,chuyenMuc4,chuyenMuc5,chuyenMuc6,chuyenMuc7));
        listView = findViewById(R.id.listview);
        adapter = new ChonChuyenMucAdapter(this,R.layout.item_chuyenmuc,listChuyenMuc);
        listView.setAdapter(adapter);
    }
//    private void xoaChiTiet(MonHoc monHoc) {
//        int mamh = monHoc.getMaMH();
//        for (int i = listChiTietDK.size()-1;i>=0;i--)
//        {
//            if (listChiTietDK.get(i).getMSMH()==mamh)
//            {
//                listChiTietDK.remove(i);
//                break;
//            }
//        }
//    }
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
//    private int kiemTraDK() {
//        int j=0,k=0;
//        if (listChiTietDK.size()==0)
//        {
//            return 0;
//        }
//        for (int s=0;s<listChiTietDK.size();s++)
//        {
//            while (k<listBangDiem.size())
//            {
//                if (listChiTietDK.get(s).getMSMH()==listBangDiem.get(k).getMaMH())
//                {
//                    Toast.makeText(this, "Môn Học: " +listChiTietDK.get(s).getTenMH()+" Đã Học trước đó!" , Toast.LENGTH_SHORT).show();
//                    return 0;
//
//                }
//                k++;
//                if (k==listBangDiem.size())
//                {
//                    k=0;
//                    break;
//                }
//            }
//        }
//        for(int i = (TrangChuActivity.listCTDKMH.size()-1);i>=0;i--)
//        {
//
//            while (j<listChiTietDK.size())
//            {
//                if (TrangChuActivity.listCTDKMH.get(i).getMSMH()==listChiTietDK.get(j).getMSMH())
//                {
//                    Toast.makeText(this, "Môn Học: " +TrangChuActivity.listCTDKMH.get(i).getTenMH()+" Đã Tồn Tại!" , Toast.LENGTH_SHORT).show();
//                    return 0;
//
//                }
//                j++;
//                if (j==listChiTietDK.size())
//                {
//                    j=0;
//                    break;
//                }
//            }
//        }
//        return 1;
//    }
}

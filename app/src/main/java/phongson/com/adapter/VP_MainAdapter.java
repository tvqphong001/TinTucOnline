package phongson.com.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import phongson.com.activity.MainActivity;
import phongson.com.fragment.fm_CongNghe;
import phongson.com.fragment.fm_GiaiTri;
import phongson.com.fragment.fm_GiaoDuc;
import phongson.com.fragment.fm_SucKhoe;
import phongson.com.fragment.fm_TheThao;
import phongson.com.fragment.fm_ThoiSu;
import phongson.com.fragment.fm_TrangChu;
import phongson.com.fragment.fm_Video;

public class VP_MainAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Trang Chu", "Dien Dan", "Tin Hot"};
    String p1 = "Trang Chủ";
    String p2 = "Thời Sự";
    String p3 = "Giải Trí";
    String p4 = "Giáo Dục";
    String p5 = "Thể Thao";
    String p6 = "Sức Khỏe";
    String p7 = "Công Nghệ";
    String p8 = "Video";
    private ArrayList<String> tab = new ArrayList<String>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));
    public VP_MainAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                    return new fm_TrangChu();
            case 1:
                return khoitaoFragment(position);

            case 2:
                return khoitaoFragment(position);

            case 3:
                return khoitaoFragment(position);

            case 4:
                return khoitaoFragment(position);

            case 5:
                return khoitaoFragment(position);

            case 6:
                return khoitaoFragment(position);

            case 7:
                return khoitaoFragment(position);


            default:
                return null;

        }
    }

    private Fragment khoitaoFragment(int position)
    {
        if ("Thời Sự".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_ThoiSu();
        if ("Giải Trí".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_GiaiTri();
        if ("Giáo Dục".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_GiaoDuc();
        if ("Sức Khỏe".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_SucKhoe();
        if ("Công Nghệ".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_CongNghe();
        if ("Video".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_Video();
        if ("Thể Thao".equals(MainActivity.listChuyenMuc.get(position).getTenchuyenmuc()))
            return new fm_TheThao();
        return null;
    }
    @Override
    public int getCount() {
        return MainActivity.listChuyenMuc.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return MainActivity.listChuyenMuc.get(position).getTenchuyenmuc() ;
    }
}

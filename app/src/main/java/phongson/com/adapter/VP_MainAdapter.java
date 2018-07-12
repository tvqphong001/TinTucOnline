package phongson.com.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

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
                return new fm_ThoiSu();
            case 2:
                return new fm_GiaiTri();
            case 3:
                return new fm_GiaoDuc();
            case 4:
                return new fm_TheThao();
            case 5:
                return new fm_SucKhoe();
            case 6:
                return new fm_CongNghe();
            case 7:
                return new fm_Video();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tab.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab.get(position) ;
    }
}

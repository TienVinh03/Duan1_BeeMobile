package com.example.du_an1_qldt.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.du_an1_qldt.Frag_QuanLiDonHang;
import com.example.du_an1_qldt.tabLayout_order1;
import com.example.du_an1_qldt.tabLayout_order2;
import com.example.du_an1_qldt.tabLayout_order3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new tabLayout_order1();
            case 1:
                return  new tabLayout_order2();
            case 2:
                return  new tabLayout_order3();
            default:
                return new Frag_QuanLiDonHang();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case  0:
                title="Chờ xác nhận";
                break;
            case  1:
                title="Đã xác nhận";
                break;
            case  2:
                title="Đã hủy";
                break;
        }
        return title;
    }
}

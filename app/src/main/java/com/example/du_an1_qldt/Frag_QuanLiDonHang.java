package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.CartAdapter;
import com.example.du_an1_qldt.Adapter.OrderAdapter;
import com.example.du_an1_qldt.Adapter.ViewPagerAdapter;
import com.example.du_an1_qldt.DAO.CartDao;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.model.Cart;
import com.example.du_an1_qldt.model.Order;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Frag_QuanLiDonHang extends Fragment {

OrderDAO orderDAO;
ArrayList<Order> orderArrayList;
RecyclerView recyclerView;
OrderAdapter  orderAdapter;
LinearLayoutManager linearLayoutManager;
private TabLayout tabLayout;
private ViewPager viewPager;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_quan_li_hoa_don,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView=view.findViewById(R.id.rcvOrder);
        tabLayout=view.findViewById(R.id.tabLayout);
        viewPager=view.findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        swipeRefreshLayout = view.findViewById(R.id.load1);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
}
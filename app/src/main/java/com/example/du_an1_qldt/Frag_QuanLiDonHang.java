package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.CartAdapter;
import com.example.du_an1_qldt.Adapter.OrderAdapter;
import com.example.du_an1_qldt.DAO.CartDao;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.model.Cart;
import com.example.du_an1_qldt.model.Order;

import java.util.ArrayList;

public class Frag_QuanLiDonHang extends Fragment {

OrderDAO orderDAO;
ArrayList<Order> orderArrayList;
RecyclerView recyclerView;
OrderAdapter  orderAdapter;
LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_quan_li_hoa_don,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rcvOrder);
        orderDAO= new OrderDAO(getContext());
        orderArrayList=orderDAO.getlistOrder();
        orderAdapter= new OrderAdapter(getContext(),orderArrayList);
        recyclerView.setAdapter(orderAdapter);
        linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.d("li", orderArrayList.size()+"");
        orderAdapter.notifyDataSetChanged();
    }
}
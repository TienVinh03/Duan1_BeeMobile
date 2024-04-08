package com.example.du_an1_qldt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.Adapter.PurchasedOrderAdapter;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.model.Order;

import java.util.ArrayList;

public class Frag_DonMua extends Fragment {
    RecyclerView recyclerView;
    PurchasedOrderAdapter purchasedOrderAdapter;
    ArrayList<Order> orders;
    OrderDAO orderDAO;
    LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_don_mua,container,false);
        recyclerView= v.findViewById(R.id.rcvHistoryOrder);
        orderDAO= new OrderDAO(getContext());
        orders=orderDAO.getConfirmedOrders();
        purchasedOrderAdapter= new PurchasedOrderAdapter(getContext(),orders);
        recyclerView.setAdapter(purchasedOrderAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        Log.d("cj", orders.size()+"");
        recyclerView.setLayoutManager(linearLayoutManager);
        purchasedOrderAdapter.notifyDataSetChanged();;
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

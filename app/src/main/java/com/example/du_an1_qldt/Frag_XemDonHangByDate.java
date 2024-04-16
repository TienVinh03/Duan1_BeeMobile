package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.OrderAdapter;
import com.example.du_an1_qldt.Adapter.PurchasedOrderAdapter;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.model.Order;

import java.util.ArrayList;

public class Frag_XemDonHangByDate extends Fragment {
    RecyclerView recyclerView;
    PurchasedOrderAdapter purchasedOrderAdapter;
    ArrayList<Order> orders;
    OrderAdapter orderAdapter;
    OrderDAO orderDAO;
    String start;
    String end;
    LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_xem_don_hang_by_date,container,false);

        Bundle bundle = getArguments();
        if (bundle!=null){
           start = bundle.getString("startDate");
           end = bundle.getString("endDate");
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rcvHistoryOrder1);
        orderDAO= new OrderDAO(getContext());

        orders=orderDAO.getOrdersWithStatusAndDateBetween(start,end);

        purchasedOrderAdapter= new PurchasedOrderAdapter(getContext(),orders);
        recyclerView.setAdapter(purchasedOrderAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        Log.d("cj", orders.size()+"");
        recyclerView.setLayoutManager(linearLayoutManager);
        purchasedOrderAdapter.notifyDataSetChanged();;

//        recyclerView= v.findViewById(R.id.rcvHistoryOrder);
//        orderDAO= new OrderDAO(getContext());
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
//        String id = sharedPreferences.getString("manguoidung", "");
//        orders=orderDAO.getConfirmedOrders(Integer.parseInt(id));
//        purchasedOrderAdapter= new PurchasedOrderAdapter(getContext(),orders);
//        recyclerView.setAdapter(purchasedOrderAdapter);
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        Log.d("cj", orders.size()+"");
//        recyclerView.setLayoutManager(linearLayoutManager);
//        purchasedOrderAdapter.notifyDataSetChanged();;


    }



    }
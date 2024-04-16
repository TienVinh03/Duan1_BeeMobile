package com.example.du_an1_qldt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.Adapter.DetailOrderAdapter;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;

import java.util.ArrayList;

public class OrderDetailView extends AppCompatActivity {
    RecyclerView recyclerView;
    DetailOrderAdapter detailOrderAdapter;
    ArrayList<OrderDetail> list;
    OrderDAO orderDAO;
    Toolbar toolbar;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_view);
        recyclerView = findViewById(R.id.rcvOrderDetail);
        orderDAO = new OrderDAO(this);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("idOrder");
        list = orderDAO.getlistOrderDetail(id);
        detailOrderAdapter = new DetailOrderAdapter(this, list);
        recyclerView.setAdapter(detailOrderAdapter);
        Log.d("fuck", list.size() + "");
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        detailOrderAdapter.notifyDataSetChanged();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailView.this,FragMentContainer.class));
            }
        });

    }
}
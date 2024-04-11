package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.CustomerAdapter;
import com.example.du_an1_qldt.DAO.CustomerDao;
import com.example.du_an1_qldt.model.Customer;

import java.util.ArrayList;

public class Frag_QuanLiKhachHang extends Fragment {
RecyclerView recyclerView;
ArrayList<Customer> customers;
CustomerDao customerDao;
CustomerAdapter customerAdapter;
LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_quan_li_khach_hang,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleViewkh);
        customerDao= new CustomerDao(getContext());
        customers=customerDao.getListCustomer();
        Log.d("checkCustomer", customers.size()+"");
        customerAdapter= new CustomerAdapter(getContext(),customers);
        recyclerView.setAdapter(customerAdapter);
        linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        customerAdapter.notifyDataSetChanged();
    }
}
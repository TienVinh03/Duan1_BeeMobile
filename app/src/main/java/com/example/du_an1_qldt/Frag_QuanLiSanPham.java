package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.SanPhamAdapter;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class Frag_QuanLiSanPham extends Fragment {
    RecyclerView rc_QLSP;

    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    ArrayList<phone> listSP;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_quan_li_san_pham,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_QLSP = view.findViewById(R.id.rc_QLSP);
        sanPhamDAO = new SanPhamDAO(getActivity());
        listSP = sanPhamDAO.getlistSP();
        sanPhamAdapter = new SanPhamAdapter(getActivity(),listSP);
        rc_QLSP.setAdapter(sanPhamAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_QLSP.setLayoutManager(linearLayoutManager);

    }
}
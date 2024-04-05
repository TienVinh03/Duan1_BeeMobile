package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.DSSP_Adapter;
import com.example.du_an1_qldt.Adapter.DSVoucher_Adapter;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class Frag_DanhSachSP extends Fragment {
    RecyclerView rc_dsSP;

    SanPhamDAO sanPhamDAO;
    DSSP_Adapter dsspAdapter;
    ArrayList<phone> listSP;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_danh_sach_sp,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_dsSP = view.findViewById(R.id.recycleViewSanPham);
        sanPhamDAO = new SanPhamDAO(getActivity());
        listSP = sanPhamDAO.getlistSP();
        dsspAdapter = new DSSP_Adapter(getActivity(),listSP);
        rc_dsSP.setAdapter(dsspAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_dsSP.setLayoutManager(linearLayoutManager);

    }
}
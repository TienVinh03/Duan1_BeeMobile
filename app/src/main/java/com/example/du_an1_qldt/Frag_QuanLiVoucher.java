package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.du_an1_qldt.Adapter.SanPhamAdapter;
import com.example.du_an1_qldt.Adapter.VoucherAdapter;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class Frag_QuanLiVoucher extends Fragment {

    RecyclerView rc_QLVoucher;

    VoucherDAO voucherDAO;
    VoucherAdapter voucherAdapter;
    ArrayList<Voucher_DTO> listVoucher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_quan_li_voucher,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_QLVoucher = view.findViewById(R.id.rc_QL_Voucher);
        voucherDAO = new VoucherDAO(getActivity());
        listVoucher = voucherDAO.getListVoucher();
        voucherAdapter = new VoucherAdapter(getActivity(),listVoucher);
        rc_QLVoucher.setAdapter(voucherAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_QLVoucher.setLayoutManager(linearLayoutManager);

    }
}
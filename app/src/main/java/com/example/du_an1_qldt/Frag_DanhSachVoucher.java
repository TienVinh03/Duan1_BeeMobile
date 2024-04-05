package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.DSVoucher_Adapter;
import com.example.du_an1_qldt.Adapter.VoucherAdapter;
import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.model.Voucher_DTO;

import java.util.ArrayList;

public class Frag_DanhSachVoucher extends Fragment {

    RecyclerView rc_QLVoucher;

    VoucherDAO voucherDAO;
    VoucherAdapter voucherAdapter;

    DSVoucher_Adapter DSvoucherAdapter;
    ArrayList<Voucher_DTO> listVoucher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_frag_danh_sach_voucher,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_QLVoucher = view.findViewById(R.id.rc_DS_Voucher);
        voucherDAO = new VoucherDAO(getActivity());
        listVoucher = voucherDAO.getListVoucher();
        DSvoucherAdapter = new DSVoucher_Adapter(getActivity(),listVoucher);
        rc_QLVoucher.setAdapter(DSvoucherAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_QLVoucher.setLayoutManager(linearLayoutManager);
//        SearchView srview = view.findViewById(R.id.searchvoucherr);
//        // Trong phương thức onViewCreated của bạn
//        srview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ArrayList<Voucher_DTO> newlist = new ArrayList<>();
//                for (Voucher_DTO s : listVoucher){
//                    if (s.getTenVoucher().toLowerCase().contains(newText.toLowerCase()) || String.valueOf(s.getSoLuong()).toLowerCase().contains(newText.toLowerCase())) {
//                        newlist.add(s);
//                    }
//                }
//                DSvoucherAdapter.updateData(newlist);
//                DSvoucherAdapter.notifyDataSetChanged();
//                return false;
//            }
//        });

// Sửa đổi phương thức updateData trong DSvoucherAdapter
//        public void updateData(ArrayList<Voucher_DTO> newList) {
//            listVoucher.clear();
//            listVoucher.addAll(newList);
//            // Không cần khởi tạo adapter mới
//        }

//        srview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//
//
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ArrayList<Voucher_DTO> newlist = new ArrayList<>();
//                for (Voucher_DTO s : listVoucher){
//                    if (s.getTenVoucher().toLowerCase().contains(newText.toLowerCase())){
//                        newlist.add(s);
//                    } else if (String.valueOf(s.getSoLuong()).toLowerCase().contains(newText.toLowerCase())) {
//
//                    }
//                }
//                loadriengchotimkiem(newlist);
//                return false;
//            }
//        });
    }


    private void loadriengchotimkiem(ArrayList<Voucher_DTO> list){
        // Cập nhật dữ liệu cho adapter hiện tại
        voucherAdapter = new VoucherAdapter(getContext(), list);
        rc_QLVoucher.setAdapter(voucherAdapter);
        DSvoucherAdapter.updateData(list);
        // Thông báo cho adapter biết dữ liệu đã thay đổi
        DSvoucherAdapter.notifyDataSetChanged();
    }

}
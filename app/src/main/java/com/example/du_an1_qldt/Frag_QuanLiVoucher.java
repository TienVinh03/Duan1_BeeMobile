package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

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
    private SwipeRefreshLayout swipeRefreshLayout;
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
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_qlvoucher);
        voucherAdapter = new VoucherAdapter(getActivity(),listVoucher);
        rc_QLVoucher.setAdapter(voucherAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_QLVoucher.setLayoutManager(linearLayoutManager);

        Button btn_addVoucher = view.findViewById(R.id.btn_addVoucher);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rc_QLVoucher = view.findViewById(R.id.rc_QL_Voucher);

                voucherDAO = new VoucherDAO(getActivity());
                listVoucher = voucherDAO.getListVoucher();
                swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_qlvoucher);
                voucherAdapter = new VoucherAdapter(getActivity(),listVoucher);
                rc_QLVoucher.setAdapter(voucherAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                rc_QLVoucher.setLayoutManager(linearLayoutManager);

                swipeRefreshLayout.setRefreshing(false);
            }
        });
        btn_addVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = ((Activity)getActivity()).getLayoutInflater();
                View v1=inflater.inflate(R.layout.dialog_add_voucher,null);
                EditText tenVoucher = v1.findViewById(R.id.edt_tenVoucher_add);
                EditText soLuong = v1.findViewById(R.id.edt_soLuongVoucher_add);
                EditText menhgia = v1.findViewById(R.id.edt_menhgia_add);
                Button sua = v1.findViewById(R.id.btn_addVoucher_add);
                Voucher_DTO voucherDto = new Voucher_DTO();




                listVoucher =voucherDAO.getListVoucher();
                voucherAdapter= new VoucherAdapter(getActivity(),listVoucher);



                builder.setTitle("                              Thêm Voucher");
                builder.setView(v1);
                Dialog dialog = builder.create();

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            int menhgia1 = Integer.parseInt(menhgia.getText().toString());
                            int soluong1 = Integer.parseInt(soLuong.getText().toString());

                            if (TextUtils.isEmpty(tenVoucher.getText().toString())||TextUtils.isEmpty(soLuong.getText().toString())||TextUtils.isEmpty(menhgia.getText().toString())){
                                Toast.makeText(getActivity(), "Vui lòng Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            }else if (menhgia1<=0) {
                                Toast.makeText(getActivity(), "Gía trị không phù hợp", Toast.LENGTH_SHORT).show();

                            } else if (menhgia1>100) {
                                Toast.makeText(getActivity(), "Gía trị không phù hợp", Toast.LENGTH_SHORT).show();
                            } else if (soluong1<0) {
                                Toast.makeText(getActivity(), "Số lượng không phù hợp", Toast.LENGTH_SHORT).show();

                            }else {
                                String tenVoucher2 = tenVoucher.getText().toString();
                                int soLuong2= Integer.parseInt(soLuong.getText().toString());
                                int menhgia2= Integer.parseInt(menhgia.getText().toString());

                                voucherDto.setTenVoucher(tenVoucher2);
                                voucherDto.setSoLuong(soLuong2);
                                voucherDto.setGiaTriGiam(menhgia2);
                                if (soluong1>0){
                                    voucherDto.setTrangThai(1);
                                }else {
                                    voucherDto.setTrangThai(0);
                                }
                                int check = voucherDAO.add_Voucher(voucherDto);
                                if (check>0){
                                    listVoucher.clear();
                                    listVoucher.addAll(voucherDAO.getListVoucher());
                                    voucherAdapter.notifyDataSetChanged();

                                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                            }
                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Vui lòng Nhập đúng dịnh dạng     ", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
                dialog.show();
            }
        });

    }
}
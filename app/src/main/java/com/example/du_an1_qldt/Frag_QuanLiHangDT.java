package com.example.du_an1_qldt;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an1_qldt.Adapter.HangDienThoaiAdapter;
import com.example.du_an1_qldt.DAO.HangDienThoaiDAO;
import com.example.du_an1_qldt.model.HangDienThoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag_QuanLiHangDT extends Fragment {

    private RecyclerView recyclerViewLoaiSanPham;
    private FloatingActionButton btnThemLoaiSanPham;
    HangDienThoaiDAO hangDienThoaiDAO;
    ArrayList<HangDienThoai> list = new ArrayList<>();

    public Frag_QuanLiHangDT() {

    }

    public static Frag_QuanLiHangDT newInstance() {
        Frag_QuanLiHangDT fragment = new Frag_QuanLiHangDT();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_quan_li_hang_dt, container, false);
        recyclerViewLoaiSanPham = view.findViewById(R.id.recycleViewLoaiSanPham);
        btnThemLoaiSanPham = view.findViewById(R.id.btnThemLoaiSanPham);
        reload();
        btnThemLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            EditText edtThemLoaiSanPham,edtThemLoaiSanPhamt;
            Button btnDialogThemLoaiSachSanPham, btnDialogHuyThemLoaiSanPham;

            @Override
            public void onClick(View view1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                view1 = inflater1.inflate(R.layout.item_themloaisanpham, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                edtThemLoaiSanPham = view1.findViewById(R.id.edtThemLoaiSanPham);
                edtThemLoaiSanPhamt = view1.findViewById(R.id.edtThemLoaiSanPhamt);
                btnDialogThemLoaiSachSanPham = view1.findViewById(R.id.btnDialogThemLoaiSachSanPham);
                btnDialogHuyThemLoaiSanPham = view1.findViewById(R.id.btnDialogHuyThemLoaiSanPham);
                btnDialogHuyThemLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogThemLoaiSachSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HangDienThoai hangDienThoai = new HangDienThoai();
                        hangDienThoai.setTenHang(edtThemLoaiSanPham.getText().toString());
                        hangDienThoai.setTenHang(edtThemLoaiSanPhamt.getText().toString());


                        if (edtThemLoaiSanPham.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        } else {
                            if (hangDienThoaiDAO.insert(hangDienThoai) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                            reload();
                        }
                    }
                });
                dialog.show();
            }
        });


        return view;
    }


    public void reload() {
        hangDienThoaiDAO = new HangDienThoaiDAO(getContext());
        list = (ArrayList<HangDienThoai>) hangDienThoaiDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewLoaiSanPham.setLayoutManager(linearLayoutManager);
        HangDienThoaiAdapter hangDienThoaiAdapter = new HangDienThoaiAdapter(list, getContext(), hangDienThoaiDAO);
        recyclerViewLoaiSanPham.setAdapter(hangDienThoaiAdapter);
    }
}
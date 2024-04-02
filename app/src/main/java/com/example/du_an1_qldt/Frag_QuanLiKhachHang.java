package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an1_qldt.Adapter.Adapter_QuanLyKhachHang;
import com.example.du_an1_qldt.DAO.KhachHangDAO;
import com.example.du_an1_qldt.model.KhachHang_DTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag_QuanLiKhachHang extends Fragment {

    KhachHangDAO khachHangDAO;
    RecyclerView recyclerView;

    public Frag_QuanLiKhachHang() {

    }

    public static Frag_QuanLiKhachHang newInstance() {
        Frag_QuanLiKhachHang fragment = new Frag_QuanLiKhachHang();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_quan_li_khach_hang, container, false);
        recyclerView = view.findViewById(R.id.recycleKhachHang);
//        FloatingActionButton floatingActionButton = view.findViewById(R.id.floadAddKhachHang);
        khachHangDAO = new KhachHangDAO(getContext());
        loadData();
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialog();
//            }
//        });
        return view;
    }

    public void loadData() {
        ArrayList<KhachHang_DTO> list = khachHangDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapter_QuanLyKhachHang adapter = new Adapter_QuanLyKhachHang(getContext(), list, khachHangDAO);
        recyclerView.setAdapter(adapter);
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themkhachhang, null);
        builder.setView(view);

        EditText edHoTen = view.findViewById(R.id.edHoTen);
        EditText edUsername = view.findViewById(R.id.edUsername);
        EditText edPassword = view.findViewById(R.id.edPassword);
        EditText edSodienthoai = view.findViewById(R.id.edSodienthoai);
        EditText edEmail = view.findViewById(R.id.edEmail);
        EditText edDiachi = view.findViewById(R.id.edDiachi);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edHoTen.getText().toString();
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String sodienthoai = edSodienthoai.getText().toString();
                String email = edEmail.getText().toString();
                String diachi = edDiachi.getText().toString();
                String loaitaikhoan = "nguoidung";
                boolean check = khachHangDAO.themkhachhang(hoten,email,password);
                if (check) {
                    loadData();
                    Toast.makeText(getContext(), "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
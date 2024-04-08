package com.example.du_an1_qldt;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.du_an1_qldt.DAO.KhachHangDAO;
import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.KhachHang_DTO;

import java.util.ArrayList;

public class Frag_Info extends Fragment {

    TextView tv_hotenkhachhang, tv_emailKhachhangg, tv_sđtkhachhang, tv_diachikhachhang;
    Button btnSuaTTkhachhang;
    ArrayList<KhachHang_DTO> list;
    KhachHangDAO dao;

    @SuppressLint("Range")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_info, container, false);

        tv_hotenkhachhang = v.findViewById(R.id.tv_hotenkhachhang);
        tv_emailKhachhangg = v.findViewById(R.id.tv_emailKhachhangg);
        tv_sđtkhachhang = v.findViewById(R.id.tv_sđtkhachhang);
        tv_diachikhachhang = v.findViewById(R.id.tv_diachikhachhang);
        btnSuaTTkhachhang = v.findViewById(R.id.btnSuaTTkhachhang);

        dao = new KhachHangDAO(getActivity());
        list = new ArrayList<>();


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String hoTen = sharedPreferences.getString("hoTen", "");
        String email = sharedPreferences.getString("email", "");
        String sodienthoai = sharedPreferences.getString("sodienthoai", "");
        String diachi = sharedPreferences.getString("diachi", "");


        tv_hotenkhachhang.setText(hoTen);
        tv_emailKhachhangg.setText(email);
        tv_diachikhachhang.setText(diachi);
        tv_sđtkhachhang.setText(sodienthoai);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSuaTTkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View v1 = inflater.inflate(R.layout.diglog_updatethongtin, null);
                builder.setView(v1);
                Dialog dialog = builder.create();

                EditText tv_suahotenkhachhang = v1.findViewById(R.id.tv_suahotenkhachhang);
                EditText tv_suaemailKhachhang = v1.findViewById(R.id.tv_suaemailKhachhang);
                EditText tv_suasđtkhachhang = v1.findViewById(R.id.tv_suasđtkhachhang);
                EditText tv_suadiachikhachhang = v1.findViewById(R.id.tv_suadiachikhachhang);
                Button btn_suathongtin = v1.findViewById(R.id.btn_suathongtin);
                Button btn_huythongtin = v1.findViewById(R.id.btn_huythongtin);

                // Hiển thị thông tin từ SharedPreferences
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("hoTen", "");
                String email = sharedPreferences.getString("email", "");
                String sodienthoai = sharedPreferences.getString("sodienthoai", "");
                String diachi = sharedPreferences.getString("diachi", "");

                tv_suahotenkhachhang.setText(name);
                tv_suaemailKhachhang.setText(email);
                tv_suasđtkhachhang.setText(sodienthoai);
                tv_suadiachikhachhang.setText(diachi);

                btn_suathongtin.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Xử lý khi người dùng nhấn nút cập nhật
                        String hoTen = tv_suahotenkhachhang.getText().toString().trim();
                        String email = tv_suaemailKhachhang.getText().toString().trim();
                        String sdt = tv_suasđtkhachhang.getText().toString().trim();
                        String diaChi = tv_suadiachikhachhang.getText().toString().trim();

                        // Tiến hành cập nhật dữ liệu nếu hợp lệ và đóng Dialog
                        if (TextUtils.isEmpty(hoTen) || TextUtils.isEmpty(email) || TextUtils.isEmpty(sdt) || TextUtils.isEmpty(diaChi)) {
                            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else if (!checkemail(email)) {
                            Toast.makeText(getActivity(), "Vui lòng nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
                        } else if (!isValidPhoneNumber(sdt)) {
                            Toast.makeText(getActivity(), "Vui lòng nhập đúng định dạng số điện thoại", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean success = dao.updateTT(0, hoTen, sdt, email, diaChi);
                            if (success) {
                                Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                // Cập nhật lại dữ liệu trên Fragment và đóng Dialog
                                tv_hotenkhachhang.setText(hoTen);
                                tv_emailKhachhangg.setText(email);
                                tv_sđtkhachhang.setText(sdt);
                                tv_diachikhachhang.setText(diaChi);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();

                btn_huythongtin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})");
    }

    public boolean checkemail(String Emaill) {
        return Patterns.EMAIL_ADDRESS.matcher(Emaill).matches();
    }
}


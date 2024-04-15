package com.example.du_an1_qldt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an1_qldt.DAO.KhachHangDAO;

public class DangKy extends AppCompatActivity {

    EditText edTendn, edEmail, edMatkhau, edNhaplaimk,edtSDT;

    Button btnDangky, btnTrolai;

    KhachHangDAO khachHangDAO;

    LinearLayout manhinhdangky;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        edTendn = findViewById(R.id.edTendn);
        edEmail = findViewById(R.id.edEmail);
        edMatkhau = findViewById(R.id.edMatkhau);
        edNhaplaimk = findViewById(R.id.edNhaplaimk);
        btnDangky = findViewById(R.id.btnDangky);
        btnTrolai = findViewById(R.id.btnTrolai);
        edtSDT = findViewById(R.id.edtSdt);
        khachHangDAO = new KhachHangDAO(getApplicationContext());

        btnTrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, MainActivity2.class));
            }
        });

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edTendn.getText().toString();
                String email = edEmail.getText().toString();
                String pass = edMatkhau.getText().toString();
                String repass = edNhaplaimk.getText().toString();
                String sdt = edtSDT.getText().toString();

                String loaitaikhoan = "nguoiDung";

                if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(DangKy.this, "Không được bỏ trống trường nào", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkEmail(email)) {
                    Toast.makeText(DangKy.this, "Nhập sai cấu trúc email", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (khachHangDAO.checkEmail(email) < 0) {
                    Toast.makeText(DangKy.this, "Email đã tồn tại trên hệ thống", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (pass.length() < 8) {
                    Toast.makeText(DangKy.this, "Mật khẩu phải có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (!pass.equals(repass)) {
                    Toast.makeText(DangKy.this, "Nhập lại mật khẩu không khớp", Toast.LENGTH_SHORT).show();

                    return;
                }

                boolean check = khachHangDAO.themkhachhang(name, email, pass,sdt);
                if (check) {
                    Toast.makeText(DangKy.this, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKy.this, DangNhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(DangKy.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void snackBar(int layout, String s) {
        Snackbar snackbar = Snackbar.make(manhinhdangky, "", Snackbar.LENGTH_LONG);
        View custom = getLayoutInflater().inflate(layout, null);
        TextView tvError = custom.findViewById(R.id.tvError);
        tvError.setText(s);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(30, 25, 30, 25);
        snackbarLayout.addView(custom, 0);
        snackbar.show();
    }

    public boolean checkEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

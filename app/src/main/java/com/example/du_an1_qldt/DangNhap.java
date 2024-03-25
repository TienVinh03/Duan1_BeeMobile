package com.example.du_an1_qldt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an1_qldt.DAO.KhachHangDAO;
import com.example.du_an1_qldt.DAO.TaiKhoanDAO;

public class DangNhap extends AppCompatActivity {

    EditText edTendn, edMatkhau;

    CheckBox chk;
    Button btnDangnhap, btnTrolai;
    KhachHangDAO khachHangDAO;
    com.example.du_an1_qldt.DAO.TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edTendn = findViewById(R.id.edTendn);
        edMatkhau = findViewById(R.id.edMatkhau);

        chk = findViewById(R.id.ckMatkhau);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnTrolai = findViewById(R.id.btnTrolai);

        khachHangDAO = new KhachHangDAO(this);
        taiKhoanDAO= new TaiKhoanDAO(this);


//        txtQuenmk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DangNhap.this, QuenMatKhau.class));
//            }
//        });


        SharedPreferences pref = getSharedPreferences("User_File", MODE_PRIVATE);
        edTendn.setText(pref.getString("username", ""));
        edMatkhau.setText(pref.getString("password", ""));
        chk.setChecked(pref.getBoolean("Remember", false));


        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use = edTendn.getText().toString();
                String pass = edMatkhau.getText().toString();
                boolean check = taiKhoanDAO.checkDangNhap(use, pass);

                if (use.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(DangNhap.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (check) {
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(use, pass, chk.isChecked());
                    startActivity(new Intent(DangNhap.this, FragMentContainer.class));
                } else {
                    edTendn.setError("Tên đăng nhập  không đúng");
                    edMatkhau.setError("Mật khẩu không đúng");
                    Toast.makeText(DangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnTrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, MainActivity2.class));
            }
        });


    }

    private void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("User_File", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xóa trắng dữ liệu trước đó
            edit.clear();
        } else {
            //lưu dữ liệu
            edit.putString("username", u);
            edit.putString("password", p);
            edit.putBoolean("Remember", status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }
}
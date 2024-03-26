package com.example.du_an1_qldt;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an1_qldt.DAO.TaiKhoanDAO;
import com.example.du_an1_qldt.model.KhachHang_DTO;

public class DoiMatKhau extends AppCompatActivity {

    EditText edMatKhauCu, edMatkhau, edNhaplaimk;
    Button btnXacnhan, btnTrolai;
    TaiKhoanDAO tkDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        // Tạo tham chiếu đến các thành phần giao diện
        edMatKhauCu = findViewById(R.id.edMatKhauCu);
        edMatkhau = findViewById(R.id.edMatkhau);
        edNhaplaimk = findViewById(R.id.edNhaplaimk);
        btnXacnhan = findViewById(R.id.btnXacnhann);
        btnTrolai = findViewById(R.id.btnTrolaii);

        // Khởi tạo TaiKhoanDAO
        tkDAO = new TaiKhoanDAO(this);

        // Thiết lập sự kiện khi click nút "Trở lại"
        btnTrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoiMatKhau.this, FragMentContainer.class));
            }
        });



        // Thiết lập sự kiện khi click nút "Xác nhận"
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matKhauCu = edMatKhauCu.getText().toString();
                String matKhauMoi = edMatkhau.getText().toString();
                String nhapLaiMK = edNhaplaimk.getText().toString();

                if (validate(matKhauCu, matKhauMoi, nhapLaiMK)) {
//                    String user = "username"; // Thay đổi thành phương thức để lấy tên người dùng
                    String user = edMatKhauCu.getText().toString(); // Thay vì sử dụng chuỗi cứng "username"

                    KhachHang_DTO nguoiDung = tkDAO.getID(user);
                    if (nguoiDung != null && nguoiDung.getPassword().equals(matKhauCu)) {
                        nguoiDung.setPassword(matKhauMoi);
                        long result = tkDAO.updatePass(nguoiDung);
                        if (result > 0) {
                            Toast.makeText(DoiMatKhau.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            edMatKhauCu.setText("");
                            edMatkhau.setText("");
                            edNhaplaimk.setText("");
                            startActivity(new Intent(DoiMatKhau.this, DangNhap.class));
                        } else {
                            Toast.makeText(DoiMatKhau.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoiMatKhau.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


//    public int validate(){
//        int check =1;
//        if (edMatKhauCu.getText().length() == 0 || edMatkhau.getText().length() == 0 || edNhaplaimk.getText().length() == 0){
//            Toast.makeText(DoiMatKhau.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//            check = -1;
//        }{
//            SharedPreferences pref = DoiMatKhau.this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
//            String passOld = pref.getString("PASSWORD","");
//            String pass = edMatkhau.getText().toString();
//            String rePass = edNhaplaimk.getText().toString();
//
//            if (!passOld.equals(edMatKhauCu.getText().toString())){
//                Toast.makeText(DoiMatKhau.this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
//                check = -1;
//            }
//            if (!pass.equals(rePass)){
//                Toast.makeText(DoiMatKhau.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
//                check = -1;
//            }
//        }
//
//
//
//
//        return check;
//    }



    // Phương thức kiểm tra tính hợp lệ của mật khẩu mới
    public boolean validate(String matKhauCu, String matKhauMoi, String nhapLaiMK) {
        if (matKhauCu.isEmpty() || matKhauMoi.isEmpty() || nhapLaiMK.isEmpty()) {
            Toast.makeText(DoiMatKhau.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!matKhauMoi.equals(nhapLaiMK)) {
            Toast.makeText(DoiMatKhau.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

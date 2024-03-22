package com.example.du_an1_qldt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an1_qldt.DataBase1.dbHelper;


public class QuenMatKhau extends AppCompatActivity {

    EditText edTendn, edEmail;
    Button btnXacnhan, btnTrolai;


    dbHelper myDbHelper = new dbHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        edTendn = findViewById(R.id.edTendn);
        edEmail = findViewById(R.id.edEmail);
        btnXacnhan = findViewById(R.id.btnXacnhan);
        btnTrolai = findViewById(R.id.btnTrolai);

        btnTrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuenMatKhau.this, DangNhap.class));
            }
        });


        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edTendn.getText().toString();
                String email = edEmail.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email)) {
                    Toast.makeText(QuenMatKhau.this, "Vui lòng nhập đủ thông tin ", Toast.LENGTH_SHORT).show();
                }


            }
        });

//        public String getPasswordByEmailAndUsername(String email, String username) {
//            SQLiteDatabase db = this.getReadableDatabase();
//            String[] columns = { "pass" };
//            String selection = "email=? AND username=?";
//            String[] selectionArgs = { email, username };
//            Cursor cursor = db.query("nguoiDung", columns, selection, selectionArgs, null, null, null);
//            String password = null;
//            if (cursor.moveToFirst()) {
//                password = cursor.getString(cursor.getColumnIndex("pass"));
//            }
//            cursor.close();
//            return password;
//        }



    }
}
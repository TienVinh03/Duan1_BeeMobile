package com.example.du_an1_qldt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_tenlogo;
    Button button_dn,btn_dangki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_tenlogo = findViewById(R.id.tv_tenlogo);
        button_dn = findViewById(R.id.btn_dangnhap);
        btn_dangki= findViewById(R.id.btn_dangki);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_tenlogo.animate().translationY(600).setDuration(1000);
            }
        },500);

        button_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, DangNhap.class));
            }
        });

        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, DangKy.class));
            }
        });

    }
}
package com.example.du_an1_qldt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_tenlogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_tenlogo = findViewById(R.id.tv_tenlogo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_tenlogo.animate().translationY(600).setDuration(3000);
            }
        },500);
    }
}
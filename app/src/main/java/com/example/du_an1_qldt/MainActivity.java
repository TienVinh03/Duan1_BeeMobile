package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imgall;
    ImageView anhlogo;
    ImageView anhlogo1;
    long second =   500;
    TextView tv_tenlogo;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgall = findViewById(R.id.imgall);
        anhlogo= findViewById(R.id.anhlogo);
        anhlogo1 = findViewById(R.id.anhlogo1);
        tv_tenlogo = findViewById(R.id.tv_tenlogo);

       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               imgall.animate().translationY(9000).setDuration(1500);
               anhlogo.animate().translationY(-5000).setDuration(5000);
               anhlogo1.animate().translationY(-5000).setDuration(5000);
               tv_tenlogo.animate().translationY(-5000).setDuration(7000);

               imgall.animate().setListener(new AnimatorListenerAdapter() {
                   @Override
                   public void onAnimationEnd(@NonNull Animator animation) {

                       Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                       startActivity(intent);
                       finish();
                   }
               });


           }
       },500);
    }
}
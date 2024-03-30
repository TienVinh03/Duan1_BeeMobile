package com.example.du_an1_qldt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class TaoDonHang extends AppCompatActivity {
    Toolbar toolbar;
    TextView sl, price, name, color, rom,quantity1,ship;
    int quantityPr = 0;
    int pr;
    double priceShip=20000;
    String pricePr, namePr, romPr, colorPr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_don_hang);
        ImageView btnplus = findViewById(R.id.btnPlus);
        ImageView btnTru = findViewById(R.id.btnApart);
        sl = findViewById(R.id.tvQuantity);
        name = findViewById(R.id.nameProduct);
        color = findViewById(R.id.colorProduct);
        rom = findViewById(R.id.ramProduct);
        price = findViewById(R.id.priceProduct);
        quantity1=findViewById(R.id.priceProduct2);
        ship=findViewById(R.id.priceShip);
        Intent intent = getIntent();
        DecimalFormat formatter = new DecimalFormat("#,###,###");

        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                namePr = bundle.getString("name");
                pricePr = bundle.getString("price");
                colorPr = bundle.getString("color");
                romPr = bundle.getString("ram");
                name.setText(namePr);
                color.setText(colorPr);
                double priceDouble = Double.parseDouble(pricePr);
                String yourFormattedString1 = formatter.format(priceDouble);
                price.setText(yourFormattedString1 + "VND");
                quantity1.setText(yourFormattedString1 +" đ");
                rom.setText("," + romPr + "Gb");
            }
        }

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityPr++;
                pr = quantityPr * Integer.parseInt(pricePr);
                String yourFormattedString = formatter.format(pr);
                price.setText(yourFormattedString + "VND");
                sl.setText(String.valueOf(quantityPr));
                quantity1.setText(yourFormattedString+" đ");
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityPr--;
                pr = quantityPr * Integer.parseInt(pricePr);
                String yourFormattedString = formatter.format(pr);
                price.setText(yourFormattedString + "VND");
                price.setText(yourFormattedString + "VND");
                sl.setText(String.valueOf(quantityPr));
                quantity1.setText(yourFormattedString+" đ");
            }
        });
        double shipdouble = priceShip;
        String yourFormattedString2 = formatter.format(shipdouble);
ship.setText(yourFormattedString2+" đ");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaoDonHang.this, FragMentContainer.class));
            }
        });
    }
}
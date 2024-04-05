package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.du_an1_qldt.Adapter.CartAdapter;
import com.example.du_an1_qldt.DAO.CartDao;
import com.example.du_an1_qldt.model.Cart;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Frag_GioHang extends Fragment implements TotalPriceListener {
    RecyclerView recyclerView;
    ArrayList<Cart> cartArrayList;
    CartDao cartDao;
    CartAdapter cartAdapter;
    Adapter adapter;
    TextView tv_price;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_gio_hang, container, false);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_cart);
        tv_price=view.findViewById(R.id.tv_price);
        cartDao = new CartDao(getContext());
        cartArrayList = cartDao.getlistCart();
        cartAdapter = new CartAdapter(getContext(), cartArrayList);
        recyclerView.setAdapter(cartAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        cartAdapter.setOnTotalPriceChangedListener(this);
        cartAdapter.notifyDataSetChanged();
        calculateTotalPrice();

    }
    @Override
    public void onTotalPriceChanged(double totalPrice) {
        tv_price.setText(String.valueOf(totalPrice));
    }
    private void calculateTotalPrice() {
        double totalPrice = 0;
        for (Cart cartItem : cartArrayList) {
            totalPrice += cartItem.getPrice(); // Giả sử getPrice() là phương thức lấy giá của một sản phẩm trong giỏ hàng
        }
        DecimalFormat decimalFormat= new DecimalFormat("#,###,###");
        String doublePrice=decimalFormat.format(totalPrice);
        tv_price.setText(doublePrice);
    }
}
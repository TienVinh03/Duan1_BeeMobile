package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.Frag_GioHang;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Cart> list;

    public CartAdapter(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_cart, parent, false);
        CartAdapter.ViewHolder holder = new CartAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.tvName.setText(cart.getName());
        holder.tvPrice.setText(String.valueOf(cart.getPrice())); // Chuyển int thành chuỗi
        holder.tvRom.setText(String.valueOf(cart.getRom())); // Chuyển int thành chuỗi
        holder.tvColor.setText(cart.getColor());
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
        holder.tvMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Frag_GioHang.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvColor, tvRom, tvPrice, tvMua, tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameProduct);
            tvColor = itemView.findViewById(R.id.colorProduct);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvRom = itemView.findViewById(R.id.ramProduct);
            tvPrice = itemView.findViewById(R.id.priceProduct);
            tvMua = itemView.findViewById(R.id.btnMuaNgay);
        }
    }
}

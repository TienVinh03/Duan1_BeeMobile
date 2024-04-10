package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Cart;

import java.util.ArrayList;

public class CartAdapter2 extends RecyclerView.Adapter<CartAdapter2.ViewHolder> {
    Context context;
    ArrayList<Cart> list;

    public CartAdapter2(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CartAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_cart2, parent, false);
        CartAdapter2.ViewHolder holder = new CartAdapter2.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter2.ViewHolder holder, int position) {
        Cart cart = list.get(position);
        SanPhamDAO sanPhamDAO= new SanPhamDAO(context);
        holder.tvName.setText(sanPhamDAO.getProductNameById(cart.getIdPhone()));
        holder.tvPrice.setText(cart.getPrice()+"VND"); // Chuyển int thành chuỗi
        holder.tvRom.setText(cart.getRom()+"gb");
        holder.tvColor.setText(cart.getColor());
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
TextView tvName,tvColor,tvQuantity,tvRom,tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameProduct);
            tvColor = itemView.findViewById(R.id.colorProduct);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvRom = itemView.findViewById(R.id.ramProduct);
            tvPrice = itemView.findViewById(R.id.priceProduct);
        }
    }
}

package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.Frag_GioHang;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.TaoDonHang;
import com.example.du_an1_qldt.model.Cart;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Cart> list;
    int sl;
    int price;
int quantity;
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
        SanPhamDAO sanPhamDAO= new SanPhamDAO(context);
        holder.tvName.setText(sanPhamDAO.getProductNameById(cart.getIdPhone()));
        quantity= cart.getPrice()* cart.getQuantity();
        holder.tvPrice.setText(String.valueOf(quantity)); // Chuyển int thành chuỗi
        holder.tvRom.setText(String.valueOf(cart.getRom())); // Chuyển int thành chuỗi
        holder.tvColor.setText(cart.getColor());
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
        holder.tvMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent  intent=new Intent(context, TaoDonHang.class);
            context.startActivity(intent);
            }
        });
         sl= cart.getQuantity();
         price= cart.getPrice();
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl++; // Tăng số lượng
                price = sl * cart.getPrice(); // Tính lại giá
                String doublePrice = formatter.format(price);
                holder.tvQuantity.setText(String.valueOf(sl)); // Cập nhật số lượng hiển thị
                holder.tvPrice.setText(String.valueOf(doublePrice)); // Cập nhật giá hiển thị
                cart.setQuantity(sl); // Cập nhật số lượng trong đối tượng Cart

            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sl > 1) { // Đảm bảo số lượng không âm
                    sl--; // Giảm số lượng
                    price = sl * cart.getPrice(); // Tính lại giá
                    String doublePrice = formatter.format(price);
                    holder.tvQuantity.setText(String.valueOf(sl)); // Cập nhật số lượng hiển thị
                    holder.tvPrice.setText(String.valueOf(doublePrice)); // Cập nhật giá hiển thị
                    cart.setQuantity(sl); // Cập nhật số lượng trong đối tượng Cart
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvColor, tvRom, tvPrice, tvMua, tvQuantity;
        ImageView tvPlus,tvMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameProduct);
            tvColor = itemView.findViewById(R.id.colorProduct);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvRom = itemView.findViewById(R.id.ramProduct);
            tvPrice = itemView.findViewById(R.id.priceProduct);
            tvMua = itemView.findViewById(R.id.btnMuaNgay);
            tvPlus = itemView.findViewById(R.id.btnPlus);
            tvMinus = itemView.findViewById(R.id.btnApart);
        }
    }
}

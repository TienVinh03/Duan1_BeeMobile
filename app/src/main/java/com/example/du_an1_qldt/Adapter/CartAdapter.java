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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.CartDao;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.Frag_GioHang;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.TaoDonHang;
import com.example.du_an1_qldt.TotalPriceListener;
import com.example.du_an1_qldt.model.Cart;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Cart> list;
    int sl;
    int price;
    private TotalPriceListener totalPriceListener;
CartDao cartDao;
DecimalFormat format;

    public void setOnTotalPriceChangedListener(TotalPriceListener listener) {
        this.totalPriceListener = listener;
    }
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
        quantity= cart.getPrice();
        format=new DecimalFormat("#,###,###");
        String quantityPr=format.format(quantity);
        holder.tvPrice.setText(quantityPr+"VND"); // Chuyển int thành chuỗi
        holder.tvRom.setText(String.valueOf(cart.getRom())+"gb"); // Chuyển int thành chuỗi
        holder.tvColor.setText(cart.getColor());
        holder.tvQuantity.setText(String.valueOf(1));
        cartDao= new CartDao(context);
        cartDao.updateCartItemQuantity(cart.getId(),sl);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check =cartDao.deleteRowCart(cart);
                if(check>0){
                    list.remove(cart);
                    if (totalPriceListener != null) {
                        totalPriceListener.onTotalPriceChanged(calculateTotalPrice()); // calculateTotalPrice() là phương thức tính tổng giá trị trong giỏ hàng
                    }
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
         sl= cart.setQuantity(1);
         price= cart.getPrice();
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl= cart.getQuantity();
                price= cart.getPrice();
                sl++; // Tăng số lượng
                price = sl * cart.getPrice(); // Tính lại giá
                String doublePrice = formatter.format(price);
                holder.tvQuantity.setText(String.valueOf(sl)); // Cập nhật số lượng hiển thị
                cartDao.updateCartItemQuantity(cart.getId(),sl);
                holder.tvPrice.setText(String.valueOf(doublePrice)+"VND"); // Cập nhật giá hiển thị
                cart.setQuantity(sl); // Cập nhật số lượng trong đối tượng Cart
                if (totalPriceListener != null) {
                    totalPriceListener.onTotalPriceChanged(calculateTotalPrice()); // calculateTotalPrice() là phương thức tính tổng giá trị trong giỏ hàng
                }

            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl= cart.getQuantity();
                price= cart.getPrice();
                if (sl > 1) { // Đảm bảo số lượng không âm
                    sl--; // Giảm số lượng
                    price = sl * cart.getPrice(); // Tính lại giá
                    String doublePrice = formatter.format(price);
                    holder.tvQuantity.setText(String.valueOf(sl)); // Cập nhật số lượng hiển thị
                    cartDao.updateCartItemQuantity(cart.getId(),sl);
                    holder.tvPrice.setText(String.valueOf(doublePrice)+"VND"); // Cập nhật giá hiển thị
                    cart.setQuantity(sl); // Cập nhật số lượng trong đối tượng Cart
                    if (totalPriceListener != null) {
                        totalPriceListener.onTotalPriceChanged(calculateTotalPrice());

                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvColor, tvRom, tvPrice, tvQuantity;
        ImageView tvPlus,tvMinus,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameProduct);
            tvColor = itemView.findViewById(R.id.colorProduct);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvRom = itemView.findViewById(R.id.ramProduct);
            tvPrice = itemView.findViewById(R.id.priceProduct);
            btnDelete = itemView.findViewById(R.id.btndelete);
            tvPlus = itemView.findViewById(R.id.btnPlus);
            tvMinus = itemView.findViewById(R.id.btnApart);
        }

    }
    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (Cart cartItem : list) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();// Giả sử bạn có phương thức getPrice() và getQuantity() trong class Cart
        }
        return totalPrice;
    }
    private void notifyTotalPriceChanged(double totalPrice) {
        if (totalPriceListener != null) {
            totalPriceListener.onTotalPriceChanged(totalPrice);
        }
    }
}

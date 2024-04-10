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
import com.example.du_an1_qldt.model.OrderDetail;

import java.util.ArrayList;

public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderAdapter.ViewHolder> {
    Context context;
    ArrayList<OrderDetail> list;

    public DetailOrderAdapter(Context context, ArrayList<OrderDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DetailOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_order_detail, parent, false);
        DetailOrderAdapter.ViewHolder holder = new DetailOrderAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOrderAdapter.ViewHolder holder, int position) {
OrderDetail orderDetail=list.get(position);
        SanPhamDAO sanPhamDAO= new SanPhamDAO(context);
holder.tvName.setText(sanPhamDAO.getProductNameById(orderDetail.getId()));
holder.tvPrice.setText(orderDetail.getPrice()+"đ");
holder.tvQuantity.setText(orderDetail.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvQuantity, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.nameProduct);
            tvQuantity=itemView.findViewById(R.id.tvQuantity);
            tvPrice=itemView.findViewById(R.id.price);
        }
    }
}

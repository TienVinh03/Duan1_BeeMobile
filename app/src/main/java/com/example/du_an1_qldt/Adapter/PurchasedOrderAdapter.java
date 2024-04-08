package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Order;

import java.util.ArrayList;

public class PurchasedOrderAdapter extends RecyclerView.Adapter<PurchasedOrderAdapter.ViewHolder> {
    Context context;
    ArrayList<Order> orders;

    public PurchasedOrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_order_customer, parent, false);
        PurchasedOrderAdapter.ViewHolder holder = new PurchasedOrderAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order=orders.get(position);
        holder.tvId.setText(String.valueOf(order.getId()));
        holder.tvDate.setText(order.getDateOrder());
        holder.tvStatus.setText(order.getStatusOrder());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvId,tvStatus,tvDate,btnDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.idOrder);
            tvStatus=itemView.findViewById(R.id.statusOrder);
            tvDate=itemView.findViewById(R.id.DateOrder);
            btnDetail=itemView.findViewById(R.id.btnDetail);
        }
    }
}

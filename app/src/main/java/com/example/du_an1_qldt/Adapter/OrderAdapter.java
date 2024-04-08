package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.DAO.TaiKhoanDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    ArrayList<Order> orders;
    OrderDAO orderDAO;
    Context context;
    TaiKhoanDAO taiKhoanDAO;


    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
        orderDAO = new OrderDAO((context));
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_order, parent, false);
        OrderAdapter.ViewHolder holder = new OrderAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.id.setText(String.valueOf(order.getId()));
        taiKhoanDAO = new TaiKhoanDAO(context);
        holder.nameCustomer.setText(taiKhoanDAO.getUserNameById(order.getId()));
        holder.date.setText(order.getDateOrder());
        holder.status.setText(order.getStatusOrder());
        LinearLayout linearLayoutToRemove = holder.layoutContainer;
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orders.remove(order);
                orderDAO.deletOrder(order);
                orderDAO.deleteOrderDetailsByOrderId(order.getId());
                notifyDataSetChanged();
            }
        });
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setStatusOrder("Đã xác nhận");
                holder.status.setText(order.getStatusOrder());
                orderDAO.updateOrder(order);
                linearLayoutToRemove.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nameCustomer, date, status, btnConfirm, btnCancel;
        LinearLayout layoutContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idOrder);
            nameCustomer = itemView.findViewById(R.id.nameCustomer);
            date = itemView.findViewById(R.id.DateOrder);
            status = itemView.findViewById(R.id.statusOrder);
            btnConfirm = itemView.findViewById(R.id.btnConfirmOrder);
            btnCancel = itemView.findViewById(R.id.btnCancelOrder);
            layoutContainer=itemView.findViewById(R.id.linearLayout);
        }
    }
}

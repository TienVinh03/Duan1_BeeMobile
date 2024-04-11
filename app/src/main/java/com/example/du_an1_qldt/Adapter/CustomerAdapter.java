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
import com.example.du_an1_qldt.model.Customer;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    Context context;
    ArrayList<Customer> customers;

    public CustomerAdapter(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_customer, parent, false);
        CustomerAdapter.ViewHolder holder = new CustomerAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        Customer customer= customers.get(position);
        holder.name.setText(customer.getName());
        holder.address.setText(customer.getAddress());
        holder.numberPhone.setText(customer.getNumberPhone());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
TextView name,address,numberPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameCustomer);
            address=itemView.findViewById(R.id.addressCustomer);
            numberPhone=itemView.findViewById(R.id.phoneNumberCustomer);
        }
    }
}
package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    Context context;
    ArrayList<Voucher_DTO> listVoucher;

    public VoucherAdapter(Context context, ArrayList<Voucher_DTO> listVoucher) {
        this.context = context;
        this.listVoucher = listVoucher;
    }
    @NonNull
    @Override
    public VoucherAdapter.VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_ql_voucher,parent,false);
        VoucherAdapter.VoucherViewHolder holder = new  VoucherAdapter.VoucherViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.VoucherViewHolder holder, int position) {
        VoucherDAO voucherDAO = new VoucherDAO(context);
        listVoucher = voucherDAO.getListVoucher();
        Voucher_DTO voucherDto = listVoucher.get(position);
        holder.tenVoucher.setText(voucherDto.getTenVoucher());
        holder.maVoucher.setText(voucherDto.getId()+"");
        holder.trangThai.setText(voucherDto.getTrangThai()+"");
        holder.soLuong.setText(voucherDto.getSoLuong()+"");
        if (voucherDto.getTrangThai()==1){
            holder.trangThai.setText("Còn voucher");
        }else {
            holder.trangThai.setText("Hết voucher");
        }
        holder.giaTriGiam.setText(voucherDto.getGiaTriGiam()+"");


        holder.cardViewVoucher.setCardBackgroundColor(Color.WHITE);
        holder.cardViewVoucher.setRadius(70);
        holder.cardViewVoucher.setCardElevation(8);

        holder.select_row_qlvoucher.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(context,holder.select_row_qlvoucher);
                popupMenu.setForceShowIcon(true);
                popupMenu.getMenuInflater().inflate(R.menu.select_sua_xoa,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.select_sua){
                            Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (item.getItemId()==R.id.select_xoa) {
                            Toast.makeText(context, "Xóa", Toast.LENGTH_SHORT).show();
                            return true;
                        }else {
                            return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVoucher.size();
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView maVoucher;
        TextView tenVoucher;
        TextView trangThai;
        TextView soLuong;
        TextView giaTriGiam;
        CardView cardViewVoucher;
        Button select_row_qlvoucher;
        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            maVoucher = itemView.findViewById(R.id.tv_maVoucher);
            tenVoucher= itemView.findViewById(R.id.tv_tenVoucher);
            trangThai = itemView.findViewById(R.id.tv_trangthaiVoucher);
            soLuong = itemView.findViewById(R.id.tv_soLuongVoucher);
            giaTriGiam = itemView.findViewById(R.id.tv_menhGiaVoucher);
            cardViewVoucher =itemView.findViewById(R.id.cardViewVoucher_ql);
            select_row_qlvoucher = itemView.findViewById(R.id.select_row_qlvoucher);
        }
    }
}

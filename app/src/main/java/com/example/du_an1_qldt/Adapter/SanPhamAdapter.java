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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.du_an1_qldt.R;

import com.example.du_an1_qldt.model.phone;
import com.example.du_an1_qldt.DAO.SanPhamDAO;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    Context context;
    ArrayList<phone> listSP;

    public SanPhamAdapter(Context context, ArrayList<phone> listSP) {
        this.context = context;
        this.listSP = listSP;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_ql_san_pham,parent,false);
        SanPhamViewHolder holder = new SanPhamViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        listSP = sanPhamDAO.getlistSP();
        phone phoneDTO = listSP.get(position);
        holder.tv_tenSP.setText(phoneDTO.getName());
        holder.tv_giaSp.setText(phoneDTO.getGia()+"");
        if (phoneDTO.getStatus()==1){
            holder.tv_statusSP.setText("Còn hàng");
        }else {
            holder.tv_statusSP.setText("Hết hàng");
        }
        holder.tv_hangSP.setText("Xiaomi");
        holder.tv_RomSP.setText(phoneDTO.getRom()+"");
        if (phoneDTO.getColor().equals("#FFFFFF")){
            holder.tv_mauSacSp.setText("Màu Trắng");
        } else if (phoneDTO.getColor().equals("#FFFF00")) {
            holder.tv_mauSacSp.setText("Màu Vàng");
        } else if (phoneDTO.getColor().equals("#000000")) {
            holder.tv_mauSacSp.setText("Màu Đen");
        }else if (phoneDTO.getColor().equals("#FF0000")) {
            holder.tv_mauSacSp.setText("Màu Đỏ");
        }


        holder.cardViewSP.setCardBackgroundColor(Color.WHITE);
        holder.cardViewSP.setRadius(70);
        holder.cardViewSP.setCardElevation(8);

        holder.select_row_qlsp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(context,holder.select_row_qlsp);
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
        return listSP.size();
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenSP;
        TextView tv_hangSP;
        TextView tv_RomSP;
        TextView tv_giaSp;
        TextView tv_mauSacSp;
        TextView tv_statusSP;
        ImageView img_anhSP;
        CardView cardViewSP;
        Button select_row_qlsp;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_statusSP = itemView.findViewById(R.id.tv_TrangthaiSP);
            tv_tenSP = itemView.findViewById(R.id.tv_tenSP);
            tv_hangSP = itemView.findViewById(R.id.tv_tenhangSP);
            tv_RomSP = itemView.findViewById(R.id.tv_RomSP);
            tv_giaSp = itemView.findViewById(R.id.tv_GiaSP);
            tv_mauSacSp = itemView.findViewById(R.id.tv_MauSacSP);
            img_anhSP =itemView.findViewById(R.id.anh1);
            cardViewSP = itemView.findViewById(R.id.cardViewSanPham);
            select_row_qlsp = itemView.findViewById(R.id.select_row_qlsp);


        }
    }
}

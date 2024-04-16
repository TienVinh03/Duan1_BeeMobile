package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Voucher_DTO;

import java.util.ArrayList;

public class DSVoucher_Adapter extends RecyclerView.Adapter<DSVoucher_Adapter.DSVoucherViewHolder> {

    Context context;
    ArrayList<Voucher_DTO> listVoucher;

    public DSVoucher_Adapter(Context context, ArrayList<Voucher_DTO> listVoucher) {
        this.context = context;
        this.listVoucher = listVoucher;
    }
    @NonNull
    @Override
    public DSVoucher_Adapter.DSVoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_ds_voucher,parent,false);
        DSVoucher_Adapter.DSVoucherViewHolder holder = new  DSVoucher_Adapter.DSVoucherViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DSVoucher_Adapter.DSVoucherViewHolder holder, int position) {
        VoucherDAO voucherDAO = new VoucherDAO(context);
        listVoucher = voucherDAO.getListVoucher();
        Voucher_DTO voucherDto = listVoucher.get(position);
        holder.tenVoucher.setText(voucherDto.getTenVoucher());
        holder.maVoucher.setText(voucherDto.getId()+"");
        holder.trangThai.setText(voucherDto.getTrangThai()+"");
        holder.soLuong.setText(voucherDto.getSoLuong()+"");
        if (voucherDto.getSoLuong()<0){
            holder.soLuong.setText("0");
            voucherDto.setGiaTriGiam(0);
            voucherDto.setTrangThai(0);
        }else {
            holder.soLuong.setText(voucherDto.getSoLuong()+"");
        }


        if (voucherDto.getSoLuong()>0){
            holder.trangThai.setText("Còn voucher");
            holder.giaTriGiam.setText(voucherDto.getGiaTriGiam()+" %");
        }else {
            holder.trangThai.setText("Hết voucher");

            holder.giaTriGiam.setText(voucherDto.getGiaTriGiam()+" %");
        }
        holder.cardViewVoucher.setCardBackgroundColor(Color.WHITE);
        holder.cardViewVoucher.setRadius(70);
        holder.cardViewVoucher.setCardElevation(8);

        holder.btn_themVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listVoucher.size();
    }

    public class DSVoucherViewHolder extends RecyclerView.ViewHolder {
        TextView maVoucher;
        TextView tenVoucher;
        TextView trangThai;
        TextView soLuong;
        TextView giaTriGiam;
        CardView cardViewVoucher;
        Button btn_themVoucher;
        public DSVoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            maVoucher = itemView.findViewById(R.id.tv_maVoucher_ds);
            tenVoucher= itemView.findViewById(R.id.tv_tenVoucher_ds);
            trangThai = itemView.findViewById(R.id.tv_trangthaiVoucher_ds);
            soLuong = itemView.findViewById(R.id.tv_soLuongVoucher_ds);
            giaTriGiam = itemView.findViewById(R.id.tv_menhGiaVoucher_ds);
            cardViewVoucher =itemView.findViewById(R.id.cardViewDSVoucher);
            btn_themVoucher = itemView.findViewById(R.id.btn_themVoucher);
        }
    }
}

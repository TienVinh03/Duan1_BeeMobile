package com.example.du_an1_qldt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.Voucher_DTO;

import java.util.ArrayList;

public class VoucherSpinnerAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Voucher_DTO> listVoucher;

    public VoucherSpinnerAdapter( Context context, ArrayList<Voucher_DTO> listVoucher) {
        this.context=context;
        this.listVoucher = listVoucher;
    }


    @Override
    public int getCount() {
        return listVoucher.size();
    }

    @Override
    public Object getItem(int position) {
        return listVoucher.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class TheLoaiViewHolder {
        TextView txtVoucherName;
        TextView txtDiscount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TheLoaiViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner_voucher, parent, false);
            viewHolder = new TheLoaiViewHolder();
            viewHolder.txtVoucherName = convertView.findViewById(R.id.nameVoucher);
            viewHolder.txtDiscount = convertView.findViewById(R.id.giaTriGiam);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TheLoaiViewHolder) convertView.getTag();
        }

        Voucher_DTO voucherDto = listVoucher.get(position);
        if (voucherDto.getSoLuong()>0){
            viewHolder.txtVoucherName.setText(voucherDto.getTenVoucher());
            viewHolder.txtDiscount.setText("-"+String.valueOf(voucherDto.getGiaTriGiam()+"%"));
        }else {viewHolder.txtVoucherName.setText(voucherDto.getTenVoucher());
            viewHolder.txtDiscount.setText("-"+String.valueOf("Voucher đã hết"));


        }

        return convertView;
    }
}

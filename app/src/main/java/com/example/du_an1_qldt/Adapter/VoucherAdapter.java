package com.example.du_an1_qldt.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.OrderDetail;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    Context context;
    ArrayList<Voucher_DTO> listVoucher;
    OrderDAO orderDAO;
    VoucherAdapter voucherAdapter;
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
    public void onBindViewHolder(@NonNull VoucherAdapter.VoucherViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VoucherDAO voucherDAO = new VoucherDAO(context);
        listVoucher = voucherDAO.getListVoucher();
        orderDAO = new OrderDAO(context);
        Voucher_DTO voucherDto = listVoucher.get(position);
        holder.tenVoucher.setText(voucherDto.getTenVoucher());
        holder.maVoucher.setText(voucherDto.getId()+"");
        holder.trangThai.setText(voucherDto.getTrangThai()+"");

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


//
//        holder.cardViewVoucher.setCardBackgroundColor(Color.WHITE);
//        holder.cardViewVoucher.setRadius(70);
//        holder.cardViewVoucher.setCardElevation(8);

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
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                            View v1=inflater.inflate(R.layout.dialog_sua_voucher,null);
                            EditText tenVoucher = v1.findViewById(R.id.edt_tenVoucher_sua);
                            EditText soLuong = v1.findViewById(R.id.edt_soLuongVoucher_sua);
                            EditText menhgia = v1.findViewById(R.id.edt_menhgia_sua);
                            Button sua = v1.findViewById(R.id.btn_suaVoucher_sua);

                            tenVoucher.setText(voucherDto.getTenVoucher());

                            if (voucherDto.getSoLuong()<=0){
                                menhgia.setText("0");
                                soLuong.setText("0");
                            }else
                            {
                                menhgia.setText(voucherDto.getGiaTriGiam()+"");
                                soLuong.setText(voucherDto.getSoLuong()+"");
                            }



                            listVoucher =voucherDAO.getListVoucher();
                            voucherAdapter= new VoucherAdapter(context,listVoucher);



                            builder.setTitle("                              Sửa Voucher");
                            builder.setView(v1);
                            Dialog dialog = builder.create();

                            sua.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {

                                        int menhgia1 = Integer.parseInt(menhgia.getText().toString());
                                        int soluong1 = Integer.parseInt(soLuong.getText().toString());

                                        if (TextUtils.isEmpty(tenVoucher.getText().toString())||TextUtils.isEmpty(soLuong.getText().toString())||TextUtils.isEmpty(menhgia.getText().toString())){
                                            Toast.makeText(context, "Vui lòng Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                                        }else if (menhgia1<=0) {
                                            Toast.makeText(context, "Gía không phù hợp", Toast.LENGTH_SHORT).show();

                                        }else if (soluong1<0) {
                                            Toast.makeText(context, "Số lượng không phù hợp", Toast.LENGTH_SHORT).show();

                                        }else {
                                            String tenVoucher2 = tenVoucher.getText().toString();
                                            int soLuong2= Integer.parseInt(soLuong.getText().toString());
                                            int menhgia2= Integer.parseInt(menhgia.getText().toString());

                                            voucherDto.setTenVoucher(tenVoucher2);
                                            voucherDto.setSoLuong(soLuong2);
                                            voucherDto.setGiaTriGiam(menhgia2);
                                            if (soluong1>0){
                                                voucherDto.setTrangThai(1);
                                            }else {
                                                voucherDto.setTrangThai(0);
                                            }
                                            int check = voucherDAO.sua_Voucher(voucherDto);
                                            if (check>0){
                                                listVoucher.clear();
                                                listVoucher.addAll(voucherDAO.getListVoucher());
                                                notifyDataSetChanged();
                                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }else {
                                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }

                                        }
                                    }catch (Exception e){
                                        Toast.makeText(context, "Vui lòng Nhập đúng dịnh dạng     ", Toast.LENGTH_SHORT).show();
                                    }

                                }

                            });
                            dialog.show();
                            return true;
                        } else if (item.getItemId()==R.id.select_xoa) {
                            VoucherDAO voucherDAO1 = new VoucherDAO(context);
                            int check = voucherDAO1.deleteRow_Voucher(voucherDto);
                            if (check>0){
                                listVoucher.remove(voucherDto);

                                notifyDataSetChanged();

                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
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
//            cardViewVoucher =itemView.findViewById(R.id.cardViewVoucher_ql);
            select_row_qlvoucher = itemView.findViewById(R.id.select_row_qlvoucher);
        }
    }
//    private void updateVoucherQuantities(int orderId) {
//        VoucherDAO voucherDAO= new VoucherDAO(context);
//        // Lấy danh sách sản phẩm trong đơn hàng từ cơ sở dữ liệu
//        ArrayList<Voucher_DTO> voucher_DTO = voucherDAO.getListVoucher();
//
//        for (Voucher_DTO voucher_dto : voucher_DTO) {
//            // Lấy số lượng sản phẩm hiện tại từ cơ sở dữ liệu
//            int currentQuantity = voucherDAO.getVoucherQuantityFromDatabase(voucher_dto.getId());
//
//            // Tính toán số lượng mới (ví dụ: giảm số lượng bằng số lượng trong đơn hàng)
//            int updatedQuantity = currentQuantity - 1;
//
//            // Cập nhật số lượng sản phẩm mới vào cơ sở dữ liệu
//            voucherDAO.updateProductQuantityInDatabase(voucher_dto.getId(), updatedQuantity);
//        }
//    }

}

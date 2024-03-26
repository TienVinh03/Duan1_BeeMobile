package com.example.du_an1_qldt.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.HangDienThoaiDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.HangDienThoai;

import java.util.ArrayList;

public class HangDienThoaiAdapter extends RecyclerView.Adapter<HangDienThoaiAdapter.ViewHolder> {
    ArrayList<HangDienThoai> list;
    Context context;
    private HangDienThoaiDAO hangDienThoaiDAO;

    public HangDienThoaiAdapter(ArrayList<HangDienThoai> list, Context context, HangDienThoaiDAO hangDienThoaiDAO) {
        this.list = list;
        this.context = context;
        this.hangDienThoaiDAO = hangDienThoaiDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.row_ql_hang_dt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenHang.setText(list.get(position).getTenHang());
        holder.tvHeDieuHanh.setText(list.get(position).getHeDieuHanh());

        holder.ibdlthdt.setOnClickListener(new View.OnClickListener() {

            Button btnDialogYesLoaiSanPham, btnDialogNoLoaiSanPham;
            private HangDienThoaiDAO hangDienThoaiDAO = new HangDienThoaiDAO(context);

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_deletehdh, null);
                builder.setView(v);
                Dialog dialog = builder.create();
                btnDialogYesLoaiSanPham = v.findViewById(R.id.btnDialogYesLoaiSanPham);
                btnDialogNoLoaiSanPham = v.findViewById(R.id.btnDialogNoLoaiSanPham);
                btnDialogNoLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnDialogYesLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HangDienThoaiDAO hangDienThoaiDAO1 = new HangDienThoaiDAO(context);
                        int check = hangDienThoaiDAO1.delete(list.get(position).getIdHang());
                        if (check > 0) {
                            list.clear();
                            list = (ArrayList<HangDienThoai>) hangDienThoaiDAO.getAll();
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(context, "Đã có sản phẩm, không thể xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        holder.ibUpdateLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            EditText edtDialogUpdateLoaiSanPham,edtDialogUpdateLoaiSanPham1;
            Button btnDialogHuySuaLoaiSanPham, btnDialogSuaLoaiSanPham;
            private HangDienThoaiDAO hangDienThoaiDAO = new HangDienThoaiDAO(context);

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                view = inflater1.inflate(R.layout.item_updatehdh, null);
                builder.setView(view);
                Dialog dialog = builder.create();

                edtDialogUpdateLoaiSanPham = view.findViewById(R.id.edtDialogUpdateLoaiSanPham);
                btnDialogHuySuaLoaiSanPham = view.findViewById(R.id.btnDialogHuySuaLoaiSanPham);
                btnDialogSuaLoaiSanPham = view.findViewById(R.id.btnDialogSuaLoaiSanPham);
                edtDialogUpdateLoaiSanPham.setText(list.get(position).getTenHang());
                edtDialogUpdateLoaiSanPham1.setText(list.get(position).getHeDieuHanh());

                btnDialogHuySuaLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogSuaLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HangDienThoai hangDienThoai = list.get(position);
                        hangDienThoai.setTenHang(edtDialogUpdateLoaiSanPham.getText().toString());
                        hangDienThoai.setHeDieuHanh(edtDialogUpdateLoaiSanPham1.getText().toString());

                        if(hangDienThoaiDAO.update(hangDienThoai) >0){
                            list.clear();
                            list = (ArrayList<HangDienThoai>) hangDienThoaiDAO.getAll();
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenHang,tvHeDieuHanh;
        ImageView ibUpdateLoaiSanPham, ibdlthdt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenHang = itemView.findViewById(R.id.tvTenHang);
            ibUpdateLoaiSanPham = itemView.findViewById(R.id.ibUpdateLoaiSanPham);
            ibdlthdt = itemView.findViewById(R.id.ibdlthdt);
            tvHeDieuHanh = itemView.findViewById(R.id.tvHeDieuHanh);


        }
    }

}

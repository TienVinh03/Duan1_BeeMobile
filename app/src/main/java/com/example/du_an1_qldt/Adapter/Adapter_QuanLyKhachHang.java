package com.example.du_an1_qldt.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.HangDienThoaiDAO;
import com.example.du_an1_qldt.DAO.KhachHangDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.model.HangDienThoai;
import com.example.du_an1_qldt.model.KhachHang_DTO;

import java.util.ArrayList;


public class Adapter_QuanLyKhachHang extends RecyclerView.Adapter<Adapter_QuanLyKhachHang.ViewHolder> {
    Context context;
    ArrayList<KhachHang_DTO> list;
    KhachHangDAO khachHangDAO;

    public Adapter_QuanLyKhachHang(Context context, ArrayList<KhachHang_DTO> list, KhachHangDAO khachHangDAO) {
        this.context = context;
        this.list = list;
        this.khachHangDAO = khachHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rycyclekhachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtHovaTen.setText("Họ và tên: " + list.get(position).getHoten());
        holder.txtUsername.setText("Username: " + list.get(position).getUsername());
        holder.txtPassword.setText("Password: " + list.get(position).getPassword());
        holder.txtSoDienThoai.setText("Số điện thoại: " + list.get(position).getSodienthoai());
        holder.txtEmail.setText("Email: " + list.get(position).getEmail());
        holder.txtDiaChi.setText("Địa chỉ: " + list.get(position).getDiachi());


//        holder.btnXoaKhachHang.setOnClickListener(new View.OnClickListener() {
//            //            int check = hangDienThoaiDAO1.delete(list.get(position).getIdHang());
//
//            @Override
//            public void onClick(View v) {
//                int check = khachHangDAO.xoaThongTinThanhVien(list.get(position).getManguoidung());
//                switch (check) {
//                    case 1:
//                        Toast.makeText(context, "Xóa khách hàng thành công", Toast.LENGTH_SHORT).show();
//                        loadData();
//                        break;
//                    case 0:
//                        Toast.makeText(context, "Xóa khách hàng thất bại", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    case -1:
//                        Toast.makeText(context, "Khách hàng đã có hóa đơn không được phép xóa", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    default:
//                        break;
//
//                }
//            }
//        });
    }
//   btnDialogYesLoaiSanPham.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            HangDienThoaiDAO hangDienThoaiDAO1 = new HangDienThoaiDAO(context);
//            int check = hangDienThoaiDAO1.delete(list.get(position).getIdHang());
//            if (check > 0) {
//                list.clear();
//                list = (ArrayList<HangDienThoai>) hangDienThoaiDAO.getAll();
//                notifyDataSetChanged();
//                dialog.dismiss();
//                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
//            } else {
//                dialog.dismiss();
//                Toast.makeText(context, "Đã có sản phẩm, không thể xóa", Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
//                dialog.show();
//}
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHovaTen, txtUsername, txtDiaChi, txtPassword, txtEmail, txtSoDienThoai;
        ImageView btnSuaKhachHang, btnXoaKhachHang;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtHovaTen = view.findViewById(R.id.txtHovaTen);
            txtUsername = view.findViewById(R.id.txtUsername);
            txtPassword = view.findViewById(R.id.txtPassword);
            txtSoDienThoai = view.findViewById(R.id.txtSoDienThoai);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtDiaChi = view.findViewById(R.id.txtDiaChi);
//            btnSuaKhachHang = view.findViewById(R.id.btnSuaKhachHang);
//            btnXoaKhachHang = view.findViewById(R.id.btnXoaKhachHang);

        }
    }

    private void loadData() {
        list.clear();
        list = khachHangDAO.getAll();
        notifyDataSetChanged();
    }
}

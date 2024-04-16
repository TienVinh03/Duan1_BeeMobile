package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.CartDao;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.TaoDonHang;
import com.example.du_an1_qldt.TrangChuNguoiDung;
import com.example.du_an1_qldt.model.Cart;
import com.example.du_an1_qldt.model.phone;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DSSP_Adapter extends RecyclerView.Adapter<DSSP_Adapter.ViewHolder_DSSP> {


    Context context;
    TrangChuNguoiDung tcnd;
    ArrayList<phone> listSP;
    SanPhamDAO dsDao;
    SQLiteDatabase sqLiteDatabase;

    public DSSP_Adapter(Context context, ArrayList<phone> listSP) {
        this.context = context;
        this.listSP = listSP;
        dsDao = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder_DSSP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_dssp, parent, false);
        ViewHolder_DSSP holder = new ViewHolder_DSSP(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_DSSP holder, int position) {


        phone dt = listSP.get(position);
        holder.tv_tenSP1.setText(String.valueOf(dt.getName()));
        double sum = dt.getGia();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedRevenueDay = currencyFormat.format(sum);
        holder.tv_giaSPham.setText(formattedRevenueDay+"");

        if (dt.getSoLuong()>0){
            holder.tv_TrangthaiSP1.setText("Còn hàng");
        }else {
            holder.tv_TrangthaiSP1.setText("Hết hàng");
        }
        holder.tv_mauSac.setText(String.valueOf(dt.getColor()));
        holder.tv_romSP.setText("Rom:" + String.valueOf(dt.getRom()));

        if (dt.getImage()==1){
            holder.anh1.setImageResource(R.drawable.k40_gaming);
        } else if (dt.getImage()==2) {
            holder.anh1.setImageResource(R.drawable.k50);
        }else if (dt.getImage()==3) {
            holder.anh1.setImageResource(R.drawable.iphone3);
        }else if (dt.getImage()==4) {
            holder.anh1.setImageResource(R.drawable.iphone5);
        }

        holder.btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", dt.getName());
                bundle.putInt("id", dt.getId());
                bundle.putString("color", dt.getColor());
                bundle.putString("ram", String.valueOf(dt.getRom()));
                bundle.putString("price", String.valueOf(dt.getGia()));
                bundle.putInt("quantity", dt.getSoLuong());
                Intent intent = new Intent(context, TaoDonHang.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnThemvaoGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                String idUser = sharedPreferences.getString("manguoidung", "");
                CartDao cartDao = new CartDao(context);
                Cart cart = new Cart();
                cart.setPrice(dt.getGia());
                cart.setRom(dt.getRom());
                cart.setQuantity(1);
                cart.setIdPhone(dt.getId());
                cart.setColor(dt.getColor());
                cart.setIdUser(Integer.parseInt(idUser));
                SanPhamDAO dao = new SanPhamDAO(context);
                cart.setName(dao.getProductNameById(dt.getId()));
                int result = cartDao.addSanPhamtoCart(cart);
                if (result > 0) {
                    Toast.makeText(context, "Thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        phone phoneDTO = listSP.get(position);


    }


    @Override
    public int getItemCount() {
        return listSP.size();
    }

    public class ViewHolder_DSSP extends RecyclerView.ViewHolder {
        TextView tv_tenSP1, tv_giaSPham, tv_TrangthaiSP1,tv_mauSac,tv_romSP,btnThemvaoGiohang,btnMuaNgay;
        ImageView anh1 ;

        public ViewHolder_DSSP(@NonNull View itemView) {
            super(itemView);

            tv_tenSP1 = itemView.findViewById(R.id.tv_tenSP1);
            tv_giaSPham = itemView.findViewById(R.id.tv_giaSPham);
            tv_TrangthaiSP1 = itemView.findViewById(R.id.tv_TrangthaiSP1);
            anh1 = itemView.findViewById(R.id.anh11);
            tv_mauSac = itemView.findViewById(R.id.tv_mauSac);
            tv_romSP = itemView.findViewById(R.id.tv_romSP);
            btnThemvaoGiohang = itemView.findViewById(R.id.btnThemvaoGiohang);
            btnMuaNgay = itemView.findViewById(R.id.btnMuaNgay);

        }
    }


}

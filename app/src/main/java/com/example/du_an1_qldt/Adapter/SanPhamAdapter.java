package com.example.du_an1_qldt.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.Frag_QuanLiSanPham;
import com.example.du_an1_qldt.R;

import com.example.du_an1_qldt.model.phone;
import com.example.du_an1_qldt.DAO.SanPhamDAO;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    Context context;

    ArrayList<phone> listSP;
    Spinner spn_hangDT;
    dbHelper dbHelper1;
    SanPhamDAO sanPhamDAO11;
    SanPhamAdapter sanPhamAdapter1;
    Frag_QuanLiSanPham fragQuanLiSanPham;

    public SanPhamAdapter(Context context, ArrayList<phone> listSP, SanPhamDAO sanPhamDAO11) {
        this.context = context;
        this.listSP = listSP;
        this.sanPhamDAO11 = sanPhamDAO11;
    }

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
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        listSP = sanPhamDAO.getlistSP();
        dbHelper1 = new dbHelper(context);
        phone phoneDTO = listSP.get(position);

        holder.tv_tenSP.setText(phoneDTO.getName());

        double sum = phoneDTO.getGia();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedRevenueDay = currencyFormat.format(sum);
        holder.tv_giaSp.setText(formattedRevenueDay+"");
        holder.tv_hangSP.setText(getTenLoaiSanPham(phoneDTO.getId_Hang()));
        if (phoneDTO.getImage()==1){
            holder.img_anhSP.setImageResource(R.drawable.k40_gaming);
        } else if (phoneDTO.getImage()==2) {
            holder.img_anhSP.setImageResource(R.drawable.k50);
        }else if (phoneDTO.getImage()==3) {
            holder.img_anhSP.setImageResource(R.drawable.iphone3);
        }else if (phoneDTO.getImage()==4) {
            holder.img_anhSP.setImageResource(R.drawable.iphone5);
        }


        holder.tv_soLuongsp.setText(phoneDTO.getSoLuong()+"");
        if (phoneDTO.getSoLuong()>0){
            holder.tv_statusSP.setText("Còn hàng");
        }else {
            holder.tv_statusSP.setText("Hết hàng");
        }

        holder.tv_RomSP.setText(phoneDTO.getRom()+"");
        holder.tv_mauSacSp.setText(phoneDTO.getColor());



//        holder.cardViewSP.setRadius(70);
//        holder.cardViewSP.setCardElevation(8);

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
                           AlertDialog.Builder builder = new AlertDialog.Builder(context);
                           LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                           View v1=inflater.inflate(R.layout.dialog_suasp,null);
                           EditText tensp = v1.findViewById(R.id.edt_tenSP_sua);
                           EditText gia_dt = v1.findViewById(R.id.edt_giaDT_sua);
                           EditText rom_dt = v1.findViewById(R.id.edt_romDT_sua);
                           EditText soluong_dt = v1.findViewById(R.id.edt_soLuongDT_sua);
                           spn_hangDT =v1.findViewById(R.id.spin_tenHangDT_sua);
                           Spinner spn_mausac = v1.findViewById(R.id.spin_mauSac_sua);
                           TextView tv_trangThai = v1.findViewById(R.id.tv_trangThai_sua);
                           Button btn_save_sua = v1.findViewById(R.id.btn_addsp_sua);

                           tensp.setText(phoneDTO.getName());
                           gia_dt.setText(phoneDTO.getGia()+"");
                           rom_dt.setText(phoneDTO.getRom()+"");
                           soluong_dt.setText(phoneDTO.getSoLuong()+"");
                           spn_hangDT.setSelection(phoneDTO.getId_Hang());
                           if (phoneDTO.getColor().equals("Xám")){
                               spn_mausac.setSelection(1);
                           } else if (phoneDTO.getColor().equals("Trắng")) {
                               spn_mausac.setSelection(2);
                           } else if (phoneDTO.getColor().equals("Đen")) {
                               spn_mausac.setSelection(3);
                           } else if (phoneDTO.getColor().equals("Tím")) {
                               spn_mausac.setSelection(4);
                           }
                           if (phoneDTO.getStatus()==1){
                               tv_trangThai.setText("Còn hàng");
                           }else {
                               tv_trangThai.setText("Hết hàng");
                           }

                           String[] items_mausac = new String[]{"Xám", "Trắng","Đen","Tím"};

                           ArrayAdapter<String> adapter_mausac = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items_mausac);
                           adapter_mausac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           spn_mausac.setAdapter(adapter_mausac);







                           ArrayList<String> tenHangDT = new ArrayList<>();

                           Cursor c = dbHelper1.getTenLoaiSanPham();
                           if (c.getCount()>0){
                               c.moveToFirst();
                               do {

                                   @SuppressLint("Range") String tenHangSanPham = c.getString(c.getColumnIndex("tenHang"));
                                   tenHangDT.add(tenHangSanPham);
                               }while (c.moveToNext());
                           }



                           ArrayAdapter<String> adapter_hangDt=new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,tenHangDT);
                           adapter_hangDt.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                           spn_hangDT.setAdapter(adapter_hangDt);



                           listSP =sanPhamDAO.getlistSP();
                           sanPhamAdapter1= new SanPhamAdapter(context,listSP);


                           builder.setView(v1);
                           builder.setTitle("                              Sửa điện thoại");

                           Dialog dialog = builder.create();

                           btn_save_sua.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   try {
                                       int rom = Integer.parseInt(rom_dt.getText().toString());
                                       int gia = Integer.parseInt(gia_dt.getText().toString());
                                       int soluong = Integer.parseInt(soluong_dt.getText().toString());


                                       if (TextUtils.isEmpty(tensp.getText().toString())||TextUtils.isEmpty(rom_dt.getText().toString())||TextUtils.isEmpty(gia_dt.getText().toString())||TextUtils.isEmpty(soluong_dt.getText().toString())){
                                           Toast.makeText(context, "Vui lòng Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                                       } else if (!(rom==128||rom==256||rom==512)) {
                                           Toast.makeText(context, "Vui lòng Nhập đúng định dạng của Rom", Toast.LENGTH_SHORT).show();

                                       } else if (gia<0) {
                                           Toast.makeText(context, "Gía không phù hợp", Toast.LENGTH_SHORT).show();

                                       }else if (soluong<0) {
                                           Toast.makeText(context, "Số lượng không phù hợp", Toast.LENGTH_SHORT).show();

                                       }else {
                                           String ten= tensp.getText().toString();
                                           String tenHang = (String) spn_hangDT.getSelectedItem();
                                           String mausac = (String) spn_mausac.getSelectedItem();
                                           String trangthai = tv_trangThai.getText().toString();



                                           phoneDTO.setName(ten);
                                           phoneDTO.setId_Hang(spn_hangDT.getSelectedItemPosition()+1);
                                           if (soluong>=1){
                                               tv_trangThai.setText("Còn hàng");
                                               phoneDTO.setStatus(1);
                                           }else {
                                               tv_trangThai.setText("Hết hàng");
                                               phoneDTO.setStatus(0);
                                           }


                                           phoneDTO.setColor(spn_mausac.getSelectedItem().toString());
                                           phoneDTO.setRom(rom);
                                           phoneDTO.setSoLuong(soluong);
                                           phoneDTO.setImage(1);
                                           phoneDTO.setGia(gia);


                                           int check = sanPhamDAO.upSanPham(phoneDTO);
                                           if (check>0){

                                               listSP.clear();
                                               listSP.addAll(sanPhamDAO.getlistSP());
                                               notifyDataSetChanged();

                                               Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();

                                               dialog.dismiss();
                                           }else {
                                               Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();

                                               dialog.dismiss();
                                           }



                                       }

                                   }catch (Exception e){
                                       Toast.makeText(context, "Vui lòng Nhập đúng dịnh dạng     ", Toast.LENGTH_SHORT).show();



                                   }
                               }
                           });dialog.show();

                           return true;
                       } else if (item.getItemId()==R.id.select_xoa) {
                           SanPhamDAO sanPhamDAO1 = new SanPhamDAO(context);
                           int check = sanPhamDAO1.deleteRow_SP(phoneDTO);
                           if (check>0){
                               listSP.remove(phoneDTO);

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
        return listSP.size();
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenSP;
        TextView tv_hangSP;
        TextView tv_RomSP;
        TextView tv_giaSp;
        TextView tv_mauSacSp;
        TextView tv_soLuongsp;
        TextView tv_statusSP;
        ImageView img_anhSP;
        CardView cardViewSP;
        Button select_row_qlsp;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_statusSP = itemView.findViewById(R.id.tv_TrangthaiSP);
            tv_tenSP = itemView.findViewById(R.id.tv_tenSP);
            tv_hangSP = itemView.findViewById(R.id.tv_tenHangSP);
            tv_soLuongsp = itemView.findViewById(R.id.tv_soLuongSP);
            tv_RomSP = itemView.findViewById(R.id.tv_RomSP);
            tv_giaSp = itemView.findViewById(R.id.tv_GiaSP);
            tv_mauSacSp = itemView.findViewById(R.id.tv_MauSacSP);
            img_anhSP =itemView.findViewById(R.id.anh1);
//            cardViewSP = itemView.findViewById(R.id.cardViewSanPham);
            select_row_qlsp = itemView.findViewById(R.id.select_row_qlsp);


        }
    }

    @SuppressLint("Range")
    private String getTenLoaiSanPham(int id) {
        String tenLoaiSanPham = "vv";
        Cursor cursor = dbHelper1.getTenLoaiSanPhamById(id);
        if (cursor.moveToFirst()) {
            tenLoaiSanPham = cursor.getString(cursor.getColumnIndex("tenHang"));
        }
        cursor.close();
        return tenLoaiSanPham;
    }


}

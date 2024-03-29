package com.example.du_an1_qldt.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.R;
import com.example.du_an1_qldt.TaoDonHang;
import com.example.du_an1_qldt.TrangChuNguoiDung;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class DSSP_Adapter extends RecyclerView.Adapter< DSSP_Adapter.ViewHolder_DSSP   > {


    Context context;
    TrangChuNguoiDung tcnd;
    ArrayList<phone> listSP;
    SanPhamDAO dsDao;

    public DSSP_Adapter(Context context, ArrayList<phone> listSP) {
        this.context = context;
        this.listSP = listSP;
        dsDao= new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder_DSSP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_dssp,parent,false);
        ViewHolder_DSSP holder= new ViewHolder_DSSP(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_DSSP holder, int position) {


        phone dt = listSP.get(position);
        holder.tv_tenSP1.setText( String.valueOf(dt.getName()) );
        holder.tv_giaSPham.setText( String.valueOf( dt.getGia()));
        holder.tv_TrangthaiSP1.setText( String.valueOf(dt.getStatus() ));
holder.btnMuaNgay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, TaoDonHang.class);
        context.startActivity(intent);
    }
});

        phone phoneDTO = listSP.get(position);

        if (phoneDTO.getStatus()==1){
            holder.tv_TrangthaiSP1.setText("Còn hàng");
        }else {
            holder.tv_TrangthaiSP1.setText("Hết hàng");
        }

    }



    @Override
    public int getItemCount() {
        return listSP.size();
    }

    public class ViewHolder_DSSP extends RecyclerView.ViewHolder{
        TextView tv_tenSP1,tv_giaSPham,tv_TrangthaiSP1;
        ImageView anh1, btnThemvaoGiohang,btnMuaNgay;

        public ViewHolder_DSSP(@NonNull View itemView) {
            super(itemView);

            tv_tenSP1 = itemView.findViewById(R.id.tv_tenSP1);
            tv_giaSPham = itemView.findViewById(R.id.tv_giaSPham);
            tv_TrangthaiSP1 = itemView.findViewById(R.id.tv_TrangthaiSP1);
            anh1 = itemView.findViewById(R.id.anh1);
            btnThemvaoGiohang = itemView.findViewById(R.id.btnThemvaoGiohang);
            btnMuaNgay = itemView.findViewById(R.id.btnMuaNgay);

        }
    }


}

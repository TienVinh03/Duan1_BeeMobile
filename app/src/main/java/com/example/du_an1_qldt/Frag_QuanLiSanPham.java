package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an1_qldt.Adapter.SanPhamAdapter;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;
import java.util.List;

public class Frag_QuanLiSanPham extends Fragment {
    RecyclerView rc_QLSP;

    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    SanPhamAdapter sanPhamAdapter1;
    ArrayList<phone> listSP;
    Spinner spn_hangDT;
    dbHelper myDbHelper;

    private SwipeRefreshLayout swipeRefreshLayout;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_quan_li_san_pham,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_QLSP = view.findViewById(R.id.rc_QLSP);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        sanPhamDAO = new SanPhamDAO((FragMentContainer)getActivity());
        listSP = sanPhamDAO.getlistSP();
        sanPhamAdapter = new SanPhamAdapter((FragMentContainer)getActivity(),listSP);
        rc_QLSP.setAdapter(sanPhamAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_QLSP.setLayoutManager(linearLayoutManager);

        Button btn_add_sp = view.findViewById(R.id.btn_add_sp);
        myDbHelper = new dbHelper(getActivity());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rc_QLSP = view.findViewById(R.id.rc_QLSP);

                sanPhamDAO = new SanPhamDAO((FragMentContainer)getActivity());
                listSP = sanPhamDAO.getlistSP();
                sanPhamAdapter = new SanPhamAdapter((FragMentContainer)getActivity(),listSP);
                rc_QLSP.setAdapter(sanPhamAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                rc_QLSP.setLayoutManager(linearLayoutManager);

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        btn_add_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View v1=inflater.inflate(R.layout.dialog_add_sp,null);
                EditText tensp = v1.findViewById(R.id.edt_tenSP_add);
                EditText gia_dt = v1.findViewById(R.id.edt_giaDT_add);
                EditText rom_dt = v1.findViewById(R.id.edt_romDT_add);
                EditText soluong_dt = v1.findViewById(R.id.edt_soLuongDT_add);
                spn_hangDT =v1.findViewById(R.id.spin_tenHangDT_add);
                Spinner spn_mausac = v1.findViewById(R.id.spin_mauSac_add);
                TextView tv_trangThai = v1.findViewById(R.id.tv_trangThai_add);
                Button btn_save_add = v1.findViewById(R.id.btn_addsp_add);





                String[] items_mausac = new String[]{"Xám", "Trắng","Đen","Tím"};

                ArrayAdapter<String> adapter_mausac = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items_mausac);
                adapter_mausac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spn_mausac.setAdapter(adapter_mausac);







                ArrayList<String> tenHangDT = new ArrayList<>();

                Cursor c = myDbHelper.getTenLoaiSanPham();
                if (c.getCount()>0){
                    c.moveToFirst();
                    do {

                        @SuppressLint("Range") String tenHangSanPham = c.getString(c.getColumnIndex("tenHang"));
                        tenHangDT.add(tenHangSanPham);
                    }while (c.moveToNext());
                }



                ArrayAdapter<String> adapter_hangDt=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,tenHangDT);
                adapter_hangDt.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spn_hangDT.setAdapter(adapter_hangDt);



                sanPhamDAO = new SanPhamDAO(getActivity());
                listSP =sanPhamDAO.getlistSP();
                sanPhamAdapter1= new SanPhamAdapter((FragMentContainer)getActivity(),listSP);
                phone phoneDTO = new phone();

                builder.setView(v1);
                builder.setTitle("                              Thêm điện thoại");

                Dialog dialog = builder.create();


                btn_save_add.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(View v) {

                        try {
                            int rom = Integer.parseInt(rom_dt.getText().toString());
                            int gia = Integer.parseInt(gia_dt.getText().toString());
                            int soluong = Integer.parseInt(soluong_dt.getText().toString());


                            if (TextUtils.isEmpty(tensp.getText().toString())||TextUtils.isEmpty(rom_dt.getText().toString())||TextUtils.isEmpty(gia_dt.getText().toString())||TextUtils.isEmpty(soluong_dt.getText().toString())){
                                Toast.makeText(getActivity(), "Vui lòng Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else if (!(rom==128||rom==256||rom==512)) {
                                Toast.makeText(getActivity(), "Vui lòng Nhập đúng định dạng của Rom", Toast.LENGTH_SHORT).show();

                            } else if (gia<0) {
                                Toast.makeText(getActivity(), "Gía không phù hợp", Toast.LENGTH_SHORT).show();

                            }else if (soluong<0) {
                                Toast.makeText(getActivity(), "Số lượng không phù hợp", Toast.LENGTH_SHORT).show();

                            }else {
                                String ten= tensp.getText().toString();
                                String tenHang = (String) spn_hangDT.getSelectedItem();
                                String mausac = (String) spn_mausac.getSelectedItem();
                                String trangthai = tv_trangThai.getText().toString();



                                phoneDTO.setName(ten);
                                phoneDTO.setId_Hang(spn_hangDT.getSelectedItemPosition());
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


                                int check = sanPhamDAO.addSanPham(phoneDTO);
                                if (check>0){

                                    listSP.clear();
                                    listSP.addAll(sanPhamDAO.getlistSP());
                                    sanPhamAdapter1.notifyDataSetChanged();

                                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                }



                            }

                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Vui lòng Nhập đúng dịnh dạng     ", Toast.LENGTH_SHORT).show();



                        }


                    }
                });

                dialog.show();


            }
        });

    }
}
package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
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

    ArrayList<phone> listSP;
    Spinner spn_hangDT;
    dbHelper myDbHelper;

    private ImageView mainImage;
    private int[] imageResources = {R.drawable.k40_gaming,R.drawable.k50, R.drawable.iphone5, R.drawable.iphone3};
    private int currentImageIndex = 0;

    SQLiteDatabase database;
    private InputMethodManager inputMethodManager;
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
        EditText edt_search_qlsp = view.findViewById(R.id.edt_search_qlsp);
        ImageView btntimkiem = view.findViewById(R.id.btntimkiem);
        inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        myDbHelper = new dbHelper(getActivity());
        database = myDbHelper.getReadableDatabase();

        edt_search_qlsp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    listSP.clear();

                    RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.rc_QLSP);
                    if (edt_search_qlsp.length() > 0) {
                        String ten = edt_search_qlsp.getText().toString().trim();
                        listSP = sanPhamDAO.TimKiemSanPham(ten);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                        SanPhamAdapter adapter_sanPhamManHinhChinh = new SanPhamAdapter( getContext(), listSP,sanPhamDAO);
                        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    }
                    return true;
                }
                return false;
            }
        });
        edt_search_qlsp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    listSP.clear();

                    RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.rc_QLSP);
                    String ten = edt_search_qlsp.getText().toString().trim();

                    if (ten.equals("")) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                        SanPhamDAO sanPhamDAO2 = new SanPhamDAO(getContext());
                        listSP = (ArrayList<phone>) sanPhamDAO.getlistSP();
                        SanPhamAdapter adapter_sanPhamManHinhChinh = new SanPhamAdapter( getContext(), listSP,sanPhamDAO2);
                        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                    } else {
                        listSP.clear();
                        recycle_sanphammanhinhchinh = view.findViewById(R.id.rc_QLSP);
                        if (edt_search_qlsp.length() > 0) {
                            listSP = sanPhamDAO.TimKiemSanPham(ten);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                            SanPhamAdapter adapter_sanPhamManHinhChinh = new SanPhamAdapter( getContext(), listSP,sanPhamDAO);
                            recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        }
                    }

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                    recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                    SanPhamAdapter adapter_sanPhamManHinhChinh = new SanPhamAdapter( getContext(), listSP,sanPhamDAO);
                    recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSP.clear();
                RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.rc_QLSP);
                String ten = edt_search_qlsp.getText().toString().trim();
                if (ten.equals("")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                    recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                    SanPhamDAO sanPhamDAO1 = new SanPhamDAO(getContext());
                    listSP = (ArrayList<phone>) sanPhamDAO1.getlistSP();
                    SanPhamAdapter adapter_sanPhamManHinhChinh = new SanPhamAdapter( getContext(), listSP,sanPhamDAO1);
                    recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                } else {
                    listSP.clear();
                    recycle_sanphammanhinhchinh = view.findViewById(R.id.rc_QLSP);
                    if (edt_search_qlsp.length() > 0) {
                        listSP = sanPhamDAO.TimKiemSanPham(ten);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                        SanPhamAdapter adapter_sanPhamManHinhChinh = new SanPhamAdapter(getContext(), listSP,sanPhamDAO);
                        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    }
                }
            }
        });





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
                sanPhamAdapter.notifyDataSetChanged();

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

                 mainImage =v1.findViewById(R.id.anh5);

                 mainImage.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         changeImage(v1);

                     }
                 });













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
                sanPhamAdapter= new SanPhamAdapter((FragMentContainer)getActivity(),listSP);
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

                            } else if (gia<=0) {
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
                                if (currentImageIndex==0){
                                    phoneDTO.setImage(1);
                                    currentImageIndex=0;
                                } else if (currentImageIndex==1) {
                                    phoneDTO.setImage(2);
                                    currentImageIndex=0;
                                } else if (currentImageIndex==2) {
                                    phoneDTO.setImage(3);
                                    currentImageIndex=0;
                                }else {
                                    phoneDTO.setImage(4);
                                    currentImageIndex=0;
                                }

                                phoneDTO.setGia(gia);




                                int check = sanPhamDAO.addSanPham(phoneDTO);
                                if (check>0){

                                    listSP.clear();
                                    listSP.addAll(sanPhamDAO.getlistSP());
                                    sanPhamAdapter.notifyDataSetChanged();

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


    public void changeImage(View view) {
        // Chuyển sang ảnh tiếp theo trong mảng
        currentImageIndex = (currentImageIndex + 1) % imageResources.length;
        mainImage.setImageResource(imageResources[currentImageIndex]);
    }




}
package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class FragMentContainer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FragmentContainerView fragmentContainerView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_ment_container);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        bottomNavigationView = findViewById(R.id.bottom_admin);
        fragmentContainerView = findViewById(R.id.fragMentContainer);
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");
        if (!loaitaikhoan.equals("admin")){
            Menu menu = bottomNavigationView.getMenu();
            menu.findItem(R.id.work).setVisible(false);

        }else {
            Menu menu = bottomNavigationView.getMenu();
            menu.findItem(R.id.donMua).setVisible(false);
        }

        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
        navigationView.setItemIconSize(80);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment1=null;
             if (item.getItemId()==R.id.home){
                 setTitle("Màn hình chính");
                 toolbar.setTitle("Trang chủ(admin)");
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new TrangChuAdmin()).addToBackStack(null).commit();
             } else if (item.getItemId()==R.id.work) {
                 setTitle("Quản lí Hóa đơn");
                 toolbar.setTitle("Quản lí Hóa đơn");
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_QuanLiDonHang()).addToBackStack(null).commit();
             } else if (item.getItemId()==R.id.donMua) {
                 setTitle("Giỏ hàng");
                 toolbar.setTitle("Giỏ hàng");
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer,new Frag_GioHang()).addToBackStack(null).commit();
             } else if (item.getItemId()==R.id.info) {
                 setTitle("Màn hình chính");
                 toolbar.setTitle("Trang chủ(admin)");
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_Info()).addToBackStack(null).commit();

             }
             drawer.close();


                return false;
            }
        });

        Fragment TrangChuNguoiDung;
        TrangChuNguoiDung = new TrangChuNguoiDung();





        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(FragMentContainer.this, drawer, toolbar, 0, 0);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        if (loaitaikhoan.equals("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.QLDM).setVisible(false);
            menu.findItem(R.id.manChinhUser).setVisible(false);
            menu.findItem(R.id.GIOHANG).setVisible(false);
            menu.findItem(R.id.dsVoucher).setVisible(false);
            menu.findItem(R.id.dsSanPham).setVisible(false);
            repLaceFragment(TrangChuAdmin.newInstance());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new TrangChuAdmin()).commit();
        }else if(loaitaikhoan.equals("user")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.QL_Voucher).setVisible(false);
            menu.findItem(R.id.QLSP).setVisible(false);
            menu.findItem(R.id.QLKH).setVisible(false);
            menu.findItem(R.id.manChinhAdmin).setVisible(false);
            menu.findItem(R.id.QLHANG).setVisible(false);
            menu.findItem(R.id.QLHD).setVisible(false);
            menu.findItem(R.id.ThongKeDoanhThu).setVisible(false);
            menu.findItem(R.id.ThongKeTop).setVisible(false);
            repLaceFragment((TrangChuNguoiDung));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new TrangChuNguoiDung()).commit();
        }


//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                Fragment fr;
//                if (item.getItemId()==R.id.QL_Voucher){
//
//                    fr=new Frag_QuanLiVoucher();
//                }else {
//                    fr=new Frag_QuanLiSanPham();
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer,fr).commit();
//                return true;
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itDangXuat){
            startActivity(new Intent(this, MainActivity2.class));
            finish();
        }
        else if (item.getItemId()==R.id.manChinhAdmin) {
            setTitle("Màn hình chính");
            toolbar.setTitle("Trang chủ(admin)");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new TrangChuAdmin()).addToBackStack(null).commit();
        }
        else if (item.getItemId()==R.id.thongTin) {
            setTitle("Thông tin ");
            toolbar.setTitle("Thông tin cá nhân");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_Info()).addToBackStack(null).commit();
        }
        else if (item.getItemId()==R.id.manChinhUser) {
            setTitle("Màn hình chính");
            toolbar.setTitle("Trang chủ(user)");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new TrangChuNguoiDung()).addToBackStack(null).commit();
        }
        else if (item.getItemId()==R.id.QLSP) {
            setTitle("Quản lí sản phẩm");
            toolbar.setTitle("Quản lí sản phẩm");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_QuanLiSanPham()).addToBackStack(null).commit();
        }
        else if (item.getItemId()==R.id.QL_Voucher) {
            setTitle("Quản lí Voucher");
            toolbar.setTitle("Quản lí Voucher");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_QuanLiVoucher()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.dsVoucher) {
            setTitle("Danh sách voucher Voucher");
            toolbar.setTitle("Danh sách Voucher");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_DanhSachVoucher()).addToBackStack(null).commit();
        }
        else if (item.getItemId()==R.id.QLHD) {
            setTitle("Quản lí đơn hàng");
            toolbar.setTitle("Quản lí đơn hàng");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_QuanLiDonHang()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.QLKH) {
            setTitle("Quản lí khách hàng");
            toolbar.setTitle("Quản lí khách hàng");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_QuanLiKhachHang()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.ThongKeDoanhThu) {
            setTitle("Thống kê theo doanh thu");
            toolbar.setTitle("Thống kê theo doanh thu");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_ThongKe()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.ThongKeTop) {
            setTitle("Top 10 điện thoại bán nhiều nhất");
            toolbar.setTitle("Top 10 điện thoại bán nhiều nhất");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_ThongKe()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.QLHANG) {
            setTitle("Quản lí hãng điện thoại");
            toolbar.setTitle("Quản lí hãng điện thoại");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer, new Frag_QuanLiHangDT()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.itDoiMatKhau) {
            setTitle("");
         startActivity(new Intent(this,DoiMatKhau.class));
         finish();
        }else if (item.getItemId()==R.id.GIOHANG) {
            setTitle("Giỏ hàng");
            toolbar.setTitle("Giỏ hàng");
           getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer,new Frag_GioHang()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.QLDM) {
            setTitle("Đơn mua gần đây");
            toolbar.setTitle("Đơn mua gần đây");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer,new Frag_DonMua()).addToBackStack(null).commit();
        }else if (item.getItemId()==R.id.dsSanPham) {
            setTitle("Danh sách sản phẩm");
            toolbar.setTitle("Danh sách sản phẩm");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMentContainer,new Frag_DanhSachSP()).addToBackStack(null).commit();
        }
        drawer.close();
        return false;
    }
    public void repLaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMentContainer, fragment);
        toolbar.setTitle("Trang chủ");
        fragmentTransaction.commit();
    }









}
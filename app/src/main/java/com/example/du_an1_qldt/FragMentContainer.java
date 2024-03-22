package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
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

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });



        if (savedInstanceState == null) {
            repLaceFragment(TrangChuAdmin.newInstance());
            setTitle("Màn hình chính (Admin)");
            toolbar.setTitle("Màn hình chính");
            toolbar.setSubtitle("Admin");

        }

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(FragMentContainer.this, drawer, toolbar, 0, 0);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");

        if (!loaitaikhoan.equals("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.QL_Voucher).setVisible(false);
            menu.findItem(R.id.QLSP).setVisible(false);
            menu.findItem(R.id.QLKH).setVisible(false);
            menu.findItem(R.id.manChinhAdmin).setVisible(false);
            menu.findItem(R.id.QLHANG).setVisible(false);
            menu.findItem(R.id.QLHD).setVisible(false);
            menu.findItem(R.id.ThongKeDoanhThu).setVisible(false);
            menu.findItem(R.id.ThongKeTop).setVisible(false);
        }else {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.manChinhNguoiDung).setVisible(false);
            menu.findItem(R.id.QLDM).setVisible(false);
            menu.findItem(R.id.GIOHANG).setVisible(false);

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
        return false;
    }
    public void repLaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMentContainer, fragment);
        fragmentTransaction.commit();
    }
}
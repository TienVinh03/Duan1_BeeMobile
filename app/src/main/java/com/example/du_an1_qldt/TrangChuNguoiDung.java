package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.Adapter.DSSP_Adapter;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class TrangChuNguoiDung extends Fragment {
    ViewPager viewPager;
    SlideAdapter slideAdapter;


    RecyclerView rv_mobile;
    DSSP_Adapter dsAdapter;
    SanPhamDAO spDao;
    ArrayList<phone> list;


    int[] images = {R.drawable.anh_slide1, R.drawable.anh_slide2, R.drawable.anh_slide3, R.drawable.anh_slide4, R.drawable.anh_slide5};

    private static final long SLIDE_DELAY = 3000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            int nextSlide = viewPager.getCurrentItem() + 1;
            if (nextSlide >= images.length) {
                nextSlide = 0;
            }
            viewPager.setCurrentItem(nextSlide, true);
            // Lặp lại việc gửi tin nhắn sau mỗi khoảng thời gian
            handler.postDelayed(this, SLIDE_DELAY);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_trang_chu_nguoi_dung,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardView cardView = view.findViewById(R.id.cardView_nguoidung);
        cardView.setCardBackgroundColor(Color.WHITE);
        cardView.setRadius(20);

        cardView.setCardElevation(8);
        viewPager = view.findViewById(R.id.viewPager_nguoidung);
        slideAdapter = new SlideAdapter(getActivity(), images);
        viewPager.setAdapter(slideAdapter);

        handler.postDelayed(runnable, SLIDE_DELAY);


        rv_mobile= view.findViewById(R.id.rv_mobile);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        spDao=new SanPhamDAO(getContext());
        rv_mobile.setLayoutManager(linearLayoutManager);
        list= spDao.getlistSP();
        dsAdapter= new DSSP_Adapter(getContext(),list);
        rv_mobile.setAdapter(dsAdapter);


    }
    public static TrangChuAdmin newInstance() {
        TrangChuAdmin fragment = new TrangChuAdmin();
        return fragment;
    }
    public void onDestroy() {
        super.onDestroy();
        // Dừng việc gửi tin nhắn khi activity bị hủy
        handler.removeCallbacks(runnable);
    }


}
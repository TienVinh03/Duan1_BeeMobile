package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class TrangChuAdmin extends Fragment {
    ViewPager viewPager;
    SlideAdapter slideAdapter;
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
        View v = inflater.inflate(R.layout.activity_trang_chu_admin,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardView cardView = view.findViewById(R.id.cardViewTrangChu);
        cardView.setCardBackgroundColor(Color.WHITE);
        cardView.setRadius(20);

        cardView.setCardElevation(8);
        viewPager = view.findViewById(R.id.viewPager);
        slideAdapter = new SlideAdapter(getActivity(), images);
        viewPager.setAdapter(slideAdapter);

        handler.postDelayed(runnable, SLIDE_DELAY);






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
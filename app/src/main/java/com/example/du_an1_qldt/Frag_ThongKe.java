package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an1_qldt.DAO.OrderDetailDao;
import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.OrderDetail;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Frag_ThongKe extends Fragment {

    BarChart barChart;
    OrderDetail orderDetail;
    OrderDetailDao orderDetailDao;
    dbHelper dbHelper1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_thong_ke,container,false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barChart = view.findViewById(R.id.barChart);
        dbHelper1 = new dbHelper(getActivity());



        setupChart();
//
//        ArrayList<BarEntry> barEntries = new ArrayList<>();
//        for (int i=1;i<5;i++){
//            float value = (float) (i*5.0);
//
//            BarEntry barEntry = new BarEntry(i,value);
//
//            barEntries.add(barEntry);
//        }
//
//        BarDataSet barDataSet = new BarDataSet(barEntries,"DoanhThu");
//        barDataSet.setColor(Color.BLUE);
//        barDataSet.setDrawValues(false);
//        barChart.setData(new BarData(barDataSet));
//
//        barChart.animateY(3000);
//        barChart.getDescription().setText("Doanh Thu Chart");
//        barChart.getDescription().setTextColor(Color.RED);






    }
    private void setupChart() {
        ArrayList<BarEntry> entries1 = new ArrayList<>();

        orderDetailDao = new OrderDetailDao(getActivity());


        entries1.add(new BarEntry( 0, (float)orderDetailDao.getTotalPriceForMonth2()));
        entries1.add(new BarEntry(1, (float)orderDetailDao.getTotalPriceForMonth3()));
//        entries1.add(new BarEntry(2, (float)orderDetailDao.getTotalPriceForMonth4()));
        entries1.add(new BarEntry(2, (float)orderDetailDao.getTotalAmount()));

        BarDataSet dataSet = new BarDataSet(entries1, "Doanh thu");
        BarData data = new BarData(dataSet);
        barChart.setData(data);


        barChart.invalidate();



        // Đặt giá trị cho trục X
        final  String[] months1 = new String[]{"Tháng 2","Tháng 3","Tổng cộng"};

        XAxis xAxis = barChart.getXAxis();
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months1);
        xAxis.setAxisLineColor(Color.YELLOW);
        xAxis.setAxisLineWidth(5);
        xAxis.setDrawLabels(true);


        barChart.getXAxis().setValueFormatter(formatter);





        // Thiết lập chiều cao của cột theo số tiền
        dataSet.setDrawValues(true);




        dataSet.setColor(Color.BLUE);
        dataSet.setLabel("Tiền");
        barChart.getDescription().setText("Doanh thu");
        barChart.animateY(2000);
        data.setBarWidth(0.7F);

//        barChart.setHighlightFullBarEnabled(true);
        barChart.setFitBars(true);






    }
}
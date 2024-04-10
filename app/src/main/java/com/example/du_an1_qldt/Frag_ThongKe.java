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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    Spinner spn_loc;
    TextView tongDoanhThu;
    TextView tongDonHang;
    TextView cotcaoNhat;
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

        tongDoanhThu = view.findViewById(R.id.toongDoanhThu);
        tongDonHang = view.findViewById(R.id.toongDonHang);
        cotcaoNhat = view.findViewById(R.id.cotCaoNhat);
        barChart = view.findViewById(R.id.barChart);
        dbHelper1 = new dbHelper(getActivity());
        spn_loc = view.findViewById(R.id.spn_loc);



        String[] intervals = {"Theo tháng", "Theo quý", "Theo năm"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, intervals);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_loc.setAdapter(adapter);
        spn_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedInterval = parent.getItemAtPosition(position).toString();
                if (selectedInterval.equals("Theo tháng")){
                    setupChartOrderMonth();
                } else if (selectedInterval.equals("Theo quý")) {
                    setupChartOrderQuarter();

                } else if (selectedInterval.equals("Theo năm")) {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
       private void setupChartOrderMonth() {


           orderDetailDao = new OrderDetailDao(getActivity());

           ArrayList<BarEntry> entries = new ArrayList<>();
           entries.add(new BarEntry(0, (float)orderDetailDao.getTotalPriceForMonth1()));
           entries.add(new BarEntry(1, (float)orderDetailDao.getTotalPriceForMonth2()));
           entries.add(new BarEntry(2, (float)orderDetailDao.getTotalPriceForMonth3()));
           entries.add(new BarEntry(3, (float)orderDetailDao.getTotalPriceForMonth4()));
//           entries.add(new BarEntry(4, (float)orderDetailDao.getTotalPriceForMonth5()));
//           entries.add(new BarEntry(5, (float)orderDetailDao.getTotalPriceForMonth6()));
//           entries.add(new BarEntry(6, (float)orderDetailDao.getTotalPriceForMonth7()));
//           entries.add(new BarEntry(7, (float)orderDetailDao.getTotalPriceForMonth8()));
//           entries.add(new BarEntry(8, (float)orderDetailDao.getTotalPriceForMonth9()));
//           entries.add(new BarEntry(9, (float)orderDetailDao.getTotalPriceForMonth10()));
//           entries.add(new BarEntry(10, (float)orderDetailDao.getTotalPriceForMonth11()));
//           entries.add(new BarEntry(11, (float)orderDetailDao.getTotalPriceForMonth12()));

           BarDataSet dataSet = new BarDataSet(entries, "Revenue");
           dataSet.setColor(Color.rgb(255, 102, 0)); // Màu của cột
           dataSet.setValueTextSize(5);

           ArrayList<String> labels = new ArrayList<>();
           labels.add("Jan");
           labels.add("Feb");
           labels.add("Mar");
           labels.add("Apr");
//           labels.add("May");
//           labels.add("Jun");
//           labels.add("Jul");
//           labels.add("Aug");
//           labels.add("Sep");
//           labels.add("Oct");
//           labels.add("Nov");
//           labels.add("Dec");

           BarData data = new BarData(dataSet);
           data.setBarWidth(0.7f);
           barChart.setData(data);
           barChart.getXAxis().setValueFormatter(new MyXAxisValueFormatter(labels));
           barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
           barChart.getXAxis().setGranularity(1f);
           barChart.getXAxis().setGranularityEnabled(true);
           barChart.getAxisLeft().setAxisMinimum(0);
           barChart.getAxisRight().setAxisMinimum(0);
           barChart.getXAxis().setAxisMaximum(4);
           barChart.getDescription().setEnabled(false);
           barChart.setDrawGridBackground(false);
           barChart.setFitBars(true); // Căn chỉnh cột
           barChart.animateY(1000);
           barChart.invalidate();


           tongDoanhThu.setText("Tổng doanh thu của các tháng : "+String.valueOf(orderDetailDao.getTotalAmount())+" VNĐ");
           tongDonHang.setText("Tổng đơn hàng của các tháng : "+String.valueOf(orderDetailDao.getNumberOfOrdersInYear2024())+" đơn");
           cotcaoNhat.setText("Tháng có doanh thu cao nhất : Tháng " +String.valueOf(orderDetailDao.getMonthWithHighestTotalPrice())+" - Tổng ĐH trong tháng này: "+orderDetailDao.getTotalOrdersInMonthWithHighestRevenue()+" đơn");

}
    private void setupChartOrderQuarter() {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, (float) orderDetailDao.getTotalPriceForQuarter1InYear(1,2024)));
        entries.add(new BarEntry(1,  (float) orderDetailDao.getTotalPriceForQuarter2InYear(2,2024)));
        entries.add(new BarEntry(2,  (float) orderDetailDao.getTotalPriceForQuarter3InYear(3,2024)));
        entries.add(new BarEntry(3,  (float) orderDetailDao.getTotalPriceForQuarter4InYear(4,2024)));

        BarDataSet dataSet = new BarDataSet(entries, "Revenue");
        dataSet.setColor(Color.rgb(255, 102, 0)); // Màu của cột

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Quý 1");
        labels.add("Quý 2");
        labels.add("Quý 3");
        labels.add("Quý 4");

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new MyXAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setFitBars(true); // Căn chỉnh cột
        barChart.animateY(1000);
        barChart.invalidate();

        tongDoanhThu.setText("Tổng doanh thu của các qúy : "+String.valueOf(orderDetailDao.getTotalAmount())+" VNĐ");
        tongDonHang.setText("Tổng đơn hàng của các quý : "+String.valueOf(orderDetailDao.getNumberOfOrdersInYear2024())+" đơn");
        cotcaoNhat.setText("Qúy có doanh thu cao nhất : Qúy " +String.valueOf(orderDetailDao.getQuarterWithHighestRevenue()) +" - Tổng ĐH trong quý này : "+orderDetailDao.getTotalOrdersInQuarterWithHighestRevenue()+" đơn");

    }
}
package com.example.du_an1_qldt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an1_qldt.DAO.OrderDetailDao;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.OrderDetail;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Frag_ThongKe extends Fragment {

    BarChart barChart;
    OrderDetail orderDetail;
    OrderDetailDao orderDetailDao;
    Spinner spn_loc;
    TextView textVND;
    TextView tongDoanhThu;
    TextView sumDH;
    TextView tongDonHang;
    TextView cotcaoNhat;
    SanPhamDAO sanPhamDAO;
    TextView xemthem;
    TextView sumSP;
    dbHelper dbHelper1;
    TextView sumMoc;
    private Calendar calendarStart, calendarEnd;
    TextView startDay;
    TextView endDay;
    TextView btnStartDay;
    LinearLayout thongsoBar;
    TextView btnEndDay;
    LinearLayout linearLayout;
    Button btnXacNhanThongKe;
    private SQLiteDatabase db;




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
        sanPhamDAO = new SanPhamDAO(getActivity());
        barChart = view.findViewById(R.id.barChart);
        dbHelper1 = new dbHelper(getActivity());
        spn_loc = view.findViewById(R.id.spn_loc);
        startDay = view.findViewById(R.id.startDay);
        endDay = view.findViewById(R.id.endDay);
        xemthem = view.findViewById(R.id.xemthem);
        btnEndDay = view.findViewById(R.id.btnendDay);
        sumSP = view.findViewById(R.id.sumSP);
        btnStartDay = view.findViewById(R.id.btnstartDay);
        linearLayout = view.findViewById(R.id.linearThongKe);
        sumMoc = view.findViewById(R.id.sumMoc);
        textVND = view.findViewById(R.id.textVND);
        calendarStart = Calendar.getInstance();
        calendarEnd = Calendar.getInstance();
        btnXacNhanThongKe = view.findViewById(R.id.btnXacNhanThongKe);
        sumDH= view.findViewById(R.id.sumDH);

        thongsoBar = view.findViewById(R.id.thongsoBar);




        String[] intervals = {"Theo mốc","Theo tháng", "Theo quý", "Theo năm"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, intervals);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_loc.setAdapter(adapter);
        spn_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedInterval = parent.getItemAtPosition(position).toString();
                if (selectedInterval.equals("Theo mốc")){
                    thongsoBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    barChart.setVisibility(View.GONE);
                    textVND.setVisibility(View.GONE);

                    orderDetailDao = new OrderDetailDao(getActivity());

                    btnStartDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDatePickerDialog(startDay);
                        }
                    });
                    btnEndDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDatePickerDialog(endDay);
                        }
                    });


                        btnXacNhanThongKe.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String DateStart = startDay.getText().toString();
                                String DateEnd = endDay.getText().toString();
                                double sum = orderDetailDao.getTotalPriceOfOrdersBetweenDates(DateStart,DateEnd);
                                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
                                String formattedRevenueDay = currencyFormat.format(sum);
                                sumMoc.setText( String.valueOf(formattedRevenueDay) + "VNĐ");
                                sumDH.setText(String.valueOf(orderDetailDao.getTotalOrdersBetweenDates(DateStart,DateEnd)+1));

                                sanPhamDAO.getProductNameById(orderDetailDao.getMostSoldProductIDBetweenDates(DateStart,DateEnd));
                                sumSP.setText(sanPhamDAO.getProductNameById(orderDetailDao.getMostSoldProductIDBetweenDates(DateStart,DateEnd)));


                               Bundle bundle = new Bundle();
                                xemthem.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       bundle.putString("startDate",DateStart);
                                       bundle.putString("endDate",DateEnd);
                                       Frag_XemDonHangByDate fragXemDH= new Frag_XemDonHangByDate();
                                        fragXemDH.setArguments(bundle);
                                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                        transaction.replace(R.id.fragMentContainer,fragXemDH).commit();
                                    }
                                });
                            }
                        });






                }
                else if (selectedInterval.equals("Theo tháng")) {
                    setupChartOrderMonth();
                    linearLayout.setVisibility(View.GONE);
                    barChart.setVisibility(View.VISIBLE);
                    textVND.setVisibility(View.VISIBLE);
                    thongsoBar.setVisibility(View.VISIBLE);

                }else if (selectedInterval.equals("Theo quý")) {
                    setupChartOrderQuarter();
                    linearLayout.setVisibility(View.GONE);
                    barChart.setVisibility(View.VISIBLE);
                    textVND.setVisibility(View.VISIBLE);
                    thongsoBar.setVisibility(View.VISIBLE);

                } else if (selectedInterval.equals("Theo năm")) {
                    setupChartOrderYear();
                    linearLayout.setVisibility(View.GONE);
                    barChart.setVisibility(View.VISIBLE);
                    thongsoBar.setVisibility(View.VISIBLE);
                    textVND.setVisibility(View.VISIBLE);
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
           double sum=orderDetailDao.getTotalAmount();

           NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
           String formattedRevenue = currencyFormat.format(sum);

           tongDoanhThu.setText("Tổng doanh thu của các tháng : "+formattedRevenue+" VNĐ");
           tongDonHang.setText("Tổng đơn hàng của các tháng : "+String.valueOf(orderDetailDao.getNumberOfOrdersInYear2024())+" đơn");
           cotcaoNhat.setText("Tháng có doanh thu cao nhất : " +String.valueOf(orderDetailDao.getMonthWithHighestTotalPrice())+" - Tổng ĐH trong tháng này: "+orderDetailDao.getTotalOrdersInMonthWithHighestRevenue()+" đơn");

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
        double sum=orderDetailDao.getTotalAmount();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedRevenue = currencyFormat.format(sum);

        tongDoanhThu.setText("Tổng doanh thu của các qúy : "+formattedRevenue+" VNĐ");
        tongDonHang.setText("Tổng đơn hàng của các quý : "+String.valueOf(orderDetailDao.getNumberOfOrdersInYear2024())+" đơn");
        cotcaoNhat.setText("Qúy có doanh thu cao nhất : " +String.valueOf(orderDetailDao.getQuarterWithHighestRevenue()) +" - Tổng ĐH trong quý này : "+orderDetailDao.getTotalOrdersInQuarterWithHighestRevenue()+" đơn");

    }
    private void setupChartOrderYear() {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, (float)orderDetailDao.getTotalPriceForYear2022()));
        entries.add(new BarEntry(1,  (float) orderDetailDao.getTotalPriceForYear2023()));
        entries.add(new BarEntry(2,  (float) orderDetailDao.getTotalPriceForYear2024()));


        BarDataSet dataSet = new BarDataSet(entries, "Revenue");
        dataSet.setColor(Color.rgb(255, 102, 0)); // Màu của cột

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Năm 2022");
        labels.add("Năm 2023");
        labels.add("Năm 2024");


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

        double sum=orderDetailDao.getTotalRevenueForThreeYears();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedRevenue = currencyFormat.format(sum);


        tongDoanhThu.setText("Tổng doanh thu của 3 năm gần nhất : "+formattedRevenue+" VNĐ");
        tongDonHang.setText("Tổng đơn hàng của các năm : "+String.valueOf(orderDetailDao.getTotalOrdersForThreeYears())+" đơn");
        cotcaoNhat.setText("Năm có doanh thu cao nhất : " +String.valueOf(orderDetailDao.getYearWithHighestRevenue()) +" - Tổng ĐH trong năm này : "+orderDetailDao.getTotalOrdersInYearWithHighestRevenue()+" đơn");

    }
    private void showDatePickerDialog(final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dateFormat = "yyyy-MM-dd"; // Định dạng ngày tháng năm
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
                textView.setText(simpleDateFormat.format(selectedDate.getTime()));

            }
        };
        new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
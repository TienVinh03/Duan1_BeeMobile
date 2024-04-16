package com.example.du_an1_qldt.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderDetailDao {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public OrderDetailDao (Context context){
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();

    }

    public ArrayList<OrderDetail> getlistOrderDetail(){
        ArrayList<OrderDetail> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from OderDetail",null,null);
        if (c.getCount()>0){
            c.moveToFirst();
            do {

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(c.getInt(0));
                orderDetail.setIdProduct(c.getInt(1));
                orderDetail.setIdDonHang(c.getInt(2));
                orderDetail.setPrice(c.getInt(4));
                orderDetail.setQuantity(c.getInt(3));
                list.add(orderDetail);



            }while (c.moveToNext());
        }
        return list;
    }
    public double getTotalAmount() {
        double total = 0;

        Cursor cursor = db.rawQuery("SELECT SUM(giaTien*soLuong) FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%Y',Oder.date) = '2024'", null);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth1() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '01' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth5() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '05' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth3() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '03' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth4() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '04' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth2() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '02' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth6() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '06' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth8() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '08' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth7() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '07' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth9() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '9' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth10() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '10' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth11() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '11' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth12() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '12' and strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForDay() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%d',Oder.date) = '10' and strftime('%m',Oder.date)='04'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public int getNumberOfOrdersInYear2024() {
        int numberOfOrders = 0;

        String query = "SELECT COUNT(*) AS number_of_orders FROM Oder WHERE strftime('%Y',Oder.date) = '2024'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            numberOfOrders = cursor.getInt(cursor.getColumnIndex("number_of_orders"));
        }
        cursor.close();

        return numberOfOrders;
    }
    @SuppressLint("Range")
    public String getMonthWithHighestTotalPrice() {
        String monthWithHighestTotalPrice = "";

        String query = "SELECT strftime('%m',Oder.date) AS month, "
                + "SUM(OderDetail.giaTien) AS total_price "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang GROUP BY month "
                + "ORDER BY total_price DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            monthWithHighestTotalPrice = cursor.getString(cursor.getColumnIndex("month"));
        }
        cursor.close();

        return monthWithHighestTotalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForQuarter1InYear(int quarter, int year) {
        double totalPrice = 0;

        // Tính ra tháng bắt đầu và kết thúc của quý
        int startMonth = (quarter - 1) * 3 + 1; // Tháng bắt đầu của quý
        int endMonth = startMonth + 2; // Tháng kết thúc của quý

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) BETWEEN '01' AND '03'"
                + " AND strftime('%Y', Oder.date) = '" + year + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForQuarter2InYear(int quarter, int year) {
        double totalPrice = 0;

        // Tính ra tháng bắt đầu và kết thúc của quý
        int startMonth = (quarter - 1) * 3 + 1; // Tháng bắt đầu của quý
        int endMonth = startMonth + 2; // Tháng kết thúc của quý

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) BETWEEN '04' AND '06'"
                + " AND strftime('%Y', Oder.date) = '" + year + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForQuarter3InYear(int quarter, int year) {
        double totalPrice = 0;

        // Tính ra tháng bắt đầu và kết thúc của quý
        int startMonth = (quarter - 1) * 3 + 1; // Tháng bắt đầu của quý
        int endMonth = startMonth + 2; // Tháng kết thúc của quý

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) BETWEEN '07' AND '09'"
                + " AND strftime('%Y', Oder.date) = '" + year + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForQuarter4InYear(int quarter, int year) {
        double totalPrice = 0;

        // Tính ra tháng bắt đầu và kết thúc của quý
        int startMonth = (quarter - 1) * 3 + 1; // Tháng bắt đầu của quý
        int endMonth = startMonth + 2; // Tháng kết thúc của quý

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) BETWEEN '10' AND '12'"
                + " AND strftime('%Y', Oder.date) = '" + year + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }

    @SuppressLint("Range")
    public int getQuarterWithHighestRevenue() {
        int highestRevenueQuarter = 0;

        String query = "SELECT ((strftime('%m', Oder.date) - 1) / 3) + 1 AS quarter, "
                + "SUM(OderDetail.giaTien) AS total_revenue "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang GROUP BY quarter "
                + "ORDER BY total_revenue DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            highestRevenueQuarter = cursor.getInt(cursor.getColumnIndex("quarter"));
        }
        cursor.close();

        return highestRevenueQuarter;
    }

    @SuppressLint("Range")
    public int getTotalOrdersInMonthWithHighestRevenue() {
        int totalOrders = 0;

        // Truy vấn lấy tháng có doanh thu cao nhất
        String highestRevenueMonthQuery = "SELECT strftime('%m-%Y',Oder.date) AS month_year "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang GROUP BY month_year"
                + " ORDER BY SUM(OderDetail.giaTien) DESC LIMIT 1";

        Cursor cursor = db.rawQuery(highestRevenueMonthQuery, null);
        String highestRevenueMonth = "";
        if (cursor.moveToFirst()) {
            highestRevenueMonth = cursor.getString(cursor.getColumnIndex("month_year"));
        }
        cursor.close();

        // Truy vấn lấy tổng số đơn hàng trong tháng có doanh thu cao nhất
        String totalOrdersQuery = "SELECT COUNT(*) AS total_orders "
                + "FROM Oder WHERE strftime('%m-%Y',Oder.date) = '" + highestRevenueMonth + "'";

        cursor = db.rawQuery(totalOrdersQuery, null);
        if (cursor.moveToFirst()) {
            totalOrders = cursor.getInt(cursor.getColumnIndex("total_orders"));
        }
        cursor.close();

        return totalOrders;
    }
    @SuppressLint("Range")
    public int getTotalOrdersInQuarterWithHighestRevenue() {
        int totalOrders = 0;

        // Truy vấn lấy quý có doanh thu cao nhất
        String highestRevenueQuarterQuery = "SELECT ((strftime('%m', Oder.date) - 1) / 3) + 1 AS quarter, "
                + "SUM(OderDetail.giaTien) AS total_revenue "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang GROUP BY quarter "
                + "ORDER BY total_revenue DESC LIMIT 1";

        Cursor cursor = db.rawQuery(highestRevenueQuarterQuery, null);
        int highestRevenueQuarter = 0;
        if (cursor.moveToFirst()) {
            highestRevenueQuarter = cursor.getInt(cursor.getColumnIndex("quarter"));
        }
        cursor.close();

        // Truy vấn lấy tổng số đơn hàng trong quý có doanh thu cao nhất
        String totalOrdersQuery = "SELECT COUNT(*) AS total_orders "
                + "FROM Oder WHERE ((strftime('%m', Oder.date) - 1) / 3) + 1 = " + highestRevenueQuarter;

        cursor = db.rawQuery(totalOrdersQuery, null);
        if (cursor.moveToFirst()) {
            totalOrders = cursor.getInt(cursor.getColumnIndex("total_orders"));
        }
        cursor.close();

        return totalOrders;
    }

    @SuppressLint("Range")
    public double getTotalPriceForYear2024() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%Y',Oder.date) = '2024'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForYear2023() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%Y',Oder.date) = '2023'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalPriceForYear2022() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%Y',Oder.date) = '2022'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public double getTotalRevenueForThreeYears() {
        double totalRevenue = 0;

        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_revenue "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE strftime('%Y',Oder.date) IN ('2022', '2023', '2024')";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getDouble(cursor.getColumnIndex("total_revenue"));
        }
        cursor.close();

        return totalRevenue;
    }
    @SuppressLint("Range")
    public int getTotalOrdersForThreeYears() {
        int totalOrders = 0;

        String query = "SELECT COUNT(*) AS total_orders "
                + "FROM Oder WHERE strftime('%Y',Oder.date) IN ('2022', '2023', '2024')";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalOrders = cursor.getInt(cursor.getColumnIndex("total_orders"));
        }
        cursor.close();

        return totalOrders;
    }
    @SuppressLint("Range")
    public int getYearWithHighestRevenue() {
        int highestRevenueYear = 0;

        String query = "SELECT strftime('%Y', Oder.date) AS year, "
                + "SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_revenue "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE strftime('%Y', Oder.date) IN ('2022', '2023', '2024')"
                + " GROUP BY year"
                + " ORDER BY total_revenue DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            highestRevenueYear = cursor.getInt(cursor.getColumnIndex("year"));
        }
        cursor.close();

        return highestRevenueYear;
    }
    @SuppressLint("Range")
    public int getTotalOrdersInYearWithHighestRevenue() {
        int totalOrders = 0;

        // Truy vấn lấy năm có doanh thu cao nhất
        String highestRevenueYearQuery = "SELECT strftime('%Y',Oder.date) AS year "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang GROUP BY year"
                + " ORDER BY SUM(OderDetail.giaTien * OderDetail.soLuong) DESC LIMIT 1";

        Cursor cursor = db.rawQuery(highestRevenueYearQuery, null);
        int highestRevenueYear = 0;
        if (cursor.moveToFirst()) {
            highestRevenueYear = cursor.getInt(cursor.getColumnIndex("year"));
        }
        cursor.close();

        // Truy vấn lấy tổng số đơn hàng trong năm có doanh thu cao nhất
        String totalOrdersQuery = "SELECT COUNT(*) AS total_orders "
                + "FROM Oder WHERE strftime('%Y', Oder.date) = '" + highestRevenueYear + "'";

        cursor = db.rawQuery(totalOrdersQuery, null);
        if (cursor.moveToFirst()) {
            totalOrders = cursor.getInt(cursor.getColumnIndex("total_orders"));
        }
        cursor.close();

        return totalOrders;
    }
    @SuppressLint("Range")
    public double getTotalRevenueForCurrentDate() {
        double totalRevenue = 0;

        // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        // Truy vấn lấy tổng doanh thu của ngày hiện tại
        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_revenue "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE date(Oder.date) = '" + currentDate + "' and Oder.status = 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getDouble(cursor.getColumnIndex("total_revenue"));
        }
        cursor.close();

        return totalRevenue;
    }
    @SuppressLint("Range")
    public double getTotalPriceOfOrdersBetweenDates(String startDate, String endDate) {
        double totalPrice = 0;

        // Truy vấn lấy tổng tiền của các đơn hàng với điều kiện ngày nằm trong khoảng đã chọn
        String query = "SELECT SUM(OderDetail.giaTien * OderDetail.soLuong) AS total_price "
                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE Oder.date BETWEEN '" + startDate + "' AND '" + endDate + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
    @SuppressLint("Range")
    public int getTotalOrdersBetweenDates(String startDate, String endDate) {
        int totalOrders = 0;

        // Truy vấn lấy tổng số đơn hàng với điều kiện ngày nằm trong khoảng đã chọn
        String query = "SELECT COUNT(*) AS total_orders "
                + "FROM Oder WHERE Oder.status = 1 AND Oder.date BETWEEN '" + startDate + "' AND '" + endDate + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalOrders = cursor.getInt(cursor.getColumnIndex("total_orders"));
        }
        cursor.close();

        return totalOrders;
    }
    @SuppressLint("Range")
    public int getMostSoldProductIDBetweenDates(String startDate, String endDate) {
        int mostSoldProductID = -1;

        // Truy vấn lấy ID sản phẩm được bán nhiều nhất trong khoảng StartDate và EndDate
//        String query = "SELECT dhct.idSp, COUNT(*) AS total_orders "
//                + "FROM Oder AS dh "
//                + "JOIN OderDetail AS dhct ON dh.id = dhct.idDonHang WHERE dh.status = 1 "
//                + "AND dh.date BETWEEN '" + startDate + "' AND '" + endDate + "' "
//                + "GROUP BY dhct.idSp ORDER BY total_orders DESC "
//                + "LIMIT 1";
        // Truy vấn lấy ID sản phẩm được bán nhiều nhất trong khoảng StartDate và EndDate
        String query = "SELECT dhct.idSp, SUM(dhct.soLuong) AS total_quantity "
                + "FROM Oder AS dh "
                + "JOIN OderDetail AS dhct ON dh.id = dhct.idDonHang WHERE dh.status = 1 "
                + "AND dh.date BETWEEN '" + startDate + "' AND '" + endDate + "' "
                + "GROUP BY dhct.idSp ORDER BY total_quantity DESC "
                + "LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            mostSoldProductID = cursor.getInt(cursor.getColumnIndex("idSp"));
        }
        cursor.close();

        return mostSoldProductID;
    }
}

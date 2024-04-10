package com.example.du_an1_qldt.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;

import java.util.ArrayList;

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

        Cursor cursor = db.rawQuery("SELECT SUM(giaTien) FROM OderDetail ", null);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }
    @SuppressLint("Range")
    public double getTotalPriceForMonth3() {
        double totalPrice = 0;

        String query = "SELECT SUM(OderDetail.giaTien) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '03'";



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

        String query = "SELECT SUM(OderDetail.giaTien) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '04'";



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

        String query = "SELECT SUM(OderDetail.giaTien) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%m',Oder.date) = '02'";



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

        String query = "SELECT SUM(OderDetail.giaTien) AS total_price "
                + "FROM OderDetail JOIN Oder ON Oder.id = OderDetail.idDonHang WHERE strftime('%d',Oder.date) = '10' and strftime('%m',Oder.date)='04'";



        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
        }
        cursor.close();

        return totalPrice;
    }
}

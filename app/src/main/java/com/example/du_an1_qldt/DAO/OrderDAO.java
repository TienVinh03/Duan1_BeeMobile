package com.example.du_an1_qldt.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public OrderDAO(Context context) {
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();

    }

    public ArrayList<Order> getOrdersByStatus(int status) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query("Oder", null, "status=?", new String[]{String.valueOf(status)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Order order = new Order();
                    order.setIdUser(cursor.getInt(1));
                    order.setId(cursor.getInt(0));
                    order.setDateOrder(cursor.getString(2));
                    order.setStatusOrder(cursor.getInt(3));
                    orders.add(order);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return orders;
    }
    public ArrayList<Order> getOrdersByStatus1(int status,String start, String end ) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query("Oder", null, "status=?", new String[]{String.valueOf(status)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Order order = new Order();
                    order.setIdUser(cursor.getInt(1));
                    order.setId(cursor.getInt(0));
                    order.setDateOrder(cursor.getString(2));
                    order.setStatusOrder(cursor.getInt(3));
                    orders.add(order);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return orders;
    }


    public int createOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("idUser", order.getIdUser());
        values.put("date", order.getDateOrder());
        values.put("status", order.getStatusOrder());

        return (int) db.insert("Oder", null, values);
    }

    public long createOrder2(Order order) {
        ContentValues values = new ContentValues();
        values.put("idUser", order.getIdUser());
        values.put("status", order.getStatusOrder());
        values.put("date", order.getDateOrder());

        long orderId = db.insert("Oder", null, values); // Chú ý: "Order" là tên của bảng đơn hàng trong cơ sở dữ liệu

        return orderId;
    }

    public int createOrderDetail(OrderDetail order) {
        ContentValues values = new ContentValues();
        values.put("idSp", order.getIdProduct());
        values.put("idDonHang", order.getIdDonHang());
        values.put("soLuong", order.getQuantity());
        values.put("giaTien", order.getPrice());

        return (int) db.insert("OderDetail", null, values);
    }

    public int deletOrder(Order order) {
        String[] dk = new String[]{String.valueOf(order.getId())};
        return db.delete("Oder", "id=?", dk);
    }

    public void deleteOrderDetailsByOrderId(int orderId) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        db.delete("OderDetail", "idDonHang=?", new String[]{String.valueOf(orderId)});
    }

    public int updateOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("status", order.getStatusOrder());
        String[] dk = new String[]{String.valueOf(order.getId())};
        int check = db.update("Oder", values, "id=?", dk);
        return check;
    }

    public ArrayList<OrderDetail> getlistOrderDetail(int orderId) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Cursor c = db.query("OderDetail", null, "idDonHang=?", new String[]{String.valueOf(orderId)}, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                OrderDetail order = new OrderDetail();
                order.setId(c.getInt(0));
                order.setIdDonHang(c.getInt(2));
                order.setIdProduct(c.getInt(1));
                order.setQuantity(c.getInt(3));
                order.setPrice(c.getInt(4));
                list.add(order);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Order> getConfirmedOrders(int idUser) {
        ArrayList<Order> confirmedOrders = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Oder WHERE status = 1 AND idUser = ?",  new String[]{String.valueOf(idUser)});
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setIdUser(cursor.getInt(1));
                order.setId(cursor.getInt(0));
                order.setDateOrder(cursor.getString(2));
                order.setStatusOrder(cursor.getInt(3));
                confirmedOrders.add(order);
            } while (cursor.moveToNext());
        }
        return confirmedOrders;
    }
    @SuppressLint("Range")
    public ArrayList<Order> getOrdersWithStatusAndDateBetween(String startDate, String endDate) {
        ArrayList<Order> orders = new ArrayList<>();

        // Truy vấn lấy danh sách đơn hàng có trạng thái bằng 1 và ngày nằm trong khoảng StartDate và EndDate
        String query = "SELECT * FROM Oder AS dh "
                + "JOIN OderDetail AS dhct ON dh.id = dhct.idDonHang WHERE dh.status = 1 "
                + "AND dh.date BETWEEN '" + startDate + "' AND '" + endDate + "'";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            // Lấy thông tin của đơn hàng từ Cursor và thêm vào danh sách
            Order order = new Order();
            order.setId(cursor.getInt(cursor.getColumnIndex("id")));
            order.setStatusOrder(cursor.getInt(cursor.getColumnIndex("status")));
            order.setDateOrder(cursor.getString(cursor.getColumnIndex("date")));
            // Thêm thông tin khác của đơn hàng nếu cần
            orders.add(order);
        }
        cursor.close();

        return orders;
    }

}

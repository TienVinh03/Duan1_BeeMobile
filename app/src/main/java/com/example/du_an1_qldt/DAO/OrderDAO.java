package com.example.du_an1_qldt.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class OrderDAO {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public OrderDAO(Context context) {
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();

    }

    public ArrayList<Order> getlistOrder() {
        ArrayList<Order> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Oder", null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Order order = new Order();
                order.setIdUser(c.getInt(1));
                order.setId(c.getInt(0));
                order.setDateOrder(c.getString(2));
                order.setStatusOrder(c.getInt(3));
                list.add(order);
            } while (c.moveToNext());
        }
        return list;
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
        values.put("idDonHang", order.getId());
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
        Cursor c = db.rawQuery("select * from OderDetail where idDonHang=?", new String[]{String.valueOf(orderId)});
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


//            String db_order_detail = "CREATE TABLE OderDetail (" +
//                    "id integer PRIMARY KEY AUTOINCREMENT, " +
//                    "idSp integer NOT NULL, " +
//                    "idDonHang integer NOT NULL, " +
//                    "soLuong integer NOT NULL," +
//                    "giaTien integer NOT NULL," +
//                    "FOREIGN KEY (idSp) REFERENCES Phone(maDt)," +
//                    "FOREIGN KEY (idDonHang) REFERENCES Oder(id))";
//            sqLiteDatabase.execSQL(db_order_detail);
        }
        return list;
    }

    public ArrayList<Order> getConfirmedOrders() {
        ArrayList<Order> confirmedOrders = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Oder WHERE status = 1", null);
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
}

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

    public OrderDAO (Context context){
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();

    }
    public ArrayList<Order> getlistOrder(){
        ArrayList<Order> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Oder",null,null);
        if (c.getCount()>0){
            c.moveToFirst();
            do {
                Order order= new Order();
                order.setIdUser(c.getInt(1));
                order.setId(c.getInt(0));
                order.setDateOrder(c.getString(2));
                order.setStatusOrder(c.getString(3));
                list.add(order);
            }while (c.moveToNext());
        }
        return list;
    }
    public int createOrder(Order order){
        ContentValues values = new ContentValues();
        values.put("idUser",order.getIdUser());
        values.put("date",order.getDateOrder());
        values.put("status",order.getStatusOrder());

        return (int)db.insert("Oder",null,values);
    }
    public int createOrderDetail(OrderDetail order){
        ContentValues values = new ContentValues();
        values.put("idSp",order.getIdProduct());
        values.put("idDonHang",order.getId());
        values.put("soLuong",order.getQuantity());
        values.put("giaTien",order.getPrice());

        return (int)db.insert("OderDetail",null,values);
    }
    public int deletOrder(Order order){
        String[] dk = new String[]{String.valueOf(order.getId())};
        return db.delete("Oder","id=?",dk);
    }
    public int updateOrder(Order order){
        ContentValues values= new ContentValues();
        values.put("status", order.getStatusOrder());
        String[] dk = new String[]{String.valueOf(order.getId())};
        int check = db.update("Oder",values,"id=?",dk);
        return check;
    }
}

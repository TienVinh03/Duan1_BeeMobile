package com.example.du_an1_qldt.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Cart;
import com.example.du_an1_qldt.model.Customer;

import java.util.ArrayList;

public class CustomerDao {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public CustomerDao(Context context) {
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public ArrayList<Customer> getListCustomer() {
        ArrayList<Customer> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Customer", null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Customer customer = new Customer();
                customer.setId(c.getInt(0));
                customer.setAddress(c.getString(3));
                customer.setName(c.getString(2));
                customer.setNumberPhone(c.getString(1));
                list.add(customer);
            } while (c.moveToNext());
        }
        return list;
    }

    public int addCustomer(Customer customer) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", customer.getName());
        values.put("sdt", customer.getNumberPhone());
        values.put("address", customer.getAddress());

        return (int) db.insert("Customer", null, values);

    }
}

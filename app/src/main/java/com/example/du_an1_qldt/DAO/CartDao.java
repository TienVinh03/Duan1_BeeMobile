package com.example.du_an1_qldt.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Cart;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class CartDao {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public CartDao( Context context) {
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public ArrayList<Cart> getlistCart() {
        ArrayList<Cart> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from ShoppingCart", null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Cart cart = new Cart();
                cart.setQuantity(c.getInt(2));
                cart.setIdPhone(c.getInt(1));
                cart.setColor(c.getString(4));
                cart.setRom(c.getInt(5));
                cart.setPrice(c.getInt(3));
                list.add(cart);
            } while (c.moveToNext());
        }
        return list;
    }
    public int addSanPhamtoCart(Cart cart) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("maDt", cart.getIdPhone());
            values.put("donGia", cart.getPrice());
            values.put("ram", cart.getRom());
            values.put("mauSac", cart.getColor());
            values.put("soLuong", cart.getQuantity());

            return (int) db.insert("ShoppingCart", null, values);

    }
}

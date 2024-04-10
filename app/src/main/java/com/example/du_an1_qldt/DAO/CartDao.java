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

    public ArrayList<Cart> getlistCart(int userId) {
        ArrayList<Cart> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from ShoppingCart where idUser=?",new String[]{String.valueOf(userId)}, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Cart cart = new Cart();
                cart.setId(c.getInt(0));
                cart.setIdUser(c.getInt(6));
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
            values.put("idUser", cart.getIdUser());

            return (int) db.insert("ShoppingCart", null, values);

    }
    public int deleteRowCart(Cart cart){
        String[] dk = new String[]{String.valueOf(cart.getId())};
        return db.delete("ShoppingCart","id=?",dk);

    }
    public void updateCartItemQuantity(int cartItemId, int newQuantity) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soLuong", newQuantity);

        // Điều kiện để cập nhật chỉ mục cụ thể
        String selection = "id=?";
        String[] selectionArgs = { String.valueOf(cartItemId) };

        // Thực hiện cập nhật
        int count = db.update("ShoppingCart", values, selection, selectionArgs);
        db.close();

        if (count > 0) {
            // Cập nhật thành công
        } else {
            // Cập nhật không thành công
        }
    }
}

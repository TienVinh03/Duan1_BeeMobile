package com.example.du_an1_qldt.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.DataBase1.dbHelper;


public class TaiKhoanDAO {
    SharedPreferences sharedPreferences;
    dbHelper database;

    public TaiKhoanDAO(Context context) {
        database = new dbHelper(context);
        sharedPreferences = context.getSharedPreferences("thongtin",MODE_PRIVATE);
    }
    public boolean checkDangNhap(String hoTen, String password) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        // mathuthu, hoten, matkhau, loaitaikhoan
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nguoiDung WHERE username = ? AND password = ?", new String[]{hoTen, password});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("hoTen",cursor.getString(1));
            editor.putString("matKhau",cursor.getString(2));
            editor.putString("loaitaikhoan",cursor.getString(7));
            editor.putString("email",cursor.getString(5));
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

//    public boolean capnhatmatkhau(String username, String passmoi, String passmoi2) {
//        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nguoidung WHERE id = ? AND password = ?", new String[]{username, passmoi});
//        if (cursor.getCount() > 0) {
//            ContentValues values = new ContentValues();
//            values.put("pass", passmoi2);
//            long check = sqLiteDatabase.update("nguoiDung", values, "id = ?", new String[]{username});
//            if (check == -1)
//
//                return false;
//            return true;
//
//        }
//        return false;
//
//    }
}

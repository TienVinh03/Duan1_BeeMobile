package com.example.du_an1_qldt.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.KhachHang_DTO;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    SQLiteDatabase sql;
    dbHelper myDbHelper;

    public KhachHangDAO(Context context) {
        myDbHelper = new dbHelper(context);
        sql = myDbHelper.getWritableDatabase();
    }

    public ArrayList<KhachHang_DTO> getAll() {

        SQLiteDatabase sql = myDbHelper.getWritableDatabase();
        ArrayList<KhachHang_DTO> list = new ArrayList<>();
        Cursor cursor = sql.rawQuery("SELECT * FROM nguoiDung", null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new KhachHang_DTO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insert(KhachHang_DTO obj) {
        SQLiteDatabase sql = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("manguoidung", obj.getManguoidung());
        values.put("username", obj.getUsername());
        values.put("password", obj.getPassword());
        values.put("hoten", obj.getHoten());
        values.put("sodienthoai", obj.getSodienthoai());
        values.put("email", obj.getEmail());
        values.put("diachi", obj.getDiachi());
        values.put("loaitaikhoan", obj.getLoaitaikhoan());

        long row = sql.insert("nguoiDung", null, values);
        return row > 0;

    }

    public boolean updateTT(int manguoidung, String hoten, String sodienthoai, String email, String diachi) {

        SQLiteDatabase sql = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hoten", hoten);
        values.put("sodienthoai", sodienthoai);
        values.put("email", email);
        values.put("diachi", diachi);
        long check = sql.update("nguoiDung", values, "manguoidung = ?", new String[]{String.valueOf(manguoidung)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    public int update(KhachHang_DTO obj) {
        SQLiteDatabase sql = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("manguoidung", obj.getManguoidung());
        values.put("username", obj.getUsername());
        values.put("password", obj.getPassword());
        values.put("hoten", obj.getHoten());
        values.put("sodienthoai", obj.getSodienthoai());
        values.put("email", obj.getEmail());
        values.put("diachi", obj.getDiachi());
        values.put("loaitaikhoan", obj.getLoaitaikhoan());

        String[] dieukien = new String[]{String.valueOf(obj.getManguoidung())};
        return sql.update("nguoiDung", values, "manguoidung=?", dieukien);
    }

    public int delete(KhachHang_DTO obj) {
        String[] dieukien = new String[]{String.valueOf(obj.getManguoidung())};
        return sql.delete("nguoiDung", "manguoidung=?", dieukien);
    }


    public KhachHang_DTO getID(String id) {
        String sqlQuery = "SELECT * FROM nguoiDung WHERE manguoidung=?";
        Cursor cursor = sql.rawQuery(sqlQuery, new String[]{id});

        if (cursor.moveToFirst()) {
            KhachHang_DTO khachHang = new KhachHang_DTO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            cursor.close();
            return khachHang;
        } else {
            return null;
        }
    }

    public KhachHang_DTO getUserName(String username) {
        String sqlQuery = "SELECT * FROM nguoiDung WHERE username=?";
        Cursor cursor = sql.rawQuery(sqlQuery, new String[]{username});

        if (cursor.moveToFirst()) {
            KhachHang_DTO khachHang = new KhachHang_DTO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            cursor.close();
            return khachHang;
        } else {
            return null;
        }
    }


    public KhachHang_DTO getUserName2(String username) {
        String sqlQuery = "SELECT * FROM nguoiDung WHERE username=?";
        Cursor cursor = sql.rawQuery(sqlQuery, new String[]{username});

        if (cursor.moveToFirst()) {
            KhachHang_DTO khachHang = new KhachHang_DTO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            cursor.close();
            return khachHang;
        } else {
            return null;
        }
    }


    public int checkEmail(String email) {
        Cursor cursor = sql.rawQuery("SELECT * FROM nguoiDung WHERE email = ?", new String[]{String.valueOf(email)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        return 1;
    }


    public boolean themkhachhang(String ten, String email, String pass,String sdt) {
        SQLiteDatabase sql = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", ten);
        values.put("email", email);
        values.put("password", pass);
        values.put("sodienthoai",sdt);
        values.put("loaitaikhoan", "user");
        long check = sql.insert("nguoiDung", null, values);

        return check != -1;
    }


    public boolean checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = myDbHelper.getReadableDatabase();

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM nguoiDung WHERE username=? AND password=?", new String[]{username, password});
            if (cursor.getCount() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.d("lỗi", String.valueOf(e));
            return false;
        }

    }


}

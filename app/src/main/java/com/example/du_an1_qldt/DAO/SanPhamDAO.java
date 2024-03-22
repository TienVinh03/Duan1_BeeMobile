package com.example.du_an1_qldt.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.phone;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public SanPhamDAO (Context context){

        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();

    }

    public int listSanPham(phone sanPhamDTO){

        ContentValues values = new ContentValues();


        return 1;
    }
}

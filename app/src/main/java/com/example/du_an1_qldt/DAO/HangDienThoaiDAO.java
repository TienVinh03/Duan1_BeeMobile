package com.example.du_an1_qldt.DAO;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.Context;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.HangDienThoai;

import java.util.ArrayList;
import java.util.List;

public class HangDienThoaiDAO {
    private Context context;

    private SQLiteDatabase db;

    public HangDienThoaiDAO(Context context) {
        this.context = context;
        dbHelper dhl = new dbHelper(context);
        db = dhl.getWritableDatabase();
    }
    public long insert(HangDienThoai hangDienThoai) {
        ContentValues values = new ContentValues();
        values.put("tenHang", hangDienThoai.getTenHang());
        values.put("heDieuHanh", hangDienThoai.getHeDieuHanh());
        return db.insert("Brand", null, values);
    }

    public int update(HangDienThoai hangDienThoai) {
        ContentValues values = new ContentValues();
        values.put("tenHang", hangDienThoai.getTenHang());
        values.put("heDieuHanh", hangDienThoai.getHeDieuHanh());
        return db.update("Brand", values, "idHang=?", new String[]{String.valueOf(hangDienThoai.getIdHang())});
    }

    public int delete(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM Phone WHERE idHang = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("Brand", "idHang=?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;


    }



    public List<HangDienThoai> getData(String sql, String... SelectArgt) {
        List<HangDienThoai> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, SelectArgt);
        while (cursor.moveToNext()) {
            HangDienThoai hangDienThoai = new HangDienThoai();
            hangDienThoai.setIdHang(cursor.getInt(0));
            hangDienThoai.setTenHang(cursor.getString(1));
            hangDienThoai.setHeDieuHanh(cursor.getString(1));
            list.add(hangDienThoai);
        }
        return list;
    }


    //
    public List<HangDienThoai> getAll() {
        String sql = "SELECT * FROM Brand";
        return getData(sql);
    }
}

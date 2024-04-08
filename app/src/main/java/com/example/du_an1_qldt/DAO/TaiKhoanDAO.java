package com.example.du_an1_qldt.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.KhachHang_DTO;

import java.util.ArrayList;
import java.util.List;


public class TaiKhoanDAO {
    SharedPreferences sharedPreferences;
    dbHelper database;
    SQLiteDatabase db;

    public TaiKhoanDAO(Context context) {
        database = new dbHelper(context);
        sharedPreferences = context.getSharedPreferences("thongtin", MODE_PRIVATE);
        db = database.getWritableDatabase(); // Mở cơ sở dữ liệu để ghi dữ liệu
    }

    public boolean checkDangNhap(String hoTen, String password) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nguoiDung WHERE username = ? AND password = ?", new String[]{hoTen, password});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("hoTen", cursor.getString(1));
            editor.putString("manguoidung", cursor.getString(0));
            editor.putString("matKhau", cursor.getString(2));
            editor.putString("loaitaikhoan", cursor.getString(7));
            editor.putString("email", cursor.getString(5));

            editor.putString("sodienthoai", cursor.getString(4));
            editor.putString("diachi", cursor.getString(6));




            editor.apply(); // Sử dụng apply() thay vì commit() để áp dụng thay đổi ngay lập tức
            return true;
        } else {
            return false;
        }
    }

    public long updatePass(KhachHang_DTO obj) {
        ContentValues values = new ContentValues();
        values.put("password", obj.getPassword());
        return db.update("nguoiDung", values, "manguoidung = ?", new String[]{String.valueOf(obj.getManguoidung())});
    }



//    public KhachHang_DTO getID(String id) {
//        String sql = "SELECT * FROM nguoiDung WHERE manguoidung=?";
//        List<KhachHang_DTO> list = getData(sql, id);
//        if (!list.isEmpty()) { // Kiểm tra nếu danh sách không trống
//            return list.get(0); // Trả về phần tử đầu tiên nếu danh sách không trống
//        } else {
//            return null; // Trả về null nếu danh sách trống
//        }
//    }

    public KhachHang_DTO getID(String userName) {
        String sql = "SELECT * FROM nguoiDung WHERE username=?";
        List<KhachHang_DTO> list = getData(sql, userName);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }


    @SuppressLint("Range")
    private List<KhachHang_DTO> getData(String sql, String... selectionArgs) {
        List<KhachHang_DTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang_DTO obj = new KhachHang_DTO();
            obj.setManguoidung(cursor.getInt(cursor.getColumnIndex("manguoidung")));
            obj.setHoten(cursor.getString(cursor.getColumnIndex("username")));
            obj.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            list.add(obj);
        }
        cursor.close(); // Đóng con trỏ sau khi sử dụng xong để tránh rò rỉ bộ nhớ
        return list;
    }

    public String getUserNameById(int userId) {
        SQLiteDatabase db = database.getReadableDatabase();

        // Truy vấn cơ sở dữ liệu để lấy tên người dùng dựa trên id
        Cursor cursor = db.query("nguoiDung", new String[]{"hoTen"}, "maNguoiDung=?", new String[]{String.valueOf(userId)}, null, null, null);
        String userName = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userName = cursor.getString(0);
            }
            cursor.close();
        }

        db.close();
        return userName;
    }

}


package com.example.du_an1_qldt.DAO;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;

    public SanPhamDAO (Context context){

        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();

    }

    public ArrayList<phone> getlistSP(){
        ArrayList<phone> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Phone",null,null);
        if (c.getCount()>0){
            c.moveToFirst();
            do {

                phone phone1 = new phone();
                phone1.setId(c.getInt(0));
                phone1.setName(c.getString(1));
                phone1.setGia(c.getInt(3));
                phone1.setRom(c.getInt(5));
                phone1.setColor(c.getString(6));
                phone1.setImage(c.getInt(4));
                phone1.setStatus(c.getInt(7));
                phone1.setSoLuong(c.getInt(8));
                phone1.setId_Hang(c.getInt(2));
                list.add(phone1);



            }while (c.moveToNext());
        }


        return list;
    }


    public int insertSP(phone sanPhamDTO){

        ContentValues values = new ContentValues();
        values.put("tenDt",sanPhamDTO.getName());

        values.put("idHang",sanPhamDTO.getId_Hang());
        values.put("gia",sanPhamDTO.getGia());
        values.put("image",sanPhamDTO.getImage());
        values.put("rom",sanPhamDTO.getRom());
        values.put("mausac",sanPhamDTO.getColor());
        values.put("trangthai",sanPhamDTO.getStatus());
        values.put("soluong",sanPhamDTO.getSoLuong());


        return (int) db.insert("Phone",null,values);
    }

//    String db_phone="create table Phone(maDt integer primary key autoincrement," +
//            "tenDt text," +
//            "idHang integer not null," +
//            "gia integer," +
//            "image integer," +
//            "rom integer," +
//            "mausac text," +
//            "trangthai int," +
//            "soluong integer,"+
//            "FOREIGN KEY (idHang) REFERENCES Brand(idHang))";
    public int addSanPham(phone phoneDTO){
        ContentValues values = new ContentValues();
        values.put("tenDt",phoneDTO.getName());
        values.put("idHang",phoneDTO.getId_Hang());
        values.put("gia",phoneDTO.getGia());
        values.put("image",phoneDTO.getImage());
        values.put("rom",phoneDTO.getRom());

        values.put("mausac",phoneDTO.getColor());
        values.put("trangthai",phoneDTO.getStatus());
        values.put("soluong",phoneDTO.getSoLuong());

        return (int)db.insert("Phone",null,values);
    }
    public int upSanPham(phone phoneDTO){
        ContentValues values = new ContentValues();
        values.put("tenDt",phoneDTO.getName());
        values.put("idHang",phoneDTO.getId_Hang());
        values.put("gia",phoneDTO.getGia());
        values.put("image",phoneDTO.getImage());
        values.put("rom",phoneDTO.getRom());

        values.put("mausac",phoneDTO.getColor());
        values.put("trangthai",phoneDTO.getStatus());
        values.put("soluong",phoneDTO.getSoLuong());

        String[] dk = new String[]{String.valueOf(phoneDTO.getId())};

        return db.update("Phone",values,"maDt=?",dk);
    }
    public int deleteRow_SP(phone phoneDTO){
        String[] dk = new String[]{String.valueOf(phoneDTO.getId())};
        return db.delete("Phone","maDt=?",dk);

    }
    public String getProductNameById(int productId) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        String productName = null;
        Cursor cursor = null;

        try {
            // Truy vấn cơ sở dữ liệu để lấy tên sản phẩm dựa trên id
            cursor = db.query("Phone", new String[]{"tenDt"}, "maDt=?", new String[]{String.valueOf(productId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                productName = cursor.getString(0);
            }
        } finally {
            // Đảm bảo đóng con trỏ và cơ sở dữ liệu
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return productName;
    }

    @SuppressLint("Range")
    public ArrayList<phone> TimKiemSanPham(String ten) {
        dbHelper database = new dbHelper(context);
        SQLiteDatabase sqLite = database.getWritableDatabase();
        ArrayList<phone> list = new ArrayList<>();

//
//        String db_phone="create table Phone(maDt integer primary key autoincrement," +
//                "tenDt text," +
//                "idHang integer not null," +
//                "gia integer," +
//                "image integer," +
//                "rom integer," +
//                "mausac text," +
//                "trangthai int," +
//                "soluong integer,"+
//                "FOREIGN KEY (idHang) REFERENCES Brand(idHang))";
        Cursor cursor = sqLite.rawQuery("SELECT  * FROM Phone  WHERE tenDt LIKE '%" + ten + "%' ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                phone sanPham = new phone();
                sanPham.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maDt"))));
                sanPham.setColor(String.valueOf(cursor.getColumnIndex("mausac")));
                sanPham.setName(cursor.getString(cursor.getColumnIndex("tenDt")));

                sanPham.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
                sanPham.setId_Hang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("idHang"))));
                sanPham.setStatus(Integer.parseInt(cursor.getString(cursor.getColumnIndex("trangthai"))));
                sanPham.setRom(Integer.parseInt(cursor.getString(cursor.getColumnIndex("rom"))));
                sanPham.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("image"))));


                list.add(sanPham);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public int getProductQuantityFromDatabase(int productId) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query("Phone", new String[]{"soluong"},
                "maDt=?", new String[]{String.valueOf(productId)},
                null, null, null, null);
        int quantity = 0;
        if (cursor != null && cursor.moveToFirst()) {
            // Lấy giá trị của cột "soluong"
            quantity = cursor.getInt(0);
            cursor.close();
        }
        return quantity;
    }

    // Method to update product quantity in database
    public void updateProductQuantityInDatabase(int productId, int updatedQuantity) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soluong", updatedQuantity);
        int rowsAffected = db.update("Phone", values, "maDt=?",
                new String[]{String.valueOf(productId)});
        db.close();

        if (rowsAffected == 0) {
            // Không có sản phẩm nào được cập nhật, có thể có lỗi xảy ra
            Log.e("UpdateProductQuantity", "No rows affected! Product ID: " + productId);
        } else {
            Log.d("UpdateProductQuantity", rowsAffected + " rows affected.");
        }
    }
    public phone getProductById(int idPr){
        phone phone1= null;

        Cursor c = db.query("Phone", null, "maDt = ?", new String[]{String.valueOf(idPr)}, null, null, null);
        if (c.getCount()>0){
            c.moveToFirst();
            if (c != null && c.moveToFirst()){
              int id=c.getInt(0);
              String color=c.getString(6);
              String name=c.getString(1);
              int idBrand=c.getInt(2);
              int price=c.getInt(3);
              int image=c.getInt(4);
              int rom=c.getInt(5);
              int status=c.getInt(7);
              int quantity=c.getInt(8);
              phone1= new phone(id,name,color,image,rom,price,quantity,idBrand,status);
                c.close();
            }
        }
        return phone1;
    }
}

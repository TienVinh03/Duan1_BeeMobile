package com.example.du_an1_qldt.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
}

package com.example.du_an1_qldt.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.example.du_an1_qldt.model.phone;

import java.util.ArrayList;

public class VoucherDAO {
    private SQLiteDatabase db;
    private Context context;
    dbHelper myDbHelper;
    public VoucherDAO(Context context) {
        this.context = context;
        myDbHelper = new dbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }


//    String db_voucher="create table Voucher(idVoucher integer primary key autoincrement,"+
//            "giaTriGiam integer,"+"tenVoucher text,"+"soLuong integer,"+"trangThai integer)";

    public ArrayList<Voucher_DTO> getListVoucher(){
        ArrayList<Voucher_DTO> listVoucher = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Voucher",null,null);
        if (c.getCount()>0){
            c.moveToFirst();
            do {

                int id = c.getInt(0);
                int giaTriGiam = c.getInt(1);
                String ten = c.getString(2);
                int soLuong = c.getInt(3);
                int trangThai = c.getInt(4);
                Voucher_DTO voucherDto = new Voucher_DTO(id,giaTriGiam,ten,soLuong,trangThai);
                listVoucher.add(voucherDto);


            }while (c.moveToNext());
        }


        return listVoucher;
    }
//    String db_voucher="create table Voucher(idVoucher integer primary key autoincrement,"+
//            "giaTriGiam integer,"+"tenVoucher text,"+"soLuong integer,"+"trangThai integer)";
//        sqLiteDatabase.execSQL(db_voucher);
    public int add_Voucher(Voucher_DTO voucherDto){
        ContentValues values = new ContentValues();
        values.put("tenVoucher",voucherDto.getTenVoucher());
        values.put("giaTriGiam",voucherDto.getGiaTriGiam());
        values.put("soLuong",voucherDto.getSoLuong());
        values.put("trangThai",voucherDto.getTrangThai());


        return (int) db.insert("Voucher",null,values);


    }
    public int sua_Voucher(Voucher_DTO voucherDto){
        ContentValues values = new ContentValues();
        values.put("tenVoucher",voucherDto.getTenVoucher());
        values.put("giaTriGiam",voucherDto.getGiaTriGiam());
        values.put("soLuong",voucherDto.getSoLuong());
        values.put("trangThai",voucherDto.getTrangThai());

        String[] dk = new String[]{String.valueOf(voucherDto.getId())};

        return db.update("Voucher",values,"idVoucher=?",dk);


    }

    public int deleteRow_Voucher(Voucher_DTO voucherDto){
        String[] dk = new String[]{String.valueOf(voucherDto.getId())};
        return db.delete("Voucher","idVoucher=?",dk);

    }
    public ArrayList<Voucher_DTO> selectAll() {
        String sql = "SELECT * FROM Voucher";
        return getAll(sql);
    }
    private ArrayList<Voucher_DTO> getAll(String sql, String... selectionArgs){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        ArrayList<Voucher_DTO> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                int discount = Integer.parseInt(String.valueOf(cursor.getInt(1)));
                String nameVoucher = cursor.getString(2);
                int id =cursor.getInt(0);
                int sl =cursor.getInt(3);
                int tt =cursor.getInt(4);
                list.add(new Voucher_DTO(id,discount, nameVoucher,sl,tt));
            } while (cursor.moveToNext());
        }
        return list;
    }
}

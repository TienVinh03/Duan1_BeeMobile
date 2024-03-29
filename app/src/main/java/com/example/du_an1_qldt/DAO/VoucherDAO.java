package com.example.du_an1_qldt.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an1_qldt.DataBase1.dbHelper;
import com.example.du_an1_qldt.model.Voucher_DTO;

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

}

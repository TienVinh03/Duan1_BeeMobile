package com.example.du_an1_qldt.DataBase1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DB_name="BeePhones11";
    public dbHelper(@Nullable Context context) {
        super(context, DB_name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_hang="create table Brand(idHang integer primary key autoincrement,"+
                "tenHang text , heDieuHanh TEXT not null)";
        sqLiteDatabase.execSQL(db_hang);
        String db_phone="create table Phone(maDt integer primary key autoincrement," +
                "tenDt text," +
                "idHang integer not null," +
                "gia integer," +
                "rom integer," +
                "mausac text," +
                "trangthai int," +
                "soluong integer,"+
                "FOREIGN KEY (idHang) REFERENCES Brand(idHang))";
        sqLiteDatabase.execSQL(db_phone);
        String db_cart = "CREATE TABLE IF NOT EXISTS ShoppingCart (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maDt INTEGER not null," +
                "soLuong INTEGER not null," +
                "donGia integer not null," +
                "mauSac TEXT not null," +
                "FOREIGN KEY (maDt) REFERENCES Phone(maDt))";
        sqLiteDatabase.execSQL(db_cart);
        String db_voucher="create table Voucher(idVoucher integer primary key autoincrement,"+
                "giaTriGiam text,"+"tenVoucher text)";
        sqLiteDatabase.execSQL(db_voucher);
        String dbnguoidung = "CREATE TABLE NguoiDung(manguoidung INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "hoten TEXT NOT NULL," +
                "sodienthoai TEXT NOT NULL," +
                "email TEXT," +
                "diachi TEXT," +
                "loaitaikhoan TEXT)";
        sqLiteDatabase.execSQL(dbnguoidung);

        sqLiteDatabase.execSQL("INSERT INTO nguoidung  VALUES(1,'admin','admin','Vu Tien Vinh','0332322764','vinhvtph45732@fpt.edu.vn','Thai Binh','admin')");

        String db_order_detail ="CREATE TABLE OderDetail (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idSp integer NOT NULL, " +
                "idDonHang integer NOT NULL, " +
                "soLuong integer NOT NULL," +
                "giaTien integer NOT NULL," +
                "FOREIGN KEY (idSp) REFERENCES Phone(maDt),"+
                "FOREIGN KEY (idDonHang) REFERENCES DonHang(id))";
        sqLiteDatabase.execSQL(db_order_detail);
        String db_order="CREATE TABLE Oder (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idAdmin integer NOT NULL, " +
                "idUser integer NOT NULL, " +
                "idSp integer NOT NULL, " +
                "idVoucher integer NOT NULL, " +
                "soLuong integer NOT NULL," +
                "giaTien integer NOT NULL," +
                "FOREIGN KEY (idSp) REFERENCES Phone(maDt),"+
                "FOREIGN KEY (idAdmin) REFERENCES Admin(id),"+
                "FOREIGN KEY (idVoucher) REFERENCES Voucher(idVoucher),"+
                "FOREIGN KEY (idUser) REFERENCES User(id))";
        sqLiteDatabase.execSQL(db_order);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        if (oldV<newV){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Admin");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS User");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Phone");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Voucher");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Older");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OlderDetail");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Brand");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ShoppingCart");
        }


    }
}

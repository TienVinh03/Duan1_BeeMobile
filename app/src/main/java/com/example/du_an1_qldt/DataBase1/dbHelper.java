package com.example.du_an1_qldt.DataBase1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DB_name="BeePhones3333333";

    public dbHelper(@Nullable Context context) {
        super(context, DB_name,null, 19);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_hang="create table Brand(idHang integer primary key autoincrement,"+
                "tenHang text , heDieuHanh TEXT not null)";
        sqLiteDatabase.execSQL(db_hang);

        String insert_hang = "insert into Brand values (1,'Xiaomi','Android'),(2,'Apple','IOS')";
        sqLiteDatabase.execSQL(insert_hang);


        String db_phone="create table Phone(maDt integer primary key autoincrement," +
                "tenDt text," +
                "idHang integer not null," +
                "gia integer," +
                "image integer," +
                "rom integer," +
                "mausac text," +
                "trangthai int," +
                "soluong integer,"+
                "FOREIGN KEY (idHang) REFERENCES Brand(idHang))";
        sqLiteDatabase.execSQL(db_phone);

        String insert_phone = "insert into Phone Values (1,'K40 Gaming',1,8200000,1,128,'Trắng',1,20)";
        sqLiteDatabase.execSQL(insert_phone);

        String db_cart = "CREATE TABLE IF NOT EXISTS ShoppingCart (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maDt INTEGER not null," +
                "soLuong INTEGER not null," +
                "donGia integer not null," +
                "mauSac TEXT not null," +
                "FOREIGN KEY (maDt) REFERENCES Phone(maDt))";
        sqLiteDatabase.execSQL(db_cart);


        String db_voucher="create table Voucher(idVoucher integer primary key autoincrement,"+
                "giaTriGiam integer,"+"tenVoucher text,"+"soLuong integer,"+"trangThai integer)";
        sqLiteDatabase.execSQL(db_voucher);

        String insert_voucher = "insert into Voucher values (1,20000,'Sale 30/4',10,1)";
        sqLiteDatabase.execSQL(insert_voucher);

//        String db_admin = "CREATE TABLE Admin (" +
//                "id integer PRIMARY KEY, " +
//                "username text not null,"+
//                "hoTen TEXT NOT NULL, " +
//                "matKhau TEXT NOT NULL," +
//                "Sdt TEXT NOT NULL," +
//                "diaChi TEXT NOT NULL," +
//               "email text not null)";
//        sqLiteDatabase.execSQL(db_admin);
//
//
//        String db_user = "CREATE TABLE User (" +
//                "id integer PRIMARY KEY AUTOINCREMENT, " +
//                "username text not null,"+
//                "hoTen TEXT NOT NULL, " +
//                "matKhau TEXT NOT NULL," +
//                "Sdt TEXT NOT NULL," +
//                "diaChi TEXT NOT NULL," +
//                "email text not null)";
//        sqLiteDatabase.execSQL(db_user);




        String dbnguoidung = "CREATE TABLE nguoiDung(" +
                "manguoidung INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "hoten TEXT ," +
                "sodienthoai TEXT ," +
                "email TEXT," +
                "diachi TEXT," +
                "loaitaikhoan TEXT)";
        sqLiteDatabase.execSQL(dbnguoidung);
//////////
        sqLiteDatabase.execSQL("INSERT INTO nguoiDung  VALUES(1,'admin','admin','Vu Tien Vinh','0332322764','vinhvtph45732@fpt.edu.vn','Thai Binh','admin'),(2,'vinh','vinh','Vu Tien Vinh','0376937097','vinh@gmail.com','Thai Binh','user')");






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
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Admin");
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS User");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS nguoiDung");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Phone");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Voucher");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Oder");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OderDetail");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Brand");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ShoppingCart");

            onCreate(sqLiteDatabase);
        }




    }

    public Cursor getTenLoaiSanPham() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("Brand", new String[]{"tenHang"}, null, null, null, null, null);
    }

    public Cursor getTenLoaiSanPhamById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("Brand", new String[]{"tenHang"}, "idHang" + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
    }
}

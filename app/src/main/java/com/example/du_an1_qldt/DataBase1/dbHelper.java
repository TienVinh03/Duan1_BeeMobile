package com.example.du_an1_qldt.DataBase1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DB_name = "BeePhones4444";


    public dbHelper(@Nullable Context context) {
        super(context, DB_name, null, 56);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_hang = "create table Brand(idHang integer primary key autoincrement," +
                "tenHang text , heDieuHanh TEXT not null)";
        sqLiteDatabase.execSQL(db_hang);

        String insert_hang = "insert into Brand values (1,'Xiaomi','Android'),(2,'Apple','IOS')";
        sqLiteDatabase.execSQL(insert_hang);


        String db_phone = "create table Phone(maDt integer primary key autoincrement," +
                "tenDt text," +
                "idHang integer not null," +
                "gia integer," +
                "image integer," +
                "rom integer," +
                "mausac text," +
                "trangthai int," +
                "soluong integer," +
                "FOREIGN KEY (idHang) REFERENCES Brand(idHang))";
        sqLiteDatabase.execSQL(db_phone);

        String insert_phone = "insert into Phone Values (1,'K40 Gaming',1,8200000,1,128,'Trắng',1,10),(2,'K50 Gaming',1,12200000,1,128,'Trắng',1,10),(3,'Iphone 15',1,32200000,1,128,'Trắng',1,10)";
        sqLiteDatabase.execSQL(insert_phone);

        String db_cart = "CREATE TABLE IF NOT EXISTS ShoppingCart (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maDt INTEGER not null," +
                "soLuong INTEGER not null," +
                "donGia integer not null," +
                "mauSac TEXT not null," +
                "ram TEXT not null," +
                "idUser INTEGER ," +
                "FOREIGN KEY (idUser) REFERENCES nguoiDung(manguoidung)," +
                "FOREIGN KEY (maDt) REFERENCES Phone(maDt))";
        sqLiteDatabase.execSQL(db_cart);


        String db_voucher = "create table Voucher(idVoucher integer primary key autoincrement," +
                "giaTriGiam integer," + "tenVoucher text," + "soLuong integer," + "trangThai integer)";
        sqLiteDatabase.execSQL(db_voucher);

        String insert_voucher = "insert into Voucher values (1,20,'Sale 30/4',10,1),(2,30,'Sale 1/6',10,1),(3,40,'Sale 1/5',10,1)";
        sqLiteDatabase.execSQL(insert_voucher);


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

        sqLiteDatabase.execSQL("INSERT INTO nguoiDung  VALUES(1,'admin','admin','Vu Tien Vinh','0332322764','vinhvtph45732@fpt.edu.vn','Thai Binh','admin'),(2,'vinh','vinh','Vu Tien Vinh','0376937097','vinh@gmail.com','Thai Binh','user')");


        String db_order_detail = "CREATE TABLE OderDetail (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idSp integer NOT NULL, " +
                "idDonHang integer NOT NULL, " +
                "soLuong integer NOT NULL," +
                "giaTien integer NOT NULL," +
                "FOREIGN KEY (idSp) REFERENCES Phone(maDt)," +
                "FOREIGN KEY (idDonHang) REFERENCES Oder(id))";
        sqLiteDatabase.execSQL(db_order_detail);


        //Vinh them
        String insert_orderDetail = "insert into OderDetail values (1,1,3,10,20000000),(2,1,2,10,42050000.0),(3,1,1,10,10000000) ";
        sqLiteDatabase.execSQL(insert_orderDetail);


        String db_order = "CREATE TABLE Oder (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idUser integer NOT NULL, " +
                "date text NOT NULL, " +
                "status int NOT NULL, " +
                "FOREIGN KEY (idUser) REFERENCES User(id))";
        sqLiteDatabase.execSQL(db_order);

        //Vinh them
        String insert_Order = " insert into Oder values (1,2,'2024-03-09 15:30:00',1),(2,2,'2024-04-10 15:30:00',1),(3,2,'2024-02-09 15:30:00',0) ";
        sqLiteDatabase.execSQL(insert_Order);


        String db_customer = "CREATE TABLE Customer(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "sdt text not null, " +
                "name text not null, " +
                "address text not null)";
        sqLiteDatabase.execSQL(db_customer);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        if (oldV < newV) {
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Admin");
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS User");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS nguoiDung");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Phone");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Voucher");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Oder");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OderDetail");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Brand");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ShoppingCart");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Customer");

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

//    @SuppressLint("Range")
//    public double getTotalPriceForMonth3() {
//        double totalPrice = 0;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT SUM(OderDetail.giaTien) AS total_price "
//                + "FROM Oder JOIN OderDetail ON Oder.id = OderDetail.idDonHang WHERE Oder('%m', Oder.date) = '03'";
//
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
//        }
//        cursor.close();
//
//        return totalPrice;
//    }
}

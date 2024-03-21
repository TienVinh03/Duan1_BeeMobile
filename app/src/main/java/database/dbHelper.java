package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DB_name="BeePhones";
    public dbHelper(@Nullable Context context) {
        super(context, DB_name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_hang="create table Brand(idHang integer primary key autoincrement,"+
                "tenHang text)";
        sqLiteDatabase.execSQL(db_hang);
        String db_phone="create table Phone(maDt integer primary key autoincrement," +
                "tenDt text," +
                "idHang integer not null," +
                "gia real," +
                "mausac text," +
                "trangthai int," +
                "soluong integer,"+
                "FOREIGN KEY (idHang) REFERENCES Brand(idHang))";
        sqLiteDatabase.execSQL(db_phone);
        String db_cart = "CREATE TABLE IF NOT EXISTS ShoppingCart (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maDt INTEGER not null," +
                "ram TEXT not null," +
                "soLuong INTEGER not null," +
                "donGia REAL not null," +
                "mauSac TEXT not null," +
                "FOREIGN KEY (maDt) REFERENCES Phone(maDt))";
        sqLiteDatabase.execSQL(db_cart);
        String db_voucher="create table Voucher(idVoucher integer primary key autoincrement,"+
                "giaTriGiam text)";
        sqLiteDatabase.execSQL(db_voucher);
        String db_admin = "CREATE TABLE Admin (" +
                "id integer PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL," +
                "Sdt TEXT NOT NULL," +
                "diaChi TEXT NOT NULL," +
               "email text not null)";
        sqLiteDatabase.execSQL(db_admin);
        String db_user = "CREATE TABLE User (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL," +
                "Sdt TEXT NOT NULL," +
                "diaChi TEXT NOT NULL," +
                "email text not null)";
        sqLiteDatabase.execSQL(db_user);
        String db_oder_detail ="CREATE TABLE OderDetail (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idSp integer NOT NULL, " +
                "idDonHang integer NOT NULL, " +
                "soLuong integer NOT NULL," +
                "giaTien real NOT NULL," +
                "FOREIGN KEY (idSp) REFERENCES Phone(maDt),"+
                "FOREIGN KEY (idDonHang) REFERENCES DonHang(id))";
        sqLiteDatabase.execSQL(db_oder_detail);
        String db_older="CREATE TABLE Oder (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idAdmin integer NOT NULL, " +
                "idUser integer NOT NULL, " +
                "idSp integer NOT NULL, " +
                "idVoucher integer NOT NULL, " +
                "soLuong integer NOT NULL," +
                "giaTien real NOT NULL," +
                "FOREIGN KEY (idSp) REFERENCES Phone(maDt),"+
                "FOREIGN KEY (idAdmin) REFERENCES Admin(id),"+
                "FOREIGN KEY (idVoucher) REFERENCES Voucher(idVoucher),"+
                "FOREIGN KEY (idUser) REFERENCES User(id))";
        sqLiteDatabase.execSQL(db_older);

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

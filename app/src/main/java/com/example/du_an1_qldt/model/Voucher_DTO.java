package com.example.du_an1_qldt.model;

public class Voucher_DTO {

//    String db_voucher="create table Voucher(idVoucher integer primary key autoincrement,"+
//            "giaTriGiam integer,"+"tenVoucher text,"+"soLuong integer,"+"trangThai integer)";

    public int id;
    public int giaTriGiam;
    public String tenVoucher;
    public int soLuong;

    public void setId(int id) {
        this.id = id;
    }

    public void setGiaTriGiam(int giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public int getGiaTriGiam() {
        return giaTriGiam;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public Voucher_DTO() {
    }

    public Voucher_DTO(int id, int giaTriGiam, String tenVoucher, int soLuong, int trangThai) {
        this.id = id;
        this.giaTriGiam = giaTriGiam;
        this.tenVoucher = tenVoucher;
        this.soLuong = soLuong;
        TrangThai = trangThai;
    }

    public int TrangThai;
}

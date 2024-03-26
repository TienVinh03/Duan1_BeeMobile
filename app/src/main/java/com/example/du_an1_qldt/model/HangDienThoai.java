package com.example.du_an1_qldt.model;

public class HangDienThoai {
    public int idHang;
    public String tenHang;
    public String heDieuHanh;

    public HangDienThoai() {
    }

    public HangDienThoai(String tenHang, String heDieuHanh) {
        this.tenHang = tenHang;
        this.heDieuHanh = heDieuHanh;
    }

    public HangDienThoai(int idHang, String tenHang, String heDieuHanh) {
        this.idHang = idHang;
        this.tenHang = tenHang;
        this.heDieuHanh = heDieuHanh;
    }

    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
        this.idHang = idHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }
}

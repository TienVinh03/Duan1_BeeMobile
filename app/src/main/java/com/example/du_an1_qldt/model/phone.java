package com.example.du_an1_qldt.model;

public class phone {
    private int id;
    private String name;
    private  String color;
    private  int image;
    private int rom;

    private int soLuong;

    private int gia;
    private int id_Hang;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getId_Hang() {
        return id_Hang;
    }

    public void setId_Hang(int id_Hang) {
        this.id_Hang = id_Hang;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public phone() {
    }

    public phone(int id, String name, String color, int image, int rom,int gia, int soLuong, int id_Hang, int status) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.image = image;
        this.rom = rom;
        this.soLuong = soLuong;
        this.id_Hang = id_Hang;
        this.status = status;
        this.gia = gia;
    }
}

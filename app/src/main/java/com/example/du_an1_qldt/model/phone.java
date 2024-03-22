package com.example.du_an1_qldt.model;

public class phone {
    private int id;
    private String name;
    private  String color;
    private  int image;
    private int rom;
    private int soLuong;
    private int id_Hang;
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setId_Hang(int id_Hang) {
        this.id_Hang = id_Hang;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getImage() {
        return image;
    }

    public int getRom() {
        return rom;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getId_Hang() {
        return id_Hang;
    }

    public int getStatus() {
        return status;
    }

    public phone() {
    }

    public phone(int id, String name, String color, int image, int rom, int soLuong, int id_Hang, int status) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.image = image;
        this.rom = rom;
        this.soLuong = soLuong;
        this.id_Hang = id_Hang;
        this.status = status;
    }
}

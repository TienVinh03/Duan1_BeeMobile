package com.example.du_an1_qldt.model;

public class Cart {
    private int id;
    private String name;
    private int idPhone;
    private String color;
    private int rom;
    private int price;
    private int quantity;

    public int getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(int idPhone) {
        this.idPhone = idPhone;
    }

    public Cart() {
    }


    public Cart(int id,String name, int idPhone, String color, int rom, int price, int quantity) {
        this.id=id;
        this.name = name;
        this.idPhone = idPhone;
        this.color = color;
        this.rom = rom;
        this.price = price;
        this.quantity = quantity;
    }

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

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

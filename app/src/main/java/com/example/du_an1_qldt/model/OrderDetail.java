package com.example.du_an1_qldt.model;

public class OrderDetail {
  private   int id;
  private   int idProduct;

  private int idDonHang;
  private   int quantity;
  private   double price;

    public OrderDetail(int id, int idProduct, int quantity, double price) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public OrderDetail(int id, int idProduct, int idDonHang, int quantity, double price) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.idDonHang = idDonHang;
    }

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

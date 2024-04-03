package com.example.du_an1_qldt.model;

public class Order {
    private  int idUser;
    private  int idAdmin;
   private int idOrder;
   private String dateOrder;
   private int statusOrder;

    public Order(int idUser, int idAdmin, int idOrder, String dateOrder, int statusOrder) {
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.statusOrder = statusOrder;
    }

    public Order() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }

}

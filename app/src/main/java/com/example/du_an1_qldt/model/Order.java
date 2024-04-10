package com.example.du_an1_qldt.model;

public class Order {
    private int id;
    private  int idUser;
   private String dateOrder;
   private int statusOrder;

    public Order(int id,int idUser, String dateOrder, int statusOrder) {
        this.id=id;
        this.idUser = idUser;
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


    public String getDateOrder() {
        return dateOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

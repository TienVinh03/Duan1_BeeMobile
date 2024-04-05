package com.example.du_an1_qldt.model;

public class Order {
    private int id;
    private  int idUser;
   private String dateOrder;
   private String statusOrder;

    public Order(int id,int idUser, String dateOrder, String statusOrder) {
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

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

}

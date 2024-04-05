package com.example.du_an1_qldt.DAO;

public class Customer {
    private int id;
    private String customerName;
    private String numberPhone;
    private String address;

    public Customer(int id, String customerName, String numberPhone, String address) {
        this.id = id;
        this.customerName = customerName;
        this.numberPhone = numberPhone;
        this.address = address;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
